package net.fiercemanul.fiercelive.data;

import net.fiercemanul.fiercelive.data.registries.FLRegister;
import net.fiercemanul.fiercelive.world.item.FakeBlockEntityItem;
import net.fiercemanul.fiercelive.world.level.block.*;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.world.level.block.BlockUtils;
import net.fiercemanul.fiercesource.world.level.block.FacingBlock;
import net.fiercemanul.fiercesource.world.level.block.HorizonAxisBlock;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingBlock;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;
import java.util.function.Supplier;

public final class FLBlocks {


    public static final DeferredBlock<Block> SOUL_CRYSTAL_ORNAMENT = regBlock(
            "soul_crystal_ornament", SoulCrystalOrnamentBlock::new, BlockBehaviour.Properties.of().strength(1.5F, 20.0F).lightLevel(value -> 15).emissiveRendering(FSUtils::getTrue).sound(SoundType.LARGE_AMETHYST_BUD).mapColor(MapColor.WOOL));
    public static final DeferredBlock<Block> PORTABLE_WORKSTATION = regBlock(
            "portable_workstation", PortableWorkstationBlock::new, BlockBehaviour.Properties.of().strength(0.25F, 6.0F).lightLevel(value -> 7).noCollission().noOcclusion().mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> LAPTOP_TERMINAL = regBlock(
            "laptop_terminal", LaptopTerminalBlock::new, BlockBehaviour.Properties.of().strength(0.25F, 6.0F).lightLevel(value -> 7).noCollission().noOcclusion().mapColor(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> BOOK_AND_LAMP = regBlock(
            "book_and_lamp", BookAndLampBlock::new, BlockBehaviour.Properties.of().strength(0.25F).lightLevel(value -> 7).noCollission().noOcclusion());
    public static final DeferredBlock<Block> ITEM_FRAME_SHELL_THIN = regBlock(
            "thin_item_frame_shell", ItemFrameShellThinBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS));
    public static final DeferredBlock<Block> ITEM_FRAME_SHELL_BIG = regBlock(
            "item_frame_shell", ItemFrameShellBigBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS));
    //TODO:星空方块要做成非方块实体，用渲染器，天空盒和末地折跃门的结合。
    //public static final DeferredBlock<Block> STAR_BLOCK = regBlock(
    //        "star_block", StarBlock::new, BlockBehaviour.Properties.of().strength(0.3F, 6.0F).lightLevel(value -> 7).isValidSpawn(FSUtils::getFalse).isRedstoneConductor(FSUtils::getFalse).sound(SoundType.GLASS).noOcclusion());
    public static final DeferredBlock<Block> GLOWSTONE_LAMP = regSimpleBlock(
            "glowstone_lamp", BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE));
    public static final DeferredBlock<Block> REINFORCED_GLOWSTONE_LAMP = regSimpleBlock(
            "reinforced_glowstone_lamp", BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).strength(1.5F, 6.0F));
    public static final DeferredBlock<Block> RAINBOW_SEA_LANTERN = regSimpleBlock(
            "rainbow_sea_lantern", BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> REINFORCED_SEA_LANTERN = regSimpleBlock(
            "reinforced_sea_lantern", BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F));
    public static final DeferredBlock<Block> RAINBOW_REINFORCED_SEA_LANTERN = regSimpleBlock(
            "rainbow_reinforced_sea_lantern", BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> LIGHT_TUBE = regBlock(
            "light_tube", LightTubeBlock::new, BlockBehaviour.Properties.of().strength(0.3F).lightLevel(value -> 15).noCollission());
    public static final DeferredBlock<Block> LIGHT_PLATE = regBlock(
            "light_plate", LightPlateBlock::new, BlockBehaviour.Properties.of().strength(0.3F).lightLevel(value -> 15).noCollission());
    public static final DeferredBlock<Block> GREEN_FUN_ROOF = regBlock(
            "green_fun_roof", GreenFunRoofBlock::new, BlockBehaviour.Properties.of().strength(2.0F).ignitedByLava().mapColor(MapColor.PODZOL).sound(SoundType.WOOD));
    public static final DeferredBlock<Block> HALF_GRASS_BLOCK = regBlock(
            "half_grass_block", HalfGrassBlock::new, BlockBehaviour.Properties.of().strength(0.6F).mapColor(MapColor.GRASS).sound(SoundType.GRASS));
    public static final DeferredBlock<Block> HALF_PODZOL = regBlock(
            "half_podzol", HalfPodzolBlock::new, BlockBehaviour.Properties.of().strength(0.5F).mapColor(MapColor.PODZOL).sound(SoundType.GRAVEL));
    public static final DeferredBlock<Block> HALF_MYCELIUM = regBlock(
            "half_mycelium", HalfMyceliumBlock::new, BlockBehaviour.Properties.of().strength(0.6F).mapColor(MapColor.COLOR_PURPLE).sound(SoundType.GRASS));
    public static final DeferredBlock<Block> HALF_DIRT = regBlock(
            "half_dirt", HalfDirtBlock::new, BlockBehaviour.Properties.of().strength(0.5F).mapColor(MapColor.DIRT).sound(SoundType.GRAVEL));
    public static final DeferredBlock<Block> HALF_DIRT_PATH = regBlock(
            "half_dirt_path", HalfPathBlock::new, BlockBehaviour.Properties.of().strength(0.65F).mapColor(MapColor.DIRT).sound(SoundType.GRASS));
    public static final DeferredBlock<Block> FIREWOOD = regBlock(
            "firewood", Firewood::new, BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD).ignitedByLava().mapColor(MapColor.PODZOL));
    public static final DeferredBlock<Block> FIREPLACE_HEART = regBlock(
            "fireplace_heart", FireplaceHeartBlock::new, BlockBehaviour.Properties.of().strength(5.0F, 6.0F).requiresCorrectToolForDrops().lightLevel(BlockUtils.litBlockEmission(15)).noOcclusion().sound(SoundType.METAL));
    public static final DeferredBlock<Block> ROCK_PATH = regBlock(
            "rock_path", RockPathBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> CRAFTING_PAD = regBlock(
            "crafting_pad", CraftingPadBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
    public static final DeferredBlock<Block> CRAFTING_DESK = regBlock(
            "crafting_desk", CraftingDeskBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
    public static final DeferredBlock<Block> CRAFTING_BLOCK = regBlock(
            "crafting_block", CraftingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
    public static final DeferredBlock<Block> MEAT_BLOCK = regBlock(
            "meat_block", MeatBlock::new, BlockBehaviour.Properties.of().strength(0.5F).friction(0.75F).mapColor(MapColor.COLOR_ORANGE).sound(SoundType.SLIME_BLOCK).pushReaction(PushReaction.PUSH_ONLY));
    public static final DeferredBlock<Block> ROTTEN_FLESH_BLOCK = regBlock(
            "rotten_flesh_block", RottenFleshBlock::new, BlockBehaviour.Properties.of().strength(0.5F).mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOL));
    public static final DeferredBlock<Block> WATERLOGGED_COBBLESTONE = regBlock(
            "waterlogged_cobblestone", WaterloggedCobblestone::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));
    public static final DeferredBlock<Block> FOX_CARROTS = FLRegister.BLOCKS.registerBlock(
            "fox_carrots", FoxCarrotBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().pushReaction(PushReaction.DESTROY).randomTicks().sound(SoundType.CROP).noCollission().noOcclusion());
    public static final DeferredBlock<Block> FOX_CARROT_SHEAF = regBlock(
            "fox_carrot_sheaf", FoxCarrotSheaf::new, BlockBehaviour.Properties.of().strength(0.5F).mapColor(MapColor.SNOW).sound(SoundType.MOSS).pushReaction(PushReaction.DESTROY));
    public static final DeferredBlock<Block> FOX_CARROT_BASKET = regSimpleBlock(
            "fox_carrot_basket", BlockBehaviour.Properties.of().strength(0.5F).mapColor(MapColor.SNOW).sound(SoundType.MOSS));
    public static final DeferredBlock<Block> A_WALL_FLOWER_POT = regBlock(
            "a_wall_flower_pot", WallFlowerPortBlock::new, BlockBehaviour.Properties.of().strength(0.5F));
    public static final DeferredBlock<Block> B_WALL_FLOWER_POT = regBlock(
            "b_wall_flower_pot", WallFlowerPortBlock::new, BlockBehaviour.Properties.of().strength(0.5F));
    public static final DeferredBlock<Block> C_WALL_FLOWER_POT = regBlock(
            "c_wall_flower_pot", WallFlowerPortBlock::new, BlockBehaviour.Properties.of().strength(0.5F));
    public static final DeferredBlock<Block> D_WALL_FLOWER_POT = regBlock(
            "d_wall_flower_pot", WallFlowerPortBlock::new, BlockBehaviour.Properties.of().strength(0.5F));
    public static final DeferredBlock<Block> E_WALL_FLOWER_POT = regBlock(
            "e_wall_flower_pot", WallFlowerPortBlock::new, BlockBehaviour.Properties.of().strength(0.5F));
    public static final DeferredBlock<Block> F_WALL_FLOWER_POT = regBlock(
            "f_wall_flower_pot", WallFlowerPortBlock::new, BlockBehaviour.Properties.of().strength(0.5F));
    public static final DeferredBlock<Block> IRON_GUARDRAIL = regBlock(
            "iron_guardrail", IronGuardrailBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS));
    public static final DeferredBlock<Block> IRON_FRAME = regBlock(
            "iron_frame", IronFrameBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS).mapColor(MapColor.METAL).noCollission());
    public static final DeferredBlock<Block> IRON_CORRIDOR = regBlock(
            "iron_corridor", IronCorridorBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS).mapColor(MapColor.METAL).isValidSpawn(Blocks::never));
    public static final DeferredBlock<Block> IRON_CORRIDOR_SLAB = regBlock(
            "iron_corridor_slab", IronCorridorSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS).mapColor(MapColor.METAL).isValidSpawn(Blocks::never));
    public static final DeferredBlock<Block> IRON_CORRIDOR_STAIRS = regBlock(
            "iron_corridor_stairs", IronCorridorStairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS).mapColor(MapColor.METAL).isValidSpawn(Blocks::never));
    public static final DeferredBlock<IronLadderBlock> IRON_LADDER = reg(
            "iron_ladder", () -> new IronLadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS), IRON_FRAME.get()));
    public static final DeferredBlock<Block> IRON_SCAFFOLDING = FLRegister.BLOCKS.registerBlock(
            "iron_scaffolding", IronScaffoldingBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.METAL).isValidSpawn(Blocks::never).pushReaction(PushReaction.DESTROY).isRedstoneConductor(FSUtils::getFalse));
    public static final DeferredBlock<Block> SMOOTH_OAK_PLANKS = regSimpleBlock(
            "smooth_oak_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_SPRUCE_PLANKS = regSimpleBlock(
            "smooth_spruce_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_BIRCH_PLANKS = regSimpleBlock(
            "smooth_birch_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_JUNGLE_PLANKS = regSimpleBlock(
            "smooth_jungle_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_ACACIA_PLANKS = regSimpleBlock(
            "smooth_acacia_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_DARK_OAK_PLANKS = regSimpleBlock(
            "smooth_dark_oak_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_MANGROVE_PLANKS = regSimpleBlock(
            "smooth_mangrove_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_BAMBOO_PLANKS = regSimpleBlock(
            "smooth_bamboo_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_CHERRY_PLANKS = regSimpleBlock(
            "smooth_cherry_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_CRIMSON_PLANKS = regSimpleBlock(
            "smooth_crimson_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_WARPED_PLANKS = regSimpleBlock(
            "smooth_warped_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS));
    public static final DeferredBlock<Block> SPIRAL_STONE = regSimpleBlock(
            "spiral_stone", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));
    public static final DeferredBlock<Block> DEEPSLATE_SPIRAL_STONE = regSimpleBlock(
            "deepslate_spiral_stone", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));
    public static final DeferredBlock<Block> HEAVY_CHAINS = regBlock(
            "heavy_chains", HeavyChainsBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> NEO_FORGE = regBlock(
            "neoforge", NeoForgeBlock::new, BlockBehaviour.Properties.of().strength(0.5F, 3.0F).sound(SoundType.WOOL).mapColor(MapColor.COLOR_ORANGE));
    public static final DeferredBlock<Block> RAINBOW_WOOL = regSimpleBlock(
            "rainbow_wool", BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
    public static final DeferredBlock<Block> RAINBOW_TERRACOTTA = regSimpleBlock(
            "rainbow_terracotta", BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TERRACOTTA));
    public static final DeferredBlock<Block> RAINBOW_CONCRETE = regSimpleBlock(
            "rainbow_concrete", BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE));
    public static final DeferredBlock<Block> RAINBOW_GLASS = reg(
            "rainbow_glass", () -> new StainedGlassBlock(DyeColor.WHITE, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    //TODO:彩虹玻璃板
    public static final DeferredBlock<Block> OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE = regSimpleBlock(
            "oak_planks_and_light_gray_concrete", BlockBehaviour.Properties.of().strength(2.0F, 3.0F).mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD));
    public static final DeferredBlock<Block> SPRUCE_PLANKS_AND_GRAY_CONCRETE = regSimpleBlock(
            "spruce_planks_and_gray_concrete", BlockBehaviour.Properties.of().strength(2.0F, 3.0F).mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD));
    public static final DeferredBlock<Block> OAK_PLANKS_AND_SPRUCE_PLANKS = regSimpleBlock(
            "oak_planks_and_spruce_planks", BlockBehaviour.Properties.of().strength(2.0F, 3.0F).mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD));
    public static final DeferredBlock<Block> WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE = regSimpleBlock(
            "white_concrete_and_light_gray_concrete", BlockBehaviour.Properties.of().strength(1.8F).mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DEEPSLATE_TILES_AND_SPRUCE_PLANKS = regSimpleBlock(
            "deepslate_tiles_and_spruce_planks", BlockBehaviour.Properties.of().strength(3.5F, 6.0F).mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.DEEPSLATE_TILES));
    public static final DeferredBlock<Block> DEEPSLATE_TILES_AND_MANGROVE_PLANKS = regSimpleBlock(
            "deepslate_tiles_and_mangrove_planks", BlockBehaviour.Properties.of().strength(3.5F, 6.0F).mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.DEEPSLATE_TILES));
    public static final DeferredBlock<Block> DARK_PRISMARINE_AND_SPRUCE_PLANKS = regSimpleBlock(
            "dark_prismarine_and_spruce_planks", BlockBehaviour.Properties.of().strength(1.5F, 6.0F).mapColor(MapColor.DIAMOND).instrument(NoteBlockInstrument.BASEDRUM));
    public static final DeferredBlock<Block> DARK_PRISMARINE_AND_MANGROVE_PLANKS = regSimpleBlock(
            "dark_prismarine_and_mangrove_planks", BlockBehaviour.Properties.of().strength(1.5F, 6.0F).mapColor(MapColor.DIAMOND).instrument(NoteBlockInstrument.BASEDRUM));
    public static final DeferredBlock<Block> BRICKS_AND_BIRCH_PLANKS = regSimpleBlock(
            "bricks_and_birch_planks", BlockBehaviour.Properties.of().strength(2.0F, 6.0F).mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASEDRUM));
    public static final DeferredBlock<Block> FAKE_HOPPER = regBlock(
            "fake_hopper", FakeHopperBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> FAKE_FURNACE = regFakeBe(
            "fake_furnace", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE).lightLevel(value -> 0));
    public static final DeferredBlock<Block> LIT_FAKE_FURNACE = regFakeBe(
            "lit_fake_furnace", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE).lightLevel(value -> 13));
    public static final DeferredBlock<Block> FAKE_BLAST_FURNACE = regFakeBe(
            "fake_blast_furnace", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLAST_FURNACE).lightLevel(value -> 0));
    public static final DeferredBlock<Block> LIT_FAKE_BLAST_FURNACE = regFakeBe(
            "lit_fake_blast_furnace", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLAST_FURNACE).lightLevel(value -> 13));
    public static final DeferredBlock<Block> FAKE_SMOKER = regFakeBe(
            "fake_smoker", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOKER).lightLevel(value -> 0));
    public static final DeferredBlock<Block> LIT_FAKE_SMOKER = regFakeBe(
            "lit_fake_smoker", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOKER).lightLevel(value -> 13));
    public static final DeferredBlock<Block> FAKE_BARREL = regFakeBe(
            "fake_barrel", FacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL));
    public static final DeferredBlock<Block> FAKE_CAMPFIRE = regBlock(
            "fake_campfire", FakeCampfireBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(value -> 0));
    public static final DeferredBlock<Block> LIT_FAKE_CAMPFIRE = reg(
            "lit_fake_campfire", () -> new LitFakeCampfireBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(value -> 15), true, 1));
    public static final DeferredBlock<Block> LIT_FAKE_SOUL_CAMPFIRE = reg(
            "lit_fake_soul_campfire", () -> new LitFakeCampfireBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_CAMPFIRE).lightLevel(value -> 10), false, 2));
    public static final DeferredBlock<Block> FAKE_CHEST = regBlock(
            "fake_chest", FakeChestBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST));
    public static final DeferredBlock<Block> FAKE_CHISELED_BOOKSHELF = regFakeBe(
            "fake_chiseled_bookshelf", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_BOOKSHELF));
    public static final DeferredBlock<Block> FAKE_LECTERN = regBlock(
            "fake_lectern", FakeLecternBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LECTERN));
    public static final DeferredBlock<Block> FAKE_BEEHIVE = regFakeBe(
            "fake_beehive", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE));
    public static final DeferredBlock<Block> FAKE_DIAMOND_BLOCK = regSimpleBlock(
            "fake_diamond_block", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> FAKE_GOLD_BLOCK = regSimpleBlock(
            "fake_gold_block", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> FAKE_IRON_BLOCK = regSimpleBlock(
            "fake_iron_block", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> FAKE_NETHERITE_BLOCK = regSimpleBlock(
            "fake_netherite_block", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> FAKE_BEDROCK = regSimpleBlock(
            "fake_bedrock", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_CHISELED_BOOKSHELF = regSimpleBlock(
            "texture_chiseled_bookshelf", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_CHISELED_BOOKSHELF_TOP = regSimpleBlock(
            "texture_chiseled_bookshelf_top", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_CHISELED_BOOKSHELF_SIDE = regBlock(
            "texture_chiseled_bookshelf_side", HorizonAxisBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_LOOM = regBlock(
            "texture_loom", HorizonAxisBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_BEEHIVE_TOP = regBlock(
            "texture_beehive_top", HorizonAxisBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_SMITHING_TABLE_BOTTOM = regSimpleBlock(
            "texture_smithing_table_bottom", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_SMITHING_TABLE_TOP = regSimpleBlock(
            "texture_smithing_table_top", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_COMPOSTER_BOTTOM = regBlock(
            "texture_composter_bottom", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_BEE_NEST_TOP = regSimpleBlock(
            "texture_bee_nest_top", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_FURNACE = regSimpleBlock(
            "texture_furnace", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_FURNACE_TOP = regSimpleBlock(
            "texture_furnace_top", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_BLAST_FURNACE = regSimpleBlock(
            "texture_blast_furnace", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_BLAST_FURNACE_TOP = regSimpleBlock(
            "texture_blast_furnace_top", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_SMOKER = regSimpleBlock(
            "texture_smoker", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    //TODO:磁石在新版本便宜了，可以移除
    public static final DeferredBlock<Block> TEXTURE_LODESTONE = regSimpleBlock(
            "texture_lodestone", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_LODESTONE_SIDE = regSimpleBlock(
            "texture_lodestone_side", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_LODESTONE_TOP = regSimpleBlock(
            "texture_lodestone_top", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> RED_LAMP = regSimpleBlock(
            "red_lamp", BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_RED).lightLevel(value -> 5).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> GREEN_LAMP = regSimpleBlock(
            "green_lamp", BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_GREEN).lightLevel(value -> 5).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> BLUE_LAMP = regSimpleBlock(
            "blue_lamp", BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 5).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> YELLOW_LAMP = regSimpleBlock(
            "yellow_lamp", BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 10).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> CYAN_LAMP = regSimpleBlock(
            "cyan_lamp", BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_CYAN).lightLevel(value -> 10).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> PURPLE_LAMP = regSimpleBlock(
            "purple_lamp", BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_PURPLE).lightLevel(value -> 10).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> RAINBOW_LAMP = regSimpleBlock(
            "rainbow_lamp", BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.SNOW).lightLevel(value -> 10).sound(SoundType.AMETHYST));

    private static DeferredBlock<Block> regSimpleBlock(String name, BlockBehaviour.Properties props) {
        DeferredBlock<Block> deferredBlock = FLRegister.BLOCKS.registerSimpleBlock(name, props);
        FLRegister.ITEMS.registerSimpleBlockItem(deferredBlock);
        return deferredBlock;
    }

    private static <B extends Block> DeferredBlock<B> regBlock(String name, Function<BlockBehaviour.Properties, ? extends B> func, BlockBehaviour.Properties props) {
        DeferredBlock<B> deferredBlock = FLRegister.BLOCKS.registerBlock(name, func, props);
        FLRegister.ITEMS.registerSimpleBlockItem(deferredBlock);
        return deferredBlock;
    }

    public static  <B extends Block> DeferredBlock<B> reg(String name, Supplier<? extends B> sup) {
        DeferredBlock<B> deferredBlock = FLRegister.BLOCKS.register(name, sup);
        FLRegister.ITEMS.registerSimpleBlockItem(deferredBlock);
        return deferredBlock;
    }

    private static <B extends Block> DeferredBlock<B> regFakeBe(String name, Function<BlockBehaviour.Properties, ? extends B> func, BlockBehaviour.Properties props) {
        DeferredBlock<B> deferredBlock = FLRegister.BLOCKS.registerBlock(name, func, props);
        FLRegister.ITEMS.register(name, () -> new FakeBlockEntityItem(deferredBlock.get(), new Item.Properties()));
        return deferredBlock;
    }

    private static <T extends Block> DeferredBlock<T>[] colorReg(Function<DyeColor, DeferredBlock<T>> func){
        DyeColor[] colors = DyeColor.values();
        DeferredBlock<T>[] blocks = new DeferredBlock[colors.length];
        for (DyeColor color : colors) blocks[color.getId()] = func.apply(color);
        return blocks;
    }

    public static void init() {}

    private FLBlocks() {}
}
