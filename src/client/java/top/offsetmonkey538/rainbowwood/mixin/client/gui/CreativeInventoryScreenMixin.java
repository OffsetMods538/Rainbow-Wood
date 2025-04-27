package top.offsetmonkey538.rainbowwood.mixin.client.gui;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.item.ITintedItem;
import top.offsetmonkey538.rainbowwood.item.group.ModItemGroups;

import java.util.Arrays;
import java.util.List;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin {

    @Unique
    private static final List<ItemStack> rainbow_wood$stacksToLoopThrough = Arrays.stream(DyeColor.values()).filter(color -> color != DyeColor.WHITE && color != DyeColor.BLACK).map(color -> ((ITintedItem) ModBlocks.RAINBOW_PLANKS.asItem()).getStackWithTint(color.getSignColor())).toList();
    @Unique
    private static final ThreadLocal<Float> rainbow_wood$delta = ThreadLocal.withInitial(() -> 0f);
    @Unique
    private static final ThreadLocal<Float> rainbow_wood$ticksToNext = ThreadLocal.withInitial(() -> 20f); // Me so smart, use initial value thread local for max ticks wow so smart
    @Unique
    private static final ThreadLocal<ItemStack> rainbow_wood$currentStack = ThreadLocal.withInitial(() -> rainbow_wood$stacksToLoopThrough.get(0)); // So smart again, can reset to go to first


    @Inject(
            method = "drawBackground",
            at = @At("HEAD")
    )
    private void rainbow_wood$storeDeltaInThreadLocal(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        rainbow_wood$delta.set(delta);
    }

    @WrapOperation(
            method = "renderTabIcon",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemGroup;getIcon()Lnet/minecraft/item/ItemStack;"
            )
    )
    private ItemStack rainbow_wood$setIconStackForRainbowItemGroup(ItemGroup instance, Operation<ItemStack> original) {
        if (instance != ModItemGroups.RAINBOW_WOOD_ITEM_GROUP) return original.call(instance);

        float ticksToNext = rainbow_wood$ticksToNext.get();

        ticksToNext -= rainbow_wood$delta.get();

        if (ticksToNext > 0) {
            rainbow_wood$ticksToNext.set(ticksToNext);
            return rainbow_wood$currentStack.get();
        }

        rainbow_wood$ticksToNext.remove();
        final int currentIndex = rainbow_wood$stacksToLoopThrough.indexOf(rainbow_wood$currentStack.get());
        if (rainbow_wood$stacksToLoopThrough.size() <= currentIndex + 1) rainbow_wood$currentStack.remove();
        else rainbow_wood$currentStack.set(rainbow_wood$stacksToLoopThrough.get(currentIndex + 1));


        return rainbow_wood$currentStack.get();
    }
}
