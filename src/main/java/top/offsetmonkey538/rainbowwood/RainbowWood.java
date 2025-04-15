package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
