package top.offsetmonkey538.rainbowwood.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.server.tag.vanilla.VanillaBlockTagProvider;
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
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.BLOCKS);

        getOrCreateTagBuilder(ModBlockTags.RAINBOW_LOGS).add(ModBlocks.RAINBOW_LOG /* TODO: stripped, wood, stripped wood */);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(ModBlockTags.RAINBOW_LOGS);
        getOrCreateTagBuilder(BlockTags.PLANKS).add(ModBlocks.RAINBOW_PLANKS);

        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add();
        // TODO: getOrCreateTagBuilder(BlockTags.FENCE_GATES).add();
    }
}
