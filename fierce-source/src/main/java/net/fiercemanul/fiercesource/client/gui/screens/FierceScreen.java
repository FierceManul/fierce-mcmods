package net.fiercemanul.fiercesource.client.gui.screens;

import net.fiercemanul.fiercesource.client.gui.components.Canvas;
import net.fiercemanul.fiercesource.client.gui.components.TestCanvas;
import net.fiercemanul.fiercesource.client.gui.style.UIStyle;
import net.fiercemanul.fiercesource.client.gui.style.Styles;
import net.fiercemanul.fiercesource.world.menu.FierceMenu;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FierceScreen extends AbstractContainerScreen<FierceMenu> {


    //画布宽度不要超过屏幕的0.4
    protected static final float MAX_CANVAS_SIZE = 0.4F;
    private static final UIStyle UI_STYLE = Styles.chooseingUIStyle;
    protected int centerX;
    protected int centerY;
    protected boolean scrollBarEnable;
    protected Canvas canvas;

    public FierceScreen(FierceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        canvas = new TestCanvas(this, 0, 0, pTitle);
    }

    @Override
    protected void init() {
        centerX = width / 2;
        centerY = height / 2;
        canvas.makeSize(width, height);
        addWidget(canvas);
        leftPos = canvas.getX();
        topPos = canvas.getY();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        canvas.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        //if (isHovered && TipLevel.level > 0) pGuiGraphics.renderTooltip(screen.getFont(), getMessage(), pMouseX, pMouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {}

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blitSprite(
                UI_STYLE.background(),
                leftPos - UI_STYLE.backgroundPadding().left(),
                topPos - UI_STYLE.backgroundPadding().top(),
                UI_STYLE.backgroundPadding().left() + canvas.getWidth() + UI_STYLE.backgroundPadding().right(),
                UI_STYLE.backgroundPadding().top() + canvas.getHeight() + UI_STYLE.backgroundPadding().bottom()
        );
        pGuiGraphics.blitSprite(
                UI_STYLE.scrollBarTab(),
                canvas.getXEnd() + UI_STYLE.scrollBarTabSize().leftOffset(),
                topPos + UI_STYLE.scrollBarTabSize().topMargin(),
                UI_STYLE.scrollBarTabSize().width(),
                canvas.getHeight() - UI_STYLE.scrollBarTabSize().topMargin() - UI_STYLE.scrollBarTabSize().bottomMargin()
        );
        pGuiGraphics.blitSprite(
                UI_STYLE.tabOn(),
                leftPos + UI_STYLE.tabMargins().leftOffset(),
                canvas.getYEnd() - UI_STYLE.tabMargins().bottomMargin(),
                UI_STYLE.tabOnSize().x(),
                UI_STYLE.tabOnSize().y()
        );
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Font getFont() {
        return font;
    }

    public void enableScrollBar() {
        scrollBarEnable = true;
    }

    public void scrollBarDisable() {
        scrollBarEnable = false;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
}
