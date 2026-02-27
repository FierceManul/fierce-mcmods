package net.fiercemanul.fiercedecoration.data.registries;

import net.fiercemanul.fiercedecoration.data.FDBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public final class BlockMaterials {


    public static final HashSet<BlockMaterialTag> LOG_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> STRIPPED_LOG_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> PLANK_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> SMOOTH_PLANK_TAGS = new HashSet<>();
    public static final BlockMaterial OAK_LOG = mc(Blocks.OAK_LOG, MapColorHolder.pillar(MapColor.WOOD, MapColor.PODZOL), LOG_TAGS);
    public static final BlockMaterial STRIPPED_OAK_LOG = mc(Blocks.STRIPPED_OAK_LOG, MapColorHolders.WOOD, STRIPPED_LOG_TAGS);
    public static final BlockMaterial OAK_PLANKS = mcPlanks(Blocks.OAK_PLANKS, MapColorHolders.WOOD, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_OAK_PLANKS = regPlanks(FDBlocks.SMOOTH_OAK_PLANKS, MapColorHolders.WOOD, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial SPRUCE_LOG = mc(Blocks.SPRUCE_LOG, MapColorHolder.pillar(MapColor.PODZOL, MapColor.COLOR_BROWN), LOG_TAGS);
    public static final BlockMaterial STRIPPED_SPRUCE_LOG = mc(Blocks.STRIPPED_SPRUCE_LOG, MapColorHolders.PODZOL, STRIPPED_LOG_TAGS);
    public static final BlockMaterial SPRUCE_PLANKS = mcPlanks(Blocks.SPRUCE_PLANKS, MapColorHolders.PODZOL, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_SPRUCE_PLANKS = regPlanks(FDBlocks.SMOOTH_SPRUCE_PLANKS, MapColorHolders.PODZOL, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial BIRCH_LOG = mc(Blocks.BIRCH_LOG, MapColorHolder.pillar(MapColor.SAND, MapColor.QUARTZ), LOG_TAGS);
    public static final BlockMaterial STRIPPED_BIRCH_LOG = mc(Blocks.STRIPPED_BIRCH_LOG, MapColorHolders.SAND, STRIPPED_LOG_TAGS);
    public static final BlockMaterial BIRCH_PLANKS = mcPlanks(Blocks.BIRCH_PLANKS, MapColorHolders.SAND, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_BIRCH_PLANKS = regPlanks(FDBlocks.SMOOTH_BIRCH_PLANKS, MapColorHolders.SAND, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial JUNGLE_LOG = mc(Blocks.JUNGLE_LOG, MapColorHolder.pillar(MapColor.DIRT, MapColor.PODZOL), LOG_TAGS);
    public static final BlockMaterial STRIPPED_JUNGLE_LOG = mc(Blocks.STRIPPED_JUNGLE_LOG, MapColorHolders.DIRT, STRIPPED_LOG_TAGS);
    public static final BlockMaterial JUNGLE_PLANKS = mcPlanks(Blocks.JUNGLE_PLANKS, MapColorHolders.DIRT, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_JUNGLE_PLANKS = regPlanks(FDBlocks.SMOOTH_JUNGLE_PLANKS, MapColorHolders.DIRT, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial ACACIA_LOG = mc(Blocks.ACACIA_LOG, MapColorHolder.pillar(MapColor.COLOR_ORANGE, MapColor.STONE), LOG_TAGS);
    public static final BlockMaterial STRIPPED_ACACIA_LOG = mc(Blocks.STRIPPED_ACACIA_LOG, MapColorHolders.COLOR_ORANGE, STRIPPED_LOG_TAGS);
    public static final BlockMaterial ACACIA_PLANKS = mcPlanks(Blocks.ACACIA_PLANKS, MapColorHolders.COLOR_ORANGE, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_ACACIA_PLANKS = regPlanks(FDBlocks.SMOOTH_ACACIA_PLANKS, MapColorHolders.COLOR_ORANGE, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial DARK_OAK_LOG = mc(Blocks.DARK_OAK_LOG, MapColorHolders.COLOR_BROWN, LOG_TAGS);
    public static final BlockMaterial STRIPPED_DARK_OAK_LOG = mc(Blocks.STRIPPED_DARK_OAK_LOG, MapColorHolders.COLOR_BROWN, LOG_TAGS);
    public static final BlockMaterial DARK_OAK_PLANKS = mcPlanks(Blocks.DARK_OAK_PLANKS, MapColorHolders.COLOR_BROWN, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_DARK_OAK_PLANKS = regPlanks(FDBlocks.SMOOTH_DARK_OAK_PLANKS, MapColorHolders.COLOR_BROWN, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial MANGROVE_LOG = mc(Blocks.MANGROVE_LOG, MapColorHolder.pillar(MapColor.COLOR_RED, MapColor.PODZOL), LOG_TAGS);
    public static final BlockMaterial STRIPPED_MANGROVE_LOG = mc(Blocks.STRIPPED_MANGROVE_LOG, MapColorHolders.COLOR_RED, STRIPPED_LOG_TAGS);
    public static final BlockMaterial MANGROVE_PLANKS = mcPlanks(Blocks.MANGROVE_PLANKS, MapColorHolders.COLOR_RED, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_MANGROVE_PLANKS = regPlanks(FDBlocks.SMOOTH_MANGROVE_PLANKS, MapColorHolders.COLOR_RED, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial BAMBOO_BLOCK = mc(Blocks.BAMBOO_BLOCK, MapColorHolder.pillar(MapColor.COLOR_YELLOW, MapColor.PLANT), LOG_TAGS);
    public static final BlockMaterial STRIPPED_BAMBOO_BLOCK = mc(Blocks.STRIPPED_BAMBOO_BLOCK, MapColorHolders.COLOR_YELLOW, STRIPPED_LOG_TAGS);
    public static final BlockMaterial BAMBOO_PLANKS = mcPlanks(Blocks.BAMBOO_PLANKS, MapColorHolders.COLOR_YELLOW, PLANK_TAGS);
    public static final BlockMaterial BAMBOO_MOSAIC = mc(Blocks.BAMBOO_MOSAIC, MapColorHolders.COLOR_YELLOW, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_BAMBOO_PLANKS = regPlanks(FDBlocks.SMOOTH_BAMBOO_PLANKS, MapColorHolders.COLOR_YELLOW, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial CHERRY_LOG = mc(Blocks.CHERRY_LOG, MapColorHolder.pillar(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY), LOG_TAGS);
    public static final BlockMaterial STRIPPED_CHERRY_LOG = mc(Blocks.STRIPPED_CHERRY_LOG, MapColorHolder.pillar(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_PINK), LOG_TAGS);
    public static final BlockMaterial CHERRY_PLANKS = mcPlanks(Blocks.CHERRY_PLANKS, MapColorHolders.TERRACOTTA_WHITE, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_CHERRY_PLANKS = regPlanks(FDBlocks.SMOOTH_CHERRY_PLANKS, MapColorHolders.TERRACOTTA_WHITE, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial CRIMSON_STEM = mc(Blocks.CRIMSON_STEM, MapColorHolders.CRIMSON_STEM, LOG_TAGS);
    public static final BlockMaterial STRIPPED_CRIMSON_STEM = mc(Blocks.STRIPPED_CRIMSON_STEM, MapColorHolders.CRIMSON_STEM, LOG_TAGS);
    public static final BlockMaterial CRIMSON_PLANKS = mcPlanks(Blocks.CRIMSON_PLANKS, MapColorHolders.CRIMSON_STEM, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_CRIMSON_PLANKS = regPlanks(FDBlocks.SMOOTH_CRIMSON_PLANKS, MapColorHolders.CRIMSON_STEM, SMOOTH_PLANK_TAGS);
    public static final BlockMaterial WARPED_STEM = mc(Blocks.WARPED_STEM, MapColorHolders.WARPED_STEM, LOG_TAGS);
    public static final BlockMaterial STRIPPED_WARPED_STEM = mc(Blocks.STRIPPED_WARPED_STEM, MapColorHolders.WARPED_STEM, STRIPPED_LOG_TAGS);
    public static final BlockMaterial WARPED_PLANKS = mcPlanks(Blocks.WARPED_PLANKS, MapColorHolders.WARPED_STEM, PLANK_TAGS);
    public static final BlockMaterial SMOOTH_WARPED_PLANKS = regPlanks(FDBlocks.SMOOTH_WARPED_PLANKS, MapColorHolders.WARPED_STEM, SMOOTH_PLANK_TAGS);

    public static final HashSet<BlockMaterialTag> DIRT_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> PODZOL_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> PILLAR_DIRT_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> SNOW_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> MOSSY_TAGS = new HashSet<>();
    public static final BlockMaterial DIRT = mc(Blocks.DIRT, MapColorHolders.DIRT, DIRT_TAGS);
    public static final BlockMaterial COARSE_DIRT = mc(Blocks.COARSE_DIRT, MapColorHolders.DIRT, DIRT_TAGS);
    public static final BlockMaterial ROOTED_DIRT = mc(Blocks.ROOTED_DIRT, MapColorHolders.DIRT, DIRT_TAGS);
    public static final BlockMaterial PODZOL = mc(Blocks.PODZOL, MapColorHolders.PODZOL, PODZOL_TAGS);
    public static final BlockMaterial MYCELIUM = mc(Blocks.MYCELIUM, MapColorHolders.COLOR_PURPLE, PODZOL_TAGS);
    public static final BlockMaterial CLAY = mc(Blocks.CLAY, MapColorHolders.CLAY, DIRT_TAGS);
    public static final BlockMaterial SOUL_SOIL = mc(Blocks.SOUL_SOIL, MapColorHolders.COLOR_BROWN, DIRT_TAGS);
    public static final BlockMaterial SNOW_BLOCK = mc(Blocks.SNOW_BLOCK, MapColorHolders.SNOW, SNOW_TAGS);
    public static final BlockMaterial MOSS_BLOCK = mc(Blocks.MOSS_BLOCK, MapColorHolders.COLOR_GREEN, MOSSY_TAGS);
    //public static final BlockMaterial MUSHROOM_STEM = mc(Blocks.MUSHROOM_STEM, MapColorHolders.DIRT, MOSSY_TAGS);
    //public static final BlockMaterial BROWN_MUSHROOM_BLOCK = mc(Blocks.BROWN_MUSHROOM_BLOCK, MapColorHolders.DIRT, MOSSY_TAGS);
    //public static final BlockMaterial RED_MUSHROOM_BLOCK = mc(Blocks.RED_MUSHROOM_BLOCK, MapColorHolders.COLOR_RED, MOSSY_TAGS);
    public static final BlockMaterial NETHER_WART_BLOCK = mc(Blocks.NETHER_WART_BLOCK, MapColorHolders.COLOR_RED, MOSSY_TAGS);
    public static final BlockMaterial WARPED_WART_BLOCK = mc(Blocks.WARPED_WART_BLOCK, MapColorHolders.WARPED_WART_BLOCK, MOSSY_TAGS);
    public static final BlockMaterial SCULK = mc(Blocks.SCULK, MapColorHolders.COLOR_BLACK, MOSSY_TAGS);
    public static final HashSet<BlockMaterialTag> NYLIUM_TAGS = new HashSet<>();
    public static final BlockMaterial CRIMSON_NYLIUM = mc(Blocks.CRIMSON_NYLIUM, MapColorHolders.CRIMSON_NYLIUM, NYLIUM_TAGS);
    public static final BlockMaterial WARPED_NYLIUM = mc(Blocks.WARPED_NYLIUM, MapColorHolders.WARPED_NYLIUM, NYLIUM_TAGS);

    public static final HashSet<BlockMaterialTag> STONE_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> OBSIDIAN_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> END_STONE_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> POLISHED_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> CHISELED_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> BRICK_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> END_STONE_BRICK_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> CRACKED_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> SMOOTH_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> NATURE_PILLAR_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> DEEPSLATE_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> PILLAR_TAGS = new HashSet<>();
    public static final BlockMaterial PACKED_MUD = mc(Blocks.PACKED_MUD, MapColorHolders.DIRT, STONE_TAGS);
    public static final BlockMaterial MUD_BRICKS = mc(Blocks.MUD_BRICKS, MapColorHolders.TERRACOTTA_LIGHT_GRAY, BRICK_TAGS);
    public static final BlockMaterial STONE = mc(Blocks.STONE, MapColorHolders.STONE, STONE_TAGS);
    public static final BlockMaterial STONE_BRICKS = mc(Blocks.STONE_BRICKS, MapColorHolders.STONE, BRICK_TAGS);
    public static final BlockMaterial MOSSY_STONE_BRICKS = mc(Blocks.MOSSY_STONE_BRICKS, MapColorHolders.STONE, BRICK_TAGS);
    public static final BlockMaterial CHISELED_STONE_BRICKS = mc(Blocks.CHISELED_STONE_BRICKS, MapColorHolders.STONE, CHISELED_TAGS);
    public static final BlockMaterial SMOOTH_STONE = mc(Blocks.SMOOTH_STONE, MapColorHolders.STONE, POLISHED_TAGS);
    public static final BlockMaterial CRACKED_STONE_BRICKS = mc(Blocks.CRACKED_STONE_BRICKS, MapColorHolders.STONE, CRACKED_TAGS);
    public static final BlockMaterial COBBLESTONE = mc(Blocks.COBBLESTONE, MapColorHolders.STONE, STONE_TAGS);
    public static final BlockMaterial MOSSY_COBBLESTONE = mc(Blocks.MOSSY_COBBLESTONE, MapColorHolders.STONE, STONE_TAGS);
    public static final BlockMaterial DEEPSLATE = mc(Blocks.DEEPSLATE, MapColorHolders.DEEPSLATE, DEEPSLATE_TAGS);
    public static final BlockMaterial COBBLED_DEEPSLATE = mc(Blocks.COBBLED_DEEPSLATE, MapColorHolders.DEEPSLATE, STONE_TAGS);
    public static final BlockMaterial POLISHED_DEEPSLATE = mc(Blocks.POLISHED_DEEPSLATE, MapColorHolders.DEEPSLATE, POLISHED_TAGS);
    public static final BlockMaterial DEEPSLATE_BRICKS = mc(Blocks.DEEPSLATE_BRICKS, MapColorHolders.DEEPSLATE, BRICK_TAGS);
    public static final BlockMaterial CRACKED_DEEPSLATE_BRICKS = mc(Blocks.CRACKED_DEEPSLATE_BRICKS, MapColorHolders.DEEPSLATE, CRACKED_TAGS);
    public static final BlockMaterial DEEPSLATE_TILES = mc(Blocks.DEEPSLATE_TILES, MapColorHolders.DEEPSLATE, BRICK_TAGS);
    public static final BlockMaterial CRACKED_DEEPSLATE_TILES = mc(Blocks.CRACKED_DEEPSLATE_TILES, MapColorHolders.DEEPSLATE, CRACKED_TAGS);
    public static final BlockMaterial CHISELED_DEEPSLATE = mc(Blocks.CHISELED_DEEPSLATE, MapColorHolders.DEEPSLATE, CHISELED_TAGS);
    public static final BlockMaterial GRANITE = mc(Blocks.GRANITE, MapColorHolders.DIRT, STONE_TAGS);
    public static final BlockMaterial POLISHED_GRANITE = mc(Blocks.POLISHED_GRANITE, MapColorHolders.DIRT, POLISHED_TAGS);
    public static final BlockMaterial DIORITE = mc(Blocks.DIORITE, MapColorHolders.QUARTZ, STONE_TAGS);
    public static final BlockMaterial POLISHED_DIORITE = mc(Blocks.POLISHED_DIORITE, MapColorHolders.QUARTZ, POLISHED_TAGS);
    public static final BlockMaterial ANDESITE = mc(Blocks.ANDESITE, MapColorHolders.STONE, STONE_TAGS);
    public static final BlockMaterial POLISHED_ANDESITE = mc(Blocks.POLISHED_ANDESITE, MapColorHolders.STONE, POLISHED_TAGS);
    public static final BlockMaterial TUFF = mc(Blocks.TUFF, MapColorHolders.TERRACOTTA_GRAY, STONE_TAGS);
    public static final BlockMaterial CALCITE = mc(Blocks.CALCITE, MapColorHolders.TERRACOTTA_WHITE, STONE_TAGS);
    //public static final BlockMaterial DRIPSTONE_BLOCK = mc(Blocks.DRIPSTONE_BLOCK, MapColorHolders.TERRACOTTA_BROWN, STONE_TAGS);
    //public static final BlockMaterial AMETHYST_BLOCK = mc(Blocks.AMETHYST_BLOCK, MapColorHolders.COLOR_PURPLE, STONE_TAGS);
    public static final BlockMaterial OBSIDIAN = mc(Blocks.OBSIDIAN, MapColorHolders.COLOR_BLACK, OBSIDIAN_TAGS);
    public static final BlockMaterial CRYING_OBSIDIAN = mc(Blocks.CRYING_OBSIDIAN, MapColorHolders.COLOR_BLACK, OBSIDIAN_TAGS);
    public static final BlockMaterial BRICKS = mc(Blocks.BRICKS, MapColorHolders.COLOR_RED, BRICK_TAGS);
    public static final HashSet<BlockMaterialTag> SANDSTONE_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> CHISELED_SANDSTONE_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> SMOOTH_SANDSTONE_TAGS = new HashSet<>();
    public static final BlockMaterial SANDSTONE = mc(Blocks.SANDSTONE, MapColorHolders.SAND, SANDSTONE_TAGS);
    public static final BlockMaterial CUT_SANDSTONE = mc(Blocks.CUT_SANDSTONE, MapColorHolders.SAND, CHISELED_SANDSTONE_TAGS);
    public static final BlockMaterial CHISELED_SANDSTONE = mc(Blocks.CHISELED_SANDSTONE, MapColorHolders.SAND, CHISELED_SANDSTONE_TAGS);
    public static final BlockMaterial SMOOTH_SANDSTONE = mc(Blocks.SMOOTH_SANDSTONE, MapColorHolders.SAND, SMOOTH_SANDSTONE_TAGS);
    public static final BlockMaterial RED_SANDSTONE = mc(Blocks.RED_SANDSTONE, MapColorHolders.COLOR_ORANGE, SANDSTONE_TAGS);
    public static final BlockMaterial CUT_RED_SANDSTONE = mc(Blocks.CUT_RED_SANDSTONE, MapColorHolders.COLOR_ORANGE, CHISELED_SANDSTONE_TAGS);
    public static final BlockMaterial CHISELED_RED_SANDSTONE = mc(Blocks.CHISELED_RED_SANDSTONE, MapColorHolders.COLOR_ORANGE, CHISELED_SANDSTONE_TAGS);
    public static final BlockMaterial SMOOTH_RED_SANDSTONE = mc(Blocks.SMOOTH_RED_SANDSTONE, MapColorHolders.COLOR_ORANGE, SMOOTH_SANDSTONE_TAGS);
    public static final BlockMaterial COPPER_BLOCK = mc(Blocks.COPPER_BLOCK, MapColorHolders.COLOR_ORANGE, POLISHED_TAGS);
    //public static final BlockMaterial BONE_BLOCK = mc(Blocks.BONE_BLOCK, MapColorHolders.SAND, PILLAR_TAGS);
    public static final BlockMaterial PRISMARINE = mc(Blocks.PRISMARINE, MapColorHolders.COLOR_CYAN, STONE_TAGS);
    public static final BlockMaterial PRISMARINE_BRICKS = mc(Blocks.PRISMARINE_BRICKS, MapColorHolders.DIAMOND, BRICK_TAGS);
    public static final BlockMaterial DARK_PRISMARINE = mc(Blocks.DARK_PRISMARINE, MapColorHolders.DIAMOND, STONE_TAGS);
    //public static final HashSet<BlockMaterialTag> ICE_TAGS = new HashSet<>();
    //public static final BlockMaterial ICE = mc(Blocks.ICE, MapColorHolders.ICE, ICE_TAGS);
    public static final BlockMaterial PACKED_ICE = mc(Blocks.PACKED_ICE, MapColorHolders.ICE, STONE_TAGS);
    public static final BlockMaterial BLUE_ICE = mc(Blocks.BLUE_ICE, MapColorHolders.ICE, STONE_TAGS);
    public static final BlockMaterial NETHERRACK = mc(Blocks.NETHERRACK, MapColorHolders.NETHER, STONE_TAGS);
    public static final BlockMaterial NETHER_BRICKS = mc(Blocks.NETHER_BRICKS, MapColorHolders.NETHER, BRICK_TAGS);
    public static final BlockMaterial CHISELED_NETHER_BRICKS = mc(Blocks.CHISELED_NETHER_BRICKS, MapColorHolders.NETHER, CHISELED_TAGS);
    public static final BlockMaterial CRACKED_NETHER_BRICKS = mc(Blocks.CRACKED_NETHER_BRICKS, MapColorHolders.NETHER, CRACKED_TAGS);
    public static final BlockMaterial RED_NETHER_BRICKS = mc(Blocks.RED_NETHER_BRICKS, MapColorHolders.NETHER, BRICK_TAGS);
    public static final BlockMaterial BLACKSTONE = mc(Blocks.BLACKSTONE, MapColorHolders.COLOR_BLACK, STONE_TAGS);
    public static final BlockMaterial POLISHED_BLACKSTONE = mc(Blocks.POLISHED_BLACKSTONE, MapColorHolders.COLOR_BLACK, POLISHED_TAGS);
    public static final BlockMaterial POLISHED_BLACKSTONE_BRICKS = mc(Blocks.POLISHED_BLACKSTONE_BRICKS, MapColorHolders.COLOR_BLACK, BRICK_TAGS);
    public static final BlockMaterial CRACKED_POLISHED_BLACKSTONE_BRICKS = mc(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, MapColorHolders.COLOR_BLACK, CRACKED_TAGS);
    public static final BlockMaterial BASALT = mc(Blocks.BASALT, MapColorHolders.COLOR_BLACK, NATURE_PILLAR_TAGS);
    public static final BlockMaterial POLISHED_BASALT = mc(Blocks.POLISHED_BASALT, MapColorHolders.COLOR_BLACK, PILLAR_TAGS);
    public static final BlockMaterial SMOOTH_BASALT = mc(Blocks.SMOOTH_BASALT, MapColorHolders.COLOR_BLACK, STONE_TAGS);
    //public static final HashSet<BlockMaterialTag> MAGMA_TAGS = new HashSet<>();
    //public static final BlockMaterial MAGMA = mc(Blocks.MAGMA_BLOCK, MapColorHolders.NETHER, MAGMA_TAGS);
    public static final HashSet<BlockMaterialTag> QUARTZ_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> CHISELED_QUARTZ_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> SMOOTH_QUARTZ_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> LOG_STONE_TAGS = new HashSet<>();
    public static final BlockMaterial QUARTZ_BLOCK = mc(Blocks.QUARTZ_BLOCK, MapColorHolders.QUARTZ, QUARTZ_TAGS);
    public static final BlockMaterial CHISELED_QUARTZ_BLOCK = mc(Blocks.CHISELED_QUARTZ_BLOCK, MapColorHolders.QUARTZ, CHISELED_QUARTZ_TAGS);
    public static final BlockMaterial QUARTZ_PILLAR = mc(Blocks.QUARTZ_PILLAR, MapColorHolders.QUARTZ, LOG_STONE_TAGS);
    public static final BlockMaterial SMOOTH_QUARTZ = mc(Blocks.SMOOTH_QUARTZ, MapColorHolders.QUARTZ, SMOOTH_QUARTZ_TAGS);
    public static final BlockMaterial QUARTZ_BRICKS = mc(Blocks.QUARTZ_BRICKS, MapColorHolders.QUARTZ, BRICK_TAGS);
    public static final BlockMaterial END_STONE = mc(Blocks.END_STONE, MapColorHolders.SAND, STONE_TAGS);
    public static final BlockMaterial END_STONE_BRICKS = mc(Blocks.END_STONE_BRICKS, MapColorHolders.SAND, BRICK_TAGS);
    public static final BlockMaterial PURPUR_BLOCK = mc(Blocks.PURPUR_BLOCK, MapColorHolders.COLOR_MAGENTA, BRICK_TAGS);
    public static final BlockMaterial PURPUR_PILLAR = mc(Blocks.PURPUR_PILLAR, MapColorHolders.COLOR_MAGENTA, LOG_STONE_TAGS);
    public static final HashSet<BlockMaterialTag> COLOR_STONE_TAGS = new HashSet<>();
    public static final BlockMaterial TERRACOTTA = mc(Blocks.TERRACOTTA, MapColorHolders.COLOR_ORANGE, STONE_TAGS);
    public static final BlockMaterial WHITE_TERRACOTTA = mc(Blocks.WHITE_TERRACOTTA, MapColorHolders.TERRACOTTA_WHITE, COLOR_STONE_TAGS);
    public static final BlockMaterial ORANGE_TERRACOTTA = mc(Blocks.ORANGE_TERRACOTTA, MapColorHolders.TERRACOTTA_ORANGE, COLOR_STONE_TAGS);
    public static final BlockMaterial MAGENTA_TERRACOTTA = mc(Blocks.MAGENTA_TERRACOTTA, MapColorHolders.TERRACOTTA_MAGENTA, COLOR_STONE_TAGS);
    public static final BlockMaterial LIGHT_BLUE_TERRACOTTA = mc(Blocks.LIGHT_BLUE_TERRACOTTA, MapColorHolders.TERRACOTTA_LIGHT_BLUE, COLOR_STONE_TAGS);
    public static final BlockMaterial YELLOW_TERRACOTTA = mc(Blocks.YELLOW_TERRACOTTA, MapColorHolders.TERRACOTTA_YELLOW, COLOR_STONE_TAGS);
    public static final BlockMaterial LIME_TERRACOTTA = mc(Blocks.LIME_TERRACOTTA, MapColorHolders.TERRACOTTA_LIGHT_GREEN, COLOR_STONE_TAGS);
    public static final BlockMaterial PINK_TERRACOTTA = mc(Blocks.PINK_TERRACOTTA, MapColorHolders.TERRACOTTA_PINK, COLOR_STONE_TAGS);
    public static final BlockMaterial GRAY_TERRACOTTA = mc(Blocks.GRAY_TERRACOTTA, MapColorHolders.TERRACOTTA_GRAY, COLOR_STONE_TAGS);
    public static final BlockMaterial LIGHT_GRAY_TERRACOTTA = mc(Blocks.LIGHT_GRAY_TERRACOTTA, MapColorHolders.TERRACOTTA_LIGHT_GRAY, COLOR_STONE_TAGS);
    public static final BlockMaterial CYAN_TERRACOTTA = mc(Blocks.CYAN_TERRACOTTA, MapColorHolders.TERRACOTTA_CYAN, COLOR_STONE_TAGS);
    public static final BlockMaterial PURPLE_TERRACOTTA = mc(Blocks.PURPLE_TERRACOTTA, MapColorHolders.TERRACOTTA_PURPLE, COLOR_STONE_TAGS);
    public static final BlockMaterial BLUE_TERRACOTTA = mc(Blocks.BLUE_TERRACOTTA, MapColorHolders.TERRACOTTA_BLUE, COLOR_STONE_TAGS);
    public static final BlockMaterial BROWN_TERRACOTTA = mc(Blocks.BROWN_TERRACOTTA, MapColorHolders.TERRACOTTA_BROWN, COLOR_STONE_TAGS);
    public static final BlockMaterial GREEN_TERRACOTTA = mc(Blocks.GREEN_TERRACOTTA, MapColorHolders.TERRACOTTA_GREEN, COLOR_STONE_TAGS);
    public static final BlockMaterial RED_TERRACOTTA = mc(Blocks.RED_TERRACOTTA, MapColorHolders.TERRACOTTA_RED, COLOR_STONE_TAGS);
    public static final BlockMaterial BLACK_TERRACOTTA = mc(Blocks.BLACK_TERRACOTTA, MapColorHolders.TERRACOTTA_BLACK, COLOR_STONE_TAGS);
    public static final BlockMaterial RAINBOW_TERRACOTTA = reg(FDBlocks.RAINBOW_TERRACOTTA, MapColorHolders.TERRACOTTA_WHITE, COLOR_STONE_TAGS);
    public static final HashSet<BlockMaterialTag> COLOR_ARTIFICIAL_STONE_TAGS = new HashSet<>();
    public static final BlockMaterial WHITE_CONCRETE = mc(Blocks.WHITE_CONCRETE, MapColorHolders.SNOW, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial ORANGE_CONCRETE = mc(Blocks.ORANGE_CONCRETE, MapColorHolders.COLOR_ORANGE, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial MAGENTA_CONCRETE = mc(Blocks.MAGENTA_CONCRETE, MapColorHolders.COLOR_MAGENTA, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial LIGHT_BLUE_CONCRETE = mc(Blocks.LIGHT_BLUE_CONCRETE, MapColorHolders.COLOR_LIGHT_BLUE, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial YELLOW_CONCRETE = mc(Blocks.YELLOW_CONCRETE, MapColorHolders.COLOR_YELLOW, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial LIME_CONCRETE = mc(Blocks.LIME_CONCRETE, MapColorHolders.COLOR_LIGHT_GREEN, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial PINK_CONCRETE = mc(Blocks.PINK_CONCRETE, MapColorHolders.COLOR_PINK, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial GRAY_CONCRETE = mc(Blocks.GRAY_CONCRETE, MapColorHolders.COLOR_GRAY, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial LIGHT_GRAY_CONCRETE = mc(Blocks.LIGHT_GRAY_CONCRETE, MapColorHolders.COLOR_LIGHT_GRAY, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial CYAN_CONCRETE = mc(Blocks.CYAN_CONCRETE, MapColorHolders.COLOR_CYAN, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial PURPLE_CONCRETE = mc(Blocks.PURPLE_CONCRETE, MapColorHolders.COLOR_PURPLE, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial BLUE_CONCRETE = mc(Blocks.BLUE_CONCRETE, MapColorHolders.COLOR_BLUE, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial BROWN_CONCRETE = mc(Blocks.BROWN_CONCRETE, MapColorHolders.COLOR_BROWN, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial GREEN_CONCRETE = mc(Blocks.GREEN_CONCRETE, MapColorHolders.COLOR_GREEN, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial RED_CONCRETE = mc(Blocks.RED_CONCRETE, MapColorHolders.COLOR_RED, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial BLACK_CONCRETE = mc(Blocks.BLACK_CONCRETE, MapColorHolders.COLOR_BLACK, COLOR_ARTIFICIAL_STONE_TAGS);
    public static final BlockMaterial RAINBOW_CONCRETE = reg(FDBlocks.RAINBOW_CONCRETE, MapColorHolders.SNOW, COLOR_ARTIFICIAL_STONE_TAGS);

    public static final HashSet<BlockMaterialTag> WOOL_TAGS = new HashSet<>();
    public static final BlockMaterial WHITE_WOOL = mc(Blocks.WHITE_WOOL, MapColorHolders.SNOW, WOOL_TAGS);
    public static final BlockMaterial ORANGE_WOOL = mc(Blocks.ORANGE_WOOL, MapColorHolders.COLOR_ORANGE, WOOL_TAGS);
    public static final BlockMaterial MAGENTA_WOOL = mc(Blocks.MAGENTA_WOOL, MapColorHolders.COLOR_MAGENTA, WOOL_TAGS);
    public static final BlockMaterial LIGHT_BLUE_WOOL = mc(Blocks.LIGHT_BLUE_WOOL, MapColorHolders.COLOR_BLUE, WOOL_TAGS);
    public static final BlockMaterial YELLOW_WOOL = mc(Blocks.YELLOW_WOOL, MapColorHolders.COLOR_YELLOW, WOOL_TAGS);
    public static final BlockMaterial LIME_WOOL = mc(Blocks.LIME_WOOL, MapColorHolders.COLOR_LIGHT_GREEN, WOOL_TAGS);
    public static final BlockMaterial PINK_WOOL = mc(Blocks.PINK_WOOL, MapColorHolders.COLOR_PINK, WOOL_TAGS);
    public static final BlockMaterial GRAY_WOOL = mc(Blocks.GRAY_WOOL, MapColorHolders.COLOR_GRAY, WOOL_TAGS);
    public static final BlockMaterial LIGHT_GRAY_WOOL = mc(Blocks.LIGHT_GRAY_WOOL, MapColorHolders.COLOR_LIGHT_GRAY, WOOL_TAGS);
    public static final BlockMaterial CYAN_WOOL = mc(Blocks.CYAN_WOOL, MapColorHolders.COLOR_CYAN, WOOL_TAGS);
    public static final BlockMaterial PURPLE_WOOL = mc(Blocks.PURPLE_WOOL, MapColorHolders.COLOR_PURPLE, WOOL_TAGS);
    public static final BlockMaterial BLUE_WOOL = mc(Blocks.BLUE_WOOL, MapColorHolders.COLOR_BLUE, WOOL_TAGS);
    public static final BlockMaterial BROWN_WOOL = mc(Blocks.BROWN_WOOL, MapColorHolders.COLOR_BROWN, WOOL_TAGS);
    public static final BlockMaterial GREEN_WOOL = mc(Blocks.GREEN_WOOL, MapColorHolders.COLOR_GREEN, WOOL_TAGS);
    public static final BlockMaterial RED_WOOL = mc(Blocks.RED_WOOL, MapColorHolders.COLOR_RED, WOOL_TAGS);
    public static final BlockMaterial BLACK_WOOL = mc(Blocks.BLACK_WOOL, MapColorHolders.COLOR_BLACK, WOOL_TAGS);
    public static final BlockMaterial RAINBOW_WOOL = reg(FDBlocks.RAINBOW_WOOL, MapColorHolders.SNOW, WOOL_TAGS);

    public static final HashSet<BlockMaterialTag> GLASS_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> STAINED_GLASS_TAGS = new HashSet<>();
    public static final BlockMaterial GLASS = mc(Blocks.GLASS, MapColorHolders.NONE, GLASS_TAGS);
    public static final BlockMaterial WHITE_STAINED_GLASS = mc(Blocks.WHITE_STAINED_GLASS, MapColorHolders.SNOW, STAINED_GLASS_TAGS);
    public static final BlockMaterial ORANGE_STAINED_GLASS = mc(Blocks.ORANGE_STAINED_GLASS, MapColorHolders.COLOR_ORANGE, STAINED_GLASS_TAGS);
    public static final BlockMaterial MAGENTA_STAINED_GLASS = mc(Blocks.MAGENTA_STAINED_GLASS, MapColorHolders.COLOR_MAGENTA, STAINED_GLASS_TAGS);
    public static final BlockMaterial LIGHT_BLUE_STAINED_GLASS = mc(Blocks.LIGHT_BLUE_STAINED_GLASS, MapColorHolders.COLOR_LIGHT_BLUE, STAINED_GLASS_TAGS);
    public static final BlockMaterial YELLOW_STAINED_GLASS = mc(Blocks.YELLOW_STAINED_GLASS, MapColorHolders.COLOR_YELLOW, STAINED_GLASS_TAGS);
    public static final BlockMaterial LIME_STAINED_GLASS = mc(Blocks.LIME_STAINED_GLASS, MapColorHolders.COLOR_LIGHT_GREEN, STAINED_GLASS_TAGS);
    public static final BlockMaterial PINK_STAINED_GLASS = mc(Blocks.PINK_STAINED_GLASS, MapColorHolders.COLOR_PINK, STAINED_GLASS_TAGS);
    public static final BlockMaterial GRAY_STAINED_GLASS = mc(Blocks.GRAY_STAINED_GLASS, MapColorHolders.COLOR_GRAY, STAINED_GLASS_TAGS);
    public static final BlockMaterial LIGHT_GRAY_STAINED_GLASS = mc(Blocks.LIGHT_GRAY_STAINED_GLASS, MapColorHolders.COLOR_LIGHT_GRAY, STAINED_GLASS_TAGS);
    public static final BlockMaterial CYAN_STAINED_GLASS = mc(Blocks.CYAN_STAINED_GLASS, MapColorHolders.COLOR_CYAN, STAINED_GLASS_TAGS);
    public static final BlockMaterial PURPLE_STAINED_GLASS = mc(Blocks.PURPLE_STAINED_GLASS, MapColorHolders.COLOR_PURPLE, STAINED_GLASS_TAGS);
    public static final BlockMaterial BLUE_STAINED_GLASS = mc(Blocks.BLUE_STAINED_GLASS, MapColorHolders.COLOR_BLUE, STAINED_GLASS_TAGS);
    public static final BlockMaterial BROWN_STAINED_GLASS = mc(Blocks.BROWN_STAINED_GLASS, MapColorHolders.COLOR_BROWN, STAINED_GLASS_TAGS);
    public static final BlockMaterial GREEN_STAINED_GLASS = mc(Blocks.GREEN_STAINED_GLASS, MapColorHolders.COLOR_GREEN, STAINED_GLASS_TAGS);
    public static final BlockMaterial RED_STAINED_GLASS = mc(Blocks.RED_STAINED_GLASS, MapColorHolders.COLOR_RED, STAINED_GLASS_TAGS);
    public static final BlockMaterial BLACK_STAINED_GLASS = mc(Blocks.BLACK_STAINED_GLASS, MapColorHolders.COLOR_BLACK, STAINED_GLASS_TAGS);
    public static final BlockMaterial RAINBOW_GLASS = reg(FDBlocks.RAINBOW_GLASS, MapColorHolders.SNOW, STAINED_GLASS_TAGS);

    public static final HashSet<BlockMaterialTag> LAMP_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> SEA_LANTERN_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> COLOR_LAMP_TAGS = new HashSet<>();
    public static final HashSet<BlockMaterialTag> FROGLIGHT_TAGS = new HashSet<>();
    public static final BlockMaterial GLOWSTONE = mc(Blocks.GLOWSTONE, MapColorHolders.SAND, LAMP_TAGS);
    public static final BlockMaterial GLOWSTONE_LAMP = reg(FDBlocks.GLOWSTONE_LAMP, MapColorHolders.SAND, LAMP_TAGS);
    public static final BlockMaterial SEA_LANTERN = mc(Blocks.SEA_LANTERN, MapColorHolders.QUARTZ, SEA_LANTERN_TAGS);
    public static final BlockMaterial RAINBOW_SEA_LANTERN = reg(FDBlocks.RAINBOW_SEA_LANTERN, MapColorHolders.SNOW, COLOR_LAMP_TAGS);
    public static final BlockMaterial OCHRE_FROGLIGHT = mc(Blocks.OCHRE_FROGLIGHT, MapColorHolders.SAND, FROGLIGHT_TAGS);
    public static final BlockMaterial VERDANT_FROGLIGHT = mc(Blocks.VERDANT_FROGLIGHT, MapColorHolders.GLOW_LICHEN, FROGLIGHT_TAGS);
    public static final BlockMaterial PEARLESCENT_FROGLIGHT = mc(Blocks.PEARLESCENT_FROGLIGHT, MapColorHolders.COLOR_PINK, FROGLIGHT_TAGS);
    public static final BlockMaterial RED_LAMP = reg(FDBlocks.RED_LAMP, MapColorHolders.COLOR_RED, COLOR_LAMP_TAGS);
    public static final BlockMaterial GREEN_LAMP = reg(FDBlocks.GREEN_LAMP, MapColorHolders.COLOR_GREEN, COLOR_LAMP_TAGS);
    public static final BlockMaterial BLUE_LAMP = reg(FDBlocks.BLUE_LAMP, MapColorHolders.COLOR_BLUE, COLOR_LAMP_TAGS);
    public static final BlockMaterial YELLOW_LAMP = reg(FDBlocks.YELLOW_LAMP, MapColorHolders.COLOR_YELLOW, COLOR_LAMP_TAGS);
    public static final BlockMaterial CYAN_LAMP = reg(FDBlocks.CYAN_LAMP, MapColorHolders.COLOR_CYAN, COLOR_LAMP_TAGS);
    public static final BlockMaterial PURPLE_LAMP = reg(FDBlocks.PURPLE_LAMP, MapColorHolders.COLOR_PURPLE, COLOR_LAMP_TAGS);
    public static final BlockMaterial RAINBOW_LAMP = reg(FDBlocks.RAINBOW_LAMP, MapColorHolders.SNOW, COLOR_LAMP_TAGS);


    private BlockMaterials() {}

    private static BlockMaterial mc(Block block, MapColorHolder mapColorHolder, HashSet<BlockMaterialTag> tags) {
        return BlockBulkRegister.registerBlockMaterial(BlockMaterial.mcBlock(block, mapColorHolder, tags));
    }

    private static BlockMaterial mcPlanks(Block block, MapColorHolder mapColorHolder, HashSet<BlockMaterialTag> tags) {
        ResourceLocation rlb = BuiltInRegistries.BLOCK.getKey(block);
        return BlockBulkRegister.registerBlockMaterial(new BlockMaterial(
                ResourceLocation.fromNamespaceAndPath(rlb.getNamespace(), rlb.getPath().replace("_planks", "")),
                () -> block,
                () -> BlockBehaviour.Properties.ofFullCopy(block),
                mapColorHolder,
                tags
        ));
    }

    private static BlockMaterial reg(DeferredBlock<? extends Block> deferredBlock, MapColorHolder mapColorHolder, HashSet<BlockMaterialTag> tags) {
        return BlockBulkRegister.registerBlockMaterial(BlockMaterial.deferredBlock(deferredBlock, mapColorHolder, tags));
    }

    private static BlockMaterial regPlanks(DeferredBlock<? extends Block> deferredBlock, MapColorHolder mapColorHolder, HashSet<BlockMaterialTag> tags) {
        ResourceLocation id = deferredBlock.getId();
        return BlockBulkRegister.registerBlockMaterial(new BlockMaterial(
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath().replace("_planks", "")),
                deferredBlock,
                () -> BlockBehaviour.Properties.ofFullCopy(deferredBlock.get()),
                mapColorHolder,
                tags
        ));
    }

    private static void natureToArtificial(Set<BlockMaterialTag> set) {
        set.remove(BlockMaterialTag.NATURE);
        set.add(BlockMaterialTag.ARTIFICIAL);
    }

    private static void tagReplace(Set<BlockMaterialTag> set, BlockMaterialTag oldTag, BlockMaterialTag newTag) {
        set.remove(oldTag);
        set.add(newTag);
    }

    public static void init() {
        LOG_TAGS.add(BlockMaterialTag.TOOL_AXE);
        LOG_TAGS.add(BlockMaterialTag.NATURE);
        LOG_TAGS.add(BlockMaterialTag.STATE_PILLAR);
        LOG_TAGS.add(BlockMaterialTag.MODEL_LOG);

        STRIPPED_LOG_TAGS.addAll(LOG_TAGS);
        natureToArtificial(STRIPPED_LOG_TAGS);

        PLANK_TAGS.add(BlockMaterialTag.TOOL_AXE);
        PLANK_TAGS.add(BlockMaterialTag.ARTIFICIAL);
        PLANK_TAGS.add(BlockMaterialTag.STATE_STAND);
        PLANK_TAGS.add(BlockMaterialTag.TEXTURE_BRICKS);
        PLANK_TAGS.add(BlockMaterialTag.MODEL_CUBE);

        SMOOTH_PLANK_TAGS.addAll(PLANK_TAGS);
        tagReplace(SMOOTH_PLANK_TAGS, BlockMaterialTag.TEXTURE_BRICKS, BlockMaterialTag.TEXTURE_SMOOTH);
        SMOOTH_PLANK_TAGS.add(BlockMaterialTag.MAKE_SLAB);

        DIRT_TAGS.add(BlockMaterialTag.TOOL_SHOVEL);
        DIRT_TAGS.add(BlockMaterialTag.NATURE);
        DIRT_TAGS.add(BlockMaterialTag.STATE_STAND);
        DIRT_TAGS.add(BlockMaterialTag.TEXTURE_SIMPLE);
        DIRT_TAGS.add(BlockMaterialTag.MODEL_CUBE);

        PODZOL_TAGS.addAll(DIRT_TAGS);
        tagReplace(PODZOL_TAGS, BlockMaterialTag.TEXTURE_SIMPLE, BlockMaterialTag.TEXTURE_PODZOL);
        tagReplace(PODZOL_TAGS, BlockMaterialTag.MODEL_CUBE, BlockMaterialTag.MODEL_UP_DOWN_SIDE);

        PILLAR_DIRT_TAGS.addAll(DIRT_TAGS);
        tagReplace(PILLAR_DIRT_TAGS, BlockMaterialTag.MODEL_CUBE, BlockMaterialTag.MODEL_PILLAR);

        MOSSY_TAGS.addAll(DIRT_TAGS);
        tagReplace(MOSSY_TAGS, BlockMaterialTag.TOOL_SHOVEL, BlockMaterialTag.TOOL_HOE);

        SNOW_TAGS.add(BlockMaterialTag.TOOL_SHOVEL);
        SNOW_TAGS.add(BlockMaterialTag.NATURE);
        SNOW_TAGS.add(BlockMaterialTag.STATE_STAND);
        SNOW_TAGS.add(BlockMaterialTag.TEXTURE_SNOW);
        SNOW_TAGS.add(BlockMaterialTag.MODEL_CUBE);

        NYLIUM_TAGS.add(BlockMaterialTag.TOOL_PICKAXE);
        NYLIUM_TAGS.add(BlockMaterialTag.NATURE);
        NYLIUM_TAGS.add(BlockMaterialTag.STATE_STAND);
        NYLIUM_TAGS.add(BlockMaterialTag.MODEL_UP_DOWN_SIDE);
        NYLIUM_TAGS.add(BlockMaterialTag.TEXTURE_NYLIUM);

        STONE_TAGS.add(BlockMaterialTag.TOOL_PICKAXE);
        STONE_TAGS.add(BlockMaterialTag.NATURE);
        STONE_TAGS.add(BlockMaterialTag.STATE_STAND);
        STONE_TAGS.add(BlockMaterialTag.TEXTURE_SIMPLE);
        STONE_TAGS.add(BlockMaterialTag.MODEL_CUBE);

        OBSIDIAN_TAGS.addAll(STONE_TAGS);
        OBSIDIAN_TAGS.add(BlockMaterialTag.NEEDS_DIAMOND_TOOL);
        OBSIDIAN_TAGS.add(BlockMaterialTag.TAG_DRAGON_IMMUNE);

        POLISHED_TAGS.addAll(STONE_TAGS);
        natureToArtificial(POLISHED_TAGS);
        tagReplace(POLISHED_TAGS, BlockMaterialTag.TEXTURE_SIMPLE, BlockMaterialTag.TEXTURE_FRAMED);

        CHISELED_TAGS.addAll(POLISHED_TAGS);
        tagReplace(CHISELED_TAGS, BlockMaterialTag.TEXTURE_FRAMED, BlockMaterialTag.TEXTURE_CHISELED);

        BRICK_TAGS.addAll(POLISHED_TAGS);
        tagReplace(BRICK_TAGS, BlockMaterialTag.TEXTURE_FRAMED, BlockMaterialTag.TEXTURE_BRICKS);

        END_STONE_TAGS.addAll(STONE_TAGS);
        END_STONE_TAGS.add(BlockMaterialTag.TAG_DRAGON_IMMUNE);

        END_STONE_BRICK_TAGS.addAll(END_STONE_TAGS);
        natureToArtificial(END_STONE_BRICK_TAGS);
        tagReplace(END_STONE_BRICK_TAGS, BlockMaterialTag.TEXTURE_SIMPLE, BlockMaterialTag.TEXTURE_BRICKS);

        CRACKED_TAGS.addAll(POLISHED_TAGS);
        tagReplace(CRACKED_TAGS, BlockMaterialTag.TEXTURE_FRAMED, BlockMaterialTag.TEXTURE_CRACKED);

        SMOOTH_TAGS.addAll(POLISHED_TAGS);
        tagReplace(SMOOTH_TAGS, BlockMaterialTag.TEXTURE_FRAMED, BlockMaterialTag.TEXTURE_SMOOTH);

        NATURE_PILLAR_TAGS.add(BlockMaterialTag.TOOL_PICKAXE);
        NATURE_PILLAR_TAGS.add(BlockMaterialTag.NATURE);
        NATURE_PILLAR_TAGS.add(BlockMaterialTag.STATE_PILLAR);
        NATURE_PILLAR_TAGS.add(BlockMaterialTag.MODEL_PILLAR);

        PILLAR_TAGS.addAll(NATURE_PILLAR_TAGS);
        natureToArtificial(PILLAR_TAGS);

        DEEPSLATE_TAGS.addAll(NATURE_PILLAR_TAGS);
        tagReplace(DEEPSLATE_TAGS, BlockMaterialTag.MODEL_PILLAR, BlockMaterialTag.MODEL_LOG);

        SANDSTONE_TAGS.add(BlockMaterialTag.TOOL_PICKAXE);
        SANDSTONE_TAGS.add(BlockMaterialTag.NATURE);
        SANDSTONE_TAGS.add(BlockMaterialTag.STATE_STAND);
        SANDSTONE_TAGS.add(BlockMaterialTag.MODEL_UP_DOWN_SIDE);
        SANDSTONE_TAGS.add(BlockMaterialTag.TEXTURE_SANDSTONE);

        CHISELED_SANDSTONE_TAGS.addAll(SANDSTONE_TAGS);
        CHISELED_SANDSTONE_TAGS.add(BlockMaterialTag.TEXTURE_CHISELED);

        SMOOTH_SANDSTONE_TAGS.addAll(SMOOTH_TAGS);
        SMOOTH_SANDSTONE_TAGS.add(BlockMaterialTag.TEXTURE_SANDSTONE);

        //ICE_TAGS.addAll(STONE_TAGS);
        //ICE_TAGS.add(BlockMaterialTag.TEXTURE_TRANSLUCENT);
        //ICE_TAGS.add(BlockMaterialTag.ICE);

        //MAGMA_TAGS.addAll(STONE_TAGS);
        //tagReplace(MAGMA_TAGS, BlockMaterialTag.TEXTURE_SIMPLE, BlockMaterialTag.TEXTURE_MAGMA);

        QUARTZ_TAGS.addAll(POLISHED_TAGS);
        QUARTZ_TAGS.add(BlockMaterialTag.TEXTURE_QUARTZ);

        CHISELED_QUARTZ_TAGS.addAll(CHISELED_TAGS);
        tagReplace(CHISELED_QUARTZ_TAGS, BlockMaterialTag.MODEL_CUBE, BlockMaterialTag.MODEL_LOG);
        CHISELED_QUARTZ_TAGS.add(BlockMaterialTag.TEXTURE_QUARTZ);

        SMOOTH_QUARTZ_TAGS.addAll(SMOOTH_TAGS);
        SMOOTH_QUARTZ_TAGS.add(BlockMaterialTag.TEXTURE_QUARTZ);

        LOG_STONE_TAGS.addAll(PILLAR_TAGS);
        tagReplace(LOG_STONE_TAGS, BlockMaterialTag.MODEL_PILLAR, BlockMaterialTag.MODEL_LOG);

        COLOR_STONE_TAGS.addAll(STONE_TAGS);
        COLOR_STONE_TAGS.add(BlockMaterialTag.TEXTURE_COLORED);

        COLOR_ARTIFICIAL_STONE_TAGS.addAll(COLOR_STONE_TAGS);
        natureToArtificial(COLOR_ARTIFICIAL_STONE_TAGS);

        WOOL_TAGS.add(BlockMaterialTag.TOOL_SHEARS);
        WOOL_TAGS.add(BlockMaterialTag.ARTIFICIAL);
        WOOL_TAGS.add(BlockMaterialTag.STATE_STAND);
        WOOL_TAGS.add(BlockMaterialTag.TEXTURE_COLORED);
        WOOL_TAGS.add(BlockMaterialTag.MODEL_CUBE);

        GLASS_TAGS.add(BlockMaterialTag.ARTIFICIAL);
        GLASS_TAGS.add(BlockMaterialTag.STATE_STAND);
        GLASS_TAGS.add(BlockMaterialTag.TEXTURE_FRAMED);
        GLASS_TAGS.add(BlockMaterialTag.TEXTURE_TRANSLUCENT);
        GLASS_TAGS.add(BlockMaterialTag.MODEL_CUBE);

        STAINED_GLASS_TAGS.addAll(GLASS_TAGS);
        STAINED_GLASS_TAGS.add(BlockMaterialTag.TEXTURE_COLORED);

        LAMP_TAGS.add(BlockMaterialTag.TOOL_PICKAXE);
        LAMP_TAGS.add(BlockMaterialTag.LAMP);
        LAMP_TAGS.add(BlockMaterialTag.MODEL_CUBE);
        SEA_LANTERN_TAGS.addAll(LAMP_TAGS);
        SEA_LANTERN_TAGS.add(BlockMaterialTag.ADD_COLOR);
        SEA_LANTERN_TAGS.remove(BlockMaterialTag.TOOL_PICKAXE);
        COLOR_LAMP_TAGS.addAll(LAMP_TAGS);
        COLOR_LAMP_TAGS.add(BlockMaterialTag.TEXTURE_COLORED);
        FROGLIGHT_TAGS.addAll(COLOR_LAMP_TAGS);
        FROGLIGHT_TAGS.remove(BlockMaterialTag.TOOL_PICKAXE);
        tagReplace(FROGLIGHT_TAGS, BlockMaterialTag.MODEL_CUBE, BlockMaterialTag.MODEL_PILLAR);



        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_OAK_PLANKS, WoodType.OAK);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_SPRUCE_PLANKS, WoodType.SPRUCE);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_BIRCH_PLANKS, WoodType.BIRCH);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_JUNGLE_PLANKS, WoodType.JUNGLE);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_ACACIA_PLANKS, WoodType.ACACIA);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_DARK_OAK_PLANKS, WoodType.DARK_OAK);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_MANGROVE_PLANKS, WoodType.MANGROVE);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_BAMBOO_PLANKS, WoodType.BAMBOO);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_CHERRY_PLANKS, WoodType.CHERRY);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_CRIMSON_PLANKS, WoodType.CRIMSON);
        BlockBulkRegister.WOOD_TYPE_MAP.put(SMOOTH_WARPED_PLANKS, WoodType.WARPED);

        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_OAK_PLANKS, BlockSetType.OAK);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_SPRUCE_PLANKS, BlockSetType.SPRUCE);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_BIRCH_PLANKS, BlockSetType.BIRCH);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_JUNGLE_PLANKS, BlockSetType.JUNGLE);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_ACACIA_PLANKS, BlockSetType.ACACIA);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_DARK_OAK_PLANKS, BlockSetType.DARK_OAK);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_MANGROVE_PLANKS, BlockSetType.MANGROVE);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_BAMBOO_PLANKS, BlockSetType.BAMBOO);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_CHERRY_PLANKS, BlockSetType.CHERRY);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_CRIMSON_PLANKS, BlockSetType.CRIMSON);
        BlockBulkRegister.BLOCK_SET_TYPE_MAP.put(SMOOTH_WARPED_PLANKS, BlockSetType.WARPED);

    }

}
