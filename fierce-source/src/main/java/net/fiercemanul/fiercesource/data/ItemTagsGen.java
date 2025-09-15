package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.data.tags.FSBlockTags;
import net.fiercemanul.fiercesource.data.tags.FSItemTags;
import net.fiercemanul.fiercesource.registries.FSBlocksAndItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
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
        Item[] crowbarItem = new Item[] {
                FSBlocksAndItems.CROWBAR_ITEM.get(),
                FSBlocksAndItems.NETHERITE_CROWBAR_ITEM.get(),
                FSBlocksAndItems.CLAW_HAMMER_ITEM.get(),
                FSBlocksAndItems.NETHERITE_CLAW_HAMMER_ITEM.get()
        };

        tag(ItemTags.PICKAXES).add(crowbarItem);
        tag(ItemTags.AXES).add(crowbarItem);
        tag(FSItemTags.WRENCH_ITEM).add(crowbarItem);

        copy(FSBlockTags.SOUL_CRYSTALS, FSItemTags.SOUL_CRYSTALS);
        tag(FSItemTags.SOUL_CRYSTALS).add(FSBlocksAndItems.SOUL_CRYSTAL_SHARD.getItem());
    }
}
