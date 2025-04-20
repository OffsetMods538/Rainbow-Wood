package top.offsetmonkey538.rainbowwood.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.LinkedList;
import java.util.List;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModBlocks {
    private ModBlocks() {

    }
    //TODO: figure out if map color can somehow be set to the actual color based on the block entity. No way to get be from block state :( Maybe mixin somewhere?

    public static final List<Block> BLOCKS = new LinkedList<>();

    public static final Block RAINBOW_LOG = register(new TintedPillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)), "rainbow_log");
    public static final Block RAINBOW_WOOD = register(new TintedPillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)), "rainbow_wood");
    public static final Block STRIPPED_RAINBOW_LOG = register(new TintedPillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)), "stripped_rainbow_log");
    public static final Block STRIPPED_RAINBOW_WOOD = register(new TintedPillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)), "stripped_rainbow_wood");
    public static final Block RAINBOW_PLANKS = register(new TintedBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)), "rainbow_planks");
    public static final Block RAINBOW_STAIRS = register(new TintedStairsBlock(RAINBOW_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)), "rainbow_stairs");
    public static final Block RAINBOW_SLAB = register(new TintedSlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)), "rainbow_slab");
    public static final Block RAINBOW_FENCE = register(new TintedFenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)), "rainbow_fence");
    public static final Block RAINBOW_FENCE_GATE = register(new TintedFenceGateBlock(ModWoodTypes.RAINBOW, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)), "rainbow_fence_gate");
    public static final Block RAINBOW_DOOR = register(new TintedDoorBlock(ModBlockSetTypes.RAINBOW, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)), "rainbow_door");
    public static final Block RAINBOW_TRAPDOOR = register(new TintedTrapdoorBlock(ModBlockSetTypes.RAINBOW, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)), "rainbow_trapdoor");
    public static final Block RAINBOW_PRESSURE_PLATE = register(new TintedPressurePlateBlock(ModBlockSetTypes.RAINBOW, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)), "rainbow_pressure_plate");
    public static final Block RAINBOW_BUTTON = register(new TintedButtonBlock(ModBlockSetTypes.RAINBOW, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)), "rainbow_button");
    // public static final Block RAINBOW_SIGN = register();
    // public static final Block RAINBOW_HANGING_SIGN = register();

    static {
        // Flammable
        //  based on FireBlock.registerDefaultFlammables();
        final FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(RAINBOW_PLANKS, 5, 20);
        registry.add(RAINBOW_SLAB, 5, 20);
        registry.add(RAINBOW_FENCE_GATE, 5, 20);
        registry.add(RAINBOW_FENCE, 5, 20);
        registry.add(RAINBOW_STAIRS, 5, 20);
        registry.add(RAINBOW_LOG, 5, 5);
        registry.add(STRIPPED_RAINBOW_LOG, 5, 5);
        registry.add(RAINBOW_WOOD, 5, 5);
        registry.add(STRIPPED_RAINBOW_WOOD, 5, 5);

        // Stripping
        StrippableBlockRegistry.register(RAINBOW_LOG, STRIPPED_RAINBOW_LOG);
        StrippableBlockRegistry.register(RAINBOW_WOOD, STRIPPED_RAINBOW_WOOD);
    }

    /*
     Example usage:
     public static final Block MY_BLOCK = register(new Block(FabricBlockSettings.copyOf(Blocks.STONE)), "my_block");
     */

    private static <T extends Block> T register(T block, String name) {
        BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, id(name), block);
    }

    public static void register() {
        // Registers blocks by loading the class.
    }
}
