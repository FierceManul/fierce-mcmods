package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
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
                FierceSource.CREATIVE_MANA_BLOCK.get(),
                FierceSource.CREATIVE_MANA_OUTPUT_BLOCK.get(),
                FierceSource.WORLD_LOCATOR_BLOCK.get()
        ).addTag(FSBlockTags.SOUL_CRYSTALS);
        tag(FSBlockTags.SOUL_CRYSTALS).add(
                FierceSource.LARGE_SOUL_CRYSTAL_BLOCK.get(),
                FierceSource.MEDIUM_SOUL_CRYSTAL_BLOCK.get(),
                FierceSource.SMALL_SOUL_CRYSTAL_BLOCK.get(),
                FierceSource.LARGE_MANA_CRYSTAL_BLOCK.get(),
                FierceSource.MEDIUM_MANA_CRYSTAL_BLOCK.get(),
                FierceSource.SMALL_MANA_CRYSTAL_BLOCK.get()
        );
    }
}
