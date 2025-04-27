package top.offsetmonkey538.rainbowwood.mixin.block.dispenser;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.entity.ITintedEntity;
import top.offsetmonkey538.rainbowwood.entity.TintedBoatEntity;
import top.offsetmonkey538.rainbowwood.entity.TintedChestBoatEntity;
import top.offsetmonkey538.rainbowwood.fasm.RainbowWoodEarlyRiser;

@Mixin(BoatDispenserBehavior.class)
public abstract class BoatDispenserBehaviorMixin extends ItemDispenserBehavior {

    @Shadow @Final private BoatEntity.Type boatType;

    @WrapOperation(
            method = "dispenseSilently",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/world/World;DDD)Lnet/minecraft/entity/vehicle/BoatEntity;"
            )
    )
    private BoatEntity rainbow_wood$dispenserPlaceTintedBoat(World world, double x, double y, double z, Operation<BoatEntity> original) {
        if (boatType == RainbowWoodEarlyRiser.getRainbowType()) return new TintedBoatEntity(world, x, y, z);

        return original.call(world, x, y, z);
    }

    @WrapOperation(
            method = "dispenseSilently",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/world/World;DDD)Lnet/minecraft/entity/vehicle/ChestBoatEntity;"
            )
    )
    private ChestBoatEntity rainbow_wood$dispenserPlaceTintedChestBoat(World world, double x, double y, double z, Operation<ChestBoatEntity> original) {
        if (boatType == RainbowWoodEarlyRiser.getRainbowType()) return new TintedChestBoatEntity(world, x, y, z);

        return original.call(world, x, y, z);
    }

    @Inject(
            method = "dispenseSilently",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/vehicle/BoatEntity;setVariant(Lnet/minecraft/entity/vehicle/BoatEntity$Type;)V"
            )
    )
    private void rainbow_wood$setTintForTintedBoatEntities(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir, @Local BoatEntity boat) {
        // Using cast to object and check if Integer as a null check with value definition again haha VVV
        if (!(boat instanceof ITintedEntity tintedEntity) || !((Object) stack.get(ModComponents.TINT_COLOR) instanceof Integer tint)) return;

        tintedEntity.setTint(tint);
    }
}
