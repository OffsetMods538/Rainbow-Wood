package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.chunk.Chunk;
import top.offsetmonkey538.rainbowwood.attachment.ModAttachmentTypes;
import top.offsetmonkey538.rainbowwood.attachment.ModBlockTintAttachedData;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

public class RainbowWoodClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register((state, world1, pos, tintIndex) -> {
			if (pos == null) return 0;
			final ClientWorld world = MinecraftClient.getInstance().world;
			if (world == null) return 0;
			final Chunk chunk = world.getChunk(pos);
			//MinecraftClient.getInstance().world.getChunk()
            final ModBlockTintAttachedData tint = chunk.getAttachedOrElse(ModAttachmentTypes.BLOCK_TINT_TYPE, ModBlockTintAttachedData.DEFAULT);

			return tint.getTint(pos.getX() - chunk.getPos().getStartX(), pos.getY(), pos.getZ() - chunk.getPos().getStartZ());
		}, ModBlocks.RAINBOW_PLANKS);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			final Integer tintColor = stack.get(ModComponents.TINT_COLOR);
			if (tintColor == null) return 0;
			return tintColor;
		}, ModBlocks.RAINBOW_PLANKS);
	}
}
