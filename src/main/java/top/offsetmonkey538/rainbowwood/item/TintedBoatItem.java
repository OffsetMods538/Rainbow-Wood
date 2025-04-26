package top.offsetmonkey538.rainbowwood.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import top.offsetmonkey538.rainbowwood.component.ModComponents;
import top.offsetmonkey538.rainbowwood.entity.ITintedEntity;
import top.offsetmonkey538.rainbowwood.entity.TintedBoatEntity;
import top.offsetmonkey538.rainbowwood.entity.TintedChestBoatEntity;

import java.util.List;

public class TintedBoatItem extends BoatItem implements ITintedItem {
    private final boolean isChest;

    public TintedBoatItem(boolean chest, BoatEntity.Type type, Settings settings) {
        super(chest, type, settings);
        this.isChest = chest;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        appendTintedTooltip(stack, tooltip);
    }

    @Override
    protected BoatEntity createEntity(World world, HitResult hitResult, ItemStack stack, PlayerEntity player) {
        final Vec3d pos = hitResult.getPos();
        final BoatEntity boatEntity = isChest ? new TintedChestBoatEntity(world, pos.x, pos.y, pos.z) : new TintedBoatEntity(world, pos.x, pos.y, pos.z);


        if (world instanceof ServerWorld serverWorld) {
            EntityType.copier(serverWorld, stack, player).accept(boatEntity);
            // TODO: maybe to client as well? if not then at least invert this as a guard vlause

            final Integer tint = stack.get(ModComponents.TINT_COLOR);
            if (tint != null) ((ITintedEntity) boatEntity).setTint(tint);
        }

        return boatEntity;
    }
}
