package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.tags.FSBlockTags;
import net.fiercemanul.fiercesource.tags.FSItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagsGen extends ItemTagsProvider {


    public ItemTagsGen(
            PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagsProvider.TagLookup<Block>> blockTagsGen,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(packOutput, lookupProvider, blockTagsGen, FierceSource.FC_MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        copy(FSBlockTags.SOUL_CRYSTALS, FSItemTags.SOUL_CRYSTALS);
        tag(FSItemTags.SOUL_CRYSTALS).add(FierceSource.SOUL_CRYSTAL_SHARD_ITEM.get());
        tag(FSItemTags.WRENCH_ITEM).add(
                FierceSource.CROWBAR_ITEM.get(),
                FierceSource.NETHERITE_CROWBAR_ITEM.get(),
                FierceSource.CLAW_HAMMER_ITEM.get(),
                FierceSource.NETHERITE_CLAW_HAMMER_ITEM.get()
        );
    }
}
