package net.fiercemanul.fiercedecoration.world.level.block;


import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public class BlockMaterial {


    public static final BlockMaterial[] BLOCK_MATERIALS = {
            new BlockMaterialBuilder(Blocks.OAK_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.WOOD, MapColor.PODZOL)).build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_OAK_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.WOOD).build(),
            new BlockMaterialBuilder(Blocks.OAK_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.WOOD).unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_OAK_PLANKS).setPropertiesFrom(Blocks.OAK_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.WOOD).build(),
            new BlockMaterialBuilder(Blocks.SPRUCE_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.PODZOL, MapColor.COLOR_BROWN)).build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_SPRUCE_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.PODZOL).build(),
            new BlockMaterialBuilder(Blocks.SPRUCE_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.PODZOL).unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_SPRUCE_PLANKS).setPropertiesFrom(Blocks.SPRUCE_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.PODZOL).build(),
            new BlockMaterialBuilder(Blocks.BIRCH_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.SAND, MapColor.QUARTZ)).build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_BIRCH_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.SAND).build(),
            new BlockMaterialBuilder(Blocks.BIRCH_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.SAND).unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_BIRCH_PLANKS).setPropertiesFrom(Blocks.BIRCH_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.SAND).build(),
            new BlockMaterialBuilder(Blocks.JUNGLE_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.DIRT, MapColor.PODZOL)).build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_JUNGLE_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.DIRT).build(),
            new BlockMaterialBuilder(Blocks.JUNGLE_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.DIRT).unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_JUNGLE_PLANKS).setPropertiesFrom(Blocks.JUNGLE_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.DIRT).build(),
            new BlockMaterialBuilder(Blocks.ACACIA_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.COLOR_ORANGE, MapColor.STONE)).build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_ACACIA_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.COLOR_ORANGE).build(),
            new BlockMaterialBuilder(Blocks.ACACIA_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.COLOR_ORANGE).unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_ACACIA_PLANKS).setPropertiesFrom(Blocks.ACACIA_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.COLOR_ORANGE).build(),
            new BlockMaterialBuilder(Blocks.DARK_OAK_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN)).build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_DARK_OAK_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.COLOR_BROWN).build(),
            new BlockMaterialBuilder(Blocks.DARK_OAK_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.COLOR_BROWN).unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_DARK_OAK_PLANKS).setPropertiesFrom(Blocks.DARK_OAK_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.COLOR_BROWN).build(),
            new BlockMaterialBuilder(Blocks.MANGROVE_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.COLOR_RED, MapColor.PODZOL)).build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_MANGROVE_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.COLOR_RED).build(),
            new BlockMaterialBuilder(Blocks.MANGROVE_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.COLOR_RED).unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_MANGROVE_PLANKS).setPropertiesFrom(Blocks.MANGROVE_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.COLOR_RED).build(),
            new BlockMaterialBuilder(Blocks.BAMBOO_BLOCK).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.COLOR_YELLOW, MapColor.PLANT)).build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_BAMBOO_BLOCK).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.COLOR_YELLOW).build(),
            new BlockMaterialBuilder(Blocks.BAMBOO_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.COLOR_YELLOW).unSturdy().build(),
            new BlockMaterialBuilder(Blocks.BAMBOO_MOSAIC).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.COLOR_YELLOW).unSturdy().build(),
            new BlockMaterialBuilder(Blocks.CHERRY_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY)).build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_CHERRY_LOG).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(new PillarMapColorHelper(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_PINK)).build(),
            new BlockMaterialBuilder(Blocks.CHERRY_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.TERRACOTTA_WHITE).unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_CHERRY_PLANKS).setPropertiesFrom(Blocks.CHERRY_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.TERRACOTTA_WHITE).build(),
            new BlockMaterialBuilder(Blocks.CRIMSON_STEM).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.CRIMSON_STEM).isNonFlammableWood().build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_CRIMSON_STEM).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.CRIMSON_STEM).isNonFlammableWood().build(),
            new BlockMaterialBuilder(Blocks.CRIMSON_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.CRIMSON_STEM).isNonFlammableWood().unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_CRIMSON_PLANKS).setPropertiesFrom(Blocks.CRIMSON_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.CRIMSON_STEM).isNonFlammableWood().build(),
            new BlockMaterialBuilder(Blocks.WARPED_STEM).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.WARPED_STEM).isNonFlammableWood().build(),
            new BlockMaterialBuilder(Blocks.STRIPPED_WARPED_STEM).setMaterialType(MaterialType.WOOD).setModelType(ModelType.LOG).setMapColorHelper(MapColor.WARPED_STEM).isNonFlammableWood().build(),
            new BlockMaterialBuilder(Blocks.WARPED_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.WARPED_STEM).isNonFlammableWood().unSturdy().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_WARPED_PLANKS).setPropertiesFrom(Blocks.WARPED_PLANKS).setMaterialType(MaterialType.WOOD).setMapColorHelper(MapColor.WARPED_STEM).isNonFlammableWood().build(),

            new BlockMaterialBuilder(Blocks.DIRT).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.DIRT).build(),
            new BlockMaterialBuilder(Blocks.COARSE_DIRT).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.DIRT).build(),
            new BlockMaterialBuilder(Blocks.ROOTED_DIRT).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.DIRT).build(),
            new BlockMaterialBuilder(Blocks.PODZOL).setMaterialType(MaterialType.DIRT).setModelType(ModelType.GRASS).setMapColorHelper(MapColor.PODZOL).build(),
            new BlockMaterialBuilder(Blocks.MYCELIUM).setMaterialType(MaterialType.DIRT).setModelType(ModelType.GRASS).setMapColorHelper(MapColor.COLOR_PURPLE).build(),
            new BlockMaterialBuilder(Blocks.MUD).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.TERRACOTTA_CYAN).build(),
            new BlockMaterialBuilder(Blocks.MUDDY_MANGROVE_ROOTS).setModelType(ModelType.PILLAR).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.PODZOL).build(),
            new BlockMaterialBuilder(Blocks.SAND).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.SAND).build(),
            new BlockMaterialBuilder(Blocks.GRAVEL).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.STONE).build(),
            new BlockMaterialBuilder(Blocks.CLAY).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.CLAY).build(),
            new BlockMaterialBuilder(Blocks.SOUL_SAND).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.COLOR_BROWN).build(),
            new BlockMaterialBuilder(Blocks.SOUL_SOIL).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.COLOR_BROWN).build(),
            new BlockMaterialBuilder(Blocks.SNOW_BLOCK).setModelType(ModelType.CUSTOM).setMaterialType(MaterialType.DIRT).setMapColorHelper(MapColor.SNOW).build(),
            new BlockMaterialBuilder(Blocks.CRIMSON_NYLIUM).setModelType(ModelType.NYLIUM).setMapColorHelper(MapColor.CRIMSON_NYLIUM).build(),
            new BlockMaterialBuilder(Blocks.WARPED_NYLIUM).setModelType(ModelType.NYLIUM).setMapColorHelper(MapColor.WARPED_NYLIUM).build(),

            new BlockMaterialBuilder(Blocks.MOSS_BLOCK).setMaterialType(MaterialType.MOSSY).setMapColorHelper(MapColor.COLOR_GREEN).build(),
            new BlockMaterialBuilder(Blocks.MUSHROOM_STEM).setMaterialType(MaterialType.MOSSY).setMapColorHelper(MapColor.DIRT).build(),
            new BlockMaterialBuilder(Blocks.BROWN_MUSHROOM_BLOCK).setMaterialType(MaterialType.MOSSY).setMapColorHelper(MapColor.DIRT).build(),
            new BlockMaterialBuilder(Blocks.RED_MUSHROOM_BLOCK).setMaterialType(MaterialType.MOSSY).setMapColorHelper(MapColor.COLOR_RED).build(),
            new BlockMaterialBuilder(Blocks.NETHER_WART_BLOCK).setMaterialType(MaterialType.MOSSY).setMapColorHelper(MapColor.COLOR_RED).build(),
            new BlockMaterialBuilder(Blocks.WARPED_WART_BLOCK).setMaterialType(MaterialType.MOSSY).setMapColorHelper(MapColor.WARPED_WART_BLOCK).build(),

            new BlockMaterialBuilder(Blocks.PACKED_MUD).setMapColorHelper(MapColor.DIRT).build(),
            new BlockMaterialBuilder(Blocks.MUD_BRICKS).setMapColorHelper(MapColor.TERRACOTTA_LIGHT_GRAY).build(),
            new BlockMaterialBuilder(Blocks.STONE).setMapColorHelper(MapColor.STONE).build(),
            new BlockMaterialBuilder(Blocks.SMOOTH_STONE).setMapColorHelper(MapColor.STONE).setModelType(ModelType.CUBE_ALL_FRAMED).build(),
            new BlockMaterialBuilder(Blocks.STONE_BRICKS).setMapColorHelper(MapColor.STONE).build(),
            new BlockMaterialBuilder(Blocks.CHISELED_STONE_BRICKS).setMapColorHelper(MapColor.STONE).isTotemTexture().build(),
            new BlockMaterialBuilder(Blocks.MOSSY_STONE_BRICKS).setMapColorHelper(MapColor.STONE).build(),
            new BlockMaterialBuilder(Blocks.CRACKED_STONE_BRICKS).setMapColorHelper(MapColor.STONE).unSturdy().build(),
            new BlockMaterialBuilder(Blocks.COBBLESTONE).setMapColorHelper(MapColor.STONE).build(),
            new BlockMaterialBuilder(Blocks.MOSSY_COBBLESTONE).setMapColorHelper(MapColor.STONE).build(),
            new BlockMaterialBuilder(Blocks.GRANITE).setMapColorHelper(MapColor.DIRT).build(),
            new BlockMaterialBuilder(Blocks.POLISHED_GRANITE).setMapColorHelper(MapColor.DIRT).setModelType(ModelType.CUBE_ALL_FRAMED).build(),
            new BlockMaterialBuilder(Blocks.DIORITE).setMapColorHelper(MapColor.QUARTZ).build(),
            new BlockMaterialBuilder(Blocks.POLISHED_DIORITE).setMapColorHelper(MapColor.QUARTZ).setModelType(ModelType.CUBE_ALL_FRAMED).build(),
            new BlockMaterialBuilder(Blocks.ANDESITE).setMapColorHelper(MapColor.STONE).build(),
            new BlockMaterialBuilder(Blocks.POLISHED_ANDESITE).setMapColorHelper(MapColor.STONE).setModelType(ModelType.CUBE_ALL_FRAMED).build(),
            new BlockMaterialBuilder(Blocks.TUFF).setMapColorHelper(MapColor.TERRACOTTA_GRAY).build(),
            new BlockMaterialBuilder(Blocks.CALCITE).setMapColorHelper(MapColor.TERRACOTTA_WHITE).build(),
            new BlockMaterialBuilder(Blocks.DEEPSLATE).setModelType(ModelType.LOG).setMapColorHelper(MapColor.DEEPSLATE).build(),
            new BlockMaterialBuilder(Blocks.COBBLED_DEEPSLATE).setMapColorHelper(MapColor.DEEPSLATE).build(),
            new BlockMaterialBuilder(Blocks.POLISHED_DEEPSLATE).setModelType(ModelType.CUBE_ALL_FRAMED).setMapColorHelper(MapColor.DEEPSLATE).build(),
            new BlockMaterialBuilder(Blocks.DEEPSLATE_BRICKS).setMapColorHelper(MapColor.DEEPSLATE).build(),
            new BlockMaterialBuilder(Blocks.CRACKED_DEEPSLATE_BRICKS).setMapColorHelper(MapColor.DEEPSLATE).unSturdy().build(),
            new BlockMaterialBuilder(Blocks.DEEPSLATE_TILES).setMapColorHelper(MapColor.DEEPSLATE).build(),
            new BlockMaterialBuilder(Blocks.CRACKED_DEEPSLATE_TILES).setMapColorHelper(MapColor.DEEPSLATE).unSturdy().build(),
            new BlockMaterialBuilder(Blocks.CHISELED_DEEPSLATE).setMapColorHelper(MapColor.DEEPSLATE).isTotemTexture().build(),
            new BlockMaterialBuilder(Blocks.DRIPSTONE_BLOCK).setMapColorHelper(MapColor.TERRACOTTA_BROWN).build(),

            new BlockMaterialBuilder(Blocks.AMETHYST_BLOCK).setMapColorHelper(MapColor.COLOR_PURPLE).build(),
            new BlockMaterialBuilder(Blocks.OBSIDIAN).setMapColorHelper(MapColor.COLOR_BLACK).setToolLevel(ToolLevel.DIAMOND).isDragonImmune().build(),
            new BlockMaterialBuilder(Blocks.CRYING_OBSIDIAN).setMapColorHelper(MapColor.COLOR_BLACK).setToolLevel(ToolLevel.DIAMOND).isDragonImmune().build(),
            new BlockMaterialBuilder(Blocks.PRISMARINE).setMapColorHelper(MapColor.COLOR_CYAN).build(),
            new BlockMaterialBuilder(Blocks.PRISMARINE_BRICKS).setMapColorHelper(MapColor.DIAMOND).build(),
            new BlockMaterialBuilder(Blocks.DARK_PRISMARINE).setMapColorHelper(MapColor.DIAMOND).build(),

            new BlockMaterialBuilder(Blocks.BRICKS).setMapColorHelper(MapColor.COLOR_RED).build(),
            new BlockMaterialBuilder(Blocks.SANDSTONE).setModelType(ModelType.SANDSTONE).setMapColorHelper(MapColor.SAND).build(),
            new BlockMaterialBuilder(Blocks.CUT_SANDSTONE).setModelType(ModelType.SANDSTONE).setMapColorHelper(MapColor.SAND).build(),
            new BlockMaterialBuilder(Blocks.CHISELED_SANDSTONE).setModelType(ModelType.SANDSTONE).setMapColorHelper(MapColor.SAND).isTotemTexture().build(),
            new BlockMaterialBuilder(Blocks.SMOOTH_SANDSTONE).setModelType(ModelType.SANDSTONE).setMapColorHelper(MapColor.SAND).build(),
            new BlockMaterialBuilder(Blocks.RED_SANDSTONE).setModelType(ModelType.RED_SANDSTONE).setMapColorHelper(MapColor.COLOR_ORANGE).build(),
            new BlockMaterialBuilder(Blocks.CUT_RED_SANDSTONE).setModelType(ModelType.RED_SANDSTONE).setMapColorHelper(MapColor.COLOR_ORANGE).build(),
            new BlockMaterialBuilder(Blocks.CHISELED_RED_SANDSTONE).setModelType(ModelType.RED_SANDSTONE).setMapColorHelper(MapColor.COLOR_ORANGE).isTotemTexture().build(),
            new BlockMaterialBuilder(Blocks.SMOOTH_RED_SANDSTONE).setModelType(ModelType.RED_SANDSTONE).setMapColorHelper(MapColor.COLOR_ORANGE).build(),
            new BlockMaterialBuilder(Blocks.TERRACOTTA).setMapColorHelper(MapColor.COLOR_ORANGE).build(),

            new BlockMaterialBuilder(Blocks.NETHERRACK).setMapColorHelper(MapColor.NETHER).build(),
            new BlockMaterialBuilder(Blocks.NETHER_BRICKS).setMapColorHelper(MapColor.NETHER).build(),
            new BlockMaterialBuilder(Blocks.CHISELED_NETHER_BRICKS).setMapColorHelper(MapColor.NETHER).isTotemTexture().build(),
            new BlockMaterialBuilder(Blocks.CRACKED_NETHER_BRICKS).setMapColorHelper(MapColor.NETHER).unSturdy().build(),
            new BlockMaterialBuilder(Blocks.RED_NETHER_BRICKS).setMapColorHelper(MapColor.NETHER).build(),
            new BlockMaterialBuilder(Blocks.BLACKSTONE).setMapColorHelper(MapColor.COLOR_BLACK).build(),
            new BlockMaterialBuilder(Blocks.POLISHED_BLACKSTONE).setModelType(ModelType.CUBE_ALL_FRAMED).setMapColorHelper(MapColor.COLOR_BLACK).build(),
            new BlockMaterialBuilder(Blocks.POLISHED_BLACKSTONE_BRICKS).setMapColorHelper(MapColor.COLOR_BLACK).build(),
            new BlockMaterialBuilder(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS).setMapColorHelper(MapColor.COLOR_BLACK).unSturdy().build(),
            new BlockMaterialBuilder(Blocks.BASALT).setModelType(ModelType.PILLAR).setMapColorHelper(MapColor.COLOR_BLACK).build(),
            new BlockMaterialBuilder(Blocks.POLISHED_BASALT).setModelType(ModelType.PILLAR).setMapColorHelper(MapColor.COLOR_BLACK).build(),
            new BlockMaterialBuilder(Blocks.SMOOTH_BASALT).setMapColorHelper(MapColor.COLOR_BLACK).build(),
            new BlockMaterialBuilder(Blocks.MAGMA_BLOCK).setModelType(ModelType.CUSTOM).setMapColorHelper(MapColor.NETHER).build(),
            new BlockMaterialBuilder(Blocks.QUARTZ_BLOCK).setModelType(ModelType.CUSTOM).setMapColorHelper(MapColor.QUARTZ).build(),
            new BlockMaterialBuilder(Blocks.CHISELED_QUARTZ_BLOCK).setModelType(ModelType.LOG).setMapColorHelper(MapColor.QUARTZ).isTotemTexture().build(),
            new BlockMaterialBuilder(Blocks.QUARTZ_PILLAR).setModelType(ModelType.LOG).setMapColorHelper(MapColor.QUARTZ).build(),
            new BlockMaterialBuilder(Blocks.SMOOTH_QUARTZ).setModelType(ModelType.CUSTOM).setMapColorHelper(MapColor.QUARTZ).build(),
            new BlockMaterialBuilder(Blocks.QUARTZ_BRICKS).setMapColorHelper(MapColor.QUARTZ).build(),
            new BlockMaterialBuilder(Blocks.ANCIENT_DEBRIS).setModelType(ModelType.PILLAR).setMapColorHelper(MapColor.COLOR_BLACK).build(),

            new BlockMaterialBuilder(Blocks.END_STONE).setMapColorHelper(MapColor.SAND).build(),
            new BlockMaterialBuilder(Blocks.END_STONE_BRICKS).setMapColorHelper(MapColor.SAND).build(),
            new BlockMaterialBuilder(Blocks.PURPUR_BLOCK).setMapColorHelper(MapColor.COLOR_MAGENTA).build(),
            new BlockMaterialBuilder(Blocks.PURPUR_PILLAR).setModelType(ModelType.LOG).setMapColorHelper(MapColor.COLOR_MAGENTA).build(),

            new BlockMaterialBuilder(Blocks.ICE).setMaterialType(MaterialType.GLASS).setMapColorHelper(MapColor.ICE).build(),
            new BlockMaterialBuilder(Blocks.PACKED_ICE).setMapColorHelper(MapColor.ICE).build(),
            new BlockMaterialBuilder(Blocks.BLUE_ICE).setMapColorHelper(MapColor.ICE).build(),
            new BlockMaterialBuilder(Blocks.BONE_BLOCK).setModelType(ModelType.PILLAR).setMapColorHelper(MapColor.SAND).build(),
            new BlockMaterialBuilder(Blocks.HONEYCOMB_BLOCK).setMapColorHelper(MapColor.COLOR_ORANGE).build(),
            new BlockMaterialBuilder(Blocks.TUBE_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_BLUE).build(),
            new BlockMaterialBuilder(Blocks.DEAD_TUBE_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_GRAY).build(),
            new BlockMaterialBuilder(Blocks.BRAIN_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_PINK).build(),
            new BlockMaterialBuilder(Blocks.DEAD_BRAIN_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_GRAY).build(),
            new BlockMaterialBuilder(Blocks.BUBBLE_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_PURPLE).build(),
            new BlockMaterialBuilder(Blocks.DEAD_BUBBLE_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_GRAY).build(),
            new BlockMaterialBuilder(Blocks.FIRE_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_RED).build(),
            new BlockMaterialBuilder(Blocks.DEAD_FIRE_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_GRAY).build(),
            new BlockMaterialBuilder(Blocks.HORN_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_YELLOW).build(),
            new BlockMaterialBuilder(Blocks.DEAD_HORN_CORAL_BLOCK).setMapColorHelper(MapColor.COLOR_GRAY).build(),
            new BlockMaterialBuilder(Blocks.SCULK).setMaterialType(MaterialType.MOSSY).setMapColorHelper(MapColor.COLOR_BLACK).build(),
            new BlockMaterialBuilder(Blocks.SCULK_CATALYST).setMaterialType(MaterialType.MOSSY).setModelType(ModelType.UP_DOWN).setMapColorHelper(MapColor.COLOR_BLACK).build(),

            new BlockMaterialBuilder(Blocks.COAL_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.COLOR_BLACK).build(),
            new BlockMaterialBuilder(Blocks.RAW_COPPER_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.COLOR_ORANGE).build(),
            new BlockMaterialBuilder(Blocks.COPPER_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.COLOR_ORANGE).build(),
            new BlockMaterialBuilder(Blocks.RAW_IRON_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.RAW_IRON).build(),
            new BlockMaterialBuilder(Blocks.IRON_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.METAL).build(),
            new BlockMaterialBuilder(Blocks.RAW_GOLD_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.GOLD).build(),
            new BlockMaterialBuilder(Blocks.GOLD_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.GOLD).build(),
            new BlockMaterialBuilder(Blocks.DIAMOND_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.DIAMOND).build(),
            new BlockMaterialBuilder(Blocks.NETHERITE_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.COLOR_BLACK).build(),
            new BlockMaterialBuilder(Blocks.LAPIS_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.LAPIS).build(),
            new BlockMaterialBuilder(Blocks.EMERALD_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.EMERALD).build(),
            new BlockMaterialBuilder(Blocks.REDSTONE_BLOCK).setMaterialType(MaterialType.METAL).setMapColorHelper(MapColor.FIRE).build(),

            new BlockMaterialBuilder(FDBlocks.RAINBOW_TERRACOTTA).setPropertiesFrom(Blocks.WHITE_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_WHITE).isColored().build(),
            new BlockMaterialBuilder(Blocks.WHITE_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_WHITE).isColored().build(),
            new BlockMaterialBuilder(Blocks.ORANGE_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_ORANGE).isColored().build(),
            new BlockMaterialBuilder(Blocks.MAGENTA_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_MAGENTA).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIGHT_BLUE_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_LIGHT_BLUE).isColored().build(),
            new BlockMaterialBuilder(Blocks.YELLOW_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_YELLOW).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIME_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_LIGHT_GREEN).isColored().build(),
            new BlockMaterialBuilder(Blocks.PINK_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_PINK).isColored().build(),
            new BlockMaterialBuilder(Blocks.GRAY_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_GRAY).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIGHT_GRAY_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_LIGHT_GRAY).isColored().build(),
            new BlockMaterialBuilder(Blocks.CYAN_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_CYAN).isColored().build(),
            new BlockMaterialBuilder(Blocks.PURPLE_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_PURPLE).isColored().build(),
            new BlockMaterialBuilder(Blocks.BLUE_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_BLUE).isColored().build(),
            new BlockMaterialBuilder(Blocks.BROWN_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_BROWN).isColored().build(),
            new BlockMaterialBuilder(Blocks.GREEN_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_GREEN).isColored().build(),
            new BlockMaterialBuilder(Blocks.RED_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_RED).isColored().build(),
            new BlockMaterialBuilder(Blocks.BLACK_TERRACOTTA).setMapColorHelper(MapColor.TERRACOTTA_BLACK).isColored().build(),

            new BlockMaterialBuilder(FDBlocks.RAINBOW_CONCRETE).setPropertiesFrom(Blocks.WHITE_CONCRETE).setMapColorHelper(DyeColor.WHITE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.WHITE_CONCRETE).setMapColorHelper(DyeColor.WHITE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.ORANGE_CONCRETE).setMapColorHelper(DyeColor.ORANGE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.MAGENTA_CONCRETE).setMapColorHelper(DyeColor.MAGENTA.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIGHT_BLUE_CONCRETE).setMapColorHelper(DyeColor.LIGHT_BLUE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.YELLOW_CONCRETE).setMapColorHelper(DyeColor.YELLOW.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIME_CONCRETE).setMapColorHelper(DyeColor.LIME.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.PINK_CONCRETE).setMapColorHelper(DyeColor.PINK.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.GRAY_CONCRETE).setMapColorHelper(DyeColor.GRAY.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIGHT_GRAY_CONCRETE).setMapColorHelper(DyeColor.LIGHT_GRAY.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.CYAN_CONCRETE).setMapColorHelper(DyeColor.CYAN.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.PURPLE_CONCRETE).setMapColorHelper(DyeColor.PURPLE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.BLUE_CONCRETE).setMapColorHelper(DyeColor.BLUE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.BROWN_CONCRETE).setMapColorHelper(DyeColor.BROWN.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.GREEN_CONCRETE).setMapColorHelper(DyeColor.GREEN.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.RED_CONCRETE).setMapColorHelper(DyeColor.RED.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.BLACK_CONCRETE).setMapColorHelper(DyeColor.BLACK.getMapColor()).isColored().build(),

            new BlockMaterialBuilder(FDBlocks.RAINBOW_WOOL).setPropertiesFrom(Blocks.WHITE_WOOL).setMapColorHelper(MapColor.SNOW).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.WHITE_WOOL).setMapColorHelper(MapColor.SNOW).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.ORANGE_WOOL).setMapColorHelper(MapColor.COLOR_ORANGE).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.MAGENTA_WOOL).setMapColorHelper(MapColor.COLOR_MAGENTA).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIGHT_BLUE_WOOL).setMapColorHelper(MapColor.COLOR_LIGHT_BLUE).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.YELLOW_WOOL).setMapColorHelper(MapColor.COLOR_YELLOW).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIME_WOOL).setMapColorHelper(MapColor.COLOR_LIGHT_GREEN).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.PINK_WOOL).setMapColorHelper(MapColor.COLOR_PINK).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.GRAY_WOOL).setMapColorHelper(MapColor.COLOR_GRAY).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIGHT_GRAY_WOOL).setMapColorHelper(MapColor.COLOR_LIGHT_GRAY).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.CYAN_WOOL).setMapColorHelper(MapColor.COLOR_CYAN).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.PURPLE_WOOL).setMapColorHelper(MapColor.COLOR_PURPLE).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.BLUE_WOOL).setMapColorHelper(MapColor.COLOR_BLUE).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.BROWN_WOOL).setMapColorHelper(MapColor.COLOR_BROWN).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.GREEN_WOOL).setMapColorHelper(MapColor.COLOR_GREEN).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.RED_WOOL).setMapColorHelper(MapColor.COLOR_RED).setMaterialType(MaterialType.NONE).isColored().build(),
            new BlockMaterialBuilder(Blocks.BLACK_WOOL).setMapColorHelper(MapColor.COLOR_BLACK).setMaterialType(MaterialType.NONE).isColored().build(),

            new BlockMaterialBuilder(Blocks.GLASS).setMaterialType(MaterialType.GLASS).build(),
            new BlockMaterialBuilder(FDBlocks.RAINBOW_GLASS).setPropertiesFrom(Blocks.WHITE_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.WHITE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.WHITE_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.WHITE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.ORANGE_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.ORANGE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.MAGENTA_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.MAGENTA.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIGHT_BLUE_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.LIGHT_BLUE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.YELLOW_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.YELLOW.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIME_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.LIME.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.PINK_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.PINK.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.GRAY_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.GRAY.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.LIGHT_GRAY_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.LIGHT_GRAY.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.CYAN_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.CYAN.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.PURPLE_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.PURPLE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.BLUE_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.BLUE.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.BROWN_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.BROWN.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.GREEN_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.GREEN.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.RED_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.RED.getMapColor()).isColored().build(),
            new BlockMaterialBuilder(Blocks.BLACK_STAINED_GLASS).setMaterialType(MaterialType.GLASS).setMapColorHelper(DyeColor.BLACK.getMapColor()).isColored().build(),

            new BlockMaterialBuilder(Blocks.GLOWSTONE).setMapColorHelper(MapColor.SAND).isLamp().build(),
            new BlockMaterialBuilder(FDBlocks.SMOOTH_GLOWSTONE).setPropertiesFrom(Blocks.GLOWSTONE).setMapColorHelper(MapColor.SAND).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_SMOOTH_GLOWSTONE).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).strength(1.5F, 6.0F)).setMapColorHelper(MapColor.SAND).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().build(),
            new BlockMaterialBuilder(Blocks.SEA_LANTERN).setMapColorHelper(MapColor.QUARTZ).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F)).setMapColorHelper(MapColor.QUARTZ).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().build(),
            new BlockMaterialBuilder(FDBlocks.RAINBOW_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.SNOW)).setMapColorHelper(MapColor.SNOW).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.RAINBOW_REINFORCED_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.SNOW)).setMapColorHelper(MapColor.SNOW).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.WHITE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.SNOW)).setMapColorHelper(MapColor.SNOW).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_WHITE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.SNOW)).setMapColorHelper(MapColor.SNOW).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.ORANGE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_ORANGE).lightLevel(value -> 11)).setMapColorHelper(MapColor.COLOR_ORANGE).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_ORANGE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_ORANGE).lightLevel(value -> 11)).setMapColorHelper(MapColor.COLOR_ORANGE).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.MAGENTA_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_MAGENTA).lightLevel(value -> 10)).setMapColorHelper(MapColor.COLOR_MAGENTA).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_MAGENTA_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_MAGENTA).lightLevel(value -> 10)).setMapColorHelper(MapColor.COLOR_MAGENTA).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.LIGHT_BLUE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel(value -> 11)).setMapColorHelper(MapColor.COLOR_LIGHT_BLUE).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_LIGHT_BLUE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel(value -> 11)).setMapColorHelper(MapColor.COLOR_LIGHT_BLUE).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.YELLOW_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_YELLOW).lightLevel(value -> 14)).setMapColorHelper(MapColor.COLOR_YELLOW).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_YELLOW_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_YELLOW).lightLevel(value -> 14)).setMapColorHelper(MapColor.COLOR_YELLOW).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.LIME_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel(value -> 12)).setMapColorHelper(MapColor.COLOR_LIGHT_GREEN).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_LIME_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel(value -> 12)).setMapColorHelper(MapColor.COLOR_LIGHT_GREEN).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.PINK_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_PINK).lightLevel(value -> 11)).setMapColorHelper(MapColor.COLOR_PINK).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_PINK_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_PINK).lightLevel(value -> 11)).setMapColorHelper(MapColor.COLOR_PINK).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.GRAY_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_GRAY).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_GRAY).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_GRAY_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_GRAY).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_GRAY).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.LIGHT_GRAY_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_LIGHT_GRAY).lightLevel(value -> 11)).setMapColorHelper(MapColor.COLOR_LIGHT_GRAY).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_LIGHT_GRAY_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_LIGHT_GRAY).lightLevel(value -> 11)).setMapColorHelper(MapColor.COLOR_LIGHT_GRAY).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.CYAN_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_CYAN).lightLevel(value -> 10)).setMapColorHelper(MapColor.COLOR_CYAN).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_CYAN_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_CYAN).lightLevel(value -> 10)).setMapColorHelper(MapColor.COLOR_CYAN).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.PURPLE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_PURPLE).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_PURPLE).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_PURPLE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_PURPLE).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_PURPLE).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.BLUE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_BLUE).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_BLUE_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_BLUE).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.BROWN_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_BROWN).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_BROWN).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_BROWN_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_BROWN).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_BROWN).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.GREEN_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_GREEN).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_GREEN).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_GREEN_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_GREEN).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_GREEN).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.RED_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_RED).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_RED).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_RED_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_RED).lightLevel(value -> 9)).setMapColorHelper(MapColor.COLOR_RED).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.BLACK_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_BLACK).lightLevel(value -> 8)).setMapColorHelper(MapColor.COLOR_BLACK).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.REINFORCED_BLACK_SEA_LANTERN).setProperties(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_BLACK).lightLevel(value -> 8)).setMapColorHelper(MapColor.COLOR_BLACK).setModelType(ModelType.CUBE_ALL_FRAMED).isLamp().isColored().build(),
            new BlockMaterialBuilder(Blocks.JACK_O_LANTERN).setMaterialType(MaterialType.WOOD).setModelType(ModelType.PUMPKIN_LAMP).setMapColorHelper(MapColor.COLOR_ORANGE).isLamp().build(),
            new BlockMaterialBuilder(Blocks.REDSTONE_LAMP).setProperties(() -> BlockBehaviour.Properties.of().lightLevel(value -> 15).mapColor(MapColor.COLOR_ORANGE)).setModelType(ModelType.REDSTONE_LAMP).isLamp().build(),
            new BlockMaterialBuilder(Blocks.SHROOMLIGHT).setMaterialType(MaterialType.MOSSY).setMapColorHelper(MapColor.COLOR_ORANGE).isLamp().build(),
            new BlockMaterialBuilder(Blocks.OCHRE_FROGLIGHT).setMaterialType(MaterialType.NONE).setModelType(ModelType.PILLAR).setMapColorHelper(MapColor.SAND).isLamp().build(),
            new BlockMaterialBuilder(Blocks.VERDANT_FROGLIGHT).setMaterialType(MaterialType.NONE).setModelType(ModelType.PILLAR).setMapColorHelper(MapColor.GLOW_LICHEN).isLamp().build(),
            new BlockMaterialBuilder(Blocks.PEARLESCENT_FROGLIGHT).setMaterialType(MaterialType.NONE).setModelType(ModelType.PILLAR).setMapColorHelper(MapColor.COLOR_PINK).isLamp().build(),
            new BlockMaterialBuilder(FDBlocks.RAINBOW_LAMP).setProperties(() -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.SNOW).lightLevel(value -> 10).sound(SoundType.AMETHYST)).setMapColorHelper(MapColor.SNOW).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.RED_LAMP).setProperties(() -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_RED).lightLevel(value -> 5).sound(SoundType.AMETHYST)).setMapColorHelper(MapColor.COLOR_RED).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.GREEN_LAMP).setProperties(() -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_GREEN).lightLevel(value -> 5).sound(SoundType.AMETHYST)).setMapColorHelper(MapColor.COLOR_GREEN).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.BLUE_LAMP).setProperties(() -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 5).sound(SoundType.AMETHYST)).setMapColorHelper(MapColor.COLOR_BLUE).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.YELLOW_LAMP).setProperties(() -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_YELLOW).lightLevel(value -> 10).sound(SoundType.AMETHYST)).setMapColorHelper(MapColor.COLOR_YELLOW).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.CYAN_LAMP).setProperties(() -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_CYAN).lightLevel(value -> 10).sound(SoundType.AMETHYST)).setMapColorHelper(MapColor.COLOR_CYAN).isLamp().isColored().build(),
            new BlockMaterialBuilder(FDBlocks.PURPLE_LAMP).setProperties(() -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_PURPLE).lightLevel(value -> 10).sound(SoundType.AMETHYST)).setMapColorHelper(MapColor.COLOR_PURPLE).isLamp().isColored().build(),


    };

    private final Supplier<Block> materialBlockSupplier;
    private final ItemLike blockItemLike;
    private final ResourceLocation id;
    private final Supplier<BlockBehaviour.Properties> propertiesSupplier;
    private final MaterialType materialType;
    private final ToolLevel toolLevel;
    private final ModelType modelType;
    private final MapColorHelper mapColorHelper;
    private final boolean sturdy;
    private final boolean colored;
    private final boolean totemTexture;
    private final boolean dragonImmune;
    private final boolean witherImmune;
    private final boolean nonFlammableWood;
    private final boolean isLamp;
    public final boolean allowVanillaSlab;
    public final boolean vanillaPlanksLike;
    public final boolean allowLampInGlass;
    public final boolean allowPeepWindow;
    public final boolean allowGuardrail;
    public final boolean allowCutBlock;
    public final boolean allowPillar;
    public final boolean allowHorizonPanel;
    public final boolean allowTable;
    public final boolean allowChair;
    public final boolean allowGardenChair;

    private BlockMaterial(
            Supplier<Block> materialBlockSupplier, ItemLike blockItemLike, ResourceLocation id, Supplier<BlockBehaviour.Properties> propertiesSupplier,
            MaterialType materialType, ToolLevel toolLevel, ModelType modelType, MapColorHelper mapColorHelper, boolean sturdy, boolean colored,
            boolean totemTexture, boolean dragonImmune, boolean witherImmune, boolean nonFlammableWood, boolean isLamp
    ) {
        this.materialBlockSupplier = materialBlockSupplier;
        this.blockItemLike = blockItemLike;
        this.id = id;
        this.propertiesSupplier = propertiesSupplier;
        this.materialType = materialType;
        this.toolLevel = toolLevel;
        this.modelType = modelType;
        this.mapColorHelper = mapColorHelper;
        this.sturdy = sturdy;
        this.colored = colored;
        this.totemTexture = totemTexture;
        this.dragonImmune = dragonImmune;
        this.witherImmune = witherImmune;
        this.nonFlammableWood = nonFlammableWood;
        this.isLamp = isLamp;
        this.allowVanillaSlab = allowVanillaSlab();
        this.vanillaPlanksLike = vanillaPlanksLike();
        this.allowLampInGlass = allowLampInGlass();
        this.allowPeepWindow = allowPeepWindow();
        this.allowGuardrail = allowGuardrail();
        this.allowCutBlock = allowCutBlock();
        this.allowHorizonPanel = allowHorizonPanel();
        this.allowPillar = allowPillar();
        this.allowTable = allowTable();
        this.allowChair = allowChair();
        this.allowGardenChair = allowGardenChair();
    }

    private boolean allowLampInGlass() {
        return isLamp;
    }

    private boolean allowPeepWindow() {
        return switch (materialType) {
            case WOOD, DIRT, STONE, GLASS, MOSSY -> !totemTexture && !isLamp;
            default -> false;
        };
    }

    private boolean allowGuardrail() {
        boolean flag = materialType.equals(MaterialType.WOOD) && isSturdy();
        boolean flag1 = materialType.equals(MaterialType.STONE) && isSturdy() && !totemTexture && !modelType.equals(ModelType.NYLIUM);
        boolean flag2 = flag || flag1 || materialType.equals(MaterialType.GLASS);
        return flag2 && !isLamp();
    }

    private boolean allowCutBlock() {
        return switch (modelType) {
            case FACING, REDSTONE_LAMP, PUMPKIN_LAMP -> false;
            default -> !materialType.equals(MaterialType.GLASS);
        };
    }

    private boolean allowPillar() {
        boolean wood = materialType.equals(MaterialType.WOOD) && sturdy;
        boolean flag1 = switch (materialType) {
            case DIRT, STONE, MOSSY -> true;
            default -> false;
        };
        boolean model = switch (modelType) {
            case CUBE_ALL, CUBE_ALL_FRAMED, LOG, PILLAR -> true;
            default -> false;
        };
        boolean flag3 = switch (id.toString()) {
            case "minecraft:sandstone", "minecraft:red_sandstone", "minecraft:smooth_sandstone", "minecraft:smooth_red_sandstone" -> true;
            default -> false;
        };
        return (wood || flag1) && (model && !totemTexture && !isLamp) || flag3;
    }

    private boolean allowTable() {
        boolean wood = materialType.equals(MaterialType.WOOD) && sturdy;
        boolean flag1 = switch (materialType) {
            case STONE, GLASS -> true;
            default -> false;
        };
        boolean model = switch (modelType) {
            case CUBE_ALL, CUBE_ALL_FRAMED, LOG, PILLAR, UP_DOWN -> true;
            default -> false;
        };
        boolean flag3 = switch (id.toString()) {
            case "minecraft:sandstone", "minecraft:red_sandstone", "minecraft:smooth_sandstone", "minecraft:smooth_red_sandstone",
                    "minecraft:quartz_block", "minecraft:smooth_quartz" -> true;
            default -> false;
        };
        return (wood || flag1) && (model && !totemTexture && !isLamp) || flag3;
    }

    private boolean allowChair() {
        boolean wood = materialType.equals(MaterialType.WOOD) && sturdy;
        boolean model = switch (modelType) {
            case CUBE_ALL, CUBE_ALL_FRAMED, LOG, PILLAR, UP_DOWN -> true;
            default -> false;
        };
        boolean flag3 = switch (id.toString()) {
            case "minecraft:sandstone", "minecraft:red_sandstone", "minecraft:smooth_sandstone", "minecraft:smooth_red_sandstone",
                    "minecraft:quartz_block", "minecraft:smooth_quartz" -> true;
            default -> false;
        };
        return (wood || materialType.equals(MaterialType.STONE)) && (model && !totemTexture && !isLamp) || flag3;
    }

    private boolean allowGardenChair() {
        boolean model = switch (modelType) {
            case CUBE_ALL, CUBE_ALL_FRAMED, UP_DOWN -> true;
            default -> false;
        };
        boolean flag3 = switch (id.toString()) {
            case "minecraft:sandstone", "minecraft:red_sandstone", "minecraft:smooth_sandstone", "minecraft:smooth_red_sandstone",
                    "minecraft:quartz_block", "minecraft:smooth_quartz" -> true;
            default -> false;
        };
        return (vanillaPlanksLike || materialType.equals(MaterialType.STONE)) && (model && !totemTexture && !isLamp) || flag3;
    }

    private boolean allowVanillaSlab() {
        return !getNamespace().equals("minecraft") && isSturdy() && !isLamp;
    }

    private boolean vanillaPlanksLike() {
        return !getNamespace().equals("minecraft") && materialType.equals(MaterialType.WOOD) && getPath().contains("planks");
    }

    private boolean allowHorizonPanel() {
        return materialType.equals(MaterialType.GLASS);
    }

    public Block getMaterialBlock() {
        return materialBlockSupplier.get();
    }

    public ItemLike getBlockItemLike() {
        return blockItemLike;
    }

    public BlockBehaviour.Properties getProperties() {
        return propertiesSupplier.get();
    }

    public BlockBehaviour.Properties getLampInGlassProperties() {
        return mapColorHelper.applyLampInGlassMapColor(propertiesSupplier.get());
    }

    public BlockBehaviour.Properties getTableProperties() {
        return mapColorHelper.applyTableMapColor(propertiesSupplier.get());
    }

    public BlockBehaviour.Properties getPeepWindowProperties() {
        return mapColorHelper.applyPeepWindowMapColor(propertiesSupplier.get());
    }

    public BlockBehaviour.Properties getCutBlockProperties() {
        return mapColorHelper.applyDefaultMapColor(propertiesSupplier.get());
    }

    public BlockBehaviour.Properties getPillarProperties() {
        return mapColorHelper.applyPillarMapColor(propertiesSupplier.get());
    }

    public BlockBehaviour.Properties getHorizonPanelProperties() {
        return propertiesSupplier.get().mapColor(MapColor.NONE);
    }

    public ResourceLocation getId() {
        return id;
    }

    public String getNamespace() {
        return id.getNamespace();
    }

    public String getPath() {
        return id.getPath();
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public ToolLevel getToolLevel() {
        return toolLevel;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public MapColorHelper getMapColorHelper() {
        return mapColorHelper;
    }

    public boolean isSturdy() {
        return sturdy && materialType.sturdy;
    }

    public boolean isColored() {
        return colored;
    }

    public boolean isTotemTexture() {
        return totemTexture;
    }

    public boolean isDragonImmune() {
        return dragonImmune;
    }

    public boolean isWitherImmune() {
        return witherImmune;
    }

    public boolean isNonFlammableWood() {
        return nonFlammableWood;
    }

    public boolean isLamp() {
        return isLamp;
    }


    public enum MaterialType {
        WOOD,
        DIRT(false),
        GRASS(false),
        STONE,
        METAL,
        GLASS(false),
        MOSSY(false),
        NONE;

        private final boolean sturdy;

        MaterialType() {
            this.sturdy = true;
        }

        MaterialType(boolean sturdy) {
            this.sturdy = sturdy;
        }
    }

    public enum ModelType {
        CUBE_ALL,
        CUBE_ALL_FRAMED,
        UP_DOWN,
        GRASS,
        NYLIUM,
        LOG,
        PILLAR,
        FACING,
        SANDSTONE,
        RED_SANDSTONE,
        REDSTONE_LAMP,
        PUMPKIN_LAMP,
        CUSTOM
    }

    public enum ToolLevel {
        NONE,
        STONE,
        IRON,
        DIAMOND
    }

    public abstract static class MapColorHelper {


        public abstract BlockBehaviour.Properties applyLampInGlassMapColor(BlockBehaviour.Properties properties);
        public abstract BlockBehaviour.Properties applyPeepWindowMapColor(BlockBehaviour.Properties properties);
        public abstract BlockBehaviour.Properties applyTableMapColor(BlockBehaviour.Properties properties);
        public abstract BlockBehaviour.Properties applyDefaultMapColor(BlockBehaviour.Properties properties);
        public abstract BlockBehaviour.Properties applyPillarMapColor(BlockBehaviour.Properties properties);

        protected static BlockBehaviour.Properties peepWindow(BlockBehaviour.Properties properties, MapColor mapColor) {
            return properties.mapColor(state -> state.getValue(BlockStateProperties.FACING).getAxis().equals(Direction.Axis.Y) ? mapColor : MapColor.NONE);
        }

        protected static BlockBehaviour.Properties pillar(BlockBehaviour.Properties properties, MapColor top, MapColor side) {
            return properties.mapColor(state -> state.getValue(BlockStateProperties.AXIS).equals(Direction.Axis.Y) ? top : side);
        }
    }

    public static class NoMapColorHelper extends MapColorHelper {


        @Override
        public BlockBehaviour.Properties applyLampInGlassMapColor(BlockBehaviour.Properties properties) {
            return properties;
        }

        @Override
        public BlockBehaviour.Properties applyPeepWindowMapColor(BlockBehaviour.Properties properties) {
            return properties;
        }

        @Override
        public BlockBehaviour.Properties applyTableMapColor(BlockBehaviour.Properties properties) {
            return properties;
        }

        @Override
        public BlockBehaviour.Properties applyDefaultMapColor(BlockBehaviour.Properties properties) {
            return properties;
        }

        @Override
        public BlockBehaviour.Properties applyPillarMapColor(BlockBehaviour.Properties properties) {
            return properties;
        }
    }

    public static class CubeMapColorHelper extends MapColorHelper {


        private final MapColor mapColor;

        public CubeMapColorHelper(MapColor mapColor) {
            this.mapColor = mapColor;
        }

        @Override
        public BlockBehaviour.Properties applyLampInGlassMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(mapColor);
        }

        @Override
        public BlockBehaviour.Properties applyPeepWindowMapColor(BlockBehaviour.Properties properties) {
            return peepWindow(properties, mapColor);
        }

        @Override
        public BlockBehaviour.Properties applyTableMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(mapColor);
        }

        @Override
        public BlockBehaviour.Properties applyDefaultMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(mapColor);
        }

        @Override
        public BlockBehaviour.Properties applyPillarMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(mapColor);
        }
    }

    public static class PillarMapColorHelper extends MapColorHelper {


        private final MapColor topColor;
        private final MapColor sideColor;

        public PillarMapColorHelper(MapColor topColor, MapColor sideColor) {
            this.topColor = topColor;
            this.sideColor = sideColor;
        }

        @Override
        public BlockBehaviour.Properties applyLampInGlassMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(state -> state.getValue(BlockStateProperties.FACING).getAxis().equals(Direction.Axis.Y) ? topColor : sideColor);
        }

        @Override
        public BlockBehaviour.Properties applyPeepWindowMapColor(BlockBehaviour.Properties properties) {
            return peepWindow(properties, topColor);
        }

        @Override
        public BlockBehaviour.Properties applyTableMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(sideColor);
        }

        @Override
        public BlockBehaviour.Properties applyDefaultMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(topColor);
        }

        @Override
        public BlockBehaviour.Properties applyPillarMapColor(BlockBehaviour.Properties properties) {
            return pillar(properties, topColor, sideColor);
        }
    }

    public static class UpDownMapColorHelper extends MapColorHelper {


        private final MapColor topColor;
        private final MapColor sideColor;
        private final MapColor downColor;

        public UpDownMapColorHelper(MapColor topColor, MapColor sideColor, MapColor downColor) {
            this.topColor = topColor;
            this.sideColor = sideColor;
            this.downColor = downColor;
        }

        @Override
        public BlockBehaviour.Properties applyLampInGlassMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(state -> {
                Direction facing = state.getValue(BlockStateProperties.FACING);
                return facing.equals(Direction.UP) ? topColor : facing.equals(Direction.DOWN) ? downColor : sideColor;
            });
        }

        @Override
        public BlockBehaviour.Properties applyPeepWindowMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(state -> switch (state.getValue(BlockStateProperties.FACING)) {
                case DOWN -> downColor;
                case UP -> topColor;
                default -> MapColor.NONE;
            });
        }

        @Override
        public BlockBehaviour.Properties applyTableMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(topColor);
        }

        @Override
        public BlockBehaviour.Properties applyDefaultMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(topColor);
        }

        @Override
        public BlockBehaviour.Properties applyPillarMapColor(BlockBehaviour.Properties properties) {
            return properties.mapColor(topColor);
        }
    }


    public static class BlockMaterialBuilder {


        private final Supplier<Block> materialBlockSupplier;
        private final ItemLike blockItemLike;
        private final ResourceLocation id;
        private Supplier<BlockBehaviour.Properties> propertiesSupplier;
        private MaterialType materialType = MaterialType.STONE;
        private ToolLevel toolLevel = ToolLevel.NONE;
        private ModelType modelType = ModelType.CUBE_ALL;
        private MapColorHelper mapColorHelper = new NoMapColorHelper();
        private boolean sturdy = true;
        private boolean colored = false;
        private boolean totemTexture = false;
        private boolean dragonImmune = false;
        private boolean witherImmune = false;
        private boolean nonFlammableWood = false;
        private boolean isLamp = false;

        public BlockMaterialBuilder(Block block) {
            this.materialBlockSupplier = () -> block;
            this.blockItemLike = block;
            this.id = BuiltInRegistries.BLOCK.getKey(block);
            this.propertiesSupplier = () -> BlockBehaviour.Properties.ofFullCopy(this.materialBlockSupplier.get());
        }

        public BlockMaterialBuilder(DeferredBlock<Block> materialBlock) {
            this.materialBlockSupplier = materialBlock;
            this.blockItemLike = materialBlock;
            this.id = materialBlock.getId();
            this.propertiesSupplier = () -> BlockBehaviour.Properties.ofFullCopy(this.materialBlockSupplier.get());
        }

        public BlockMaterialBuilder setProperties(Supplier<BlockBehaviour.Properties> propertiesSupplier) {
            this.propertiesSupplier = propertiesSupplier;
            return this;
        }

        public BlockMaterialBuilder setPropertiesFrom(Block block) {
            this.propertiesSupplier = () -> BlockBehaviour.Properties.ofFullCopy(block);
            return this;
        }

        public BlockMaterialBuilder setMaterialType(MaterialType materialType) {
            this.materialType = materialType;
            return this;
        }

        public BlockMaterialBuilder setToolLevel(ToolLevel toolLevel) {
            this.toolLevel = toolLevel;
            return this;
        }

        public BlockMaterialBuilder setModelType(ModelType modelType) {
            this.modelType = modelType;
            return this;
        }

        public BlockMaterialBuilder setMapColorHelper(MapColor mapColor) {
            this.mapColorHelper = new CubeMapColorHelper(mapColor);
            return this;
        }

        public BlockMaterialBuilder setMapColorHelper(MapColorHelper mapColorHelper) {
            this.mapColorHelper = mapColorHelper;
            return this;
        }

        public BlockMaterialBuilder unSturdy() {
            this.sturdy = false;
            return this;
        }

        public BlockMaterialBuilder isColored() {
            this.colored = true;
            return this;
        }

        public BlockMaterialBuilder isTotemTexture() {
            this.totemTexture = true;
            return this;
        }

        public BlockMaterialBuilder isDragonImmune() {
            this.dragonImmune = true;
            return this;
        }

        public BlockMaterialBuilder isWitherImmune() {
            this.witherImmune = true;
            return this;
        }

        public BlockMaterialBuilder isNonFlammableWood() {
            this.nonFlammableWood = true;
            return this;
        }

        public BlockMaterialBuilder isLamp() {
            this.isLamp = true;
            return this;
        }

        public BlockMaterial build() {
            return new BlockMaterial(materialBlockSupplier, blockItemLike, id, propertiesSupplier, materialType, toolLevel, modelType, mapColorHelper, sturdy,
                                     colored, totemTexture, dragonImmune, witherImmune, nonFlammableWood, isLamp
            );
        }
    }
}
