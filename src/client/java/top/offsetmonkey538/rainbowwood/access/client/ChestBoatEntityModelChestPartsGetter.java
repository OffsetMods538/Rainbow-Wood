package top.offsetmonkey538.rainbowwood.access.client;

import net.minecraft.client.model.ModelPart;

import java.util.Set;

public interface ChestBoatEntityModelChestPartsGetter {
    default Set<ModelPart> rainbow_wood$getChestModelParts() {
        throw new UnsupportedOperationException();
    }
}
