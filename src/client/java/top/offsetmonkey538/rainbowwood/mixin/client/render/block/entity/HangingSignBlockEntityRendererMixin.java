package top.offsetmonkey538.rainbowwood.mixin.client.render.block.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.rainbowwood.client.render.block.entity.TintedHangingSignBlockEntityRenderer;
import top.offsetmonkey538.rainbowwood.util.SignRenderContext;

@Mixin(HangingSignBlockEntityRenderer.class)
public abstract class HangingSignBlockEntityRendererMixin {

    @WrapOperation(
            method = "renderSignModel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V")
    )
    private void rainbow_wood$setColorForModelPartRenderWhenRainbowSign(ModelPart instance, MatrixStack matrices, VertexConsumer vertices, int light, int overlay, Operation<Void> original, @Share("oakModel") LocalRef<HangingSignBlockEntityRenderer.HangingSignModel> oakModelRef) {
        if (!((HangingSignBlockEntityRenderer) (Object) this instanceof TintedHangingSignBlockEntityRenderer tintedEntityRenderer)) {
            original.call(instance, matrices, vertices, light, overlay);
            return;
        }

        SignRenderContext.contextualize(() -> original.call(instance, matrices, vertices, light, overlay), tintedEntityRenderer.getTint());
    }
}
