package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceMediaScreen;
import net.fiercemanul.fiercesource.client.level.menu.ClientFierceMediaMenu;
import net.fiercemanul.fiercesource.util.TwoInt;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

/**
 * ui主画布
 */
public abstract class Canvas extends AbstractWidget {


    protected static final int MIN_WIDTH = 154;
    protected static final int MIN_HEIGHT = 143;
    //画布宽度不要超过屏幕的0.4
    protected static final double MAX_WIDTH_PERCENTAGE = 0.4;
    protected final TwoInt[] slotsPos;
    protected FierceMediaScreen screen;
    protected boolean scrollBarEnable = false;

    public Canvas(FierceMediaScreen screen, int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.screen = screen;
        slotsPos = new TwoInt[screen.getMenu().playerInv.getContainerSize()];
        Arrays.fill(slotsPos, ClientFierceMediaMenu.DEFAULT_SLOT_POS);
    }

    public void init() {
        setX(screen.centerX - width / 2);
        setY(screen.centerY - height / 2);
    }

    public TwoInt[] getSlotsPos() {
        return slotsPos;
    }

    /**
     * 相对位置
     */
    public int findSlot(double mouseX, double mouseY) {
        return -999;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.enableScissor(getX(), getY(), getRight(), getBottom());
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(getX(), getY(), 0);
        renderCanvas(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.pose().popPose();
        guiGraphics.disableScissor();
    }

    protected abstract void renderCanvas(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);

    @Override
    public void playDownSound(SoundManager pHandler) {}

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {}

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

}
