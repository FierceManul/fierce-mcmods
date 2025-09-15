package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceMediaScreen;
import net.fiercemanul.fiercesource.client.gui.style.UIStyles;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TestBigContainerCanvas extends Canvas {


    int playerInvX, playerInvY, containerHeight;


    public TestBigContainerCanvas(FierceMediaScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, pMessage);
    }

    @Override
    public void init() {

        double spaceX = screen.width * UIStyles.style.maxCanvasWidthPercentage;
        double spaceY = screen.height * UIStyles.style.maxCanvasHeightPercentage;
        spaceY -= UIStyles.style.invSlotsHeight + UIStyles.style.craftSlotsHeight + 11;
        int row = (int) ((spaceY - 1) / 17);
        int column = (int) ((spaceX - 1) / 17);
        width = column * 17 + 1;
        containerHeight = row * 17 + 1;
        height = containerHeight + UIStyles.style.invSlotsHeight + UIStyles.style.craftSlotsHeight + 11;

        playerInvX = width / 2 - 77;
        playerInvY = height - UIStyles.style.invSlotsHeight;
        UIStyles.style.buildInvSlots(slotsPos, playerInvX, playerInvY);

        super.init();
    }

    @Override
    protected void renderCanvas(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.drawString(screen.getFont(), "TestBigContainerCanvas", 1, 2, 4210752, false);
        UIStyles.style.renderContainer(guiGraphics, 0, 11, width, containerHeight);
        UIStyles.style.renderCrafting(guiGraphics, 37, height - 121);
        UIStyles.style.renderInventory(guiGraphics, playerInvX, playerInvY);
    }

}
