package top.offsetmonkey538.rainbowwood.util;

import net.minecraft.block.MapColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static top.offsetmonkey538.rainbowwood.RainbowWood.LOGGER;

public final class NamedColor {

    public static final String NAMED_COLOR_TRANSLATION_KEY_TEMPLATE = "general.rainbow_wood.named_color.%s";
    private static final List<NamedColor> withMapColor = new LinkedList<>();
    private static final NamedColor[] colors = generateSortedColorArray(new NamedColor[]{
            // Dyes
            new NamedColor(0xFFFFFF, "White Dye"),
            new NamedColor(0xFF681F, "Orange Dye"),
            new NamedColor(0xFF00FF, "Magenta Dye"),
            new NamedColor(0x9AC0CD, "Light Blue Dye"),
            new NamedColor(0xFFFF00, "Yellow Dye"),
            new NamedColor(0xBFFF00, "Lime Dye"),
            new NamedColor(0xFF69B4, "Pink Dye"),
            new NamedColor(0x808080, "Gray Dye"),
            new NamedColor(0xD3D3D3, "Light Gray Dye"),
            new NamedColor(0x00FFFF, "Cyan Dye"),
            new NamedColor(0xA020F0, "Purple Dye"),
            new NamedColor(0x0000FF, "Blue Dye"),
            new NamedColor(0x8B4513, "Brown Dye", MapColor.BROWN.id),
            new NamedColor(0x00FF00, "Green Dye"),
            new NamedColor(0xFF0000, "Red Dye"),
            new NamedColor(0x000000, "Black Dye", MapColor.BLACK.id),

            // Map Colors
            new NamedColor(0x7FB238, "Pale Green", MapColor.PALE_GREEN.id),
            new NamedColor(0xF7E9A3, "Pale Yellow", MapColor.PALE_YELLOW.id),
            new NamedColor(0xC7C7C7, "White Gray", MapColor.WHITE_GRAY.id),
            new NamedColor(0xA0A0FF, "Pale Purple", MapColor.PALE_PURPLE.id),
            new NamedColor(0xA7A7A7, "Iron Gray", MapColor.IRON_GRAY.id),
            new NamedColor(0x007C00, "Dark Green", MapColor.DARK_GREEN.id),
            new NamedColor(0xA4A8B8, "Light Blue Gray", MapColor.LIGHT_BLUE_GRAY.id),
            new NamedColor(0x976D4D, "Dirt Brown", MapColor.DIRT_BROWN.id),
            new NamedColor(0x707070, "Stone Gray", MapColor.STONE_GRAY.id),
            new NamedColor(0x4040FF, "Water Blue", MapColor.WATER_BLUE.id),
            new NamedColor(0x8F7748, "Oak Tan", MapColor.OAK_TAN.id),
            new NamedColor(0xFFFCF5, "Off White", MapColor.OFF_WHITE.id),
            new NamedColor(0xD87F33, "Orange", MapColor.ORANGE.id),
            new NamedColor(0xB24CD8, "Magenta", MapColor.MAGENTA.id),
            new NamedColor(0x6699D8, "Light Blue", MapColor.LIGHT_BLUE.id),
            new NamedColor(0xE5E533, "Yellow", MapColor.YELLOW.id),
            new NamedColor(0x7FCC19, "Lime", MapColor.LIME.id),
            new NamedColor(0xF27FA5, "Pink", MapColor.PINK.id),
            new NamedColor(0x4C4C4C, "Gray", MapColor.GRAY.id),
            new NamedColor(0x999999, "Light Gray", MapColor.LIGHT_GRAY.id),
            new NamedColor(0x4C7F99, "Cyan", MapColor.CYAN.id),
            new NamedColor(0x7F3FB2, "Purple", MapColor.PURPLE.id),
            new NamedColor(0x334CB2, "Blue", MapColor.BLUE.id),
            new NamedColor(0x664C33, "Brown", MapColor.BROWN.id),
            new NamedColor(0x667F33, "Green", MapColor.GREEN.id),
            new NamedColor(0x993333, "Red", MapColor.RED.id),
            new NamedColor(0x191919, "Black", MapColor.BLACK.id),
            new NamedColor(0xFAEE4D, "Gold", MapColor.GOLD.id),
            new NamedColor(0x5CDBD5, "Diamond Blue", MapColor.DIAMOND_BLUE.id),
            new NamedColor(0x4A80FF, "Lapis Blue", MapColor.LAPIS_BLUE.id),
            new NamedColor(0x00D93A, "Emerald Green", MapColor.EMERALD_GREEN.id),
            new NamedColor(0x815631, "Spruce Brown", MapColor.SPRUCE_BROWN.id),
            new NamedColor(0x700200, "Dark Red", MapColor.DARK_RED.id),
            new NamedColor(0xD1B1A1, "Terracotta White", MapColor.TERRACOTTA_WHITE.id),
            new NamedColor(0x9F5224, "Terracotta Orange", MapColor.TERRACOTTA_ORANGE.id),
            new NamedColor(0x95576C, "Terracotta Magenta", MapColor.TERRACOTTA_MAGENTA.id),
            new NamedColor(0x706C8A, "Terracotta Light Blue", MapColor.TERRACOTTA_LIGHT_BLUE.id),
            new NamedColor(0xBA8524, "Terracotta Yellow", MapColor.TERRACOTTA_YELLOW.id),
            new NamedColor(0x677535, "Terracotta Lime", MapColor.TERRACOTTA_LIME.id),
            new NamedColor(0xA04D4E, "Terracotta Pink", MapColor.TERRACOTTA_PINK.id),
            new NamedColor(0x392923, "Terracotta Gray", MapColor.TERRACOTTA_GRAY.id),
            new NamedColor(0x876B62, "Terracotta Light Gray", MapColor.TERRACOTTA_LIGHT_GRAY.id),
            new NamedColor(0x575C5C, "Terracotta Cyan", MapColor.TERRACOTTA_CYAN.id),
            new NamedColor(0x7A4958, "Terracotta Purple", MapColor.TERRACOTTA_PURPLE.id),
            new NamedColor(0x4C3E5C, "Terracotta Blue", MapColor.TERRACOTTA_BLUE.id),
            new NamedColor(0x4C3223, "Terracotta Brown", MapColor.TERRACOTTA_BROWN.id),
            new NamedColor(0x4C522A, "Terracotta Green", MapColor.TERRACOTTA_GREEN.id),
            new NamedColor(0x8E3C2E, "Terracotta Red", MapColor.TERRACOTTA_RED.id),
            new NamedColor(0x251610, "Terracotta Black", MapColor.TERRACOTTA_BLACK.id),
            new NamedColor(0xBD3031, "Dull Red", MapColor.DULL_RED.id),
            new NamedColor(0x943F61, "Dull Pink", MapColor.DULL_PINK.id),
            new NamedColor(0x5C191D, "Dark Crimson", MapColor.DARK_CRIMSON.id),
            new NamedColor(0x167E86, "Teal", MapColor.TEAL.id),
            new NamedColor(0x3A8E8C, "Dark Aqua", MapColor.DARK_AQUA.id),
            new NamedColor(0x562C3E, "Dark Dull Pink", MapColor.DARK_DULL_PINK.id),
            new NamedColor(0x14B485, "Bright Teal", MapColor.BRIGHT_TEAL.id),
            new NamedColor(0x646464, "Deepslate Gray", MapColor.DEEPSLATE_GRAY.id),
            new NamedColor(0xD8AF93, "Raw Iron Pink", MapColor.RAW_IRON_PINK.id),
            new NamedColor(0x7FA796, "Lichen Green", MapColor.LICHEN_GREEN.id),

            // Manual
            new NamedColor(0x0072CE, "Estonian Blue"),
    });

