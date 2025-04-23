package top.offsetmonkey538.rainbowwood.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

public class TintedBlockEntity extends BlockEntity implements ITintedBlockEntity {
    private int blockTint = -1;

    public TintedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TINTED_BLOCK_ENTITY, pos, state);
    }

    public int getTint() {
        return blockTint;
    }

    @Override
    public void setTint(int newTint) {
        blockTint = newTint;
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder) {
        super.addComponents(componentMapBuilder);
        componentMapBuilder.add(ModComponents.TINT_COLOR, blockTint);
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

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
