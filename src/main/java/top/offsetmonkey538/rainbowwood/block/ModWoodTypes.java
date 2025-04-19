package top.offsetmonkey538.rainbowwood.block;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.*;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModWoodTypes {
    private ModWoodTypes() {

    }

    public static final WoodType RAINBOW = WoodTypeBuilder.copyOf(WoodType.OAK).register(id("rainbow"), ModBlockSetTypes.RAINBOW);

    public static void register() {
        // Registers blocks by loading the class.
    }
}
