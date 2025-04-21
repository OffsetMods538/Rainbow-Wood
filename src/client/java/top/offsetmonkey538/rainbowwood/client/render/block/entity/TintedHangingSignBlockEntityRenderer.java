package top.offsetmonkey538.rainbowwood.client.render.block.entity;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import top.offsetmonkey538.rainbowwood.block.entity.TintedHangingSignBlockEntity;

public class TintedHangingSignBlockEntityRenderer extends HangingSignBlockEntityRenderer {
    private int tint = -1;

    public TintedHangingSignBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(SignBlockEntity signBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        if (signBlockEntity instanceof TintedHangingSignBlockEntity tintedHangingSignBlockEntity) tint = tintedHangingSignBlockEntity.getTint();
        super.render(signBlockEntity, f, matrixStack, vertexConsumerProvider, i, j);
    }

    public int getTint() {
        return tint;
    }
}
