package net.fiercemanul.fiercesource.client.gui.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public class SizeItemIcon implements Icon {


    private final ItemStack stack;
    private final float size;

    public SizeItemIcon(ItemStack stack, float size) {
        this.stack = stack;
        this.size = size;
    }

    @Override
    public void renderIcon(GuiGraphics pGuiGraphics, float pPartialTick, int iconX, int iconY) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(iconX, iconY, 0);
        pGuiGraphics.pose().scale(size, size, 0);
        pGuiGraphics.renderFakeItem(stack, 0, 0);
        pGuiGraphics.pose().popPose();
    }

}
