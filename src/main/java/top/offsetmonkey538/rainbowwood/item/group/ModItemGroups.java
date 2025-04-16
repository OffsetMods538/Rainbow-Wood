package top.offsetmonkey538.rainbowwood.item.group;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.item.TintedBlockItem;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModItemGroups {
    private ModItemGroups() {

    }

    public static final ItemGroup RAINBOW_WOOD_ITEM_GROUP = register("rainbow_wood_item_group",
            FabricItemGroup.builder()
                    .icon(() -> ((TintedBlockItem) ModBlocks.RAINBOW_PLANKS.asItem()).getStackWithTint(0xFF00FF)) // Find some nice color for mod and item group icon.
                    .displayName(Text.translatable("itemGroup.rainbow_wood.main_group"))
                    .entries((displayContext, entries) -> {
                        for (DyeColor dyeColor : DyeColor.values()) {
                            for (Block block : ModBlocks.BLOCKS) {
                                entries.add(((TintedBlockItem) block.asItem()).getStackWithTint(dyeColor.getSignColor()));
                            }
                        }

                        //for (int i = 0; i <= 0xFFFFFF; i++) {
                        //    entries.add(ModItems.RAINBOW_PLANKS.getStackWithTint(i));
                        //}

                        //entries.add(ModItems.RAINBOW_PLANKS.getStackWithTint(0x000000));

                        //entries.add(ModItems.RAINBOW_PLANKS.getStackWithTint(0xFF0000));
                        //entries.add(ModItems.RAINBOW_PLANKS.getStackWithTint(0xFFFF00));
                        //entries.add(ModItems.RAINBOW_PLANKS.getStackWithTint(0xFF00FF));
                        //entries.add(ModItems.RAINBOW_PLANKS.getStackWithTint(0x00FF00));
                        //entries.add(ModItems.RAINBOW_PLANKS.getStackWithTint(0x00FFFF));
                        //entries.add(ModItems.RAINBOW_PLANKS.getStackWithTint(0x0000FF));
                        //entries.add(ModItems.RAINBOW_PLANKS.getStackWithTint(0xFFFFFF));
                    })
                    .build()
    );


    private static <T extends ItemGroup> T register(String name, T group) {
        return Registry.register(Registries.ITEM_GROUP, id(name), group);
    }

    @SuppressWarnings("EmptyMethod")
    public static void register() {
        // Registers item groups by loading the class.
    }
}
