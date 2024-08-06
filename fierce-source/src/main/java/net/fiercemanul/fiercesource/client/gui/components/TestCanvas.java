package net.fiercemanul.fiercesource.client.gui.components;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.math.Axis;
import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.client.gui.screens.FierceScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TestCanvas extends Canvas{


    public TestCanvas(FierceScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, 256, 256, pMessage);
    }

    @Override
    public void makeSize(int screenWidth, int screenHeight) {
        setX(screen.getCenterX() - 128);
        setY(screen.getCenterY() - 128);
        xEnd = screen.getCenterX() + 128;
        yEnd = screen.getCenterY() + 128;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(getX(), getY(), 0);
        //pGuiGraphics.enableScissor(0, 0, width, height);
        pGuiGraphics.fill(
                0, 0, width, height,
                FastColor.ARGB32.color(230, 230, 255)
        );
        pGuiGraphics.drawString(screen.getFont(), "Random: " + screen.getMenu().random, 8, 6, 4210752, false);
        pGuiGraphics.renderFakeItem(new ItemStack(Items.NETHERITE_SWORD), 25, 32);
        pGuiGraphics.pose().pushPose();


        pGuiGraphics.pose().translate(128, 128, 150);
        pGuiGraphics.pose().scale(64.0F, -64.0F, 64.0F);
        pGuiGraphics.pose().mulPose(Axis.XP.rotationDegrees(30));
        pGuiGraphics.pose().mulPose(Axis.YP.rotationDegrees(45));
        ItemStack stack = Items.NETHERITE_SWORD.getDefaultInstance();
        BakedModel bakedmodel = Minecraft.getInstance().getItemRenderer().getModel(stack, Minecraft.getInstance().level, null, 0);
        Lighting.setupForFlatItems();
        Minecraft.getInstance().getItemRenderer().render(
                stack,
                ItemDisplayContext.NONE,
                false,
                pGuiGraphics.pose(),
                pGuiGraphics.bufferSource(),
                15728880,
                OverlayTexture.NO_OVERLAY,
                bakedmodel
        );
        pGuiGraphics.flush();
        Lighting.setupFor3DItems();
        pGuiGraphics.pose().popPose();

        //pGuiGraphics.disableScissor();

        pGuiGraphics.pose().popPose();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {}

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }
}
