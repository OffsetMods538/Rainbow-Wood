package top.offsetmonkey538.rainbowwood.recipe;

import com.mojang.datafixers.util.Pair;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
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

    public static final List<Pair<Function<CraftingRecipeCategory, ? extends SpecialCraftingRecipe>, String>> RECIPES = new LinkedList<>();

    public static final SpecialRecipeSerializer<PlanksFromLog> PLANKS_FROM_LOG = register("planks_from_log", PlanksFromLog::new);
    public static final SpecialRecipeSerializer<ButtonFromPlanks> BUTTON_FROM_PLANKS = register("button_from_planks", ButtonFromPlanks::new);

    // Coloring TODO: probably do this using the block list from ModBlocks?
    public static final SpecialRecipeSerializer<ColoringRecipe> RAINBOW_LOG_COLORING = registerColoring(ModBlocks.RAINBOW_LOG);
    public static final SpecialRecipeSerializer<ColoringRecipe> RAINBOW_PLANKS_COLORING = registerColoring(ModBlocks.RAINBOW_PLANKS);
    public static final SpecialRecipeSerializer<ColoringRecipe> RAINBOW_BUTTON_COLORING = registerColoring(ModBlocks.RAINBOW_BUTTON);


    private static <T extends SpecialCraftingRecipe> SpecialRecipeSerializer<T> register(String name, Function<CraftingRecipeCategory, T> recipeFactory) {
        RECIPES.add(Pair.of(recipeFactory, name));
        return Registry.register(Registries.RECIPE_SERIALIZER, id(name), new SpecialRecipeSerializer<>(recipeFactory::apply));
    }

    private static SpecialRecipeSerializer<ColoringRecipe> registerColoring(ItemConvertible forItem) {
        if (!(forItem.asItem() instanceof TintedBlockItem tintedForItem)) {
            throw new IllegalArgumentException("Expected 'Item' for '%s' to be a 'TintedBlockItem', got '%s'!".formatted(forItem, forItem.asItem()));
        }

        return register("%s_coloring".formatted(Registries.ITEM.getId(tintedForItem).getPath()), craftingRecipeCategory -> new ColoringRecipe(craftingRecipeCategory, tintedForItem));
    }

    @SuppressWarnings("EmptyMethod")
    public static void register() {
        // Registers recipe serializers by loading the class.
    }
}
