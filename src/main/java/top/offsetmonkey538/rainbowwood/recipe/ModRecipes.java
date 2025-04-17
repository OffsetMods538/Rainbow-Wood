package top.offsetmonkey538.rainbowwood.recipe;

import com.mojang.datafixers.util.Pair;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.item.TintedBlockItem;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModRecipes {
    private ModRecipes() {

    }

    public static final String COLORING_ID_FORMATTING = "coloring/%s";

    public static final List<Pair<Function<CraftingRecipeCategory, ? extends SpecialCraftingRecipe>, String>> RECIPES = new LinkedList<>();

    public static final RecipeSerializer<TintedShapedRecipe> TINTED_SHAPED = register("crafting_tinted_shaped", new TintedShapedRecipe.Serializer());

    //public static final SpecialRecipeSerializer<PlanksFromLog> PLANKS_FROM_LOG = registerSpecial("planks_from_log", PlanksFromLog::new);
    //public static final SpecialRecipeSerializer<ButtonFromPlanks> BUTTON_FROM_PLANKS = registerSpecial("button_from_planks", ButtonFromPlanks::new);

    // Coloring
    static {
        ModBlocks.BLOCKS.forEach(ModRecipes::registerColoring);
    }


    private static <T extends RecipeSerializer<?>> T register(String name, T serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, id(name), serializer);
    }

    private static <T extends SpecialCraftingRecipe> SpecialRecipeSerializer<T> registerSpecial(String name, Function<CraftingRecipeCategory, T> recipeFactory) {
        RECIPES.add(Pair.of(recipeFactory, name));
        return register(name, new SpecialRecipeSerializer<>(recipeFactory::apply));
    }

    private static void registerColoring(ItemConvertible forItem) {
        if (!(forItem.asItem() instanceof TintedBlockItem tintedForItem)) {
            throw new IllegalArgumentException("Expected 'Item' for '%s' to be a 'TintedBlockItem', got '%s'!".formatted(forItem, forItem.asItem()));
        }

        registerSpecial(COLORING_ID_FORMATTING.formatted(Registries.ITEM.getId(tintedForItem).getPath()), craftingRecipeCategory -> new ColoringRecipe(craftingRecipeCategory, tintedForItem));
    }

    @SuppressWarnings("EmptyMethod")
    public static void register() {
        // Registers recipe serializers by loading the class.
    }
}
