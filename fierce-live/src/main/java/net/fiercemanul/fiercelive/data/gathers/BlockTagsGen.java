package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.registries.BlockMaterial;
import net.fiercemanul.fiercelive.data.registries.BlockMaterialTag;
import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.fiercemanul.fiercelive.data.FLBlocks.*;


public class BlockTagsGen extends BlockTagsProvider {


    public static final HashSet<Consumer<BlockTagsGen>> ROWS = new HashSet<>();
    public BlockTagsGen(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, FierceLive.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(FLBlockTags.MINEABLE_WITH_CROWBAR).addTags(
                BlockTags.MINEABLE_WITH_PICKAXE,
                BlockTags.MINEABLE_WITH_AXE
        );
        tag(FLBlockTags.MINEABLE_WITH_GLASS_KNIFE).addTags(
                Tags.Blocks.GLASS_BLOCKS,
                Tags.Blocks.GLASS_PANES
        );
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                SOUL_CRYSTAL_ORNAMENT.get(),
                ITEM_FRAME_SHELL_THIN.get(),
                ITEM_FRAME_SHELL_BIG.get(),
                //FLBlocks.STAR_BLOCK.get(),
                FIREPLACE_HEART.get(),
                REINFORCED_GLOWSTONE_LAMP.get(),
                REINFORCED_SEA_LANTERN.get(),
                WATERLOGGED_COBBLESTONE.get(),
                SPIRAL_STONE.get(),
                SPIRAL_DEEPSLATE.get(),
                SPIRAL_TUFF.get(),
                SPIRAL_BLACKSTONE.get(),
                SPIRAL_END_STONE.get(),
                INTERLACE_BRICKS.get(),
                VILLAGE_PATTERNED_TILES.get(),
                VILLAGE_MOSAIC_TILES.get(),
                BIG_FLOWER_POT.get(),
                CONCRETE.get(),
                GRAVEL_CONCRETE.get(),
                BLACK_IRON_BLOCK.get(),
                A_WALL_FLOWER_POT.get(),
                B_WALL_FLOWER_POT.get(),
                C_WALL_FLOWER_POT.get(),
                D_WALL_FLOWER_POT.get(),
                E_WALL_FLOWER_POT.get(),
                F_WALL_FLOWER_POT.get(),
                IRON_GUARDRAIL.get(),
                IRON_FRAME.get(),
                IRON_CORRIDOR.get(),
                IRON_CORRIDOR_SLAB.get(),
                IRON_CORRIDOR_STAIRS.get(),
                IRON_LADDER.get(),
                IRON_SCAFFOLDING.get(),
                HEAVY_CHAINS.get(),
                RAINBOW_CONCRETE.get(),
                RAINBOW_TERRACOTTA.get(),
                RAINBOW_REINFORCED_SEA_LANTERN.get(),
                OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE.get(),
                SPRUCE_PLANKS_AND_GRAY_CONCRETE.get(),
                WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE.get(),
                DEEPSLATE_TILES_AND_SPRUCE_PLANKS.get(),
                DEEPSLATE_TILES_AND_MANGROVE_PLANKS.get(),
                DARK_PRISMARINE_AND_SPRUCE_PLANKS.get(),
                DARK_PRISMARINE_AND_MANGROVE_PLANKS.get(),
                BRICKS_AND_BIRCH_PLANKS.get(),
                FAKE_HOPPER.get(),
                FAKE_FURNACE.get(),
                LIT_FAKE_FURNACE.get(),
                FAKE_BLAST_FURNACE.get(),
                LIT_FAKE_BLAST_FURNACE.get(),
                FAKE_SMOKER.get(),
                LIT_FAKE_SMOKER.get(),
                FAKE_GOLD_BLOCK.get(),
                FAKE_IRON_BLOCK.get(),
                FAKE_DIAMOND_BLOCK.get(),
                FAKE_NETHERITE_BLOCK.get(),
                FAKE_REINFORCED_DEEPSLATE.get(),
                FAKE_BEDROCK.get(),
                TEXTURE_FURNACE.get(),
                TEXTURE_FURNACE_TOP.get(),
                TEXTURE_BLAST_FURNACE.get(),
                TEXTURE_BLAST_FURNACE_TOP.get(),
                TEXTURE_SMOKER.get(),
                TEXTURE_SMITHING_TABLE_TOP.get(),
                TEXTURE_LODESTONE.get(),
                TEXTURE_LODESTONE_SIDE.get(),
                TEXTURE_LODESTONE_TOP.get(),
                RED_LAMP.get(),
                GREEN_LAMP.get(),
                BLUE_LAMP.get(),
                YELLOW_LAMP.get(),
                CYAN_LAMP.get(),
                PURPLE_LAMP.get(),
                RAINBOW_LAMP.get(),
                ROCK_PATH.get(),
                QUARTZ_SQUAT_TOILET.get(),
                IRON_SQUAT_TOILET.get(),
                QUARTZ_TOILET.get(),
                IRON_TOILET.get(),
                SINK.get(),
                MANGROVE_SINK.get(),
                MIRROR.get(),
                CABINET_MIRROR.get(),
                SHOWER_SET.get(),
                INFINITE_TNT.get(),
                INFINITE_RAIL_CARPET.get(),
                SNOWBALL_GENERATOR.get()
        ).addTags(
                FLBlockTags.REINFORCED_SEA_LANTERNS
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                SMOOTH_OAK_PLANKS.get(),
                SMOOTH_SPRUCE_PLANKS.get(),
                SMOOTH_BIRCH_PLANKS.get(),
                SMOOTH_JUNGLE_PLANKS.get(),
                SMOOTH_ACACIA_PLANKS.get(),
                SMOOTH_DARK_OAK_PLANKS.get(),
                SMOOTH_MANGROVE_PLANKS.get(),
                SMOOTH_BAMBOO_PLANKS.get(),
                SMOOTH_CHERRY_PLANKS.get(),
                SMOOTH_CRIMSON_PLANKS.get(),
                SMOOTH_WARPED_PLANKS.get(),
                GREEN_FUN_ROOF.get(),
                FIREWOOD.get(),
                MANGROVE_SINK.get(),
                MIRROR.get(),
                CABINET_MIRROR.get(),
                OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE.get(),
                SPRUCE_PLANKS_AND_GRAY_CONCRETE.get(),
                OAK_PLANKS_AND_SPRUCE_PLANKS.get(),
                DEEPSLATE_TILES_AND_SPRUCE_PLANKS.get(),
                DEEPSLATE_TILES_AND_MANGROVE_PLANKS.get(),
                DARK_PRISMARINE_AND_SPRUCE_PLANKS.get(),
                DARK_PRISMARINE_AND_MANGROVE_PLANKS.get(),
                BRICKS_AND_BIRCH_PLANKS.get(),
                REINFORCED_GLOWSTONE_LAMP.get(),
                FAKE_BARREL.get(),
                FAKE_CAMPFIRE.get(),
                LIT_FAKE_CAMPFIRE.get(),
                LIT_FAKE_SOUL_CAMPFIRE.get(),
                FAKE_CHEST.get(),
                FAKE_CHISELED_BOOKSHELF.get(),
                FAKE_LECTERN.get(),
                FAKE_BEEHIVE.get(),
                TEXTURE_CHISELED_BOOKSHELF.get(),
                TEXTURE_CHISELED_BOOKSHELF_TOP.get(),
                TEXTURE_CHISELED_BOOKSHELF_SIDE.get(),
                TEXTURE_LOOM.get(),
                TEXTURE_BEEHIVE_TOP.get(),
                TEXTURE_SMITHING_TABLE_BOTTOM.get(),
                TEXTURE_COMPOSTER_BOTTOM.get(),
                TEXTURE_BEE_NEST_TOP.get(),
                CRAFTING_PAD.get(),
                CRAFTING_DESK.get(),
                CRAFTING_BLOCK.get()
        ).addTags(
                FLBlockTags.CABINETS,
                FLBlockTags.REINFORCED_SEA_LANTERNS
        );
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                MEAT_BLOCK.get(),
                ROTTEN_FLESH_BLOCK.get(),
                HALF_GRASS_BLOCK.get(),
                HALF_PODZOL.get(),
                HALF_MYCELIUM.get(),
                HALF_DIRT.get(),
                HALF_DIRT_PATH.get()
        );
        tag(BlockTags.MINEABLE_WITH_HOE).add(
                FOX_CARROT_SHEAF.get(),
                FOX_CARROT_BASKET.get()
        );
        tag(BlockTags.NEEDS_STONE_TOOL).add(
                BLACK_IRON_BLOCK.get(),
                IRON_GUARDRAIL.get(),
                IRON_FRAME.get(),
                IRON_CORRIDOR.get(),
                IRON_CORRIDOR_SLAB.get(),
                IRON_CORRIDOR_STAIRS.get(),
                IRON_LADDER.get(),
                IRON_SCAFFOLDING.get()
        );
        tag(BlockTags.CONCRETE_POWDER).add(
                CONCRETE_POWDER.get(),
                GRAVEL_CONCRETE_POWDER.get()
        );
        tag(BlockTags.WOOL).add(RAINBOW_WOOL.get()).addTag(FLBlockTags.WOOL_SOFAS);
        tag(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(
                HALF_GRASS_BLOCK.get(),
                HALF_PODZOL.get(),
                HALF_MYCELIUM.get(),
                HALF_DIRT.get(),
                HALF_DIRT_PATH.get()
        );
        tag(BlockTags.ENDERMAN_HOLDABLE).add(
                HALF_GRASS_BLOCK.get(),
                HALF_PODZOL.get(),
                HALF_MYCELIUM.get(),
                HALF_DIRT.get(),
                HALF_DIRT_PATH.get()
        );
        tag(BlockTags.DIRT).add(BIG_FLOWER_POT.get());
        tag(BlockTags.PLANKS).add(
                SMOOTH_OAK_PLANKS.get(),
                SMOOTH_SPRUCE_PLANKS.get(),
                SMOOTH_BIRCH_PLANKS.get(),
                SMOOTH_JUNGLE_PLANKS.get(),
                SMOOTH_ACACIA_PLANKS.get(),
                SMOOTH_DARK_OAK_PLANKS.get(),
                SMOOTH_MANGROVE_PLANKS.get(),
                SMOOTH_BAMBOO_PLANKS.get(),
                SMOOTH_CHERRY_PLANKS.get(),
                SMOOTH_CRIMSON_PLANKS.get(),
                SMOOTH_WARPED_PLANKS.get()
        );
        tag(BlockTags.CAMPFIRES).add(
                FIREPLACE_HEART.get()
        );
        tag(FLBlockTags.REINFORCED_SEA_LANTERNS).add(
                REINFORCED_SEA_LANTERN.get(),
                RAINBOW_REINFORCED_SEA_LANTERN.get()
        );
        tag(FLBlockTags.SEA_LANTERNS).add(
                Blocks.SEA_LANTERN,
                RAINBOW_SEA_LANTERN.get()
        ).addTags(
                FLBlockTags.REINFORCED_SEA_LANTERNS
        );
        tag(FLBlockTags.PILLAR_FORCE_CONNECT_UP).add(
                Blocks.LANTERN,
                Blocks.SOUL_LANTERN
        );
        tag(FLBlockTags.PILLAR_FORCE_CONNECT_DOWN).add(
                Blocks.TORCH,
                Blocks.SOUL_TORCH
        );
        tag(Tags.Blocks.CHESTS).addTag(FLBlockTags.CABINETS);
        tag(FLBlockTags.TABLE_CONNECT)
                .addTag(FLBlockTags.TABLES)
                .addTag(FLBlockTags.CABINETS);
        tag(BlockTags.TERRACOTTA).add(RAINBOW_TERRACOTTA.get());
        tag(Tags.Blocks.CONCRETES).add(
                RAINBOW_CONCRETE.get(),
                CONCRETE.get(),
                GRAVEL_CONCRETE.get()
        );
        tag(BlockTags.BEACON_BASE_BLOCKS).add(BLACK_IRON_BLOCK.get());
        tag(BlockTags.WOOL).add(RAINBOW_WOOL.get());
        tag(Tags.Blocks.GLASS_BLOCKS).add(RAINBOW_GLASS.get());
        tag(Tags.Blocks.GLASS_PANES).add(RAINBOW_GLASS_PANE.get());
        tag(BlockTags.IMPERMEABLE).add(RAINBOW_GLASS.get());
        tag(Tags.Blocks.DYED_RED).add(RED_LAMP.get());
        tag(Tags.Blocks.DYED_GREEN).add(GREEN_LAMP.get());
        tag(Tags.Blocks.DYED_BLUE).add(BLUE_LAMP.get());
        tag(Tags.Blocks.DYED_YELLOW).add(YELLOW_LAMP.get());
        tag(Tags.Blocks.DYED_CYAN).add(CYAN_LAMP.get());
        tag(Tags.Blocks.DYED_PURPLE).add(PURPLE_LAMP.get());
        tag(FLBlockTags.COLOR_LAMPS).add(
                RED_LAMP.get(),
                GREEN_LAMP.get(),
                BLUE_LAMP.get(),
                YELLOW_LAMP.get(),
                CYAN_LAMP.get(),
                PURPLE_LAMP.get(),
                RAINBOW_LAMP.get()
        );
        tag(FLBlockTags.RAINBOW_DYED).add(
                RAINBOW_CONCRETE.get(),
                RAINBOW_GLASS.get(),
                RAINBOW_GLASS_PANE.get(),
                RAINBOW_LAMP.get(),
                RAINBOW_SEA_LANTERN.get(),
                RAINBOW_REINFORCED_SEA_LANTERN.get(),
                RAINBOW_WOOL.get(),
                RAINBOW_TERRACOTTA.get()
        );
        tag(Tags.Blocks.DYED).addTag(FLBlockTags.RAINBOW_DYED);
        tag(BlockTags.CLIMBABLE).add(
                IRON_FRAME.get(),
                IRON_CORRIDOR.get(),
                IRON_LADDER.get(),
                IRON_SCAFFOLDING.get()
        );
        tag(FLBlockTags.FRAMES).add(
                IRON_FRAME.get(),
                IRON_CORRIDOR.get()
        );
        tag(FLBlockTags.IRON_CORRIDORS).add(
                IRON_CORRIDOR.get(),
                IRON_CORRIDOR_SLAB.get(),
                IRON_CORRIDOR_STAIRS.get()
        );
        tag(FLBlockTags.HANDY_REDSTONE).add(
                INFINITE_TNT.get(),
                INFINITE_RAIL_CARPET.get(),
                SNOWBALL_GENERATOR.get()
        );
        tag(BlockTags.DRAGON_IMMUNE).add(
                Blocks.END_STONE_BRICKS,
                Blocks.END_STONE_BRICK_SLAB,
                Blocks.END_STONE_BRICK_STAIRS,
                Blocks.END_STONE_BRICK_WALL,
                Blocks.PURPUR_BLOCK,
                Blocks.PURPUR_SLAB,
                Blocks.PURPUR_STAIRS,
                Blocks.PURPUR_PILLAR,
                Blocks.END_ROD,
                Blocks.ENDER_CHEST,
                Blocks.CHEST
        );

        ROWS.forEach(row -> row.accept(this));

    }

    public void woodenTagOrBasicTags(BlockMaterial material, TagKey<Block> tag, DeferredBlock<? extends Block> block) {
        if (material.hasTag(BlockMaterialTag.TOOL_AXE)) tag(tag).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_SHOVEL)) tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)) tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_HOE)) tag(BlockTags.MINEABLE_WITH_HOE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_SHEARS)) tag(BlockTags.WOOL).add(block.get());
        specialTags(material, block);
    }

    public void woodenStoneTagOrBasicTags(BlockMaterial material, TagKey<Block> woodTag, TagKey<Block> stoneTag, DeferredBlock<? extends Block> block) {
        if (material.hasTag(BlockMaterialTag.TOOL_AXE)) tag(woodTag).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_SHOVEL)) tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)) tag(stoneTag).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_HOE)) tag(BlockTags.MINEABLE_WITH_HOE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_SHEARS)) tag(BlockTags.WOOL).add(block.get());
        specialTags(material, block);
    }

    public void basicTags(BlockMaterial material, DeferredBlock<? extends Block> block) {
        if (material.hasTag(BlockMaterialTag.TOOL_AXE)) tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_SHOVEL)) tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)) tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_HOE)) tag(BlockTags.MINEABLE_WITH_HOE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TOOL_SHEARS)) tag(BlockTags.WOOL).add(block.get());
        specialTags(material, block);
    }

    public void specialTags(BlockMaterial material, DeferredBlock<? extends Block> block) {
        if (material.hasTag(BlockMaterialTag.TAG_DRAGON_IMMUNE)) tag(BlockTags.DRAGON_IMMUNE).add(block.get());
        if (material.hasTag(BlockMaterialTag.TAG_WITHER_IMMUNE)) tag(BlockTags.WITHER_IMMUNE).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_STONE_TOOL)) tag(BlockTags.NEEDS_STONE_TOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_IRON_TOOL)) tag(BlockTags.NEEDS_IRON_TOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_DIAMOND_TOOL)) tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.NEEDS_NETHERITE_TOOL)) tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(block.get());
        if (material.hasTag(BlockMaterialTag.TEXTURE_COLORED)) colorTags(material.mapColorHolder().top(), block);
    }

    public void colorTags(MapColor mapColor, DeferredBlock<? extends Block> block) {
        TagKey<Block> tag = null;
        if (mapColor == MapColor.SNOW || mapColor == MapColor.TERRACOTTA_WHITE) {
            if (block.getId().getPath().contains("rainbow")) tag = FLBlockTags.RAINBOW_DYED;
            else tag = Tags.Blocks.DYED_WHITE;
        }
        else if (mapColor == MapColor.COLOR_ORANGE || mapColor == MapColor.TERRACOTTA_ORANGE) tag = Tags.Blocks.DYED_ORANGE;
        else if (mapColor == MapColor.COLOR_MAGENTA || mapColor == MapColor.TERRACOTTA_MAGENTA) tag = Tags.Blocks.DYED_MAGENTA;
        else if (mapColor == MapColor.COLOR_LIGHT_BLUE || mapColor == MapColor.TERRACOTTA_LIGHT_BLUE) tag = Tags.Blocks.DYED_LIGHT_BLUE;
        else if (mapColor == MapColor.COLOR_YELLOW || mapColor == MapColor.TERRACOTTA_YELLOW) tag = Tags.Blocks.DYED_YELLOW;
        else if (mapColor == MapColor.COLOR_LIGHT_GREEN || mapColor == MapColor.TERRACOTTA_LIGHT_GREEN) tag = Tags.Blocks.DYED_LIME;
        else if (mapColor == MapColor.COLOR_PINK || mapColor == MapColor.TERRACOTTA_PINK) tag = Tags.Blocks.DYED_PINK;
        else if (mapColor == MapColor.COLOR_GRAY || mapColor == MapColor.TERRACOTTA_GRAY) tag = Tags.Blocks.DYED_GRAY;
        else if (mapColor == MapColor.COLOR_LIGHT_GRAY || mapColor == MapColor.TERRACOTTA_LIGHT_GRAY) tag = Tags.Blocks.DYED_LIGHT_GRAY;
        else if (mapColor == MapColor.COLOR_CYAN || mapColor == MapColor.TERRACOTTA_CYAN) tag = Tags.Blocks.DYED_CYAN;
        else if (mapColor == MapColor.COLOR_PURPLE || mapColor == MapColor.TERRACOTTA_PURPLE) tag = Tags.Blocks.DYED_PURPLE;
        else if (mapColor == MapColor.COLOR_BLUE || mapColor == MapColor.TERRACOTTA_BLUE) tag = Tags.Blocks.DYED_BLUE;
        else if (mapColor == MapColor.COLOR_BROWN || mapColor == MapColor.TERRACOTTA_BROWN) tag = Tags.Blocks.DYED_BROWN;
        else if (mapColor == MapColor.COLOR_GREEN || mapColor == MapColor.TERRACOTTA_GREEN) tag = Tags.Blocks.DYED_GREEN;
        else if (mapColor == MapColor.COLOR_RED || mapColor == MapColor.TERRACOTTA_RED) tag = Tags.Blocks.DYED_RED;
        else if (mapColor == MapColor.COLOR_BLACK || mapColor == MapColor.TERRACOTTA_BLACK) tag = Tags.Blocks.DYED_BLACK;
        if (tag != null) tag(tag).add(block.get());
    }

    public void tag(TagKey<Block> tag, DeferredBlock<? extends Block> block) {
        tag(tag).add(block.get());
    }
}
