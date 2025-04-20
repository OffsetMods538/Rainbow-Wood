package top.offsetmonkey538.rainbowwood.mixin;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.offsetmonkey538.rainbowwood.block.ModBlocks;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.item.ITintedBlockItem;

import java.util.Optional;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(
            method = "onBlockCollision",
            at = @At("HEAD")
    )
    private void rainbow_wood$clearWoodColorWhenCollidingWithCauldronWithWater(BlockState state, CallbackInfo ci) {
        if (!state.isOf(Blocks.WATER_CAULDRON)) return;
        final Entity thisEntity = (Entity) (Object) this; // Yes VVVV I am casting Item to Object and then checking if it's an Item. The return value is nullable so this is a null-check with the benefit of getting the instance haha
        if (!(thisEntity instanceof ItemEntity itemEntity) || !((Object) rainbow_wood$getMatchingTintableBlockItemFor(itemEntity.getStack()) instanceof Item newItem)) return;

        final ItemStack newStack = itemEntity.getStack().copyComponentsToNewStack(newItem, itemEntity.getStack().getCount());
        if (newStack.get(ModComponents.TINT_COLOR) == null && itemEntity.getStack().isOf(newItem)) return;
        newStack.remove(ModComponents.TINT_COLOR);
        itemEntity.setStack(newStack);

        thisEntity.getWorld().playSound(thisEntity, thisEntity.getBlockPos(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.AMBIENT, 1, 1);
    }

    @Unique
    private @Nullable Item rainbow_wood$getMatchingTintableBlockItemFor(final ItemStack item) {
        if (item.getItem() instanceof ITintedBlockItem) return item.getItem();
        if (item.isIn(ItemTags.LOGS)) return rainbow_wood$getMatchingTintableBlockItemForLog(item);
        if (item.isIn(ItemTags.PLANKS)) return ModBlocks.RAINBOW_PLANKS.asItem();
        if (item.isIn(ItemTags.WOODEN_SLABS)) return ModBlocks.RAINBOW_SLAB.asItem();
        if (item.isIn(ItemTags.SIGNS)) return ModBlocks.RAINBOW_SIGN.asItem();
        //TODO: if (item.isIn(ItemTags.HANGING_SIGNS)) return ModBlocks.RAINBOW_HANGING_SIGN.asItem();
        if (item.isIn(ItemTags.WOODEN_BUTTONS)) return ModBlocks.RAINBOW_BUTTON.asItem();
        if (item.isIn(ItemTags.WOODEN_DOORS)) return ModBlocks.RAINBOW_DOOR.asItem();
        if (item.isIn(ItemTags.WOODEN_STAIRS)) return ModBlocks.RAINBOW_STAIRS.asItem();
        if (item.isIn(ItemTags.WOODEN_FENCES)) return ModBlocks.RAINBOW_FENCE.asItem();
        if (item.isIn(ItemTags.FENCE_GATES)) return ModBlocks.RAINBOW_FENCE_GATE.asItem();
        if (item.isIn(ItemTags.WOODEN_PRESSURE_PLATES)) return ModBlocks.RAINBOW_PRESSURE_PLATE.asItem();
        if (item.isIn(ItemTags.WOODEN_TRAPDOORS)) return ModBlocks.RAINBOW_TRAPDOOR.asItem();
        return null;
    }

    @Unique
    private @NotNull Item rainbow_wood$getMatchingTintableBlockItemForLog(final ItemStack item) {
        if (item.isIn(ConventionalItemTags.STRIPPED_LOGS)) return ModBlocks.STRIPPED_RAINBOW_LOG.asItem();
        if (item.isIn(ConventionalItemTags.STRIPPED_WOODS)) return ModBlocks.STRIPPED_RAINBOW_WOOD.asItem();

        if (!(item.getItem() instanceof BlockItem blockItem)) return ModBlocks.RAINBOW_LOG.asItem();

        final Optional<Block> optionalStrippedVariant = Optional.ofNullable(AxeItem.STRIPPED_BLOCKS.get(blockItem.getBlock()));
        if (optionalStrippedVariant.isEmpty()) return ModBlocks.RAINBOW_LOG.asItem();

        if (optionalStrippedVariant.get().asItem().getDefaultStack().isIn(ConventionalItemTags.STRIPPED_WOODS)) return ModBlocks.RAINBOW_WOOD.asItem();

        return ModBlocks.RAINBOW_LOG.asItem();
    }
}
