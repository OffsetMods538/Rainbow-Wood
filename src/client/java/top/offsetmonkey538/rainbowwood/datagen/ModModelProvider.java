package top.offsetmonkey538.rainbowwood.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import java.util.Optional;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public class ModModelProvider extends FabricModelProvider {
    private static final Model TINTED_CUBE_ALL =               createModel("tinted_cube_all"   , ""           , TextureKey.ALL);
    private static final Model TINTED_CUBE_COLUMN =            createModel("tinted_cube_column", ""           , TextureKey.END, TextureKey.SIDE);
    private static final Model TINTED_CUBE_COLUMN_HORIZONTAL = createModel("tinted_cube_column", "_horizontal", TextureKey.END, TextureKey.SIDE);
    private static final Model TINTED_BUTTON =                 createModel("tinted_button"     , ""           , TextureKey.TEXTURE);
    private static final Model TINTED_BUTTON_PRESSED =         createModel("tinted_button"     , "_pressed"   , TextureKey.TEXTURE);
    private static final Model TINTED_BUTTON_INVENTORY =       createModel("tinted_button"     , "_inventory" , TextureKey.TEXTURE);

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(ModBlocks.RAINBOW_PLANKS, TextureMap.all(ModBlocks.RAINBOW_PLANKS), TINTED_CUBE_ALL);

        generateLog(ModBlocks.RAINBOW_LOG, TextureMap.sideAndEndForTop(ModBlocks.RAINBOW_LOG), blockStateModelGenerator);

        final TextureMap plankTexture = TextureMap.texture(ModBlocks.RAINBOW_PLANKS);
        generateButton(ModBlocks.RAINBOW_BUTTON, plankTexture, blockStateModelGenerator);
    }

    private void generateLog(final Block logBlock, final TextureMap textures, final BlockStateModelGenerator blockStateModelGenerator) {
        final Identifier columnId = TINTED_CUBE_COLUMN.upload(logBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier horizontalId = TINTED_CUBE_COLUMN_HORIZONTAL.upload(logBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(logBlock, columnId, horizontalId));
    }

    private void generateButton(final Block buttonBlock, final TextureMap textures, final BlockStateModelGenerator blockStateModelGenerator) {
        final Identifier mainId = TINTED_BUTTON.upload(buttonBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier pressedId = TINTED_BUTTON_PRESSED.upload(buttonBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(buttonBlock, mainId, pressedId));

        final Identifier inventoryId = TINTED_BUTTON_INVENTORY.upload(buttonBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(buttonBlock, inventoryId);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    private static Model createModel(final String id, final @NotNull String variant, final TextureKey... textureKeys) {
        return new Model(Optional.of(id("block/%s%s".formatted(id, variant))), variant.isEmpty() ? Optional.empty() : Optional.of(variant), textureKeys);
    }
}
