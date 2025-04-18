package top.offsetmonkey538.rainbowwood.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.tag.ModBlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.BLOCKS.toArray(new Block[0]));
//TODO: add to conventiontags as well
        getOrCreateTagBuilder(ModBlockTags.RAINBOW_LOGS).add(ModBlocks.RAINBOW_LOG, ModBlocks.STRIPPED_RAINBOW_LOG, ModBlocks.RAINBOW_WOOD, ModBlocks.STRIPPED_RAINBOW_WOOD);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(ModBlockTags.RAINBOW_LOGS);
        getOrCreateTagBuilder(BlockTags.PLANKS).add(ModBlocks.RAINBOW_PLANKS);
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(ModBlocks.RAINBOW_STAIRS);
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(ModBlocks.RAINBOW_SLAB);
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add();
        // TODO: getOrCreateTagBuilder(BlockTags.FENCE_GATES).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add();
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.RAINBOW_BUTTON);
        // TODO: getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add();
    }
}
