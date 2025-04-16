package top.offsetmonkey538.rainbowwood.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import java.util.Optional;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public class ModModelProvider extends FabricModelProvider {
    private static final Model TINTED_CUBE_ALL = new Model(Optional.of(id("block/tinted_cube_all")), Optional.empty(), TextureKey.ALL);
    private static final Model TINTED_CUBE_COLUMN = new Model(Optional.of(id("block/tinted_cube_column")), Optional.empty(), TextureKey.END, TextureKey.SIDE);
    private static final Model TINTED_CUBE_COLUMN_HORIZONTAL = new Model(Optional.of(id("block/tinted_cube_column_horizontal")), Optional.of("_horizontal"), TextureKey.END, TextureKey.SIDE);

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(ModBlocks.RAINBOW_PLANKS, TextureMap.all(ModBlocks.RAINBOW_PLANKS), TINTED_CUBE_ALL);

        generateLog(ModBlocks.RAINBOW_LOG, TextureMap.sideAndEndForTop(ModBlocks.RAINBOW_LOG), blockStateModelGenerator);
    }

    private void generateLog(final Block logBlock, final TextureMap textures, final BlockStateModelGenerator blockStateModelGenerator) {
        final Identifier columnId = TINTED_CUBE_COLUMN.upload(logBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier horizontalId = TINTED_CUBE_COLUMN_HORIZONTAL.upload(logBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(logBlock, columnId, horizontalId));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
