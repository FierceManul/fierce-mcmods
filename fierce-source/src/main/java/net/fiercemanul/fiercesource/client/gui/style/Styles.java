package net.fiercemanul.fiercesource.client.gui.style;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.resources.ResourceLocation;

public class Styles {

    public static final UIStyle DEFAULT = new UIStyle(
            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/background"),
            Padding.two(26, 6),
            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/scrollbar_tab"),
            new ScrollBarTabSize(2, 58, 63,9),
            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_off"),
            new IntXY(20, 22),
            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_on"),
            IntXY.same(22),
            new TabMargins(-26, 54, 80, 24),
            IntXY.same(3)
    );

    public static UIStyle chooseingUIStyle = DEFAULT;
}
