package net.fiercemanul.fiercesource.client.gui.style;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

/**
 * 都是精灵图
 */
public record UIStyle(
        BackgroundData backgroundData,
        ScrollbarData scrollbarData,
        TabsArea tabsArea,
        TabData tabData
) {

    public static final Codec<UIStyle> CODEC = RecordCodecBuilder.create(uiStyleInstance -> uiStyleInstance.group(
            BackgroundData.CODEC.fieldOf("background_data").forGetter(UIStyle::backgroundData),
            ScrollbarData.CODEC.fieldOf("scrollbar_data").forGetter(UIStyle::scrollbarData),
            TabsArea.CODEC.fieldOf("tabs_area").forGetter(UIStyle::tabsArea),
            TabData.CODEC.fieldOf("tab_data").forGetter(UIStyle::tabData)
    ).apply(uiStyleInstance, UIStyle::new));

    public static final int INVENTORY_SLOTS_WIDTH = 154;
    public static final int INVENTORY_SLOTS_HEIGHT = 69;

    public record Size(int width, int height) {

        public static final Codec<Size> CODEC = RecordCodecBuilder.create(sizeInstance -> sizeInstance.group(
                Codec.INT.fieldOf("width").forGetter(Size::width),
                Codec.INT.fieldOf("height").forGetter(Size::height)
        ).apply(sizeInstance, Size::new));

        public static Size same(int i) {
            return new Size(i, i);
        }
    }

    public record Offset(int x, int y) {

        public static final Codec<Offset> CODEC = RecordCodecBuilder.create(offsetInstance -> offsetInstance.group(
                Codec.INT.fieldOf("x").forGetter(Offset::x),
                Codec.INT.fieldOf("y").forGetter(Offset::y)
        ).apply(offsetInstance, Offset::new));

        public static Offset same(int i) {
            return new Offset(i, i);
        }
    }

    public record Padding(int left, int top, int right, int bottom) {

        public static final Codec<Padding> CODEC = RecordCodecBuilder.create(paddingInstance -> paddingInstance.group(
                Codec.INT.fieldOf("left").forGetter(Padding::left),
                Codec.INT.fieldOf("top").forGetter(Padding::top),
                Codec.INT.fieldOf("right").forGetter(Padding::right),
                Codec.INT.fieldOf("bottom").forGetter(Padding::bottom)
        ).apply(paddingInstance, Padding::new));

        public static final Padding ZERO = same(0);

        public static Padding same(int i) {
            return new Padding(i, i, i, i);
        }

        public static Padding two(int left, int top) {
            return new Padding(left, top, left, top);
        }
    }

    public record ImgButtonData(
            Size size,
            ResourceLocation offImg,
            ResourceLocation onImg,
            Padding imgPadding
    ) {
        public static final Codec<ImgButtonData> CODEC = RecordCodecBuilder.create(imgButtonDataInstance -> imgButtonDataInstance.group(
                Size.CODEC.fieldOf("size").forGetter(ImgButtonData::size),
                ResourceLocation.CODEC.fieldOf("off_img").forGetter(ImgButtonData::offImg),
                ResourceLocation.CODEC.fieldOf("on_img").forGetter(ImgButtonData::onImg),
                Padding.CODEC.fieldOf("img_padding").forGetter(ImgButtonData::imgPadding)
        ).apply(imgButtonDataInstance, ImgButtonData::new));
    }

    public record RefineImgButtonData(
            Size size,
            ResourceLocation offImg,
            ResourceLocation offHoverImg,
            ResourceLocation offPressImg,
            ResourceLocation onImg,
            ResourceLocation onHoverImg,
            ResourceLocation onPressImg,
            Padding imgPadding
    ) {
        public static final Codec<RefineImgButtonData> CODEC = RecordCodecBuilder.create(refineImgButtonDataInstance -> refineImgButtonDataInstance.group(
                Size.CODEC.fieldOf("size").forGetter(RefineImgButtonData::size),
                ResourceLocation.CODEC.fieldOf("off_img").forGetter(RefineImgButtonData::offImg),
                ResourceLocation.CODEC.fieldOf("off_hover_img").forGetter(RefineImgButtonData::offHoverImg),
                ResourceLocation.CODEC.fieldOf("off_press_img").forGetter(RefineImgButtonData::offPressImg),
                ResourceLocation.CODEC.fieldOf("on_img").forGetter(RefineImgButtonData::onImg),
                ResourceLocation.CODEC.fieldOf("on_hover_img").forGetter(RefineImgButtonData::onHoverImg),
                ResourceLocation.CODEC.fieldOf("on_press_img").forGetter(RefineImgButtonData::onPressImg),
                Padding.CODEC.fieldOf("img_padding").forGetter(RefineImgButtonData::imgPadding)
        ).apply(refineImgButtonDataInstance, RefineImgButtonData::new));
    }

    /**
     * 主背景图,内边距相对于画布
     */
    public record BackgroundData(
            ResourceLocation img,
            Padding imgPadding
    ) {
        public static final Codec<BackgroundData> CODEC = RecordCodecBuilder.create(backgroundDataInstance -> backgroundDataInstance.group(
                ResourceLocation.CODEC.fieldOf("img").forGetter(BackgroundData::img),
                Padding.CODEC.fieldOf("img_padding").forGetter(BackgroundData::imgPadding)
        ).apply(backgroundDataInstance, BackgroundData::new));
    }

    /**
     * 滚动条
     * @param leftOffset 滚动条左对画布右
     * @param topOffset 滚动条的顶对画布顶
     * @param weight 滚动条宽度
     */
    public record ScrollbarData(
            ResourceLocation img,
            int leftOffset,
            int topOffset,
            int bottomMargin,
            int weight
    ) {
        public static final Codec<ScrollbarData> CODEC = RecordCodecBuilder.create(scrollBarDataInstance -> scrollBarDataInstance.group(
                ResourceLocation.CODEC.fieldOf("img").forGetter(ScrollbarData::img),
                Codec.INT.fieldOf("left_offset").forGetter(ScrollbarData::leftOffset),
                Codec.INT.fieldOf("top_offset").forGetter(ScrollbarData::topOffset),
                Codec.INT.fieldOf("bottom_margin").forGetter(ScrollbarData::bottomMargin),
                Codec.INT.fieldOf("weight").forGetter(ScrollbarData::weight)
        ).apply(scrollBarDataInstance, ScrollbarData::new));
    }

    /**
     * 画布标签集可用区域，不是实际区域
     * @param leftOffset 区域左相对画布左
     * @param topOffset 区域又相对画布左
     * @param bottomOffset 区域底相对画布底
     */
    public record TabsArea(
            int leftOffset,
            int topOffset,
            int bottomOffset
    ) {
        public static final Codec<TabsArea> CODEC = RecordCodecBuilder.create(tabsAreaInstance -> tabsAreaInstance.group(
                Codec.INT.fieldOf("left_offset").forGetter(TabsArea::leftOffset),
                Codec.INT.fieldOf("top_offset").forGetter(TabsArea::topOffset),
                Codec.INT.fieldOf("bottom_offset").forGetter(TabsArea::bottomOffset)
        ).apply(tabsAreaInstance, TabsArea::new));
    }

    /**
     * 画布标签,内边距相对于size
     */
    public record TabData(
            ImgButtonData tabImgButtonData,
            Offset iconOffset,
            int tabMargin,
            ImgButtonData pageUpImgButtonData,
            ImgButtonData pageDownImgButtonData,
            int pageButtonMargin
    ) {
        public static final Codec<TabData> CODEC = RecordCodecBuilder.create(tabDataInstance -> tabDataInstance.group(
                ImgButtonData.CODEC.fieldOf("tab_img_button_data").forGetter(TabData::tabImgButtonData),
                Offset.CODEC.fieldOf("icon_offset").forGetter(TabData::iconOffset),
                Codec.INT.fieldOf("tab_margin").forGetter(TabData::tabMargin),
                ImgButtonData.CODEC.fieldOf("page_up_img_button_data").forGetter(TabData::pageUpImgButtonData),
                ImgButtonData.CODEC.fieldOf("page_down_img_button_data").forGetter(TabData::pageDownImgButtonData),
                Codec.INT.fieldOf("page_button_margin").forGetter(TabData::pageButtonMargin)
        ).apply(tabDataInstance, TabData::new));
    }
}
