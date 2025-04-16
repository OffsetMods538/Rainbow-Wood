package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

import java.util.List;

public class TintedBlockItem extends BlockItem {
    public TintedBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public ItemStack getStackWithTint(final int tint) {
        final ItemStack result = getDefaultStack();
        result.set(ModComponents.TINT_COLOR, tint);
        return result;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        final Integer tintColor = stack.get(ModComponents.TINT_COLOR);
        if (tintColor == null) return;

        tooltip.add(Text.translatable("general.rainbow_wood.tooltip.color", Text.literal("#%06X".formatted(tintColor)).withColor(tintColor)));
    }
}
