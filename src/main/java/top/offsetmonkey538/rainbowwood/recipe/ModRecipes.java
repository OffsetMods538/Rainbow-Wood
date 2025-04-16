package top.offsetmonkey538.rainbowwood.recipe;

import com.mojang.datafixers.util.Pair;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModRecipes {
    private ModRecipes() {

    }

    public static final List<Pair<Function<CraftingRecipeCategory, ? extends SpecialCraftingRecipe>, String>> RECIPES = new LinkedList<>();

    public static final SpecialRecipeSerializer<PlanksFromLog> PLANKS_FROM_LOG = register("planks_from_log", PlanksFromLog::new);

    @SuppressWarnings("SameParameterValue")
    private static <T extends SpecialCraftingRecipe> SpecialRecipeSerializer<T> register(String name, Function<CraftingRecipeCategory, T> recipeFactory) {
        RECIPES.add(Pair.of(recipeFactory, name));
        return Registry.register(Registries.RECIPE_SERIALIZER, id(name), new SpecialRecipeSerializer<>(recipeFactory::apply));
    }

    @SuppressWarnings("EmptyMethod")
    public static void register() {
        // Registers recipe serializers by loading the class.
    }
}
