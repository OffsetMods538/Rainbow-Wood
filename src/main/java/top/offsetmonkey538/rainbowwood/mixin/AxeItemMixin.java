package top.offsetmonkey538.rainbowwood.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.offsetmonkey538.rainbowwood.block.entity.TintedBlockEntity;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin extends MiningToolItem {
    public AxeItemMixin(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(material, effectiveBlocks, settings);
    }

    @Inject(
            method = "useOnBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemUsageContext;getPlayer()Lnet/minecraft/entity/player/PlayerEntity;"
            )
    )
    private void rainbow_wood$copyBlockTintNbtFromNewBlockEntity(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, @Local World world, @Local BlockPos pos, @Share("blockTint") LocalIntRef blockTint) {
        if (!(world.getBlockEntity(pos) instanceof TintedBlockEntity tintedBlockEntity)) return;
        blockTint.set(tintedBlockEntity.getTint());
    }

    @Inject(
            method = "useOnBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/event/GameEvent$Emitter;)V"
            )
    )
    private void rainbow_wood$copyBlockTintNbtToNewBlockEntity(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, @Local World world, @Local BlockPos pos, @Share("blockTint") LocalIntRef blockTint) {
        if (!(world.getBlockEntity(pos) instanceof TintedBlockEntity tintedBlockEntity)) return;
        tintedBlockEntity.setTint(blockTint.get());
    }
}
