package top.offsetmonkey538.rainbowwood.util;

import org.jetbrains.annotations.NotNull;

public final class NamedColors {

    public static final String NAMED_COLOR_TRANSLATION_KEY_TEMPLATE = "general.rainbow_wood.named_color.%s";
    private static final NamedColors[] colors = new NamedColors[] {
            new NamedColors(0xFFFFFF, "White Dye"),
            new NamedColors(0xFF681F, "Orange Dye"),
            new NamedColors(0xFF00FF, "Magenta Dye"),
            new NamedColors(0x9AC0CD, "Light Blue Dye"),
            new NamedColors(0xFFFF00, "Yellow Dye"),
            new NamedColors(0xBFFF00, "Lime Dye"),
            new NamedColors(0xFF69B4, "Pink Dye"),
            new NamedColors(0x808080, "Gray Dye"),
            new NamedColors(0xD3D3D3, "Light Gray Dye"),
            new NamedColors(0x00FFFF, "Cyan Dye"),
            new NamedColors(0xA020F0, "Purple Dye"),
            new NamedColors(0x0000FF, "Blue Dye"),
            new NamedColors(0x8B4513, "Brown Dye"),
            new NamedColors(0x00FF00, "Green Dye"),
            new NamedColors(0xFF0000, "Red Dye"),
            new NamedColors(0x000000, "Black Dye"),

            new NamedColors(0x7FB238, "PALE_GREEN"),
            new NamedColors(0xF7E9A3, "PALE_YELLOW"),
            new NamedColors(0xC7C7C7, "WHITE_GRAY"),
            new NamedColors(0xA0A0FF, "PALE_PURPLE"),
            new NamedColors(0xA7A7A7, "IRON_GRAY"),
            new NamedColors(0x007C00, "DARK_GREEN"),
            new NamedColors(0xA4A8B8, "LIGHT_BLUE_GRAY"),
            new NamedColors(0x976D4D, "DIRT_BROWN"),
            new NamedColors(0x707070, "STONE_GRAY"),
            new NamedColors(0x4040FF, "WATER_BLUE"),
            new NamedColors(0x8F7748, "OAK_TAN"),
            new NamedColors(0xFFFCF5, "OFF_WHITE"),
            new NamedColors(0xD87F33, "ORANGE"),
            new NamedColors(0xB24CD8, "MAGENTA"),
            new NamedColors(0x6699D8, "LIGHT_BLUE"),
            new NamedColors(0xE5E533, "YELLOW"),
            new NamedColors(0x7FCC19, "LIME"),
            new NamedColors(0xF27FA5, "PINK"),
            new NamedColors(0x4C4C4C, "GRAY"),
            new NamedColors(0x999999, "LIGHT_GRAY"),
            new NamedColors(0x4C7F99, "CYAN"),
            new NamedColors(0x7F3FB2, "PURPLE"),
            new NamedColors(0x334CB2, "BLUE"),
            new NamedColors(0x664C33, "BROWN"),
            new NamedColors(0x667F33, "GREEN"),
            new NamedColors(0x993333, "RED"),
            new NamedColors(0x191919, "BLACK"),
            new NamedColors(0xFAEE4D, "GOLD"),
            new NamedColors(0x5CDBD5, "DIAMOND_BLUE"),
            new NamedColors(0x4A80FF, "LAPIS_BLUE"),
            new NamedColors(0x00D93A, "EMERALD_GREEN"),
            new NamedColors(0x815631, "SPRUCE_BROWN"),
            new NamedColors(0x700200, "DARK_RED"),
            new NamedColors(0xD1B1A1, "TERRACOTTA_WHITE"),
            new NamedColors(0x9F5224, "TERRACOTTA_ORANGE"),
            new NamedColors(0x95576C, "TERRACOTTA_MAGENTA"),
            new NamedColors(0x706C8A, "TERRACOTTA_LIGHT_BLUE"),
            new NamedColors(0xBA8524, "TERRACOTTA_YELLOW"),
            new NamedColors(0x677535, "TERRACOTTA_LIME"),
            new NamedColors(0xA04D4E, "TERRACOTTA_PINK"),
            new NamedColors(0x392923, "TERRACOTTA_GRAY"),
            new NamedColors(0x876B62, "TERRACOTTA_LIGHT_GRAY"),
            new NamedColors(0x575C5C, "TERRACOTTA_CYAN"),
            new NamedColors(0x7A4958, "TERRACOTTA_PURPLE"),
            new NamedColors(0x4C3E5C, "TERRACOTTA_BLUE"),
            new NamedColors(0x4C3223, "TERRACOTTA_BROWN"),
            new NamedColors(0x4C522A, "TERRACOTTA_GREEN"),
            new NamedColors(0x8E3C2E, "TERRACOTTA_RED"),
            new NamedColors(0x251610, "TERRACOTTA_BLACK"),
            new NamedColors(0xBD3031, "DULL_RED"),
            new NamedColors(0x943F61, "DULL_PINK"),
            new NamedColors(0x5C191D, "DARK_CRIMSON"),
            new NamedColors(0x167E86, "TEAL"),
            new NamedColors(0x3A8E8C, "DARK_AQUA"),
            new NamedColors(0x562C3E, "DARK_DULL_PINK"),
            new NamedColors(0x14B485, "BRIGHT_TEAL"),
            new NamedColors(0x646464, "DEEPSLATE_GRAY"),
            new NamedColors(0xD8AF93, "RAW_IRON_PINK"),
            new NamedColors(0x7FA796, "LICHEN_GREEN")
    };

    static {
        for (NamedColors color : colors) {
            System.out.printf("%s:                            #%06X%n", color.name, color.color);
        }
    }

    public final int color;
    public final @NotNull String name;
    public final @NotNull String translation;

    private NamedColors(int color, @NotNull String name) {
        if (name.isEmpty()) throw new IllegalArgumentException("Name for named color may not be empty!");

        this.color = color;
        this.translation = NAMED_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(color);
        this.name = name;
    }

    public static NamedColors getClosestNamedColor(final int color) {
        // TODO: add check for when colors are equal to save calculating the distance.
        //  Good for testing right now as otherwise everything would've looked correct, even tho it thought everything was pink :concern:

        double closestDistance = Double.MAX_VALUE;
        NamedColors closestColor = null;

        for (NamedColors namedColor : colors) {
            final double distanceTo = namedColor.distanceTo(color);
            if (closestDistance < distanceTo) continue;

            closestDistance = distanceTo;
            closestColor = namedColor;
        }

        return closestColor;
    }

    public static NamedColors[] getAllColors() {
        return colors;
    }

    private double distanceTo(int rgb) {
        // TODO: some stuff can probably be like bytes and shorts and stuff

        final int red = (rgb >> 16) & 0xFF;
        final int green = (rgb >> 8) & 0xFF;
        final int blue = rgb & 0xFF;

        final int thisRed = (color >> 16) & 0xFF;
        final int thisGreen = (color >> 8) & 0xFF;
        final int thisBlue = color & 0xFF;

        final int deltaRedRoot   = (red   - thisRed) * (red   - thisRed);
        final int deltagreenRoot = (green - thisGreen) * (green - thisGreen);
        final int deltablueRoot  = (blue  - thisBlue) * (blue  - thisBlue);

        final float redWithWeirdLineOnTop = 0.5f * (red + thisRed);

        final double redmeanDistance;

        redmeanDistance = Math.sqrt((2 + (redWithWeirdLineOnTop / 256)) * deltaRedRoot + 4*deltagreenRoot + (2 + ((255 - redWithWeirdLineOnTop) / 256)) * deltablueRoot);

        return redmeanDistance;
    }
}
