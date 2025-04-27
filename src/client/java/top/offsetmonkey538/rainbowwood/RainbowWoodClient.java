package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.Item;
import top.offsetmonkey538.rainbowwood.block.ITintedBlock;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.block.entity.ModBlockEntities;
import top.offsetmonkey538.rainbowwood.client.render.block.entity.TintedHangingSignBlockEntityRenderer;
import top.offsetmonkey538.rainbowwood.client.render.block.entity.TintedSignBlockEntityRenderer;
import top.offsetmonkey538.rainbowwood.client.render.entity.TintedBoatEntityRenderer;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.entity.ModEntityTypes;
import top.offsetmonkey538.rainbowwood.item.ModItems;

public class RainbowWoodClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (world == null || pos == null || !(state.getBlock() instanceof ITintedBlock tintedBlock)) return -1;
			return tintedBlock.getTint(world, state, pos);
		}, ModBlocks.BLOCKS.toArray(new Block[0]));


		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			// Layer 1 doesn't have tint
			if (tintIndex == 1) return -1;

			final Integer tintColor = stack.get(ModComponents.TINT_COLOR);
			if (tintColor == null) return -1;
			return tintColor | 0xFF000000; // Only required by the boats for some reason, no idea why other items don't care about the alpha value lol
		}, ModItems.ITEMS.toArray(new Item[0]));

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.RAINBOW_DOOR, ModBlocks.RAINBOW_TRAPDOOR);

		BlockEntityRendererFactories.register(ModBlockEntities.TINTED_SIGN_BLOCK_ENTITY, TintedSignBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(ModBlockEntities.TINTED_HANGING_SIGN_BLOCK_ENTITY, TintedHangingSignBlockEntityRenderer::new);

		EntityRendererRegistry.register(ModEntityTypes.TINTED_BOAT, ctx -> new TintedBoatEntityRenderer(ctx, false));
		EntityRendererRegistry.register(ModEntityTypes.TINTED_CHEST_BOAT, ctx -> new TintedBoatEntityRenderer(ctx, true));
	}
}