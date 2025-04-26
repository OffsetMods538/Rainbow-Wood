package top.offsetmonkey538.rainbowwood.mixin.client.render.entity.model;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.rainbowwood.access.client.ChestBoatEntityModelChestPartsGetter;
import top.offsetmonkey538.rainbowwood.util.TintedRenderContext;

import java.util.Set;
import java.util.function.Consumer;

@Mixin(CompositeEntityModel.class)
public abstract class CompositeEntityModelMixin {

    @Unique
    @Nullable
    private static Set<ModelPart> rainbow_wood$ignoredParts = null;

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Iterable;forEach(Ljava/util/function/Consumer;)V"
            )
    )
    private void rainbow_wood$setIgnoredParts(Iterable<ModelPart> instance, Consumer<ModelPart> consumer, Operation<Void> original) {
        if (this instanceof ChestBoatEntityModelChestPartsGetter getter) rainbow_wood$ignoredParts = getter.rainbow_wood$getChestModelParts();
        original.call(instance, consumer);
        rainbow_wood$ignoredParts = null;
    }

    @WrapOperation(
            method = "method_22961",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
            )
    )
    private static void rainbow_wood$setColorForRainbowBoat(ModelPart instance, MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color, Operation<Void> original) {
        final Integer tint = TintedRenderContext.color.get();
        if ((rainbow_wood$ignoredParts != null && rainbow_wood$ignoredParts.contains(instance)) || tint == null) {
            original.call(instance, matrices, vertices, light, overlay, color);
            return;
        }

        //noinspection UnnecessaryUnboxing: MCDev is concerned that tint is an Integer, not an int.
        original.call(instance, matrices, vertices, light, overlay, tint.intValue());
    }
}
