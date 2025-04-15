package top.offsetmonkey538.rainbowwood.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import java.util.Map;
import java.util.Optional;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public class RainbowWoodDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModModelProvider::new);
        //pack.addProvider(TestBlockModel::new);
        /*
        Example:
        pack.addProvider(MyEnglishLanguageProvider::new)
         */
    }

    /*
    private static class TestBlockModel extends FabricModelProvider {
        private static final Model TINTABLE_CUBE_ALL = new Model(Optional.of(Identifier.ofVanilla("block/block")), Optional.empty(), TextureKey.TEXTURE);

        public TestBlockModel(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            TINTABLE_CUBE_ALL.upload(
                    ModelIds.getBlockModelId(Blocks.AMETHYST_BLOCK),
                    TextureMap.texture(Blocks.AMETHYST_BLOCK),
                    blockStateModelGenerator.modelCollector,
                    (id, textures) -> {
                        final JsonObject json = TINTABLE_CUBE_ALL.createJson(id, textures);

                        //final JsonArray elements = new JsonArray(1);
                        //final JsonObject element = new JsonObject();
                        //element.add("from", new JsonArray());
                        //elements.add(element);

                        //json.add("elements");
                        LOGGER.error("" +json); // (just to log what it does)
                        return json;
                    }
            );
            //Models.CUBE_ALL.upload(ModelIds.getBlockModelId(ModBlocks.RAINBOW_PLANKS),
            //        TextureMap.all(ModBlocks.RAINBOW_PLANKS), blockStateModelGenerator.modelCollector,
            //        (id, textures) -> {
            //    final JsonObject json = Models.CUBE_ALL.createJson(id, textures);
            //    LOGGER.error("" +json);
            //    return json;
            //});


            //Models.GENERATED.upload(ModelIds.getItemModelId(ModItems.SAMPLE_TAKER), TextureMap.layer0(ModItems.SAMPLE_TAKER), itemModelGenerator.writer, this::sampleTakerJsonGenerator);
            ////blockStateModelGenerator.registerSimpleCubeAll();
            ////blockStateModelGenerator.registerTintableCrossBlockState(Blocks.AMETHYST_BLOCK, BlockStateModelGenerator.TintType.TINTED);
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        }

        private JsonObject sampleTakerJsonGenerator(Identifier id, Map<TextureKey, Identifier> textures) {
            JsonObject json = Models.GENERATED.createJson(id, textures);

            JsonArray overrides = new JsonArray();
            for (int filled = 1; filled <= 9; filled++) {
                JsonObject override = new JsonObject();
                JsonObject predicate = new JsonObject();

                predicate.addProperty(id("filled").toString(), filled  / 10f);
                override.add("predicate", predicate);

                override.addProperty("model", id.withSuffixedPath("_filled_" + filled).toString());

                overrides.add(override);
            }
            json.add("overrides", overrides);

            return json;
        }
    }
    */
}
