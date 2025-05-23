package top.offsetmonkey538.rainbowwood;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.offsetmonkey538.rainbowwood.block.ModBlockSetTypes;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;

import top.offsetmonkey538.rainbowwood.block.ModWoodTypes;
import top.offsetmonkey538.rainbowwood.block.entity.ModBlockEntities;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.entity.ModEntityTypes;
import top.offsetmonkey538.rainbowwood.fasm.RainbowWoodEarlyRiser;
import top.offsetmonkey538.rainbowwood.item.ModItems;
import top.offsetmonkey538.rainbowwood.item.group.ModItemGroups;
import top.offsetmonkey538.rainbowwood.recipe.ModRecipes;
import top.offsetmonkey538.rainbowwood.tag.ModBlockTags;
import top.offsetmonkey538.rainbowwood.tag.ModItemTags;
import top.offsetmonkey538.rainbowwood.util.NamedColor;

public class RainbowWood implements ModInitializer {
	public static final String MOD_ID = "rainbow-wood";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		NamedColor.initialize();

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
		ModEntityTypes.register();

		DispenserBlock.registerBehavior(ModItems.TINTED_BOAT, new BoatDispenserBehavior(RainbowWoodEarlyRiser.getRainbowType()));
		DispenserBlock.registerBehavior(ModItems.TINTED_CHEST_BOAT, new BoatDispenserBehavior(RainbowWoodEarlyRiser.getRainbowType(), true));
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
