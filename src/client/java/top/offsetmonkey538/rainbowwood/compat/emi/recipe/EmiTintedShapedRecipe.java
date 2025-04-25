package top.offsetmonkey538.rainbowwood.compat.emi.recipe;

import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiPatternCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.GeneratedSlotWidget;
import dev.emi.emi.api.widget.SlotWidget;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import top.offsetmonkey538.rainbowwood.item.ITintedBlockItem;
import top.offsetmonkey538.rainbowwood.recipe.TintedShapedRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmiTintedShapedRecipe extends EmiPatternCraftingRecipe {
    private final ITintedBlockItem outputItem;
    private final DefaultedList<Ingredient> ingredients;

    public EmiTintedShapedRecipe(TintedShapedRecipe recipe, Identifier id) {
        super(pad(recipe), EmiStack.of(recipe.getResult(), recipe.getResultCount()), id);
        //super(recipe.getIngredients().stream().map(EmiIngredient::of).toList(), EmiStack.of(recipe.getResult(), recipe.getResultCount()), id);

        ingredients = recipe.getIngredients();

        if (!(recipe.getResult() instanceof ITintedBlockItem tintedBlockItem)) throw new IllegalStateException("Result '%s' for recipe '%s' not instance of ITintedBlockItem".formatted(recipe.getResult(), id));
        outputItem = tintedBlockItem;
    }

    @Override
    public SlotWidget getInputWidget(int slot, int x, int y) {
        //if (slot >= input.size()) return new SlotWidget(EmiStack.EMPTY, x, y);

        final EmiIngredient ingredient = input.get(slot);
        final List<Item> items = ingredient.getEmiStacks().stream().map(EmiStack::getItemStack).map(ItemStack::getItem).toList();

        boolean isTinted = false;
        for (Item item : items) {

            if (item instanceof ITintedBlockItem && !item.getDefaultStack().isEmpty()) isTinted = true;
            else if (isTinted) throw new IllegalStateException("Some candidate input items of recipe '%s' slot '%s' are tinted, but some are not. Input items for slot: '%s'".formatted(id, slot, String.join(", ", items.stream().map(Item::toString).toList())));
            else isTinted = false;
        }

        if (isTinted) return new GeneratedSlotWidget(random -> {
            // TODO: don't think it's possible to make TagIngredients tinted
            return EmiIngredient.of(items.stream().filter(item -> !item.getDefaultStack().isEmpty()).map(item -> ((ITintedBlockItem) item).getStackWithTint(getColor(random))).map(EmiStack::of).toList());
        }, unique, x, y);


        return new SlotWidget(ingredient, x, y);

        //if (slot == 0) {
        //    return new SlotWidget(EmiStack.of(armor), x, y);
        //} else {
        //    final int s = slot - 1;
        //    return new GeneratedSlotWidget(r -> {
        //        List<DyeItem> dyes = getDyes(r);
        //        if (s < dyes.size()) {
        //            return EmiStack.of(dyes.get(s));
        //        }
        //        return EmiStack.EMPTY;
        //    }, unique, x, y);
        //}
    }

    @Override
    public SlotWidget getOutputWidget(int x, int y) {
        return new GeneratedSlotWidget(random -> EmiStack.of(outputItem.getStackWithTint(getColor(random)), output.getAmount()), unique, x, y);
    }

    private int getColor(Random random) {
        return random.nextInt() & 0xFFFFFF;
        //final NamedColor[] allColors = NamedColor.getAllColors();
        //return allColors[random.nextInt(allColors.length)].color;
    }

    private static List<EmiIngredient> pad(TintedShapedRecipe recipe) {
        final List<EmiIngredient> result = new ArrayList<>(3 * 3);

        //int index = 0;
        //for (int y = 0; y < 3; y++) {
        //    for (int x = 0; x < 3; x++) {
        //        if (index >= recipe.getIngredients().size()) result.add(EmiStack.EMPTY);
        //        else result.add(EmiIngredient.of(recipe.getIngredients().get(index++), recipe.getResultCount()));
        //    }
        //}

        //return result;

        List<EmiIngredient> list = Lists.newArrayList();
        int i = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (x >= recipe.getWidth() || y >= recipe.getHeight() || i >= recipe.getIngredients().size()) {
                    list.add(EmiStack.EMPTY);
                } else {
                    list.add(EmiIngredient.of(recipe.getIngredients().get(i++)));
                }
            }
        }
        return list;
    }
}
