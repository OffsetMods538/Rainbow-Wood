package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.util.NamedColor;

import java.util.List;

public interface ITintedBlockItem extends ItemConvertible {
    default ItemStack getStackWithTint(final int tint) {
        final ItemStack stack = ((Item) this).getDefaultStack();
        stack.set(ModComponents.TINT_COLOR, tint);
        return stack;
    }

    default void appendTintedTooltip(ItemStack stack, List<Text> tooltip) {
        final Integer tintColor = stack.get(ModComponents.TINT_COLOR);
        if (tintColor == null) {
            tooltip.add(Text.translatable("general.rainbow_wood.tooltip.uncolored"));
            return;
        }

        tooltip.add(Text.translatable("general.rainbow_wood.tooltip.color", Text.literal("#%06X".formatted(tintColor)).withColor(tintColor)));

        final NamedColor closestColor = NamedColor.getClosestNamedColor(tintColor);
        tooltip.add(Text.translatable("general.rainbow_wood.tooltip.closest.named_color", Text.translatable(closestColor.translation).withColor(closestColor.color)));
    }
}
