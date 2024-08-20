package net.fiercemanul.fiercesource.client.gui.style;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.resources.ResourceLocation;

public class Styles {

    public static final UIStyle DEFAULT = new UIStyle(
            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/background"),
            Int4.two(26, 10),
            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/scrollbar_tab"),
            new Int4(11, 12, 12,9),
            new Int2(17, 18),
            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_off"),
            new Int4(0, 0, 17, 19),
            ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "default/tab_on"),
            new Int4(0, -1, 22, 20),
            new Int4(-24, 9, 27, 21),
            Int2.same(1)
    );

    public static UIStyle chooseingUIStyle = DEFAULT;
}
