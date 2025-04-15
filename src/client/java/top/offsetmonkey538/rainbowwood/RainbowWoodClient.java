package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

public class RainbowWoodClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			return 0xff00ff;
		}, ModBlocks.RAINBOW_PLANKS);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			return 0xff00ff;
		}, ModBlocks.RAINBOW_PLANKS);
	}
}
