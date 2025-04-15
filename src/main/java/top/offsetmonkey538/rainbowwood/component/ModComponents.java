package top.offsetmonkey538.rainbowwood.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModComponents {
    private ModComponents() {

    }

    public static final ComponentType<Integer> TINT_COLOR = register(ComponentType.<Integer>builder().codec(Codec.INT).packetCodec(PacketCodecs.INTEGER).build(), "tint_color");

    private static <T> ComponentType<T> register(ComponentType<T> component, String name) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, id(name), component);
    }

    @SuppressWarnings("EmptyMethod")
    public static void register() {
        // Registers components by loading the class.
    }
}
