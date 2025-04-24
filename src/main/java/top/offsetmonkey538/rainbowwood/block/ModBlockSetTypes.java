package top.offsetmonkey538.rainbowwood.block;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.BlockSetType;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModBlockSetTypes {
    private ModBlockSetTypes() {

    }

    public static final BlockSetType RAINBOW = BlockSetTypeBuilder.copyOf(new BlockSetType("")).register(id("rainbow"));

    public static void register() {
        // Registers block set types by loading the class.
    }
}
