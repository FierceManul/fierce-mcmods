package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceMediaScreen;
import net.fiercemanul.fiercesource.client.gui.style.UIStyles;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class TestMinCanvas extends Canvas {


    public static final MutableComponent TITLE = Component.literal("I,O十O,I");

    public TestMinCanvas(FierceMediaScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, pMessage);
    }

    @Override
    public void init() {
        super.init();
        UIStyles.style.buildInvSlots(slotsPos, 0, 74);
    }

    @Override
    protected void renderCanvas(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        UIStyles.style.render3RowContainerCanvas(guiGraphics, screen.getFont(), TITLE);
    }

    @Override
    public int findSlot(double mouseX, double mouseY) {
        if (FSUtils.inArea(
                0,
                UIStyles.style.invSlotsWidth,
                74,
                UIStyles.style.threeRowContainerCanvasHeight,
                mouseX,
                mouseY
        )) return UIStyles.style.findInvSlot(mouseX, mouseY - 74);
        return super.findSlot(mouseX, mouseY);
    }
}
