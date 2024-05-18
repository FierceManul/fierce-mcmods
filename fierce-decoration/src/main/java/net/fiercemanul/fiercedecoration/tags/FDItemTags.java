package net.fiercemanul.fiercedecoration.tags;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class FDItemTags {


    public static final TagKey<Item> LAMP_IN_GLASS = ItemTags.create(new ResourceLocation(FierceDecoration.MODID, "lamp_in_glass"));
    public static final TagKey<Item> GUARDRAILS_TAG = ItemTags.create(new ResourceLocation(FierceDecoration.MODID, "guardrails"));
    public static final TagKey<Item> PEEP_WINDOWS_TAG = ItemTags.create(new ResourceLocation(FierceDecoration.MODID, "peep_windows"));
    public static final TagKey<Item> CUT_BLOCKS_TAG = ItemTags.create(new ResourceLocation(FierceDecoration.MODID, "cut_blocks"));
    public static final TagKey<Item> PILLAR_TAG = ItemTags.create(new ResourceLocation(FierceDecoration.MODID, "pillars"));
    public static final TagKey<Item> SEA_LANTERN_TAG = ItemTags.create(new ResourceLocation(FierceDecoration.MODID, "sea_lanterns"));

    private FDItemTags() {}
}
