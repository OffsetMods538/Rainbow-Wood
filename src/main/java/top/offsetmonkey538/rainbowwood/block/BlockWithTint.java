package top.offsetmonkey538.rainbowwood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.attachment.ModAttachmentTypes;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

public class BlockWithTint extends Block {
    public BlockWithTint(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.isClient()) return;

        world.setAttached(ModAttachmentTypes.BLOCK_TINT_TYPE, world.getAttachedOrCreate(ModAttachmentTypes.BLOCK_TINT_TYPE).addBlockTint(pos, itemStack.getOrDefault(ModComponents.TINT_COLOR, 0)));

    }
}
