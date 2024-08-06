package net.fiercemanul.fiercesource.client.gui.style;

import net.minecraft.resources.ResourceLocation;

public record UIStyle(
        ResourceLocation background,
        //相对于主画布的边距
        Padding backgroundPadding,
        ResourceLocation scrollBarTab,
        ScrollBarTabSize scrollBarTabSize,
        //x相对于主画布右边，y相对于主画布顶边。
        ResourceLocation tabOff,
        //tab大小
        IntXY tabOffSize,
        ResourceLocation tabOn,
        //tab大小
        IntXY tabOnSize,
        TabMargins tabMargins,
        //tab内容物对tab的左上边距
        IntXY tabPadding
) {
    public interface WidgetSizeGetter {
        IntXY get(int canvasHeight);
    }
}
