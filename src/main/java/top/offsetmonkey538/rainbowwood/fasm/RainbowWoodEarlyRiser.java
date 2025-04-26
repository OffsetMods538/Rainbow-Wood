package top.offsetmonkey538.rainbowwood.fasm;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.entity.vehicle.BoatEntity;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

public class RainbowWoodEarlyRiser implements Runnable {
    private static BoatEntity.Type rainbowType;

    @Override
    public void run() {
        final MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        final String boatEntityType = remapper.mapClassName("intermediary", "net.minecraft.class_1690$class_1692");
        final String block = "L%s;".formatted(remapper.mapClassName("intermediary", "net.minecraft.class_2248"));
        final String string = "Ljava/lang/String;";
        ClassTinkerers.enumBuilder(boatEntityType, block, string).addEnum("RAINBOW", () -> new Object[] {ModBlocks.RAINBOW_PLANKS, "rainbow"}).build();
    }

    public static BoatEntity.Type getRainbowType() {
        if (rainbowType == null) rainbowType = ClassTinkerers.getEnum(BoatEntity.Type.class, "RAINBOW");
        return rainbowType;
    }
}
