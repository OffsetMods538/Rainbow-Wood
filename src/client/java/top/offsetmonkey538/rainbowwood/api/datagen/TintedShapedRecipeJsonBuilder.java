package top.offsetmonkey538.rainbowwood.api.datagen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.recipe.TintedShapedRecipe;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TintedShapedRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    public final RecipeCategory category;
    public final Ingredient input;
    public final Item output;
    public final int count;
    private final List<String> pattern = Lists.<String>newArrayList();
    public final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable public String group;

    public TintedShapedRecipeJsonBuilder(RecipeCategory category, Ingredient input, ItemConvertible output, int count) {
        this.category = category;
        this.input = input;
        this.output = output.asItem();
        this.count = count;
    }

    public static TintedShapedRecipeJsonBuilder create(RecipeCategory category, TagKey<Item> input, ItemConvertible output, int count) {
        return create(category, Ingredient.fromTag(input), output, count);
    }
    public static TintedShapedRecipeJsonBuilder create(RecipeCategory category, ItemConvertible input, ItemConvertible output, int count) {
        return create(category, Ingredient.ofItems(input), output, count);
    }
    public static TintedShapedRecipeJsonBuilder create(RecipeCategory category, Ingredient input, ItemConvertible output, int count) {
        return new TintedShapedRecipeJsonBuilder(category, input, output, count);
    }

    public TintedShapedRecipeJsonBuilder pattern(String pattern) {
        if (!this.pattern.isEmpty() && pattern.length() != this.pattern.get(0).length()) throw new IllegalArgumentException("Pattern must be the same width on every line!");
        this.pattern.add(pattern);
        return this;
    }

    @Override
    public TintedShapedRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        criteria.put(name, criterion);
        return this;
    }

    @Override
    public TintedShapedRecipeJsonBuilder group(@Nullable String group) {
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

        final TintedShapedRecipe recipe = new TintedShapedRecipe(
                Objects.requireNonNullElse(group, ""),
                CraftingRecipeJsonBuilder.toCraftingCategory(category),
                pattern,
                input,
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
