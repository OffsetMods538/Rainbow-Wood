package top.offsetmonkey538.rainbowwood.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import java.util.Optional;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public class ModModelProvider extends FabricModelProvider {
    private static final Model TINTED_CUBE_ALL = new Model(Optional.of(id("block/tinted_cube_all")), Optional.empty(), TextureKey.ALL);

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(ModBlocks.RAINBOW_PLANKS, TextureMap.all(ModBlocks.RAINBOW_PLANKS), TINTED_CUBE_ALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
