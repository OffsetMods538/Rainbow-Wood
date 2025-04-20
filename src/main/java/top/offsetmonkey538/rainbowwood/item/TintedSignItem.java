package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SignItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class TintedSignItem extends SignItem implements ITintedBlockItem {
    public TintedSignItem(Settings settings, Block standingBlock, Block wallBlock) {
        super(settings, standingBlock, wallBlock);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        appendTintedTooltip(stack, tooltip);
    }
}
