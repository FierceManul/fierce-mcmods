package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.registries.BlockMaterial;
import net.fiercemanul.fiercelive.data.registries.BlockMaterialTag;
import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.fiercemanul.fiercelive.data.tags.FLItemTags;
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
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                FLBlocks.SOUL_CRYSTAL_ORNAMENT.get(),
                FLBlocks.ITEM_FRAME_SHELL_THIN.get(),
                FLBlocks.ITEM_FRAME_SHELL_BIG.get(),
                //FLBlocks.STAR_BLOCK.get(),
                FLBlocks.FIREPLACE_HEART.get(),
                FLBlocks.REINFORCED_GLOWSTONE_LAMP.get(),
                FLBlocks.REINFORCED_SEA_LANTERN.get(),
                FLBlocks.WATERLOGGED_COBBLESTONE.get(),
                FLBlocks.SPIRAL_STONE.get(),
                FLBlocks.DEEPSLATE_SPIRAL_STONE.get(),
                FLBlocks.A_WALL_FLOWER_POT.get(),
                FLBlocks.B_WALL_FLOWER_POT.get(),
                FLBlocks.C_WALL_FLOWER_POT.get(),
                FLBlocks.D_WALL_FLOWER_POT.get(),
                FLBlocks.E_WALL_FLOWER_POT.get(),
                FLBlocks.F_WALL_FLOWER_POT.get(),
                FLBlocks.HEAVY_CHAINS.get(),
                FLBlocks.RAINBOW_CONCRETE.get(),
                FLBlocks.RAINBOW_TERRACOTTA.get(),
                FLBlocks.RAINBOW_REINFORCED_SEA_LANTERN.get(),
                FLBlocks.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE.get(),
                FLBlocks.SPRUCE_PLANKS_AND_GRAY_CONCRETE.get(),
                FLBlocks.WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE.get(),
                FLBlocks.DEEPSLATE_TILES_AND_SPRUCE_PLANKS.get(),
                FLBlocks.DEEPSLATE_TILES_AND_MANGROVE_PLANKS.get(),
                FLBlocks.DARK_PRISMARINE_AND_SPRUCE_PLANKS.get(),
                FLBlocks.DARK_PRISMARINE_AND_MANGROVE_PLANKS.get(),
                FLBlocks.BRICKS_AND_BIRCH_PLANKS.get(),
                FLBlocks.FAKE_HOPPER.get(),
                FLBlocks.FAKE_FURNACE.get(),
                FLBlocks.LIT_FAKE_FURNACE.get(),
                FLBlocks.FAKE_BLAST_FURNACE.get(),
                FLBlocks.LIT_FAKE_BLAST_FURNACE.get(),
                FLBlocks.FAKE_SMOKER.get(),
                FLBlocks.LIT_FAKE_SMOKER.get(),
                FLBlocks.FAKE_GOLD_BLOCK.get(),
                FLBlocks.FAKE_IRON_BLOCK.get(),
                FLBlocks.FAKE_DIAMOND_BLOCK.get(),
                FLBlocks.FAKE_NETHERITE_BLOCK.get(),
                FLBlocks.FAKE_BEDROCK.get(),
                FLBlocks.TEXTURE_FURNACE.get(),
                FLBlocks.TEXTURE_FURNACE_TOP.get(),
                FLBlocks.TEXTURE_BLAST_FURNACE.get(),
                FLBlocks.TEXTURE_BLAST_FURNACE_TOP.get(),
                FLBlocks.TEXTURE_SMOKER.get(),
                FLBlocks.TEXTURE_SMITHING_TABLE_TOP.get(),
                FLBlocks.TEXTURE_LODESTONE.get(),
                FLBlocks.TEXTURE_LODESTONE_SIDE.get(),
                FLBlocks.TEXTURE_LODESTONE_TOP.get(),
                FLBlocks.RED_LAMP.get(),
                FLBlocks.GREEN_LAMP.get(),
                FLBlocks.BLUE_LAMP.get(),
                FLBlocks.YELLOW_LAMP.get(),
                FLBlocks.CYAN_LAMP.get(),
                FLBlocks.PURPLE_LAMP.get(),
                FLBlocks.RAINBOW_LAMP.get(),
                FLBlocks.ROCK_PATH.get()
        ).addTags(
                FLBlockTags.REINFORCED_SEA_LANTERNS
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                FLBlocks.SMOOTH_OAK_PLANKS.get(),
                FLBlocks.SMOOTH_SPRUCE_PLANKS.get(),
                FLBlocks.SMOOTH_BIRCH_PLANKS.get(),
                FLBlocks.SMOOTH_JUNGLE_PLANKS.get(),
                FLBlocks.SMOOTH_ACACIA_PLANKS.get(),
                FLBlocks.SMOOTH_DARK_OAK_PLANKS.get(),
                FLBlocks.SMOOTH_MANGROVE_PLANKS.get(),
                FLBlocks.SMOOTH_BAMBOO_PLANKS.get(),
                FLBlocks.SMOOTH_CHERRY_PLANKS.get(),
                FLBlocks.SMOOTH_CRIMSON_PLANKS.get(),
                FLBlocks.SMOOTH_WARPED_PLANKS.get(),
                FLBlocks.GREEN_FUN_ROOF.get(),
                FLBlocks.FIREWOOD.get(),
                FLBlocks.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE.get(),
                FLBlocks.SPRUCE_PLANKS_AND_GRAY_CONCRETE.get(),
                FLBlocks.OAK_PLANKS_AND_SPRUCE_PLANKS.get(),
                FLBlocks.DEEPSLATE_TILES_AND_SPRUCE_PLANKS.get(),
                FLBlocks.DEEPSLATE_TILES_AND_MANGROVE_PLANKS.get(),
                FLBlocks.DARK_PRISMARINE_AND_SPRUCE_PLANKS.get(),
                FLBlocks.DARK_PRISMARINE_AND_MANGROVE_PLANKS.get(),
                FLBlocks.BRICKS_AND_BIRCH_PLANKS.get(),
                FLBlocks.REINFORCED_GLOWSTONE_LAMP.get(),
                FLBlocks.FAKE_BARREL.get(),
                FLBlocks.FAKE_CAMPFIRE.get(),
                FLBlocks.LIT_FAKE_CAMPFIRE.get(),
                FLBlocks.LIT_FAKE_SOUL_CAMPFIRE.get(),
                FLBlocks.FAKE_CHEST.get(),
                FLBlocks.FAKE_CHISELED_BOOKSHELF.get(),
                FLBlocks.FAKE_LECTERN.get(),
                FLBlocks.FAKE_BEEHIVE.get(),
                FLBlocks.TEXTURE_CHISELED_BOOKSHELF.get(),
                FLBlocks.TEXTURE_CHISELED_BOOKSHELF_TOP.get(),
                FLBlocks.TEXTURE_CHISELED_BOOKSHELF_SIDE.get(),
                FLBlocks.TEXTURE_LOOM.get(),
                FLBlocks.TEXTURE_BEEHIVE_TOP.get(),
                FLBlocks.TEXTURE_SMITHING_TABLE_BOTTOM.get(),
                FLBlocks.TEXTURE_COMPOSTER_BOTTOM.get(),
                FLBlocks.TEXTURE_BEE_NEST_TOP.get(),
                FLBlocks.CRAFTING_PAD.get(),
                FLBlocks.CRAFTING_DESK.get(),
                FLBlocks.CRAFTING_BLOCK.get()
        ).addTags(
                FLBlockTags.CABINETS,
                FLBlockTags.REINFORCED_SEA_LANTERNS
        );
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                FLBlocks.MEAT_BLOCK.get(),
                FLBlocks.ROTTEN_FLESH_BLOCK.get(),
                FLBlocks.HALF_GRASS_BLOCK.get(),
                FLBlocks.HALF_PODZOL.get(),
                FLBlocks.HALF_MYCELIUM.get(),
                FLBlocks.HALF_DIRT_PATH.get()
        );
        tag(BlockTags.WOOL).add(FLBlocks.RAINBOW_WOOL.get()).addTag(FLBlockTags.WOOL_SOFAS);
        tag(BlockTags.DIRT).add(
                FLBlocks.HALF_GRASS_BLOCK.get(),
                FLBlocks.HALF_PODZOL.get(),
                FLBlocks.HALF_MYCELIUM.get()
        );
        tag(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(
                FLBlocks.HALF_GRASS_BLOCK.get(),
                FLBlocks.HALF_PODZOL.get(),
                FLBlocks.HALF_MYCELIUM.get()
        );
        tag(BlockTags.MINEABLE_WITH_HOE).add(
                FLBlocks.FOX_CARROT_SHEAF.get(),
                FLBlocks.FOX_CARROT_BASKET.get()
        );
        tag(BlockTags.PLANKS).add(
                FLBlocks.SMOOTH_OAK_PLANKS.get(),
                FLBlocks.SMOOTH_SPRUCE_PLANKS.get(),
                FLBlocks.SMOOTH_BIRCH_PLANKS.get(),
                FLBlocks.SMOOTH_JUNGLE_PLANKS.get(),
                FLBlocks.SMOOTH_ACACIA_PLANKS.get(),
                FLBlocks.SMOOTH_DARK_OAK_PLANKS.get(),
                FLBlocks.SMOOTH_MANGROVE_PLANKS.get(),
                FLBlocks.SMOOTH_BAMBOO_PLANKS.get(),
                FLBlocks.SMOOTH_CHERRY_PLANKS.get(),
                FLBlocks.SMOOTH_CRIMSON_PLANKS.get(),
                FLBlocks.SMOOTH_WARPED_PLANKS.get()
        );
        tag(BlockTags.CAMPFIRES).add(
                FLBlocks.FIREPLACE_HEART.get()
        );
        //tag(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON).add(
        //        FLBlocks.HALF_GRASS_BLOCK.get()
        //);
        tag(FLBlockTags.REINFORCED_SEA_LANTERNS).add(
                FLBlocks.REINFORCED_SEA_LANTERN.get(),
                FLBlocks.RAINBOW_REINFORCED_SEA_LANTERN.get()
        );
        tag(FLBlockTags.SEA_LANTERNS).add(
                Blocks.SEA_LANTERN,
                FLBlocks.RAINBOW_SEA_LANTERN.get()
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
        tag(BlockTags.TERRACOTTA).add(FLBlocks.RAINBOW_TERRACOTTA.get());
        tag(Tags.Blocks.CONCRETES).add(FLBlocks.RAINBOW_CONCRETE.get());
        tag(BlockTags.WOOL).add(FLBlocks.RAINBOW_WOOL.get());
        tag(Tags.Blocks.GLASS_BLOCKS).add(FLBlocks.RAINBOW_GLASS.get());
        tag(BlockTags.IMPERMEABLE).add(FLBlocks.RAINBOW_GLASS.get());
        tag(Tags.Blocks.DYED_RED).add(FLBlocks.RED_LAMP.get());
        tag(Tags.Blocks.DYED_GREEN).add(FLBlocks.GREEN_LAMP.get());
        tag(Tags.Blocks.DYED_BLUE).add(FLBlocks.BLUE_LAMP.get());
        tag(Tags.Blocks.DYED_YELLOW).add(FLBlocks.YELLOW_LAMP.get());
        tag(Tags.Blocks.DYED_CYAN).add(FLBlocks.CYAN_LAMP.get());
        tag(Tags.Blocks.DYED_PURPLE).add(FLBlocks.PURPLE_LAMP.get());
        tag(FLBlockTags.COLOR_LAMPS).add(
                FLBlocks.RED_LAMP.get(),
                FLBlocks.GREEN_LAMP.get(),
                FLBlocks.BLUE_LAMP.get(),
                FLBlocks.YELLOW_LAMP.get(),
                FLBlocks.CYAN_LAMP.get(),
                FLBlocks.PURPLE_LAMP.get(),
                FLBlocks.RAINBOW_LAMP.get()
        );
        tag(FLBlockTags.RAINBOW_DYED).add(
                FLBlocks.RAINBOW_CONCRETE.get(),
                FLBlocks.RAINBOW_GLASS.get(),
                FLBlocks.RAINBOW_LAMP.get(),
                FLBlocks.RAINBOW_SEA_LANTERN.get(),
                FLBlocks.RAINBOW_REINFORCED_SEA_LANTERN.get(),
                FLBlocks.RAINBOW_WOOL.get(),
                FLBlocks.RAINBOW_TERRACOTTA.get()
        );
        tag(Tags.Blocks.DYED).addTag(FLBlockTags.RAINBOW_DYED);

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
