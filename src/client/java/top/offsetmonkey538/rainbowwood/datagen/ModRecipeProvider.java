package top.offsetmonkey538.rainbowwood.datagen;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import top.offsetmonkey538.rainbowwood.recipe.ModRecipes;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        for (Pair<Function<CraftingRecipeCategory, ? extends SpecialCraftingRecipe>, String> recipeInfo : ModRecipes.RECIPES) {
            ComplexRecipeJsonBuilder
                    .create(craftingRecipeCategory -> recipeInfo.getFirst().apply(craftingRecipeCategory))
                    .offerTo(exporter, recipeInfo.getSecond());
        }
    }
}
