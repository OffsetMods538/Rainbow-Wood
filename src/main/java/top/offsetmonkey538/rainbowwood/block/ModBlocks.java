package top.offsetmonkey538.rainbowwood.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModBlocks {
    private ModBlocks() {

    }

    public static final BlockWithTint RAINBOW_PLANKS = register(new BlockWithTint(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), "rainbow_planks");

    /*
     Example usage:
     public static final Block MY_BLOCK = register(new Block(FabricBlockSettings.copyOf(Blocks.STONE)), "my_block");
     */

    private static <T extends Block> T register(T block, String name) {
        return Registry.register(Registries.BLOCK, id(name), block);
    }

    public static void register() {
        // Registers blocks by loading the class.
    }
}
