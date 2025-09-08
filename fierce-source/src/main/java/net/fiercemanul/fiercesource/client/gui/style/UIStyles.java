package net.fiercemanul.fiercesource.client.gui.style;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class UIStyles {


    public static final UIStyle DEFAULT = new UIStyle(
            new UIStyle.BackgroundData(
                    ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/background"),
                    UIStyle.Padding.two(28, 9)
            ),
            new UIStyle.ScrollbarData(
                    ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/scrollbar_tab"),
                    9, 11, 30, 14
            ),
            new UIStyle.TabsArea(-25, 7, -7),
            new UIStyle.TabData(
                    new UIStyle.ImgButtonData(
                            UIStyle.Size.same(18),
                            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_off"),
                            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_on"),
                            UIStyle.Padding.same(1)
                    ),
                    UIStyle.Offset.same(1),
                    -1,
                    new UIStyle.ImgButtonData(
                            new UIStyle.Size(12, 6),
                            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_page_off"),
                            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_page_up_on"),
                            UIStyle.Padding.ZERO
                    ),
                    new UIStyle.ImgButtonData(
                            new UIStyle.Size(12, 6),
                            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_page_off"),
                            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_page_down_on"),
                            UIStyle.Padding.ZERO
                    ),
                    2
            )
    );

    public static UIStyle style = DEFAULT;

    private static Map<ResourceLocation, UIStyle> styles = new HashMap<>();


    public static void setStyle(ResourceLocation rl) {
        style = styles.getOrDefault(rl, DEFAULT);
    }

    public static void setStyles(Map<ResourceLocation, UIStyle> styles) {
        UIStyles.styles = styles;
    }
}
