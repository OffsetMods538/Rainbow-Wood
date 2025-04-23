package top.offsetmonkey538.rainbowwood.util;

import net.minecraft.block.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.TreeMap;

// TODO: unused right now, map colors should probably be added to NamedColors, but with an easy way to get closest MapColor... I guess having each NamedColor instance contain a reference to it's closest MapColor?
public final class MapColorMapHolder {
    private MapColorMapHolder() {

    }

    // color to translation key
    private static final TreeMap<Integer, String> mapColorReverseMap = new TreeMap<>();

    public static void addMapColor(final @NotNull MapColor mapColor) {
        mapColorReverseMap.put(mapColor.color, NamedColor.NAMED_COLOR_TRANSLATION_KEY_TEMPLATE.formatted(mapColor.color));
    }

    public static int getClosestMapColor(final int color) {
        final int closestCeilColor = mapColorReverseMap.ceilingKey(color);
        final int closestFloorColor = mapColorReverseMap.floorKey(color);

        return Math.abs(color - closestCeilColor) > Math.abs(closestFloorColor - color) ? closestCeilColor : closestFloorColor;
    }

    public static String get(final int color) {
        return mapColorReverseMap.get(color);
    }
}
