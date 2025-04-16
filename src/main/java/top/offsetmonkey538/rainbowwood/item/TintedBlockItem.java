package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

public class TintedBlockItem extends BlockItem {
    public TintedBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public ItemStack getStackWithTint(final int tint) {
        final ItemStack result = getDefaultStack();
        result.set(ModComponents.TINT_COLOR, tint);
        return result;
    }
}
