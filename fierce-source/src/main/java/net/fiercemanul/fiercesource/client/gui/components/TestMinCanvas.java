package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceMediaScreen;
import net.fiercemanul.fiercesource.client.gui.style.UIStyles;
import net.fiercemanul.fiercesource.client.level.menu.ClientFierceMediaMenu;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.util.TwoInt;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Arrays;

public class TestMinCanvas extends Canvas {


    public static final MutableComponent TITLE = Component.literal("I,O十O,I");
    protected TwoInt[] ctnSlotsPos = new TwoInt[27];

    public TestMinCanvas(FierceMediaScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, pMessage);
        Arrays.fill(ctnSlotsPos, ClientFierceMediaMenu.DEFAULT_SLOT_POS);
    }

    @Override
    public void init() {
        super.init();
        UIStyles.style.buildInvSlots(slotsPos, 0, 74);
        for (int i = 0; i < 3; i++) for (int j = 0; j < 9; j++) ctnSlotsPos[i * 9 + j] = new TwoInt(1 + j * 17, 12 + i * 17);
    }

    @Override
    public TwoInt[] getSlotsPos() {
        TwoInt[] pos = Arrays.copyOf(slotsPos, slotsPos.length + ctnSlotsPos.length);
        System.arraycopy(ctnSlotsPos, 0, pos, slotsPos.length, ctnSlotsPos.length);
        return pos;
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
        else if (FSUtils.inArea(0, UIStyles.style.invSlotsWidth, 12, 64, mouseX, mouseY)) {
            int x = UIStyles.style.slotPos(mouseX);
            int y = UIStyles.style.slotPos(mouseY - 12);
            return Math.min(x, 8) + Math.min(y, 2) * 9 + screen.getMenu().invSize;
        }
        return super.findSlot(mouseX, mouseY);
    }
}
