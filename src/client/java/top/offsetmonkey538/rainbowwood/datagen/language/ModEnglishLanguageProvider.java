package top.offsetmonkey538.rainbowwood.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.tag.ModBlockTags;
import top.offsetmonkey538.rainbowwood.tag.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModEnglishLanguageProvider extends FabricLanguageProvider {
    public ModEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("general.rainbow_wood.tooltip.color", "Color: %s");

        translationBuilder.add("itemGroup.rainbow_wood.main_group", "Rainbow Wood");

        translationBuilder.add(ModBlockTags.RAINBOW_LOGS, "Rainbow Logs");
        translationBuilder.add(ModItemTags.RAINBOW_LOGS, "Rainbow Logs");

        translationBuilder.add(ModBlocks.RAINBOW_LOG, "Rainbow Log");
        translationBuilder.add(ModBlocks.RAINBOW_WOOD, "Rainbow Wood");
        translationBuilder.add(ModBlocks.STRIPPED_RAINBOW_LOG, "Stripped Rainbow Log");
        translationBuilder.add(ModBlocks.STRIPPED_RAINBOW_WOOD, "Stripped Rainbow Wood");
        translationBuilder.add(ModBlocks.RAINBOW_PLANKS, "Rainbow Planks");
        translationBuilder.add(ModBlocks.RAINBOW_STAIRS, "Rainbow Stairs");
        translationBuilder.add(ModBlocks.RAINBOW_SLAB, "Rainbow Slab");
        translationBuilder.add(ModBlocks.RAINBOW_FENCE, "Rainbow Fence");
        translationBuilder.add(ModBlocks.RAINBOW_FENCE_GATE, "Rainbow Fence Gate");
        translationBuilder.add(ModBlocks.RAINBOW_DOOR, "Rainbow Door");
        translationBuilder.add(ModBlocks.RAINBOW_TRAPDOOR, "Rainbow Trapdoor");
        translationBuilder.add(ModBlocks.RAINBOW_PRESSURE_PLATE, "Rainbow Pressure Plate");
        translationBuilder.add(ModBlocks.RAINBOW_BUTTON, "Rainbow Button");
        //translationBuilder.add(ModBlocks.RAINBOW_SIGN, "Rainbow Sign");
        //translationBuilder.add(ModBlocks.RAINBOW_HANGING_SIGN, "Rainbow Hanging Sign");
    }
}
