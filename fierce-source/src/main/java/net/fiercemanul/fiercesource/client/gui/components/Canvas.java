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

    public Canvas(FierceScreen screen, int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.screen = screen;
    }

    public void makeSize(int screenWidth, int screenHeight) {
        setX(screen.getCenterX() - width / 2);
        setY(screen.getCenterY() - height / 2);
    }

    @Override
    public void playDownSound(SoundManager pHandler) {}
}
