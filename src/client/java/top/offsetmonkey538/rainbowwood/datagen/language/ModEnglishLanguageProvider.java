package top.offsetmonkey538.rainbowwood.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.tag.ModBlockTags;
import top.offsetmonkey538.rainbowwood.tag.ModItemTags;
import top.offsetmonkey538.rainbowwood.util.NamedColors;

import java.util.concurrent.CompletableFuture;

public class ModEnglishLanguageProvider extends FabricLanguageProvider {
    public ModEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("general.rainbow_wood.tooltip.uncolored", "Uncolored");
        translationBuilder.add("general.rainbow_wood.tooltip.color", "Color: %s");
        translationBuilder.add("general.rainbow_wood.tooltip.closest.named_color", "Closest Named Color: %s");

        generateMapColors(translationBuilder);

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
        translationBuilder.add(ModBlocks.RAINBOW_SIGN, "Rainbow Sign");
        translationBuilder.add(ModBlocks.RAINBOW_HANGING_SIGN, "Rainbow Hanging Sign");
    }

    private void generateMapColors(TranslationBuilder translationBuilder) {
        for (NamedColors value : NamedColors.getAllColors()) {
            translationBuilder.add(value.translation, value.name);
        }
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.PALE_GREEN.color),            "PALE_GREEN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.PALE_YELLOW.color),           "PALE_YELLOW");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.WHITE_GRAY.color),            "WHITE_GRAY");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.BRIGHT_RED.color),            "BRIGHT_RED");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.PALE_PURPLE.color),           "PALE_PURPLE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.IRON_GRAY.color),             "IRON_GRAY");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DARK_GREEN.color),            "DARK_GREEN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.WHITE.color),                 "WHITE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.LIGHT_BLUE_GRAY.color),       "LIGHT_BLUE_GRAY");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DIRT_BROWN.color),            "DIRT_BROWN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.STONE_GRAY.color),            "STONE_GRAY");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.WATER_BLUE.color),            "WATER_BLUE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.OAK_TAN.color),               "OAK_TAN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.OFF_WHITE.color),             "OFF_WHITE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.ORANGE.color),                "ORANGE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.MAGENTA.color),               "MAGENTA");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.LIGHT_BLUE.color),            "LIGHT_BLUE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.YELLOW.color),                "YELLOW");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.LIME.color),                  "LIME");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.PINK.color),                  "PINK");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.GRAY.color),                  "GRAY");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.LIGHT_GRAY.color),            "LIGHT_GRAY");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.CYAN.color),                  "CYAN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.PURPLE.color),                "PURPLE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.BLUE.color),                  "BLUE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.BROWN.color),                 "BROWN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.GREEN.color),                 "GREEN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.RED.color),                   "RED");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.BLACK.color),                 "BLACK");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.GOLD.color),                  "GOLD");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DIAMOND_BLUE.color),          "DIAMOND_BLUE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.LAPIS_BLUE.color),            "LAPIS_BLUE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.EMERALD_GREEN.color),         "EMERALD_GREEN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.SPRUCE_BROWN.color),          "SPRUCE_BROWN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DARK_RED.color),              "DARK_RED");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_WHITE.color),      "TERRACOTTA_WHITE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_ORANGE.color),     "TERRACOTTA_ORANGE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_MAGENTA.color),    "TERRACOTTA_MAGENTA");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_LIGHT_BLUE.color), "TERRACOTTA_LIGHT_BLUE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_YELLOW.color),     "TERRACOTTA_YELLOW");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_LIME.color),       "TERRACOTTA_LIME");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_PINK.color),       "TERRACOTTA_PINK");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_GRAY.color),       "TERRACOTTA_GRAY");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_LIGHT_GRAY.color), "TERRACOTTA_LIGHT_GRAY");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_CYAN.color),       "TERRACOTTA_CYAN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_PURPLE.color),     "TERRACOTTA_PURPLE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_BLUE.color),       "TERRACOTTA_BLUE");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_BROWN.color),      "TERRACOTTA_BROWN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_GREEN.color),      "TERRACOTTA_GREEN");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_RED.color),        "TERRACOTTA_RED");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TERRACOTTA_BLACK.color),      "TERRACOTTA_BLACK");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DULL_RED.color),              "DULL_RED");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DULL_PINK.color),             "DULL_PINK");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DARK_CRIMSON.color),          "DARK_CRIMSON");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.TEAL.color),                  "TEAL");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DARK_AQUA.color),             "DARK_AQUA");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DARK_DULL_PINK.color),        "DARK_DULL_PINK");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.BRIGHT_TEAL.color),           "BRIGHT_TEAL");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.DEEPSLATE_GRAY.color),        "DEEPSLATE_GRAY");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.RAW_IRON_PINK.color),         "RAW_IRON_PINK");
        //translationBuilder.add(MapColorMapHolder.MAP_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(MapColor.LICHEN_GREEN.color),          "LICHEN_GREEN");
    }
}
