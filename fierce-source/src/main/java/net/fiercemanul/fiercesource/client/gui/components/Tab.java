package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceScreen;
import net.fiercemanul.fiercesource.client.gui.style.Styles;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public abstract class Tab extends AbstractWidget {


    private final FierceScreen screen;
    private Canvas canvas;

    public Tab(int pX, int pY, int pWidth, int pHeight, Component pMessage, FierceScreen screen, Canvas canvas) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.screen = screen;
        this.canvas = canvas;
        this.setTooltip(Tooltip.create(pMessage));
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if (isFocused()) {
            pGuiGraphics.pose().pushPose();
            pGuiGraphics.pose().translate(0, 0, 10);
            pGuiGraphics.blitSprite(
                    Styles.chooseingUIStyle.tabOn(),
                    getX(),
                    getY(),
                    Styles.chooseingUIStyle.tabOnSize().x(),
                    Styles.chooseingUIStyle.tabOnSize().y()
            );
            renderIcon(pGuiGraphics, pPartialTick);
            pGuiGraphics.pose().popPose();
        } else {
            if (isHovered) {
                pGuiGraphics.pose().pushPose();
                pGuiGraphics.pose().translate(0, 0, 20);
            }
            pGuiGraphics.blitSprite(
                    Styles.chooseingUIStyle.tabOff(),
                    getX(),
                    getY(),
                    Styles.chooseingUIStyle.tabOffSize().x(),
                    Styles.chooseingUIStyle.tabOffSize().y()
            );
            renderIcon(pGuiGraphics, pPartialTick);
            if (isHovered) pGuiGraphics.pose().popPose();
        }
    }

    abstract protected void renderIcon(GuiGraphics pGuiGraphics, float pPartialTick);

    @Override
    public void onClick(double mouseX, double mouseY, int button) {
        screen.setCanvas(canvas);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {}

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
