package net.fiercemanul.fiercelive.data.tags;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class FLItemTags {


    public static final TagKey<Item> RAINBOW_DYES = ItemTags.create(FSUtils.rl("c", "dyes/rainbow"));
    public static final TagKey<Item> RAINBOW_DYED = ItemTags.create(FSUtils.rl("c", "dyed/rainbow"));
    public static final TagKey<Item> GLASS_LAMPS = create("glass_lamps");
    public static final TagKey<Item> GUARDRAILS = create("guardrails");
    public static final TagKey<Item> CROSS_HOLES = create("cross_holes");
    public static final TagKey<Item> CUT_BLOCKS = create("cut_blocks");
    public static final TagKey<Item> PILLARS = create("pillars");
    public static final TagKey<Item> SEA_LANTERNS = createCommunity("sea_lanterns");
    public static final TagKey<Item> REINFORCED_SEA_LANTERNS = create("reinforced_sea_lanterns");
    public static final TagKey<Item> TABLES = createCommunity("tables");
    public static final TagKey<Item> CABINETS = createCommunity("cabinets");
    public static final TagKey<Item> WOOL_SOFAS = create("wool_sofas");
    public static final TagKey<Item> COLOR_LAMPS = create("color_lamps");
    public static final TagKey<Item> IRON_CORRIDORS = create("iron_corridors");
    public static final TagKey<Item> TOOLS_CROWBAR = createCommunity("tools/crowbar");
    public static final TagKey<Item> CROWBARS = create("crowbars");
    public static final TagKey<Item> WEAPON_METEOR_HAMMER = createCommunity("tools/weapon_meteor_hammer");
    public static final TagKey<Item> METEOR_HAMMERS = create("meteor_hammers");
    public static final TagKey<Item> DOOR_SHIELDS = create("door_shields");

    private static TagKey<Item> create(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(FierceLive.MODID, name));
    }

    private static TagKey<Item> createCommunity(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    private FLItemTags() {}
}
