package net.fiercemanul.fiercesource.data.tags;

import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class FSBlockTags {

    public static final TagKey<Block> SOUL_CRYSTALS = BlockTags.create(FSUtils.rl("soul_crystals"));
    public static final TagKey<Block> MINEABLE_WITH_CROWBAR = BlockTags.create(FSUtils.rl("mineable/crowbar"));
}
