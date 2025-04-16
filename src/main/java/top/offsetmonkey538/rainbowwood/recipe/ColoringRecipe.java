package top.offsetmonkey538.rainbowwood.recipe;

import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.TintedBlockItem;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public class ColoringRecipe extends SpecialCraftingRecipe {
    private final TintedBlockItem forItem;

    public ColoringRecipe(CraftingRecipeCategory category, TintedBlockItem forItem) {
        super(category);
        this.forItem = forItem;
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        int forItemCount = 0;
        boolean foundDye = false;

        for (int i = 0; i < input.getStacks().size(); i++) {
            final ItemStack stack = input.getStacks().get(i);
            if (stack.isEmpty()) continue;

            if (stack.isOf(forItem)) {
                if (stack.get(ModComponents.TINT_COLOR) == null) return false;
                forItemCount++;
                continue;
            }

            if (stack.getItem() instanceof DyeItem) {
                foundDye = true;
                continue;
            }

            return false;
        }

        // Matches if:
        //  1) Doesn't have dye but has at least 2 of the item. Can't combine em if there's nothing to combine xD
        //  2) Found a dye and has at least 1 of the item.
        return (!foundDye && forItemCount >= 2) || (foundDye && forItemCount >= 1);
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        int colorR = 0, colorG = 0, colorB = 0, colorAmount = 0, forItemAmount = 0;

        for (int i = 0; i < input.getStacks().size(); i++) {
            final ItemStack stack = input.getStacks().get(i);

            if (stack.isOf(forItem)) {
                final Integer color = stack.get(ModComponents.TINT_COLOR);
                assert color != null;

                colorAmount++;
                forItemAmount++;
                colorR += ColorHelper.Argb.getRed(color);
                colorG += ColorHelper.Argb.getGreen(color);
                colorB += ColorHelper.Argb.getBlue(color);
            }

            if (stack.getItem() instanceof DyeItem dye) {
                final int color = dye.getColor().getSignColor();

                colorAmount++;
                forItemAmount++;
                colorR += ColorHelper.Argb.getRed(color);
                colorG += ColorHelper.Argb.getGreen(color);
                colorB += ColorHelper.Argb.getBlue(color);
            }
        }

        colorR /= colorAmount;
        colorG /= colorAmount;
        colorB /= colorAmount;
        final int color = ColorHelper.Argb.getArgb(0, colorR, colorG, colorB);

        return forItem.getStackWithTint(color).copyWithCount(forItemAmount);
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registries.RECIPE_SERIALIZER.get(id("%s_coloring".formatted(Registries.ITEM.getId(forItem).getPath())));
    }
}
