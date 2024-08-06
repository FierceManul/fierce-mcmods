package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceScreen;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;

/**
 * ui主画布
 */
public abstract class Canvas extends AbstractWidget {


    protected FierceScreen screen;
    protected int xEnd;
    protected int yEnd;

    public Canvas(FierceScreen screen, int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.screen = screen;
        xEnd = pX + width;
        yEnd = pY + height;
    }

    abstract public void makeSize(int screenWidth, int screenHeight);

    @Override
    public void playDownSound(SoundManager pHandler) {}

    public int getXEnd() {
        return xEnd;
    }

    public int getYEnd() {
        return yEnd;
    }
}
