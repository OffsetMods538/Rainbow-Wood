package top.offsetmonkey538.rainbowwood.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HangingSignBlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;

public class TintedHangingSignBlockEntity extends HangingSignBlockEntity implements ITintedBlockEntity {
    private int blockTint = -1;

    public TintedHangingSignBlockEntity(BlockPos blockPos, BlockState blockState) {
        // type is changed in HangingSignBlockEntityMixin. My thinking is that extending the vanilla HangingSignBlockEntity is better for compatibility?
        super(blockPos, blockState);
    }

    public int getTint() {
        return blockTint;
    }

    @Override
    public void setTint(int newTint) {
        blockTint = newTint;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        writeTintedNbt(nbt);
        super.writeNbt(nbt, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        readTintedNbt(nbt, world, pos, getCachedState());
        super.readNbt(nbt, registryLookup);
    }
}
