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
import org.jetbrains.annotations.Nullable;
import top.offsetmonkey538.rainbowwood.recipe.TintedShapedRecipe;

import java.util.*;

public class TintedShapedRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    public final RecipeCategory category;
    public final Item output;
    public final int count;
    private final List<String> pattern = Lists.newArrayList();
    public final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable public String group;
    public Map<Character, Ingredient> inputs = new HashMap<>();

    public TintedShapedRecipeJsonBuilder(RecipeCategory category, ItemConvertible output, int count) {
        this.category = category;
        this.output = output.asItem();
        this.count = count;
    }

    public static TintedShapedRecipeJsonBuilder create(RecipeCategory category, ItemConvertible output) {
        return create(category, output, 1);
    }
    public static TintedShapedRecipeJsonBuilder create(RecipeCategory category, ItemConvertible output, int count) {
        return new TintedShapedRecipeJsonBuilder(category, output, count);
    }

    public TintedShapedRecipeJsonBuilder input(char character, TagKey<Item> tag) {
        return input(character, Ingredient.fromTag(tag));
    }

    public TintedShapedRecipeJsonBuilder input(char character, ItemConvertible itemProvider) {
        return input(character, Ingredient.ofItems(itemProvider));
    }

    public TintedShapedRecipeJsonBuilder input(char character, Ingredient input) {
        if (inputs.containsKey(character)) throw new IllegalArgumentException("Symbol '%s' is already defined!".formatted(character));
        if (character == ' ') throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        inputs.put(character, input);
        return this;
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
