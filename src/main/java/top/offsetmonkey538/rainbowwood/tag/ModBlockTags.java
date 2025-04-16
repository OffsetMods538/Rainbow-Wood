package top.offsetmonkey538.rainbowwood.tag;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModBlockTags {
    private ModBlockTags() {

    }

    public static final TagKey<Block> RAINBOW_LOGS = getKey("rainbow_logs");

    private static TagKey<Block> getKey(String name) {
        return TagKey.of(RegistryKeys.BLOCK, id(name));
    }

    @SuppressWarnings("EmptyMethod")
    public static void register() {
        // Don't think this class really has to be initialized at startup as it's not actually registering anything new, but still just in case.
    }
}
