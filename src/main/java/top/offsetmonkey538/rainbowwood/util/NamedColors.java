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
            new NamedColors(0x7FB238, "Pale Green"),
            new NamedColors(0xF7E9A3, "Pale Yellow"),
            new NamedColors(0xC7C7C7, "White Gray"),
            new NamedColors(0xA0A0FF, "Pale Purple"),
            new NamedColors(0xA7A7A7, "Iron Gray"),
            new NamedColors(0x007C00, "Dark Green"),
            new NamedColors(0xA4A8B8, "Light Blue Gray"),
            new NamedColors(0x976D4D, "Dirt Brown"),
            new NamedColors(0x707070, "Stone Gray"),
            new NamedColors(0x4040FF, "Water Blue"),
            new NamedColors(0x8F7748, "Oak Tan"),
            new NamedColors(0xFFFCF5, "Off White"),
            new NamedColors(0xD87F33, "Orange"),
            new NamedColors(0xB24CD8, "Magenta"),
            new NamedColors(0x6699D8, "Light Blue"),
            new NamedColors(0xE5E533, "Yellow"),
            new NamedColors(0x7FCC19, "Lime"),
            new NamedColors(0xF27FA5, "Pink"),
            new NamedColors(0x4C4C4C, "Gray"),
            new NamedColors(0x999999, "Light Gray"),
            new NamedColors(0x4C7F99, "Cyan"),
            new NamedColors(0x7F3FB2, "Purple"),
            new NamedColors(0x334CB2, "Blue"),
            new NamedColors(0x664C33, "Brown"),
            new NamedColors(0x667F33, "Green"),
            new NamedColors(0x993333, "Red"),
            new NamedColors(0x191919, "Black"),
            new NamedColors(0xFAEE4D, "Gold"),
            new NamedColors(0x5CDBD5, "Diamond Blue"),
            new NamedColors(0x4A80FF, "Lapis Blue"),
            new NamedColors(0x00D93A, "Emerald Green"),
            new NamedColors(0x815631, "Spruce Brown"),
            new NamedColors(0x700200, "Dark Red"),
            new NamedColors(0xD1B1A1, "Terracotta White"),
            new NamedColors(0x9F5224, "Terracotta Orange"),
            new NamedColors(0x95576C, "Terracotta Magenta"),
            new NamedColors(0x706C8A, "Terracotta Light Blue"),
            new NamedColors(0xBA8524, "Terracotta Yellow"),
            new NamedColors(0x677535, "Terracotta Lime"),
            new NamedColors(0xA04D4E, "Terracotta Pink"),
            new NamedColors(0x392923, "Terracotta Gray"),
            new NamedColors(0x876B62, "Terracotta Light Gray"),
            new NamedColors(0x575C5C, "Terracotta Cyan"),
            new NamedColors(0x7A4958, "Terracotta Purple"),
            new NamedColors(0x4C3E5C, "Terracotta Blue"),
            new NamedColors(0x4C3223, "Terracotta Brown"),
            new NamedColors(0x4C522A, "Terracotta Green"),
            new NamedColors(0x8E3C2E, "Terracotta Red"),
            new NamedColors(0x251610, "Terracotta Black"),
            new NamedColors(0xBD3031, "Dull Red"),
            new NamedColors(0x943F61, "Dull Pink"),
            new NamedColors(0x5C191D, "Dark Crimson"),
            new NamedColors(0x167E86, "Teal"),
            new NamedColors(0x3A8E8C, "Dark Aqua"),
            new NamedColors(0x562C3E, "Dark Dull Pink"),
            new NamedColors(0x14B485, "Bright Teal"),
            new NamedColors(0x646464, "Deepslate Gray"),
            new NamedColors(0xD8AF93, "Raw Iron Pink"),
            new NamedColors(0x7FA796, "Lichen Green"),

            // Manual
            new NamedColors(0x0072CE, "Estonian Blue"),
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
