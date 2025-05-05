package top.offsetmonkey538.rainbowwood.entity;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

public interface ITintedEntity {
    void setTint(Integer newTint);
    Integer getTint();
    ItemStack pickBlockStack();
    void runKill();
    World world();
    Text customName();
    void runDropStack(ItemStack stack);

    default void writeTintedCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("EntityTint", getTint());
    }

    default void readTintedCustomDataFromNbt(NbtCompound nbt) {
        if (!nbt.contains("EntityTint", NbtElement.INT_TYPE)) return;
        setTint(nbt.getInt("EntityTint"));
    }

    default ItemStack getTintedPickBlockStack() {
        final ItemStack result = pickBlockStack();
        if (getTint() != null) result.set(ModComponents.TINT_COLOR, getTint());
        return result;
    }

    default void killAndDropTintedItem(Item selfAsItem) {
        runKill();
        if (!this.world().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) return;

        final ItemStack itemStack = new ItemStack(selfAsItem);

        itemStack.set(DataComponentTypes.CUSTOM_NAME, customName());
        if (getTint() != null) itemStack.set(ModComponents.TINT_COLOR, getTint());

        runDropStack(itemStack);
    }
}
