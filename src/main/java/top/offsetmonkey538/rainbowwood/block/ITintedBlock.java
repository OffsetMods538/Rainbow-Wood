package top.offsetmonkey538.rainbowwood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.block.entity.ModBlockEntities;
import top.offsetmonkey538.rainbowwood.block.entity.TintedBlockEntity;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

import java.util.Optional;

public interface ITintedBlock extends BlockEntityProvider {
    default void placeTinted(World world, BlockPos pos, BlockState state, ItemStack itemStack) {
        final Integer color = itemStack.get(ModComponents.TINT_COLOR);
        if (color == null) return;

        final Optional<TintedBlockEntity> optionalBlockEntity = world.getBlockEntity(pos, ModBlockEntities.TINTED_BLOCK_ENTITY);
        if (optionalBlockEntity.isEmpty()) return;

        optionalBlockEntity.get().blockTint = color;

        optionalBlockEntity.get().markDirty();
        world.updateListeners(pos, state, state, Block.NOTIFY_ALL_AND_REDRAW);
    }

    default @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TintedBlockEntity(pos, state);
    }
}
