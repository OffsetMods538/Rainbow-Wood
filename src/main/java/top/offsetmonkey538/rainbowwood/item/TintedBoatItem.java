package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class TintedBoatItem extends BoatItem implements ITintedItem {
    public TintedBoatItem(boolean chest, BoatEntity.Type type, Settings settings) {
        super(chest, type, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        appendTintedTooltip(stack, tooltip);
    }
}
