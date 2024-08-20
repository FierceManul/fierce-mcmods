package net.fiercemanul.fiercesource.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fiercemanul.fiercesource.client.gui.components.*;
import net.fiercemanul.fiercesource.client.gui.style.Int4;
import net.fiercemanul.fiercesource.client.gui.style.UIStyle;
import net.fiercemanul.fiercesource.client.gui.style.Styles;
import net.fiercemanul.fiercesource.world.menu.FierceMenu;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.LinkedHashSet;

@OnlyIn(Dist.CLIENT)
public class FierceScreen extends AbstractContainerScreen<FierceMenu> {


    //画布宽度不要超过屏幕的0.4
    protected static final float MAX_CANVAS_SIZE = 0.4F;
    private static final UIStyle UI_STYLE = Styles.chooseingUIStyle;
    protected int centerX;
    protected int centerY;
    protected boolean scrollBarEnable;
    protected Canvas canvas;
    protected Canvas canvas1;
    protected Canvas choseCanvas;
    protected Tab<?>[] tabs;
    protected Tab<?> choseTab;
    private boolean stackTab = false;
    protected Tab<?> blockTab;
    protected Tab<?> itemTab;

    public FierceScreen(FierceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        canvas = new TestCanvas(this, 0, 0, pTitle);
        canvas1 = new TestCanvas1(this, 0, 0, pTitle);
        choseCanvas = canvas;

        LinkedHashSet<Tab<?>> tabs = new LinkedHashSet<>();
        for (int i = 0; i < 7; i++) {
            tabs.add(new ItemTab(0, 0, Component.literal("Tab " + (2 * i + 1)), this, canvas, Items.NETHERITE_SWORD.getDefaultInstance()));
            tabs.add(new ItemTab(0, 0, Component.literal("Tab " + (2 * i + 2)), this, canvas1, Items.STONE.getDefaultInstance()));
        }
        this.tabs = tabs.toArray(new Tab<?>[]{});
        choseTab = tabs.getFirst();
        choseTab.setFocused(true);

        blockTab = new ItemTab(0, 0, Component.literal("Block Tab"), this, canvas, Items.CHEST.getDefaultInstance());
        itemTab = new ItemTab(0, 0, Component.literal("Item Tab"), this, canvas1, Items.GOLDEN_SWORD.getDefaultInstance());
    }

    @Override
    protected void init() {
        centerX = width / 2;
        centerY = height / 2;
        addWidget(choseCanvas);
        addRenderableWidget(blockTab);
        addRenderableWidget(itemTab);
        canvasChanged();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        choseCanvas.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        int toolTipOffset = 0;
        if (stackTab) {
            int c = tabs.length;
            toolTipOffset = tabs.length * 200 + 200;
            boolean hasHovered = false;
            for (int i = 0; i < c; i++) {
                PoseStack stack = pGuiGraphics.pose();
                stack.pushPose();
                if (tabs[i] == choseTab) stack.translate(0, 0, toolTipOffset - 200);
                else if (!hasHovered && tabs[i].isMouseOver(pMouseX, pMouseY) && !choseTab.isMouseOver(pMouseX, pMouseY)) {
                    stack.translate(0, 0, toolTipOffset);
                    hasHovered = true;
                }
                else stack.translate(0, 0, 200 * (c - i - 1));
                tabs[i].renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
                stack.popPose();
            }
        } else for (Tab<?> tab : tabs) tab.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);


        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(0, 0, toolTipOffset);
        if (choseTab.isMouseOver(pMouseX, pMouseY)) pGuiGraphics.renderTooltip(font, choseTab.getMessage(), pMouseX, pMouseY);
        else for (Tab<?> tab : tabs) if (tab.isMouseOver(pMouseX, pMouseY)) {
            Tooltip tooltip = tab.getTooltip();
            if (tooltip != null && minecraft != null) pGuiGraphics.renderTooltip(font, tooltip.toCharSequence(minecraft), pMouseX, pMouseY);
            break;
        }

        pGuiGraphics.fill(
                pMouseX - 1,
                pMouseY - 1,
                pMouseX + 1,
                pMouseY + 1,
                FastColor.ARGB32.color(128, 255, 255, 255)
        );
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {

        //未击中以选择的标签时
        if (!choseTab.isMouseOver(pMouseX, pMouseY)) {
            //是否已经击中其他标签
            boolean clicked = false;
            for (Tab<?> tab : tabs) {
                if (!clicked) {
                    if (tab == choseTab) continue; //未击中时，跳过已选择的标签，避免所有标签都被去焦点。
                    if (tab.isMouseOver(pMouseX, pMouseY)) { //击中了标签
                        tab.tabClicked(pButton);
                        clicked = true;
                        choseTab.setFocused(false);
                        choseTab = tab;
                        continue;
                    }
                }
                tab.setFocused(false);
            }
        }

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {}

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        Int4 backgroundPadding = UI_STYLE.backgroundPadding();
        pGuiGraphics.blitSprite(
                UI_STYLE.background(),
                leftPos - backgroundPadding.i1(),
                topPos - backgroundPadding.i2(),
                backgroundPadding.i1() + choseCanvas.getWidth() + backgroundPadding.i3(),
                backgroundPadding.i2() + choseCanvas.getHeight() + backgroundPadding.i4()
        );

        Int4 scrollBarTabSize = UI_STYLE.scrollBarTabSize();
        pGuiGraphics.blitSprite(
                UI_STYLE.scrollBarTab(),
                choseCanvas.getRight() + scrollBarTabSize.i1(),
                topPos + scrollBarTabSize.i2(),
                scrollBarTabSize.i4(),
                choseCanvas.getHeight() - scrollBarTabSize.i2() - scrollBarTabSize.i3()
        );
    }

    public void setCanvas(Canvas canvas) {
        removeWidget(choseCanvas);
        addWidget(canvas);
        this.choseCanvas = canvas;
        canvasChanged();
    }

    private void canvasChanged() {
        choseCanvas.makeSize(width, height);
        leftPos = choseCanvas.getX();
        topPos = choseCanvas.getY();

        Int4 tabsMargin = Styles.chooseingUIStyle.tabsMargin();
        int tabLeft = leftPos + tabsMargin.i1();
        int tabsMarginTop = tabsMargin.i2();
        int tabSpace = tabsMargin.i4();
        int tabsTop = topPos + tabsMarginTop + tabSpace;
        int tabsBottom = choseCanvas.getBottom() - tabsMargin.i3();
        int tabsHeight = tabs.length * tabSpace;
        int preHeight = tabSpace;
        int maxTabsHeight = tabsBottom - tabsTop;
        if (tabsHeight <= maxTabsHeight) stackTab = false;
        else {
            tabsHeight = maxTabsHeight - tabSpace;
            preHeight = tabsHeight / (tabs.length - 1);
            tabsHeight = preHeight * (tabs.length - 1) + tabSpace;  //矫正整数精度误差
            stackTab = true;
        }
        int tabsStar = tabsTop + (maxTabsHeight - tabsHeight) / 2;
        for (int i = 0; i < tabs.length; i++) tabs[i].setPosition(tabLeft, tabsStar + preHeight * i);
        blockTab.setPosition(tabLeft, topPos + tabsMarginTop);
        itemTab.setPosition(tabLeft, tabsBottom);
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
