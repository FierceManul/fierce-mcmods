package net.fiercemanul.fiercesource.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fiercemanul.fiercesource.client.gui.screens.FierceScreen;
import net.fiercemanul.fiercesource.client.gui.style.Int2;
import net.fiercemanul.fiercesource.client.gui.style.Styles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ItemTab extends Tab<ItemStack> {


    private ItemStack itemStack;


    public ItemTab(int pX, int pY, Component pMessage, FierceScreen screen, Canvas canvas, ItemStack itemStack) {
        super(pX, pY, pMessage, screen, canvas);
        this.itemStack = itemStack;
    }

    @Override
    protected void renderIcon(GuiGraphics pGuiGraphics, float pPartialTick) {
        Int2 tabPadding = Styles.chooseingUIStyle.tabPadding();
        pGuiGraphics.renderItem(
                itemStack,
                getX() + tabPadding.i1(),
                getY() + tabPadding.i2(),
                0,
                0
        );
    }

    public void setIcon(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
