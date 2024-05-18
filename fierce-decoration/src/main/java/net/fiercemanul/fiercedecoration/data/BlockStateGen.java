package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.world.level.block.*;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.ChairType;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.fiercemanul.fiercesource.data.FSBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;


public class BlockStateGen extends FSBlockStateProvider {


    public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FierceDecoration.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simple(FDBlocks.SMOOTH_GLOWSTONE);
        simple(FDBlocks.REINFORCED_SMOOTH_GLOWSTONE);
        simple(FDBlocks.REINFORCED_SEA_LANTERN);
        simple(FDBlocks.WATERLOGGED_COBBLESTONE);
        simple(FDBlocks.SMOOTH_OAK_PLANKS);
        simple(FDBlocks.SMOOTH_SPRUCE_PLANKS);
        simple(FDBlocks.SMOOTH_BIRCH_PLANKS);
        simple(FDBlocks.SMOOTH_JUNGLE_PLANKS);
        simple(FDBlocks.SMOOTH_ACACIA_PLANKS);
        simple(FDBlocks.SMOOTH_DARK_OAK_PLANKS);
        simple(FDBlocks.SMOOTH_MANGROVE_PLANKS);
        simple(FDBlocks.SMOOTH_CHERRY_PLANKS);
        simple(FDBlocks.SMOOTH_CRIMSON_PLANKS);
        simple(FDBlocks.SMOOTH_WARPED_PLANKS);
        simpleNature(FDBlocks.ROTTEN_FLESH_BLOCK);
        simple(FDBlocks.RAINBOW_WOOL);
        simple(FDBlocks.RAINBOW_TERRACOTTA);
        simple(FDBlocks.RAINBOW_CONCRETE);
        simple(FDBlocks.RAINBOW_SEA_LANTERN);
        simple(FDBlocks.RAINBOW_REINFORCED_SEA_LANTERN);
        simpleWithModel(FDBlocks.RAINBOW_GLASS);
        simple(FDBlocks.WHITE_SEA_LANTERN);
        simple(FDBlocks.ORANGE_SEA_LANTERN);
        simple(FDBlocks.MAGENTA_SEA_LANTERN);
        simple(FDBlocks.LIGHT_BLUE_SEA_LANTERN);
        simple(FDBlocks.YELLOW_SEA_LANTERN);
        simple(FDBlocks.LIME_SEA_LANTERN);
        simple(FDBlocks.PINK_SEA_LANTERN);
        simple(FDBlocks.GRAY_SEA_LANTERN);
        simple(FDBlocks.LIGHT_GRAY_SEA_LANTERN);
        simple(FDBlocks.CYAN_SEA_LANTERN);
        simple(FDBlocks.PURPLE_SEA_LANTERN);
        simple(FDBlocks.BLUE_SEA_LANTERN);
        simple(FDBlocks.BROWN_SEA_LANTERN);
        simple(FDBlocks.GREEN_SEA_LANTERN);
        simple(FDBlocks.RED_SEA_LANTERN);
        simple(FDBlocks.BLACK_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_WHITE_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_ORANGE_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_MAGENTA_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_LIGHT_BLUE_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_YELLOW_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_LIME_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_PINK_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_GRAY_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_LIGHT_GRAY_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_CYAN_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_PURPLE_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_BLUE_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_BROWN_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_GREEN_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_RED_SEA_LANTERN);
        simple(FDBlocks.REINFORCED_BLACK_SEA_LANTERN);
        simple(FDBlocks.RAINBOW_LAMP);
        simple(FDBlocks.RED_LAMP);
        simple(FDBlocks.GREEN_LAMP);
        simple(FDBlocks.BLUE_LAMP);
        simple(FDBlocks.YELLOW_LAMP);
        simple(FDBlocks.CYAN_LAMP);
        simple(FDBlocks.PURPLE_LAMP);
        simpleWithModel(FDBlocks.FOX_CARROT_SHEAF);
        simpleWithModel(FDBlocks.FOX_CARROT_BASKET);
        directionModel(FDBlocks.CRAFTING_PAD, false);
        simpleWithModel(FDBlocks.CRAFTING_DESK);
        simpleWithModel(FDBlocks.CRAFTING_BLOCK);
        simpleWithModel(FDBlocks.ROCK_PATH);

        horizontalDirectionModel(FDBlocks.PORTABLE_WORKSTATION, false);
        horizontalDirectionModel(FDBlocks.LAPTOP_TERMINAL, false);
        horizontalDirectionModel(FDBlocks.WALL_FLOWER_POT_A, false);
        horizontalDirectionModel(FDBlocks.WALL_FLOWER_POT_B, false);
        horizontalDirectionModel(FDBlocks.WALL_FLOWER_POT_C, false);
        horizontalDirectionModel(FDBlocks.WALL_FLOWER_POT_D, false);
        horizontalDirectionModel(FDBlocks.WALL_FLOWER_POT_E, false);
        horizontalDirectionModel(FDBlocks.WALL_FLOWER_POT_F, false);
        horizontalDirectionModel(FDBlocks.NEO_FORGE, false);

        itemFrameShell();
        lightTube();
        lightPlate();
        heavyChains();

        doubleBlock(FDBlocks.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE, mcLoc("block/oak_planks"), mcLoc("block/light_gray_concrete"));
        doubleBlock(FDBlocks.SPRUCE_PLANKS_AND_GRAY_CONCRETE, mcLoc("block/spruce_planks"), mcLoc("block/gray_concrete"));
        doubleBlock(FDBlocks.OAK_PLANKS_AND_SPRUCE_PLANKS, mcLoc("block/oak_planks"), mcLoc("block/spruce_planks"));
        doubleBlock(FDBlocks.WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE, mcLoc("block/white_concrete"), mcLoc("block/light_gray_concrete"));
        doubleBlock(FDBlocks.DEEPSLATE_TILES_AND_SPRUCE_PLANKS, mcLoc("block/deepslate_tiles"), mcLoc("block/spruce_planks"));
        doubleBlock(FDBlocks.DEEPSLATE_TILES_AND_MANGROVE_PLANKS, mcLoc("block/deepslate_tiles"), mcLoc("block/mangrove_planks"));
        doubleBlock(FDBlocks.DARK_PRISMARINE_AND_SPRUCE_PLANKS, mcLoc("block/dark_prismarine"), mcLoc("block/spruce_planks"));
        doubleBlock(FDBlocks.DARK_PRISMARINE_AND_MANGROVE_PLANKS, mcLoc("block/dark_prismarine"), mcLoc("block/mangrove_planks"));
        doubleBlock(FDBlocks.BRICKS_AND_BIRCH_PLANKS, mcLoc("block/bricks"), mcLoc("block/birch_planks"));

        simpleWithMcModel(FDBlocks.FAKE_DIAMOND_BLOCK, "diamond_block");
        simpleWithMcModel(FDBlocks.FAKE_GOLD_BLOCK, "gold_block");
        simpleWithMcModel(FDBlocks.FAKE_IRON_BLOCK, "iron_block");
        simpleWithMcModel(FDBlocks.FAKE_NETHERITE_BLOCK, "netherite_block");
        simpleWithMcModel(FDBlocks.FAKE_BEDROCK, "bedrock");

        ResourceLocation sandstone_top = mcLoc("block/sandstone_top");
        ResourceLocation sandstone_side = mcLoc("block/sandstone");
        ResourceLocation sandstone_bottom = mcLoc("block/sandstone_bottom");
        ResourceLocation red_sandstone_top = mcLoc("block/red_sandstone_top");
        ResourceLocation red_sandstone_side = mcLoc("block/red_sandstone");
        ResourceLocation red_sandstone_bottom = mcLoc("block/red_sandstone_bottom");
        ResourceLocation quartz_block_side = mcLoc("block/quartz_block_side");
        ResourceLocation quartz_block_bottom = mcLoc("block/quartz_block_bottom");
        ResourceLocation snow = mcLoc("block/snow");
        ResourceLocation magma = mcLoc("block/magma");

        BlockModelBuilder dirtPeepWindow = models()
                .withExistingParent("dirt_peep_window", modLoc("block/peep_window"))
                .texture("all", mcLoc("block/dirt"));

        BlockModelBuilder netherrackPeepWindow = models()
                .withExistingParent("netherrack_peep_window", modLoc("block/peep_window"))
                .texture("all", mcLoc("block/netherrack"));

        BlockModelBuilder sandstonePeepWindowTop = models()
                .withExistingParent("sandstone_peep_window_top", modLoc("block/peep_window"))
                .texture("all", sandstone_top);

        BlockModelBuilder sandstonePeepWindowBottom = models()
                .withExistingParent("sandstone_peep_window_bottom", modLoc("block/peep_window"))
                .texture("all", sandstone_bottom);

        BlockModelBuilder redSandstonePeepWindowTop = models()
                .withExistingParent("red_sandstone_peep_window_top", modLoc("block/peep_window"))
                .texture("all", red_sandstone_top);

        BlockModelBuilder redSandstonePeepWindowBottom = models()
                .withExistingParent("red_sandstone_peep_window_bottom", modLoc("block/peep_window"))
                .texture("all", red_sandstone_bottom);


