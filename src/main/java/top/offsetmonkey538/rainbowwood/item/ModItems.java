package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

public final class ModItems {
    private ModItems() {

    }

    static {
        for (Block block : ModBlocks.BLOCKS) {
            register(new TintedBlockItem(block, new Item.Settings()), Registries.BLOCK.getId(block));
        }
    }

    /*
     Example usage:
     public static final Item MY_ITEM = register(new Item(new FabricItemSettings()), "my_item");
     */

    private static <T extends Item> void register(T item, Identifier id) {
        Registry.register(Registries.ITEM, id, item);
    }

    public static void register() {
        // Registers items by loading the class.
    }
}
