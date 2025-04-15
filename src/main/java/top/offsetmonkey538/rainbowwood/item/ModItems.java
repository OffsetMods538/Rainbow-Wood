package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModItems {
    private ModItems() {

    }

    public static final Item RAINBOW_PLANKS = register(new BlockItem(ModBlocks.RAINBOW_PLANKS, new Item.Settings()), "rainbow_planks");

    /*
     Example usage:
     public static final Item MY_ITEM = register(new Item(new FabricItemSettings()), "my_item");
     */

    private static <T extends Item> T register(T item, String name) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    public static void register() {
        // Registers items by loading the class.
    }
}
