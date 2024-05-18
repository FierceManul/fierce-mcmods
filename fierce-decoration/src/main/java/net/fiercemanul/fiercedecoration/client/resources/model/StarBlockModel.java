package net.fiercemanul.fiercedecoration.client.resources.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.BakedModelWrapper;

@OnlyIn(Dist.CLIENT)
public class StarBlockModel extends BakedModelWrapper<BakedModel> {

    public StarBlockModel(BakedModel originalModel) {
        super(originalModel);
    }

    @Override
    public BakedModel applyTransform(ItemDisplayContext cameraTransformType, PoseStack poseStack, boolean applyLeftHandTransform) {
        super.applyTransform(cameraTransformType, poseStack, applyLeftHandTransform);
        return this;
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

}
