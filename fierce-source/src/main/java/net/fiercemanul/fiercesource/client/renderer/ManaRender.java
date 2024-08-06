package net.fiercemanul.fiercesource.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ManaRender {


    private final static RenderType LINE_RENDER_TYPE = RenderType.lines();

    public static void renderLine(
            PoseStack pPoseStack,
            MultiBufferSource pBufferSource,
            float x1,
            float y1,
            float z1,
            float x2,
            float y2,
            float z2,
            float r,
            float g,
            float b,
            float a
    ) {
        PoseStack.Pose pose = pPoseStack.last();
        VertexConsumer consumer = pBufferSource.getBuffer(LINE_RENDER_TYPE);
        float x3 = x2 - x1;
        float y3 = y2 - y1;
        float z3 = z2 - z1;
        consumer.addVertex(pose, x1, y1, z1).setColor(r, g, b, a).setNormal(pose, x3, y3, z3);
        consumer.addVertex(pose, x2, y2, z2).setColor(r, g, b, a).setNormal(pose, x3, y3, z3);
    }
}
