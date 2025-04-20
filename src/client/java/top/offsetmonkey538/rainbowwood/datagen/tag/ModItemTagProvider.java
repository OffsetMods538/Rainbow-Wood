package top.offsetmonkey538.rainbowwood.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.tag.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        addBlockItemToItemTag(ConventionalItemTags.STRIPPED_LOGS, ModBlocks.STRIPPED_RAINBOW_LOG);
        addBlockItemToItemTag(ConventionalItemTags.STRIPPED_WOODS, ModBlocks.STRIPPED_RAINBOW_WOOD);
        addBlockItemToItemTag(ConventionalItemTags.WOODEN_FENCES, ModBlocks.RAINBOW_FENCE);
        // TODO: addBlockItemToItemTag(ConventionalItemTags.WOODEN_FENCE_GATES, ModBlocks.RAINBOW_FENCE_GATE);

        addBlockItemToItemTag(ModItemTags.RAINBOW_LOGS, ModBlocks.RAINBOW_LOG, ModBlocks.STRIPPED_RAINBOW_LOG, ModBlocks.RAINBOW_WOOD, ModBlocks.STRIPPED_RAINBOW_WOOD);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).addTag(ModItemTags.RAINBOW_LOGS);

        addBlockItemToItemTag(ItemTags.PLANKS, ModBlocks.RAINBOW_PLANKS);
        addBlockItemToItemTag(ItemTags.WOODEN_STAIRS, ModBlocks.RAINBOW_STAIRS);
        addBlockItemToItemTag(ItemTags.WOODEN_SLABS, ModBlocks.RAINBOW_SLAB);
        addBlockItemToItemTag(ItemTags.WOODEN_FENCES, ModBlocks.RAINBOW_FENCE);
        addBlockItemToItemTag(ItemTags.FENCE_GATES, ModBlocks.RAINBOW_FENCE_GATE);
        addBlockItemToItemTag(ItemTags.WOODEN_DOORS, ModBlocks.RAINBOW_DOOR);
        addBlockItemToItemTag(ItemTags.WOODEN_TRAPDOORS, ModBlocks.RAINBOW_TRAPDOOR);
        addBlockItemToItemTag(ItemTags.WOODEN_PRESSURE_PLATES, ModBlocks.RAINBOW_PRESSURE_PLATE);
        addBlockItemToItemTag(ItemTags.WOODEN_BUTTONS, ModBlocks.RAINBOW_BUTTON);
        // TODO: addBlockItemToItemTag(ItemTags.SIGNS, );
        // TODO: addBlockItemToItemTag(ItemTags.HANGING_SIGNS, );
    }

    private void addBlockItemToItemTag(final TagKey<Item> tag, @NotNull final Block... blocks) {
        final FabricTagBuilder builder = getOrCreateTagBuilder(tag);
        for (final Block block : blocks) {
            builder.add(block.asItem());
        }
    }
}
