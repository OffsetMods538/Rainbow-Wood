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
    //TODO: figure out if map color can somehow be set to the actual color based on the block entity. No way to get be from block state :( Maybe mixin somewhere?

    //public static final Block RAINBOW_LOG = register(new TintedBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)), "rainbow_log");
    public static final Block RAINBOW_PLANKS = register(new TintedBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), "rainbow_planks");

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
