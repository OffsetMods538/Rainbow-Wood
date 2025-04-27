package top.offsetmonkey538.rainbowwood.recipe;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.ITintedBlockItem;

import java.util.Objects;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public class ColoringRecipe extends SpecialCraftingRecipe {
    private static final Ingredient ADDITION_MODIFIER = Ingredient.fromTag(ConventionalItemTags.IRON_INGOTS); // Maybe some other item?
    private final ITintedBlockItem forItem;

    public ColoringRecipe(CraftingRecipeCategory category, ITintedBlockItem forItem) {
        super(category);
        this.forItem = forItem;
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        int additionModifierCount = 0;
        int forItemCount = 0;
        boolean foundDye = false;
        boolean forItemsEqual = true;
        ItemStack firstForItem = null;

        for (int i = 0; i < input.getStacks().size(); i++) {
            final ItemStack stack = input.getStacks().get(i);
            if (stack.isEmpty()) continue;

            if (stack.isOf(forItem.asItem())) {
                if (firstForItem == null) firstForItem = stack;

                forItemsEqual = forItemsEqual && Objects.equals(stack.get(ModComponents.TINT_COLOR), firstForItem.get(ModComponents.TINT_COLOR));
                forItemCount++;
                continue;
            }

            if (stack.getItem() instanceof DyeItem) {
                foundDye = true;
                continue;
            }

            if (ADDITION_MODIFIER.test(stack)) {
                additionModifierCount++;
                if (additionModifierCount > 1) return false;
                continue;
            }

            return false;
        }

        // Matches if:
        //  1) Doesn't have dye but has at least 2 different tint of the item. Can't combine em if there's nothing to combine xD
        //  2) Doesn't have dye but has at least 2 same or different tint of the item AND also a modifier.
        //  3) Found a dye and has at least 1 of the item.
        return (!foundDye && forItemCount >= 2 && (!forItemsEqual || additionModifierCount == 1)) || (foundDye && forItemCount >= 1);
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        int colorR = 0, colorG = 0, colorB = 0, colorAmount = 0, forItemAmount = 0;
        boolean additive = false;

        for (int i = 0; i < input.getStacks().size(); i++) {
            final ItemStack stack = input.getStacks().get(i);

            if (ADDITION_MODIFIER.test(stack)) {
                additive = true;
                continue;
            }

            if (stack.isOf(forItem.asItem())) {
                final Integer color = stack.get(ModComponents.TINT_COLOR);

                if (color == null) {
                    forItemAmount++;
                    continue;
                }

                colorAmount++;
                forItemAmount++;
                colorR += ColorHelper.Argb.getRed(color);
                colorG += ColorHelper.Argb.getGreen(color);
                colorB += ColorHelper.Argb.getBlue(color);
            }

            if (stack.getItem() instanceof DyeItem dye) {
                final int color = dye.getColor().getSignColor();

                colorAmount++;
                colorR += ColorHelper.Argb.getRed(color);
                colorG += ColorHelper.Argb.getGreen(color);
                colorB += ColorHelper.Argb.getBlue(color);
            }
        }

        if (!additive) {
            colorR /= colorAmount;
            colorG /= colorAmount;
            colorB /= colorAmount;
        }
        final int color = ColorHelper.Argb.getArgb(0, Math.min(colorR, 0xFF), Math.min(colorG, 0xFF), Math.min(colorB, 0xFF));

        return forItem.getStackWithTint(color).copyWithCount(forItemAmount);
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registries.RECIPE_SERIALIZER.get(id(ModRecipes.COLORING_ID_FORMATTING.formatted(Registries.ITEM.getId(forItem.asItem()).getPath())));
    }
}
