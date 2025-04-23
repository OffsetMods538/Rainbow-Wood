package top.offsetmonkey538.rainbowwood.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.client.gui.screen.ingame.HangingSignEditScreen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.rainbowwood.block.entity.TintedHangingSignBlockEntity;

@Mixin(HangingSignEditScreen.class)
public abstract class HangingSignEditScreenMixin extends AbstractSignEditScreen {

    public HangingSignEditScreenMixin(SignBlockEntity blockEntity, boolean front, boolean filtered) {
        super(blockEntity, front, filtered);
    }

    @WrapOperation(
            method = "renderSignBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V")
    )
    private void rainbow_wood$setShaderColorForRenderingSignTextureWhenRainbowHangingSign(DrawContext drawContext, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight, Operation<Void> original) {
        if (((AbstractSignEditScreenAccessor) this).getBlockEntity() instanceof TintedHangingSignBlockEntity tintedSignBlockEntity && tintedSignBlockEntity.getTint() != -1) {
            int tint = tintedSignBlockEntity.getTint();

            RenderSystem.setShaderColor(((tint >> 16) & 0xFF) / 255f, ((tint >> 8) & 0xFF) / 255f, (tint & 0xFF) / 255f, 1f);
            original.call(drawContext, texture, x, y, u, v, width, height, textureWidth, textureHeight);
            RenderSystem.setShaderColor(1, 1, 1, 1);

            return;
        }

        original.call(drawContext, texture, x, y, u, v, width, height, textureWidth, textureHeight);
    }
}
