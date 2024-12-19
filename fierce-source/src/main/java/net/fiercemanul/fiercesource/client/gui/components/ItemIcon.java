package net.fiercemanul.fiercesource.client.gui.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public class ItemIcon implements Icon {

    private final ItemStack stack;

    public ItemIcon(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public void renderIcon(GuiGraphics pGuiGraphics, float pPartialTick, int iconX, int iconY) {
        pGuiGraphics.renderFakeItem(stack, iconX, iconY);
    }
}
