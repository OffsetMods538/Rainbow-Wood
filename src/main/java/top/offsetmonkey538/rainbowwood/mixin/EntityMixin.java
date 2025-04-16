package top.offsetmonkey538.rainbowwood.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.TintedBlockItem;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(
            method = "onBlockCollision",
            at = @At("HEAD")
    )
    private void rainbow_wood$clearWoodColorWhenCollidingWithCauldronWithWater(BlockState state, CallbackInfo ci) {
        if (!state.isOf(Blocks.WATER_CAULDRON)) return;
        final Entity thisEntity = (Entity) (Object) this;
        if (!(thisEntity instanceof ItemEntity itemEntity) || !(itemEntity.getStack().getItem() instanceof TintedBlockItem)) return;

        if (itemEntity.getStack().get(ModComponents.TINT_COLOR) == null) return;
        itemEntity.getStack().remove(ModComponents.TINT_COLOR);

        thisEntity.getWorld().playSound(thisEntity, thisEntity.getBlockPos(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.AMBIENT, 1, 1);
    }
}
