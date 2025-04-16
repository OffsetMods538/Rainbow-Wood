package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModItems {
    private ModItems() {

    }

    public static final TintedBlockItem RAINBOW_PLANKS = register(new TintedBlockItem(ModBlocks.RAINBOW_PLANKS, new Item.Settings().component(ModComponents.TINT_COLOR, 0xFFFFFF)), "rainbow_planks");

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
