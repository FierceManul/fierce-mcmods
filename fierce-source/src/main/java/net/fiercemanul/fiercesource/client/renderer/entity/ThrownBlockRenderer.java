package net.fiercemanul.fiercesource.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
import com.mojang.math.Axis;
import net.fiercemanul.fiercesource.world.entity.ThrownBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.joml.Quaternionf;

import static net.minecraft.client.resources.model.ModelBakery.DESTROY_TYPES;

public class ThrownBlockRenderer extends EntityRenderer<ThrownBlock> {


    //private static final RandomSource RANDOM_SOURCE = RandomSource.create();
    //private static final long SEED = RANDOM_SOURCE.nextLong();
    private final BlockRenderDispatcher dispatcher;

    public ThrownBlockRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.dispatcher = context.getBlockRenderDispatcher();

    }

    @Override
    public void render(ThrownBlock thrownBlock, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        BlockState state = thrownBlock.getBlockState();
        Level level = thrownBlock.level();
        /*if (state != level.getBlockState(thrownBlock.blockPosition()) && state.getRenderShape() != RenderShape.INVISIBLE) {
            var model = dispatcher.getBlockModel(state);
            for (var renderType : model.getRenderTypes(state, RANDOM_SOURCE, ModelData.EMPTY))
                dispatcher.getModelRenderer().tesselateWithAO(
                        level, model, state,
                        BlockPos.ZERO,
                        poseStack,
                        bufferSource.getBuffer(RenderTypeHelper.getMovingBlockRenderType(renderType)),
                        false,
                        RANDOM_SOURCE, SEED,
                        OverlayTexture.NO_OVERLAY,
                        ModelData.EMPTY,
                        renderType
                );
        }*/
        poseStack.pushPose();
        poseStack.translate(0, 0.5, 0);
        switch (thrownBlock.getFacingAnimation()) {
            case NONE -> {
                //poseStack.mulPose(new Quaternionf().rotateZ((float) Mth.lerp(partialTick, -thrownBlock.zArcO, -thrownBlock.zArc)));
                //poseStack.mulPose(new Quaternionf().rotateX((float) Mth.lerp(partialTick, thrownBlock.xArcO, thrownBlock.xArc)));
                //poseStack.mulPose(new Quaternionf(thrownBlock.rollRotationPrev).slerp(thrownBlock.rollRotation, partialTick));
                Vec3 vel = thrownBlock.getDeltaMovement();
                double speed = vel.horizontalDistance();
                if (thrownBlock.rollAnglePrev + thrownBlock.rollAngle > 0) {
                    float angle = (float) Mth.lerp(partialTick, thrownBlock.rollAnglePrev, thrownBlock.rollAngle);
                    float axisX = (float)(vel.z / speed);
                    float axisZ = (float)(-vel.x / speed);
                    poseStack.mulPose(new Quaternionf().rotateAxis(angle, axisX, 0, axisZ));
                }
            }
            case FRONT -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, thrownBlock.yRotO, thrownBlock.getYRot()) - 180.0F));
                poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTick, thrownBlock.xRotO, thrownBlock.getXRot())));
            }
            case UP -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, thrownBlock.yRotO, thrownBlock.getYRot()) - 180.0F));
                poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTick, thrownBlock.xRotO, thrownBlock.getXRot()) - 90.0F));
            }
            case DOWN -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, thrownBlock.yRotO, thrownBlock.getYRot()) - 180.0F));
                poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTick, thrownBlock.xRotO, thrownBlock.getXRot()) + 90.0F));
            }
        }
        poseStack.translate(-0.5, -0.5, -0.5);
        dispatcher.renderSingleBlock(
                state,
                poseStack, bufferSource,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                ModelData.EMPTY,
                null
        );
        float destroyProgress = thrownBlock.getDestroyProgress();
        int i = Math.round(destroyProgress * DESTROY_TYPES.size());
        if (i > 0) dispatcher.renderBreakingTexture(
                state, BlockPos.ZERO, level,
                poseStack,
                new SheetedDecalTextureGenerator(
                        bufferSource.getBuffer(DESTROY_TYPES.get(i - 1)),
                        poseStack.last(), 1.0F
                ),
                ModelData.EMPTY
        );
        poseStack.popPose();
        super.render(thrownBlock, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownBlock entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

}
