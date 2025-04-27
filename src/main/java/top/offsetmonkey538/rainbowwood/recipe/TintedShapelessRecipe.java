package top.offsetmonkey538.rainbowwood.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.ITintedItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class TintedShapelessRecipe implements CraftingRecipe {
    private final String group;
    private final CraftingRecipeCategory category;
    private final DefaultedList<Ingredient> ingredients;
    private final Item result;
    private final int resultCount;

    public TintedShapelessRecipe(String group, CraftingRecipeCategory category, DefaultedList<Ingredient> ingredients, Item result, int resultCount) {
        this.group = group;
        this.category = category;
        this.ingredients = ingredients;
        this.result = result;
        this.resultCount = resultCount;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return category;
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getStackCount() != this.ingredients.size()) return false;

        return input.getSize() == 1 && this.ingredients.size() == 1 ? ingredients.get(0).test(input.getStackInSlot(0)) : matches(input);
    }

    private boolean matches(CraftingRecipeInput input) {
        final List<Ingredient> requiredIngredients = new ArrayList<>(ingredients);
        ItemStack firstTintedItem = null;

        for (int x = 0; x < input.getWidth(); x++) {
            for (int y = 0; y < input.getHeight(); y++) {
                final ItemStack stack = input.getStackInSlot(x, y);
                if (stack.isEmpty()) continue;

                final Optional<Ingredient> optionalMatchingIngredient = requiredIngredients.stream().filter(ingredient -> ingredient.test(stack)).findFirst();
                if (optionalMatchingIngredient.isEmpty()) return false;
                final Ingredient matchingIngredient = optionalMatchingIngredient.get();

                if (!requiredIngredients.remove(matchingIngredient)) return false;

                if (stack.getItem() instanceof ITintedItem) {
                    if (firstTintedItem == null) firstTintedItem = stack;

                    if (!Objects.equals(stack.get(ModComponents.TINT_COLOR), firstTintedItem.get(ModComponents.TINT_COLOR))) return false;
                }
            }
        }

        return requiredIngredients.isEmpty();
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
        return width * height >= ingredients.size();
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return result.getDefaultStack().copyWithCount(resultCount);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TINTED_SHAPELESS;
    }

    public static class Serializer implements RecipeSerializer<TintedShapelessRecipe> {
        public static final MapCodec<TintedShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                                CraftingRecipeCategory.CODEC.fieldOf("category").orElse(CraftingRecipeCategory.MISC).forGetter(recipe -> recipe.category),
                                Ingredient.DISALLOW_EMPTY_CODEC
                                        .listOf()
                                        .fieldOf("ingredients")
                                        .flatXmap(
                                                ingredients -> {
                                                    final Ingredient[] ingredientsArray = ingredients.stream().filter(ingredient -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                                                    if (ingredientsArray.length == 0) return DataResult.error(() -> "No ingredients for tinted shapeless recipe");

                                                    return DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredientsArray));
                                                },
                                                DataResult::success
                                        )
                                        .forGetter(recipe -> recipe.ingredients),
                                Registries.ITEM.getCodec().fieldOf("result").forGetter(recipe -> recipe.result),
                                Codec.INT.fieldOf("resultCount").forGetter(recipe -> recipe.resultCount)
                        )
                        .apply(instance, TintedShapelessRecipe::new)
        );

        public static final PacketCodec<RegistryByteBuf, TintedShapelessRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                Serializer::write, Serializer::read
        );


        private static void write(RegistryByteBuf buf, TintedShapelessRecipe recipe) {
            buf.writeString(recipe.group);
            buf.writeEnumConstant(recipe.category);

            buf.writeInt(recipe.ingredients.size());
            for (final Ingredient ingredient : recipe.ingredients) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }

            buf.writeRegistryKey(Registries.ITEM.getKey(recipe.result).orElseThrow());
            buf.writeInt(recipe.resultCount);
        }

        private static TintedShapelessRecipe read(RegistryByteBuf buf) {
            final String group = buf.readString();
            final CraftingRecipeCategory category = buf.readEnumConstant(CraftingRecipeCategory.class);

            final int ingredientCount = buf.readInt();
            final DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(ingredientCount, Ingredient.EMPTY);
            for (int i = 0; i < ingredientCount; i++) {
                ingredients.set(i, Ingredient.PACKET_CODEC.decode(buf));
            }

            final Item result = Registries.ITEM.get(buf.readRegistryKey(RegistryKeys.ITEM));
            final int resultCount = buf.readInt();

            return new TintedShapelessRecipe(group, category, ingredients, result, resultCount);
        }

        @Override
        public MapCodec<TintedShapelessRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, TintedShapelessRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}
