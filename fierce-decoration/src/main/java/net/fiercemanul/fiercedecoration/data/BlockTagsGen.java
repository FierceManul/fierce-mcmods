package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.tags.FDBlockTags;
import net.fiercemanul.fiercedecoration.world.level.block.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.Tags;
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
        super(output, lookupProvider, FierceDecoration.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                FDBlocks.SOUL_CRYSTAL_ORNAMENT.get(),
                FDBlocks.ITEM_FRAME_SHELL_THIN.get(),
                FDBlocks.ITEM_FRAME_SHELL_BIG.get(),
                FDBlocks.STAR_BLOCK.get(),
                FDBlocks.FIREPLACE_HEART.get(),
                FDBlocks.SMOOTH_GLOWSTONE.get(),
                FDBlocks.REINFORCED_SMOOTH_GLOWSTONE.get(),
                FDBlocks.REINFORCED_SEA_LANTERN.get(),
                FDBlocks.WATERLOGGED_COBBLESTONE.get(),
                FDBlocks.WALL_FLOWER_POT_A.get(),
                FDBlocks.WALL_FLOWER_POT_B.get(),
                FDBlocks.WALL_FLOWER_POT_C.get(),
                FDBlocks.WALL_FLOWER_POT_D.get(),
                FDBlocks.WALL_FLOWER_POT_E.get(),
                FDBlocks.WALL_FLOWER_POT_F.get(),
                FDBlocks.HEAVY_CHAINS_BLOCK.get(),
                FDBlocks.RAINBOW_CONCRETE.get(),
                FDBlocks.RAINBOW_TERRACOTTA.get(),
                FDBlocks.RAINBOW_GLASS.get(),
                FDBlocks.RAINBOW_SEA_LANTERN.get(),
                FDBlocks.RAINBOW_REINFORCED_SEA_LANTERN.get(),
                FDBlocks.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE.get(),
                FDBlocks.SPRUCE_PLANKS_AND_GRAY_CONCRETE.get(),
                FDBlocks.WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE.get(),
                FDBlocks.DEEPSLATE_TILES_AND_SPRUCE_PLANKS.get(),
                FDBlocks.DEEPSLATE_TILES_AND_MANGROVE_PLANKS.get(),
                FDBlocks.DARK_PRISMARINE_AND_SPRUCE_PLANKS.get(),
                FDBlocks.DARK_PRISMARINE_AND_MANGROVE_PLANKS.get(),
                FDBlocks.BRICKS_AND_BIRCH_PLANKS.get(),
                FDBlocks.FAKE_HOPPER.get(),
                FDBlocks.FAKE_FURNACE.get(),
                FDBlocks.LIT_FAKE_FURNACE.get(),
                FDBlocks.FAKE_BLAST_FURNACE.get(),
                FDBlocks.LIT_FAKE_BLAST_FURNACE.get(),
                FDBlocks.FAKE_SMOKER.get(),
                FDBlocks.LIT_FAKE_SMOKER.get(),
                FDBlocks.FAKE_GOLD_BLOCK.get(),
                FDBlocks.FAKE_IRON_BLOCK.get(),
                FDBlocks.FAKE_DIAMOND_BLOCK.get(),
                FDBlocks.FAKE_NETHERITE_BLOCK.get(),
                FDBlocks.FAKE_BEDROCK.get(),
                FDBlocks.TEXTURE_FURNACE.get(),
                FDBlocks.TEXTURE_FURNACE_TOP.get(),
                FDBlocks.TEXTURE_BLAST_FURNACE.get(),
                FDBlocks.TEXTURE_BLAST_FURNACE_TOP.get(),
                FDBlocks.TEXTURE_SMOKER.get(),
                FDBlocks.TEXTURE_SMITHING_TABLE_TOP.get(),
                FDBlocks.TEXTURE_LODESTONE.get(),
                FDBlocks.TEXTURE_LODESTONE_SIDE.get(),
                FDBlocks.TEXTURE_LODESTONE_TOP.get(),
                FDBlocks.WHITE_SEA_LANTERN.get(),
                FDBlocks.ORANGE_SEA_LANTERN.get(),
                FDBlocks.MAGENTA_SEA_LANTERN.get(),
                FDBlocks.LIGHT_BLUE_SEA_LANTERN.get(),
                FDBlocks.YELLOW_SEA_LANTERN.get(),
                FDBlocks.LIME_SEA_LANTERN.get(),
                FDBlocks.PINK_SEA_LANTERN.get(),
                FDBlocks.GRAY_SEA_LANTERN.get(),
                FDBlocks.LIGHT_GRAY_SEA_LANTERN.get(),
                FDBlocks.CYAN_SEA_LANTERN.get(),
                FDBlocks.PURPLE_SEA_LANTERN.get(),
                FDBlocks.BLUE_SEA_LANTERN.get(),
                FDBlocks.BROWN_SEA_LANTERN.get(),
                FDBlocks.GREEN_SEA_LANTERN.get(),
                FDBlocks.RED_SEA_LANTERN.get(),
                FDBlocks.BLACK_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_WHITE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_ORANGE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_MAGENTA_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_LIGHT_BLUE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_YELLOW_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_LIME_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_PINK_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_GRAY_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_LIGHT_GRAY_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_CYAN_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_PURPLE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_BLUE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_BROWN_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_GREEN_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_RED_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_BLACK_SEA_LANTERN.get(),
                FDBlocks.RED_LAMP.get(),
                FDBlocks.GREEN_LAMP.get(),
                FDBlocks.BLUE_LAMP.get(),
                FDBlocks.YELLOW_LAMP.get(),
                FDBlocks.CYAN_LAMP.get(),
                FDBlocks.PURPLE_LAMP.get(),
                FDBlocks.RAINBOW_LAMP.get(),
                FDBlocks.ROCK_PATH.get()
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                FDBlocks.SMOOTH_OAK_PLANKS.get(),
                FDBlocks.SMOOTH_SPRUCE_PLANKS.get(),
                FDBlocks.SMOOTH_BIRCH_PLANKS.get(),
                FDBlocks.SMOOTH_JUNGLE_PLANKS.get(),
                FDBlocks.SMOOTH_ACACIA_PLANKS.get(),
                FDBlocks.SMOOTH_DARK_OAK_PLANKS.get(),
                FDBlocks.SMOOTH_MANGROVE_PLANKS.get(),
                FDBlocks.SMOOTH_CHERRY_PLANKS.get(),
                FDBlocks.SMOOTH_CRIMSON_PLANKS.get(),
                FDBlocks.SMOOTH_WARPED_PLANKS.get(),
                FDBlocks.GREEN_FUN_ROOF.get(),
                FDBlocks.FIREWOOD.get(),
                FDBlocks.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE.get(),
                FDBlocks.SPRUCE_PLANKS_AND_GRAY_CONCRETE.get(),
                FDBlocks.OAK_PLANKS_AND_SPRUCE_PLANKS.get(),
                FDBlocks.DEEPSLATE_TILES_AND_SPRUCE_PLANKS.get(),
                FDBlocks.DEEPSLATE_TILES_AND_MANGROVE_PLANKS.get(),
                FDBlocks.DARK_PRISMARINE_AND_SPRUCE_PLANKS.get(),
                FDBlocks.DARK_PRISMARINE_AND_MANGROVE_PLANKS.get(),
                FDBlocks.BRICKS_AND_BIRCH_PLANKS.get(),
                FDBlocks.FAKE_BARREL.get(),
                FDBlocks.FAKE_CAMPFIRE.get(),
                FDBlocks.LIT_FAKE_CAMPFIRE.get(),
                FDBlocks.LIT_FAKE_SOUL_CAMPFIRE.get(),
                FDBlocks.FAKE_CHEST.get(),
                FDBlocks.FAKE_CHISELED_BOOKSHELF.get(),
                FDBlocks.FAKE_LECTERN.get(),
                FDBlocks.FAKE_BEEHIVE.get(),
                FDBlocks.TEXTURE_CHISELED_BOOKSHELF.get(),
                FDBlocks.TEXTURE_CHISELED_BOOKSHELF_TOP.get(),
                FDBlocks.TEXTURE_CHISELED_BOOKSHELF_SIDE.get(),
                FDBlocks.TEXTURE_LOOM.get(),
                FDBlocks.TEXTURE_BEEHIVE_TOP.get(),
                FDBlocks.TEXTURE_SMITHING_TABLE_BOTTOM.get(),
                FDBlocks.TEXTURE_COMPOSTER_BOTTOM.get(),
                FDBlocks.TEXTURE_BEE_NEST_TOP.get(),
                FDBlocks.CRAFTING_PAD.get(),
                FDBlocks.CRAFTING_DESK.get(),
                FDBlocks.CRAFTING_BLOCK.get()
        );
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                FDBlocks.ROTTEN_FLESH_BLOCK.get(),
                FDBlocks.HALF_GRASS_BLOCK.get(),
                FDBlocks.HALF_PODZOL.get(),
                FDBlocks.HALF_MYCELIUM.get(),
                FDBlocks.HALF_PATH_BLOCK.get()
        );
        tag(BlockTags.DIRT).add(
                FDBlocks.HALF_GRASS_BLOCK.get(),
                FDBlocks.HALF_PODZOL.get(),
                FDBlocks.HALF_MYCELIUM.get()
        );
        tag(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(
                FDBlocks.HALF_GRASS_BLOCK.get(),
                FDBlocks.HALF_PODZOL.get(),
                FDBlocks.HALF_MYCELIUM.get()
        );
        tag(BlockTags.MINEABLE_WITH_HOE).add(
                FDBlocks.FOX_CARROT_SHEAF.get(),
                FDBlocks.FOX_CARROT_BASKET.get()
        );
        tag(BlockTags.PLANKS).add(
                FDBlocks.SMOOTH_OAK_PLANKS.get(),
                FDBlocks.SMOOTH_SPRUCE_PLANKS.get(),
                FDBlocks.SMOOTH_BIRCH_PLANKS.get(),
                FDBlocks.SMOOTH_JUNGLE_PLANKS.get(),
                FDBlocks.SMOOTH_ACACIA_PLANKS.get(),
                FDBlocks.SMOOTH_DARK_OAK_PLANKS.get(),
                FDBlocks.SMOOTH_MANGROVE_PLANKS.get(),
                FDBlocks.SMOOTH_CHERRY_PLANKS.get(),
                FDBlocks.SMOOTH_CRIMSON_PLANKS.get(),
                FDBlocks.SMOOTH_WARPED_PLANKS.get()
        );
        tag(BlockTags.CAMPFIRES).add(
                FDBlocks.FIREPLACE_HEART.get()
        );
        tag(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON).add(
                FDBlocks.HALF_GRASS_BLOCK.get()
        );
        tag(FDBlockTags.SEA_LANTERN_TAG).add(
                Blocks.SEA_LANTERN,
                FDBlocks.WHITE_SEA_LANTERN.get(),
                FDBlocks.ORANGE_SEA_LANTERN.get(),
                FDBlocks.MAGENTA_SEA_LANTERN.get(),
                FDBlocks.LIGHT_BLUE_SEA_LANTERN.get(),
                FDBlocks.YELLOW_SEA_LANTERN.get(),
                FDBlocks.LIME_SEA_LANTERN.get(),
                FDBlocks.PINK_SEA_LANTERN.get(),
                FDBlocks.GRAY_SEA_LANTERN.get(),
                FDBlocks.LIGHT_GRAY_SEA_LANTERN.get(),
                FDBlocks.CYAN_SEA_LANTERN.get(),
                FDBlocks.PURPLE_SEA_LANTERN.get(),
                FDBlocks.BLUE_SEA_LANTERN.get(),
                FDBlocks.BROWN_SEA_LANTERN.get(),
                FDBlocks.GREEN_SEA_LANTERN.get(),
                FDBlocks.RED_SEA_LANTERN.get(),
                FDBlocks.BLACK_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_WHITE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_ORANGE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_MAGENTA_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_LIGHT_BLUE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_YELLOW_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_LIME_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_PINK_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_GRAY_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_LIGHT_GRAY_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_CYAN_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_PURPLE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_BLUE_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_BROWN_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_GREEN_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_RED_SEA_LANTERN.get(),
                FDBlocks.REINFORCED_BLACK_SEA_LANTERN.get(),
                FDBlocks.RAINBOW_SEA_LANTERN.get(),
                FDBlocks.RAINBOW_REINFORCED_SEA_LANTERN.get()
        );
        tag(FDBlockTags.PILLAR_FORCE_CONNECT_UP).add(
                Blocks.LANTERN,
                Blocks.SOUL_LANTERN
        );
        tag(FDBlockTags.PILLAR_FORCE_CONNECT_DOWN).add(
                Blocks.TORCH,
                Blocks.SOUL_TORCH
        );
        tag(Tags.Blocks.CHESTS).addTag(FDBlockTags.CABINETS);
        tag(FDBlockTags.TABLE_CONNECT)
                .addTag(FDBlockTags.TABLES)
                .addTag(FDBlockTags.CABINETS);
        DataGen.BLOCKS_AND_MATERIALS.forEach((deferredBlock, blockMaterial) -> {
            Block block = deferredBlock.get();
            if (block instanceof StairBlock) {
                tag(BlockTags.STAIRS).add(block);
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.WOOD)) tag(BlockTags.WOODEN_STAIRS).add(block);
            }
            if (block instanceof SlabBlock) {
                tag(BlockTags.SLABS).add(block);
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.WOOD)) tag(BlockTags.WOODEN_SLABS).add(block);
            }
            if (block instanceof FenceBlock) {
                tag(BlockTags.FENCES).add(block);
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.WOOD)) tag(BlockTags.WOODEN_FENCES).add(block);
            }
            if (block instanceof FenceGateBlock) {
                tag(BlockTags.FENCE_GATES).add(block);
                tag(Tags.Blocks.FENCE_GATES).add(block);
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.WOOD)) tag(Tags.Blocks.FENCE_GATES_WOODEN).add(block);
            }
            if (block instanceof ButtonBlock) {
                tag(BlockTags.BUTTONS).add(block);
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.WOOD)) tag(BlockTags.WOODEN_BUTTONS).add(block);
            }
            if (block instanceof PressurePlateBlock) {
                tag(BlockTags.PRESSURE_PLATES).add(block);
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.WOOD)) tag(BlockTags.WOODEN_PRESSURE_PLATES).add(block);
            }
            if (block instanceof CabinetBlock) tag(FDBlockTags.CABINETS).add(block);
            if (block instanceof TableBlock) tag(FDBlockTags.TABLES).add(block);
            if (block instanceof GuardrailBlock) tag(FDBlockTags.GUARDRAILS_TAG).add(block);
            if (block instanceof PeepWindowBlock) tag(FDBlockTags.PEEP_WINDOWS_TAG).add(block);
            if (block instanceof DoubleCutBlock || block instanceof OneCutBlock || block instanceof TripleCutBlock) tag(FDBlockTags.CUT_BLOCKS_TAG).add(block);
            if (block instanceof Pillar4PXBlock
                    || block instanceof Pillar6PXBlock
                    || block instanceof Pillar8PXBlock
                    || block instanceof Pillar12PXBlock
                    || block instanceof PillarConnector4PXBlock
                    || block instanceof PillarConnector6PXBlock
                    || block instanceof PillarConnector8PXBlock
                    || block instanceof PillarConnector12PXBlock) tag(FDBlockTags.PILLAR_TAG).add(block);
            if (blockMaterial.isDragonImmune()) tag(BlockTags.DRAGON_IMMUNE).add(block);
            if (blockMaterial.isDragonImmune()) tag(BlockTags.WITHER_IMMUNE).add(block);

            if (block instanceof LampInGlassBlock) {
                tag(FDBlockTags.LAMP_IN_GLASS).add(block);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
            }
            else {
                switch (blockMaterial.getMaterialType()) {
                    case WOOD -> tag(BlockTags.MINEABLE_WITH_AXE).add(block);
                    case DIRT, GRASS -> tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block);
                    case STONE, METAL, GLASS -> tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                    case MOSSY -> tag(BlockTags.MINEABLE_WITH_HOE).add(block);
                }
                switch (blockMaterial.getToolLevel()) {
                    case STONE -> tag(BlockTags.NEEDS_STONE_TOOL).add(block);
                    case IRON -> tag(BlockTags.NEEDS_IRON_TOOL).add(block);
                    case DIAMOND -> tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block);
                }
            }
        });
    }
}
