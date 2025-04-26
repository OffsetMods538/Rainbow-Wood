package top.offsetmonkey538.rainbowwood.entity;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

public interface ITintedEntity {
    void setTint(Integer newTint);
    Integer getTint();

    default void writeTintedCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("EntityTint", getTint());
    }

    default void readTintedCustomDataFromNbt(NbtCompound nbt) {
        if (!nbt.contains("EntityTint", NbtElement.INT_TYPE)) return;
        setTint(nbt.getInt("EntityTint"));
    }
}
