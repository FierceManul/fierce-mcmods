package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.screens.FierceScreen;
import net.fiercemanul.fiercesource.client.gui.style.Int4;
import net.fiercemanul.fiercesource.client.gui.style.Styles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public abstract class Tab<T> extends AbstractWidget {


    protected final FierceScreen screen;
    protected Canvas canvas;


    public Tab(int pX, int pY, Component pMessage, FierceScreen screen, Canvas canvas) {
        super(pX, pY, Styles.chooseingUIStyle.tabSize().i1(), Styles.chooseingUIStyle.tabSize().i2(), pMessage);
        this.screen = screen;
        this.canvas = canvas;
        this.setTooltip(Tooltip.create(pMessage));
    }

    public void renderTab(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        ResourceLocation img;
        Int4 data;
        if (isFocused()) {
            img = Styles.chooseingUIStyle.tabUp();
            data = Styles.chooseingUIStyle.tabUpImgPadding();
        } else {
            img = Styles.chooseingUIStyle.tabDown();
            data = Styles.chooseingUIStyle.tabDownImgPadding();
        }
        pGuiGraphics.blitSprite(
                img,
                getX() + data.i1(),
                getY() + data.i2(),
                data.i3(),
                data.i4()
        );
        renderIcon(pGuiGraphics, pPartialTick);
    }

    abstract protected void renderIcon(GuiGraphics pGuiGraphics, float pPartialTick);

    abstract public void setIcon(T icon);

    public void tabClicked(int button) {
        setFocused(true);
        screen.setCanvas(canvas);
        playDownSound(Minecraft.getInstance().getSoundManager());
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {}

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

}
