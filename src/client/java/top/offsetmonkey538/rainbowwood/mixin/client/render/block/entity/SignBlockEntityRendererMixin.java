package top.offsetmonkey538.rainbowwood.mixin.client.render.block.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.rainbowwood.client.render.block.entity.TintedSignBlockEntityRenderer;
import top.offsetmonkey538.rainbowwood.util.TintedRenderContext;

@Mixin(SignBlockEntityRenderer.class)
public abstract class SignBlockEntityRendererMixin {

    @WrapOperation(
            method = "renderSignModel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V")
    )
    private void rainbow_wood$setColorForModelPartRenderWhenRainbowSign(ModelPart instance, MatrixStack matrices, VertexConsumer vertices, int light, int overlay, Operation<Void> original) {
        TintedRenderContext.contextualize(() -> original.call(instance, matrices, vertices, light, overlay), (SignBlockEntityRenderer) (Object) this instanceof TintedSignBlockEntityRenderer tintedEntityRenderer ? tintedEntityRenderer.getTint() : null);
    }
}
