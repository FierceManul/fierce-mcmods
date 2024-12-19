package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceContainerScreen;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;

/**
 * ui主画布
 */
public abstract class Canvas extends AbstractWidget {


    protected static final int MIN_WIDTH = 154;
    protected static final int MIN_HEIGHT = 143;
    //画布宽度不要超过屏幕的0.4
    protected static final double MAX_WIDTH_PERCENTAGE = 0.4;
    protected FierceContainerScreen screen;
    protected boolean scrollBarEnable = false;

    public Canvas(FierceContainerScreen screen, int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.screen = screen;
    }

    public void makeSize(int screenWidth, int screenHeight) {
        setX(screen.getCenterX() - width / 2);
        setY(screen.getCenterY() - height / 2);
    }

    @Override
    public void playDownSound(SoundManager pHandler) {}

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {}
}
