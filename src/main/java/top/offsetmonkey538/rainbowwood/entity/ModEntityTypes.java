package top.offsetmonkey538.rainbowwood.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModEntityTypes {

    public static final EntityType<TintedBoatEntity> TINTED_BOAT = register(EntityType.Builder.<TintedBoatEntity>create(TintedBoatEntity::new, SpawnGroup.MISC).dimensions(1.375F, 0.5625F).eyeHeight(0.5625F).maxTrackingRange(10), "tinted_boat");
    public static final EntityType<TintedChestBoatEntity> TINTED_CHEST_BOAT = register(EntityType.Builder.<TintedChestBoatEntity>create(TintedChestBoatEntity::new, SpawnGroup.MISC).dimensions(1.375F, 0.5625F).eyeHeight(0.5625F).maxTrackingRange(10), "tinted_chest_boat");

    private static <T extends Entity> EntityType<T> register(EntityType.Builder<T> entityTypeBuilder, String name) {
        return Registry.register(Registries.ENTITY_TYPE, id(name), entityTypeBuilder.build(name));
    }

    public static void register() {
        // Registers entities by loading the class.
    }
}
