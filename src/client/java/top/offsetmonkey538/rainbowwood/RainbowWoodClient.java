package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.Item;
import top.offsetmonkey538.rainbowwood.block.ITintedBlock;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.block.entity.ModBlockEntities;
import top.offsetmonkey538.rainbowwood.client.render.block.entity.TintedSignBlockEntityRenderer;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.ModItems;

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
		}, ModItems.ITEMS.toArray(new Item[0]));

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.RAINBOW_DOOR, ModBlocks.RAINBOW_TRAPDOOR);

		BlockEntityRendererFactories.register(ModBlockEntities.TINTED_SIGN_BLOCK_ENTITY, TintedSignBlockEntityRenderer::new);
	}
}