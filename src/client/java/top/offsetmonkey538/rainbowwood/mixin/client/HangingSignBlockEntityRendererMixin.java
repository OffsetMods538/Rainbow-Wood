package top.offsetmonkey538.rainbowwood.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.rainbowwood.client.render.block.entity.TintedHangingSignBlockEntityRenderer;
import top.offsetmonkey538.rainbowwood.client.render.block.entity.TintedSignBlockEntityRenderer;
import top.offsetmonkey538.rainbowwood.util.SignRenderContext;

import java.util.Map;

@Mixin(HangingSignBlockEntityRenderer.class)
public abstract class HangingSignBlockEntityRendererMixin {

    @Shadow @Final private Map<WoodType, HangingSignBlockEntityRenderer.HangingSignModel> MODELS;

    @Unique
    private HangingSignBlockEntityRenderer.HangingSignModel rainbow_wood$oakModel = null;

    @WrapOperation(
            method = "render(Lnet/minecraft/block/entity/SignBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/HangingSignBlockEntityRenderer;render(Lnet/minecraft/block/entity/SignBlockEntity;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/block/BlockState;Lnet/minecraft/block/AbstractSignBlock;Lnet/minecraft/block/WoodType;Lnet/minecraft/client/model/Model;)V")
    )
    private void rainbow_wood$setColorForModelPartRenderWhenRainbowSign(HangingSignBlockEntityRenderer instance, SignBlockEntity signBlockEntity, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, BlockState blockState, AbstractSignBlock abstractSignBlock, WoodType woodType, Model model, Operation<Void> original) {
        if ((HangingSignBlockEntityRenderer) (Object) this instanceof TintedHangingSignBlockEntityRenderer) {
            rainbow_wood$oakModel = MODELS.get(WoodType.OAK);
            rainbow_wood$oakModel.updateVisibleParts(blockState);
        }

        original.call(instance, signBlockEntity, matrixStack, vertexConsumerProvider, i, j, blockState, abstractSignBlock, woodType, model);
    }

    @WrapOperation(
            method = "renderSignModel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V")
    )
    private void rainbow_wood$setColorForModelPartRenderWhenRainbowSign(ModelPart instance, MatrixStack matrices, VertexConsumer vertices, int light, int overlay, Operation<Void> original, @Share("oakModel") LocalRef<HangingSignBlockEntityRenderer.HangingSignModel> oakModelRef) {
        if (!((HangingSignBlockEntityRenderer) (Object) this instanceof TintedHangingSignBlockEntityRenderer tintedEntityRenderer)) {
            original.call(instance, matrices, vertices, light, overlay);
            return;
        }

        if (rainbow_wood$oakModel != null) rainbow_wood$oakModel.render(matrices, vertices, light, overlay);
        SignRenderContext.contextualize(() -> original.call(instance, matrices, vertices, light, overlay), tintedEntityRenderer.getTint());
    }
}
