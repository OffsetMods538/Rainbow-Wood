package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.block.Block;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class TintedHangingSignItem extends HangingSignItem implements ITintedItem {
    public TintedHangingSignItem(Block hangingSign, Block wallHangingSign, Settings settings) {
        super(hangingSign, wallHangingSign, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        appendTintedTooltip(stack, tooltip);
    }
}
