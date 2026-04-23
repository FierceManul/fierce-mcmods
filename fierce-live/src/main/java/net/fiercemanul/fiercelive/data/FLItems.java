package net.fiercemanul.fiercelive.data;

import net.fiercemanul.fiercelive.world.item.CrowbarItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ScaffoldingBlockItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.fiercemanul.fiercelive.data.registries.FLRegister.ITEMS;

public final class FLItems {


    public static final DeferredItem<ScaffoldingBlockItem> IRON_SCAFFOLDING = ITEMS.register(
            "iron_scaffolding", () -> new ScaffoldingBlockItem(FLBlocks.IRON_SCAFFOLDING.get(), new Item.Properties()));
    public static final DeferredItem<Item> FOX_CARROT = ITEMS.registerSimpleItem(
            "fox_carrot", new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.3F).build()));
    public static final DeferredItem<Item> FOX_CARROT_SEED = ITEMS.register(
            "fox_carrot_seeds", () -> new ItemNameBlockItem(FLBlocks.FOX_CARROTS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CLAW_HAMMER_ITEM = ITEMS.register(
            "claw_hammer", () -> new CrowbarItem(Tiers.IRON, new Item.Properties(), 1.0F, -1.0F));
    public static final DeferredItem<Item> NETHERITE_CLAW_HAMMER_ITEM = ITEMS.register(
            "netherite_claw_hammer", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant(), 1.0F, -1.0F));
    public static final DeferredItem<Item> CROWBAR_ITEM = ITEMS.register(
            "crowbar", () -> new CrowbarItem(Tiers.IRON, new Item.Properties(), 1.0F, -1.0F));
    public static final DeferredItem<Item> NETHERITE_CROWBAR_ITEM = ITEMS.register(
            "netherite_crowbar", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant(), 1.0F, -1.0F));
    public static final DeferredItem<Item> RAINBOW_DYE = ITEMS.registerSimpleItem(
            "rainbow_dye", new Item.Properties());

    public static void init() {}

    private FLItems() {}
}
