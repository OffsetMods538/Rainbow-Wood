package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import java.util.LinkedList;
import java.util.List;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModItems {
    private ModItems() {

    }

    public static final List<Item> ITEMS = new LinkedList<>();

    static {
        for (Block block : ModBlocks.BLOCKS_WITH_DEFAULT_ITEM) {
            register(new TintedBlockItem(block, new Item.Settings()), Registries.BLOCK.getId(block));
        }
    }
    public static final TintedSignItem TINTED_SIGN = register(new TintedSignItem(new Item.Settings().maxCount(16), ModBlocks.RAINBOW_SIGN, ModBlocks.RAINBOW_WALL_SIGN), "rainbow_sign");
    public static final TintedHangingSignItem TINTED_HANGING_SIGN = register(new TintedHangingSignItem(ModBlocks.RAINBOW_HANGING_SIGN, ModBlocks.RAINBOW_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)), "rainbow_hanging_sign");

    /*
     Example usage:
     public static final Item MY_ITEM = register(new Item(new FabricItemSettings()), "my_item");
     */

    private static <T extends Item> T register(T item, String name) {
        return register(item, id(name));
    }

    private static <T extends Item> T register(T item, Identifier id) {
        ITEMS.add(item);
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void register() {
        // Registers items by loading the class.
    }
}
