package top.offsetmonkey538.rainbowwood.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ITintedBlockEntity {
    String BLOCK_TINT_KEY = "BlockTint";

    int getTint();
    void setTint(int newTint);

    default void writeTintedNbt(NbtCompound nbt) {
        nbt.putInt(BLOCK_TINT_KEY, getTint());
    }

    default void readTintedNbt(NbtCompound nbt, World world, BlockPos pos, BlockState state) {
        if (!nbt.contains(BLOCK_TINT_KEY)) return;
        setTint(nbt.getInt(BLOCK_TINT_KEY));
        markDirty();
        if (world != null) world.updateListeners(pos, state, state, Block.REDRAW_ON_MAIN_THREAD);
    }

    void markDirty();
}
