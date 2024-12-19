package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.tags.FDBlockTags;
import net.fiercemanul.fiercedecoration.tags.FDItemTags;
import net.fiercemanul.fiercedecoration.world.item.FDItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
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
        super(packOutput, lookupProvider, blockTagsGen, FierceDecoration.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.PLANKS).add(
                FDItems.SMOOTH_DARK_OAK_PLANKS.get(),
                FDItems.SMOOTH_SPRUCE_PLANKS.get(),
                FDItems.SMOOTH_BIRCH_PLANKS.get(),
                FDItems.SMOOTH_JUNGLE_PLANKS.get(),
                FDItems.SMOOTH_ACACIA_PLANKS.get(),
                FDItems.SMOOTH_DARK_OAK_PLANKS.get(),
                FDItems.SMOOTH_MANGROVE_PLANKS.get(),
                FDItems.SMOOTH_CHERRY_PLANKS.get(),
                FDItems.SMOOTH_CRIMSON_PLANKS.get(),
                FDItems.SMOOTH_WARPED_PLANKS.get()
        );
        tag(ItemTags.NON_FLAMMABLE_WOOD).add(
                FDItems.SMOOTH_CRIMSON_PLANKS.get(),
                FDItems.SMOOTH_WARPED_PLANKS.get()
        );
        tag(Tags.Items.SEEDS).add(
                FDItems.FOX_CARROT_SEED.get()
        );
        tag(Tags.Items.CHESTS).addTag(FDItemTags.CABINETS);
        this.copy(FDBlockTags.LAMP_IN_GLASS, FDItemTags.LAMP_IN_GLASS);
        this.copy(FDBlockTags.GUARDRAILS_TAG, FDItemTags.GUARDRAILS_TAG);
        this.copy(FDBlockTags.PEEP_WINDOWS_TAG, FDItemTags.PEEP_WINDOWS_TAG);
        this.copy(FDBlockTags.CUT_BLOCKS_TAG, FDItemTags.CUT_BLOCKS_TAG);
        this.copy(FDBlockTags.PILLAR_TAG, FDItemTags.PILLAR_TAG);
        this.copy(FDBlockTags.SEA_LANTERN_TAG, FDItemTags.SEA_LANTERN_TAG);
        this.copy(FDBlockTags.CABINETS, FDItemTags.CABINETS);
        this.copy(FDBlockTags.TABLES, FDItemTags.TABLES);
        DataGen.BLOCKS_AND_MATERIALS.forEach((deferredBlock, blockMaterial) -> {
            Item item = deferredBlock.asItem();
            if (blockMaterial.isNonFlammableWood()) tag(ItemTags.NON_FLAMMABLE_WOOD).add(item);
        });
    }
}
