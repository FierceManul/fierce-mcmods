package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.style.UIStyle;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class Scrollbar extends AbstractWidget {


    protected static final ResourceLocation DEFAULT_BG = ResourceLocation.withDefaultNamespace("widget/scroller_background");
    protected static final ResourceLocation DEFAULT_SCROLLER = ResourceLocation.withDefaultNamespace("widget/scroller");
    protected int imgX, imgY, imgW, imgH;


    public Scrollbar(Component message) {
        super(0, 0, 0, 0, message);
    }

    public void init(UIStyle.ImgButtonData buttonData, int x, int y) {
        int width = buttonData.size().width();
        int height = buttonData.size().height();
        this.width = width;
        this.height = height;
        UIStyle.Padding padding = buttonData.imgPadding();
        setX(x);
        setY(y);
        imgW = padding.left() + width + padding.right();
        imgH = padding.top() + height + padding.bottom();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        //guiGraphics.blitSprite(isFocused() ? buttonData.onImg() : buttonData.offImg(), imgX, imgY, imgW, imgH);
    }

    @Override
    protected boolean isValidClickButton(int button) {
        return true;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {}
}
