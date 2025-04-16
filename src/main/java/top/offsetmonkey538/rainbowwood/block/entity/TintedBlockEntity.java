package top.offsetmonkey538.rainbowwood.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;

public class TintedBlockEntity extends BlockEntity {
    public static final String BLOCK_TINT_KEY = "BlockTint";

    private int blockTint = 0;

    public TintedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TINTED_BLOCK_ENTITY, pos, state);
    }

    public void setTint(final int blockTint) {
        this.blockTint = blockTint;
        markDirty();
    }

    public int getTint() {
        return blockTint;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt(BLOCK_TINT_KEY, blockTint);

        super.writeNbt(nbt, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        if (nbt.contains(BLOCK_TINT_KEY)) setTint(nbt.getInt(BLOCK_TINT_KEY));

        super.readNbt(nbt, registryLookup);
    }
}
