package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import top.offsetmonkey538.rainbowwood.block.ITintedBlock;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

public class RainbowWoodClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (world == null || pos == null || !(state.getBlock() instanceof ITintedBlock tintedBlock)) return -1;
            return tintedBlock.getTint(world, state, pos);
        }, ModBlocks.BLOCKS.toArray(new Block[0]));

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			final Integer tintColor = stack.get(ModComponents.TINT_COLOR);
			if (tintColor == null) return -1;
			return tintColor;
		}, ModBlocks.BLOCKS.toArray(new Block[0]));

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RAINBOW_DOOR, RenderLayer.getCutout());
	}
}
