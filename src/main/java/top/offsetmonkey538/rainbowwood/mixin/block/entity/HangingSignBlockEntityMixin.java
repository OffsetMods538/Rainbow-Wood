package top.offsetmonkey538.rainbowwood.mixin.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HangingSignBlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import top.offsetmonkey538.rainbowwood.block.ITintedBlock;
import top.offsetmonkey538.rainbowwood.block.entity.ModBlockEntities;

@Mixin(HangingSignBlockEntity.class)
public class HangingSignBlockEntityMixin {
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/entity/SignBlockEntity;<init>(Lnet/minecraft/block/entity/BlockEntityType;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"
            ),
            index = 0
    )
    private static BlockEntityType<?> rainbow_wood$changeBlockEntityTypeToTintedWhenStateIsTinted(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        if (blockState.getBlock() instanceof ITintedBlock) return ModBlockEntities.TINTED_HANGING_SIGN_BLOCK_ENTITY;
        return blockEntityType;
    }
}
