package net.fiercemanul.fiercesource.data.gathers;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.data.tags.FSBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.fiercemanul.fiercesource.data.FSBlocks.*;

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
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                CREATIVE_MANA_BLOCK.get(),
                CREATIVE_MANA_PROVIDER.get(),
                WORLD_LOCATOR.get()
        ).addTags(
                FSBlockTags.SOUL_CRYSTALS,
                FSBlockTags.HAO_STONES,
                FSBlockTags.DEEP_STONES
        );
        tag(FSBlockTags.SOUL_CRYSTALS).add(
                LARGE_SOUL_CRYSTAL.get(),
                MEDIUM_SOUL_CRYSTAL.get(),
                SMALL_SOUL_CRYSTAL.get(),
                LARGE_MANA_CRYSTAL.get(),
                MEDIUM_MANA_CRYSTAL.get(),
                SMALL_MANA_CRYSTAL.get()
        );
        tag(FSBlockTags.HAO_STONES).add(
                HAO_STONE.get(),
                POLISHED_HAO_STONE.get(),
                SMOOTH_HAO_STONE.get()
        );
        tag(FSBlockTags.DEEP_STONES).add(
                DEEP_STONE.get(),
                POLISHED_DEEP_STONE.get(),
                SMOOTH_DEEP_STONE.get()
        );
        tag(Tags.Blocks.STONES).add(
                HAO_STONE.get(),
                DEEP_STONE.get()
        );
    }
}
