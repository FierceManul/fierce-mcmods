package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.client.gui.screens.FierceContainerScreen;
import net.fiercemanul.fiercesource.client.gui.style.UIStyle;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class TestBigInvCanvas extends Canvas{


    private final ResourceLocation inventory_slots = ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/inventory_slots");
    private final ResourceLocation crafting_slots = ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/crafting_slots");
    private final ResourceLocation container_slots = ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/container_slots");

    public TestBigInvCanvas(FierceContainerScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, 188, 258, pMessage);
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(getX(), getY(), 0);

        pGuiGraphics.blitSprite(inventory_slots, width / 2 - 77, height - 69, UIStyle.INVENTORY_SLOTS_WIDTH, UIStyle.INVENTORY_SLOTS_HEIGHT);

        pGuiGraphics.blitSprite(crafting_slots, 0, height - 121, 188, 52);

        pGuiGraphics.blitSprite(container_slots, 0, 0, 188, 137);

        pGuiGraphics.pose().popPose();
    }
}
