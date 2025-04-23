package top.offsetmonkey538.rainbowwood.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.rainbowwood.block.entity.ITintedBlockEntity;
import top.offsetmonkey538.rainbowwood.util.NamedColor;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {

    @ModifyReturnValue(
            method = "getMapColor",
            at = @At("RETURN")
    )
    private MapColor rainbow_wood$setMapColorWhenTintedBlock(MapColor original, BlockView world, BlockPos pos) {
        final BlockEntity blockEntity = world.getBlockEntity(pos);

        if (!(blockEntity instanceof ITintedBlockEntity tintedBlockEntity)) return original;
        if (tintedBlockEntity.getTint() == -1) return MapColor.WHITE;

        return NamedColor.getClosestMapColor(tintedBlockEntity.getTint());
    }
}
