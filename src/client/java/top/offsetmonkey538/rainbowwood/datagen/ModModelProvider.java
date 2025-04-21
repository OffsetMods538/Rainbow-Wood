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
    private static final Model TINTED_CUBE_ALL               = createModel("tinted_cube_all"      , ""                  , TextureKey.ALL);
    private static final Model TINTED_CUBE_COLUMN            = createModel("tinted_cube_column"   , ""                  , TextureKey.END, TextureKey.SIDE);
    private static final Model TINTED_CUBE_COLUMN_HORIZONTAL = createModel("tinted_cube_column"   , "_horizontal"       , TextureKey.END, TextureKey.SIDE);
    private static final Model TINTED_BUTTON                 = createModel("tinted_button"        , ""                  , TextureKey.TEXTURE);
    private static final Model TINTED_BUTTON_PRESSED         = createModel("tinted_button"        , "_pressed"          , TextureKey.TEXTURE);
    private static final Model TINTED_BUTTON_INVENTORY       = createModel("tinted_button"        , "_inventory"        , TextureKey.TEXTURE);
    private static final Model TINTED_SLAB                   = createModel("tinted_slab"          , ""                  , TextureKey.TEXTURE);
    private static final Model TINTED_SLAB_TOP               = createModel("tinted_slab"          , "_top"              , TextureKey.TEXTURE);
    private static final Model TINTED_STAIRS                 = createModel("tinted_stairs"        , ""                  , TextureKey.TEXTURE);
    private static final Model TINTED_STAIRS_INNER           = createModel("tinted_stairs"        , "_inner"            , TextureKey.TEXTURE);
    private static final Model TINTED_STAIRS_OUTER           = createModel("tinted_stairs"        , "_outer"            , TextureKey.TEXTURE);
    private static final Model TINTED_FENCE_POST             = createModel("tinted_fence"         , "_post"             , TextureKey.TEXTURE);
    private static final Model TINTED_FENCE_SIDE             = createModel("tinted_fence"         , "_side"             , TextureKey.TEXTURE);
    private static final Model TINTED_FENCE_INVENTORY        = createModel("tinted_fence"         , "_inventory"        , TextureKey.TEXTURE);
    private static final Model TINTED_FENCE_GATE             = createModel("tinted_fence_gate"    , ""                  , TextureKey.TEXTURE);
    private static final Model TINTED_FENCE_GATE_OPEN        = createModel("tinted_fence_gate"    , "_open"             , TextureKey.TEXTURE);
    private static final Model TINTED_FENCE_GATE_WALL        = createModel("tinted_fence_gate"    , "_wall"             , TextureKey.TEXTURE);
    private static final Model TINTED_FENCE_GATE_WALL_OPEN   = createModel("tinted_fence_gate"    , "_wall_open"        , TextureKey.TEXTURE);
    private static final Model TINTED_DOOR_BOTTOM_LEFT       = createModel("tinted_door"          , "_bottom_left"      , TextureKey.TOP, TextureKey.BOTTOM);
    private static final Model TINTED_DOOR_BOTTOM_LEFT_OPEN  = createModel("tinted_door"          , "_bottom_left_open" , TextureKey.TOP, TextureKey.BOTTOM);
    private static final Model TINTED_DOOR_BOTTOM_RIGHT      = createModel("tinted_door"          , "_bottom_right"     , TextureKey.TOP, TextureKey.BOTTOM);
    private static final Model TINTED_DOOR_BOTTOM_RIGHT_OPEN = createModel("tinted_door"          , "_bottom_right_open", TextureKey.TOP, TextureKey.BOTTOM);
    private static final Model TINTED_DOOR_TOP_LEFT          = createModel("tinted_door"          , "_top_left"         , TextureKey.TOP, TextureKey.BOTTOM);
    private static final Model TINTED_DOOR_TOP_LEFT_OPEN     = createModel("tinted_door"          , "_top_left_open"    , TextureKey.TOP, TextureKey.BOTTOM);
    private static final Model TINTED_DOOR_TOP_RIGHT         = createModel("tinted_door"          , "_top_right"        , TextureKey.TOP, TextureKey.BOTTOM);
    private static final Model TINTED_DOOR_TOP_RIGHT_OPEN    = createModel("tinted_door"          , "_top_right_open"   , TextureKey.TOP, TextureKey.BOTTOM);
    private static final Model TINTED_TRAPDOOR_TOP           = createModel("tinted_trapdoor"      , "_top"              , TextureKey.TEXTURE);
    private static final Model TINTED_TRAPDOOR_BOTTOM        = createModel("tinted_trapdoor"      , "_bottom"           , TextureKey.TEXTURE);
    private static final Model TINTED_TRAPDOOR_OPEN          = createModel("tinted_trapdoor"      , "_open"             , TextureKey.TEXTURE);
    private static final Model TINTED_PRESSURE_PLATE_UP      = createModel("tinted_pressure_plate", "_up"               , TextureKey.TEXTURE);
    private static final Model TINTED_PRESSURE_PLATE_DOWN    = createModel("tinted_pressure_plate", "_down"             , TextureKey.TEXTURE);


    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        final Identifier baseModelId = TINTED_CUBE_ALL.upload(ModBlocks.RAINBOW_PLANKS, TextureMap.all(ModBlocks.RAINBOW_PLANKS), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(ModBlocks.RAINBOW_PLANKS, baseModelId));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.RAINBOW_PLANKS, baseModelId);

        generateLog(ModBlocks.RAINBOW_LOG, TextureMap.sideAndEndForTop(ModBlocks.RAINBOW_LOG), blockStateModelGenerator);
        generateLog(ModBlocks.STRIPPED_RAINBOW_LOG, TextureMap.sideAndEndForTop(ModBlocks.STRIPPED_RAINBOW_LOG), blockStateModelGenerator);
        generateWood(ModBlocks.RAINBOW_WOOD, ModBlocks.RAINBOW_LOG, blockStateModelGenerator);
        generateWood(ModBlocks.STRIPPED_RAINBOW_WOOD, ModBlocks.STRIPPED_RAINBOW_LOG, blockStateModelGenerator);

        final TextureMap plankTexture = TextureMap.texture(ModBlocks.RAINBOW_PLANKS);
        generateSlab(ModBlocks.RAINBOW_SLAB, plankTexture, blockStateModelGenerator, baseModelId);
        generateStairs(ModBlocks.RAINBOW_STAIRS, plankTexture, blockStateModelGenerator);
        generateFence(ModBlocks.RAINBOW_FENCE, plankTexture, blockStateModelGenerator);
        generateFenceGate(ModBlocks.RAINBOW_FENCE_GATE, plankTexture, blockStateModelGenerator);
        generateDoor(ModBlocks.RAINBOW_DOOR, blockStateModelGenerator);
        generateTrapdoor(ModBlocks.RAINBOW_TRAPDOOR, blockStateModelGenerator);
        generatePressurePlate(ModBlocks.RAINBOW_PRESSURE_PLATE, plankTexture, blockStateModelGenerator);
        generateSign(ModBlocks.RAINBOW_SIGN, ModBlocks.RAINBOW_WALL_SIGN, plankTexture, blockStateModelGenerator);
        generateHangingSign(ModBlocks.RAINBOW_HANGING_SIGN, ModBlocks.RAINBOW_WALL_HANGING_SIGN, TextureMap.texture(ModBlocks.STRIPPED_RAINBOW_LOG), blockStateModelGenerator);
        generateButton(ModBlocks.RAINBOW_BUTTON, plankTexture, blockStateModelGenerator);
    }

    private void generateLog(final Block logBlock, final TextureMap textures, final BlockStateModelGenerator blockStateModelGenerator) {
        final Identifier columnId = TINTED_CUBE_COLUMN.upload(logBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier horizontalId = TINTED_CUBE_COLUMN_HORIZONTAL.upload(logBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(logBlock, columnId, horizontalId));
    }

    private void generateWood(final Block woodBlock, final Block textureBlock, final BlockStateModelGenerator blockStateModelGenerator) {
        final TextureMap textures =  new TextureMap().put(TextureKey.SIDE, TextureMap.getId(textureBlock)).put(TextureKey.END, TextureMap.getId(textureBlock)).put(TextureKey.PARTICLE, TextureMap.getId(textureBlock));
        final Identifier columnId = TINTED_CUBE_COLUMN.upload(woodBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(woodBlock, columnId));
    }

    private void generateSlab(final Block slabBlock, final TextureMap textures, final BlockStateModelGenerator blockStateModelGenerator, final Identifier baseModel) {
        final Identifier mainId = TINTED_SLAB.upload(slabBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier topId = TINTED_SLAB_TOP.upload(slabBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slabBlock, mainId, topId, baseModel));
        blockStateModelGenerator.registerParentedItemModel(slabBlock, mainId);
    }

    private void generateStairs(final Block stairsBlock, final TextureMap textures, final BlockStateModelGenerator blockStateModelGenerator) {
        final Identifier mainId = TINTED_STAIRS.upload(stairsBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier innerId = TINTED_STAIRS_INNER.upload(stairsBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier outerId = TINTED_STAIRS_OUTER.upload(stairsBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairsBlock, innerId, mainId, outerId));
        blockStateModelGenerator.registerParentedItemModel(stairsBlock, mainId);
    }

    private void generateFence(final Block fenceBlock, final TextureMap textures, final BlockStateModelGenerator blockStateModelGenerator) {
        final Identifier postId = TINTED_FENCE_POST.upload(fenceBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier sideId = TINTED_FENCE_SIDE.upload(fenceBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, postId, sideId));

        final Identifier inventoryId = TINTED_FENCE_INVENTORY.upload(fenceBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(fenceBlock, inventoryId);
    }

    private void generateFenceGate(final Block fenceGateBlock, final TextureMap textures, final BlockStateModelGenerator blockStateModelGenerator) {
        final Identifier mainId = TINTED_FENCE_GATE.upload(fenceGateBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier mainOpenId = TINTED_FENCE_GATE_OPEN.upload(fenceGateBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier wallId = TINTED_FENCE_GATE_WALL.upload(fenceGateBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier wallOpenId = TINTED_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock, textures, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock, mainOpenId, mainId, wallOpenId, wallId, true));
    }

    private void generateDoor(final Block doorBlock, final BlockStateModelGenerator blockStateModelGenerator) {
        final TextureMap textures = TextureMap.topBottom(doorBlock);

        final Identifier bottomLeftId = TINTED_DOOR_BOTTOM_LEFT.upload(doorBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier bottomLeftOpenId = TINTED_DOOR_BOTTOM_LEFT_OPEN.upload(doorBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier bottomRightId = TINTED_DOOR_BOTTOM_RIGHT.upload(doorBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier bottomRightOpenId = TINTED_DOOR_BOTTOM_RIGHT_OPEN.upload(doorBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier topLeftId = TINTED_DOOR_TOP_LEFT.upload(doorBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier topLeftOpenId = TINTED_DOOR_TOP_LEFT_OPEN.upload(doorBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier topRightId = TINTED_DOOR_TOP_RIGHT.upload(doorBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier topRightOpenId = TINTED_DOOR_TOP_RIGHT_OPEN.upload(doorBlock, textures, blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createDoorBlockState(doorBlock, bottomLeftId, bottomLeftOpenId, bottomRightId, bottomRightOpenId, topLeftId, topLeftOpenId, topRightId, topRightOpenId));
        blockStateModelGenerator.registerItemModel(doorBlock.asItem());
    }

    private void generateTrapdoor(final Block trapdoorBlock, final BlockStateModelGenerator blockStateModelGenerator) {
        final TextureMap textures = TextureMap.texture(trapdoorBlock);
        final Identifier topId = TINTED_TRAPDOOR_TOP.upload(trapdoorBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier bottomId = TINTED_TRAPDOOR_BOTTOM.upload(trapdoorBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier openId = TINTED_TRAPDOOR_OPEN.upload(trapdoorBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createTrapdoorBlockState(trapdoorBlock, topId, bottomId, openId));
        blockStateModelGenerator.registerParentedItemModel(trapdoorBlock, bottomId);
    }

    private void generatePressurePlate(final Block pressurePlateBlock, final TextureMap textures, final BlockStateModelGenerator blockStateModelGenerator) {
        final Identifier upId = TINTED_PRESSURE_PLATE_UP.upload(pressurePlateBlock, textures, blockStateModelGenerator.modelCollector);
        final Identifier downId = TINTED_PRESSURE_PLATE_DOWN.upload(pressurePlateBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(pressurePlateBlock, upId, downId));
        blockStateModelGenerator.registerParentedItemModel(pressurePlateBlock, upId);
    }

    private void generateSign(final Block signBlock, final Block wallSignBlock, final TextureMap plankTextures, final BlockStateModelGenerator blockStateModelGenerator) {
        final TextureMap textures = TextureMap.particle(plankTextures.getTexture(TextureKey.TEXTURE));

        blockStateModelGenerator.registerSingleton(signBlock, textures, Models.PARTICLE);
        blockStateModelGenerator.registerSingleton(wallSignBlock, textures, Models.PARTICLE);
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(wallSignBlock);
        blockStateModelGenerator.registerItemModel(signBlock.asItem());
    }

    private void generateHangingSign(final Block hangingSignBlock, final Block wallHangingSignBlock, final TextureMap strippedLogTextures, final BlockStateModelGenerator blockStateModelGenerator) {
        final TextureMap textures = TextureMap.particle(strippedLogTextures.getTexture(TextureKey.TEXTURE));

        blockStateModelGenerator.registerSingleton(hangingSignBlock, textures, Models.PARTICLE);
        blockStateModelGenerator.registerSingleton(wallHangingSignBlock, textures, Models.PARTICLE);
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(hangingSignBlock);
        Models.GENERATED.upload(
                ModelIds.getItemModelId(hangingSignBlock.asItem()),
                TextureMap
                        .layer0(hangingSignBlock.asItem())
                        .register(TextureKey.LAYER1, TextureMap.getSubId(hangingSignBlock.asItem(), "_layer_1")),
                blockStateModelGenerator.modelCollector
        );
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
