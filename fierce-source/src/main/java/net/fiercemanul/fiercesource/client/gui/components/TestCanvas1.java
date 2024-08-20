package net.fiercemanul.fiercesource.client.gui.components;

import com.mojang.blaze3d.platform.Lighting;
import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.client.gui.screens.FierceScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TestCanvas1 extends Canvas{


    private final ResourceLocation inventory_slots = ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/inventory_slots");
    private final ResourceLocation crafting_slots = ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/crafting_slots");
    private final ResourceLocation container_slots = ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/container_slots");

    public TestCanvas1(FierceScreen screen, int pX, int pY, Component pMessage) {
        super(screen, pX, pY, 188, 258, pMessage);
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(getX(), getY(), 0);

        pGuiGraphics.blitSprite(inventory_slots, width / 2 - 77, height - 69, 154, 69);

        pGuiGraphics.blitSprite(crafting_slots, 0, height - 121, 188, 52);

        pGuiGraphics.blitSprite(container_slots, 0, 0, 188, 137);

        pGuiGraphics.pose().popPose();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {}

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }
}
