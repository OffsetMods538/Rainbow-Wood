package top.offsetmonkey538.rainbowwood.block;

import net.minecraft.block.BlockSetType;

public final class ModBlockSetTypes {
    private ModBlockSetTypes() {

    }

    public static final BlockSetType RAINBOW = BlockSetType.register(new BlockSetType("rainbow"));

    public static void register() {
        // Registers block set types by loading the class.
    }
}
