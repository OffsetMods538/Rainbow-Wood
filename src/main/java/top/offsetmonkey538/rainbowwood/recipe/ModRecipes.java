package top.offsetmonkey538.rainbowwood.recipe;

import com.mojang.datafixers.util.Pair;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.rainbowwood.item.ITintedItem;
import top.offsetmonkey538.rainbowwood.item.ModItems;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModRecipes {
    private ModRecipes() {

    }

    public static final String COLORING_ID_FORMATTING = "coloring/%s";

    public static final List<Pair<Function<CraftingRecipeCategory, ? extends SpecialCraftingRecipe>, String>> COLORING_RECIPES = new LinkedList<>();

    public static final RecipeSerializer<TintedShapedRecipe> TINTED_SHAPED = register("crafting_tinted_shaped", new TintedShapedRecipe.Serializer());
    public static final RecipeSerializer<TintedShapelessRecipe> TINTED_SHAPELESS = register("crafting_tinted_shapeless", new TintedShapelessRecipe.Serializer());

    // Coloring
    static {
        ModItems.ITEMS.forEach(ModRecipes::registerColoring);
    }


    private static <T extends RecipeSerializer<?>> T register(String name, T serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, id(name), serializer);
    }

    private static void registerColoring(ItemConvertible forItem) {
        if (!(forItem.asItem() instanceof ITintedItem tintedForItem)) {
            throw new IllegalArgumentException("Expected 'Item' for '%s' to be a 'ITintedBlockItem', got '%s'!".formatted(forItem, forItem.asItem()));
        }

        final Function<CraftingRecipeCategory, SpecialCraftingRecipe> factory = craftingRecipeCategory -> new ColoringRecipe(craftingRecipeCategory, tintedForItem);
        final String name = COLORING_ID_FORMATTING.formatted(Registries.ITEM.getId(forItem.asItem()).getPath());

        COLORING_RECIPES.add(Pair.of(factory, COLORING_ID_FORMATTING.formatted(Registries.ITEM.getId(forItem.asItem()).getPath())));
        register(name, new SpecialRecipeSerializer<>(factory::apply));
    }

    @SuppressWarnings("EmptyMethod")
    public static void register() {
        // Registers recipe serializers by loading the class.
    }
}
