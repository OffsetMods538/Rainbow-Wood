package top.offsetmonkey538.rainbowwood.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import top.offsetmonkey538.rainbowwood.datagen.language.ModEnglishLanguageProvider;
import top.offsetmonkey538.rainbowwood.datagen.tag.ModBlockTagProvider;
import top.offsetmonkey538.rainbowwood.datagen.tag.ModItemTagProvider;

public class RainbowWoodDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);

        pack.addProvider(ModEnglishLanguageProvider::new);
    }
}
