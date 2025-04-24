package top.offsetmonkey538.rainbowwood.mixin.client.render.model;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import top.offsetmonkey538.rainbowwood.util.SignRenderContext;

import java.util.*;

@Mixin(ModelPart.class)
public abstract class ModelPartMixin {

    @Shadow @Final private Map<String, ModelPart> children;

    @Unique
    private Map<ModelPart, String> rainbow_wood$reverseChildrenMap = null;

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

    @WrapOperation(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
            )
    )
    private void rainbow_wood$dontSetColorForChainsOfRainbowHangingSign(ModelPart instance, MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color, Operation<Void> original) {
        if (SignRenderContext.color.get() == null) {
            original.call(instance, matrices, vertices, light, overlay, color);
            return;
        }

        if (rainbow_wood$reverseChildrenMap == null) {
            rainbow_wood$reverseChildrenMap = children
                    .entrySet()
                    .stream()
                    .collect(HashMap::new, (map, entry) -> map.put(entry.getValue(), entry.getKey()), HashMap::putAll);
        }
        final String name = rainbow_wood$reverseChildrenMap.get(instance);

        original.call(instance, matrices, vertices, light, overlay, name.toUpperCase().contains("CHAIN") ? -1 : SignRenderContext.color.get());
    }
}
