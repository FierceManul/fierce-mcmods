package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.client.gui.screens.FierceContainerScreen;
import net.fiercemanul.fiercesource.client.gui.style.UIStyle;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class TestMinCanvas extends Canvas{


    private final ResourceLocation inventory_slots = ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/inventory_slots");
    private final ResourceLocation container_slots = ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/container_slots");

    public TestMinCanvas(FierceContainerScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, 154, 143, pMessage);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(getX(), getY(), 0);

        guiGraphics.drawString(screen.getFont(), "I,O十O,I", 1, 2, 4210752, false);
        guiGraphics.blitSprite(container_slots, 0, 11, UIStyle.INVENTORY_SLOTS_WIDTH, 52);
        guiGraphics.drawString(screen.getFont(), "I,O十O,I", 1, 65, 4210752, false);
        guiGraphics.blitSprite(inventory_slots, 0, 74, UIStyle.INVENTORY_SLOTS_WIDTH, UIStyle.INVENTORY_SLOTS_HEIGHT);

        guiGraphics.pose().popPose();
    }
}
