package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

import java.util.List;

public interface ITintedBlockItem extends ItemConvertible {
    default ItemStack getStackWithTint(final int tint) {
        final ItemStack stack = ((Item) this).getDefaultStack();
        stack.set(ModComponents.TINT_COLOR, tint);
        return stack;
    }

    default void appendTintedTooltip(ItemStack stack, List<Text> tooltip) {
        final Integer tintColor = stack.get(ModComponents.TINT_COLOR);
        if (tintColor == null) return;

        tooltip.add(Text.translatable("general.rainbow_wood.tooltip.color", Text.literal("#%06X".formatted(tintColor)).withColor(tintColor)));
    }
}
