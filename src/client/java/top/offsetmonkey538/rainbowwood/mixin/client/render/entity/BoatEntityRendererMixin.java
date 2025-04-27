package top.offsetmonkey538.rainbowwood.mixin.client.render.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.rainbowwood.fasm.RainbowWoodEarlyRiser;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

@Mixin(BoatEntityRenderer.class)
public final class BoatEntityRendererMixin {
    @WrapOperation(
            method = "getTexture(Lnet/minecraft/entity/vehicle/BoatEntity$Type;Z)Lnet/minecraft/util/Identifier;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/Identifier;ofVanilla(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"
            )
    )
    private static Identifier rainbow_wood$useRainbowWoodNamespaceForRainbowBoat(String path, Operation<Identifier> original, BoatEntity.Type type) {
        if (type == RainbowWoodEarlyRiser.getRainbowType()) return id(path);
        return original.call(path);
    }
}
