package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.style.UIStyles;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;

public class Tab extends StyleButton {


    private Canvas canvas;
    private int iconX;
    private int iconY;
    private Icon icon;


    public Tab(Canvas canvas, Icon icon, Component pMessage) {
        super(pMessage);
        this.canvas = canvas;
        this.icon = icon;
        this.setTooltip(Tooltip.create(pMessage));
    }

    public void init(int x, int y) {
        super.init(UIStyles.style.tabData().tabImgButtonData(), x, y);
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        iconX = getX() + UIStyles.style.tabData().iconOffset().x();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        iconY = getY() + UIStyles.style.tabData().iconOffset().y();
    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        icon.renderIcon(pGuiGraphics, pPartialTick, iconX, iconY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return super.clicked(mouseX, mouseY);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
