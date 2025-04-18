package top.offsetmonkey538.rainbowwood.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TintedShapedRecipe implements CraftingRecipe {
    private final String group;
    private final CraftingRecipeCategory category;
    private final List<String> pattern;
    private final Ingredient ingredient;
    private final Item result;
    private final int resultCount;


    public TintedShapedRecipe(String group, CraftingRecipeCategory category, List<String> pattern, Ingredient input, Item result, int resultCount) {
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.ingredient = input;
        this.result = result;
        this.resultCount = resultCount;

        for (String string : pattern) {
            for (char chara : string.toCharArray()) {
                if (chara != '#' && chara != ' ') throw new IllegalArgumentException("Tinted shaped recipe pattern may only contain '#' and ' ', got '%s'".formatted(chara));
            }
        }
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return category;
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        final String[] patternNoPadding = RawShapedRecipe.removePadding(pattern);
        final int width = patternNoPadding[0].length();
        final int height = patternNoPadding.length;

        if (input.getStackCount() != Arrays.stream(patternNoPadding).flatMapToInt(String::chars).filter(character -> character == '#').count()) return false;

        if (input.getWidth() == width && input.getHeight() == height) {
            if (!Util.isSymmetrical(width, height, Arrays.stream(patternNoPadding).flatMapToInt(String::chars).boxed().toList()) && this.matches(input, true)) return true;

            return this.matches(input, false);
        }

        return false;
    }

    private boolean matches(CraftingRecipeInput input, boolean mirrored) {
        final int height = pattern.size();
        final int width = pattern.get(0).length();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final char ingredientChar;

                if (mirrored) ingredientChar = pattern.get(y).charAt(width - x - 1);
                else ingredientChar = pattern.get(y).charAt(x);

                final ItemStack stack = input.getStackInSlot(x, y);
                if (ingredientChar == ' ' ? !stack.isEmpty() : !ingredient.test(stack)) return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return input.getStacks().get(0).copyComponentsToNewStack(result, resultCount);
    }

    @Override
    public boolean fits(int width, int height) {
        return height == pattern.size() && width == pattern.get(0).length();
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return result.getDefaultStack();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TINTED_SHAPED;
    }

    public static class Serializer implements RecipeSerializer<TintedShapedRecipe> {
        public static final MapCodec<TintedShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                                CraftingRecipeCategory.CODEC.fieldOf("category").orElse(CraftingRecipeCategory.MISC).forGetter(recipe -> recipe.category),
                                Codec.STRING.listOf().fieldOf("pattern").forGetter(recipe -> recipe.pattern),
                                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
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
            Ingredient.PACKET_CODEC.encode(buf, recipe.ingredient);
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
            final Ingredient ingredient = Ingredient.PACKET_CODEC.decode(buf);
            final Item result = Registries.ITEM.get(buf.readRegistryKey(RegistryKeys.ITEM));
            final int resultCount = buf.readInt();

            return new TintedShapedRecipe(group, category, pattern, ingredient, result, resultCount);
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
