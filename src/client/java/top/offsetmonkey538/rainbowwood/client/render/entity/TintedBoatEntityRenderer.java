package top.offsetmonkey538.rainbowwood.client.render.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.vehicle.BoatEntity;
import top.offsetmonkey538.rainbowwood.entity.ITintedEntity;
import top.offsetmonkey538.rainbowwood.util.TintedRenderContext;

public class TintedBoatEntityRenderer extends BoatEntityRenderer {
    public TintedBoatEntityRenderer(EntityRendererFactory.Context ctx, boolean chest) {
        super(ctx, chest);
    }

    @Override
    public void render(BoatEntity boatEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (!(boatEntity instanceof ITintedEntity tintedEntity)) throw new IllegalStateException("BoatEntity provided to TintedBoatEntityRenderer.render not instanceof ITintedEntity! Got '%s'".formatted(boatEntity.getClass()));
        TintedRenderContext.contextualize(() -> super.render(boatEntity, f, g, matrixStack, vertexConsumerProvider, i), tintedEntity.getTint());
    }
}
