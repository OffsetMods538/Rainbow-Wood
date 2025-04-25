package top.offsetmonkey538.rainbowwood.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.util.Identifier;
import top.offsetmonkey538.rainbowwood.block.ITintedBlock;
import top.offsetmonkey538.rainbowwood.compat.emi.recipe.EmiTintedShapedRecipe;
import top.offsetmonkey538.rainbowwood.item.ITintedBlockItem;
import top.offsetmonkey538.rainbowwood.recipe.ModRecipes;
import top.offsetmonkey538.rainbowwood.recipe.TintedShapedRecipe;
import top.offsetmonkey538.rainbowwood.tag.ModItemTags;

import java.util.List;

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
