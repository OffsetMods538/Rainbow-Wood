package top.offsetmonkey538.rainbowwood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.block.entity.ModBlockEntities;
import top.offsetmonkey538.rainbowwood.block.entity.TintedBlockEntity;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

import java.util.Optional;

public class RainbowPlanksBlock extends Block implements BlockEntityProvider {

    public RainbowPlanksBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        final Optional<TintedBlockEntity> optionalBlockEntity = world.getBlockEntity(pos, ModBlockEntities.TINTED_BLOCK_ENTITY);
        if (optionalBlockEntity.isEmpty()) return;
        optionalBlockEntity.get().setTint(itemStack.getOrDefault(ModComponents.TINT_COLOR, 0));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TintedBlockEntity(pos, state);
    }
}
