package net.fiercemanul.fiercedecoration.world.level.block;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.world.level.block.*;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

public final class FDBlocks {


    public static final DeferredBlock<Block> SOUL_CRYSTAL_ORNAMENT = FierceDecoration.BLOCKS.registerBlock(
            "soul_crystal_ornament", SoulCrystalOrnamentBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> PORTABLE_WORKSTATION = FierceDecoration.BLOCKS.registerBlock(
            "portable_workstation", PortableWorkstationBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> LAPTOP_TERMINAL = FierceDecoration.BLOCKS.registerBlock(
            "laptop_terminal", LaptopTerminalBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> BOOK_AND_LAMP = FierceDecoration.BLOCKS.registerBlock(
            "book_and_lamp", BookAndLampBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> ITEM_FRAME_SHELL_THIN = FierceDecoration.BLOCKS.registerBlock(
            "item_frame_shell_thin", ItemFrameShellThinBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS));
    public static final DeferredBlock<Block> ITEM_FRAME_SHELL_BIG = FierceDecoration.BLOCKS.registerBlock(
            "item_frame_shell_big", ItemFrameShellBigBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS));
    public static final DeferredBlock<Block> STAR_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "star_block", StarBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> SMOOTH_GLOWSTONE = FierceDecoration.BLOCKS.registerBlock(
            "smooth_glowstone", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE));
    public static final DeferredBlock<Block> REINFORCED_SMOOTH_GLOWSTONE = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_smooth_glowstone", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).strength(1.5F, 6.0F));
    public static final DeferredBlock<Block> REINFORCED_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F));
    public static final DeferredBlock<Block> LIGHT_TUBE_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "light_tube", LightTubeBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> LIGHT_PLATE_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "light_plate", LightPlateBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> GREEN_FUN_ROOF = FierceDecoration.BLOCKS.registerBlock(
            "green_fun_roof", GreenFunRoofBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> HALF_GRASS_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "half_grass_block", HalfGrassBlock::new, BlockBehaviour.Properties.of().strength(0.6F).mapColor(MapColor.GRASS).sound(SoundType.GRASS));
    public static final DeferredBlock<Block> HALF_PODZOL = FierceDecoration.BLOCKS.registerBlock(
            "half_podzol_block", HalfPodzolBlock::new, BlockBehaviour.Properties.of().strength(0.5F).mapColor(MapColor.PODZOL).sound(SoundType.GRAVEL));
    public static final DeferredBlock<Block> HALF_MYCELIUM = FierceDecoration.BLOCKS.registerBlock(
            "half_mycelium_block", HalfMyceliumBlock::new, BlockBehaviour.Properties.of().strength(0.6F).mapColor(MapColor.COLOR_PURPLE).sound(SoundType.GRASS));
    public static final DeferredBlock<Block> HALF_DIRT = FierceDecoration.BLOCKS.registerBlock(
            "half_dirt_block", HalfDirtBlock::new, BlockBehaviour.Properties.of().strength(0.5F).mapColor(MapColor.DIRT).sound(SoundType.GRAVEL));
    public static final DeferredBlock<Block> HALF_PATH_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "half_path_block", HalfPathBlock::new, BlockBehaviour.Properties.of().strength(0.65F).mapColor(MapColor.DIRT).sound(SoundType.GRASS));
    public static final DeferredBlock<Block> FIREWOOD = FierceDecoration.BLOCKS.registerBlock(
            "firewood", Firewood::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> FIREPLACE_HEART = FierceDecoration.BLOCKS.registerBlock(
            "fireplace_heart", FireplaceHeartBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> ROCK_PATH = FierceDecoration.BLOCKS.registerBlock(
            "rock_path", RockPathBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> CRAFTING_PAD = FierceDecoration.BLOCKS.registerBlock(
            "crafting_pad", CraftingPadBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
    public static final DeferredBlock<Block> CRAFTING_DESK = FierceDecoration.BLOCKS.registerBlock(
            "crafting_desk", CraftingDeskBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
    public static final DeferredBlock<Block> CRAFTING_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "crafting_block", CraftingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
    public static final DeferredBlock<Block> ROTTEN_FLESH_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "rotten_flesh_block", RottenFleshBlock::new, BlockBehaviour.Properties.of()
                                                                                  .strength(0.5F)
                                                                                  .friction(0.8F)
                                                                                  .jumpFactor(0.75F)
                                                                                  .isValidSpawn(FSUtils::getTrue)
                                                                                  .isRedstoneConductor(FSUtils::getTrue)
                                                                                  .isSuffocating(FSUtils::getTrue)
                                                                                  .isViewBlocking(FSUtils::getTrue)
                                                                                  .mapColor(MapColor.COLOR_BROWN)
                                                                                  .sound(SoundType.SLIME_BLOCK));
    public static final DeferredBlock<Block> WATERLOGGED_COBBLESTONE = FierceDecoration.BLOCKS.registerBlock(
            "waterlogged_cobblestone", WaterloggedCobblestone::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));
    public static final DeferredBlock<Block> FOX_CARROT = FierceDecoration.BLOCKS.registerBlock(
            "fox_carrot", FoxCarrotBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
                                                                        .instabreak()
                                                                        .pushReaction(PushReaction.DESTROY)
                                                                        .randomTicks()
                                                                        .sound(SoundType.CROP)
                                                                        .noOcclusion());
    public static final DeferredBlock<Block> FOX_CARROT_SHEAF = FierceDecoration.BLOCKS.registerBlock(
            "fox_carrot_sheaf", FoxCarrotSheaf::new, BlockBehaviour.Properties.of()
                                                                               .strength(0.5F)
                                                                               .mapColor(MapColor.SNOW)
                                                                               .sound(SoundType.MOSS)
                                                                               .pushReaction(PushReaction.DESTROY));
    public static final DeferredBlock<Block> FOX_CARROT_BASKET = FierceDecoration.BLOCKS.registerSimpleBlock(
            "fox_carrot_basket", BlockBehaviour.Properties.of()
                                                          .strength(0.5F)
                                                          .mapColor(MapColor.SNOW)
                                                          .sound(SoundType.MOSS));
    public static final DeferredBlock<Block> WALL_FLOWER_POT_A = FierceDecoration.BLOCKS.registerBlock(
            "wall_flower_pot_a", WallFlowerPortBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> WALL_FLOWER_POT_B = FierceDecoration.BLOCKS.registerBlock(
            "wall_flower_pot_b", WallFlowerPortBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> WALL_FLOWER_POT_C = FierceDecoration.BLOCKS.registerBlock(
            "wall_flower_pot_c", WallFlowerPortBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> WALL_FLOWER_POT_D = FierceDecoration.BLOCKS.registerBlock(
            "wall_flower_pot_d", WallFlowerPortBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> WALL_FLOWER_POT_E = FierceDecoration.BLOCKS.registerBlock(
            "wall_flower_pot_e", WallFlowerPortBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> WALL_FLOWER_POT_F = FierceDecoration.BLOCKS.registerBlock(
            "wall_flower_pot_f", WallFlowerPortBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> SMOOTH_OAK_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_oak_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_SPRUCE_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_spruce_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_BIRCH_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_birch_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_JUNGLE_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_jungle_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_ACACIA_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_acacia_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_DARK_OAK_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_dark_oak_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_MANGROVE_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_mangrove_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_BAMBOO_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_bamboo_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_CHERRY_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_cherry_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_CRIMSON_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_crimson_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS));
    public static final DeferredBlock<Block> SMOOTH_WARPED_PLANKS = FierceDecoration.BLOCKS.registerSimpleBlock(
            "smooth_warped_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS));
    public static final DeferredBlock<Block> HEAVY_CHAINS_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "heavy_chains", HeavyChainsBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> NEO_FORGE = FierceDecoration.BLOCKS.registerBlock(
            "neo_forge", NeoForgeBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> RAINBOW_WOOL = FierceDecoration.BLOCKS.registerSimpleBlock(
            "rainbow_wool", BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
    public static final DeferredBlock<Block> RAINBOW_TERRACOTTA = FierceDecoration.BLOCKS.registerSimpleBlock(
            "rainbow_terracotta", BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TERRACOTTA));
    public static final DeferredBlock<Block> RAINBOW_CONCRETE = FierceDecoration.BLOCKS.registerSimpleBlock(
            "rainbow_concrete", BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE));
    public static final DeferredBlock<Block> RAINBOW_GLASS = FierceDecoration.BLOCKS.register(
            "rainbow_glass", () -> new StainedGlassBlock(DyeColor.WHITE, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE = FierceDecoration.BLOCKS.registerBlock(
            "oak_planks_and_light_gray_concrete", WrenchDismantleBlock::new, BlockBehaviour.Properties.of()
                                                                           .strength(2.0F, 3.0F)
                                                                           .mapColor(MapColor.WOOD)
                                                                           .instrument(NoteBlockInstrument.BASS)
                                                                           .sound(SoundType.WOOD));
    public static final DeferredBlock<Block> SPRUCE_PLANKS_AND_GRAY_CONCRETE = FierceDecoration.BLOCKS.registerBlock(
            "spruce_planks_and_gray_concrete", WrenchDismantleBlock::new, BlockBehaviour.Properties.of()
                                                                        .strength(2.0F, 3.0F)
                                                                        .mapColor(MapColor.PODZOL)
                                                                        .instrument(NoteBlockInstrument.BASS)
                                                                        .sound(SoundType.WOOD));
    public static final DeferredBlock<Block> OAK_PLANKS_AND_SPRUCE_PLANKS = FierceDecoration.BLOCKS.registerBlock(
            "oak_planks_and_spruce_planks", WrenchDismantleBlock::new, BlockBehaviour.Properties.of()
                                                                                                .strength(2.0F, 3.0F)
                                                                                                .mapColor(MapColor.WOOD)
                                                                                                .instrument(NoteBlockInstrument.BASS)
                                                                                                .sound(SoundType.WOOD));
    public static final DeferredBlock<Block> WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE = FierceDecoration.BLOCKS.registerBlock(
            "white_concrete_and_light_gray_concrete", WrenchDismantleBlock::new, BlockBehaviour.Properties.of()
                                                                               .strength(1.8F)
                                                                               .mapColor(DyeColor.WHITE)
                                                                               .instrument(NoteBlockInstrument.BASEDRUM)
                                                                               .requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DEEPSLATE_TILES_AND_SPRUCE_PLANKS = FierceDecoration.BLOCKS.registerBlock(
            "deepslate_tiles_and_spruce_planks", WrenchDismantleBlock::new, BlockBehaviour.Properties.of()
                                                                                                          .strength(3.5F, 6.0F)
                                                                                                          .mapColor(MapColor.DEEPSLATE)
                                                                                                          .instrument(NoteBlockInstrument.BASEDRUM)
                                                                                                          .sound(SoundType.DEEPSLATE_TILES));
    public static final DeferredBlock<Block> DEEPSLATE_TILES_AND_MANGROVE_PLANKS = FierceDecoration.BLOCKS.registerBlock(
            "deepslate_tiles_and_mangrove_planks", WrenchDismantleBlock::new, BlockBehaviour.Properties.of()
                                                                                                     .strength(3.5F, 6.0F)
                                                                                                     .mapColor(MapColor.DEEPSLATE)
                                                                                                     .instrument(NoteBlockInstrument.BASEDRUM)
                                                                                                     .sound(SoundType.DEEPSLATE_TILES));
    public static final DeferredBlock<Block> DARK_PRISMARINE_AND_SPRUCE_PLANKS = FierceDecoration.BLOCKS.registerBlock(
            "dark_prismarine_and_spruce_planks", WrenchDismantleBlock::new, BlockBehaviour.Properties.of()
                                                                                                     .strength(1.5F, 6.0F)
                                                                                                     .mapColor(MapColor.DIAMOND)
                                                                                                     .instrument(NoteBlockInstrument.BASEDRUM));
    public static final DeferredBlock<Block> DARK_PRISMARINE_AND_MANGROVE_PLANKS = FierceDecoration.BLOCKS.registerBlock(
            "dark_prismarine_and_mangrove_planks", WrenchDismantleBlock::new, BlockBehaviour.Properties.of()
                                                                                                     .strength(1.5F, 6.0F)
                                                                                                     .mapColor(MapColor.DIAMOND)
                                                                                                     .instrument(NoteBlockInstrument.BASEDRUM));
    public static final DeferredBlock<Block> BRICKS_AND_BIRCH_PLANKS = FierceDecoration.BLOCKS.registerBlock(
            "bricks_and_birch_planks", WrenchDismantleBlock::new, BlockBehaviour.Properties.of()
                                                                                           .strength(2.0F, 6.0F)
                                                                                           .mapColor(MapColor.COLOR_RED)
                                                                                           .instrument(NoteBlockInstrument.BASEDRUM));
    public static final DeferredBlock<Block> FAKE_HOPPER = FierceDecoration.BLOCKS.registerBlock(
            "fake_hopper", FakeHopperBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> FAKE_FURNACE = FierceDecoration.BLOCKS.registerBlock(
            "fake_furnace", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE).lightLevel(value -> 0));
    public static final DeferredBlock<Block> LIT_FAKE_FURNACE = FierceDecoration.BLOCKS.registerBlock(
            "lit_fake_furnace", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE).lightLevel(value -> 13));
    public static final DeferredBlock<Block> FAKE_BLAST_FURNACE = FierceDecoration.BLOCKS.registerBlock(
            "fake_blast_furnace", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLAST_FURNACE).lightLevel(value -> 0));
    public static final DeferredBlock<Block> LIT_FAKE_BLAST_FURNACE = FierceDecoration.BLOCKS.registerBlock(
            "lit_fake_blast_furnace", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLAST_FURNACE).lightLevel(value -> 13));
    public static final DeferredBlock<Block> FAKE_SMOKER = FierceDecoration.BLOCKS.registerBlock(
            "fake_smoker", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOKER).lightLevel(value -> 0));
    public static final DeferredBlock<Block> LIT_FAKE_SMOKER = FierceDecoration.BLOCKS.registerBlock(
            "lit_fake_smoker", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOKER).lightLevel(value -> 13));
    public static final DeferredBlock<Block> FAKE_BARREL = FierceDecoration.BLOCKS.registerBlock(
            "fake_barrel", FacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL));
    public static final DeferredBlock<Block> FAKE_CAMPFIRE = FierceDecoration.BLOCKS.registerBlock(
            "fake_campfire", FakeCampfireBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(value -> 0));
    public static final DeferredBlock<Block> LIT_FAKE_CAMPFIRE = FierceDecoration.BLOCKS.register(
            "lit_fake_campfire", () -> new LitFakeCampfireBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).lightLevel(value -> 15), true, 1));
    public static final DeferredBlock<Block> LIT_FAKE_SOUL_CAMPFIRE = FierceDecoration.BLOCKS.register(
            "lit_fake_soul_campfire", () -> new LitFakeCampfireBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_CAMPFIRE).lightLevel(value -> 10), false, 2));
    public static final DeferredBlock<Block> FAKE_CHEST = FierceDecoration.BLOCKS.registerBlock(
            "fake_chest", FakeChestBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST));
    public static final DeferredBlock<Block> FAKE_CHISELED_BOOKSHELF = FierceDecoration.BLOCKS.registerBlock(
            "fake_chiseled_bookshelf", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_BOOKSHELF));
    public static final DeferredBlock<Block> FAKE_LECTERN = FierceDecoration.BLOCKS.registerBlock(
            "fake_lectern", FakeLecternBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LECTERN));
    public static final DeferredBlock<Block> FAKE_BEEHIVE = FierceDecoration.BLOCKS.registerBlock(
            "fake_beehive", HorizonFacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE));
    public static final DeferredBlock<Block> FAKE_DIAMOND_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "fake_diamond_block", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> FAKE_GOLD_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "fake_gold_block", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> FAKE_IRON_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "fake_iron_block", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> FAKE_NETHERITE_BLOCK = FierceDecoration.BLOCKS.registerBlock(
            "fake_netherite_block", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> FAKE_BEDROCK = FierceDecoration.BLOCKS.registerBlock(
            "fake_bedrock_block", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_CHISELED_BOOKSHELF = FierceDecoration.BLOCKS.registerBlock(
            "texture_chiseled_bookshelf", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_CHISELED_BOOKSHELF_TOP = FierceDecoration.BLOCKS.registerBlock(
            "texture_chiseled_bookshelf_top", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_CHISELED_BOOKSHELF_SIDE = FierceDecoration.BLOCKS.registerBlock(
            "texture_chiseled_bookshelf_side", HorizonAxisBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_LOOM = FierceDecoration.BLOCKS.registerBlock(
            "texture_loom", HorizonAxisBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_BEEHIVE_TOP = FierceDecoration.BLOCKS.registerBlock(
            "texture_beehive_top", HorizonAxisBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_SMITHING_TABLE_BOTTOM = FierceDecoration.BLOCKS.registerBlock(
            "texture_smithing_table_bottom", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_COMPOSTER_BOTTOM = FierceDecoration.BLOCKS.registerBlock(
            "texture_composter_bottom", AxisBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_BEE_NEST_TOP = FierceDecoration.BLOCKS.registerBlock(
            "texture_bee_nest_top", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> TEXTURE_FURNACE = FierceDecoration.BLOCKS.registerBlock(
            "texture_furnace", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_FURNACE_TOP = FierceDecoration.BLOCKS.registerBlock(
            "texture_furnace_top", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_BLAST_FURNACE = FierceDecoration.BLOCKS.registerBlock(
            "texture_blast_furnace", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_BLAST_FURNACE_TOP = FierceDecoration.BLOCKS.registerBlock(
            "texture_blast_furnace_top", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_SMOKER = FierceDecoration.BLOCKS.registerBlock(
            "texture_smoker", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_SMITHING_TABLE_TOP = FierceDecoration.BLOCKS.registerBlock(
            "texture_smithing_table_top", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_LODESTONE = FierceDecoration.BLOCKS.registerBlock(
            "texture_lodestone", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_LODESTONE_SIDE = FierceDecoration.BLOCKS.registerBlock(
            "texture_lodestone_side", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> TEXTURE_LODESTONE_TOP = FierceDecoration.BLOCKS.registerBlock(
            "texture_lodestone_top", WrenchDismantleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> WHITE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "white_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> ORANGE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "orange_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_ORANGE).lightLevel(value -> 11));
    public static final DeferredBlock<Block> MAGENTA_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "magenta_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_MAGENTA).lightLevel(value -> 10));
    public static final DeferredBlock<Block> LIGHT_BLUE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "light_blue_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel(value -> 11));
    public static final DeferredBlock<Block> YELLOW_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "yellow_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_YELLOW).lightLevel(value -> 14));
    public static final DeferredBlock<Block> LIME_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "lime_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel(value -> 12));
    public static final DeferredBlock<Block> PINK_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "pink_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_PINK).lightLevel(value -> 11));
    public static final DeferredBlock<Block> GRAY_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "gray_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_GRAY).lightLevel(value -> 9));
    public static final DeferredBlock<Block> LIGHT_GRAY_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "light_gray_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_LIGHT_GRAY).lightLevel(value -> 11));
    public static final DeferredBlock<Block> CYAN_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "cyan_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_CYAN).lightLevel(value -> 10));
    public static final DeferredBlock<Block> PURPLE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "purple_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_PURPLE).lightLevel(value -> 9));
    public static final DeferredBlock<Block> BLUE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "blue_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 9));
    public static final DeferredBlock<Block> BROWN_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "brown_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_BROWN).lightLevel(value -> 9));
    public static final DeferredBlock<Block> GREEN_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "green_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_GREEN).lightLevel(value -> 9));
    public static final DeferredBlock<Block> RED_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "red_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_RED).lightLevel(value -> 9));
    public static final DeferredBlock<Block> BLACK_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "black_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_BLACK).lightLevel(value -> 8));
    public static final DeferredBlock<Block> REINFORCED_WHITE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_white_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> REINFORCED_ORANGE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_orange_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_ORANGE).lightLevel(value -> 11));
    public static final DeferredBlock<Block> REINFORCED_MAGENTA_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_magenta_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_MAGENTA).lightLevel(value -> 10));
    public static final DeferredBlock<Block> REINFORCED_LIGHT_BLUE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_light_blue_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel(value -> 11));
    public static final DeferredBlock<Block> REINFORCED_YELLOW_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_yellow_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_YELLOW).lightLevel(value -> 14));
    public static final DeferredBlock<Block> REINFORCED_LIME_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_lime_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel(value -> 12));
    public static final DeferredBlock<Block> REINFORCED_PINK_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_pink_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_PINK).lightLevel(value -> 11));
    public static final DeferredBlock<Block> REINFORCED_GRAY_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_gray_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_GRAY).lightLevel(value -> 9));
    public static final DeferredBlock<Block> REINFORCED_LIGHT_GRAY_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_light_gray_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_LIGHT_GRAY).lightLevel(value -> 11));
    public static final DeferredBlock<Block> REINFORCED_CYAN_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_cyan_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_CYAN).lightLevel(value -> 10));
    public static final DeferredBlock<Block> REINFORCED_PURPLE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_purple_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_PURPLE).lightLevel(value -> 9));
    public static final DeferredBlock<Block> REINFORCED_BLUE_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_blue_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 9));
    public static final DeferredBlock<Block> REINFORCED_BROWN_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_brown_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_BROWN).lightLevel(value -> 9));
    public static final DeferredBlock<Block> REINFORCED_GREEN_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_green_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_GREEN).lightLevel(value -> 9));
    public static final DeferredBlock<Block> REINFORCED_RED_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_red_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_RED).lightLevel(value -> 9));
    public static final DeferredBlock<Block> REINFORCED_BLACK_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "reinforced_black_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.COLOR_BLACK).lightLevel(value -> 8));
    public static final DeferredBlock<Block> RAINBOW_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "rainbow_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> RAINBOW_REINFORCED_SEA_LANTERN = FierceDecoration.BLOCKS.registerBlock(
            "rainbow_reinforced_sea_lantern", SimpleGlowingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F).mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> RED_LAMP = FierceDecoration.BLOCKS.registerBlock(
            "red_lamp", SimpleGlowingBlock::new, BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_RED).lightLevel(value -> 5).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> GREEN_LAMP = FierceDecoration.BLOCKS.registerBlock(
            "green_lamp", SimpleGlowingBlock::new, BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_GREEN).lightLevel(value -> 5).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> BLUE_LAMP = FierceDecoration.BLOCKS.registerBlock(
            "blue_lamp", SimpleGlowingBlock::new, BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 5).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> YELLOW_LAMP = FierceDecoration.BLOCKS.registerBlock(
            "yellow_lamp", SimpleGlowingBlock::new, BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 10).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> CYAN_LAMP = FierceDecoration.BLOCKS.registerBlock(
            "cyan_lamp", SimpleGlowingBlock::new, BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_CYAN).lightLevel(value -> 10).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> PURPLE_LAMP = FierceDecoration.BLOCKS.registerBlock(
            "purple_lamp", SimpleGlowingBlock::new, BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_PURPLE).lightLevel(value -> 10).sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> RAINBOW_LAMP = FierceDecoration.BLOCKS.registerBlock(
            "rainbow_lamp", SimpleGlowingBlock::new, BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.SNOW).lightLevel(value -> 10).sound(SoundType.AMETHYST));


    public static void loadClass() {}

    private FDBlocks() {}
}
