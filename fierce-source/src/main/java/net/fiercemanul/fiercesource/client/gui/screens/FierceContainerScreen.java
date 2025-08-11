package net.fiercemanul.fiercesource.client.gui.screens;

import net.fiercemanul.fiercesource.client.TipLevel;
import net.fiercemanul.fiercesource.client.gui.components.*;
import net.fiercemanul.fiercesource.client.gui.style.UIStyle;
import net.fiercemanul.fiercesource.client.gui.style.UIStyles;
import net.fiercemanul.fiercesource.world.inventory.FierceContainerMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.LinkedHashSet;

@OnlyIn(Dist.CLIENT)
public class FierceContainerScreen extends AbstractContainerScreen<FierceContainerMenu> {


    protected int centerX, centerY;
    protected double mouseX, mouseY;
    protected Canvas choseCanvas;
    protected boolean scrollBarEnable = false;
    protected Tab[] tabs;
    protected Tab[][] tabPages = new Tab[0][];
    protected int tabPageIndex;
    protected Tab choseTab;
    protected int tabAreaLeft, tabAreaTop, tabAreaRight, tabAreaBottom;
    protected StyleButton tabPageUpButton;
    protected StyleButton tabPageDownButton;

    public FierceContainerScreen(FierceContainerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        choseCanvas = new TestMainCanvas(this, 0, 0, pTitle);

        MutableComponent component = Component.literal("lv0");
        if (TipLevel.level > 0) component.append(Component.literal("\nlv1").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        if (TipLevel.level > 1) component.append(Component.literal("\nlv2").withStyle(ChatFormatting.LIGHT_PURPLE));

        LinkedHashSet<Tab> tabs = new LinkedHashSet<>();
        tabs.add(new Tab(choseCanvas, new ItemIcon(Items.NETHERITE_SWORD.getDefaultInstance()), Component.literal("MainTest")));
        tabs.add(new Tab(new TestBigInvCanvas(this, 0, 0, pTitle), new ItemIcon(Items.CHEST.getDefaultInstance()), Component.literal("BigInvTest")));
        tabs.add(new Tab(new TestMinCanvas(this, 0, 0, pTitle), new ItemIcon(Items.ENDER_CHEST.getDefaultInstance()), Component.literal("MinTest")));
        tabs.add(new Tab(new TestAutoSizeCanvas(this, 0, 0, pTitle), new ItemIcon(Items.SLIME_BLOCK.getDefaultInstance()), Component.literal("AutoSizeTest")));
        for (int i = 0; i < 12; i++) tabs.add(new Tab(choseCanvas, new ItemIcon(Items.STONE.getDefaultInstance()), Component.literal("Test " + (i + 5))));
        this.tabs = tabs.toArray(new Tab[]{});
        choseTab = tabs.getFirst();
        choseTab.setFocused(true);
        choseTab.setTooltip(Tooltip.create(component));

        tabPageUpButton = new StyleButton(Component.empty());
        tabPageDownButton = new StyleButton(Component.empty());
    }

    @Override
    protected void init() {
        centerX = width / 2;
        centerY = height / 2;
        mouseX = centerX;
        mouseY = centerY;
        addWidget(choseCanvas);
        canvasChanged();
    }

    private void canvasChanged() {
        choseCanvas.makeSize(width, height);
        leftPos = choseCanvas.getX();
        topPos = choseCanvas.getY();

        UIStyle.TabsArea tabsArea = UIStyles.chooseingUIStyle.tabsArea();
        UIStyle.TabData tabData = UIStyles.chooseingUIStyle.tabData();
        UIStyle.Size tabSize = tabData.tabImgButtonData().size();
        int tabMargin = tabData.tabMargin();

        tabAreaLeft = leftPos + tabsArea.leftOffset();
        tabAreaTop = topPos + tabsArea.topOffset();
        tabAreaRight = tabAreaLeft + tabSize.width();
        tabAreaBottom = choseCanvas.getBottom() + tabsArea.bottomOffset();

        UIStyle.ImgButtonData pageUpImgButtonData = tabData.pageUpImgButtonData();
        tabPageUpButton.init(
                pageUpImgButtonData,
                tabAreaLeft + ((tabSize.width() - pageUpImgButtonData.size().width()) / 2),
                tabAreaTop
        );
        UIStyle.ImgButtonData pageDownImgButtonData = tabData.pageDownImgButtonData();
        tabPageDownButton.init(
                pageDownImgButtonData,
                tabAreaLeft + ((tabSize.width() - pageDownImgButtonData.size().width()) / 2),
                tabAreaBottom - pageDownImgButtonData.size().height()
        );

        //排列tab
        int tabsH = tabs.length * (tabSize.height() + tabMargin) - tabMargin;
        int preHeight = tabSize.height() + tabMargin;
        if (tabsH <= tabAreaBottom - tabAreaTop) {
            tabPages = new Tab[][]{tabs};
            for (int i = 0; i < tabs.length; i++) tabs[i].init(tabAreaLeft, tabAreaTop + preHeight * i);
            tabPageIndex = 0;
        }
        else {
            tabAreaTop = tabAreaTop + pageUpImgButtonData.size().height();
            tabAreaBottom = tabAreaBottom - pageDownImgButtonData.size().height();
            int tabStart = tabAreaTop + tabData.pageButtonMargin();
            int tabEnd = tabAreaBottom - tabData.pageButtonMargin();
            int tabSpace = tabEnd - tabStart;
            int pagePreTabs = (tabSpace + tabMargin) / (tabSize.height() + tabMargin);
            if (pagePreTabs <= 0) pagePreTabs = 1;
            int pages = (int) Math.ceil((double) tabs.length / pagePreTabs);
            tabPages = new Tab[pages][];
            int pageIndex = 0;
            for (int i = 0; i < pages; i++) {
                int index = i * pagePreTabs;
                tabPages[i] = Arrays.copyOfRange(tabs, index, Math.min(index + pagePreTabs, tabs.length));
                for (int j = 0; j < tabPages[i].length; j++) if (tabPages[i][j] != null) {
                    Tab tab = tabPages[i][j];
                    tab.init(tabAreaLeft, tabStart + preHeight * j);
                    if (choseTab == tab) pageIndex = i;
                }
            }
            setTabPage(pageIndex);
            tabAreaBottom = tabStart + preHeight * pagePreTabs - tabMargin + tabData.pageButtonMargin();
            tabPageDownButton.setY(tabAreaBottom);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        choseCanvas.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        if (tabPages.length > 1) {
            tabPageUpButton.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            tabPageDownButton.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        }
        for (Tab tab : tabPages[tabPageIndex]) tab.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);


        //debug mouse pos
        pGuiGraphics.fill(
                (int) mouseX,
                (int) mouseY,
                (int) (mouseX + 1),
                (int) (mouseY + 1),
                FastColor.ARGB32.color(128, 255, 255, 255)
        );
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        if (inTabArea(mouseX, mouseY)) {
            for (Tab tab : tabPages[tabPageIndex]) {
                if (mouseY >= tab.getY() && mouseY < tab.getBottom()) {
                    if (tab == choseTab) return false;
                    playButtonClickSound();
                    tab.setFocused(true);
                    choseTab.setFocused(false);
                    choseTab = tab;
                    setCanvas(tab.getCanvas());
                    return true;
                }
            }
            return false;
        }

        if (tabPageUpButton.canMouseHover(mouseX, mouseY)) {
            setTabPage(tabPageIndex - 1);
            playButtonClickSound();
            return true;
        }
        if (tabPageDownButton.canMouseHover(mouseX, mouseY)) {
            setTabPage(tabPageIndex + 1);
            playButtonClickSound();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (inTabArea(mouseX, mouseY) || tabPageUpButton.canMouseHover(mouseX, mouseY) || tabPageDownButton.canMouseHover(mouseX, mouseY)) {
            int i = (int) scrollY; //利用去小数特性过滤可能出现的小于1的值，不能用floor和ceil替换。
            if (i != 0) setTabPage(tabPageIndex - i);
            playScrolledSound();
            return true;
        }
        else return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {}

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        UIStyle.BackgroundData backgroundData = UIStyles.chooseingUIStyle.backgroundData();
        pGuiGraphics.blitSprite(
                backgroundData.img(),
                leftPos - backgroundData.imgPadding().left(),
                topPos - backgroundData.imgPadding().top(),
                backgroundData.imgPadding().left() + choseCanvas.getWidth() + backgroundData.imgPadding().right(),
                backgroundData.imgPadding().top() + choseCanvas.getHeight() + backgroundData.imgPadding().bottom()
        );

        UIStyle.ScrollbarData scrollBarData = UIStyles.chooseingUIStyle.scrollbarData();
        pGuiGraphics.blitSprite(
                scrollBarData.img(),
                choseCanvas.getRight() + scrollBarData.leftOffset(),
                topPos + scrollBarData.topOffset(),
                scrollBarData.weight(),
                choseCanvas.getHeight() - scrollBarData.topOffset() - scrollBarData.bottomMargin()
        );
    }

    protected void setCanvas(Canvas canvas) {
        removeWidget(choseCanvas);
        addWidget(canvas);
        this.choseCanvas = canvas;
        canvasChanged();
    }

    protected void setTabPage(int page) {
        if (page < 0) page = tabPages.length - 1;
        if (page > tabPages.length - 1) page = 0;
        tabPageIndex = page;
        Component msg = Component.literal(page + 1 + "/" + tabPages.length);
        tabPageUpButton.setFocused(page > 0);
        tabPageUpButton.setTooltip(Tooltip.create(msg));
        tabPageDownButton.setFocused(page < tabPages.length - 1);
        tabPageDownButton.setTooltip(Tooltip.create(msg));
    }

    protected boolean inTabArea(double mouseX, double mouseY) {
        return mouseX >= tabAreaLeft && mouseX < tabAreaRight && mouseY >= tabAreaTop && mouseY < tabAreaBottom;
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    protected void playButtonClickSound() {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    protected void playScrolledSound() {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1.0F));
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

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }
}
