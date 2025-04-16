package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import top.offsetmonkey538.rainbowwood.block.entity.ModBlockEntities;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.ModItems;
import top.offsetmonkey538.rainbowwood.item.group.ModItemGroups;
import top.offsetmonkey538.rainbowwood.recipe.ModRecipes;
import top.offsetmonkey538.rainbowwood.tag.ModBlockTags;

//TODO: get clear blocks by dropping in cauldron with water. I guess clear would mean fully white? Or hmm what would actually just no tint look like? ig i'd have to make the fallback in the tint provider be -1 for that?
public class RainbowWood implements ModInitializer {
	public static final String MOD_ID = "rainbow-wood";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.register();
		ModBlockEntities.register();
		ModComponents.register();
		ModItems.register();
		ModItemGroups.register();
		ModRecipes.register();
		ModBlockTags.register();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
