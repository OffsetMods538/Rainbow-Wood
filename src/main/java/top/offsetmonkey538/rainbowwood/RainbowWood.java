package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.offsetmonkey538.rainbowwood.attachment.ModBlockTintAttachedData;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.ModItems;

public class RainbowWood implements ModInitializer {
	public static final String MOD_ID = "rainbow-wood";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.register();
		ModComponents.register();
		ModItems.register();

		LOGGER.error("");
		LOGGER.error("");
		LOGGER.error("");
		LOGGER.error("");
		test(new BlockPos(12, -1956, 9));
		test(new BlockPos(0, -2032, 14));
		test(new BlockPos(15, 1956, 8));
		test(new BlockPos(3, 2032, 15));
		LOGGER.error("");
		LOGGER.error("");
		LOGGER.error("");
		LOGGER.error("");
	}
	private void test(BlockPos pos) {
		System.out.println(pos);
		System.out.println(ModBlockTintAttachedData.decodePos(ModBlockTintAttachedData.encodePos(pos)));
		System.out.println();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
