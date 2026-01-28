package top.offsetmonkey538.rainbowwood.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.chars.CharArraySet;
import it.unimi.dsi.fastutil.chars.CharSet;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.ITintedItem;
import top.offsetmonkey538.rainbowwood.mixin.recipe.RawShapedRecipeAccessor;
import top.offsetmonkey538.rainbowwood.mixin.recipe.RawShapedRecipeDataAccessor;

import java.util.*;


public class TintedShapedRecipe implements CraftingRecipe {
    private final String group;
    private final CraftingRecipeCategory category;
    private final List<String> pattern;
    private final @Nullable Map<Character, Ingredient> ingredientMap;
    private final int width, height;
    private final DefaultedList<Ingredient> ingredients;
    private final Item result;
    private final int resultCount;


    public TintedShapedRecipe(String group, CraftingRecipeCategory category, List<String> pattern, @NotNull Map<Character, Ingredient> ingredientMap, Item result, int resultCount) {
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.ingredientMap = ingredientMap;
        this.result = result;
        this.resultCount = resultCount;

        final String[] unpaddedPattern = RawShapedRecipeAccessor.removePadding(pattern);
        this.width = unpaddedPattern[0].length();
        this.height = unpaddedPattern.length;
        this.ingredients = createIngredientList(unpaddedPattern, ingredientMap);
    }

    private TintedShapedRecipe(String group, CraftingRecipeCategory category, List<String> pattern, DefaultedList<Ingredient> ingredients, Item result, int resultCount) {
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.ingredientMap = null;
        this.ingredients = ingredients;
        this.result = result;
        this.resultCount = resultCount;

        final String[] unpaddedPattern = RawShapedRecipeAccessor.removePadding(pattern);
        this.width = unpaddedPattern[0].length();
        this.height = unpaddedPattern.length;
    }

    private DefaultedList<Ingredient> createIngredientList(final String[] noPadPattern, final Map<Character, Ingredient> ingredientMap) {
        final DefaultedList<Ingredient> result = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
        final CharSet charSet = new CharArraySet(ingredientMap.keySet());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char character = noPadPattern[y].charAt(x);
                final Ingredient ingredient = character == ' ' ? Ingredient.EMPTY : ingredientMap.get(character);

                if (ingredient == null) throw new IllegalArgumentException("Pattern references symbol '%s' but it's not defined".formatted(character));

                charSet.remove(character);
                result.set(x + width * y, ingredient);
            }
        }

        if (!charSet.isEmpty()) throw new IllegalArgumentException("Symbols defined that aren't used in pattern: %s".formatted(charSet));
        return result;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return category;
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getStackCount() != ingredients.stream().filter(ingredient -> !ingredient.isEmpty()).count()) return false;

        if (input.getWidth() == width && input.getHeight() == height) {
            if (!Util.isSymmetrical(width, height, ingredients) && this.matches(input, true)) return true;

            return this.matches(input, false);
        }

        return false;
    }

    private boolean matches(CraftingRecipeInput input, boolean mirrored) {
        ItemStack firstTintedItem = null;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Ingredient ingredient;

                if (mirrored) ingredient = ingredients.get(width - x - 1 + y * width);
                else ingredient = ingredients.get(x + y * width);

                final ItemStack stack = input.getStackInSlot(x, y);

                if (stack.getItem() instanceof ITintedItem) {
                    if (firstTintedItem == null) firstTintedItem = stack;

                    if (!Objects.equals(stack.get(ModComponents.TINT_COLOR), firstTintedItem.get(ModComponents.TINT_COLOR))) return false;
                }

                if (!ingredient.test(stack)) return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        boolean found;
        for (final ItemStack stack : input.getStacks()) {
            found = stack.getItem() instanceof ITintedItem;
            if (found) return stack.copyComponentsToNewStack(result, resultCount);
        }
        return new ItemStack(result, resultCount);
    }

    @Override
    public boolean fits(int width, int height) {
        return height == pattern.size() && width == pattern.get(0).length();
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return result.getDefaultStack().copyWithCount(resultCount);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TINTED_SHAPED;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DefaultedList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Item getResult() {
        return result;
    }

    public int getResultCount() {
        return resultCount;
    }

    public static class Serializer implements RecipeSerializer<TintedShapedRecipe> {
        public static final MapCodec<TintedShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                                CraftingRecipeCategory.CODEC.fieldOf("category").orElse(CraftingRecipeCategory.MISC).forGetter(recipe -> recipe.category),
                                Codec.STRING.listOf().fieldOf("pattern").forGetter(recipe -> recipe.pattern),
                                Codecs.strictUnboundedMap(RawShapedRecipeDataAccessor.getKEY_ENTRY_CODEC(), Ingredient.DISALLOW_EMPTY_CODEC).fieldOf("ingredients").forGetter(recipe -> recipe.ingredientMap),
                                Registries.ITEM.getCodec().fieldOf("result").forGetter(recipe -> recipe.result),
                                Codec.INT.fieldOf("resultCount").forGetter(recipe -> recipe.resultCount)
                        )
                        .apply(instance, TintedShapedRecipe::new)
        );

        public static final PacketCodec<RegistryByteBuf, TintedShapedRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                Serializer::write, Serializer::read
        );


        private static void write(RegistryByteBuf buf, TintedShapedRecipe recipe) {
            buf.writeString(recipe.group);
            buf.writeEnumConstant(recipe.category);

            buf.writeInt(recipe.pattern.size());
            for (final String patternLine : recipe.pattern) {
                buf.writeString(patternLine);
            }

            buf.writeInt(recipe.width);
            buf.writeInt(recipe.height);
            for (final Ingredient ingredient : recipe.ingredients) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }

            buf.writeRegistryKey(Registries.ITEM.getKey(recipe.result).orElseThrow());
            buf.writeInt(recipe.resultCount);
        }

        private static TintedShapedRecipe read(RegistryByteBuf buf) {
            final String group = buf.readString();
            final CraftingRecipeCategory category = buf.readEnumConstant(CraftingRecipeCategory.class);

            final int patternSize = buf.readInt();
            final List<String> pattern = new ArrayList<>(patternSize);
            for (int i = 0; i < patternSize; i++) {
                pattern.add(buf.readString());
            }

            final int width = buf.readInt();
            final int height = buf.readInt();
            final DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
            for (int i = 0; i < width * height; i++) {
                ingredients.set(i, Ingredient.PACKET_CODEC.decode(buf));
            }

            final Item result = Registries.ITEM.get(buf.readRegistryKey(RegistryKeys.ITEM));
            final int resultCount = buf.readInt();

            return new TintedShapedRecipe(group, category, pattern, ingredients, result, resultCount);
        }

        @Override
        public MapCodec<TintedShapedRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, TintedShapedRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}
