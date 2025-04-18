package top.offsetmonkey538.rainbowwood.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
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
        addBlockItemToItemTag(ModItemTags.RAINBOW_LOGS, ModBlocks.RAINBOW_LOG, ModBlocks.STRIPPED_RAINBOW_LOG /* TODO: wood, stripped wood */);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).addTag(ModItemTags.RAINBOW_LOGS);

        addBlockItemToItemTag(ItemTags.PLANKS, ModBlocks.RAINBOW_PLANKS);
        // TODO: addBlockItemToItemTag(ItemTags.WOODEN_STAIRS, );
        addBlockItemToItemTag(ItemTags.WOODEN_SLABS, ModBlocks.RAINBOW_SLAB);
        // TODO: addBlockItemToItemTag(ItemTags.WOODEN_FENCES, );
        // TODO: addBlockItemToItemTag(ItemTags.FENCE_GATES, );
        // TODO: addBlockItemToItemTag(ItemTags.WOODEN_DOORS, );
        // TODO: addBlockItemToItemTag(ItemTags.WOODEN_TRAPDOORS, );
        // TODO: addBlockItemToItemTag(ItemTags.WOODEN_PRESSURE_PLATES, );
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
