package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceContainerScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.Items;

public class TestAutoSizeCanvas extends Canvas{


    public TestAutoSizeCanvas(FierceContainerScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, MIN_WIDTH, MIN_HEIGHT, pMessage);
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.enableScissor(getX(), getY(), getRight(), getBottom());
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(getX(), getY(), 0);

        pGuiGraphics.fill(
                0, 0, width, height,
                FastColor.ARGB32.color(230, 230, 255)
        );
        pGuiGraphics.drawString(screen.getFont(), "AutoSize", 8, 6, 4210752, false);
        pGuiGraphics.renderFakeItem(Items.SLIME_BALL.getDefaultInstance(), 16, 16);

        pGuiGraphics.pose().popPose();
        pGuiGraphics.disableScissor();
    }

    @Override
    public void makeSize(int screenWidth, int screenHeight) {
        width = Math.max((int) (screenWidth * 0.4), MIN_WIDTH);
        height = Math.max(screenHeight - 16, MIN_HEIGHT);
        super.makeSize(screenWidth, screenHeight);
    }
}
