package net.fiercemanul.fiercedecoration.tags;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class FDBlockTags {


    public static final TagKey<Block> LAMP_IN_GLASS = BlockTags.create(new ResourceLocation(FierceDecoration.MODID, "lamp_in_glass"));
    public static final TagKey<Block> GUARDRAILS_TAG = BlockTags.create(new ResourceLocation(FierceDecoration.MODID, "guardrails"));
    public static final TagKey<Block> PEEP_WINDOWS_TAG = BlockTags.create(new ResourceLocation(FierceDecoration.MODID, "peep_windows"));
    public static final TagKey<Block> CUT_BLOCKS_TAG = BlockTags.create(new ResourceLocation(FierceDecoration.MODID, "cut_blocks"));
    public static final TagKey<Block> PILLAR_TAG = BlockTags.create(new ResourceLocation(FierceDecoration.MODID, "pillars"));
    public static final TagKey<Block> PILLAR_FORCE_CONNECT_UP = BlockTags.create(new ResourceLocation(FierceDecoration.MODID, "pillar_force_connect_up"));
    public static final TagKey<Block> PILLAR_FORCE_CONNECT_DOWN = BlockTags.create(new ResourceLocation(FierceDecoration.MODID, "pillar_force_connect_down"));
    public static final TagKey<Block> PILLAR_FORCE_CONNECT_SIDE = BlockTags.create(new ResourceLocation(FierceDecoration.MODID, "pillar_force_connect_side"));
    public static final TagKey<Block> SEA_LANTERN_TAG = BlockTags.create(new ResourceLocation(FierceDecoration.MODID, "sea_lanterns"));

    private FDBlockTags() {}
}