    static {
        for (NamedColor color : colors) {
            System.out.printf("%s:                            #%06X%n", color.name, color.color);
        }
    }

    public final int color;
    public final @NotNull String name;
    public final @NotNull String translation;
    public final @Nullable Integer mapColorId;

    private NamedColor(int color, @NotNull String name) {
        this(color, name, null);
    }
    private NamedColor(int color, @NotNull String name, @Nullable Integer mapColorId) {
        if (name.isEmpty()) throw new IllegalArgumentException("Name for named color may not be empty!");

        this.color = color;
        this.translation = NAMED_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(color);
        this.name = name;
        this.mapColorId = mapColorId;

        if (mapColorId != null) withMapColor.add(this);
    }

    public static MapColor getClosestMapColor(final int color) {
        NamedColor closestColor = getClosestNamedColorWithMapColor(color);
        //noinspection DataFlowIssue: null checked by getClosestNamedColorWithMapColor
        return MapColor.get(closestColor.mapColorId);
    }

    public static NamedColor getClosestNamedColorWithMapColor(final int color) {
        final NamedColor closestColor = getClosestNamedColorInArray(color, withMapColor.toArray(NamedColor[]::new));
        if (closestColor.mapColorId == null) throw new IllegalStateException("NamedColor '%s' from 'NamedColor.mapColors' array doesn't define 'mapColorId'!");
        return closestColor;
    }

    public static NamedColor getClosestNamedColor(final int color) {
        return getClosestNamedColorInArray(color, colors);
    }

    private static NamedColor getClosestNamedColorInArray(final int color, @NotNull final NamedColor[] colorsArray) {
        double closestDistance = Double.MAX_VALUE;
        NamedColor closestColor = null;

        for (NamedColor namedColor : colorsArray) {
            final double distanceTo = namedColor.distanceTo(color);
            if (closestDistance < distanceTo) continue;

            closestDistance = distanceTo;
            closestColor = namedColor;
        }

        return closestColor;
    }

    public static NamedColor[] getAllColors() {
        return colors;
    }

    private double distanceTo(int rgb) {
        if (rgb == this.color) return 0;


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

    private static NamedColor[] generateSortedColorArray(NamedColor[] colors) {
        List<NamedColor> sortedColors = Arrays.stream(colors).sorted((color1, color2) -> {
            final float[] hsv1 = Color.RGBtoHSB( (color1.color >> 16) & 0xFF, (color1.color >> 8) & 0xFF, (color1.color & 0xFF), null);
            final float[] hsv2 = Color.RGBtoHSB( (color2.color >> 16) & 0xFF, (color2.color >> 8) & 0xFF, (color2.color & 0xFF), null);

            int comparison = Float.compare(hsv1[0], hsv2[0]);
            if (comparison == 0) comparison = Float.compare(hsv1[1], hsv2[1]);
            if (comparison == 0) comparison = Float.compare(hsv1[2], hsv2[2]);
            return comparison;
        }).collect(Collectors.toCollection(ArrayList::new));

        try {
            //noinspection unchecked
            sortedColors = (List<NamedColor>) sortedColors.getClass().getMethod("reversed").invoke(sortedColors);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            LOGGER.warn("Failed to find List.reversed() method. Must be running on JVM < 21, using own implementation...", e);
            for (int i = 0; i < sortedColors.size() / 2; i++) {
                final NamedColor color = sortedColors.get(1);
                sortedColors.set(i, sortedColors.get(sortedColors.size() - 1 - i));
                sortedColors.set(sortedColors.size() - 1 - i, color);
            }
        }

        return sortedColors.toArray(NamedColor[]::new);
    }

    public static void initialize() {
        // Initializes colors array by loading class
    }
}
