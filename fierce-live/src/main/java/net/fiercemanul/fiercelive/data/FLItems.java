package net.fiercemanul.fiercelive.data;

import net.fiercemanul.fiercelive.world.item.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.fiercemanul.fiercelive.data.registries.FLRegister.ITEMS;

public final class FLItems {


    public static final DeferredItem<Item> FOX_CARROT = ITEMS.registerSimpleItem(
            "fox_carrot", new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.3F).build()));
    public static final DeferredItem<Item> FOX_CARROT_SEED = ITEMS.register(
            "fox_carrot_seeds", () -> new ItemNameBlockItem(FLBlocks.FOX_CARROTS.get(), new Item.Properties()));
    //public static final DeferredItem<Item> CLAW_HAMMER = ITEMS.register(
    //        "claw_hammer", () -> new CrowbarItem(Tiers.IRON, new Item.Properties(), 1.0F, -1.0F));
    //public static final DeferredItem<Item> NETHERITE_CLAW_HAMMER = ITEMS.register(
    //        "netherite_claw_hammer", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant(), 1.0F, -1.0F));
    public static final DeferredItem<Item> GLOW_PEARL_ARROW = ITEMS.registerItem(
            "glow_pearl_arrow", GlowPearlArrowItem::new);
    public static final DeferredItem<Item> CROWBAR = ITEMS.register(
            "crowbar", () -> new CrowbarItem(Tiers.IRON, new Item.Properties(), 1.0F, -1.0F));
    public static final DeferredItem<Item> NETHERITE_CROWBAR = ITEMS.register(
            "netherite_crowbar", () -> new CrowbarItem(Tiers.NETHERITE, new Item.Properties().fireResistant(), 1.0F, -1.0F));
    public static final DeferredItem<GlassKnifeItem> GLASS_KNIFE = ITEMS.registerItem(
            "glass_knife", GlassKnifeItem::new, GlassKnifeItem.DEFAULT_PROPERTIES);
    public static final DeferredItem<Item> RAINBOW_DYE = ITEMS.registerSimpleItem(
            "rainbow_dye");
    public static final DeferredItem<Item> CARAMEL = ITEMS.registerSimpleItem(
            "caramel");
    public static final DeferredItem<Item> FROSTED_BREAD = ITEMS.registerSimpleItem(
            "frosted_bread", new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).build()));
    public static final DeferredItem<Item> SWEET_BERRY_COOKIE = ITEMS.registerSimpleItem(
            "sweet_berry_cookie", new Item.Properties().food(Foods.COOKIE));
    public static final DeferredItem<Item> GLOW_BERRY_COOKIE = ITEMS.registerSimpleItem(
            "glow_berry_cookie", new Item.Properties().food(Foods.COOKIE));
    public static final DeferredItem<Item> CARAMEL_COOKIE = ITEMS.registerSimpleItem(
            "caramel_cookie", new Item.Properties().food(Foods.COOKIE));
    public static final DeferredItem<Item> PUFFERFISH_ROD  = ITEMS.registerItem(
            "pufferfish_rod", PufferfishRodItem::new, new Item.Properties().durability(Tiers.WOOD.getUses()).attributes(createAttributes(2, -0.5F)));
    public static final DeferredItem<Item> METEOR_HAMMER = ITEMS.register(
            "meteor_hammer", () -> new MeteorHammerItem(Tiers.IRON, new Item.Properties(), 6.0F, -3.1F));
    public static final DeferredItem<Item> NETHERITE_METEOR_HAMMER = ITEMS.register(
            "netherite_meteor_hammer", () -> new MeteorHammerItem(Tiers.NETHERITE, new Item.Properties().fireResistant(), 5.0F, -3F));
    public static final DeferredItem<Item> SACABAMBASPIS = ITEMS.registerItem(
            "sacabambaspis", SimpleWeaponItem::new, new Item.Properties().durability(Tiers.IRON.getUses()).attributes(createAttributes(3, -1F)));
    public static final DeferredItem<Item> PARROT = ITEMS.registerItem(
            "parrot", WrenchItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> WOODEN_DOOR = ITEMS.registerItem(
            "wooden_door", ShieldItem::new, new Item.Properties().durability(168));
    public static final DeferredItem<Item> IRON_DOOR = ITEMS.registerItem(
            "iron_door", ShieldItem::new, new Item.Properties().durability(1008));
    public static final DeferredItem<Item> INFINITE_SNOWBALL = ITEMS.registerItem(
            "infinite_snowball", InfiniteSnowballItem::new, new Item.Properties().stacksTo(1).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));


    public static void init() {}

    private static ItemAttributeModifiers createAttributes(float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                                     .add(
                                             Attributes.ATTACK_DAMAGE,
                                             new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage, AttributeModifier.Operation.ADD_VALUE),
                                             EquipmentSlotGroup.MAINHAND
                                     )
                                     .add(
                                             Attributes.ATTACK_SPEED,
                                             new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                                             EquipmentSlotGroup.MAINHAND
                                     )
                                     .build();
    }

    private FLItems() {}
}
