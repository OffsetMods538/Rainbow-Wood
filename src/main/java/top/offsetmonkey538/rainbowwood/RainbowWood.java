package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.offsetmonkey538.rainbowwood.block.ModBlockSetTypes;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import top.offsetmonkey538.rainbowwood.block.ModWoodTypes;
import top.offsetmonkey538.rainbowwood.block.entity.ModBlockEntities;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.ModItems;
import top.offsetmonkey538.rainbowwood.item.group.ModItemGroups;
import top.offsetmonkey538.rainbowwood.recipe.ModRecipes;
import top.offsetmonkey538.rainbowwood.tag.ModBlockTags;
import top.offsetmonkey538.rainbowwood.tag.ModItemTags;

public class RainbowWood implements ModInitializer {
	public static final String MOD_ID = "rainbow-wood";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlockSetTypes.register();
		ModWoodTypes.register();
		ModBlocks.register();
		ModBlockEntities.register();
		ModComponents.register();
		ModItems.register();
		ModItemGroups.register();
		ModRecipes.register();
		ModBlockTags.register();
		ModItemTags.register();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
