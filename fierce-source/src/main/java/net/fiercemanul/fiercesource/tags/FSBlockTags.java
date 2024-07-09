package net.fiercemanul.fiercesource.tags;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class FSBlockTags {

    public static final TagKey<Block> SOUL_CRYSTALS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "soul_crystals"));
}
