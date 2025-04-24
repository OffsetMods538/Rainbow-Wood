package top.offsetmonkey538.rainbowwood.mixin.recipe;

import net.minecraft.recipe.RawShapedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(RawShapedRecipe.class)
public interface RawShapedRecipeAccessor {

    @Invoker("removePadding")
    static String[] removePadding(List<String> pattern) {
        throw new UnsupportedOperationException();
    }
}
