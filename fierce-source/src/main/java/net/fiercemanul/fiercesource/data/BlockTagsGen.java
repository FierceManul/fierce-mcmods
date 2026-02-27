package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.data.registries.FSBlocks;
import net.fiercemanul.fiercesource.data.tags.FSBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagsGen extends BlockTagsProvider {


    public BlockTagsGen(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, FierceSource.FC_MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                FSBlocks.CREATIVE_MANA_BLOCK.getBlock(),
                FSBlocks.CREATIVE_MANA_OUTPUT.getBlock(),
                FSBlocks.WORLD_LOCATOR.getBlock()
        ).addTags(
                FSBlockTags.SOUL_CRYSTALS,
                FSBlockTags.HAO_STONES,
                FSBlockTags.DEEP_STONES
        );
        tag(FSBlockTags.SOUL_CRYSTALS).add(
                FSBlocks.LARGE_SOUL_CRYSTAL.getBlock(),
                FSBlocks.MEDIUM_SOUL_CRYSTAL.getBlock(),
                FSBlocks.SMALL_SOUL_CRYSTAL.getBlock(),
                FSBlocks.LARGE_MANA_CRYSTAL.getBlock(),
                FSBlocks.MEDIUM_MANA_CRYSTAL.getBlock(),
                FSBlocks.SMALL_MANA_CRYSTAL.getBlock()
        );
        tag(FSBlockTags.HAO_STONES).add(
                FSBlocks.HAO_STONE.getBlock(),
                FSBlocks.POLISHED_HAO_STONE.getBlock(),
                FSBlocks.SMOOTH_HAO_STONE.getBlock()
        );
        tag(FSBlockTags.DEEP_STONES).add(
                FSBlocks.DEEP_STONE.getBlock(),
                FSBlocks.POLISHED_DEEP_STONE.getBlock(),
                FSBlocks.SMOOTH_DEEP_STONE.getBlock()
        );
        tag(Tags.Blocks.STONES).add(
                FSBlocks.HAO_STONE.getBlock(),
                FSBlocks.DEEP_STONE.getBlock()
        );
        tag(FSBlockTags.MINEABLE_WITH_CROWBAR).addTags(
                BlockTags.MINEABLE_WITH_PICKAXE,
                BlockTags.MINEABLE_WITH_AXE
        );
    }
}
