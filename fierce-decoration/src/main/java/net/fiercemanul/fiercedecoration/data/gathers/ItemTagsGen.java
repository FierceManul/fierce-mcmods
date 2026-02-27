package net.fiercemanul.fiercedecoration.data.gathers;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.data.registries.BlockMaterial;
import net.fiercemanul.fiercedecoration.data.registries.BlockMaterialTag;
import net.fiercemanul.fiercedecoration.tags.FDBlockTags;
import net.fiercemanul.fiercedecoration.tags.FDItemTags;
import net.fiercemanul.fiercedecoration.data.FDItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class ItemTagsGen extends ItemTagsProvider {


    public static final HashSet<Consumer<ItemTagsGen>> ROWS = new HashSet<>();
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
                FDItems.SMOOTH_OAK_PLANKS.get(),
                FDItems.SMOOTH_SPRUCE_PLANKS.get(),
                FDItems.SMOOTH_BIRCH_PLANKS.get(),
                FDItems.SMOOTH_JUNGLE_PLANKS.get(),
                FDItems.SMOOTH_ACACIA_PLANKS.get(),
                FDItems.SMOOTH_DARK_OAK_PLANKS.get(),
                FDItems.SMOOTH_MANGROVE_PLANKS.get(),
                FDItems.SMOOTH_BAMBOO_PLANKS.get(),
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
        this.copy(FDBlockTags.GLASS_LAMPS, FDItemTags.GLASS_LAMPS);
        this.copy(FDBlockTags.GUARDRAILS, FDItemTags.GUARDRAILS);
        this.copy(FDBlockTags.CROSS_HOLES, FDItemTags.CROSS_HOLES);
        this.copy(FDBlockTags.CUT_BLOCKS, FDItemTags.CUT_BLOCKS);
        this.copy(FDBlockTags.PILLARS, FDItemTags.PILLARS);
        this.copy(FDBlockTags.SEA_LANTERNS, FDItemTags.SEA_LANTERNS);
        this.copy(FDBlockTags.REINFORCED_SEA_LANTERNS, FDItemTags.REINFORCED_SEA_LANTERNS);
        this.copy(FDBlockTags.CABINETS, FDItemTags.CABINETS);
        this.copy(FDBlockTags.TABLES, FDItemTags.TABLES);
        this.copy(FDBlockTags.WOOL_SOFAS, FDItemTags.WOOL_SOFAS);

        ROWS.forEach(consumer -> consumer.accept(this));

    }

    public void basicTags(BlockMaterial material, DeferredBlock<? extends Block> block) {
        if (material.hasTag(BlockMaterialTag.TAG_NON_FLAMMABLE_WOOD)) tag(block, ItemTags.NON_FLAMMABLE_WOOD);
    }

    public void tag(DeferredBlock<? extends Block> block, TagKey<Item> tag) {
        tag(tag).add(block.asItem());
    }
}
