package top.offsetmonkey538.rainbowwood.mixin;

import net.minecraft.block.MapColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.offsetmonkey538.rainbowwood.util.MapColorMapHolder;

@Mixin(MapColor.class)
// TODO: unused right now, map colors should probably be added to NamedColors, but with an easy way to get closest MapColor... I guess having each NamedColor instance contain a reference to it's closest MapColor?
public abstract class MapColorMixin {

    @Shadow @Final public static MapColor[] COLORS;

    @Inject(
            method = "<clinit>",
            at = @At("TAIL")
    )
    private static void rainbow_wood$generateReverseMapOfMapColorColorToMapColor(CallbackInfo ci) {
        for (final MapColor mapColor : COLORS) {
            if (mapColor == null) continue;
            MapColorMapHolder.addMapColor(mapColor);
        }
    }
}
