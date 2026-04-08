package net.fiercemanul.fiercesource.data;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.fiercemanul.fiercesource.data.registries.FCRegistries.ITEMS;

public final class FSItems {


    public static final DeferredItem<Item>
            TEST_ITEM = ITEMS.registerSimpleItem("test_item");
    public static final DeferredItem<Item>
            MANA_ICON = ITEMS.registerSimpleItem("mana_icon");
    public static final DeferredItem<Item>
            FE_ICON = ITEMS.registerSimpleItem("fe_icon");

    public static void init() {
    }
}
