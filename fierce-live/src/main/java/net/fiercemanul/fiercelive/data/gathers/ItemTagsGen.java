package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.FLItems;
import net.fiercemanul.fiercelive.data.registries.BlockMaterial;
import net.fiercemanul.fiercelive.data.registries.BlockMaterialTag;
import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.fiercemanul.fiercelive.data.tags.FLItemTags;
import net.fiercemanul.fiercesource.data.tags.FSItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
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
        super(packOutput, lookupProvider, blockTagsGen, FierceLive.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.PLANKS).add(
                FLBlocks.SMOOTH_OAK_PLANKS.asItem(),
                FLBlocks.SMOOTH_SPRUCE_PLANKS.asItem(),
                FLBlocks.SMOOTH_BIRCH_PLANKS.asItem(),
                FLBlocks.SMOOTH_JUNGLE_PLANKS.asItem(),
                FLBlocks.SMOOTH_ACACIA_PLANKS.asItem(),
                FLBlocks.SMOOTH_DARK_OAK_PLANKS.asItem(),
                FLBlocks.SMOOTH_MANGROVE_PLANKS.asItem(),
                FLBlocks.SMOOTH_BAMBOO_PLANKS.asItem(),
                FLBlocks.SMOOTH_CHERRY_PLANKS.asItem(),
                FLBlocks.SMOOTH_CRIMSON_PLANKS.asItem(),
                FLBlocks.SMOOTH_WARPED_PLANKS.asItem()
        );
        tag(ItemTags.NON_FLAMMABLE_WOOD).add(
                FLBlocks.SMOOTH_CRIMSON_PLANKS.asItem(),
                FLBlocks.SMOOTH_WARPED_PLANKS.asItem()
        );
        tag(Tags.Items.SEEDS).add(
                FLItems.FOX_CARROT_SEED.get()
        );
        tag(Tags.Items.CHESTS).addTag(FLItemTags.CABINETS);
        this.copy(FLBlockTags.GLASS_LAMPS, FLItemTags.GLASS_LAMPS);
        this.copy(FLBlockTags.GUARDRAILS, FLItemTags.GUARDRAILS);
        this.copy(FLBlockTags.CROSS_HOLES, FLItemTags.CROSS_HOLES);
        this.copy(FLBlockTags.CUT_BLOCKS, FLItemTags.CUT_BLOCKS);
        this.copy(FLBlockTags.PILLARS, FLItemTags.PILLARS);
        this.copy(FLBlockTags.SEA_LANTERNS, FLItemTags.SEA_LANTERNS);
        this.copy(FLBlockTags.REINFORCED_SEA_LANTERNS, FLItemTags.REINFORCED_SEA_LANTERNS);
        this.copy(FLBlockTags.CABINETS, FLItemTags.CABINETS);
        this.copy(FLBlockTags.TABLES, FLItemTags.TABLES);
        this.copy(FLBlockTags.WOOL_SOFAS, FLItemTags.WOOL_SOFAS);
        this.copy(FLBlockTags.COLOR_LAMPS, FLItemTags.COLOR_LAMPS);
        this.copy(FLBlockTags.RAINBOW_DYED, FLItemTags.RAINBOW_DYED);
        tag(ItemTags.WOOL).add(FLBlocks.RAINBOW_WOOL.asItem()).addTag(FLItemTags.WOOL_SOFAS);
        tag(ItemTags.TERRACOTTA).add(FLBlocks.RAINBOW_TERRACOTTA.asItem());
        tag(Tags.Items.CONCRETES).add(FLBlocks.RAINBOW_CONCRETE.asItem());
        tag(Tags.Items.GLASS_BLOCKS).add(FLBlocks.RAINBOW_GLASS.asItem());
        tag(FLItemTags.RAINBOW_DYES).add(FLItems.RAINBOW_DYE.get());
        tag(Tags.Items.DYES).addTag(FLItemTags.RAINBOW_DYES);
        tag(Tags.Items.DYED_RED).add(FLBlocks.RED_LAMP.asItem());
        tag(Tags.Items.DYED_GREEN).add(FLBlocks.GREEN_LAMP.asItem());
        tag(Tags.Items.DYED_BLUE).add(FLBlocks.BLUE_LAMP.asItem());
        tag(Tags.Items.DYED_YELLOW).add(FLBlocks.YELLOW_LAMP.asItem());
        tag(Tags.Items.DYED_CYAN).add(FLBlocks.CYAN_LAMP.asItem());
        tag(Tags.Items.DYED_PURPLE).add(FLBlocks.PURPLE_LAMP.asItem());

        Item[] crowbarItem = new Item[] {
                FLItems.CROWBAR_ITEM.get(),
                FLItems.NETHERITE_CROWBAR_ITEM.get(),
                FLItems.CLAW_HAMMER_ITEM.get(),
                FLItems.NETHERITE_CLAW_HAMMER_ITEM.get()
        };
        tag(ItemTags.PICKAXES).add(crowbarItem);
        tag(ItemTags.AXES).add(crowbarItem);
        tag(FSItemTags.WRENCH_ITEM).add(crowbarItem);

        ROWS.forEach(consumer -> consumer.accept(this));

    }

    public void basicTags(BlockMaterial material, DeferredBlock<? extends Block> block) {
        if (material.hasTag(BlockMaterialTag.TAG_NON_FLAMMABLE_WOOD)) tag(ItemTags.NON_FLAMMABLE_WOOD, block);
        if (material.hasTag(BlockMaterialTag.TEXTURE_COLORED)) colorTags(material.mapColorHolder().top(), block);
    }

    public void colorTags(MapColor mapColor, DeferredBlock<? extends Block> block) {
        TagKey<Item> tag = null;
        if (mapColor == MapColor.SNOW || mapColor == MapColor.TERRACOTTA_WHITE) {
            if (block.getId().getPath().contains("rainbow")) tag = FLItemTags.RAINBOW_DYED;
            else tag = Tags.Items.DYED_WHITE;
        }
        else if (mapColor == MapColor.COLOR_ORANGE || mapColor == MapColor.TERRACOTTA_ORANGE) tag = Tags.Items.DYED_ORANGE;
        else if (mapColor == MapColor.COLOR_MAGENTA || mapColor == MapColor.TERRACOTTA_MAGENTA) tag = Tags.Items.DYED_MAGENTA;
        else if (mapColor == MapColor.COLOR_LIGHT_BLUE || mapColor == MapColor.TERRACOTTA_LIGHT_BLUE) tag = Tags.Items.DYED_LIGHT_BLUE;
        else if (mapColor == MapColor.COLOR_YELLOW || mapColor == MapColor.TERRACOTTA_YELLOW) tag = Tags.Items.DYED_YELLOW;
        else if (mapColor == MapColor.COLOR_LIGHT_GREEN || mapColor == MapColor.TERRACOTTA_LIGHT_GREEN) tag = Tags.Items.DYED_LIME;
        else if (mapColor == MapColor.COLOR_PINK || mapColor == MapColor.TERRACOTTA_PINK) tag = Tags.Items.DYED_PINK;
        else if (mapColor == MapColor.COLOR_GRAY || mapColor == MapColor.TERRACOTTA_GRAY) tag = Tags.Items.DYED_GRAY;
        else if (mapColor == MapColor.COLOR_LIGHT_GRAY || mapColor == MapColor.TERRACOTTA_LIGHT_GRAY) tag = Tags.Items.DYED_LIGHT_GRAY;
        else if (mapColor == MapColor.COLOR_CYAN || mapColor == MapColor.TERRACOTTA_CYAN) tag = Tags.Items.DYED_CYAN;
        else if (mapColor == MapColor.COLOR_PURPLE || mapColor == MapColor.TERRACOTTA_PURPLE) tag = Tags.Items.DYED_PURPLE;
        else if (mapColor == MapColor.COLOR_BLUE || mapColor == MapColor.TERRACOTTA_BLUE) tag = Tags.Items.DYED_BLUE;
        else if (mapColor == MapColor.COLOR_BROWN || mapColor == MapColor.TERRACOTTA_BROWN) tag = Tags.Items.DYED_BROWN;
        else if (mapColor == MapColor.COLOR_GREEN || mapColor == MapColor.TERRACOTTA_GREEN) tag = Tags.Items.DYED_GREEN;
        else if (mapColor == MapColor.COLOR_RED || mapColor == MapColor.TERRACOTTA_RED) tag = Tags.Items.DYED_RED;
        else if (mapColor == MapColor.COLOR_BLACK || mapColor == MapColor.TERRACOTTA_BLACK) tag = Tags.Items.DYED_BLACK;
        if (tag != null) tag(tag).add(block.asItem());
    }

    public void tag(TagKey<Item> tag, DeferredBlock<? extends Block> block) {
        tag(tag).add(block.asItem());
    }
}
