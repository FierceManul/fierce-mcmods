package net.fiercemanul.fiercedecoration.data.gathers;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.data.FDBlocks;
import net.fiercemanul.fiercedecoration.data.registries.BlockMaterial;
import net.fiercemanul.fiercedecoration.data.registries.BlockMaterialTag;
import net.fiercemanul.fiercedecoration.tags.FDBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class BlockTagsGen extends BlockTagsProvider {


    public static final HashSet<Consumer<BlockTagsGen>> ROWS = new HashSet<>();
    public BlockTagsGen(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, FierceDecoration.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                FDBlocks.SOUL_CRYSTAL_ORNAMENT.get(),
                FDBlocks.ITEM_FRAME_SHELL_THIN.get(),
                FDBlocks.ITEM_FRAME_SHELL_BIG.get(),
                FDBlocks.STAR_BLOCK.get(),
                FDBlocks.FIREPLACE_HEART.get(),
                FDBlocks.REINFORCED_GLOWSTONE_LAMP.get(),
                FDBlocks.REINFORCED_SEA_LANTERN.get(),
                FDBlocks.WATERLOGGED_COBBLESTONE.get(),
                FDBlocks.A_WALL_FLOWER_POT.get(),
                FDBlocks.B_WALL_FLOWER_POT.get(),
                FDBlocks.C_WALL_FLOWER_POT.get(),
                FDBlocks.D_WALL_FLOWER_POT.get(),
                FDBlocks.E_WALL_FLOWER_POT.get(),
                FDBlocks.F_WALL_FLOWER_POT.get(),
                FDBlocks.HEAVY_CHAINS.get(),
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
                FDBlocks.REINFORCED_SEA_LANTERN.get(),
                FDBlocks.RED_LAMP.get(),
                FDBlocks.GREEN_LAMP.get(),
                FDBlocks.BLUE_LAMP.get(),
                FDBlocks.YELLOW_LAMP.get(),
                FDBlocks.CYAN_LAMP.get(),
                FDBlocks.PURPLE_LAMP.get(),
                FDBlocks.RAINBOW_LAMP.get(),
                FDBlocks.ROCK_PATH.get()
        ).addTags(
                FDBlockTags.REINFORCED_SEA_LANTERNS
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                FDBlocks.SMOOTH_OAK_PLANKS.get(),
                FDBlocks.SMOOTH_SPRUCE_PLANKS.get(),
                FDBlocks.SMOOTH_BIRCH_PLANKS.get(),
                FDBlocks.SMOOTH_JUNGLE_PLANKS.get(),
                FDBlocks.SMOOTH_ACACIA_PLANKS.get(),
                FDBlocks.SMOOTH_DARK_OAK_PLANKS.get(),
                FDBlocks.SMOOTH_MANGROVE_PLANKS.get(),
                FDBlocks.SMOOTH_BAMBOO_PLANKS.get(),
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
                FDBlocks.REINFORCED_GLOWSTONE_LAMP.get(),
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
        ).addTags(
                FDBlockTags.CABINETS,
                FDBlockTags.REINFORCED_SEA_LANTERNS
        );
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                FDBlocks.ROTTEN_FLESH_BLOCK.get(),
                FDBlocks.HALF_GRASS_BLOCK.get(),
                FDBlocks.HALF_PODZOL.get(),
                FDBlocks.HALF_MYCELIUM.get(),
                FDBlocks.HALF_PATH_BLOCK.get()
        );
        tag(BlockTags.WOOL).addTag(FDBlockTags.WOOL_SOFAS);
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
                FDBlocks.SMOOTH_BAMBOO_PLANKS.get(),
                FDBlocks.SMOOTH_CHERRY_PLANKS.get(),
                FDBlocks.SMOOTH_CRIMSON_PLANKS.get(),
                FDBlocks.SMOOTH_WARPED_PLANKS.get()
        );
        tag(BlockTags.CAMPFIRES).add(
                FDBlocks.FIREPLACE_HEART.get()
        );
        //tag(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON).add(
        //        FDBlocks.HALF_GRASS_BLOCK.get()
        //);
        tag(FDBlockTags.REINFORCED_SEA_LANTERNS).add(
                FDBlocks.REINFORCED_SEA_LANTERN.get(),
                FDBlocks.RAINBOW_REINFORCED_SEA_LANTERN.get()
        );
        tag(FDBlockTags.SEA_LANTERNS).add(
                Blocks.SEA_LANTERN,
                FDBlocks.RAINBOW_SEA_LANTERN.get()
        ).addTags(
                FDBlockTags.REINFORCED_SEA_LANTERNS
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

        ROWS.forEach(row -> row.accept(this));

    }

    public void woodenOrTags(BlockMaterial material, TagKey<Block> tag, DeferredBlock<? extends Block> block) {
        if (material.hasTag(BlockMaterialTag.TOOL_AXE)) tag(tag).add(block.get());
        else {
            if (material.hasTag(BlockMaterialTag.TOOL_SHOVEL)) tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block.get());
            if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)) tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
            if (material.hasTag(BlockMaterialTag.TOOL_HOE)) tag(BlockTags.MINEABLE_WITH_HOE).add(block.get());
            if (material.hasTag(BlockMaterialTag.TOOL_SHEARS)) tag(BlockTags.WOOL).add(block.get());
        }
        if (material.hasTag(BlockMaterialTag.TAG_DRAGON_IMMUNE)) tag(BlockTags.DRAGON_IMMUNE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TAG_WITHER_IMMUNE)) tag(BlockTags.WITHER_IMMUNE).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_STONE_TOOL)) tag(BlockTags.NEEDS_STONE_TOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_IRON_TOOL)) tag(BlockTags.NEEDS_IRON_TOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_DIAMOND_TOOL)) tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_NETHERITE_TOOL)) tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(block.get());
    }

    public void basicTags(BlockMaterial material, DeferredBlock<? extends Block> block) {
        if (material.hasTag(BlockMaterialTag.TOOL_AXE)) tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_SHOVEL)) tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)) tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_HOE)) tag(BlockTags.MINEABLE_WITH_HOE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_SHEARS)) tag(BlockTags.WOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.TAG_DRAGON_IMMUNE)) tag(BlockTags.DRAGON_IMMUNE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TAG_WITHER_IMMUNE)) tag(BlockTags.WITHER_IMMUNE).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_STONE_TOOL)) tag(BlockTags.NEEDS_STONE_TOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_IRON_TOOL)) tag(BlockTags.NEEDS_IRON_TOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_DIAMOND_TOOL)) tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block.get());
    }

    public void tag(TagKey<Block> tag, DeferredBlock<? extends Block> block) {
        tag(tag).add(block.get());
    }
}
