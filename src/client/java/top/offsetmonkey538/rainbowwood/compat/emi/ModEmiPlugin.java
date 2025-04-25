package top.offsetmonkey538.rainbowwood.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.RecipeInput;
import top.offsetmonkey538.rainbowwood.compat.emi.recipe.EmiTintedShapedRecipe;
import top.offsetmonkey538.rainbowwood.recipe.TintedShapedRecipe;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public class ModEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        for (RecipeEntry<CraftingRecipe> recipe : registry.getRecipeManager().listAllOfType(RecipeType.CRAFTING)) {
            if (!(recipe.value() instanceof TintedShapedRecipe tintedShapedRecipe)) continue;

            registry.addRecipe(new EmiTintedShapedRecipe(tintedShapedRecipe, id("/tinted_shaped_recipe/%s".formatted(recipe.id().getPath()))));
        }
    }

    /*
    private static void addWashingRecipe(final EmiRegistry registry, final Item input, final Item output) {
        if (!(input instanceof ITintedBlockItem tintedInput)) throw new IllegalArgumentException("Washing recipe input must be an ITintedBlockItem, got '%s'".formatted(input));

        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                        .id("/washing/%s".formatted(i))
                .build()
        );
    }
    */

    private static <C extends RecipeInput, T extends Recipe<C>> Iterable<T> getRecipes(EmiRegistry registry, RecipeType<T> type) {
        return registry.getRecipeManager().listAllOfType(type).stream().map(RecipeEntry::value)::iterator;
    }
}
