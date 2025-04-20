package top.offsetmonkey538.rainbowwood.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TintedDoorBlock extends DoorBlock implements ITintedBlock {

    public TintedDoorBlock(BlockSetType type, Settings settings) {
        super(type, settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        placeTinted(world, pos, state, itemStack);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return getTintedPickStack(world, state, pos);
    }

    @Override
    public int getTint(@NotNull BlockView world, @NotNull BlockState state, @NotNull BlockPos pos) {
        final DoubleBlockHalf half = state.get(HALF);
        if (half == DoubleBlockHalf.UPPER) pos = pos.down();

        return ITintedBlock.super.getTint(world, state, pos);
    }
}
