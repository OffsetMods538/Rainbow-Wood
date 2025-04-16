package top.offsetmonkey538.rainbowwood.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TintedBlockEntity extends BlockEntity {
    public static final String BLOCK_TINT_KEY = "BlockTint";

    public int blockTint = 0;

    public TintedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TINTED_BLOCK_ENTITY, pos, state);
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
        if (nbt.contains(BLOCK_TINT_KEY)) {
            blockTint = nbt.getInt(BLOCK_TINT_KEY);
            markDirty();
            if (world != null) world.updateListeners(pos, getCachedState(), getCachedState(), Block.REDRAW_ON_MAIN_THREAD);
        }

        super.readNbt(nbt, registryLookup);
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
