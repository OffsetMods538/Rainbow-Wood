package top.offsetmonkey538.rainbowwood.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.block.entity.TintedHangingSignBlockEntity;

public class TintedHangingSignBlock extends HangingSignBlock implements ITintedBlock {

    public TintedHangingSignBlock(WoodType type, Settings settings) {
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
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TintedHangingSignBlockEntity(pos, state);
    }
}
