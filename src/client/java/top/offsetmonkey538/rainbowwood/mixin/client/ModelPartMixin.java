package top.offsetmonkey538.rainbowwood.mixin.client;

import net.minecraft.client.model.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import top.offsetmonkey538.rainbowwood.util.SignRenderContext;

@Mixin(ModelPart.class)
public abstract class ModelPartMixin {

    @ModifyArg(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
            ),
            index = 4
    )
    private int rainbow_wood$setColorForRainbowSign(int originalColor) {
        final Integer color = SignRenderContext.color.get();
        return color == null ? originalColor : color;
    }
}
