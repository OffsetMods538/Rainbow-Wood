package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

public class RainbowWoodClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register((state, world1, pos, tintIndex) -> {
			return 0x7f6a5e;
		}, ModBlocks.RAINBOW_PLANKS);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			final Integer tintColor = stack.get(ModComponents.TINT_COLOR);
			if (tintColor == null) return 0;
			return tintColor;
		}, ModBlocks.RAINBOW_PLANKS);
	}
}
