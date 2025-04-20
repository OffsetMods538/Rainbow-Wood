package top.offsetmonkey538.rainbowwood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.block.entity.ITintedBlockEntity;
import top.offsetmonkey538.rainbowwood.block.entity.TintedBlockEntity;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.ITintedBlockItem;

public interface ITintedBlock extends BlockEntityProvider, ItemConvertible {
    default void placeTinted(World world, BlockPos pos, BlockState state, ItemStack itemStack) {
        final Integer color = itemStack.get(ModComponents.TINT_COLOR);
        if (color == null) return;

        final BlockEntity optionalBlockEntity = world.getBlockEntity(pos);
        if (!(optionalBlockEntity instanceof ITintedBlockEntity tintedBlockEntity)) return;

        tintedBlockEntity.setTint(color);

        ((BlockEntity) tintedBlockEntity).markDirty();
        world.updateListeners(pos, state, state, Block.NOTIFY_ALL_AND_REDRAW);
    }

    default ItemStack getTintedPickStack(final WorldView world, final BlockState state, final BlockPos pos) {
        if (!(asItem() instanceof ITintedBlockItem tintedItem)) return asItem().getDefaultStack();

        int tint = getTint(world, state, pos);

        if (tint == -1) return asItem().getDefaultStack();
        return tintedItem.getStackWithTint(tint);
    }

    default int getTint(final BlockView world, final BlockState state, final BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof ITintedBlockEntity tintedBlockEntity) return tintedBlockEntity.getTint();
        return -1;
    }

    default @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TintedBlockEntity(pos, state);
    }
}
