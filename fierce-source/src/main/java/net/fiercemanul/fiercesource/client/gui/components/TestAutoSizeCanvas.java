package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceMediaScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.Items;

public class TestAutoSizeCanvas extends Canvas {


    public TestAutoSizeCanvas(FierceMediaScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, MIN_WIDTH, MIN_HEIGHT, pMessage);
    }

    @Override
    protected void renderCanvas(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.fill(
                0, 0, width, height,
                FastColor.ARGB32.color(230, 230, 255)
        );
        guiGraphics.drawString(screen.getFont(), "AutoSize", 8, 6, 4210752, false);
        guiGraphics.renderFakeItem(Items.SLIME_BALL.getDefaultInstance(), 16, 16);
    }

    @Override
    public void init() {
        width = Math.max((int) (screen.width * MAX_WIDTH_PERCENTAGE), MIN_WIDTH);
        height = Math.max(screen.height - 16, MIN_HEIGHT);
        super.init();
    }
}
