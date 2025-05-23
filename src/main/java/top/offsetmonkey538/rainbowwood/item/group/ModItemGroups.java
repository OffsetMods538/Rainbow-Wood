package top.offsetmonkey538.rainbowwood.item.group;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import top.offsetmonkey538.rainbowwood.item.ITintedItem;
import top.offsetmonkey538.rainbowwood.item.ModItems;
import top.offsetmonkey538.rainbowwood.util.NamedColor;

import java.util.LinkedList;
import java.util.List;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public final class ModItemGroups {
    private ModItemGroups() {

    }

    private static final ItemStack[] RAINBOW_WOOD_ITEM_GROUP_CONTENT;

    static {
        final List<ItemStack> content = new LinkedList<>();

        for (Item item : ModItems.ITEMS) {
            content.add(item.getDefaultStack());
        }

        for (final NamedColor color : NamedColor.getAllColors()) {
            for (Item item : ModItems.ITEMS) {
                content.add(((ITintedItem) item).getStackWithTint(color.color));
            }
        }

        RAINBOW_WOOD_ITEM_GROUP_CONTENT = content.toArray(ItemStack[]::new);
    }

    public static final ItemGroup RAINBOW_WOOD_ITEM_GROUP = register("rainbow_wood_item_group",
            FabricItemGroup.builder()
                    // Icon set in CreativeInventoryScreenMixin
                    .displayName(Text.translatable("itemGroup.rainbow_wood.main_group"))
                    .entries((displayContext, entries) -> {
                        // Probably better (or at least fine) to loop through an array and add each one myself
                        //  rather than storing a List and having addAll use the lists forEach method?
                        for (ItemStack item : RAINBOW_WOOD_ITEM_GROUP_CONTENT) {
                            entries.add(item);
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
