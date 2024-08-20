package net.fiercemanul.fiercesource.client.gui.style;

import net.minecraft.resources.ResourceLocation;

/**
 * 都是精灵图
 * 一些定死的数据，硬编码：
 * tabIcon：16x16
 * @param background 背景图
 * @param backgroundPadding 背景图相对于主画布的边距 左 顶 右 底
 * @param scrollBarTab 滚动条标签图
 * @param scrollBarTabSize 大小与偏移, 画布左对tab左, 画布顶对tab的顶, 画布底对tab的底, 宽度
 * @param tabSize 标签的逻辑大小 x y
 * @param tabDown 下去的标签图
 * @param tabDownImgPadding tab图片数据 左偏移 顶偏移 宽 高
 * @param tabUp 选择的标签图
 * @param tabUpImgPadding tab图片数据 左偏移 顶偏移 宽 高
 * @param tabsMargin 标签集外边距, 画布左对tab左, 画布顶对第一个tab的顶, 画布底对最后一个tab的顶, tab顶与顶的距离
 * @param tabPadding tab对内容物的左上边距 x y
 */
public record UIStyle(
        ResourceLocation background,
        Int4 backgroundPadding,
        ResourceLocation scrollBarTab,
        Int4 scrollBarTabSize,
        Int2 tabSize,
        ResourceLocation tabDown,
        Int4 tabDownImgPadding,
        ResourceLocation tabUp,
        Int4 tabUpImgPadding,
        Int4 tabsMargin,
        Int2 tabPadding
) {}
