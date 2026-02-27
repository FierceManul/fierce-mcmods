package net.fiercemanul.fiercedecoration.tags;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class FDBlockTags {


    public static final TagKey<Block> GLASS_LAMPS = create("glass_lamps");
    public static final TagKey<Block> GUARDRAILS = create("guardrails");
    public static final TagKey<Block> CROSS_HOLES = create("cross_holes");
    public static final TagKey<Block> CUT_BLOCKS = create("cut_blocks");
    public static final TagKey<Block> PILLARS = create("pillars");
    public static final TagKey<Block> PILLAR_FORCE_CONNECT_UP = create("pillar_force_connect_up");
    public static final TagKey<Block> PILLAR_FORCE_CONNECT_DOWN = create("pillar_force_connect_down");
    public static final TagKey<Block> PILLAR_FORCE_CONNECT_SIDE = create("pillar_force_connect_side");
    public static final TagKey<Block> SEA_LANTERNS = createCommunity("sea_lanterns");
    public static final TagKey<Block> REINFORCED_SEA_LANTERNS = create("reinforced_sea_lanterns");
    public static final TagKey<Block> TABLES = createCommunity("tables");
    public static final TagKey<Block> CABINETS = createCommunity("cabinets");
    public static final TagKey<Block> TABLE_CONNECT = create("table_connect");
    public static final TagKey<Block> WOOL_SOFAS = create("wool_sofas");

    private static TagKey<Block> create(String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(FierceDecoration.MODID, name));
    }

    private static TagKey<Block> createCommunity(String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    private FDBlockTags() {}
}
