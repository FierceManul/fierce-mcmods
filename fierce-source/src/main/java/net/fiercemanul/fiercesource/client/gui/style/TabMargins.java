package net.fiercemanul.fiercesource.client.gui.style;

/**
 * @param leftOffset 画布左对tab左
 * @param topMargin 画布顶对第一个tab的顶
 * @param bottomMargin 画布底对最后一个tab的顶
 * @param tabsOffset tab顶与顶的距离
 */
public record TabMargins(
        int leftOffset,
        int topMargin,
        int bottomMargin,
        int tabsOffset
) {
}
