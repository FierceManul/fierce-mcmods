package net.fiercemanul.fiercedecoration.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.world.level.block.entity.StarBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class StarBlockRender implements BlockEntityRenderer<StarBlockEntity> {


    private final static RenderType LINE_IN = RenderType.itemEntityTranslucentCull(ResourceLocation.fromNamespaceAndPath(FierceDecoration.MODID, "textures/entity/mana_transmit_in.png"));
    private final static RenderType LINE_OUT = RenderType.itemEntityTranslucentCull(ResourceLocation.fromNamespaceAndPath(FierceDecoration.MODID, "textures/entity/mana_transmit_out.png"));

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


        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1.0, 0.5);
        Vec3 off = new Vec3(3, 0.3, 2);
        consumer = pBufferSource.getBuffer(LINE_IN);
        PoseStack.Pose pose = pPoseStack.last();
        renderManaLine(
                0.03125,
                off,
                pose,
                consumer,
                0.8F
        );
        consumer = pBufferSource.getBuffer(LINE_OUT);
        renderManaLine(
                0.0625,
                off,
                pose,
                consumer,
                0.4F
        );
        pPoseStack.popPose();

    }

    private void renderFace(Matrix4f matrix4f, VertexConsumer consumer, float v1, float v2, float v3, float v4, float v5, float v6, float v7, float v8) {
        consumer.addVertex(matrix4f, v1, v3, v5);
        consumer.addVertex(matrix4f, v2, v3, v6);
        consumer.addVertex(matrix4f, v2, v4, v7);
        consumer.addVertex(matrix4f, v1, v4, v8);
    }

    private void face(PoseStack.Pose pose, VertexConsumer consumer, Vector3f px, Vector3f pX, Vector3f pY, Vector3f py, float cR, float cG, float cB, float cA, float uv) {
        consumer.addVertex(pose, px.x, px.y, px.z).setColor(cR, cG, cB, cA).setUv(0,0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(pose, 0.0F, 1.0F, 0.0F);
        consumer.addVertex(pose, pX.x, pX.y, pX.z).setColor(cR, cG, cB, cA).setUv(1,0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(pose, 0.0F, 1.0F, 0.0F);
        consumer.addVertex(pose, pY.x, pY.y, pY.z).setColor(cR, cG, cB, cA).setUv(1,uv).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(pose, 0.0F, 1.0F, 0.0F);
        consumer.addVertex(pose, py.x, py.y, py.z).setColor(cR, cG, cB, cA).setUv(0,uv).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }

    private void renderManaLine(double halfWidth, Vec3 vector, PoseStack.Pose pose, VertexConsumer consumer, float alpha) {

        double sqrtYZ = Math.sqrt(vector.y * vector.y + vector.z * vector.z);
        double sinX = vector.y / sqrtYZ;
        double cosX = vector.z / sqrtYZ;

        double sqrtXZ = Math.sqrt(vector.x * vector.x + vector.z * vector.z);
        double sinY = vector.z / sqrtXZ;
        double cosY = vector.x / sqrtXZ;

        Vector3f pxy = rotPos(-halfWidth, -halfWidth, 0, sinX, cosX, sinY, cosY).toVector3f();
        Vector3f pXy = rotPos(halfWidth, -halfWidth, 0, sinX, cosX, sinY, cosY).toVector3f();
        Vector3f pxY = rotPos(-halfWidth, halfWidth, 0, sinX, cosX, sinY, cosY).toVector3f();
        Vector3f pXY = rotPos(halfWidth,  halfWidth, 0, sinX, cosX, sinY, cosY).toVector3f();

        Vector3f offset = vector.toVector3f();
        Vector3f pxy2 = new Vector3f(pxy).add(offset);
        Vector3f pXy2 = new Vector3f(pXy).add(offset);
        Vector3f pxY2 = new Vector3f(pxY).add(offset);
        Vector3f pXY2 = new Vector3f(pXY).add(offset);
        float length = offset.length() * 3.0F;

        face(pose, consumer, pxy, pXy, pXy2, pxy2, 1.0F, 1.0F, 1.0F, alpha, length);
        face(pose, consumer, pxY, pxy, pxy2, pxY2, 1.0F, 1.0F, 1.0F, alpha, length);
        face(pose, consumer, pXY, pxY, pxY2, pXY2, 1.0F, 1.0F, 1.0F, alpha, length);
        face(pose, consumer, pXy, pXY, pXY2, pXy2, 1.0F, 1.0F, 1.0F, alpha, length);

    }

    private Vec3 rotPos(double x, double y, double z, double sinX, double cosX, double sinY, double cosY) {
        double z1 = z * cosX - y * sinX;
        return new Vec3(
                x * sinY + z1 * cosY,
                y * cosX + z * sinX,
                z1 * sinY - x * cosY
        );
    }

    public int getViewDistance() {
        return 128;
    }

    @Override
    public boolean shouldRenderOffScreen(StarBlockEntity pBlockEntity) {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(StarBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2);
    }
}
