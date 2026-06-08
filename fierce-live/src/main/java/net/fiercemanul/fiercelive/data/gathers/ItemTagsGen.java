package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.registries.BlockMaterial;
import net.fiercemanul.fiercelive.data.registries.BlockMaterialTag;
import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.fiercemanul.fiercelive.data.tags.FLItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.fiercemanul.fiercelive.data.FLItems.*;


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

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        copy(FLBlockTags.GLASS_LAMPS, FLItemTags.GLASS_LAMPS);
        copy(FLBlockTags.GUARDRAILS, FLItemTags.GUARDRAILS);
        copy(FLBlockTags.CROSS_HOLES, FLItemTags.CROSS_HOLES);
        copy(FLBlockTags.CUT_BLOCKS, FLItemTags.CUT_BLOCKS);
        copy(FLBlockTags.PILLARS, FLItemTags.PILLARS);
        copy(FLBlockTags.SEA_LANTERNS, FLItemTags.SEA_LANTERNS);
        copy(FLBlockTags.REINFORCED_SEA_LANTERNS, FLItemTags.REINFORCED_SEA_LANTERNS);
        copy(FLBlockTags.CABINETS, FLItemTags.CABINETS);
        copy(FLBlockTags.TABLES, FLItemTags.TABLES);
        copy(FLBlockTags.WOOL_SOFAS, FLItemTags.WOOL_SOFAS);
        copy(FLBlockTags.COLOR_LAMPS, FLItemTags.COLOR_LAMPS);
        copy(FLBlockTags.RAINBOW_DYED, FLItemTags.RAINBOW_DYED);
        copy(FLBlockTags.IRON_CORRIDORS, FLItemTags.IRON_CORRIDORS);
        copy(FLBlockTags.CHI_STONE_TECH, FLItemTags.CHI_STONE_TECH);

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
                FOX_CARROT_SEED.get()
        );
        tag(Tags.Items.CHESTS).addTag(FLItemTags.CABINETS);

        tag(ItemTags.WOOL).add(FLBlocks.RAINBOW_WOOL.asItem()).addTag(FLItemTags.WOOL_SOFAS);
        tag(ItemTags.TERRACOTTA).add(FLBlocks.RAINBOW_TERRACOTTA.asItem());
        tag(Tags.Items.CONCRETES).add(FLBlocks.RAINBOW_CONCRETE.asItem());
        tag(Tags.Items.GLASS_BLOCKS).add(FLBlocks.RAINBOW_GLASS.asItem());
        tag(Tags.Items.GLASS_PANES).add(FLBlocks.RAINBOW_GLASS_PANE.asItem());
        tag(FLItemTags.RAINBOW_DYES).add(RAINBOW_DYE.get());
        tag(Tags.Items.DYES).addTag(FLItemTags.RAINBOW_DYES);
        tag(Tags.Items.DYED).addTag(FLItemTags.RAINBOW_DYED);
        tag(Tags.Items.DYED_RED).add(FLBlocks.RED_LAMP.asItem());
        tag(Tags.Items.DYED_GREEN).add(FLBlocks.GREEN_LAMP.asItem());
        tag(Tags.Items.DYED_BLUE).add(FLBlocks.BLUE_LAMP.asItem());
        tag(Tags.Items.DYED_YELLOW).add(FLBlocks.YELLOW_LAMP.asItem());
        tag(Tags.Items.DYED_CYAN).add(FLBlocks.CYAN_LAMP.asItem());
        tag(Tags.Items.DYED_PURPLE).add(FLBlocks.PURPLE_LAMP.asItem());
        tag(Tags.Items.CONCRETES).add(
                FLBlocks.CONCRETE.asItem(),
                FLBlocks.GRAVEL_CONCRETE.asItem()
        );
        tag(Tags.Items.CONCRETE_POWDERS).add(
                FLBlocks.CONCRETE_POWDER.asItem(),
                FLBlocks.GRAVEL_CONCRETE_POWDER.asItem()
        );

        tag(FLItemTags.CROWBARS).add(
                CROWBAR.get(),
                NETHERITE_CROWBAR.get()
        );
        tag(FLItemTags.TOOLS_CROWBAR).addTag(FLItemTags.CROWBARS);
        tag(Tags.Items.TOOLS_WRENCH).add(PARROT.get()).addTag(FLItemTags.CROWBARS);

        tag(FLItemTags.METEOR_HAMMERS).add(
                METEOR_HAMMER.get(),
                NETHERITE_METEOR_HAMMER.get()
        );
        tag(FLItemTags.WEAPON_METEOR_HAMMER).addTag(FLItemTags.METEOR_HAMMERS);
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE).addTag(FLItemTags.WEAPON_METEOR_HAMMER);
        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE).addTag(FLItemTags.WEAPON_METEOR_HAMMER);

        tag(FLItemTags.DOOR_SHIELDS).add(
                WOODEN_DOOR.get(),
                IRON_DOOR.get()
        );
        tag(Tags.Items.TOOLS_SHIELD).addTag(FLItemTags.DOOR_SHIELDS);

        tag(Tags.Items.MELEE_WEAPON_TOOLS).add(
                PUFFERFISH_ROD.get(),
                SACABAMBASPIS.get()
        ).addTag(FLItemTags.WEAPON_METEOR_HAMMER);

        tag(ItemTags.DURABILITY_ENCHANTABLE).add(
                PUFFERFISH_ROD.get(),
                SACABAMBASPIS.get()
        ).addTags(
                FLItemTags.WEAPON_METEOR_HAMMER,
                FLItemTags.DOOR_SHIELDS
        );

        tag(ItemTags.VANISHING_ENCHANTABLE).add(
                PUFFERFISH_ROD.get(),
                SACABAMBASPIS.get()
        ).addTags(
                FLItemTags.WEAPON_METEOR_HAMMER,
                FLItemTags.DOOR_SHIELDS
        );

        tag(Tags.Items.MINING_TOOL_TOOLS).add(GLASS_KNIFE.get()).addTags(FLItemTags.CROWBARS);

        tag(ItemTags.ARROWS).add(GLOW_PEARL_ARROW.get());

        tag(FLItemTags.DEAD_CORAL_FANS).add(
                Items.DEAD_BRAIN_CORAL_FAN,
                Items.DEAD_BUBBLE_CORAL_FAN,
                Items.DEAD_FIRE_CORAL_FAN,
                Items.DEAD_HORN_CORAL_FAN,
                Items.DEAD_TUBE_CORAL_FAN
        );

        tag(FLItemTags.CHI_STONE_TECH).add(INFINITE_SNOWBALL.get());


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
