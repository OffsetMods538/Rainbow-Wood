package top.offsetmonkey538.rainbowwood.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;

public class TintedSignBlockEntity extends SignBlockEntity implements ITintedBlockEntity {
    private int blockTint = -1;

    public TintedSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TINTED_SIGN_BLOCK_ENTITY, pos, state);
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
