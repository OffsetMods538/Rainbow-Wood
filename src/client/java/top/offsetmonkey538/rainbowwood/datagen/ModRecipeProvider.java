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
        TintedShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItemTags.RAINBOW_LOGS, ModBlocks.RAINBOW_PLANKS, 4)
                .pattern("#")
                .group("planks")
                .criterion("has_logs", conditionsFromTag(ModItemTags.RAINBOW_LOGS))
                .offerTo(exporter);

        TintedShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAINBOW_PLANKS, ModBlocks.RAINBOW_SLAB, 6)
                .pattern("###")
                .group("slabs")
                .criterion("has_planks", conditionsFromItem(ModBlocks.RAINBOW_PLANKS))
                .offerTo(exporter);

        TintedShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.RAINBOW_PLANKS, ModBlocks.RAINBOW_BUTTON, 1)
                .pattern("#")
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
