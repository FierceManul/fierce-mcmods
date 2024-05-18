package net.fiercemanul.fiercedecoration.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class StarBlockItemRender extends BlockEntityWithoutLevelRenderer {


    @SuppressWarnings("all")
    private static volatile StarBlockItemRender INSTANCE;

    @SuppressWarnings("all")
    public static StarBlockItemRender getInstance() {
        if (INSTANCE == null) {
            synchronized (StarBlockItemRender.class) {
                if (INSTANCE == null) INSTANCE = new StarBlockItemRender();
            }
        }
        return INSTANCE;
    }


    private StarBlockItemRender() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        INSTANCE = this;
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        Matrix4f matrix4f = pPoseStack.last().pose();
        VertexConsumer consumer = pBuffer.getBuffer(RenderType.endGateway());
        renderFace(matrix4f, consumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F);
        renderFace(matrix4f, consumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        renderFace(matrix4f, consumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F);
        renderFace(matrix4f, consumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F);
        renderFace(matrix4f, consumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F);
        renderFace(matrix4f, consumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F);
    }

    private void renderFace(Matrix4f matrix4f, VertexConsumer consumer, float v1, float v2, float v3, float v4, float v5, float v6, float v7, float v8) {
        consumer.vertex(matrix4f, v1, v3, v5).endVertex();
        consumer.vertex(matrix4f, v2, v3, v6).endVertex();
        consumer.vertex(matrix4f, v2, v4, v7).endVertex();
        consumer.vertex(matrix4f, v1, v4, v8).endVertex();
    }
}
