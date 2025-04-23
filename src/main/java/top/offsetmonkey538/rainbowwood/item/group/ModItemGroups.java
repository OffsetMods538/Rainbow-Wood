package top.offsetmonkey538.rainbowwood.item.group;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.item.ITintedBlockItem;
import top.offsetmonkey538.rainbowwood.item.ModItems;
import top.offsetmonkey538.rainbowwood.util.NamedColor;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModItemGroups {
    private ModItemGroups() {

    }

    public static final ItemGroup RAINBOW_WOOD_ITEM_GROUP = register("rainbow_wood_item_group",
            FabricItemGroup.builder()
                    .icon(() -> ((ITintedBlockItem) ModBlocks.RAINBOW_PLANKS.asItem()).getStackWithTint(0xFF00FF)) // todo: Find some nice color for mod and item group icon.
                    .displayName(Text.translatable("itemGroup.rainbow_wood.main_group"))
                    .entries((displayContext, entries) -> {
                        for (Item item : ModItems.ITEMS) {
                            entries.add(item.getDefaultStack());
                        }

                        for (final NamedColor color : NamedColor.getAllColors()) {
                            for (Item item : ModItems.ITEMS) {
                                entries.add(((ITintedBlockItem) item).getStackWithTint(color.color));
                            }
                        }
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
