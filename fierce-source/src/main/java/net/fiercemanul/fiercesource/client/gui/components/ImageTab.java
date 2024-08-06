package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ImageTab extends Tab {


    private final ResourceLocation image;

    public ImageTab(int pX, int pY, int pWidth, int pHeight, Component pMessage, ResourceLocation image, FierceScreen screen, Canvas canvas) {
        super(pX, pY, pWidth, pHeight, pMessage, screen, canvas);
        this.image = image;
    }

    @Override
    protected void renderIcon(GuiGraphics pGuiGraphics, float pPartialTick) {

    }
}
