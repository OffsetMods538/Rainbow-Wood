package top.offsetmonkey538.rainbowwood.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class TintedBoatEntity extends BoatEntity implements ITintedEntity {
    private static final TrackedData<Integer> ENTITY_TINT = DataTracker.registerData(TintedBoatEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public TintedBoatEntity(EntityType<TintedBoatEntity> entityType, World world) {
        super(entityType, world);
    }

    public TintedBoatEntity(World world, double x, double y, double z) {
        this(ModEntityTypes.TINTED_BOAT, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ENTITY_TINT, -1);
    }

    @Override
    public void setTint(Integer newTint) {
        dataTracker.set(ENTITY_TINT, newTint);
    }

    @Override
    public Integer getTint() {
        return dataTracker.get(ENTITY_TINT);
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        writeTintedCustomDataToNbt(nbt);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        readTintedCustomDataFromNbt(nbt);
    }
}