        DataGen.BLOCKS_AND_MATERIALS.forEach((deferredBlock, blockMaterial) -> {
            Block block = deferredBlock.get();
            Block materialBlock = blockMaterial.getMaterialBlock();
            BlockMaterial.ModelType modelType = blockMaterial.getModelType();

            if (block instanceof WoodenGuardrailBlock) guardrail(deferredBlock, texture(blockMaterial), "guardrail_wooden", false);
            else if (block instanceof WoodenGuardrailTypeBBlock) guardrail(deferredBlock, texture(blockMaterial), "guardrail_wooden_b", true);
            else if (block instanceof StoneGuardrailBlock) {
                switch (modelType) {
                    case CUBE_ALL -> guardrail(deferredBlock, texture(blockMaterial), "guardrail_rock", true);
                    case CUBE_ALL_FRAMED -> guardrail(deferredBlock, texture(blockMaterial), "guardrail_frame", true);
                    case PILLAR -> pillarGuardrail(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case LOG -> pillarGuardrail(deferredBlock, texture(blockMaterial), texture(blockMaterial, "top"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE)) guardrail(
                                deferredBlock,
                                sandstone_top,
                                "guardrail_rock",
                                false
                        );
                        else customGuardrail(
                                deferredBlock,
                                texture(blockMaterial),
                                sandstone_top,
                                sandstone_top,
                                false
                        );
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE)) guardrail(
                                deferredBlock,
                                red_sandstone_top,
                                "guardrail_rock",
                                false
                        );
                        else customGuardrail(
                                deferredBlock,
                                texture(blockMaterial),
                                red_sandstone_top,
                                red_sandstone_top,
                                false
                        );
                    }
                    case GRASS -> customGuardrail(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            new ResourceLocation("block/dirt"),
                            false
                    );
                    case NYLIUM -> customGuardrail(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial),
                            new ResourceLocation("block/netherrack"),
                            false
                    );
                    case CUSTOM -> {
                        if (materialBlock.equals(Blocks.QUARTZ_BLOCK)) guardrail(deferredBlock, quartz_block_side, "guardrail_frame", true);
                        if (materialBlock.equals(Blocks.SMOOTH_QUARTZ)) guardrail(deferredBlock, quartz_block_bottom, "guardrail_rock", true);
                        if (materialBlock.equals(Blocks.SNOW_BLOCK)) guardrail(deferredBlock, snow, "guardrail_rock", true);
                        if (materialBlock.equals(Blocks.MAGMA_BLOCK)) guardrail(deferredBlock, magma, "guardrail_rock", true);
                    }
                }
            }
            else if (block instanceof GlassGuardrailBlock) {
                ResourceLocation glass = texture(blockMaterial);
                glassGuardrail(deferredBlock, glass, glass);
            }
            else if (block instanceof PeepWindowBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> {
                        ResourceLocation material;
                        if (materialBlock.equals(Blocks.GLASS)) material = new ResourceLocation(FierceDecoration.MODID, "block/glass_peep_window");
                        else material = texture(blockMaterial);
                        peepWindow(
                                deferredBlock,
                                material,
                                blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.GLASS)
                        );
                    }
                    case LOG, PILLAR -> peepWindow(
                            deferredBlock,
                            texture(blockMaterial, "top"),
                            false
                    );
                    case UP_DOWN -> peepWindowUpDown(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            texture(blockMaterial, "bottom")
                    );
                    case SANDSTONE -> {
                        String path = deferredBlock.getId().getPath();
                        if (materialBlock.equals(Blocks.SANDSTONE)) {
                            BlockModelBuilder modelSide = models()
                                    .withExistingParent(path, modLoc("block/peep_window"))
                                    .texture("all", mcLoc("block/sandstone"));
                            peepWindowStates(block, modelSide, sandstonePeepWindowTop, sandstonePeepWindowBottom);
                            itemModels().getBuilder(path).parent(modelSide);
                        }
                        else if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE)) {
                            peepWindowStates(block, sandstonePeepWindowTop, sandstonePeepWindowTop, sandstonePeepWindowTop);
                            itemModels().getBuilder(path).parent(sandstonePeepWindowTop);
                        }
                        else {
                            BlockModelBuilder modelSide = models()
                                    .withExistingParent(path, modLoc("block/peep_window"))
                                    .texture("all", texture(blockMaterial));
                            peepWindowStates(block, modelSide, sandstonePeepWindowTop, sandstonePeepWindowTop);
                            itemModels().getBuilder(path).parent(modelSide);
                        }
                    }
                    case RED_SANDSTONE -> {
                        String path = deferredBlock.getId().getPath();
                        if (materialBlock.equals(Blocks.RED_SANDSTONE)) {
                            BlockModelBuilder modelSide = models()
                                    .withExistingParent(path, modLoc("block/peep_window"))
                                    .texture("all", mcLoc("block/red_sandstone"));
                            peepWindowStates(block, modelSide, redSandstonePeepWindowTop, redSandstonePeepWindowBottom);
                            itemModels().getBuilder(path).parent(modelSide);
                        }
                        else if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE)) {
                            peepWindowStates(block, redSandstonePeepWindowTop, redSandstonePeepWindowTop, redSandstonePeepWindowTop);
                            itemModels().getBuilder(path).parent(redSandstonePeepWindowTop);
                        }
                        else {
                            BlockModelBuilder modelSide = models()
                                    .withExistingParent(path, modLoc("block/peep_window"))
                                    .texture("all", texture(blockMaterial));
                            peepWindowStates(block, modelSide, redSandstonePeepWindowTop, redSandstonePeepWindowTop);
                            itemModels().getBuilder(path).parent(modelSide);
                        }
                    }
                    case GRASS -> {
                        String path = deferredBlock.getId().getPath();
                        BlockModelBuilder modelSide = models()
                                .withExistingParent(path, modLoc("block/peep_window"))
                                .texture("all", texture(blockMaterial, "side"));
                        BlockModelBuilder modelTop = models()
                                .withExistingParent(path + "_top", modLoc("block/peep_window"))
                                .texture("all", texture(blockMaterial, "top"));
                        peepWindowStates(block, modelSide, modelTop, dirtPeepWindow);
                        itemModels().getBuilder(path).parent(modelSide);
                    }
                    case NYLIUM -> {
                        String path = deferredBlock.getId().getPath();
                        BlockModelBuilder modelSide = models()
                                .withExistingParent(path, modLoc("block/peep_window"))
                                .texture("all", texture(blockMaterial, "side"));
                        BlockModelBuilder modelTop = models()
                                .withExistingParent(path + "_top", modLoc("block/peep_window"))
                                .texture("all", texture(blockMaterial));
                        peepWindowStates(block, modelSide, modelTop, netherrackPeepWindow);
                        itemModels().getBuilder(path).parent(modelSide);
                    }
                    case CUSTOM -> {
                        if (materialBlock.equals(Blocks.QUARTZ_BLOCK)) peepWindow(deferredBlock, quartz_block_side, false);
                        if (materialBlock.equals(Blocks.SMOOTH_QUARTZ)) peepWindow(deferredBlock, quartz_block_bottom, false);
                        if (materialBlock.equals(Blocks.SNOW_BLOCK)) peepWindow(deferredBlock, snow, false);
                        if (materialBlock.equals(Blocks.MAGMA_BLOCK)) peepWindow(deferredBlock, magma, false);
                    }
                }
            }
            else if (block instanceof LampInGlassBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> lampInGlass(deferredBlock, texture(blockMaterial));
                    case LOG -> {
                        ResourceLocation top = texture(blockMaterial, "top");
                        lampInGlassCustom(deferredBlock, texture(blockMaterial), top, top);
                    }
                    case PILLAR -> {
                        ResourceLocation top = texture(blockMaterial, "top");
                        lampInGlassCustom(deferredBlock, texture(blockMaterial, "side"), top, top);
                    }
                    case FACING -> {
                        ResourceLocation top = texture(blockMaterial, "top");
                        lampInGlassCustom(deferredBlock, texture(blockMaterial, "front"), top, top);
                    }
                    case REDSTONE_LAMP -> lampInGlass(deferredBlock, texture(blockMaterial, "on"));
                    case PUMPKIN_LAMP -> {
                        ResourceLocation top = new ResourceLocation("block/pumpkin_top");
                        lampInGlassCustom(deferredBlock, texture(blockMaterial), top, top);
                    }
                }
            }
            else if (block instanceof OneCutBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> oneCutBlock(deferredBlock, texture(blockMaterial));
                    case LOG -> oneCutBlockPillar(deferredBlock, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> oneCutBlockPillar(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case UP_DOWN -> oneCutBlockUpDown(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            texture(blockMaterial, "bottom")
                    );
                    case SANDSTONE -> {
                        if (blockMaterial.getMaterialBlock().equals(Blocks.SMOOTH_SANDSTONE)) oneCutBlock(deferredBlock, sandstone_top);
                        else oneCutBlockUpDown(
                                deferredBlock,
                                texture(blockMaterial),
                                sandstone_top,
                                sandstone_bottom
                        );
                    }
                    case RED_SANDSTONE -> {
                        if (blockMaterial.getMaterialBlock().equals(Blocks.SMOOTH_RED_SANDSTONE)) oneCutBlock(
                                deferredBlock, red_sandstone_top
                        );
                        else oneCutBlockUpDown(
                                deferredBlock,
                                texture(blockMaterial),
                                red_sandstone_top,
                                red_sandstone_bottom
                        );
                    }
                    case GRASS -> oneCutBlockUpDown(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            new ResourceLocation("block/dirt")
                    );
                    case NYLIUM -> oneCutBlockUpDown(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial),
                            new ResourceLocation("block/netherrack")
                    );
                    case CUSTOM -> {
                        if (materialBlock.equals(Blocks.QUARTZ_BLOCK)) oneCutBlock(deferredBlock, quartz_block_side);
                        if (materialBlock.equals(Blocks.SMOOTH_QUARTZ)) oneCutBlock(deferredBlock, quartz_block_bottom);
                        if (materialBlock.equals(Blocks.SNOW_BLOCK)) oneCutBlock(deferredBlock, snow);
                        if (materialBlock.equals(Blocks.MAGMA_BLOCK)) oneCutBlock(deferredBlock, magma);
                    }
                }
            }
            else if (block instanceof ThinStairBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> thinStair(deferredBlock, texture(blockMaterial));
                    case UP_DOWN -> thinStairUpDown(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            texture(blockMaterial, "bottom")
                    );
                    case LOG -> {
                        ResourceLocation top = texture(blockMaterial, "top");
                        thinStairUpDown(deferredBlock, texture(blockMaterial), top, top);
                    }
                    case PILLAR -> {
                        ResourceLocation top = texture(blockMaterial, "top");
                        thinStairUpDown(deferredBlock, texture(blockMaterial, "side"), top, top);
                    }
                    case SANDSTONE -> {
                        if (blockMaterial.getMaterialBlock().equals(Blocks.SMOOTH_SANDSTONE)) thinStair(
                                deferredBlock, sandstone_top
                        );
                        else thinStairUpDown(
                                deferredBlock,
                                texture(blockMaterial),
                                sandstone_top,
                                sandstone_bottom
                        );
                    }
                    case RED_SANDSTONE -> {
                        if (blockMaterial.getMaterialBlock().equals(Blocks.SMOOTH_RED_SANDSTONE)) thinStair(
                                deferredBlock, red_sandstone_top
                        );
                        else thinStairUpDown(
                                deferredBlock,
                                texture(blockMaterial),
                                red_sandstone_top,
                                red_sandstone_bottom
                        );
                    }
                    case GRASS -> thinStairUpDown(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            new ResourceLocation("block/dirt")
                    );
                    case NYLIUM -> thinStairUpDown(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial),
                            new ResourceLocation("block/netherrack")
                    );
                    case CUSTOM -> {
                        if (materialBlock.equals(Blocks.QUARTZ_BLOCK)) thinStair(deferredBlock, quartz_block_side);
                        if (materialBlock.equals(Blocks.SMOOTH_QUARTZ)) thinStair(deferredBlock, quartz_block_bottom);
                        if (materialBlock.equals(Blocks.SNOW_BLOCK)) thinStair(deferredBlock, snow);
                        if (materialBlock.equals(Blocks.MAGMA_BLOCK)) thinStair(deferredBlock, magma);
                    }
                }
            }
            else if (block instanceof DoubleCutBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> doubleCutBlock(deferredBlock, texture(blockMaterial));
                    case UP_DOWN -> doubleCutBlockCustom(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            texture(blockMaterial, "bottom")
                    );
                    case LOG -> {
                        ResourceLocation top = texture(blockMaterial, "top");
                        doubleCutBlockCustom(deferredBlock, texture(blockMaterial), top, top);
                    }
                    case PILLAR -> {
                        ResourceLocation top = texture(blockMaterial, "top");
                        doubleCutBlockCustom(deferredBlock, texture(blockMaterial, "side"), top, top);
                    }
                    case SANDSTONE -> {
                        if (blockMaterial.getMaterialBlock().equals(Blocks.SMOOTH_SANDSTONE)) doubleCutBlock(
                                deferredBlock, sandstone_top
                        );
                        else doubleCutBlockCustom(
                                deferredBlock,
                                texture(blockMaterial),
                                sandstone_top,
                                sandstone_bottom
                        );
                    }
                    case RED_SANDSTONE -> {
                        if (blockMaterial.getMaterialBlock().equals(Blocks.SMOOTH_RED_SANDSTONE)) doubleCutBlock(
                                deferredBlock, red_sandstone_top
                        );
                        else doubleCutBlockCustom(
                                deferredBlock,
                                texture(blockMaterial),
                                red_sandstone_top,
                                red_sandstone_bottom
                        );
                    }
                    case GRASS -> doubleCutBlockCustom(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            new ResourceLocation("block/dirt")
                    );
                    case NYLIUM -> doubleCutBlockCustom(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial),
                            new ResourceLocation("block/netherrack")
                    );
                    case CUSTOM -> {
                        if (materialBlock.equals(Blocks.QUARTZ_BLOCK)) doubleCutBlock(deferredBlock, quartz_block_side);
                        if (materialBlock.equals(Blocks.SMOOTH_QUARTZ)) doubleCutBlock(deferredBlock, quartz_block_bottom);
                        if (materialBlock.equals(Blocks.SNOW_BLOCK)) doubleCutBlock(deferredBlock, snow);
                        if (materialBlock.equals(Blocks.MAGMA_BLOCK)) doubleCutBlock(deferredBlock, magma);
                    }
                }
            }
            else if (block instanceof TripleCutBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> tripleCut(deferredBlock, texture(blockMaterial));
                    case UP_DOWN -> tripleCutCustom(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            texture(blockMaterial, "bottom")
                    );
                    case LOG -> {
                        ResourceLocation top = texture(blockMaterial, "top");
                        tripleCutCustom(deferredBlock, texture(blockMaterial), top, top);
                    }
                    case PILLAR -> {
                        ResourceLocation top = texture(blockMaterial, "top");
                        tripleCutCustom(deferredBlock, texture(blockMaterial, "side"), top, top);
                    }
                    case SANDSTONE -> {
                        if (blockMaterial.getMaterialBlock().equals(Blocks.SMOOTH_SANDSTONE)) tripleCut(deferredBlock, sandstone_top);
                        else tripleCutCustom(
                                deferredBlock,
                                texture(blockMaterial),
                                sandstone_top,
                                sandstone_bottom
                        );
                    }
                    case RED_SANDSTONE -> {
                        if (blockMaterial.getMaterialBlock().equals(Blocks.SMOOTH_RED_SANDSTONE)) tripleCut(deferredBlock, red_sandstone_top);
                        else tripleCutCustom(
                                deferredBlock,
                                texture(blockMaterial),
                                red_sandstone_top,
                                red_sandstone_bottom
                        );
                    }
                    case GRASS -> tripleCutCustom(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial, "top"),
                            new ResourceLocation("block/dirt")
                    );
                    case NYLIUM -> tripleCutCustom(
                            deferredBlock,
                            texture(blockMaterial, "side"),
                            texture(blockMaterial),
                            new ResourceLocation("block/netherrack")
                    );
                    case CUSTOM -> {
                        if (materialBlock.equals(Blocks.QUARTZ_BLOCK)) tripleCut(deferredBlock, quartz_block_side);
                        if (materialBlock.equals(Blocks.SMOOTH_QUARTZ)) tripleCut(deferredBlock, quartz_block_bottom);
                        if (materialBlock.equals(Blocks.SNOW_BLOCK)) tripleCut(deferredBlock, snow);
                        if (materialBlock.equals(Blocks.MAGMA_BLOCK)) tripleCut(deferredBlock, magma);
                    }
                }
            }
            else if (block instanceof Pillar12PXBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> pillar12px(deferredBlock, texture(blockMaterial), texture(blockMaterial));
                    case LOG -> pillar12px(deferredBlock, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> pillar12px(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            pillar12px(deferredBlock, sandstone_top, sandstone_top);
                        else pillar12px(deferredBlock, sandstone_bottom, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            pillar12px(deferredBlock, red_sandstone_top, red_sandstone_top);
                        else pillar12px(deferredBlock, red_sandstone_bottom, red_sandstone_bottom);
                    }
                }
            }
            else if (block instanceof Pillar8PXBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> pillar8px(deferredBlock, texture(blockMaterial), texture(blockMaterial));
                    case LOG -> pillar8px(deferredBlock, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> pillar8px(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            pillar8px(deferredBlock, sandstone_top, sandstone_top);
                        else pillar8px(deferredBlock, sandstone_bottom, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            pillar8px(deferredBlock, red_sandstone_top, red_sandstone_top);
                        else pillar8px(deferredBlock, red_sandstone_bottom, red_sandstone_bottom);
                    }
                }
            }
            else if (block instanceof Pillar6PXBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> pillar6px(deferredBlock, texture(blockMaterial), texture(blockMaterial));
                    case LOG -> pillar6px(deferredBlock, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> pillar6px(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            pillar6px(deferredBlock, sandstone_top, sandstone_top);
                        else pillar6px(deferredBlock, sandstone_bottom, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            pillar6px(deferredBlock, red_sandstone_top, red_sandstone_top);
                        else pillar6px(deferredBlock, red_sandstone_bottom, red_sandstone_bottom);
                    }
                }
            }
            else if (block instanceof Pillar4PXBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> pillar4px(deferredBlock, texture(blockMaterial), texture(blockMaterial));
                    case LOG -> pillar4px(deferredBlock, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> pillar4px(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            pillar4px(deferredBlock, sandstone_top, sandstone_top);
                        else pillar4px(deferredBlock, sandstone_bottom, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            pillar4px(deferredBlock, red_sandstone_top, red_sandstone_top);
                        else pillar4px(deferredBlock, red_sandstone_bottom, red_sandstone_bottom);
                    }
                }
            }
            else if (block instanceof PillarConnector4PXBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> pillarConnector(deferredBlock, 4, texture(blockMaterial), texture(blockMaterial));
                    case LOG -> pillarConnector(deferredBlock, 4, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> pillarConnector(deferredBlock, 4, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            pillarConnector(deferredBlock, 4, sandstone_top, sandstone_top);
                        else pillarConnector(deferredBlock, 4, sandstone_bottom, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            pillarConnector(deferredBlock, 4, red_sandstone_top, red_sandstone_top);
                        else pillarConnector(deferredBlock, 4, red_sandstone_bottom, red_sandstone_bottom);
                    }
                }
            }
            else if (block instanceof PillarConnector6PXBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> pillarConnector(deferredBlock, 6, texture(blockMaterial), texture(blockMaterial));
                    case LOG -> pillarConnector(deferredBlock, 6, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> pillarConnector(deferredBlock, 6, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            pillarConnector(deferredBlock, 6, sandstone_top, sandstone_top);
                        else pillarConnector(deferredBlock, 6, sandstone_bottom, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            pillarConnector(deferredBlock, 6, red_sandstone_top, red_sandstone_top);
                        else pillarConnector(deferredBlock, 6, red_sandstone_bottom, red_sandstone_bottom);
                    }
                }
            }
            else if (block instanceof PillarConnector8PXBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> pillarConnector(deferredBlock, 8, texture(blockMaterial), texture(blockMaterial));
                    case LOG -> pillarConnector(deferredBlock, 8, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> pillarConnector(deferredBlock, 8, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            pillarConnector(deferredBlock, 8, sandstone_top, sandstone_top);
                        else pillarConnector(deferredBlock, 8, sandstone_bottom, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            pillarConnector(deferredBlock, 8, red_sandstone_top, red_sandstone_top);
                        else pillarConnector(deferredBlock, 8, red_sandstone_bottom, red_sandstone_bottom);
                    }
                }
            }
            else if (block instanceof PillarConnector12PXBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> pillarConnector(deferredBlock, 12, texture(blockMaterial), texture(blockMaterial));
                    case LOG -> pillarConnector(deferredBlock, 12, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> pillarConnector(deferredBlock, 12, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            pillarConnector(deferredBlock, 12, sandstone_top, sandstone_top);
                        else pillarConnector(deferredBlock, 12, sandstone_bottom, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            pillarConnector(deferredBlock, 12, red_sandstone_top, red_sandstone_top);
                        else pillarConnector(deferredBlock, 12, red_sandstone_bottom, red_sandstone_bottom);
                    }
                }
            }
            else if (block instanceof TableBlock) {
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.GLASS))
                    tableGlass(deferredBlock, texture(blockMaterial));
                else switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> table(deferredBlock, texture(blockMaterial));
                    case LOG -> table(deferredBlock, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> table(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case UP_DOWN -> table(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"), texture(blockMaterial, "bottom"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            table(deferredBlock, sandstone_top);
                        else table(deferredBlock, sandstone_side, sandstone_top, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            table(deferredBlock, red_sandstone_top);
                        else table(deferredBlock, red_sandstone_side, red_sandstone_top, red_sandstone_bottom);
                    }
                    case CUSTOM -> {
                        if (materialBlock.equals(Blocks.QUARTZ_BLOCK)) table(deferredBlock, quartz_block_side);
                        if (materialBlock.equals(Blocks.SMOOTH_QUARTZ)) table(deferredBlock, quartz_block_bottom);
                    }
                }
            }
            else if (block instanceof ChairBlock) {
                switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> chair(deferredBlock, texture(blockMaterial));
                    case LOG -> chair(deferredBlock, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> chair(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case UP_DOWN -> chair(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"), texture(blockMaterial, "bottom"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            chair(deferredBlock, sandstone_top);
                        else chair(deferredBlock, sandstone_side, sandstone_top, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            chair(deferredBlock, red_sandstone_top);
                        else chair(deferredBlock, red_sandstone_side, red_sandstone_top, red_sandstone_bottom);
                    }
                    case CUSTOM -> {
                        if (materialBlock.equals(Blocks.QUARTZ_BLOCK)) chair(deferredBlock, quartz_block_side);
                        if (materialBlock.equals(Blocks.SMOOTH_QUARTZ)) chair(deferredBlock, quartz_block_bottom);
                    }
                }
            }
            else if (block instanceof GardenChairBlock) {
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.WOOD)) woodenGardenChair(deferredBlock, texture(blockMaterial));
                else switch (modelType) {
                    case CUBE_ALL, CUBE_ALL_FRAMED -> stoneGardenChair(deferredBlock, texture(blockMaterial));
                    case LOG -> stoneGardenChair(deferredBlock, texture(blockMaterial), texture(blockMaterial, "top"));
                    case PILLAR -> stoneGardenChair(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"));
                    case UP_DOWN -> stoneGardenChair(deferredBlock, texture(blockMaterial, "side"), texture(blockMaterial, "top"), texture(blockMaterial, "bottom"));
                    case SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_SANDSTONE))
                            stoneGardenChair(deferredBlock, sandstone_top);
                        else stoneGardenChair(deferredBlock, sandstone_side, sandstone_top, sandstone_bottom);
                    }
                    case RED_SANDSTONE -> {
                        if (materialBlock.equals(Blocks.SMOOTH_RED_SANDSTONE))
                            stoneGardenChair(deferredBlock, red_sandstone_top);
                        else stoneGardenChair(deferredBlock, red_sandstone_side, red_sandstone_top, red_sandstone_bottom);
                    }
                    case CUSTOM -> {
                        if (materialBlock.equals(Blocks.QUARTZ_BLOCK)) stoneGardenChair(deferredBlock, quartz_block_side);
                        if (materialBlock.equals(Blocks.SMOOTH_QUARTZ)) stoneGardenChair(deferredBlock, quartz_block_bottom);
                    }
                }
            }
            else if (block instanceof HorizonPanelBlock) {
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.GLASS)) {
                    ModelFile modelFile = models().withExistingParent(deferredBlock.getId().getPath(), modLoc("block/translucent_panel_horizon"))
                                                  .texture("all", texture(blockMaterial));
                    simpleWithModel(deferredBlock, modelFile);
                }
            }
            else if (block instanceof StairBlock stairBlock) {
                stairsBlock(stairBlock, texture(blockMaterial));
                itemModels().stairs(deferredBlock.getId().getPath(), texture(blockMaterial), texture(blockMaterial), texture(blockMaterial));
            }
            else if (block instanceof SlabBlock slabBlock) {
                slabBlock(slabBlock, texture(blockMaterial), texture(blockMaterial));
                itemModels().slab(deferredBlock.getId().getPath(), texture(blockMaterial), texture(blockMaterial), texture(blockMaterial));
            }
            else if (block instanceof FenceBlock fenceBlock) {
                fenceBlock(fenceBlock, texture(blockMaterial));
                itemModels().fenceInventory(deferredBlock.getId().getPath(), texture(blockMaterial));
            }
            else if (block instanceof FenceGateBlock fenceGateBlock) {
                fenceGateBlock(fenceGateBlock, texture(blockMaterial));
                itemModels().fenceGate(deferredBlock.getId().getPath(), texture(blockMaterial));
            }
            else if (block instanceof PressurePlateBlock pressurePlateBlock) {
                pressurePlateBlock(pressurePlateBlock, texture(blockMaterial));
                itemModels().pressurePlate(deferredBlock.getId().getPath(), texture(blockMaterial));
            }
            else if (block instanceof ButtonBlock buttonBlock) {
                buttonBlock(buttonBlock, texture(blockMaterial));
                itemModels().buttonInventory(deferredBlock.getId().getPath(), texture(blockMaterial));
            }
        });
    }

    private ResourceLocation texture(BlockMaterial blockMaterial) {
        return new ResourceLocation(blockMaterial.getNamespace(), "block/" + blockMaterial.getPath());
    }

    private ResourceLocation texture(BlockMaterial blockMaterial, String s) {
        return new ResourceLocation(blockMaterial.getNamespace(), "block/" + blockMaterial.getPath() + "_" + s);
    }

    private void pillarConnector(DeferredBlock<Block> deferredBlock, int px, ResourceLocation side, ResourceLocation top) {
        String path = deferredBlock.getId().getPath();
        ModelFile modelCore = models()
                .withExistingParent(path + "_core", modLoc("block/pillar_connector_" + px + "px_core"))
                .texture("all", top);
        ModelFile modelFront = models()
                .withExistingParent(path + "_front", modLoc("block/pillar_connector_" + px + "px_front"))
                .texture("side", side).texture("end", top);
        ModelFile modelBack = models()
                .withExistingParent(path + "_back", modLoc("block/pillar_connector_" + px + "px_back"))
                .texture("side", side).texture("end", top);
        ModelFile modelInv = models()
                .withExistingParent(path + "_inv", modLoc("block/pillar_connector_" + px + "px_inv"))
                .texture("side", side).texture("end", top);
        pillarConnectorModel(deferredBlock.get(), modelCore, modelFront, modelBack);
        itemModels().getBuilder(path).parent(modelInv);
    }

    private void pillarConnectorModel(Block block, ModelFile modelCore, ModelFile modelFront, ModelFile modelBack) {
        getMultipartBuilder(block)
                .part()
                .modelFile(modelCore).addModel()
                .condition(BlockStateProperties.NORTH, false).end()
                .part()
                .modelFile(modelFront).addModel()
                .condition(BlockStateProperties.NORTH, true).end()
                .part()
                .modelFile(modelCore)
                .rotationY(180).addModel()
                .condition(BlockStateProperties.SOUTH, false).end()
                .part()
                .modelFile(modelBack).addModel()
                .condition(BlockStateProperties.SOUTH, true).end()
                .part()
                .modelFile(modelCore)
                .rotationY(270).addModel()
                .condition(BlockStateProperties.WEST, false).end()
                .part()
                .modelFile(modelFront)
                .rotationY(270).addModel()
                .condition(BlockStateProperties.WEST, true).end()
                .part()
                .modelFile(modelCore)
                .rotationY(90).addModel()
                .condition(BlockStateProperties.EAST, false).end()
                .part()
                .modelFile(modelBack)
                .rotationY(270).addModel()
                .condition(BlockStateProperties.EAST, true).end()
                .part()
                .modelFile(modelCore)
                .rotationX(270).uvLock(true).addModel()
                .condition(BlockStateProperties.UP, false).end()
                .part()
                .modelFile(modelFront)
                .rotationX(270).addModel()
                .condition(BlockStateProperties.UP, true).end()
                .part()
                .modelFile(modelCore)
                .rotationX(90).uvLock(true).addModel()
                .condition(BlockStateProperties.DOWN, false).end()
                .part()
                .modelFile(modelBack)
                .rotationX(270).addModel()
                .condition(BlockStateProperties.DOWN, true).end();
    }

    private void pillar4px(DeferredBlock<Block> deferredBlock, ResourceLocation side, ResourceLocation top) {
        String path = deferredBlock.getId().getPath();
        ModelFile modelFile = models()
                .withExistingParent(path, modLoc("block/pillar_4px"))
                .texture("side", side).texture("end", top);
        pillarYModel(path, deferredBlock.get(), modelFile, false);
    }

    private void pillar6px(DeferredBlock<Block> deferredBlock, ResourceLocation side, ResourceLocation top) {
        String path = deferredBlock.getId().getPath();
        ModelFile modelFile = models()
                .withExistingParent(path, modLoc("block/pillar_6px"))
                .texture("side", side).texture("end", top);
        pillarYModel(path, deferredBlock.get(), modelFile, false);
    }

    private void pillar8px(DeferredBlock<Block> deferredBlock, ResourceLocation side, ResourceLocation top) {
        String path = deferredBlock.getId().getPath();
        ModelFile modelFile = models()
                .withExistingParent(path, modLoc("block/pillar_8px"))
                .texture("side", side).texture("end", top);
        pillarYModel(path, deferredBlock.get(), modelFile, false);
    }
    
    private void pillar12px(DeferredBlock<Block> deferredBlock, ResourceLocation side, ResourceLocation top) {
        String path = deferredBlock.getId().getPath();
        ModelFile modelFile = models()
                .withExistingParent(path, modLoc("block/pillar_12px"))
                .texture("side", side).texture("end", top);
        pillarYModel(path, deferredBlock.get(), modelFile, false);
    }
    
    private void pillarYModel(String path, Block block, ModelFile model, boolean lockUV) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Y).modelForState()
                .modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.X).modelForState()
                .modelFile(model)
                .rotationX(90).rotationY(270).uvLock(lockUV).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Z).modelForState()
                .modelFile(model)
                .rotationX(90).uvLock(lockUV).addModel();
        itemModels().getBuilder(path).parent(model);
    }

    private void itemFrameShell() {
        directionModel(FDBlocks.ITEM_FRAME_SHELL_THIN, true);
        simple(
                FDBlocks.ITEM_FRAME_SHELL_BIG.get(),
                FDBlocks.ITEM_FRAME_SHELL_BIG.getId().getPath(),
                models().getExistingFile(modLoc("block/custom_glass"))
        );
    }

    private void lightTube() {
        ModelFile h = models().getExistingFile(modLoc("block/light_tube_horizontal"));
        ModelFile v = models().getExistingFile(modLoc("block/light_tube_vertical"));
        getVariantBuilder(FDBlocks.LIGHT_TUBE_BLOCK.get())
                .partialState()
                .with(BlockStateProperties.FACING, Direction.NORTH)
                .with(LightTubeBlock.VERTICAL, false).modelForState()
                .modelFile(h).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.NORTH)
                .with(LightTubeBlock.VERTICAL, true).modelForState()
                .modelFile(v).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.SOUTH)
                .with(LightTubeBlock.VERTICAL, false).modelForState()
                .modelFile(h).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.SOUTH)
                .with(LightTubeBlock.VERTICAL, true).modelForState()
                .modelFile(v).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.WEST)
                .with(LightTubeBlock.VERTICAL, false).modelForState()
                .modelFile(h).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.WEST)
                .with(LightTubeBlock.VERTICAL, true).modelForState()
                .modelFile(v).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.EAST)
                .with(LightTubeBlock.VERTICAL, false).modelForState()
                .modelFile(h).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.EAST)
                .with(LightTubeBlock.VERTICAL, true).modelForState()
                .modelFile(v).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.DOWN)
                .with(LightTubeBlock.VERTICAL, false).modelForState()
                .modelFile(h).rotationX(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.DOWN)
                .with(LightTubeBlock.VERTICAL, true).modelForState()
                .modelFile(v).rotationX(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.UP)
                .with(LightTubeBlock.VERTICAL, false).modelForState()
                .modelFile(h).rotationX(270).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.UP)
                .with(LightTubeBlock.VERTICAL, true).modelForState()
                .modelFile(v).rotationX(270).addModel();
        itemModels().getBuilder(FDBlocks.LIGHT_TUBE_BLOCK.getId().getPath()).parent(h);
    }

    private void lightPlate() {
        ModelFile s = models().getExistingFile(modLoc("block/light_plate_side"));
        ModelFile b = models().getExistingFile(modLoc("block/light_plate_up"));
        getVariantBuilder(FDBlocks.LIGHT_PLATE_BLOCK.get())
                .partialState()
                .with(BlockStateProperties.FACING, Direction.NORTH).modelForState()
                .modelFile(s).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.SOUTH).modelForState()
                .modelFile(s).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.WEST).modelForState()
                .modelFile(s).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.EAST).modelForState()
                .modelFile(s).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.DOWN).modelForState()
                .modelFile(b).rotationX(180).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.UP).modelForState()
                .modelFile(b).addModel();
        itemModels().getBuilder(FDBlocks.LIGHT_PLATE_BLOCK.getId().getPath()).parent(s);
    }

    private void heavyChains() {
        ModelFile modelFile = models().getExistingFile(modLoc("block/heavy_chains"));
        ModelFile modelFile2 = models().getExistingFile(modLoc("block/heavy_chains_mirror"));
        getVariantBuilder(FDBlocks.HEAVY_CHAINS_BLOCK.get())
                .partialState()
                .with(BlockStateProperties.FACING, Direction.NORTH).modelForState()
                .modelFile(modelFile).rotationX(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.SOUTH).modelForState()
                .modelFile(modelFile2).rotationX(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.WEST).modelForState()
                .modelFile(modelFile).rotationX(90).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.EAST).modelForState()
                .modelFile(modelFile2).rotationX(90).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.DOWN).modelForState()
                .modelFile(modelFile2).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.UP).modelForState()
                .modelFile(modelFile).addModel();
        itemModels().getBuilder(FDBlocks.HEAVY_CHAINS_BLOCK.getId().getPath()).parent(modelFile);
    }

    private void doubleBlock(DeferredBlock<Block> deferredBlock, ResourceLocation materialTop, ResourceLocation materialBottom) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        ModelFile modelFile = models().withExistingParent(path, modLoc("block/double_block"))
                                      .texture("top", materialTop)
                                      .texture("bottom", materialBottom);
        simple(block, path, modelFile);
    }

    private void guardrail(DeferredBlock<Block> deferredBlock, ResourceLocation texture, String modelName, boolean lockUV) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/" + modelName)))
                                          .texture("all", texture);

        BlockModelBuilder modelL = models().getBuilder(path + "_lower")
                                           .parent(models().getExistingFile(modLoc("block/" + modelName + "_lower")))
                                           .texture("all", texture);

        BlockModelBuilder modelC = models().getBuilder(path + "_corner")
                                           .parent(models().getExistingFile(modLoc("block/" + modelName + "_corner")))
                                           .texture("all", texture);

        BlockModelBuilder modelCL = models().getBuilder(path + "_corner_lower")
                                            .parent(models().getExistingFile(modLoc("block/" + modelName + "_lower")))
                                            .texture("all", texture);

        guardrailStates(block, model, modelL, modelC, modelCL, lockUV);
        itemModels().getBuilder(path).parent(model);
    }

    private void pillarGuardrail(DeferredBlock<Block> deferredBlock, ResourceLocation textureSide, ResourceLocation textureTop) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/guardrail_pillar")))
                                          .texture("side", textureSide)
                                          .texture("top", textureTop);

        BlockModelBuilder modelL = models().getBuilder(path + "_lower")
                                           .parent(models().getExistingFile(modLoc("block/guardrail_pillar_lower")))
                                           .texture("side", textureSide)
                                           .texture("top", textureTop);

        BlockModelBuilder modelC = models().getBuilder(path + "_corner")
                                           .parent(models().getExistingFile(modLoc("block/guardrail_pillar_corner")))
                                           .texture("side", textureSide)
                                           .texture("top", textureTop);

        BlockModelBuilder modelCL = models().getBuilder(path + "_corner_lower")
                                            .parent(models().getExistingFile(modLoc("block/guardrail_pillar_corner_lower")))
                                            .texture("side", textureSide)
                                            .texture("top", textureTop);

        guardrailStates(block, model, modelL, modelC, modelCL, false);
        itemModels().getBuilder(path).parent(model);
    }

    private void glassGuardrail(DeferredBlock<Block> deferredBlock, ResourceLocation textureSide, ResourceLocation textureTop) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/guardrail_translucent")))
                                          .texture("glass", textureSide)
                                          .texture("glass_side", textureTop);

        BlockModelBuilder modelL = models().getBuilder(path + "_lower")
                                           .parent(models().getExistingFile(modLoc("block/guardrail_translucent_lower")))
                                           .texture("glass", textureSide)
                                           .texture("glass_side", textureTop);

        BlockModelBuilder modelC = models().getBuilder(path + "_corner")
                                           .parent(models().getExistingFile(modLoc("block/guardrail_translucent_corner")))
                                           .texture("glass", textureSide)
                                           .texture("glass_side", textureTop);

        BlockModelBuilder modelCL = models().getBuilder(path + "_corner_lower")
                                            .parent(models().getExistingFile(modLoc("block/guardrail_translucent_corner_lower")))
                                            .texture("glass", textureSide)
                                            .texture("glass_side", textureTop);

        guardrailStates(block, model, modelL, modelC, modelCL, false);
        itemModels().getBuilder(path).parent(model);
    }

    private void customGuardrail(DeferredBlock<Block> deferredBlock, ResourceLocation textureSide, ResourceLocation textureTop, ResourceLocation textureBottom, boolean lockUV) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/guardrail_custom")))
                                          .texture("side", textureSide)
                                          .texture("top", textureTop)
                                          .texture("bottom", textureBottom)
                                          .texture("particle", textureSide);

        BlockModelBuilder modelL = models().getBuilder(path + "_lower")
                                           .parent(models().getExistingFile(modLoc("block/guardrail_custom_lower")))
                                           .texture("side", textureSide)
                                           .texture("top", textureTop)
                                           .texture("bottom", textureBottom)
                                           .texture("particle", textureSide);

        BlockModelBuilder modelC = models().getBuilder(path + "_corner")
                                           .parent(models().getExistingFile(modLoc("block/guardrail_custom_corner")))
                                           .texture("side", textureSide)
                                           .texture("top", textureTop)
                                           .texture("bottom", textureBottom)
                                           .texture("particle", textureSide);

        BlockModelBuilder modelCL = models().getBuilder(path + "_corner_lower")
                                            .parent(models().getExistingFile(modLoc("block/guardrail_custom_corner_lower")))
                                            .texture("side", textureSide)
                                            .texture("top", textureTop)
                                            .texture("bottom", textureBottom)
                                            .texture("particle", textureSide);

        guardrailStates(block, model, modelL, modelC, modelCL, lockUV);
        itemModels().getBuilder(path).parent(model);
    }

    private void guardrailStates(Block block, ModelFile model, ModelFile modelL, ModelFile modelC, ModelFile modelCL, boolean lockUV) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(GuardrailBlock.CORNER, false)
                .with(GuardrailBlock.LOWER, false)
                .modelForState().modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(GuardrailBlock.CORNER, false)
                .with(GuardrailBlock.LOWER, false)
                .modelForState().uvLock(lockUV).modelFile(model)
                .rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(GuardrailBlock.CORNER, false)
                .with(GuardrailBlock.LOWER, false)
                .modelForState().uvLock(lockUV).modelFile(model)
                .rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(GuardrailBlock.CORNER, false)
                .with(GuardrailBlock.LOWER, false)
                .modelForState().uvLock(lockUV).modelFile(model)
                .rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(GuardrailBlock.CORNER, false)
                .with(GuardrailBlock.LOWER, true)
                .modelForState().modelFile(modelL).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(GuardrailBlock.CORNER, false)
                .with(GuardrailBlock.LOWER, true)
                .modelForState().uvLock(lockUV).modelFile(modelL)
                .rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(GuardrailBlock.CORNER, false)
                .with(GuardrailBlock.LOWER, true)
                .modelForState().uvLock(lockUV).modelFile(modelL)
                .rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(GuardrailBlock.CORNER, false)
                .with(GuardrailBlock.LOWER, true)
                .modelForState().uvLock(lockUV).modelFile(modelL)
                .rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(GuardrailBlock.CORNER, true)
                .with(GuardrailBlock.LOWER, false)
                .modelForState().modelFile(modelC).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(GuardrailBlock.CORNER, true)
                .with(GuardrailBlock.LOWER, false)
                .modelForState().uvLock(lockUV).modelFile(modelC)
                .rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(GuardrailBlock.CORNER, true)
                .with(GuardrailBlock.LOWER, false)
                .modelForState().uvLock(lockUV).modelFile(modelC)
                .rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(GuardrailBlock.CORNER, true)
                .with(GuardrailBlock.LOWER, false)
                .modelForState().uvLock(lockUV).modelFile(modelC)
                .rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(GuardrailBlock.CORNER, true)
                .with(GuardrailBlock.LOWER, true)
                .modelForState().modelFile(modelCL).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(GuardrailBlock.CORNER, true)
                .with(GuardrailBlock.LOWER, true)
                .modelForState().uvLock(lockUV).modelFile(modelCL)
                .rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(GuardrailBlock.CORNER, true)
                .with(GuardrailBlock.LOWER, true)
                .modelForState().uvLock(lockUV).modelFile(modelCL)
                .rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(GuardrailBlock.CORNER, true)
                .with(GuardrailBlock.LOWER, true)
                .modelForState().uvLock(lockUV).modelFile(modelCL)
                .rotationY(90).addModel();
    }

    private void peepWindow(DeferredBlock<Block> deferredBlock, ResourceLocation material, boolean translucent) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder modelFile = models()
                .withExistingParent(path, modLoc(translucent ? "block/peep_window_translucent" : "block/peep_window"))
                .texture("all", material);
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.FACING, Direction.NORTH).modelForState()
                .modelFile(modelFile).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.SOUTH).modelForState()
                .modelFile(modelFile)
                .rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.WEST).modelForState()
                .modelFile(modelFile)
                .rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.EAST).modelForState()
                .modelFile(modelFile)
                .rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.UP).modelForState()
                .modelFile(modelFile)
                .rotationX(270).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.DOWN).modelForState()
                .modelFile(modelFile)
                .rotationX(90).rotationY(180).addModel();
        itemModels().getBuilder(path).parent(modelFile);
    }

    private void peepWindowUpDown(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialUp, ResourceLocation materialDown) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder modelSide = models()
                .withExistingParent(path, modLoc("block/peep_window"))
                .texture("all", materialSide);
        BlockModelBuilder modelUp = models()
                .withExistingParent(path + "_up", modLoc("block/peep_window"))
                .texture("all", materialUp);
        BlockModelBuilder modelDown = models()
                .withExistingParent(path + "_down", modLoc("block/peep_window"))
                .texture("all", materialDown);

        peepWindowStates(block, modelSide, modelUp, modelDown);
        itemModels().getBuilder(path).parent(modelSide);
    }

    private void peepWindowStates(Block block, ModelFile modelSide, BlockModelBuilder modelUp, BlockModelBuilder modelDown) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.FACING, Direction.NORTH).modelForState()
                .modelFile(modelSide).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.SOUTH).modelForState()
                .modelFile(modelSide)
                .rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.WEST).modelForState()
                .modelFile(modelSide)
                .rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.EAST).modelForState()
                .modelFile(modelSide)
                .rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.UP).modelForState()
                .modelFile(modelUp)
                .rotationX(270).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.FACING, Direction.DOWN).modelForState()
                .modelFile(modelDown)
                .rotationX(90).rotationY(180).addModel();
    }

    private void lampInGlass(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        lampInGlassStates(
                block,
                models().getBuilder(path)
                        .parent(models().getExistingFile(modLoc("block/lamp_in_glass_all")))
                        .texture("all", material),
                true
        );
        itemModels().getBuilder(path).parent(
                models().getBuilder(path + "_inventory")
                        .parent(models().getExistingFile(modLoc("block/lamp_in_glass_inventory_all")))
                        .texture("all", material)
        );
    }

    private void lampInGlassCustom(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop, ResourceLocation materialBottom) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        lampInGlassStates(
                block,
                models().getBuilder(path)
                        .parent(models().getExistingFile(modLoc("block/lamp_in_glass")))
                        .texture("lamp_side", materialSide)
                        .texture("lamp_top", materialTop)
                        .texture("lamp_bottom", materialBottom),
                false
        );
        itemModels().getBuilder(path).parent(
                models().getBuilder(path + "_inventory")
                        .parent(models().getExistingFile(modLoc("block/lamp_in_glass_inventory")))
                        .texture("lamp_side", materialSide)
                        .texture("lamp_top", materialTop)
                        .texture("lamp_bottom", materialBottom)
        );
    }

    private void lampInGlassStates(Block block, ModelFile model, boolean lockUV) {
        ModelFile glass = models().getExistingFile(modLoc("block/custom_glass"));
        getMultipartBuilder(block)
                .part().modelFile(glass).addModel().end()
                .part().modelFile(model)
                .rotationX(90).uvLock(lockUV).addModel()
                .condition(BlockStateProperties.FACING, Direction.NORTH).end()
                .part().modelFile(model)
                .rotationX(270).uvLock(lockUV).addModel()
                .condition(BlockStateProperties.FACING, Direction.SOUTH).end()
                .part().modelFile(model)
                .rotationX(90).rotationY(270).uvLock(lockUV).addModel()
                .condition(BlockStateProperties.FACING, Direction.WEST).end()
                .part().modelFile(model)
                .rotationX(90).rotationY(90).uvLock(lockUV).addModel()
                .condition(BlockStateProperties.FACING, Direction.EAST).end()
                .part().modelFile(model).addModel()
                .condition(BlockStateProperties.FACING, Direction.UP).end()
                .part().modelFile(model).rotationX(180).uvLock(lockUV).addModel()
                .condition(BlockStateProperties.FACING, Direction.DOWN).end();
    }

    private void table(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder modelLeg = models()
                .withExistingParent(path + "_leg", modLoc("block/table_leg_all"))
                .texture("all", material);
        tableState(
                block,
                models()
                        .withExistingParent(path, modLoc("block/table_all"))
                        .texture("all", material),
                modelLeg
        );
        itemModels().getBuilder(path).parent(modelLeg);
    }

    private void tableGlass(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        tableGlassState(
                block,
                models()
                        .withExistingParent(path, modLoc("block/table_glass"))
                        .texture("all", material)
        );
        itemModels().getBuilder(path).parent(
                models()
                        .withExistingParent(path + "_inv", modLoc("block/table_leg_glass_inv"))
                        .texture("all", material)
        );
    }

    private void table(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialEnd) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models()
                .withExistingParent(path, modLoc("block/table_pillar"))
                .texture("side", materialSide)
                .texture("end", materialEnd);
        BlockModelBuilder modelLeg = models()
                .withExistingParent(path + "_leg", modLoc("block/table_leg_pillar"))
                .texture("side", materialSide)
                .texture("end", materialEnd);
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X)
                .with(TableBlock.LEGGED, false).modelForState()
                .modelFile(model).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z)
                .with(TableBlock.LEGGED, false).modelForState()
                .modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X)
                .with(TableBlock.LEGGED, true).modelForState()
                .modelFile(modelLeg).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z)
                .with(TableBlock.LEGGED, true).modelForState()
                .modelFile(modelLeg).addModel();
        itemModels().getBuilder(path).parent(modelLeg);
    }

    private void table(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop, ResourceLocation materialBottom) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder modelLeg = models()
                .withExistingParent(path + "_leg", modLoc("block/table_leg"))
                .texture("side", materialSide)
                .texture("top", materialTop)
                .texture("bottom", materialBottom);
        tableState(
                block,
                models()
                        .withExistingParent(path, modLoc("block/table"))
                        .texture("side", materialSide)
                        .texture("top", materialTop)
                        .texture("bottom", materialBottom),
                modelLeg
        );
        itemModels().getBuilder(path).parent(modelLeg);
    }

    private void tableState(Block block, ModelFile model, ModelFile modelLeg) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X)
                .with(TableBlock.LEGGED, false).modelForState()
                .modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z)
                .with(TableBlock.LEGGED, false).modelForState()
                .modelFile(model).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X)
                .with(TableBlock.LEGGED, true).modelForState()
                .modelFile(modelLeg).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z)
                .with(TableBlock.LEGGED, true).modelForState()
                .modelFile(modelLeg).rotationY(90).addModel();
    }

    private void tableGlassState(Block block, ModelFile model) {
        getMultipartBuilder(block)
                .part()
                .modelFile(model).addModel()
                .condition(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X).end()
                .part()
                .modelFile(model).rotationY(90).addModel()
                .condition(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z).end()
                .part()
                .modelFile(models().getExistingFile(modLoc("block/table_leg_iron"))).addModel()
                .condition(TableBlock.LEGGED, true).end();
    }

    private void chair(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        horizontalDirectionModel(
                block,
                path,
                models().withExistingParent(path, modLoc("block/chair_all"))
                        .texture("all", material),
                false
        );
    }

    private void chair(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop) {
        chair(deferredBlock, materialSide, materialTop, materialTop);
    }

    private void chair(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop, ResourceLocation materialBottom) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        horizontalDirectionModel(
                block,
                path,
                models().withExistingParent(path, modLoc("block/chair"))
                        .texture("side", materialSide)
                        .texture("top", materialTop)
                        .texture("bottom", materialBottom),
                false
        );
    }

    private void woodenGardenChair(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        ModelFile modelSingle = models().withExistingParent(path + "_single", modLoc("block/garden_chair_wooden_single")).texture("all", material);
        ModelFile modelCenter = models().withExistingParent(path + "_center", modLoc("block/garden_chair_wooden_center")).texture("all", material);
        ModelFile modelLeft = models().withExistingParent(path + "_left", modLoc("block/garden_chair_wooden_left")).texture("all", material);
        ModelFile modelRight = models().withExistingParent(path + "_right", modLoc("block/garden_chair_wooden_right")).texture("all", material);

        gardenChair(block, modelSingle, modelCenter, modelLeft, modelRight);

        itemModels().getBuilder(path).parent(modelSingle);
    }

    private void stoneGardenChair(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop) {
        stoneGardenChair(deferredBlock, materialSide, materialTop, materialTop);
    }

    private void stoneGardenChair(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        ModelFile modelSingle = models().withExistingParent(path + "_single", modLoc("block/garden_chair_stone_single_all")).texture("all", material);
        ModelFile modelCenter = models().withExistingParent(path + "_center", modLoc("block/garden_chair_stone_center_all")).texture("all", material);
        ModelFile modelLeft = models().withExistingParent(path + "_left", modLoc("block/garden_chair_stone_left_all")).texture("all", material);
        ModelFile modelRight = models().withExistingParent(path + "_right", modLoc("block/garden_chair_stone_right_all")).texture("all", material);

        gardenChair(block, modelSingle, modelCenter, modelLeft, modelRight);

        itemModels().getBuilder(path).parent(modelSingle);
    }

    private void stoneGardenChair(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop, ResourceLocation materialBottom) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        ModelFile modelSingle = models().withExistingParent(path + "_single", modLoc("block/garden_chair_stone_single"))
                                        .texture("side", materialSide)
                                        .texture("top", materialTop)
                                        .texture("bottom", materialBottom);
        ModelFile modelCenter = models().withExistingParent(path + "_center", modLoc("block/garden_chair_stone_center"))
                                        .texture("side", materialSide)
                                        .texture("top", materialTop)
                                        .texture("bottom", materialBottom);
        ModelFile modelLeft = models().withExistingParent(path + "_left", modLoc("block/garden_chair_stone_left"))
                                      .texture("side", materialSide)
                                      .texture("top", materialTop)
                                      .texture("bottom", materialBottom);
        ModelFile modelRight = models().withExistingParent(path + "_right", modLoc("block/garden_chair_stone_right"))
                                       .texture("side", materialSide)
                                       .texture("top", materialTop)
                                       .texture("bottom", materialBottom);

        gardenChair(block, modelSingle, modelCenter, modelLeft, modelRight);

        itemModels().getBuilder(path).parent(modelSingle);
    }

    private void gardenChair(Block block, ModelFile modelSingle, ModelFile modelCenter, ModelFile modelLeft, ModelFile modelRight) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.SINGLE)
                .modelForState().modelFile(modelSingle).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.CENTER)
                .modelForState().modelFile(modelCenter).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.LEFT)
                .modelForState().modelFile(modelLeft).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.RIGHT)
                .modelForState().modelFile(modelRight).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.SINGLE)
                .modelForState().modelFile(modelSingle).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.CENTER)
                .modelForState().modelFile(modelCenter).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.LEFT)
                .modelForState().modelFile(modelLeft).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.RIGHT)
                .modelForState().modelFile(modelRight).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.SINGLE)
                .modelForState().modelFile(modelSingle).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.CENTER)
                .modelForState().modelFile(modelCenter).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.LEFT)
                .modelForState().modelFile(modelLeft).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.RIGHT)
                .modelForState().modelFile(modelRight).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.SINGLE)
                .modelForState().modelFile(modelSingle).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.CENTER)
                .modelForState().modelFile(modelCenter).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.LEFT)
                .modelForState().modelFile(modelLeft).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(FDBlockStateProperties.CHAIR_TYPE, ChairType.RIGHT)
                .modelForState().modelFile(modelRight).rotationY(90).addModel();
    }

    private void oneCutBlock(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                              .parent(models().getExistingFile(modLoc("block/one_cut_block_all")))
                                              .texture("all", material);
        getMultipartBuilder(block)
                .part().modelFile(model).addModel()
                .condition(BlockStateProperties.FACING, Direction.NORTH).end()
                .part().modelFile(model).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.NORTH)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(model).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.SOUTH).end()
                .part().modelFile(model).addModel()
                .condition(BlockStateProperties.FACING, Direction.SOUTH)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(model).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.WEST).end()
                .part().modelFile(model).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.WEST)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(model).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.EAST).end()
                .part().modelFile(model).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.EAST)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(model).rotationX(270).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.UP).end()
                .part().modelFile(model).rotationX(90).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.UP)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(model).rotationX(90).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.DOWN).end()
                .part().modelFile(model).rotationX(270).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.DOWN)
                .condition(OneCutBlock.DOUBLE, true).end();
        itemModels().getBuilder(path).parent(model);
    }

    private void oneCutBlockPillar(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder modelSide = models().getBuilder(path)
                                              .parent(models().getExistingFile(modLoc("block/one_cut_block_horizontal")))
                                              .texture("side", materialSide)
                                              .texture("top", materialTop)
                                              .texture("bottom", materialTop);
        BlockModelBuilder modelDown = models().getBuilder(path + "_down")
                                              .parent(models().getExistingFile(modLoc("block/one_cut_block_down")))
                                              .texture("side", materialSide)
                                              .texture("top", materialTop)
                                              .texture("bottom", materialTop);

        getMultipartBuilder(block)
                .part().modelFile(modelSide).addModel()
                .condition(BlockStateProperties.FACING, Direction.NORTH).end()
                .part().modelFile(modelSide).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.NORTH)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelSide).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.SOUTH).end()
                .part().modelFile(modelSide).addModel()
                .condition(BlockStateProperties.FACING, Direction.SOUTH)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelSide).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.WEST).end()
                .part().modelFile(modelSide).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.WEST)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelSide).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.EAST).end()
                .part().modelFile(modelSide).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.EAST)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelDown).rotationX(180).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.UP).end()
                .part().modelFile(modelDown).addModel()
                .condition(BlockStateProperties.FACING, Direction.UP)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelDown).addModel()
                .condition(BlockStateProperties.FACING, Direction.DOWN).end()
                .part().modelFile(modelDown).rotationX(180).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.DOWN)
                .condition(OneCutBlock.DOUBLE, true).end();
        itemModels().getBuilder(path).parent(modelSide);
    }

    private void oneCutBlockUpDown(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop, ResourceLocation materialBottom) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder modelSide = models().getBuilder(path)
                                              .parent(models().getExistingFile(modLoc("block/one_cut_block_horizontal")))
                                              .texture("side", materialSide)
                                              .texture("top", materialTop)
                                              .texture("bottom", materialBottom);
        BlockModelBuilder modelDown = models().getBuilder(path + "_down")
                                              .parent(models().getExistingFile(modLoc("block/one_cut_block_down")))
                                              .texture("side", materialSide)
                                              .texture("top", materialTop)
                                              .texture("bottom", materialBottom);
        BlockModelBuilder modelUp = models().getBuilder(path + "_up")
                                              .parent(models().getExistingFile(modLoc("block/one_cut_block_up")))
                                              .texture("side", materialSide)
                                              .texture("bottom", materialBottom);

        getMultipartBuilder(block)
                .part().modelFile(modelSide).addModel()
                .condition(BlockStateProperties.FACING, Direction.NORTH).end()
                .part().modelFile(modelSide).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.NORTH)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelSide).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.SOUTH).end()
                .part().modelFile(modelSide).addModel()
                .condition(BlockStateProperties.FACING, Direction.SOUTH)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelSide).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.WEST).end()
                .part().modelFile(modelSide).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.WEST)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelSide).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.EAST).end()
                .part().modelFile(modelSide).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.EAST)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelUp).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.UP).end()
                .part().modelFile(modelDown).addModel()
                .condition(BlockStateProperties.FACING, Direction.UP)
                .condition(OneCutBlock.DOUBLE, true).end()
                .part().modelFile(modelDown).addModel()
                .condition(BlockStateProperties.FACING, Direction.DOWN).end()
                .part().modelFile(modelUp).uvLock(true).addModel()
                .condition(BlockStateProperties.FACING, Direction.DOWN)
                .condition(OneCutBlock.DOUBLE, true).end();
        itemModels().getBuilder(path).parent(modelSide);
    }

    private void thinStair(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder straight = models().getBuilder(path)
                                             .parent(models().getExistingFile(modLoc("block/thin_stair")))
                                             .texture("all", material);
        BlockModelBuilder inner = models().getBuilder(path + "_inner")
                                          .parent(models().getExistingFile(modLoc("block/thin_stair_inner")))
                                          .texture("all", material);
        BlockModelBuilder outer = models().getBuilder(path + "_outer")
                                          .parent(models().getExistingFile(modLoc("block/thin_stair_outer")))
                                          .texture("all", material);

        thinStairStates(block, straight, inner, outer);
        itemModels().getBuilder(deferredBlock.getId().getPath()).parent(straight);
    }

    private void thinStairUpDown(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop, ResourceLocation materialBottom) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder straight = models().getBuilder(path)
                                             .parent(models().getExistingFile(modLoc("block/thin_stair_updown")))
                                             .texture("side", materialSide)
                                             .texture("top", materialTop)
                                             .texture("bottom", materialBottom);
        BlockModelBuilder inner = models().getBuilder(path + "_inner")
                                          .parent(models().getExistingFile(modLoc("block/thin_stair_updown_inner")))
                                          .texture("side", materialSide)
                                          .texture("top", materialTop)
                                          .texture("bottom", materialBottom);
        BlockModelBuilder outer = models().getBuilder(path + "_outer")
                                          .parent(models().getExistingFile(modLoc("block/thin_stair_updown_outer")))
                                          .texture("side", materialSide)
                                          .texture("top", materialTop)
                                          .texture("bottom", materialBottom);

        thinStairStates(block, straight, inner, outer);
        itemModels().getBuilder(path).parent(straight);
    }

    private void thinStairStates(Block block, ModelFile straight, ModelFile inner, ModelFile outer) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.STRAIGHT)
                .modelForState().modelFile(straight).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.INNER)
                .modelForState().modelFile(inner).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.OUTER)
                .modelForState().modelFile(outer).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.STRAIGHT)
                .modelForState().modelFile(straight).rotationY(180).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.INNER)
                .modelForState().modelFile(inner).rotationY(180).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.OUTER)
                .modelForState().modelFile(outer).rotationY(180).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.STRAIGHT)
                .modelForState().modelFile(straight).rotationY(270).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.INNER)
                .modelForState().modelFile(inner).rotationY(270).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.OUTER)
                .modelForState().modelFile(outer).rotationY(270).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.STRAIGHT)
                .modelForState().modelFile(straight).rotationY(90).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.INNER)
                .modelForState().modelFile(inner).rotationY(90).uvLock(true).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(ThinStairBlock.SHAPE, ThinStairBlock.ThinStairsShape.OUTER)
                .modelForState().modelFile(outer).rotationY(90).uvLock(true).addModel();
    }

    private void doubleCutBlock(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model1 = models().getBuilder(path)
                                           .parent(models().getExistingFile(modLoc("block/double_cut_block_all")))
                                           .texture("all", material);
        BlockModelBuilder model2 = models().getBuilder(path + "_vertical")
                                           .parent(models().getExistingFile(modLoc("block/double_cut_block_vertical_all")))
                                           .texture("all", material);
        getMultipartBuilder(block)
                .part().modelFile(model1).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FDBlockStateProperties.PART_A, true).end()
                .part().modelFile(model1)
                .rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FDBlockStateProperties.PART_B, true).end()
                .part().modelFile(model1)
                .rotationX(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FDBlockStateProperties.PART_C, true).end()
                .part().modelFile(model1)
                .rotationX(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FDBlockStateProperties.PART_D, true).end()
                .part().modelFile(model1)
                .rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FDBlockStateProperties.PART_A, true).end()
                .part().modelFile(model1)
                .rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FDBlockStateProperties.PART_B, true).end()
                .part().modelFile(model1)
                .rotationX(180).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FDBlockStateProperties.PART_C, true).end()
                .part().modelFile(model1)
                .rotationX(270).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FDBlockStateProperties.PART_D, true).end()
                .part().modelFile(model2)
                .addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FDBlockStateProperties.PART_A, true).end()
                .part().modelFile(model2)
                .rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FDBlockStateProperties.PART_B, true).end()
                .part().modelFile(model2)
                .rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FDBlockStateProperties.PART_C, true).end()
                .part().modelFile(model2)
                .rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FDBlockStateProperties.PART_D, true).end();
        itemModels().getBuilder(path).parent(model1);
    }

    private void doubleCutBlockCustom(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop, ResourceLocation materialBottom) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model1 = models().getBuilder(path)
                                           .parent(models().getExistingFile(modLoc("block/double_cut_block")))
                                           .texture("side", materialSide)
                                           .texture("top", materialTop)
                                           .texture("bottom", materialBottom);
        BlockModelBuilder model2 = models().getBuilder(path + "_top")
                                           .parent(models().getExistingFile(modLoc("block/double_cut_block_top")))
                                           .texture("side", materialSide)
                                           .texture("bottom", materialBottom);
        BlockModelBuilder modelV = models().getBuilder(path + "_vertical")
                                           .parent(models().getExistingFile(modLoc("block/double_cut_block_vertical")))
                                           .texture("side", materialSide)
                                           .texture("top", materialTop)
                                           .texture("bottom", materialBottom);

        getMultipartBuilder(block)
                .part().modelFile(model1).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FDBlockStateProperties.PART_A, true).end()
                .part().modelFile(model1)
                .rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FDBlockStateProperties.PART_B, true).end()
                .part().modelFile(model2).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FDBlockStateProperties.PART_C, true).end()
                .part().modelFile(model2)
                .rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FDBlockStateProperties.PART_D, true).end()
                .part().modelFile(model1)
                .rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FDBlockStateProperties.PART_A, true).end()
                .part().modelFile(model1)
                .rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FDBlockStateProperties.PART_B, true).end()
                .part().modelFile(model2)
                .rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FDBlockStateProperties.PART_C, true).end()
                .part().modelFile(model2)
                .rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FDBlockStateProperties.PART_D, true).end()
                .part().modelFile(modelV).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FDBlockStateProperties.PART_A, true).end()
                .part().modelFile(modelV)
                .rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FDBlockStateProperties.PART_B, true).end()
                .part().modelFile(modelV)
                .rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FDBlockStateProperties.PART_C, true).end()
                .part().modelFile(modelV)
                .rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FDBlockStateProperties.PART_D, true).end();
        itemModels().getBuilder(path).parent(model1);
    }

    private void tripleCut(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder modelFile = models().getBuilder(path)
                                              .parent(models().getExistingFile(modLoc("block/triple_cut_block_all")))
                                              .texture("all", material);

        getMultipartBuilder(block)
                .part().modelFile(modelFile)
                .addModel()
                .condition(FDBlockStateProperties.PART_A, true).end()
                .part().modelFile(modelFile)
                .rotationY(90).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_B, true).end()
                .part().modelFile(modelFile)
                .rotationY(270).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_C, true).end()
                .part().modelFile(modelFile)
                .rotationY(180).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_D, true).end()
                .part().modelFile(modelFile)
                .rotationX(270).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_E, true).end()
                .part().modelFile(modelFile)
                .rotationX(270).rotationY(90).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_F, true).end()
                .part().modelFile(modelFile)
                .rotationX(180).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_G, true).end()
                .part().modelFile(modelFile)
                .rotationX(180).rotationY(270).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_H, true).end();

        itemModels().getBuilder(path).parent(modelFile);
    }

    private void tripleCutCustom(DeferredBlock<Block> deferredBlock, ResourceLocation materialSide, ResourceLocation materialTop, ResourceLocation materialBottom) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/triple_cut_block")))
                                          .texture("side", materialSide)
                                          .texture("top", materialTop)
                                          .texture("bottom", materialBottom);
        BlockModelBuilder model2 = models().getBuilder(path + "_up")
                                           .parent(models().getExistingFile(modLoc("block/triple_cut_block_up")))
                                           .texture("side", materialSide)
                                           .texture("bottom", materialBottom);

        getMultipartBuilder(block)
                .part().modelFile(model).addModel()
                .condition(FDBlockStateProperties.PART_A, true).end()
                .part().modelFile(model)
                .rotationY(90).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_B, true).end()
                .part().modelFile(model)
                .rotationY(270).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_C, true).end()
                .part().modelFile(model)
                .rotationY(180).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_D, true).end()
                .part().modelFile(model2).addModel()
                .condition(FDBlockStateProperties.PART_E, true).end()
                .part().modelFile(model2)
                .rotationY(90).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_F, true).end()
                .part().modelFile(model2)
                .rotationY(270).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_G, true).end()
                .part().modelFile(model2)
                .rotationY(180).uvLock(true).addModel()
                .condition(FDBlockStateProperties.PART_H, true).end();

        itemModels().getBuilder(path).parent(model);
    }
    

}
