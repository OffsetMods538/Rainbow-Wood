package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.block.entity.ModBlockEntities;
import top.offsetmonkey538.rainbowwood.block.entity.TintedBlockEntity;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

public class RainbowWoodClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (world == null) return 0;
            return world.getBlockEntity(pos, ModBlockEntities.TINTED_BLOCK_ENTITY).map(TintedBlockEntity::getTint).orElse(-1);
        }, ModBlocks.BLOCKS);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			final Integer tintColor = stack.get(ModComponents.TINT_COLOR);
			if (tintColor == null) return -1;
			return tintColor;
		}, ModBlocks.BLOCKS);
	}
}
