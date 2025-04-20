package top.offsetmonkey538.rainbowwood.client.render.block.entity;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import top.offsetmonkey538.rainbowwood.block.entity.TintedSignBlockEntity;

public class TintedSignBlockEntityRenderer extends SignBlockEntityRenderer {
    private int tint = -1;

    public TintedSignBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(SignBlockEntity signBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        if (signBlockEntity instanceof TintedSignBlockEntity tintedSignBlockEntity) tint = tintedSignBlockEntity.getTint();
        super.render(signBlockEntity, f, matrixStack, vertexConsumerProvider, i, j);
    }

    public int getTint() {
        return tint;
    }
}
