package net.fiercemanul.fiercedecoration.registries;

import net.fiercemanul.fiercedecoration.data.BlockBulkRegisterDataProperties;
import net.fiercemanul.fiercedecoration.world.level.block.FDBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class BlockBulkRegisterKeys {


    public final List<BlockBulkRegisterKey> keys = new LinkedList<>();
    private final BlockBulkRegisterProperties regs = new BlockBulkRegisterProperties();
    private final MapColorHolders colors = new MapColorHolders();


    public BlockBulkRegisterKeys() {
        BlockBulkRegisterKey.RegisterPropertiesBuilder regLogs = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.pillar,
                regs.woodenGuardrail,
                regs.peepWindow,
                regs.table,
                regs.chair
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exLog = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_AXE,
                BlockBulkRegisterDataProperties.TextureType.LOG
        ));
        BlockBulkRegisterKey.ExtendPropertiesBuilder exStem = exLog.getCopy().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.NON_FLAMMABLE_WOOD
        ));
        BlockBulkRegisterKey.RegisterPropertiesBuilder regMcPlanks = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.peepWindow
        );
        BlockBulkRegisterKey.RegisterPropertiesBuilder regFdPlanks = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.vanillaPlanks,
                regs.cutBlock,
                regs.pillar,
                regs.woodenGuardrailB,
                regs.windowA,
                regs.windowB,
                regs.peepWindow,
                regs.table,
                regs.chair,
                regs.gardenChair,
                regs.cabinet
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exPlanks = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_AXE,
                BlockBulkRegisterDataProperties.TextureType.CUBE
        ));
        BlockBulkRegisterKey.ExtendPropertiesBuilder exNoFlamPlanks = exPlanks.getCopy().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.NON_FLAMMABLE_WOOD
        ));
        keys.add(new BlockBulkRegisterKey(Blocks.OAK_LOG, new MapColorHolders.Pillar(MapColor.WOOD, MapColor.PODZOL), regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_OAK_LOG, colors.wood, regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.OAK_PLANKS, colors.wood, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_OAK_PLANKS, Blocks.OAK_PLANKS, colors.wood, regFdPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.SPRUCE_LOG, new MapColorHolders.Pillar(MapColor.PODZOL, MapColor.COLOR_BROWN), regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_SPRUCE_LOG, colors.podzol, regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.SPRUCE_PLANKS, colors.podzol, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_SPRUCE_PLANKS, Blocks.SPRUCE_PLANKS, colors.podzol, regFdPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.BIRCH_LOG, new MapColorHolders.Pillar(MapColor.SAND, MapColor.QUARTZ), regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_BIRCH_LOG, colors.sand, regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.BIRCH_PLANKS, colors.sand, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_BIRCH_PLANKS, Blocks.BIRCH_PLANKS, colors.sand, regFdPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.JUNGLE_LOG, new MapColorHolders.Pillar(MapColor.DIRT, MapColor.PODZOL), regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_JUNGLE_LOG, colors.dirt, regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.JUNGLE_PLANKS, colors.dirt, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_JUNGLE_PLANKS, Blocks.JUNGLE_PLANKS, colors.dirt, regFdPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.ACACIA_LOG, new MapColorHolders.Pillar(MapColor.COLOR_ORANGE, MapColor.STONE), regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_ACACIA_LOG, colors.color_orange, regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.ACACIA_PLANKS, colors.color_orange, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_ACACIA_PLANKS, Blocks.ACACIA_PLANKS, colors.color_orange, regFdPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.DARK_OAK_LOG, colors.color_brown, regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_DARK_OAK_LOG, colors.color_brown, regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.DARK_OAK_PLANKS, colors.color_brown, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_DARK_OAK_PLANKS, Blocks.DARK_OAK_PLANKS, colors.color_brown, regFdPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.MANGROVE_LOG, new MapColorHolders.Pillar(MapColor.COLOR_RED, MapColor.PODZOL), regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_MANGROVE_LOG, colors.color_red, regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.MANGROVE_PLANKS, colors.color_red, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_MANGROVE_PLANKS, Blocks.MANGROVE_PLANKS, colors.color_red, regFdPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.BAMBOO_BLOCK, new MapColorHolders.Pillar(MapColor.COLOR_YELLOW, MapColor.PLANT), regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_BAMBOO_BLOCK, colors.color_yellow, regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.BAMBOO_PLANKS, colors.color_yellow, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.BAMBOO_MOSAIC, colors.color_yellow, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_BAMBOO_PLANKS, Blocks.BAMBOO_MOSAIC, colors.color_yellow, regFdPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.CHERRY_LOG, new MapColorHolders.Pillar(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY), regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_CHERRY_LOG, new MapColorHolders.Pillar(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_PINK), regLogs, exLog));
        keys.add(new BlockBulkRegisterKey(Blocks.CHERRY_PLANKS, colors.terracotta_white, regMcPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_CHERRY_PLANKS, Blocks.CHERRY_PLANKS, colors.terracotta_white, regFdPlanks, exPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.CRIMSON_STEM, colors.crimson_stem, regLogs, exStem));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_CRIMSON_STEM, colors.crimson_stem, regLogs, exStem));
        keys.add(new BlockBulkRegisterKey(Blocks.CRIMSON_PLANKS, colors.crimson_stem, regMcPlanks, exNoFlamPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_CRIMSON_PLANKS, Blocks.CRIMSON_PLANKS, colors.crimson_stem, regFdPlanks, exNoFlamPlanks));
        keys.add(new BlockBulkRegisterKey(Blocks.WARPED_STEM, colors.warped_stem, regLogs, exStem));
        keys.add(new BlockBulkRegisterKey(Blocks.STRIPPED_WARPED_STEM, colors.warped_stem, regLogs, exStem));
        keys.add(new BlockBulkRegisterKey(Blocks.WARPED_PLANKS, colors.warped_stem, regMcPlanks, exNoFlamPlanks));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_WARPED_PLANKS, Blocks.WARPED_PLANKS, colors.warped_stem, regFdPlanks, exNoFlamPlanks));

        BlockBulkRegisterKey.RegisterPropertiesBuilder regDirt = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.pillar,
                regs.peepWindow
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exDirt = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_SHOVEL,
                BlockBulkRegisterDataProperties.TextureType.CUBE
        ));
        BlockBulkRegisterKey.RegisterPropertiesBuilder regGrass = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.peepWindow
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exGrass = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_SHOVEL,
                BlockBulkRegisterDataProperties.TextureType.GRASS
        ));
        BlockBulkRegisterKey.ExtendPropertiesBuilder exNylium = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.NYLIUM
        ));
        keys.add(new BlockBulkRegisterKey(Blocks.DIRT, colors.dirt, regDirt, exDirt));
        keys.add(new BlockBulkRegisterKey(Blocks.COARSE_DIRT, colors.dirt, regDirt, exDirt));
        keys.add(new BlockBulkRegisterKey(Blocks.ROOTED_DIRT, colors.dirt, regDirt, exDirt));
        keys.add(new BlockBulkRegisterKey(Blocks.PODZOL, colors.podzol, regGrass, exGrass));
        keys.add(new BlockBulkRegisterKey(Blocks.MYCELIUM, colors.color_purple, regGrass, exGrass));
        keys.add(new BlockBulkRegisterKey(Blocks.MUD, colors.terracotta_cyan, regDirt, exDirt));
        keys.add(new BlockBulkRegisterKey(Blocks.MUDDY_MANGROVE_ROOTS, colors.podzol, regDirt, new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_SHOVEL,
                BlockBulkRegisterDataProperties.TextureType.PILLAR
        ))));
        keys.add(new BlockBulkRegisterKey(Blocks.CLAY, colors.clay, regDirt, exDirt));
        keys.add(new BlockBulkRegisterKey(Blocks.SOUL_SOIL, colors.color_brown, regDirt, exDirt));
        keys.add(new BlockBulkRegisterKey(Blocks.SNOW_BLOCK, colors.snow, regDirt, new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_SHOVEL,
                BlockBulkRegisterDataProperties.TextureType.SNOW_BLOCK
        ))));
        keys.add(new BlockBulkRegisterKey(Blocks.CRIMSON_NYLIUM, colors.crimson_nylium, regGrass, exNylium));
        keys.add(new BlockBulkRegisterKey(Blocks.WARPED_NYLIUM, colors.warped_nylium, regGrass, exNylium));
        
        BlockBulkRegisterKey.ExtendPropertiesBuilder exMoss = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_HOE,
                BlockBulkRegisterDataProperties.TextureType.CUBE
        ));
        keys.add(new BlockBulkRegisterKey(Blocks.MOSS_BLOCK, colors.color_green, regDirt, exMoss));
        keys.add(new BlockBulkRegisterKey(Blocks.MUSHROOM_STEM, colors.dirt, regDirt, exMoss));
        keys.add(new BlockBulkRegisterKey(Blocks.BROWN_MUSHROOM_BLOCK, colors.dirt, regDirt, exMoss));
        keys.add(new BlockBulkRegisterKey(Blocks.RED_MUSHROOM_BLOCK, colors.color_red, regDirt, exMoss));
        keys.add(new BlockBulkRegisterKey(Blocks.NETHER_WART_BLOCK, colors.color_red, regDirt, exMoss));
        keys.add(new BlockBulkRegisterKey(Blocks.WARPED_WART_BLOCK, colors.warped_wart_block, regDirt, exMoss));
        keys.add(new BlockBulkRegisterKey(Blocks.SCULK, colors.color_black, regDirt, exMoss));
        keys.add(new BlockBulkRegisterKey(Blocks.SCULK_CATALYST, colors.color_black, regGrass, new BlockBulkRegisterKey.ExtendPropertiesBuilder().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_HOE,
                BlockBulkRegisterDataProperties.TextureType.UP_DOWN
        ))));

        BlockBulkRegisterKey.RegisterPropertiesBuilder regStones = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.pillar,
                regs.stoneGuardrail,
                regs.windowA,
                regs.peepWindow,
                regs.table,
                regs.chair,
                regs.gardenChair
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exStone = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.CUBE
        ));
        BlockBulkRegisterKey.ExtendPropertiesBuilder exFramedStone = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.CUBE_FRAMED
        ));
        BlockBulkRegisterKey.RegisterPropertiesBuilder regWeakStones = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.pillar,
                regs.windowA,
                regs.peepWindow,
                regs.table,
                regs.chair,
                regs.gardenChair
        );
        BlockBulkRegisterKey.RegisterPropertiesBuilder regPillarStones = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.pillar,
                regs.stoneGuardrail,
                regs.peepWindow,
                regs.table,
                regs.chair
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exPillar = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.PILLAR
        ));
        BlockBulkRegisterKey.ExtendPropertiesBuilder exPillar2 = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.LOG
        ));
        BlockBulkRegisterKey.RegisterPropertiesBuilder regArtStones = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock
        );
        keys.add(new BlockBulkRegisterKey(Blocks.PACKED_MUD, colors.dirt, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.MUD_BRICKS, colors.terracotta_light_gray, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.STONE, colors.stone, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.SMOOTH_STONE, colors.stone, regStones, exFramedStone));
        keys.add(new BlockBulkRegisterKey(Blocks.STONE_BRICKS, colors.stone, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CHISELED_STONE_BRICKS, colors.stone, regArtStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.MOSSY_STONE_BRICKS, colors.stone, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CRACKED_STONE_BRICKS, colors.stone, regWeakStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.COBBLESTONE, colors.stone, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.MOSSY_COBBLESTONE, colors.stone, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DEEPSLATE, colors.deepslate, regPillarStones, exPillar2));
        keys.add(new BlockBulkRegisterKey(Blocks.COBBLED_DEEPSLATE, colors.deepslate, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.POLISHED_DEEPSLATE, colors.deepslate, regStones, exFramedStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DEEPSLATE_BRICKS, colors.deepslate, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CRACKED_DEEPSLATE_BRICKS, colors.deepslate, regWeakStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DEEPSLATE_TILES, colors.deepslate, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CRACKED_DEEPSLATE_TILES, colors.deepslate, regWeakStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CHISELED_DEEPSLATE, colors.deepslate, regArtStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.GRANITE, colors.dirt, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.POLISHED_GRANITE, colors.dirt, regStones, exFramedStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DIORITE, colors.quartz, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.POLISHED_DIORITE, colors.quartz, regStones, exFramedStone));
        keys.add(new BlockBulkRegisterKey(Blocks.ANDESITE, colors.stone, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.POLISHED_ANDESITE, colors.stone, regStones, exFramedStone));
        keys.add(new BlockBulkRegisterKey(Blocks.TUFF, colors.terracotta_gray, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CALCITE, colors.terracotta_white, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DRIPSTONE_BLOCK, colors.terracotta_brown, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.AMETHYST_BLOCK, colors.color_purple, regStones, exStone));

        BlockBulkRegisterKey.ExtendPropertiesBuilder exObsidian = exStone.getCopy().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.NEEDS_DIAMOND_TOOL,
                BlockBulkRegisterDataProperties.DRAGON_IMMUNE
        ));
        keys.add(new BlockBulkRegisterKey(Blocks.OBSIDIAN, colors.color_black, regStones, exObsidian));
        keys.add(new BlockBulkRegisterKey(Blocks.CRYING_OBSIDIAN, colors.color_black, regStones, exObsidian));
        keys.add(new BlockBulkRegisterKey(Blocks.BRICKS, colors.color_red, regStones, exStone));

        BlockBulkRegisterKey.RegisterPropertiesBuilder regCutSandStone = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.stoneGuardrail,
                regs.peepWindow
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exSandStone = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.SANDSTONE
        ));
        BlockBulkRegisterKey.ExtendPropertiesBuilder exRedSandStone = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.RED_SANDSTONE
        ));
        keys.add(new BlockBulkRegisterKey(Blocks.SANDSTONE, colors.sand, regStones, exSandStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CUT_SANDSTONE, colors.sand, regCutSandStone, exSandStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CHISELED_SANDSTONE, colors.sand, regArtStones, exSandStone));
        keys.add(new BlockBulkRegisterKey(Blocks.SMOOTH_SANDSTONE, colors.sand, regStones, exSandStone));
        keys.add(new BlockBulkRegisterKey(Blocks.RED_SANDSTONE, colors.color_orange, regStones, exRedSandStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CUT_RED_SANDSTONE, colors.color_orange, regCutSandStone, exRedSandStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CHISELED_RED_SANDSTONE, colors.color_orange, regArtStones, exRedSandStone));
        keys.add(new BlockBulkRegisterKey(Blocks.SMOOTH_RED_SANDSTONE, colors.color_orange, regStones, exRedSandStone));
        keys.add(new BlockBulkRegisterKey(Blocks.COPPER_BLOCK, colors.color_orange, regStones, exFramedStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BONE_BLOCK, colors.sand, regPillarStones, exPillar));
        keys.add(new BlockBulkRegisterKey(Blocks.HONEYCOMB_BLOCK, colors.color_orange, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.PRISMARINE, colors.color_cyan, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.PRISMARINE_BRICKS, colors.diamond, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DARK_PRISMARINE, colors.diamond, regStones, exStone));

        BlockBulkRegisterKey.RegisterPropertiesBuilder regTransparent = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.horizonPanel,
                regs.glassGuardrail,
                regs.peepWindow,
                regs.table
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exTransparent = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.CUBE,
                BlockBulkRegisterDataProperties.TRANSPARENT
        ));
        keys.add(new BlockBulkRegisterKey(Blocks.ICE, colors.ice, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.PACKED_ICE, colors.ice, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BLUE_ICE, colors.ice, regStones, exStone));

        keys.add(new BlockBulkRegisterKey(Blocks.NETHERRACK, colors.nether, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.NETHER_BRICKS, colors.nether, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CHISELED_NETHER_BRICKS, colors.nether,regArtStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CRACKED_NETHER_BRICKS, colors.nether, regWeakStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.RED_NETHER_BRICKS, colors.nether, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BLACKSTONE, colors.color_black, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.POLISHED_BLACKSTONE, colors.color_black, regStones, exFramedStone));
        keys.add(new BlockBulkRegisterKey(Blocks.POLISHED_BLACKSTONE_BRICKS, colors.color_black, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, colors.color_black, regWeakStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BASALT, colors.color_black, regPillarStones, exPillar));
        keys.add(new BlockBulkRegisterKey(Blocks.POLISHED_BASALT, colors.color_black, regPillarStones, exPillar));
        keys.add(new BlockBulkRegisterKey(Blocks.SMOOTH_BASALT, colors.color_black, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.MAGMA_BLOCK, colors.nether, regStones, new BlockBulkRegisterKey.ExtendPropertiesBuilder().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.MAGMA_BLOCK
        ))));
        keys.add(new BlockBulkRegisterKey(Blocks.QUARTZ_BLOCK, colors.quartz, regStones, new BlockBulkRegisterKey.ExtendPropertiesBuilder().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.QUARTZ_BLOCK
        ))));
        keys.add(new BlockBulkRegisterKey(Blocks.CHISELED_QUARTZ_BLOCK, colors.quartz, regArtStones, exPillar2));
        keys.add(new BlockBulkRegisterKey(Blocks.QUARTZ_PILLAR, colors.quartz, regPillarStones, exPillar2));
        keys.add(new BlockBulkRegisterKey(Blocks.SMOOTH_QUARTZ, colors.quartz, regStones, new BlockBulkRegisterKey.ExtendPropertiesBuilder().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.SMOOTH_QUARTZ
        ))));
        keys.add(new BlockBulkRegisterKey(Blocks.QUARTZ_BRICKS, colors.quartz, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.ANCIENT_DEBRIS, colors.color_black, regPillarStones, exPillar));

        keys.add(new BlockBulkRegisterKey(Blocks.END_STONE, colors.sand, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.END_STONE_BRICKS, colors.sand, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.PURPUR_BLOCK, colors.color_magenta, regStones, exStone));
        keys.add(new BlockBulkRegisterKey(Blocks.PURPUR_PILLAR, colors.color_magenta, regPillarStones, exPillar2));

        BlockBulkRegisterKey.RegisterPropertiesBuilder regRainbowStones = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.stairSlab,
                regs.cutBlock,
                regs.pillar,
                regs.stoneGuardrail,
                regs.windowA,
                regs.peepWindow,
                regs.table,
                regs.chair,
                regs.gardenChair
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exColorStone = exStone.getCopy().applyProperty(regs.colored);
        keys.add(new BlockBulkRegisterKey(Blocks.TUBE_CORAL_BLOCK, colors.color_black, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DEAD_TUBE_CORAL_BLOCK, colors.color_gray, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BRAIN_CORAL_BLOCK, colors.color_pink, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DEAD_BRAIN_CORAL_BLOCK, colors.color_gray, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BUBBLE_CORAL_BLOCK, colors.color_purple, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DEAD_BUBBLE_CORAL_BLOCK, colors.color_gray, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.FIRE_CORAL_BLOCK, colors.color_red, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DEAD_FIRE_CORAL_BLOCK, colors.color_gray, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.HORN_CORAL_BLOCK, colors.color_yellow, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.DEAD_HORN_CORAL_BLOCK, colors.color_gray, regStones, exColorStone));

        keys.add(new BlockBulkRegisterKey(Blocks.TERRACOTTA, colors.color_orange, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.WHITE_TERRACOTTA, colors.terracotta_white, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.ORANGE_TERRACOTTA, colors.terracotta_orange, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.MAGENTA_TERRACOTTA, colors.terracotta_magenta, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.LIGHT_BLUE_TERRACOTTA, colors.terracotta_light_blue, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.YELLOW_TERRACOTTA, colors.terracotta_yellow, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.LIME_TERRACOTTA, colors.terracotta_light_green, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.PINK_TERRACOTTA, colors.terracotta_pink, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.GRAY_TERRACOTTA, colors.terracotta_gray, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.LIGHT_GRAY_TERRACOTTA, colors.terracotta_light_gray, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CYAN_TERRACOTTA, colors.terracotta_cyan, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.PURPLE_TERRACOTTA, colors.terracotta_purple, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BLUE_TERRACOTTA, colors.terracotta_blue, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BROWN_TERRACOTTA, colors.terracotta_brown, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.GREEN_TERRACOTTA, colors.terracotta_green, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.RED_TERRACOTTA, colors.terracotta_red, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BLACK_TERRACOTTA, colors.terracotta_black, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(FDBlocks.RAINBOW_TERRACOTTA, Blocks.WHITE_TERRACOTTA, colors.terracotta_white, regRainbowStones, exColorStone));

        keys.add(new BlockBulkRegisterKey(Blocks.WHITE_CONCRETE, colors.snow, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.ORANGE_CONCRETE, colors.color_orange, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.MAGENTA_CONCRETE, colors.color_magenta, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.LIGHT_BLUE_CONCRETE, colors.color_light_blue, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.YELLOW_CONCRETE, colors.color_yellow, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.LIME_CONCRETE, colors.color_light_green, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.PINK_CONCRETE, colors.color_pink, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.GRAY_CONCRETE, colors.color_gray, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.LIGHT_GRAY_CONCRETE, colors.color_light_gray, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.CYAN_CONCRETE, colors.color_cyan, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.PURPLE_CONCRETE, colors.color_purple, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BLUE_CONCRETE, colors.color_blue, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BROWN_CONCRETE, colors.color_brown, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.GREEN_CONCRETE, colors.color_green, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.RED_CONCRETE, colors.color_red, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(Blocks.BLACK_CONCRETE, colors.color_black, regStones, exColorStone));
        keys.add(new BlockBulkRegisterKey(FDBlocks.RAINBOW_CONCRETE, Blocks.WHITE_CONCRETE, colors.snow, regRainbowStones, exColorStone));

        BlockBulkRegisterKey.RegisterPropertiesBuilder regWools = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.woolSofa
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exWool = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
                regs.colored
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.TextureType.CUBE,
                BlockBulkRegisterDataProperties.WOOL
        ));
        keys.add(new BlockBulkRegisterKey(Blocks.WHITE_WOOL, colors.snow, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.ORANGE_WOOL, colors.color_orange, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.MAGENTA_WOOL, colors.color_magenta, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.LIGHT_BLUE_WOOL, colors.color_blue, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.YELLOW_WOOL, colors.color_yellow, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.LIME_WOOL, colors.color_light_green, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.PINK_WOOL, colors.color_pink, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.GRAY_WOOL, colors.color_gray, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.LIGHT_GRAY_WOOL, colors.color_light_gray, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.CYAN_WOOL, colors.color_cyan, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.PURPLE_WOOL, colors.color_purple, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.BLUE_WOOL, colors.color_blue, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.BROWN_WOOL, colors.color_brown, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.GREEN_WOOL, colors.color_green, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.RED_WOOL, colors.color_red, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(Blocks.BLACK_WOOL, colors.color_black, regWools, exWool));
        keys.add(new BlockBulkRegisterKey(FDBlocks.RAINBOW_WOOL, Blocks.WHITE_WOOL, colors.snow, new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.stairSlab,
                regs.cutBlock,
                regs.woolSofa
        ), exWool));
        
        BlockBulkRegisterKey.ExtendPropertiesBuilder exGlass = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.GLASS,
                BlockBulkRegisterDataProperties.TRANSPARENT
        ));
        keys.add(new BlockBulkRegisterKey(Blocks.GLASS, colors.none, regTransparent, exGlass));
        keys.add(new BlockBulkRegisterKey(Blocks.WHITE_STAINED_GLASS, colors.snow, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.ORANGE_STAINED_GLASS, colors.color_orange, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.MAGENTA_STAINED_GLASS, colors.color_magenta, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.LIGHT_BLUE_STAINED_GLASS, colors.color_light_blue, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.YELLOW_STAINED_GLASS, colors.color_yellow, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.LIME_STAINED_GLASS, colors.color_light_green, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.PINK_STAINED_GLASS, colors.color_pink, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.GRAY_STAINED_GLASS, colors.color_gray, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.LIGHT_GRAY_STAINED_GLASS, colors.color_light_gray, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.CYAN_STAINED_GLASS, colors.color_cyan, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.PURPLE_STAINED_GLASS, colors.color_purple, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.BLUE_STAINED_GLASS, colors.color_blue, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.BROWN_STAINED_GLASS, colors.color_brown, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.GREEN_STAINED_GLASS, colors.color_green, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.RED_STAINED_GLASS, colors.color_red, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(Blocks.BLACK_STAINED_GLASS, colors.color_black, regTransparent, exTransparent));
        keys.add(new BlockBulkRegisterKey(FDBlocks.RAINBOW_GLASS, Blocks.WHITE_STAINED_GLASS, colors.snow, regTransparent, exTransparent));

        BlockBulkRegisterKey.RegisterPropertiesBuilder regLamp = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.cutBlock,
                regs.lampInGlass
        );
        BlockBulkRegisterKey.ExtendPropertiesBuilder exLamp = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.CUBE
        ));
        BlockBulkRegisterKey.ExtendPropertiesBuilder exFramedLamp = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.CUBE_FRAMED
        ));
        BlockBulkRegisterKey.ExtendPropertiesBuilder exColorLamp = exLamp.getCopy().applyProperty(regs.colored);
        BlockBulkRegisterKey.ExtendPropertiesBuilder exColorFramedLamp = exLamp.getCopy().applyProperty(regs.colored);
        Supplier<BlockBehaviour.Properties> seaLantern = () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN);
        Supplier<BlockBehaviour.Properties> reinforcedSeaLantern = () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).strength(1.5F, 6.0F);
        keys.add(new BlockBulkRegisterKey(Blocks.GLOWSTONE, colors.sand, regLamp, exLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.SMOOTH_GLOWSTONE, Blocks.GLOWSTONE, colors.sand, regLamp, exFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_SMOOTH_GLOWSTONE, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).strength(1.5F, 6.0F), colors.sand, regLamp, exFramedLamp));
        keys.add(new BlockBulkRegisterKey(Blocks.SEA_LANTERN, colors.quartz, regLamp, exFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_SEA_LANTERN, reinforcedSeaLantern, colors.quartz, regLamp, exFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.WHITE_SEA_LANTERN, seaLantern, colors.snow, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_WHITE_SEA_LANTERN, reinforcedSeaLantern, colors.snow, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.ORANGE_SEA_LANTERN, seaLantern, colors.color_orange, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_ORANGE_SEA_LANTERN, reinforcedSeaLantern, colors.color_orange, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.MAGENTA_SEA_LANTERN, seaLantern, colors.color_magenta, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_MAGENTA_SEA_LANTERN, reinforcedSeaLantern, colors.color_magenta, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.LIGHT_BLUE_SEA_LANTERN, seaLantern, colors.color_light_blue, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_LIGHT_BLUE_SEA_LANTERN, reinforcedSeaLantern, colors.color_light_blue, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.YELLOW_SEA_LANTERN, seaLantern, colors.color_yellow, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_YELLOW_SEA_LANTERN, reinforcedSeaLantern, colors.color_yellow, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.LIME_SEA_LANTERN, seaLantern, colors.color_light_green, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_LIME_SEA_LANTERN, reinforcedSeaLantern, colors.color_light_green, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.PINK_SEA_LANTERN, seaLantern, colors.color_pink, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_PINK_SEA_LANTERN, reinforcedSeaLantern, colors.color_pink, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.GRAY_SEA_LANTERN, seaLantern, colors.color_gray, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_GRAY_SEA_LANTERN, reinforcedSeaLantern, colors.color_gray, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.LIGHT_GRAY_SEA_LANTERN, seaLantern, colors.color_light_gray, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_LIGHT_GRAY_SEA_LANTERN, reinforcedSeaLantern, colors.color_light_gray, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.CYAN_SEA_LANTERN, seaLantern, colors.color_cyan, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_CYAN_SEA_LANTERN, reinforcedSeaLantern, colors.color_cyan, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.PURPLE_SEA_LANTERN, seaLantern, colors.color_purple, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_PURPLE_SEA_LANTERN, reinforcedSeaLantern, colors.color_purple, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.BLUE_SEA_LANTERN, seaLantern, colors.color_blue, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_BLUE_SEA_LANTERN, reinforcedSeaLantern, colors.color_blue, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.BROWN_SEA_LANTERN, seaLantern, colors.color_brown, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_BROWN_SEA_LANTERN, reinforcedSeaLantern, colors.color_brown, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.GREEN_SEA_LANTERN, seaLantern, colors.color_green, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_GREEN_SEA_LANTERN, reinforcedSeaLantern, colors.color_green, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.RED_SEA_LANTERN, seaLantern, colors.color_red, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_RED_SEA_LANTERN, reinforcedSeaLantern, colors.color_red, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.BLACK_SEA_LANTERN, seaLantern, colors.color_black, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.REINFORCED_BLACK_SEA_LANTERN, reinforcedSeaLantern, colors.color_black, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.RAINBOW_SEA_LANTERN, seaLantern, colors.snow, regLamp, exColorFramedLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.RAINBOW_REINFORCED_SEA_LANTERN, reinforcedSeaLantern, colors.snow, regLamp, exColorFramedLamp));

        BlockBulkRegisterKey.RegisterPropertiesBuilder regOnlyLamp = new BlockBulkRegisterKey.RegisterPropertiesBuilder(
                regs.lampInGlass
        );
        keys.add(new BlockBulkRegisterKey(Blocks.JACK_O_LANTERN, colors.color_orange, regOnlyLamp, new BlockBulkRegisterKey.ExtendPropertiesBuilder().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_AXE,
                BlockBulkRegisterDataProperties.TextureType.PUMPKIN_LAMP
        ))));
        keys.add(new BlockBulkRegisterKey(Blocks.REDSTONE_LAMP, () -> BlockBehaviour.Properties.of().lightLevel(value -> 15).strength(0.3F).sound(SoundType.GLASS), colors.color_orange, regOnlyLamp, new BlockBulkRegisterKey.ExtendPropertiesBuilder().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_PICKAXE,
                BlockBulkRegisterDataProperties.TextureType.REDSTONE_LAMP
        ))));
        keys.add(new BlockBulkRegisterKey(Blocks.SHROOMLIGHT, colors.color_orange, regLamp, new BlockBulkRegisterKey.ExtendPropertiesBuilder().ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.MINEABLE_WITH_HOE,
                BlockBulkRegisterDataProperties.TextureType.CUBE
        ))));

        BlockBulkRegisterKey.ExtendPropertiesBuilder exFrogLight = new BlockBulkRegisterKey.ExtendPropertiesBuilder(
        ).ifDataGen(extendPropertiesBuilder -> extendPropertiesBuilder.applyProperty(
                BlockBulkRegisterDataProperties.TextureType.PILLAR
        ));
        keys.add(new BlockBulkRegisterKey(Blocks.OCHRE_FROGLIGHT, colors.sand, regLamp, exFrogLight));
        keys.add(new BlockBulkRegisterKey(Blocks.VERDANT_FROGLIGHT, colors.glow_lichen, regLamp, exFrogLight));
        keys.add(new BlockBulkRegisterKey(Blocks.PEARLESCENT_FROGLIGHT, colors.color_pink, regLamp, exFrogLight));
        keys.add(new BlockBulkRegisterKey(FDBlocks.RED_LAMP, () -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_RED).lightLevel(value -> 5).sound(SoundType.AMETHYST), colors.color_red, regLamp, exColorLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.GREEN_LAMP, () -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_GREEN).lightLevel(value -> 5).sound(SoundType.AMETHYST), colors.color_green, regLamp, exColorLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.BLUE_LAMP, () -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 5).sound(SoundType.AMETHYST), colors.color_blue, regLamp, exColorLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.YELLOW_LAMP, () -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_BLUE).lightLevel(value -> 10).sound(SoundType.AMETHYST), colors.color_yellow, regLamp, exColorLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.CYAN_LAMP, () -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_CYAN).lightLevel(value -> 10).sound(SoundType.AMETHYST), colors.color_cyan, regLamp, exColorLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.PURPLE_LAMP, () -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_PURPLE).lightLevel(value -> 10).sound(SoundType.AMETHYST), colors.color_purple, regLamp, exColorLamp));
        keys.add(new BlockBulkRegisterKey(FDBlocks.RAINBOW_LAMP, () -> BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.SNOW).lightLevel(value -> 10).sound(SoundType.AMETHYST), colors.snow, regLamp, exColorLamp));
    }
}
