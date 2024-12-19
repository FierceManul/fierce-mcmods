package net.fiercemanul.fiercesource.client.gui.components;

import net.fiercemanul.fiercesource.client.gui.style.UIStyle;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class StyleButton extends AbstractWidget {


    protected static final UIStyle.ImgButtonData DEFAULT_BUTTON_DATA = new UIStyle.ImgButtonData(
            new UIStyle.Size(0, 0),
            ResourceLocation.withDefaultNamespace("widget/button_disabled"),
            ResourceLocation.withDefaultNamespace("widget/button"),
            UIStyle.Padding.ZERO
    );
    protected UIStyle.ImgButtonData buttonData = DEFAULT_BUTTON_DATA;
    protected int imgX, imgY, imgW, imgH;


    public StyleButton(Component message) {
        super(0, 0, 0, 0, message);
    }

    public void init(UIStyle.ImgButtonData buttonData, int x, int y) {
        this.buttonData = buttonData;
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
        imgX = x - buttonData.imgPadding().left();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        imgY = y - buttonData.imgPadding().top();
    }

    /**
     * 无视可见性的悬浮检测
     */
    public boolean canMouseHover(double mouseX, double mouseY) {
        return mouseX >= this.getX() && mouseX < this.getX() + width && mouseY >= this.getY() && mouseY < this.getY() + height;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blitSprite(isFocused() ? buttonData.onImg() : buttonData.offImg(), imgX, imgY, imgW, imgH);
    }

    @Override
    protected boolean isValidClickButton(int button) {
        return true;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {}
}
