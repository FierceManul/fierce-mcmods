package net.fiercemanul.fiercedecoration.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fiercemanul.fiercedecoration.world.level.block.entity.StarBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class StarBlockRender implements BlockEntityRenderer<StarBlockEntity> {

    public StarBlockRender(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(StarBlockEntity starBlock, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        Matrix4f matrix4f = pPoseStack.last().pose();
        VertexConsumer consumer = pBufferSource.getBuffer(RenderType.endGateway());
        if (starBlock.needUpdate) starBlock.updateAllFace();
        if (starBlock.north) renderFace(matrix4f, consumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        if (starBlock.south) renderFace(matrix4f, consumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F);
        if (starBlock.west) renderFace(matrix4f, consumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F);
        if (starBlock.east) renderFace(matrix4f, consumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F);
        if (starBlock.up) renderFace(matrix4f, consumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F);
        if (starBlock.down) renderFace(matrix4f, consumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F);
    }

    private void renderFace(Matrix4f matrix4f, VertexConsumer consumer, float v1, float v2, float v3, float v4, float v5, float v6, float v7, float v8) {
        consumer.vertex(matrix4f, v1, v3, v5).endVertex();
        consumer.vertex(matrix4f, v2, v3, v6).endVertex();
        consumer.vertex(matrix4f, v2, v4, v7).endVertex();
        consumer.vertex(matrix4f, v1, v4, v8).endVertex();
    }

    public int getViewDistance() {
        return 128;
    }
}
