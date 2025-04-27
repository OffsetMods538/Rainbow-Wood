package top.offsetmonkey538.rainbowwood.api.datagen;

import com.google.common.collect.Lists;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.recipe.TintedShapedRecipe;
import top.offsetmonkey538.rainbowwood.recipe.TintedShapelessRecipe;

import java.util.*;

public class TintedShapelessRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    public final RecipeCategory category;
    public final Item output;
    public final int count;
    public final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable public String group;
    public final DefaultedList<Ingredient> inputs = DefaultedList.of();

    public TintedShapelessRecipeJsonBuilder(RecipeCategory category, ItemConvertible output, int count) {
        this.category = category;
        this.output = output.asItem();
        this.count = count;
    }

    public static TintedShapelessRecipeJsonBuilder create(RecipeCategory category, ItemConvertible output) {
        return create(category, output, 1);
    }
    public static TintedShapelessRecipeJsonBuilder create(RecipeCategory category, ItemConvertible output, int count) {
        return new TintedShapelessRecipeJsonBuilder(category, output, count);
    }

    public TintedShapelessRecipeJsonBuilder input(TagKey<Item> tag) {
        return input(Ingredient.fromTag(tag));
    }

    public TintedShapelessRecipeJsonBuilder input(ItemConvertible itemProvider) {
        return input(Ingredient.ofItems(itemProvider));
    }

    public TintedShapelessRecipeJsonBuilder input(Ingredient input) {
        inputs.add(input);
        return this;
    }

    @Override
    public TintedShapelessRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        criteria.put(name, criterion);
        return this;
    }

    @Override
    public TintedShapelessRecipeJsonBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getOutputItem() {
        return output;
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        validate(recipeId);

        final Advancement.Builder advancement = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        criteria.forEach(advancement::criterion);

        final TintedShapelessRecipe recipe = new TintedShapelessRecipe(
                Objects.requireNonNullElse(group, ""),
                CraftingRecipeJsonBuilder.toCraftingCategory(category),
                inputs,
                output,
                count
        );

        exporter.accept(recipeId, recipe, advancement.build(recipeId.withPrefixedPath("recipes/%s/".formatted(category.getName()))));
    }

    public final void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }
}
