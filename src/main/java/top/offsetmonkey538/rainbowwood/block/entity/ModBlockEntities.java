package top.offsetmonkey538.rainbowwood.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModBlockEntities {
    private ModBlockEntities() {

    }

    public static final BlockEntityType<TintedBlockEntity> TINTED_BLOCK_ENTITY = register(TintedBlockEntity::new, "rainbow_planks", ModBlocks.BLOCKS);

    /*
     Example usage:
     public static final Block MY_BLOCK = register(new Block(FabricBlockSettings.copyOf(Blocks.STONE)), "my_block");
     */

    private static <T extends BlockEntity> BlockEntityType<T> register(BlockEntityType.BlockEntityFactory<T> factory, String name, Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id(name), BlockEntityType.Builder.create(factory, blocks).build());
    }

    public static void register() {
        // Registers blocks by loading the class.
    }
}
