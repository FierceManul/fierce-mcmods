package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.registries.FSBlocksAndItems;
import net.fiercemanul.fiercesource.tags.FSBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                FSBlocksAndItems.CREATIVE_MANA_BLOCK.getBlock(),
                FSBlocksAndItems.CREATIVE_MANA_OUTPUT.getBlock(),
                FSBlocksAndItems.WORLD_LOCATOR.getBlock()
        ).addTag(FSBlockTags.SOUL_CRYSTALS);
        tag(FSBlockTags.SOUL_CRYSTALS).add(
                FSBlocksAndItems.LARGE_SOUL_CRYSTAL.getBlock(),
                FSBlocksAndItems.MEDIUM_SOUL_CRYSTAL.getBlock(),
                FSBlocksAndItems.SMALL_SOUL_CRYSTAL.getBlock(),
                FSBlocksAndItems.LARGE_MANA_CRYSTAL.getBlock(),
                FSBlocksAndItems.MEDIUM_MANA_CRYSTAL.getBlock(),
                FSBlocksAndItems.SMALL_MANA_CRYSTAL.getBlock()
        );
    }
}
