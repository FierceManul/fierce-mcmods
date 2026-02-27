package net.fiercemanul.fiercesource.data.registries;

import net.fiercemanul.fiercesource.world.item.CrowbarItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.fiercemanul.fiercesource.data.registries.FCRegistries.ITEM;

public final class FSItems {


    public static final DeferredItem<Item>
            CLAW_HAMMER_ITEM = ITEM.register("claw_hammer", () -> new CrowbarItem(Tiers.IRON, new Item.Properties(), 1.0F, -1.0F));
    public static final DeferredItem<Item>
            NETHERITE_CLAW_HAMMER_ITEM = ITEM.register("netherite_claw_hammer", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant(), 1.0F, -1.0F));
    public static final DeferredItem<Item>
            CROWBAR_ITEM = ITEM.register("crowbar", () -> new CrowbarItem(Tiers.IRON, new Item.Properties(), 1.0F, -1.0F));
    public static final DeferredItem<Item>
            NETHERITE_CROWBAR_ITEM = ITEM.register("netherite_crowbar", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant(), 1.0F, -1.0F));
    public static final DeferredItem<Item>
            TEST_ITEM = ITEM.registerSimpleItem("test_item");
    public static final DeferredItem<Item>
            MANA_ICON = ITEM.registerSimpleItem("mana_icon");
    public static final DeferredItem<Item>
            FE_ICON = ITEM.registerSimpleItem("fe_icon");

    public static void init() {
    }
}
