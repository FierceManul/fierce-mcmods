package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceMediaScreen;
import net.fiercemanul.fiercesource.client.gui.style.UIStyles;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TestContainerCanvas extends Canvas {


    int playerInvX, playerInvY;


    public TestContainerCanvas(FierceMediaScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, 188, 252, pMessage);
    }

    @Override
    public void init() {
        super.init();
        playerInvX = width / 2 - 77;
        playerInvY = height - UIStyles.style.invSlotsHeight;
        UIStyles.style.buildInvSlots(slotsPos, playerInvX, playerInvY);
    }

    @Override
    protected void renderCanvas(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.drawString(screen.getFont(), "TestContainerCanvas", 1, 2, 4210752, false);
        UIStyles.style.renderContainer(guiGraphics, 0, 11, 188, 120);
        UIStyles.style.renderCrafting(guiGraphics, 37, height - 121);
        UIStyles.style.renderInventory(guiGraphics, playerInvX, playerInvY);
    }

}
