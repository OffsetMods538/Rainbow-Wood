package top.offsetmonkey538.rainbowwood.util;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static top.offsetmonkey538.rainbowwood.RainbowWood.LOGGER;

public final class NamedColors {

    public static final String NAMED_COLOR_TRANSLATION_KEY_TEMPLATE = "general.rainbow_wood.named_color.%s";
    private static final NamedColors[] colors = generateSortedColorArray(new NamedColors[]{
            // Dyes
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

            // Map Colors
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
            new NamedColors(0x7FA796, "LICHEN_GREEN"),

            // // Manual
            // new NamedColors(0x32CD32, "Lime Green"),
            // new NamedColors(0xFF4500, "Orange Red"),
            // new NamedColors(0xB22222, "Fire Brick")
    });

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
        // 0 to 255 is exactly 8 bits aka 1 byte, but java byte is signed so next best thing is short
        final short red = (short) ((rgb >> 16) & 0xFF);
        final short green = (short) ((rgb >> 8) & 0xFF);
        final short blue = (short) (rgb & 0xFF);

        // 0 to 255 is exactly 8 bits aka 1 byte, but java byte is signed so next best thing is short
        final short thisRed = (short) ((color >> 16) & 0xFF);
        final short thisGreen = (short) ((color >> 8) & 0xFF);
        final short thisBlue = (short) (color & 0xFF);

        // both sides have to be the same, so no (0-255) * (255-0) = -65025
        // min value = (0  -0) * (0  -0) =    0 * 0   = 0
        // max value = (255-0) * (255-0) =  255 * 255 = 65025
        //  max 16 bits, short is a *signed* 16-bit value, so the first bit is reserved for
        //  the sign so only 15-bit positive values fit. BUTTTT Java has the char primitive,
        //  which is an *unsigned* 16-bit integer and will fit these values perfectly
        final char deltaRedRoot = (char) ((red - thisRed) * (red - thisRed));
        final char deltaGreenRoot = (char) ((green - thisGreen) * (green - thisGreen));
        final char deltaBlueRoot = (char) ((blue - thisBlue) * (blue - thisBlue));

        final float meanRed = 0.5f * (red + thisRed);

        return Math.sqrt((2 + (meanRed / 256)) * deltaRedRoot + 4 * deltaGreenRoot + (2 + ((255 - meanRed) / 256)) * deltaBlueRoot);
    }

    private static NamedColors[] generateSortedColorArray(NamedColors[] colors) {
        List<NamedColors> sortedColors = Arrays.stream(colors).sorted((color1, color2) -> {
            final float[] hsv1 = Color.RGBtoHSB( (color1.color >> 16) & 0xFF, (color1.color >> 8) & 0xFF, (color1.color & 0xFF), null);
            final float[] hsv2 = Color.RGBtoHSB( (color2.color >> 16) & 0xFF, (color2.color >> 8) & 0xFF, (color2.color & 0xFF), null);

            int comparison = Float.compare(hsv1[0], hsv2[0]);
            if (comparison == 0) comparison = Float.compare(hsv1[1], hsv2[1]);
            if (comparison == 0) comparison = Float.compare(hsv1[2], hsv2[2]);
            return comparison;

            //final boolean isWhite1 = (hsv1[1] == 0 && hsv1[2] == 1);
            //final boolean isWhite2 = (hsv2[1] == 0 && hsv2[2] == 1);
            //if (isWhite1 ^ isWhite2) return isWhite1 ? -1 : 1;

            //final boolean isBlack1 = (hsv1[2] == 0);
            //final boolean isBlack2 = (hsv2[2] == 0);
            //if (isBlack1 ^ isBlack2) return isBlack1 ? -1 : 1;

            //return Float.compare(hsv1[0], hsv2[0]);
        }).collect(Collectors.toCollection(ArrayList::new));

        try {
            //noinspection unchecked
            sortedColors = (List<NamedColors>) sortedColors.getClass().getMethod("reversed").invoke(sortedColors);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            LOGGER.warn("Failed to find List.reversed() method. Must be running on JVM < 21, using own implementation...", e);
            for (int i = 0; i < sortedColors.size() / 2; i++) {
                final NamedColors color = sortedColors.get(1);
                sortedColors.set(i, sortedColors.get(sortedColors.size() - 1 - i));
                sortedColors.set(sortedColors.size() - 1 - i, color);
            }
        }

        return sortedColors.toArray(NamedColors[]::new);
    }

    public static void initialize() {
        // Initializes colors array by loading class
    }
}
