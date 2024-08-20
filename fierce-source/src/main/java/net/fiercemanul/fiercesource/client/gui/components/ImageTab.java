package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceScreen;
import net.fiercemanul.fiercesource.client.gui.style.Int2;
import net.fiercemanul.fiercesource.client.gui.style.Styles;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ImageTab extends Tab<ResourceLocation> {


    private ResourceLocation imageSprite;


    public ImageTab(int pX, int pY, Component pMessage, FierceScreen screen, Canvas canvas, ResourceLocation imageSprite) {
        super(pX, pY, pMessage, screen, canvas);
        this.imageSprite = imageSprite;
    }

    @Override
    protected void renderIcon(GuiGraphics pGuiGraphics, float pPartialTick) {
        Int2 tabIconPadding = Styles.chooseingUIStyle.tabPadding();
        pGuiGraphics.blitSprite(
                imageSprite,
                getX() + tabIconPadding.i1(),
                getY() + tabIconPadding.i2(),
                16,
                16
        );
    }

    @Override
    public void setIcon(ResourceLocation imageSprite) {
        this.imageSprite = imageSprite;
    }
}
