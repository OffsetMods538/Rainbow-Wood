package top.offsetmonkey538.rainbowwood.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.loot.function.CopyComponentsLootFunction;
import net.minecraft.registry.RegistryWrapper;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.component.ModComponents;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for (Block block : ModBlocks.BLOCKS) {
            if (block == ModBlocks.RAINBOW_DOOR) continue;

            this.addDrop(block, this.drops(block).apply(
                    CopyComponentsLootFunction.builder(CopyComponentsLootFunction.Source.BLOCK_ENTITY)
                            .include(ModComponents.TINT_COLOR)
            ));
        }

        this.addDrop(ModBlocks.RAINBOW_DOOR, this.doorDrops(ModBlocks.RAINBOW_DOOR).apply(
                CopyComponentsLootFunction.builder(CopyComponentsLootFunction.Source.BLOCK_ENTITY)
                        .include(ModComponents.TINT_COLOR)
        ));
    }
}
