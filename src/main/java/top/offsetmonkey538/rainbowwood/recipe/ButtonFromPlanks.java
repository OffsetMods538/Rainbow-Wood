package top.offsetmonkey538.rainbowwood.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
/*
public class ButtonFromPlanks extends SpecialCraftingRecipe {
    public ButtonFromPlanks(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        return input.getStackCount() == 1 && input.getStacks().get(0).isOf(ModBlocks.RAINBOW_PLANKS.asItem());
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return input.getStacks().get(0).copyComponentsToNewStack(ModBlocks.RAINBOW_BUTTON, 1);
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.BUTTON_FROM_PLANKS;
    }
}
*/