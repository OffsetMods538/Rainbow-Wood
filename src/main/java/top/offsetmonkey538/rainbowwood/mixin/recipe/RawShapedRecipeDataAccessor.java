package top.offsetmonkey538.rainbowwood.mixin.recipe;

import com.mojang.serialization.Codec;
import net.minecraft.recipe.RawShapedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RawShapedRecipe.Data.class)
public interface RawShapedRecipeDataAccessor {

    @Accessor("KEY_ENTRY_CODEC")
    static Codec<Character> getKEY_ENTRY_CODEC() {
        throw new UnsupportedOperationException();
    }
}
