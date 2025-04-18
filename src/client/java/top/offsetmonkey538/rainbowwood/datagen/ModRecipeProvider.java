package top.offsetmonkey538.rainbowwood.datagen;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import top.offsetmonkey538.rainbowwood.api.datagen.TintedShapedRecipeJsonBuilder;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.recipe.ModRecipes;
import top.offsetmonkey538.rainbowwood.tag.ModItemTags;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        TintedShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAINBOW_PLANKS, 4)
                .pattern("#")
                .input('#', ModItemTags.RAINBOW_LOGS)
                .group("planks")
                .criterion("has_logs", conditionsFromTag(ModItemTags.RAINBOW_LOGS))
                .offerTo(exporter);

        TintedShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAINBOW_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.RAINBOW_LOG)
                .group("wood")
                .criterion("has_logs", conditionsFromItem(ModBlocks.RAINBOW_LOG))
                .offerTo(exporter);

        TintedShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_RAINBOW_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.STRIPPED_RAINBOW_LOG)
                .group("stripped_wood")
                .criterion("has_stripped_logs", conditionsFromItem(ModBlocks.STRIPPED_RAINBOW_LOG))
                .offerTo(exporter);

        TintedShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAINBOW_SLAB, 6)
                .pattern("###")
                .input('#', ModBlocks.RAINBOW_PLANKS)
                .group("slabs")
                .criterion("has_planks", conditionsFromItem(ModBlocks.RAINBOW_PLANKS))
                .offerTo(exporter);

        TintedShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAINBOW_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.RAINBOW_PLANKS)
                .group("stairs")
                .criterion("has_planks", conditionsFromItem(ModBlocks.RAINBOW_PLANKS))
                .offerTo(exporter);

        TintedShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.RAINBOW_BUTTON)
                .pattern("#")
                .input('#', ModBlocks.RAINBOW_PLANKS)
                .group("buttons")
                .criterion("has_planks", conditionsFromItem(ModBlocks.RAINBOW_PLANKS))
                .offerTo(exporter);

        for (Pair<Function<CraftingRecipeCategory, ? extends SpecialCraftingRecipe>, String> recipeInfo : ModRecipes.COLRING_RECIPES) {
            ComplexRecipeJsonBuilder
                    .create(craftingRecipeCategory -> recipeInfo.getFirst().apply(craftingRecipeCategory))
                    .offerTo(exporter, recipeInfo.getSecond());
        }
    }
}
