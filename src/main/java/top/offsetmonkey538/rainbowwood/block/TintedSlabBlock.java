package top.offsetmonkey538.rainbowwood.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.block.entity.ITintedBlockEntity;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

public class TintedSlabBlock extends SlabBlock implements ITintedBlock {

    public TintedSlabBlock(Settings settings) {
        super(settings);
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
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        final boolean superResult = super.canReplace(state, context);

        Integer color = context.getStack().get(ModComponents.TINT_COLOR);
        if (color == null) color = -1;

        if (context.getWorld().getBlockEntity(context.getBlockPos()) instanceof ITintedBlockEntity tintedBlockEntity) return superResult && tintedBlockEntity.getTint() == color;

        return superResult && color == -1;
    }
}
