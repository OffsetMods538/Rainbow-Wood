package top.offsetmonkey538.rainbowwood.mixin.client.render.entity.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.ChestBoatEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.offsetmonkey538.rainbowwood.access.client.ChestBoatEntityModelChestPartsGetter;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(ChestBoatEntityModel.class)
public abstract class ChestBoatEntityModelMixin extends BoatEntityModel implements ChestBoatEntityModelChestPartsGetter {
    public ChestBoatEntityModelMixin(ModelPart root) {
        super(root);
    }

    @Unique
    private Set<ModelPart> rainbow_wood$chestParts;


    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void rainbow_wood$setChestModelParts(ModelPart modelPart, CallbackInfo ci) {
        rainbow_wood$chestParts = ((ModelPartAccessor) (Object) modelPart).getChildren().entrySet().stream().filter(entry -> entry.getKey().toUpperCase().contains("CHEST")).map(Map.Entry::getValue).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<ModelPart> rainbow_wood$getChestModelParts() {
        return rainbow_wood$chestParts;
    }
}
