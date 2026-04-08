package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.registries.BlockMaterial;
import net.fiercemanul.fiercelive.data.registries.BlockMaterialTag;
import net.fiercemanul.fiercelive.data.registries.FLRegister;
import net.fiercemanul.fiercelive.world.level.block.LightTubeBlock;
import net.fiercemanul.fiercelive.world.level.block.TableBlock;
import net.fiercemanul.fiercelive.world.level.block.state.properties.*;
import net.fiercemanul.fiercesource.data.FSBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.function.Consumer;

import static net.fiercemanul.fiercelive.data.FLBlocks.*;


public class BlockStateGen extends FSBlockStateProvider {


    public static final HashMap<DeferredHolder<Block, ? extends Block>, Consumer<BlockStateGen>> ROWS = new HashMap<>();

    private final ResourceLocation dirt = mcLoc("block/dirt");
    private final ResourceLocation glass = mcLoc("block/glass");
    private final ResourceLocation sandstone_top = mcLoc("block/sandstone_top");
    private final ResourceLocation sandstone_side = mcLoc("block/sandstone");
    private final ResourceLocation sandstone_bottom = mcLoc("block/sandstone_bottom");
    private final ResourceLocation red_sandstone_top = mcLoc("block/red_sandstone_top");
    private final ResourceLocation red_sandstone_side = mcLoc("block/red_sandstone");
    private final ResourceLocation red_sandstone_bottom = mcLoc("block/red_sandstone_bottom");
    private final ResourceLocation quartz_block_side = mcLoc("block/quartz_block_side");
    private final ResourceLocation quartz_block_bottom = mcLoc("block/quartz_block_bottom");
    private final ResourceLocation snow = mcLoc("block/snow");
    private final ResourceLocation magma = mcLoc("block/magma");
    private final ResourceLocation netherrack = mcLoc("block/netherrack");
    private final ResourceLocation crimson_nylium = mcLoc("block/crimson_nylium");
    private final ResourceLocation warped_nylium = mcLoc("block/warped_nylium");
    private final ResourceLocation blackIron = modLoc("block/black_iron");

    @Nullable
    private ModelFile tint_sea_lantern, tint_sea_lantern_glass_lamp, tint_reinforced_sea_lantern;

    public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FierceLive.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        tint_sea_lantern = models().getBuilder("tint_sea_lantern").parent(models().getExistingFile(modLoc("block/cube_tint_all"))).texture("all", modLoc("block/white_sea_lantern"));
        tint_sea_lantern_glass_lamp = models().withExistingParent("tint_sea_lantern_glass_lamp", mcLoc("block/block"))
                                              .texture("particle", glass)
                                              .customLoader(CompositeModelBuilder::begin)
                                              .child("part_a", models().nested().parent(models().getExistingFile(glass)).renderType("translucent"))
                                              .child("part_b", models().nested().parent(models().getExistingFile(modLoc("block/glass_lamp_tint_all")))
                                                                       .texture("all", modLoc("block/white_sea_lantern")))
                                              .end();
        tint_reinforced_sea_lantern = models().getExistingFile(modLoc("block/tint_reinforced_sea_lantern"));

        ROWS.put(FOX_CARROTS, gen -> {});
        ROWS.put(FOX_CARROT_SHEAF, gen -> gen.simpleWithModel(FOX_CARROT_SHEAF));
        ROWS.put(FOX_CARROT_BASKET, gen -> gen.simpleWithModel(FOX_CARROT_BASKET));
        ROWS.put(CRAFTING_DESK, gen -> gen.simpleWithModel(CRAFTING_DESK));
        ROWS.put(CRAFTING_BLOCK, gen -> gen.simpleWithModel(CRAFTING_BLOCK));
        ROWS.put(ROCK_PATH, gen -> gen.simpleWithModelNatureHorizontal(ROCK_PATH));
        ROWS.put(CRAFTING_PAD, gen -> gen.directionBlock(CRAFTING_PAD, false));
        ROWS.put(PORTABLE_WORKSTATION, gen -> gen.horizontalDirectionBlock(PORTABLE_WORKSTATION, false));
        ROWS.put(LAPTOP_TERMINAL, gen -> gen.horizontalDirectionBlock(LAPTOP_TERMINAL, false));
        ROWS.put(BOOK_AND_LAMP, gen -> gen.horizontalDirectionBlock(BOOK_AND_LAMP, false));
        ROWS.put(NEO_FORGE, gen -> gen.horizontalDirectionBlock(NEO_FORGE, false));
        ROWS.put(FIREPLACE_HEART, gen -> {});
        ROWS.put(FIREWOOD, gen -> gen.horizontalAxisBlock(FIREWOOD, false));
        ROWS.put(SOUL_CRYSTAL_ORNAMENT, BlockStateGen::soulCrystalOrnament);
        ROWS.put(ITEM_FRAME_SHELL_THIN, gen -> itemFrameShell());
        ROWS.put(ITEM_FRAME_SHELL_BIG, gen -> {});
        ROWS.put(LIGHT_TUBE, gen -> lightTube());
        ROWS.put(LIGHT_PLATE, gen -> lightPlate());
        ROWS.put(HEAVY_CHAINS, gen -> heavyChains());
        ROWS.put(HALF_GRASS_BLOCK, gen -> halfBlock());
        ROWS.put(HALF_DIRT, gen -> {});
        ROWS.put(HALF_DIRT_PATH, gen -> {});
        ROWS.put(HALF_PODZOL, gen -> {});
        ROWS.put(HALF_MYCELIUM, gen -> {});
        ROWS.put(GREEN_FUN_ROOF, gen -> {});

        ROWS.put(RAINBOW_GLASS, gen -> gen.simpleWithModel(RAINBOW_GLASS));

        ROWS.put(A_WALL_FLOWER_POT, gen -> gen.horizontalDirectionBlock(A_WALL_FLOWER_POT, "wall_flower_pot_a", false));
        ROWS.put(B_WALL_FLOWER_POT, gen -> gen.horizontalDirectionBlock(B_WALL_FLOWER_POT, "wall_flower_pot_b", false));
        ROWS.put(C_WALL_FLOWER_POT, gen -> gen.horizontalDirectionBlock(C_WALL_FLOWER_POT, "wall_flower_pot_c", false));
        ROWS.put(D_WALL_FLOWER_POT, gen -> gen.horizontalDirectionBlock(D_WALL_FLOWER_POT, "wall_flower_pot_d", false));
        ROWS.put(E_WALL_FLOWER_POT, gen -> gen.horizontalDirectionBlock(E_WALL_FLOWER_POT, "wall_flower_pot_e", false));
        ROWS.put(F_WALL_FLOWER_POT, gen -> gen.horizontalDirectionBlock(F_WALL_FLOWER_POT, "wall_flower_pot_f", false));

        ROWS.put(OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE, gen -> gen.doubleBlock(OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE, mcLoc("block/oak_planks"), mcLoc("block/light_gray_concrete")));
        ROWS.put(SPRUCE_PLANKS_AND_GRAY_CONCRETE, gen -> gen.doubleBlock(SPRUCE_PLANKS_AND_GRAY_CONCRETE, mcLoc("block/spruce_planks"), mcLoc("block/gray_concrete")));
        ROWS.put(OAK_PLANKS_AND_SPRUCE_PLANKS, gen -> gen.doubleBlock(OAK_PLANKS_AND_SPRUCE_PLANKS, mcLoc("block/oak_planks"), mcLoc("block/spruce_planks")));
        ROWS.put(WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE, gen -> gen.doubleBlock(WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE, mcLoc("block/white_concrete"), mcLoc("block/light_gray_concrete")));
        ROWS.put(DEEPSLATE_TILES_AND_SPRUCE_PLANKS, gen -> gen.doubleBlock(DEEPSLATE_TILES_AND_SPRUCE_PLANKS, mcLoc("block/deepslate_tiles"), mcLoc("block/spruce_planks")));
        ROWS.put(DEEPSLATE_TILES_AND_MANGROVE_PLANKS, gen -> gen.doubleBlock(DEEPSLATE_TILES_AND_MANGROVE_PLANKS, mcLoc("block/deepslate_tiles"), mcLoc("block/mangrove_planks")));
        ROWS.put(DARK_PRISMARINE_AND_SPRUCE_PLANKS, gen -> gen.doubleBlock(DARK_PRISMARINE_AND_SPRUCE_PLANKS, mcLoc("block/dark_prismarine"), mcLoc("block/spruce_planks")));
        ROWS.put(DARK_PRISMARINE_AND_MANGROVE_PLANKS, gen -> gen.doubleBlock(DARK_PRISMARINE_AND_MANGROVE_PLANKS, mcLoc("block/dark_prismarine"), mcLoc("block/mangrove_planks")));
        ROWS.put(BRICKS_AND_BIRCH_PLANKS, gen -> gen.doubleBlock(BRICKS_AND_BIRCH_PLANKS, mcLoc("block/bricks"), mcLoc("block/birch_planks")));

        ROWS.put(FAKE_FURNACE, gen -> gen.horizontalDirectionFakeBlock(FAKE_FURNACE, Blocks.FURNACE, false));
        ROWS.put(LIT_FAKE_FURNACE, gen -> gen.horizontalDirectionFakeBlock(LIT_FAKE_FURNACE, "furnace_on", false));
        ROWS.put(FAKE_BLAST_FURNACE, gen -> gen.horizontalDirectionFakeBlock(FAKE_BLAST_FURNACE, Blocks.BLAST_FURNACE, false));
        ROWS.put(LIT_FAKE_BLAST_FURNACE, gen -> gen.horizontalDirectionFakeBlock(LIT_FAKE_BLAST_FURNACE, "blast_furnace_on", false));
        ROWS.put(FAKE_SMOKER, gen -> gen.horizontalDirectionFakeBlock(FAKE_SMOKER, Blocks.SMOKER, false));
        ROWS.put(LIT_FAKE_SMOKER, gen -> gen.horizontalDirectionFakeBlock(LIT_FAKE_SMOKER, "smoker_on", false));
        ROWS.put(FAKE_CHISELED_BOOKSHELF, gen -> gen.horizontalDirectionBlock(FAKE_CHISELED_BOOKSHELF, false));
        ROWS.put(FAKE_LECTERN, gen -> gen.horizontalDirectionFakeBlock(FAKE_LECTERN, Blocks.LECTERN, false));
        ROWS.put(FAKE_BEEHIVE, gen -> gen.horizontalDirectionFakeBlock(FAKE_BEEHIVE, Blocks.BEEHIVE, false));
        ROWS.put(FAKE_BARREL, gen -> {});
        ROWS.put(FAKE_CAMPFIRE, gen -> {});
        ROWS.put(LIT_FAKE_CAMPFIRE, gen -> {});
        ROWS.put(LIT_FAKE_SOUL_CAMPFIRE, gen -> {});
        ROWS.put(FAKE_CHEST, gen -> {});
        ROWS.put(FAKE_HOPPER, gen -> {});
        ROWS.put(FAKE_DIAMOND_BLOCK, gen -> gen.simpleWithMcModel(FAKE_DIAMOND_BLOCK, "diamond_block"));
        ROWS.put(FAKE_GOLD_BLOCK, gen -> gen.simpleWithMcModel(FAKE_GOLD_BLOCK, "gold_block"));
        ROWS.put(FAKE_IRON_BLOCK, gen -> gen.simpleWithMcModel(FAKE_IRON_BLOCK, "iron_block"));
        ROWS.put(FAKE_NETHERITE_BLOCK, gen -> gen.simpleWithMcModel(FAKE_NETHERITE_BLOCK, "netherite_block"));
        ROWS.put(FAKE_BEDROCK, gen -> gen.simpleWithMcModel(FAKE_BEDROCK, "bedrock"));
        ROWS.put(TEXTURE_CHISELED_BOOKSHELF, gen -> gen.texturedCubeColumn(TEXTURE_CHISELED_BOOKSHELF, "block/chiseled_bookshelf_side", "block/chiseled_bookshelf_top"));
        ROWS.put(TEXTURE_CHISELED_BOOKSHELF_TOP, gen -> gen.texturedCube(TEXTURE_CHISELED_BOOKSHELF_TOP, "block/chiseled_bookshelf_top"));
        ROWS.put(TEXTURE_CHISELED_BOOKSHELF_SIDE, gen -> gen.texturedHorizonAxisCube(TEXTURE_CHISELED_BOOKSHELF_SIDE, "block/chiseled_bookshelf_side"));
        ROWS.put(TEXTURE_LOOM, gen -> gen.texturedHorizonAxisCubeColumn(TEXTURE_LOOM, "block/loom_side", "block/beehive_end"));
        ROWS.put(TEXTURE_BEEHIVE_TOP, gen -> gen.texturedHorizonAxisCube(TEXTURE_BEEHIVE_TOP, "block/beehive_end"));
        ROWS.put(TEXTURE_SMITHING_TABLE_BOTTOM, gen -> gen.texturedCube(TEXTURE_SMITHING_TABLE_BOTTOM, "block/smithing_table_bottom"));
        ROWS.put(TEXTURE_SMITHING_TABLE_TOP, gen -> gen.texturedCube(TEXTURE_SMITHING_TABLE_TOP, "block/smithing_table_top"));
        ROWS.put(TEXTURE_COMPOSTER_BOTTOM, gen -> gen.texturedPillarCube(TEXTURE_COMPOSTER_BOTTOM, "block/composter_bottom"));
        ROWS.put(TEXTURE_BEE_NEST_TOP, gen -> gen.texturedCube(TEXTURE_BEE_NEST_TOP, "block/bee_nest_top"));
        ROWS.put(TEXTURE_FURNACE, gen -> gen.texturedCubeColumn(TEXTURE_FURNACE, "block/furnace_side", "block/furnace_top"));
        ROWS.put(TEXTURE_FURNACE_TOP, gen -> gen.texturedCube(TEXTURE_FURNACE_TOP, "block/furnace_top"));
        ROWS.put(TEXTURE_BLAST_FURNACE, gen -> gen.texturedCubeColumn(TEXTURE_BLAST_FURNACE, "block/blast_furnace_side", "block/blast_furnace_top"));
        ROWS.put(TEXTURE_BLAST_FURNACE_TOP, gen -> gen.texturedCube(TEXTURE_BLAST_FURNACE_TOP, "block/blast_furnace_top"));
        ROWS.put(TEXTURE_SMOKER, gen -> gen.texturedCubeColumn(TEXTURE_SMOKER, "block/smoker_side", "block/smoker_top"));
        ROWS.put(TEXTURE_LODESTONE, gen -> gen.simpleWithMcModel(TEXTURE_LODESTONE, "lodestone"));
        ROWS.put(TEXTURE_LODESTONE_SIDE, gen -> gen.texturedCube(TEXTURE_LODESTONE_SIDE, "block/lodestone_side"));
        ROWS.put(TEXTURE_LODESTONE_TOP, gen -> gen.texturedCube(TEXTURE_LODESTONE_TOP, "block/lodestone_top"));


        FLRegister.BLOCKS.getEntries().forEach(deferredBlock -> {
            if (ROWS.containsKey(deferredBlock)) ROWS.get(deferredBlock).accept(this);
            else simple(deferredBlock);
        });

    }

    public ResourceLocation blockTexture(BlockMaterial material) {
        ResourceLocation rl = BuiltInRegistries.BLOCK.getKey(material.getBlock());
        return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), "block/" + rl.getPath());
    }

    public ResourceLocation blockTexture(BlockMaterial material, String s) {
        ResourceLocation rl = BuiltInRegistries.BLOCK.getKey(material.getBlock());
        return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), "block/" + rl.getPath() + "_" + s);
    }

    public ResourceLocation getSimpleCubeMaterialResource(BlockMaterial material) {
        Block block = material.getBlock();
        if (material.hasTag(BlockMaterialTag.TEXTURE_QUARTZ)) {
            if (block == Blocks.QUARTZ_BLOCK) return quartz_block_side;
            else return quartz_block_bottom;
        }
        else if (material.hasTag(BlockMaterialTag.TEXTURE_SANDSTONE)) {
            if (block == Blocks.SMOOTH_SANDSTONE) return sandstone_top;
            else return red_sandstone_top;
        }
        else return blockTexture(material);
    }

    public ResourceLocation[] getPillarMaterialResource(BlockMaterial material) {
        Block block = material.getBlock();
        if (block == Blocks.QUARTZ_BLOCK) return new ResourceLocation[]{quartz_block_side, quartz_block_side};
        if (block == Blocks.SMOOTH_QUARTZ) return new ResourceLocation[]{quartz_block_bottom, quartz_block_bottom};
        if (block == Blocks.SMOOTH_SANDSTONE) return new ResourceLocation[]{sandstone_top, sandstone_top};
        if (block == Blocks.SMOOTH_RED_SANDSTONE) return new ResourceLocation[]{red_sandstone_top, red_sandstone_top};
        if (material.hasTag(BlockMaterialTag.MODEL_LOG)) return new ResourceLocation[]{blockTexture(material), blockTexture(material, "top")};
        if (material.hasTag(BlockMaterialTag.MODEL_PILLAR)) return new ResourceLocation[]{blockTexture(material, "side"), blockTexture(material, "top")};
        return new ResourceLocation[]{blockTexture(material), blockTexture(material)};
    }

    public ResourceLocation[] getUpDownSideMaterialResource(BlockMaterial material) {
        Block block = material.getBlock();
        if (block == Blocks.SANDSTONE) return new ResourceLocation[]{sandstone_top, sandstone_bottom, sandstone_side};
        if (block == Blocks.CHISELED_SANDSTONE) return new ResourceLocation[]{sandstone_top, sandstone_top, mcLoc("block/chiseled_sandstone")};
        if (block == Blocks.CUT_SANDSTONE) return new ResourceLocation[]{sandstone_top, sandstone_top, mcLoc("block/cut_sandstone")};
        if (block == Blocks.RED_SANDSTONE) return new ResourceLocation[]{red_sandstone_top, red_sandstone_bottom, red_sandstone_side};
        if (block == Blocks.CHISELED_RED_SANDSTONE) return new ResourceLocation[]{red_sandstone_top, red_sandstone_top, mcLoc("block/chiseled_red_sandstone")};
        if (block == Blocks.CUT_RED_SANDSTONE) return new ResourceLocation[]{red_sandstone_top, red_sandstone_top, mcLoc("block/cut_red_sandstone")};
        if (block == Blocks.CRIMSON_NYLIUM) return new ResourceLocation[]{crimson_nylium, netherrack, mcLoc("block/crimson_nylium_side")};
        if (block == Blocks.WARPED_NYLIUM) return new ResourceLocation[]{warped_nylium, netherrack, mcLoc("block/warped_nylium_side")};
        return new ResourceLocation[]{blockTexture(material), blockTexture(material), blockTexture(material)};
    }

    public void pillar(DeferredBlock<Block> deferredBlock, int px, BlockMaterial material) {
        var rls = getPillarMaterialResource(material);
        pillar(deferredBlock, px, rls[0], rls[1]);
    }

    private void pillar(DeferredBlock<Block> deferredBlock, int px,  ResourceLocation side, ResourceLocation end) {
        String path = deferredBlock.getId().getPath();
        ModelFile modelFile = models()
                .withExistingParent(path, modLoc("block/pillar_" + px + "px"))
                .texture("side", side).texture("end", end);
        yAxisModel(deferredBlock.get(), path, modelFile, false);
    }

    public void pillarConnector(DeferredBlock<Block> deferredBlock, int px, BlockMaterial material) {
        var rls = getPillarMaterialResource(material);
        pillarConnector(deferredBlock, px, rls[0], rls[1]);
    }

    private void pillarConnector(DeferredBlock<Block> deferredBlock, int px, ResourceLocation side, ResourceLocation end) {
        String path = deferredBlock.getId().getPath();
        ModelFile modelCore = models()
                .withExistingParent(path + "_core", modLoc("block/pillar_connector_" + px + "px_core"))
                .texture("all", end);
        ModelFile modelFront = models()
                .withExistingParent(path + "_front", modLoc("block/pillar_connector_" + px + "px_front"))
                .texture("side", side).texture("end", end);
        ModelFile modelBack = models()
                .withExistingParent(path + "_back", modLoc("block/pillar_connector_" + px + "px_back"))
                .texture("side", side).texture("end", end);
        ModelFile modelInv = models()
                .withExistingParent(path + "_inv", modLoc("block/pillar_connector_" + px + "px_inv"))
                .texture("side", side).texture("end", end);
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

    public void horizonPanel(DeferredBlock<Block> deferredBlock, BlockMaterial material) {
        simpleWithModel(deferredBlock, models().withExistingParent(deferredBlock.getId().getPath(), modLoc("block/translucent_panel_horizon"))
                                               .texture("side", blockTexture(material, "pane_top"))
                                               .texture("end", blockTexture(material))
        );
    }

    private void soulCrystalOrnament() {
        getMultipartBuilder(SOUL_CRYSTAL_ORNAMENT.get())
                .part().modelFile(models().getExistingFile(modLoc("block/soul_crystal_ornament_a"))).addModel().end()
                .part().modelFile(models().getExistingFile(modLoc("block/soul_crystal_ornament_b"))).addModel().end();
        itemModels().withExistingParent(SOUL_CRYSTAL_ORNAMENT.getId().getPath(), modLoc("block/soul_crystal_ornament_inv"));
    }

    private void itemFrameShell() {
        directionBlock(ITEM_FRAME_SHELL_THIN, "item_frame_shell_thin", true);
        simple(
                ITEM_FRAME_SHELL_BIG.get(),
                ITEM_FRAME_SHELL_BIG.getId().getPath(),
                models().getExistingFile(modLoc("block/custom_glass"))
        );
    }

    private void halfBlock() {

        ModelFile modelGrass = models().getExistingFile(modLoc("block/half_grass_block"));
        ModelFile modelPodzol = models().withExistingParent(HALF_PODZOL.getId().getPath(), modLoc("block/half_grass_block"))
                                        .texture("side", mcLoc("block/podzol_side"))
                                        .texture("top", mcLoc("block/podzol_top"))
                                        .texture("bottom", mcLoc("block/dirt"));
        ModelFile modelMycelium = models().withExistingParent(HALF_MYCELIUM.getId().getPath(), modLoc("block/half_grass_block"))
                                        .texture("side", mcLoc("block/mycelium_side"))
                                        .texture("top", mcLoc("block/mycelium_top"))
                                        .texture("bottom", mcLoc("block/dirt"));
        ModelFile modelDirt = models().withExistingParent(HALF_DIRT.getId().getPath(), modLoc("block/half_grass_block"))
                                          .texture("side", mcLoc("block/dirt"))
                                          .texture("top", mcLoc("block/dirt"))
                                          .texture("bottom", mcLoc("block/dirt"));
        ModelFile modelDirt2 = models().withExistingParent(HALF_DIRT.getId().getPath() + "_2", modLoc("block/half_grass_block_snow"))
                                      .texture("side", mcLoc("block/dirt"));
        ModelFile modelOvl = models().getExistingFile(modLoc("block/half_grass_block_overlay"));
        ModelFile modelSnow = models().getExistingFile(modLoc("block/half_grass_block_snow"));
        ModelFile layer1 = models().getExistingFile(modLoc("block/half_snow_height2"));
        ModelFile layer2 = models().getExistingFile(modLoc("block/half_snow_height4"));
        ModelFile layer3 = models().getExistingFile(modLoc("block/half_snow_height6"));
        ModelFile layer4 = models().getExistingFile(modLoc("block/half_snow_height8"));

        getMultipartBuilder(HALF_GRASS_BLOCK.get())
                .part()
                .modelFile(modelGrass).nextModel()
                .modelFile(modelGrass).rotationY(90).nextModel()
                .modelFile(modelGrass).rotationY(180).nextModel()
                .modelFile(modelGrass).rotationY(270).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 0).end()

                .part().modelFile(modelOvl).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 0).end()

                .part()
                .modelFile(modelSnow).nextModel()
                .modelFile(modelSnow).rotationY(90).nextModel()
                .modelFile(modelSnow).rotationY(180).nextModel()
                .modelFile(modelSnow).rotationY(270).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 1, 2, 3, 4).end()

                .part()
                .modelFile(layer1).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 1).end()

                .part()
                .modelFile(layer2).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 2).end()

                .part()
                .modelFile(layer3).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 3).end()

                .part()
                .modelFile(layer4).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 4).end();

        itemModels().withExistingParent(HALF_GRASS_BLOCK.getId().getPath(), modLoc("block/half_grass_block_inv"));


        getMultipartBuilder(HALF_PODZOL.get())
                .part()
                .modelFile(modelPodzol).nextModel()
                .modelFile(modelPodzol).rotationY(90).nextModel()
                .modelFile(modelPodzol).rotationY(180).nextModel()
                .modelFile(modelPodzol).rotationY(270).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 0).end()

                .part()
                .modelFile(modelSnow).nextModel()
                .modelFile(modelSnow).rotationY(90).nextModel()
                .modelFile(modelSnow).rotationY(180).nextModel()
                .modelFile(modelSnow).rotationY(270).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 1, 2, 3, 4).end()

                .part()
                .modelFile(layer1).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 1).end()

                .part()
                .modelFile(layer2).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 2).end()

                .part()
                .modelFile(layer3).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 3).end()

                .part()
                .modelFile(layer4).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 4).end();

        itemModels().getBuilder(HALF_PODZOL.getId().getPath()).parent(modelPodzol);


        getMultipartBuilder(HALF_MYCELIUM.get())
                .part()
                .modelFile(modelMycelium).nextModel()
                .modelFile(modelMycelium).rotationY(90).nextModel()
                .modelFile(modelMycelium).rotationY(180).nextModel()
                .modelFile(modelMycelium).rotationY(270).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 0).end()

                .part()
                .modelFile(modelSnow).nextModel()
                .modelFile(modelSnow).rotationY(90).nextModel()
                .modelFile(modelSnow).rotationY(180).nextModel()
                .modelFile(modelSnow).rotationY(270).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 1, 2, 3, 4).end()

                .part()
                .modelFile(layer1).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 1).end()

                .part()
                .modelFile(layer2).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 2).end()

                .part()
                .modelFile(layer3).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 3).end()

                .part()
                .modelFile(layer4).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 4).end();

        itemModels().getBuilder(HALF_MYCELIUM.getId().getPath()).parent(modelMycelium);


        getMultipartBuilder(HALF_DIRT.get())
                .part()
                .modelFile(modelDirt).nextModel()
                .modelFile(modelDirt).rotationY(90).nextModel()
                .modelFile(modelDirt).rotationY(180).nextModel()
                .modelFile(modelDirt).rotationY(270).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 0).end()

                .part()
                .modelFile(modelDirt2).nextModel()
                .modelFile(modelDirt2).rotationY(90).nextModel()
                .modelFile(modelDirt2).rotationY(180).nextModel()
                .modelFile(modelDirt2).rotationY(270).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 1, 2, 3, 4).end()

                .part()
                .modelFile(layer1).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 1).end()

                .part()
                .modelFile(layer2).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 2).end()

                .part()
                .modelFile(layer3).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 3).end()

                .part()
                .modelFile(layer4).addModel()
                .condition(FLBlockStateProperties.HALF_LAYERS, 4).end();

        itemModels().getBuilder(HALF_DIRT.getId().getPath()).parent(modelDirt);


        ModelFile modelPath = models().getExistingFile(modLoc("block/half_dirt_path"));

        getVariantBuilder(HALF_DIRT_PATH.get())
                .partialState().modelForState()
                .modelFile(modelPath).nextModel()
                .modelFile(modelPath).rotationY(90).nextModel()
                .modelFile(modelPath).rotationY(180).nextModel()
                .modelFile(modelPath).rotationY(270).addModel();
        itemModels().withExistingParent(HALF_DIRT_PATH.getId().getPath(), modLoc("block/half_dirt_path"));
    }

    private void lightTube() {
        ModelFile h = models().getExistingFile(modLoc("block/light_tube_horizontal"));
        ModelFile v = models().getExistingFile(modLoc("block/light_tube_vertical"));
        getVariantBuilder(LIGHT_TUBE.get())
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
        itemModels().getBuilder(LIGHT_TUBE.getId().getPath()).parent(h);
    }

    private void lightPlate() {
        ModelFile s = models().getExistingFile(modLoc("block/light_plate_side"));
        ModelFile b = models().getExistingFile(modLoc("block/light_plate_up"));
        getVariantBuilder(LIGHT_PLATE.get())
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
        itemModels().getBuilder(LIGHT_PLATE.getId().getPath()).parent(s);
    }

    private void heavyChains() {
        ModelFile modelFile = models().getExistingFile(modLoc("block/heavy_chains"));
        ModelFile modelFile2 = models().getExistingFile(modLoc("block/heavy_chains_mirror"));
        getVariantBuilder(HEAVY_CHAINS.get())
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
        itemModels().getBuilder(HEAVY_CHAINS.getId().getPath()).parent(modelFile);
    }

    private void doubleBlock(DeferredBlock<Block> deferredBlock, ResourceLocation materialTop, ResourceLocation materialBottom) {
        String path = deferredBlock.getId().getPath();
        ModelFile modelFile = models().withExistingParent(path, modLoc("block/double_block"))
                                      .texture("top", materialTop)
                                      .texture("bottom", materialBottom);
        simple(deferredBlock.get(), path, modelFile);
    }

    public void stoneGuardrail(DeferredBlock<Block> deferredBlock, BlockMaterial material) {
        if (material.hasTag(BlockMaterialTag.MODEL_CUBE)) guardrail(
                deferredBlock,
                getSimpleCubeMaterialResource(material),
                material.hasTag(BlockMaterialTag.TEXTURE_FRAMED) ? "guardrail_frame" : "guardrail_rock_all"
        );
        else if (material.hasAnyTags(BlockMaterialTag.MODEL_PILLAR, BlockMaterialTag.MODEL_LOG)) {
            var rls = getPillarMaterialResource(material);
            pillarGuardrail(deferredBlock, rls[0], rls[1]);
        }
        else if (material.hasTag(BlockMaterialTag.MODEL_UP_DOWN_SIDE)) {
            var rls = getUpDownSideMaterialResource(material);
            upDownSideGuardrail(deferredBlock, rls[0], rls[1], rls[2]);
        }
    }

    public void guardrail(DeferredBlock<Block> deferredBlock, ResourceLocation texture, String modelName) {
        String path = deferredBlock.getId().getPath();
        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/" + modelName)))
                                          .texture("all", texture);
        guardrailStates(
                deferredBlock.get(),
                model,
                models().getBuilder(path + "_lower")
                        .parent(models().getExistingFile(modLoc("block/" + modelName + "_lower")))
                        .texture("all", texture),
                models().getBuilder(path + "_inner")
                        .parent(models().getExistingFile(modLoc("block/" + modelName + "_inner")))
                        .texture("all", texture),
                models().getBuilder(path + "_inner_lower")
                        .parent(models().getExistingFile(modLoc("block/" + modelName + "_inner_lower")))
                        .texture("all", texture),
                models().getBuilder(path + "_outer")
                        .parent(models().getExistingFile(modLoc("block/" + modelName + "_outer")))
                        .texture("all", texture),
                models().getBuilder(path + "_outer_lower")
                        .parent(models().getExistingFile(modLoc("block/" + modelName + "_outer_lower")))
                        .texture("all", texture)
        );
        itemModels().getBuilder(path).parent(model);
    }

    private void pillarGuardrail(DeferredBlock<Block> deferredBlock, ResourceLocation side, ResourceLocation end) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();
        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/guardrail_pillar")))
                                          .texture("side", side)
                                          .texture("top", end)
                                          .texture("particle", side);
        guardrailStates(
                block,
                model,
                models().getBuilder(path + "_lower")
                        .parent(models().getExistingFile(modLoc("block/guardrail_pillar_lower")))
                        .texture("side", side)
                        .texture("top", end)
                        .texture("particle", side),
                models().getBuilder(path + "_inner_left")
                        .parent(models().getExistingFile(modLoc("block/guardrail_pillar_inner_left")))
                        .texture("side", side)
                        .texture("top", end)
                        .texture("particle", side),
                models().getBuilder(path + "_inner_left_lower")
                        .parent(models().getExistingFile(modLoc("block/guardrail_pillar_inner_left_lower")))
                        .texture("side", side)
                        .texture("top", end)
                        .texture("particle", side),
                models().getBuilder(path + "_inner_right")
                        .parent(models().getExistingFile(modLoc("block/guardrail_pillar_inner_right")))
                        .texture("side", side)
                        .texture("top", end)
                        .texture("particle", side),
                models().getBuilder(path + "_inner_right_lower")
                        .parent(models().getExistingFile(modLoc("block/guardrail_pillar_inner_right_lower")))
                        .texture("side", side)
                        .texture("top", end)
                        .texture("particle", side),
                models().getBuilder(path + "_outer_left")
                        .parent(models().getExistingFile(modLoc("block/guardrail_pillar_outer_left")))
                        .texture("side", side)
                        .texture("top", end)
                        .texture("particle", side),
                models().getBuilder(path + "_outer_left_lower")
                        .parent(models().getExistingFile(modLoc("block/guardrail_pillar_outer_left_lower")))
                        .texture("side", side)
                        .texture("top", end)
                        .texture("particle", side),
                models().getBuilder(path + "_outer_right")
                        .parent(models().getExistingFile(modLoc("block/guardrail_pillar_outer_right")))
                        .texture("side", side)
                        .texture("top", end)
                        .texture("particle", side),
                models().getBuilder(path + "_outer_right_lower")
                        .parent(models().getExistingFile(modLoc("block/guardrail_pillar_outer_right_lower")))
                        .texture("side", side)
                        .texture("top", end)
                        .texture("particle", side)
        );
        itemModels().getBuilder(path).parent(model);
    }

    public void glassGuardrail(DeferredBlock<Block> deferredBlock, ResourceLocation glass) {
        String path = deferredBlock.getId().getPath();
        BlockModelBuilder model = models().withExistingParent(path, modLoc("block/guardrail_translucent"))
                                          .texture("particle", blackIron)
                                          .customLoader(CompositeModelBuilder::begin)
                                          .child("part_a", models().nested().parent(models().getExistingFile(modLoc("block/guardrail_translucent_a"))))
                                          .child("part_b", models().nested().parent(models().getExistingFile(modLoc("block/guardrail_translucent_b"))).texture("glass", glass))
                                          .end();
        guardrailStates(
                deferredBlock.get(),
                model,
                models().withExistingParent(path + "_lower", mcLoc("block/block"))
                        .texture("particle", blackIron)
                        .customLoader(CompositeModelBuilder::begin)
                        .child("part_a", models().nested().parent(models().getExistingFile(modLoc("block/guardrail_translucent_lower_a"))))
                        .child("part_b", models().nested().parent(models().getExistingFile(modLoc("block/guardrail_translucent_lower_b"))).texture("glass", glass))
                        .end(),
                models().withExistingParent(path + "_inner", mcLoc("block/block"))
                        .texture("particle", blackIron)
                        .customLoader(CompositeModelBuilder::begin)
                        .child("part_a", models().nested().parent(models().getExistingFile(modLoc("block/guardrail_translucent_inner_a"))))
                        .child("part_b", models().nested().parent(models().getExistingFile(modLoc("block/guardrail_translucent_inner_b"))).texture("glass", glass))
                        .end(),
                models().withExistingParent(path + "_inner_lower", mcLoc("block/block"))
                        .texture("particle", blackIron)
                        .customLoader(CompositeModelBuilder::begin)
                        .child("part_a", models().nested().parent(models().getExistingFile(modLoc("block/guardrail_translucent_inner_lower_a"))))
                        .child("part_b", models().nested().parent(models().getExistingFile(modLoc("block/guardrail_translucent_inner_lower_b"))).texture("glass", glass))
                        .end(),
                models().getExistingFile(modLoc("block/guardrail_translucent_outer")),
                models().getExistingFile(modLoc("block/guardrail_translucent_outer_lower"))
        );
        itemModels().getBuilder(path).parent(model);
    }

    private void upDownSideGuardrail(DeferredBlock<Block> deferredBlock, ResourceLocation up, ResourceLocation down, ResourceLocation side) {
        String path = deferredBlock.getId().getPath();
        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/guardrail_rock")))
                                          .texture("side", side)
                                          .texture("top", up)
                                          .texture("bottom", down)
                                          .texture("particle", down);
        guardrailStates(
                deferredBlock.get(),
                model,
                models().getBuilder(path + "_lower")
                        .parent(models().getExistingFile(modLoc("block/guardrail_rock_lower")))
                        .texture("side", side)
                        .texture("top", up)
                        .texture("bottom", down)
                        .texture("particle", down),
                models().getBuilder(path + "_inner")
                        .parent(models().getExistingFile(modLoc("block/guardrail_rock_inner")))
                        .texture("side", side)
                        .texture("top", up)
                        .texture("bottom", down)
                        .texture("particle", down),
                models().getBuilder(path + "_inner_lower")
                        .parent(models().getExistingFile(modLoc("block/guardrail_rock_inner_lower")))
                        .texture("side", side)
                        .texture("top", up)
                        .texture("bottom", down)
                        .texture("particle", down),
                models().getBuilder(path + "_outer")
                        .parent(models().getExistingFile(modLoc("block/guardrail_rock_outer")))
                        .texture("side", side)
                        .texture("top", up)
                        .texture("bottom", down)
                        .texture("particle", down),
                models().getBuilder(path + "_outer_lower")
                        .parent(models().getExistingFile(modLoc("block/guardrail_rock_outer_lower")))
                        .texture("side", side)
                        .texture("top", up)
                        .texture("bottom", down)
                        .texture("particle", down)
        );
        itemModels().getBuilder(path).parent(model);
    }

    private void guardrailStates(
            Block block,
            ModelFile model,
            ModelFile lower,
            ModelFile inner,
            ModelFile innerLower,
            ModelFile outer,
            ModelFile outerLower
    ) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            Half half = state.getValue(BlockStateProperties.HALF);
            StairsShape shape = state.getValue(BlockStateProperties.STAIRS_SHAPE);
            int yRot = (int) facing.getOpposite().toYRot();
            if (shape == StairsShape.INNER_LEFT) yRot += 270;
            else if (shape == StairsShape.OUTER_RIGHT) yRot += 90;
            yRot %= 360;
            boolean uvlock = yRot != 0;
            return ConfiguredModel.builder()
                                  .modelFile(
                                          half == Half.TOP
                                          ? switch (shape){
                                              case STRAIGHT -> model;
                                              case INNER_LEFT, INNER_RIGHT -> inner;
                                              case OUTER_LEFT, OUTER_RIGHT -> outer;
                                          } : switch (shape){
                                              case STRAIGHT -> lower;
                                              case INNER_LEFT, INNER_RIGHT -> innerLower;
                                              case OUTER_LEFT, OUTER_RIGHT -> outerLower;
                                          }
                                  )
                                  .rotationY(yRot)
                                  .uvLock(uvlock)
                                  .build();
        }, BlockStateProperties.WATERLOGGED);
    }

    private void guardrailStates(
            Block block,
            ModelFile model,
            ModelFile lower,
            ModelFile innerLeft,
            ModelFile innerLeftLower,
            ModelFile innerRight,
            ModelFile innerRightLower,
            ModelFile outerLeft,
            ModelFile outerLeftLower,
            ModelFile outerRight,
            ModelFile outerRightLower
    ) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            Half half = state.getValue(BlockStateProperties.HALF);
            StairsShape shape = state.getValue(BlockStateProperties.STAIRS_SHAPE);
            return ConfiguredModel.builder()
                                  .modelFile(
                                          half == Half.TOP
                                          ? switch (shape){
                                              case STRAIGHT -> model;
                                              case INNER_LEFT -> innerLeft;
                                              case INNER_RIGHT -> innerRight;
                                              case OUTER_LEFT -> outerLeft;
                                              case OUTER_RIGHT -> outerRight;
                                          } : switch (shape){
                                              case STRAIGHT -> lower;
                                              case INNER_LEFT -> innerLeftLower;
                                              case INNER_RIGHT -> innerRightLower;
                                              case OUTER_LEFT -> outerLeftLower;
                                              case OUTER_RIGHT -> outerRightLower;
                                          }
                                  )
                                  .rotationY((int) facing.getOpposite().toYRot())
                                  .build();
        }, BlockStateProperties.WATERLOGGED);
    }

    public void simpleTintCube(DeferredBlock<? extends Block> deferredBlock, BlockMaterial material) {
        String path = deferredBlock.getId().getPath();
        ModelFile model = models().getBuilder(path).parent(models().getExistingFile(modLoc("block/cube_tint_all"))).texture("all", blockTexture(material));
        simple(deferredBlock.get(), path, model);
    }

    public void glassLamp(DeferredBlock<? extends Block> deferredBlock, BlockMaterial material) {
        glassLamp(deferredBlock, material, false);
    }

    public void glassLamp(DeferredBlock<? extends Block> deferredBlock, BlockMaterial material, boolean tint) {
        String path = deferredBlock.getId().getPath();
        String s = tint ? "_tint" : "";
        simple(
                deferredBlock.get(), path,
                models().withExistingParent(path, mcLoc("block/block"))
                        .texture("particle", glass)
                        .customLoader(CompositeModelBuilder::begin)
                        .child("part_a", models().nested().parent(models().getExistingFile(glass)).renderType("translucent"))
                        .child("part_b", material.hasTag(BlockMaterialTag.MODEL_PILLAR)
                                         ? models().nested().parent(models().getExistingFile(modLoc("block/glass_lamp" + s)))
                                                   .texture("lamp_side", blockTexture(material, "side"))
                                                   .texture("lamp_top", blockTexture(material, "top"))
                                                   .texture("lamp_bottom", blockTexture(material, "top"))
                                         : models().nested().parent(models().getExistingFile(modLoc("block/glass_lamp" + s +"_all")))
                                                   .texture("all", blockTexture(material))
                        )
                        .end()
        );
    }

    public void tintSeaLantern(DeferredBlock<? extends Block> deferredBlock) {
        if (tint_sea_lantern != null) simple(deferredBlock.get(), deferredBlock.getId().getPath(), tint_sea_lantern);
    }

    public void tintSeaLanternGlassLamp(DeferredBlock<? extends Block> deferredBlock) {
        if (tint_sea_lantern_glass_lamp != null) simple(deferredBlock.get(), deferredBlock.getId().getPath(), tint_sea_lantern_glass_lamp);
    }

    public void tintReinforcedSeaLantern(DeferredBlock<? extends Block> deferredBlock) {
        if (tint_reinforced_sea_lantern != null) simple(deferredBlock.get(), deferredBlock.getId().getPath(), tint_reinforced_sea_lantern);
    }

    public void windHole(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();
        zAxisModel(
                deferredBlock.get(), path,
                models().withExistingParent(path, modLoc("block/wind_hole"))
                        .texture("all", material),
                true
        );
    }

    public void glassWindow(DeferredBlock<Block> deferredBlock) {
        String path = deferredBlock.getId().getPath();

        var builder = getVariantBuilder(deferredBlock.get());

        builder = glassWindowStatePart(builder, Direction.NORTH, 0, path);
        builder = glassWindowStatePart(builder, Direction.SOUTH, 180, path);
        builder = glassWindowStatePart(builder, Direction.WEST, 270, path);
        glassWindowStatePart(builder, Direction.EAST, 90, path);

        itemModels().withExistingParent(path, modLoc("block/" + path + "_single"));
    }

    private VariantBlockStateBuilder glassWindowStatePart(
            VariantBlockStateBuilder builder,
            Direction direction,
            int rot,
            String path
    ) {
        ResourceLocation side = modLoc("block/" + path + "_side");
        ResourceLocation end = modLoc("block/" + path + "_end");
        builder = glassWindowStatePart2(builder, direction, rot, "", path + "_single", side, end, TallBlockType.SINGLE);
        builder = glassWindowStatePart2(builder, direction, rot, "_bottom", path + "_bottom", side, end, TallBlockType.BOTTOM);
        builder = glassWindowStatePart2(builder, direction, rot, "_center", path + "_center", side, end, TallBlockType.CENTER);
        return glassWindowStatePart2(builder, direction, rot, "_top", path + "_top", side, end, TallBlockType.TOP);
    }

    private VariantBlockStateBuilder glassWindowStatePart2(
            VariantBlockStateBuilder builder,
            Direction direction,
            int rot,
            String parentPath,
            String path,
            ResourceLocation side,
            ResourceLocation end,
            TallBlockType type
    ) {
        ResourceLocation frontR = modLoc("block/" + path);
        ModelFile model = models().withExistingParent(path, modLoc("block/glass_window" + parentPath))
                                  .texture("front", frontR)
                                  .texture("side", side)
                                  .texture("end", end);
        ModelFile model_open = models().withExistingParent(path + "_open", modLoc("block/glass_window_open" + parentPath))
                                       .texture("front", frontR)
                                       .texture("side", side)
                                       .texture("end", end);
        ModelFile model_mirror = models().withExistingParent(path + "_mirror", modLoc("block/glass_window_mirror" + parentPath))
                                         .texture("front", frontR)
                                         .texture("side", side)
                                         .texture("end", end);
        ModelFile model_open_mirror = models().withExistingParent(path + "_open_mirror", modLoc("block/glass_window_open_mirror" + parentPath))
                                              .texture("front", frontR)
                                              .texture("side", side)
                                              .texture("end", end);

        ConfiguredModel.Builder<VariantBlockStateBuilder> modelFile = builder
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                .with(BlockStateProperties.DOOR_HINGE, DoorHingeSide.LEFT)
                .with(FLBlockStateProperties.OPEN_TYPE, OpenType.CLOSE)
                .with(FLBlockStateProperties.TALL_BLOCK_TYPE, type)
                .modelForState().modelFile(model);
        if (rot > 0) modelFile = modelFile.rotationY(rot);
        modelFile = modelFile.addModel()
             .partialState()
             .with(BlockStateProperties.HORIZONTAL_FACING, direction)
             .with(BlockStateProperties.DOOR_HINGE, DoorHingeSide.LEFT)
             .with(FLBlockStateProperties.OPEN_TYPE, OpenType.FORCE_CLOSE)
             .with(FLBlockStateProperties.TALL_BLOCK_TYPE, type)
             .modelForState().modelFile(model);
        if (rot > 0) modelFile = modelFile.rotationY(rot);
        modelFile = modelFile.addModel()
             .partialState()
             .with(BlockStateProperties.HORIZONTAL_FACING, direction)
             .with(BlockStateProperties.DOOR_HINGE, DoorHingeSide.LEFT)
             .with(FLBlockStateProperties.OPEN_TYPE, OpenType.FORCE_OPEN)
             .with(FLBlockStateProperties.TALL_BLOCK_TYPE, type)
             .modelForState().modelFile(model_open);
        if (rot > 0) modelFile = modelFile.rotationY(rot);
        modelFile = modelFile.addModel()
             .partialState()
             .with(BlockStateProperties.HORIZONTAL_FACING, direction)
             .with(BlockStateProperties.DOOR_HINGE, DoorHingeSide.LEFT)
             .with(FLBlockStateProperties.OPEN_TYPE, OpenType.OPEN)
             .with(FLBlockStateProperties.TALL_BLOCK_TYPE, type)
             .modelForState().modelFile(model_open);
        if (rot > 0) modelFile = modelFile.rotationY(rot);
        modelFile = modelFile.addModel()
             .partialState()
             .with(BlockStateProperties.HORIZONTAL_FACING, direction)
             .with(BlockStateProperties.DOOR_HINGE, DoorHingeSide.RIGHT)
             .with(FLBlockStateProperties.OPEN_TYPE, OpenType.CLOSE)
             .with(FLBlockStateProperties.TALL_BLOCK_TYPE, type)
             .modelForState().modelFile(model_mirror);
        if (rot > 0) modelFile = modelFile.rotationY(rot);
        modelFile = modelFile.addModel()
             .partialState()
             .with(BlockStateProperties.HORIZONTAL_FACING, direction)
             .with(BlockStateProperties.DOOR_HINGE, DoorHingeSide.RIGHT)
             .with(FLBlockStateProperties.OPEN_TYPE, OpenType.FORCE_CLOSE)
             .with(FLBlockStateProperties.TALL_BLOCK_TYPE, type)
             .modelForState().modelFile(model_mirror);
        if (rot > 0) modelFile = modelFile.rotationY(rot);
        modelFile = modelFile.addModel()
             .partialState()
             .with(BlockStateProperties.HORIZONTAL_FACING, direction)
             .with(BlockStateProperties.DOOR_HINGE, DoorHingeSide.RIGHT)
             .with(FLBlockStateProperties.OPEN_TYPE, OpenType.OPEN)
             .with(FLBlockStateProperties.TALL_BLOCK_TYPE, type)
             .modelForState().modelFile(model_open_mirror);
        if (rot > 0) modelFile = modelFile.rotationY(rot);
        modelFile = modelFile.addModel()
             .partialState()
             .with(BlockStateProperties.HORIZONTAL_FACING, direction)
             .with(BlockStateProperties.DOOR_HINGE, DoorHingeSide.RIGHT)
             .with(FLBlockStateProperties.OPEN_TYPE, OpenType.FORCE_OPEN)
             .with(FLBlockStateProperties.TALL_BLOCK_TYPE, type)
             .modelForState().modelFile(model_open_mirror);
        if (rot > 0) modelFile = modelFile.rotationY(rot);
        return modelFile.addModel();
    }

    public void table(DeferredBlock<Block> deferredBlock, BlockMaterial material) {
        if (material.hasTag(BlockMaterialTag.TEXTURE_TRANSLUCENT)) tableGlass(deferredBlock, blockTexture(material));
        else table(deferredBlock, getSimpleCubeMaterialResource(material));
    }

    private void table(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();
        BlockModelBuilder modelLeg = models()
                .withExistingParent(path + "_leg", modLoc("block/table_leg_all"))
                .texture("all", material);
        tableState(
                deferredBlock.get(),
                models().withExistingParent(path, modLoc("block/table_all"))
                        .texture("all", material),
                modelLeg
        );
        itemModels().getBuilder(path).parent(modelLeg);
    }

    private void tableGlass(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();
        BlockModelBuilder modelLeg = models()
                .withExistingParent(path + "_leg", mcLoc("block/block"))
                .texture("particle", material)
                .customLoader(CompositeModelBuilder::begin)
                .child("part_a", models().nested().parent(models().getExistingFile(modLoc("block/table_glass"))).texture("all", material))
                .child("part_b", models().nested().parent(models().getExistingFile(modLoc("block/table_leg_iron"))))
                .end();
        tableState(
                deferredBlock.get(),
                models().withExistingParent(path, modLoc("block/table_glass"))
                        .texture("all", material),
                modelLeg
        );
        itemModels().getBuilder(path).parent(modelLeg);
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
                .with(TableBlock.LEGGED, false).modelForState()
                .modelFile(model).addModel()
                .partialState()
                .with(TableBlock.LEGGED, true).modelForState()
                .modelFile(modelLeg).addModel();
    }

    private void tableGlassState(Block block, ModelFile model) {
        getMultipartBuilder(block)
                .part()
                .modelFile(model).addModel().end()
                .part()
                .modelFile(models().getExistingFile(modLoc("block/table_leg_iron"))).addModel()
                .condition(TableBlock.LEGGED, true).end();
    }

    public void cabinetA(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();

        ModelFile modelS = models()
                .withExistingParent(path, modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_single"));
        ModelFile modelT = models()
                .withExistingParent(path + "_top", modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_top"));
        ModelFile modelB = models()
                .withExistingParent(path + "_bottom", modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_bottom"));

        getVariantBuilder(deferredBlock.get())
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.SINGLE)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .modelForState().modelFile(modelS).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.TOP)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .modelForState().modelFile(modelT).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.BOTTOM)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .modelForState().modelFile(modelB).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.SINGLE)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .modelForState().modelFile(modelS).rotationY(180).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.TOP)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .modelForState().modelFile(modelT).rotationY(180).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.BOTTOM)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .modelForState().modelFile(modelB).rotationY(180).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.SINGLE)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .modelForState().modelFile(modelS).rotationY(90).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.TOP)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .modelForState().modelFile(modelT).rotationY(90).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.BOTTOM)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .modelForState().modelFile(modelB).rotationY(90).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.SINGLE)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .modelForState().modelFile(modelS).rotationY(270).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.TOP)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .modelForState().modelFile(modelT).rotationY(270).addModel()
                .partialState()
                .with(FLBlockStateProperties.CABINET_TYPE_A, ComplexCabinetType.BOTTOM)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .modelForState().modelFile(modelB).rotationY(270).addModel();
        itemModels().getBuilder(path).parent(modelS);
    }

    public void cabinetB(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();

        ModelFile modelS = models()
                .withExistingParent(path, modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_single"));
        ModelFile modelSM = models()
                .withExistingParent(path + "_mirror", modLoc("block/cabinet_mirror"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_single"));
        ModelFile modelT = models()
                .withExistingParent(path + "_top", modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_top"));
        ModelFile modelTM = models()
                .withExistingParent(path + "_top_mirror", modLoc("block/cabinet_mirror"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_top"));
        ModelFile modelB = models()
                .withExistingParent(path + "_bottom", modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_bottom"));
        ModelFile modelBM = models()
                .withExistingParent(path + "_bottom_mirror", modLoc("block/cabinet_mirror"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_bottom"));
        ModelFile modelL = models()
                .withExistingParent(path + "_left", modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_left"));
        ModelFile modelR = models()
                .withExistingParent(path + "_right", modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_right"));


        var builder = getVariantBuilder(deferredBlock.get());
        for (Direction direction : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()) {
            builder.partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE, ComplexCabinetType.SINGLE)
                   .modelForState().modelFile(modelS).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel()
                   .partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE, ComplexCabinetType.MIRROR)
                   .modelForState().modelFile(modelSM).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel()
                   .partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE, ComplexCabinetType.TOP)
                   .modelForState().modelFile(modelT).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel()
                   .partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE, ComplexCabinetType.TOP_MIRROR)
                   .modelForState().modelFile(modelTM).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel()
                   .partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE, ComplexCabinetType.BOTTOM)
                   .modelForState().modelFile(modelB).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel()
                   .partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE, ComplexCabinetType.BOTTOM_MIRROR)
                   .modelForState().modelFile(modelBM).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel()
                   .partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE, ComplexCabinetType.LEFT)
                   .modelForState().modelFile(modelL).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel()
                   .partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE, ComplexCabinetType.RIGHT)
                   .modelForState().modelFile(modelR).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel();
        }
        itemModels().getBuilder(path).parent(modelS);
    }

    public void cabinetC(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();

        ModelFile modelS = models()
                .withExistingParent(path, modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_single"));
        ModelFile modelL = models()
                .withExistingParent(path + "_left", modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_left"));
        ModelFile modelR = models()
                .withExistingParent(path + "_right", modLoc("block/cabinet"))
                .texture("all", material)
                .texture("front", modLoc("block/" + path + "_right"));

        var builder = getVariantBuilder(deferredBlock.get());
        for (Direction direction : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()) {
            builder.partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE_C, ComplexCabinetType.SINGLE)
                   .modelForState().modelFile(modelS).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel()
                   .partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE_C, ComplexCabinetType.LEFT)
                   .modelForState().modelFile(modelL).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel()
                   .partialState()
                   .with(BlockStateProperties.HORIZONTAL_FACING, direction)
                   .with(FLBlockStateProperties.CABINET_TYPE_C, ComplexCabinetType.RIGHT)
                   .modelForState().modelFile(modelR).rotationY(YROT_MAP.getOrDefault(direction, 0)).addModel();
        }
        itemModels().getBuilder(path).parent(modelS);
    }

    public void cabinetD(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();
        horizontalDirectionModel(
                deferredBlock.get(),
                path,
                models().withExistingParent(path, modLoc("block/cabinet"))
                        .texture("all", material)
                        .texture("front", modLoc("block/" + path)),
                false
        );
    }

    public void chair(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();

        horizontalDirectionModel(
                deferredBlock.get(),
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

    public void gardenChair(DeferredBlock<Block> deferredBlock, BlockMaterial material) {
        if (material.hasTag(BlockMaterialTag.TOOL_AXE)) woodenGardenChair(deferredBlock, blockTexture(material));
        else stoneGardenChair(deferredBlock, getSimpleCubeMaterialResource(material));
    }

    private void woodenGardenChair(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        ModelFile modelSingle = models().withExistingParent(path + "_single", modLoc("block/garden_chair_wooden_single")).texture("all", material);
        ModelFile modelCenter = models().withExistingParent(path + "_center", modLoc("block/garden_chair_wooden_center")).texture("all", material);
        ModelFile modelLeft = models().withExistingParent(path + "_left", modLoc("block/garden_chair_wooden_left")).texture("all", material);
        ModelFile modelRight = models().withExistingParent(path + "_right", modLoc("block/garden_chair_wooden_right")).texture("all", material);

        longChair(block, modelSingle, modelCenter, modelLeft, modelRight);

        itemModels().getBuilder(path).parent(modelSingle);
    }

    private void stoneGardenChair(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        ModelFile modelSingle = models().withExistingParent(path + "_single", modLoc("block/garden_chair_stone_single")).texture("all", material);
        ModelFile modelCenter = models().withExistingParent(path + "_center", modLoc("block/garden_chair_stone_center")).texture("all", material);
        ModelFile modelLeft = models().withExistingParent(path + "_left", modLoc("block/garden_chair_stone_left")).texture("all", material);
        ModelFile modelRight = models().withExistingParent(path + "_right", modLoc("block/garden_chair_stone_right")).texture("all", material);

        longChair(block, modelSingle, modelCenter, modelLeft, modelRight);

        itemModels().getBuilder(path).parent(modelSingle);
    }

    public void woolSofa(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        ModelFile modelSingle = models().withExistingParent(path + "_single", modLoc("block/wool_sofa_single")).texture("all", material);
        ModelFile modelCenter = models().withExistingParent(path + "_center", modLoc("block/wool_sofa_center")).texture("all", material);
        ModelFile modelLeft = models().withExistingParent(path + "_left", modLoc("block/wool_sofa_left")).texture("all", material);
        ModelFile modelRight = models().withExistingParent(path + "_right", modLoc("block/wool_sofa_right")).texture("all", material);

        longChair(block, modelSingle, modelCenter, modelLeft, modelRight);

        itemModels().getBuilder(path).parent(modelSingle);
    }

    private void longChair(Block block, ModelFile modelSingle, ModelFile modelCenter, ModelFile modelLeft, ModelFile modelRight) {
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.SINGLE)
                .modelForState().modelFile(modelSingle).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.CENTER)
                .modelForState().modelFile(modelCenter).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.LEFT)
                .modelForState().modelFile(modelLeft).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.RIGHT)
                .modelForState().modelFile(modelRight).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.SINGLE)
                .modelForState().modelFile(modelSingle).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.CENTER)
                .modelForState().modelFile(modelCenter).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.LEFT)
                .modelForState().modelFile(modelLeft).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.RIGHT)
                .modelForState().modelFile(modelRight).rotationY(180).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.SINGLE)
                .modelForState().modelFile(modelSingle).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.CENTER)
                .modelForState().modelFile(modelCenter).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.LEFT)
                .modelForState().modelFile(modelLeft).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.RIGHT)
                .modelForState().modelFile(modelRight).rotationY(270).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.SINGLE)
                .modelForState().modelFile(modelSingle).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.CENTER)
                .modelForState().modelFile(modelCenter).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.LEFT)
                .modelForState().modelFile(modelLeft).rotationY(90).addModel()
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .with(FLBlockStateProperties.LONG_BLOCK_TYPE, LongBlockType.RIGHT)
                .modelForState().modelFile(modelRight).rotationY(90).addModel();
    }

    public void oneCutBlock(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                              .parent(models().getExistingFile(modLoc("block/one_cut_block")))
                                              .texture("all", material);
        BlockModelBuilder modelD = models().getBuilder(path + "_double")
                                          .parent(models().getExistingFile(modLoc("block/one_cut_block_double")))
                                          .texture("all", material);

        getVariantBuilder(deferredBlock.get())
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Z)
                .with(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM)
                .modelForState().modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Z)
                .with(BlockStateProperties.SLAB_TYPE, SlabType.TOP)
                .modelForState().rotationY(180).uvLock(true).modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Z)
                .with(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)
                .modelForState().modelFile(modelD).addModel()

                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.X)
                .with(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM)
                .modelForState().rotationY(270).uvLock(true).modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.X)
                .with(BlockStateProperties.SLAB_TYPE, SlabType.TOP)
                .modelForState().rotationY(90).uvLock(true).modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.X)
                .with(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)
                .modelForState().rotationY(90).uvLock(true).modelFile(modelD).addModel()

                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Y)
                .with(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM)
                .modelForState().rotationX(90).uvLock(true).modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Y)
                .with(BlockStateProperties.SLAB_TYPE, SlabType.TOP)
                .modelForState().rotationX(270).uvLock(true).modelFile(model).addModel()
                .partialState()
                .with(BlockStateProperties.AXIS, Direction.Axis.Y)
                .with(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)
                .modelForState().rotationX(90).uvLock(true).modelFile(modelD).addModel();

        itemModels().getBuilder(path).parent(model);
    }

    public void thinStairs(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
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

        thinStairStates(deferredBlock.get(), straight, inner, outer);
        itemModels().getBuilder(deferredBlock.getId().getPath()).parent(straight);
    }

    private void thinStairStates(Block block, ModelFile straight, ModelFile inner, ModelFile outer) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            StairsShape shape = state.getValue(BlockStateProperties.STAIRS_SHAPE);
            int yRot = (int) facing.getClockWise().toYRot();
            if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) yRot += 270;
            yRot %= 360;
            boolean uvlock = yRot != 0;
            return ConfiguredModel.builder()
                                  .modelFile(shape == StairsShape.STRAIGHT ? straight : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? inner : outer)
                                  .rotationY(yRot).uvLock(uvlock).build();
        }, StairBlock.WATERLOGGED);
    }

    public void doubleCutBlock(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/double_cut_block")))
                                          .texture("all", material);
        BlockModelBuilder modelC = models().getBuilder(path + "_clip")
                                           .parent(models().getExistingFile(modLoc("block/double_cut_block_clip")))
                                           .texture("all", material);
        BlockModelBuilder modelV = models().getBuilder(path + "_vertical")
                                           .parent(models().getExistingFile(modLoc("block/double_cut_block_vertical")))
                                           .texture("all", material);
        BlockModelBuilder modelVC = models().getBuilder(path + "_vertical_clip")
                                            .parent(models().getExistingFile(modLoc("block/double_cut_block_vertical_clip")))
                                            .texture("all", material);
        BlockModelBuilder modelInv = models().getBuilder(path + "_inv")
                                             .parent(models().getExistingFile(modLoc("block/double_cut_block_inv")))
                                             .texture("all", material);

        //TODO:跟进mc模型宽限
        doubleCutBlockState(block, path, model, modelC, modelV, modelVC, modelInv);
    }

    private void doubleCutBlockState(
            Block block,
            String path,
            ModelFile model,
            ModelFile modelC,
            ModelFile modelV,
            ModelFile modelVC,
            ModelFile modelInv
    ) {
        getMultipartBuilder(block)
                .part().modelFile(model).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_A, true)
                .end()
                .part().modelFile(modelC).rotationX(90).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_A, true)
                .condition(FLBlockStateProperties.PART_B, false)
                .end()
                .part().modelFile(modelC).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_A, true)
                .condition(FLBlockStateProperties.PART_C, false)
                .end()

                .part().modelFile(model).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_B, true)
                .end()
                        .part().modelFile(modelC).rotationX(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_B, true)
                .condition(FLBlockStateProperties.PART_A, false)
                .end()
                .part().modelFile(modelC).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_B, true)
                .condition(FLBlockStateProperties.PART_D, false)
                .end()

                .part().modelFile(model).rotationX(180).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_C, true)
                .end()
                .part().modelFile(modelC).rotationX(180).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_C, true)
                .condition(FLBlockStateProperties.PART_A, false)
                .end()
                .part().modelFile(modelC).rotationX(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_C, true)
                .condition(FLBlockStateProperties.PART_D, false)
                .end()

                .part().modelFile(model).rotationX(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_D, true)
                .end()
                .part().modelFile(modelC).rotationX(270).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_D, true)
                .condition(FLBlockStateProperties.PART_C, false)
                .end()
                .part().modelFile(modelC).rotationX(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.X)
                .condition(FLBlockStateProperties.PART_D, true)
                .condition(FLBlockStateProperties.PART_B, false)
                .end()

                .part().modelFile(model).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_A, true)
                .end()
                .part().modelFile(modelC).rotationX(90).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_A, true)
                .condition(FLBlockStateProperties.PART_B, false)
                .end()
                .part().modelFile(modelC).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_A, true)
                .condition(FLBlockStateProperties.PART_C, false)
                .end()

                .part().modelFile(model).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_B, true)
                .end()
                .part().modelFile(modelC).rotationX(90).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_B, true)
                .condition(FLBlockStateProperties.PART_A, false)
                .end()
                .part().modelFile(modelC).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_B, true)
                .condition(FLBlockStateProperties.PART_D, false)
                .end()

                .part().modelFile(model).rotationX(180).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_C, true)
                .end()
                .part().modelFile(modelC).rotationX(180).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_C, true)
                .condition(FLBlockStateProperties.PART_A, false)
                .end()
                .part().modelFile(modelC).rotationX(270).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_C, true)
                .condition(FLBlockStateProperties.PART_D, false)
                .end()

                .part().modelFile(model).rotationX(180).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_D, true)
                .end()
                .part().modelFile(modelC).rotationX(270).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_D, true)
                .condition(FLBlockStateProperties.PART_C, false)
                .end()
                .part().modelFile(modelC).rotationX(180).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Z)
                .condition(FLBlockStateProperties.PART_D, true)
                .condition(FLBlockStateProperties.PART_B, false)
                .end()

                .part().modelFile(modelV).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_A, true)
                .end()
                .part().modelFile(modelVC).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_A, true)
                .condition(FLBlockStateProperties.PART_B, false)
                .end()
                .part().modelFile(modelVC).rotationX(180).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_A, true)
                .condition(FLBlockStateProperties.PART_C, false)
                .end()

                .part().modelFile(modelV).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_B, true)
                .end()
                .part().modelFile(modelVC).rotationX(180).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_B, true)
                .condition(FLBlockStateProperties.PART_A, false)
                .end()
                .part().modelFile(modelVC).rotationY(90).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_B, true)
                .condition(FLBlockStateProperties.PART_D, false)
                .end()

                .part().modelFile(modelV).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_C, true)
                .end()
                .part().modelFile(modelVC).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_C, true)
                .condition(FLBlockStateProperties.PART_A, false)
                .end()
                .part().modelFile(modelVC).rotationX(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_C, true)
                .condition(FLBlockStateProperties.PART_D, false)
                .end()

                .part().modelFile(modelV).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_D, true)
                .end()
                .part().modelFile(modelVC).rotationY(180).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_D, true)
                .condition(FLBlockStateProperties.PART_C, false)
                .end()
                .part().modelFile(modelVC).rotationX(180).rotationY(270).uvLock(true).addModel()
                .condition(BlockStateProperties.AXIS, Direction.Axis.Y)
                .condition(FLBlockStateProperties.PART_D, true)
                .condition(FLBlockStateProperties.PART_B, false)
                .end();

        itemModels().getBuilder(path).parent(modelInv);
    }

    public void tripleCutBlock(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                              .parent(models().getExistingFile(modLoc("block/triple_cut_block_all")))
                                              .texture("all", material);
        BlockModelBuilder modelC = models().getBuilder(path + "_clip")
                                           .parent(models().getExistingFile(modLoc("block/triple_cut_block_clip_top")))
                                           .texture("all", material);
        BlockModelBuilder modelC2 = models().getBuilder(path + "_clip_2")
                                            .parent(models().getExistingFile(modLoc("block/triple_cut_block_clip_bottom")))
                                            .texture("all", material);
        BlockModelBuilder modelInv = models().getBuilder(path + "_inv")
                                            .parent(models().getExistingFile(modLoc("block/triple_cut_block_inv_all")))
                                            .texture("all", material);

        tripleCutState(deferredBlock.get(), path, model, modelC, modelC2, modelInv);
    }

    private void tripleCutState(Block block, String path, ModelFile model, ModelFile modelC, ModelFile modelC2, ModelFile modelInv) {
        getMultipartBuilder(block)
                .part().modelFile(model).addModel()
                .condition(FLBlockStateProperties.PART_A, true)
                .end()
                .part().modelFile(modelC).rotationX(90).rotationY(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_A, true)
                .condition(FLBlockStateProperties.PART_B, false)
                .end()
                .part().modelFile(modelC2).rotationX(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_A, true)
                .condition(FLBlockStateProperties.PART_C, false)
                .end()
                .part().modelFile(modelC).addModel()
                .condition(FLBlockStateProperties.PART_A, true)
                .condition(FLBlockStateProperties.PART_E, false)
                .end()

                .part().modelFile(model).rotationY(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_B, true)
                .end()
                .part().modelFile(modelC2).rotationX(90).rotationY(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_B, true)
                .condition(FLBlockStateProperties.PART_A, false)
                .end()
                .part().modelFile(modelC).rotationX(90).rotationY(180).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_B, true)
                .condition(FLBlockStateProperties.PART_D, false)
                .end()
                .part().modelFile(modelC).rotationY(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_B, true)
                .condition(FLBlockStateProperties.PART_F, false)
                .end()

                .part().modelFile(model).rotationY(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_C, true)
                .end()
                .part().modelFile(modelC).rotationX(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_C, true)
                .condition(FLBlockStateProperties.PART_A, false)
                .end()
                .part().modelFile(modelC2).rotationX(90).rotationY(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_C, true)
                .condition(FLBlockStateProperties.PART_D, false)
                .end()
                .part().modelFile(modelC).rotationY(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_C, true)
                .condition(FLBlockStateProperties.PART_G, false)
                .end()

                .part().modelFile(model).rotationY(180).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_D, true)
                .end()
                .part().modelFile(modelC2).rotationX(90).rotationY(180).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_D, true)
                .condition(FLBlockStateProperties.PART_B, false)
                .end()
                .part().modelFile(modelC).rotationX(90).rotationY(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_D, true)
                .condition(FLBlockStateProperties.PART_C, false)
                .end()
                .part().modelFile(modelC).rotationY(180).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_D, true)
                .condition(FLBlockStateProperties.PART_H, false)
                .end()

                .part().modelFile(model).rotationX(180).rotationY(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_E, true)
                .end()
                .part().modelFile(modelC2).rotationX(270).rotationY(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_E, true)
                .condition(FLBlockStateProperties.PART_F, false)
                .end()
                .part().modelFile(modelC).rotationX(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_E, true)
                .condition(FLBlockStateProperties.PART_G, false)
                .end()
                .part().modelFile(modelC2).addModel()
                .condition(FLBlockStateProperties.PART_E, true)
                .condition(FLBlockStateProperties.PART_A, false)
                .end()

                .part().modelFile(model).rotationX(180).rotationY(180).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_F, true)
                .end()
                .part().modelFile(modelC).rotationX(270).rotationY(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_F, true)
                .condition(FLBlockStateProperties.PART_E, false)
                .end()
                .part().modelFile(modelC2).rotationX(270).rotationY(180).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_F, true)
                .condition(FLBlockStateProperties.PART_H, false)
                .end()
                .part().modelFile(modelC2).rotationY(90).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_F, true)
                .condition(FLBlockStateProperties.PART_B, false)
                .end()

                .part().modelFile(model).rotationX(180).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_G, true)
                .end()
                .part().modelFile(modelC2).rotationX(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_G, true)
                .condition(FLBlockStateProperties.PART_E, false)
                .end()
                .part().modelFile(modelC).rotationX(270).rotationY(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_G, true)
                .condition(FLBlockStateProperties.PART_H, false)
                .end()
                .part().modelFile(modelC2).rotationY(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_G, true)
                .condition(FLBlockStateProperties.PART_C, false)
                .end()

                .part().modelFile(model).rotationX(180).rotationY(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_H, true)
                .end()
                .part().modelFile(modelC).rotationX(270).rotationY(180).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_H, true)
                .condition(FLBlockStateProperties.PART_F, false)
                .end()
                .part().modelFile(modelC2).rotationX(270).rotationY(270).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_H, true)
                .condition(FLBlockStateProperties.PART_G, false)
                .end()
                .part().modelFile(modelC2).rotationY(180).uvLock(true).addModel()
                .condition(FLBlockStateProperties.PART_H, true)
                .condition(FLBlockStateProperties.PART_D, false)
                .end();

        itemModels().getBuilder(path).parent(modelInv);
    }

    public void panel4px(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/panel_4px")))
                                          .texture("all", material);

        directionModel(block, path, model, true);
    }

    public void panel2px(DeferredBlock<Block> deferredBlock, ResourceLocation material) {
        Block block = deferredBlock.get();
        String path = deferredBlock.getId().getPath();

        BlockModelBuilder model = models().getBuilder(path)
                                          .parent(models().getExistingFile(modLoc("block/panel_2px")))
                                          .texture("all", material);

        directionModel(block, path, model, true);
    }

    public void crossHole(DeferredBlock<Block> deferredBlock, BlockMaterial material) {
        String path = deferredBlock.getId().getPath();
        BlockModelBuilder model;
        if (material.hasTag(BlockMaterialTag.TEXTURE_TRANSLUCENT)) {
            ResourceLocation rl = material.getBlock() == Blocks.GLASS ? modLoc("block/glass_cross_hole") : blockTexture(material);
            model = models().getBuilder(path)
                            .parent(models().getExistingFile(modLoc("block/cross_hole_translucent")))
                            .texture("end", rl)
                            .texture("side", blockTexture(material, "pane_top"));
            directionModel(deferredBlock.get(), path, model, false);
        }
        else {
            model = models().getBuilder(path)
                                 .parent(models().getExistingFile(modLoc("block/cross_hole")))
                                 .texture("all", getSimpleCubeMaterialResource(material));
            directionModel(deferredBlock.get(), path, model, true);
        }
    }

    public void stair(DeferredBlock<StairBlock> stair, BlockMaterial material) {
        ResourceLocation texture = getSimpleCubeMaterialResource(material);
        stairsBlock(stair.get(), texture);
        itemModels().stairs(stair.getId().getPath(), texture, texture, texture);
    }

    public void slab(DeferredBlock<SlabBlock> slab, BlockMaterial material) {
        ResourceLocation texture = getSimpleCubeMaterialResource(material);
        slabBlock(slab.get(), blockTexture(material), texture);
        itemModels().slab(slab.getId().getPath(), texture, texture, texture);
    }

    public void fence(DeferredBlock<FenceBlock> fence, BlockMaterial material) {
        fenceBlock(fence.get(), blockTexture(material));
        itemModels().fenceInventory(fence.getId().getPath(), blockTexture(material));
    }

    public void fenceGate(DeferredBlock<FenceGateBlock> fenceGate, BlockMaterial material) {
        fenceGateBlock(fenceGate.get(), blockTexture(material));
        itemModels().fenceGate(fenceGate.getId().getPath(), blockTexture(material));
    }

    public void pressurePlate(DeferredBlock<PressurePlateBlock> pressurePlate, BlockMaterial material) {
        pressurePlateBlock(pressurePlate.get(), blockTexture(material));
        itemModels().pressurePlate(pressurePlate.getId().getPath(), blockTexture(material));
    }

    public void button(DeferredBlock<ButtonBlock> button, BlockMaterial material) {
        buttonBlock(button.get(), blockTexture(material));
        itemModels().buttonInventory(button.getId().getPath(), blockTexture(material));
    }

    public void wall(DeferredBlock<WallBlock> wall, BlockMaterial material) {
        ResourceLocation texture = getSimpleCubeMaterialResource(material);
        wallBlock(wall.get(), texture);
        itemModels().wallInventory(wall.getId().getPath(), texture);
    }

    public void horizontalDirectionFakeBlock(DeferredBlock<Block> fakeBlock, Block block, boolean lockUV) {
        ResourceLocation id = fakeBlock.getId();
        ResourceLocation rl = BuiltInRegistries.BLOCK.getKey(block);
        horizontalDirectionModel(
                fakeBlock.get(),
                id.getPath(),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), "block/" + rl.getPath())),
                lockUV
        );
    }

    public void horizontalDirectionFakeBlock(DeferredBlock<Block> fakeBlock, String s, boolean lockUV) {
        ResourceLocation id = fakeBlock.getId();
        horizontalDirectionModel(
                fakeBlock.get(),
                id.getPath(),
                models().getExistingFile(mcLoc("block/" + s)),
                lockUV
        );
    }

    protected void directionFakeBlock(DeferredHolder<Block, ? extends Block> deferredBlock, Block block, boolean lockUV) {
        ResourceLocation id = deferredBlock.getId();
        ResourceLocation rl = BuiltInRegistries.BLOCK.getKey(block);
        directionModel(
                deferredBlock.get(),
                id.getPath(),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath())),
                lockUV
        );
    }

    protected void texturedCube(DeferredHolder<Block, ? extends Block> deferredBlock, String all) {
        String path = deferredBlock.getId().getPath();
        ModelFile model = models().withExistingParent(path, "block/cube_all").texture("all", mcLoc(all));
        simple(deferredBlock.get(), path, model);
    }

    protected void texturedCubeColumn(DeferredHolder<Block, ? extends Block> deferredBlock, String side, String end) {
        String path = deferredBlock.getId().getPath();
        ModelFile model = models().withExistingParent(path, "block/cube_column")
                                  .texture("side", mcLoc(side))
                                  .texture("end", mcLoc(end));
        simple(deferredBlock.get(), path, model);
    }

    protected void texturedHorizonAxisCube(DeferredHolder<Block, ? extends Block> deferredBlock, String all) {
        String path = deferredBlock.getId().getPath();
        ModelFile model = models().withExistingParent(path, "block/cube_all").texture("all", mcLoc(all));
        horizontalAxisModel(deferredBlock.get(), path, model, false);
    }

    protected void texturedHorizonAxisCubeColumn(DeferredHolder<Block, ? extends Block> deferredBlock, String side, String end) {
        String path = deferredBlock.getId().getPath();
        ModelFile model = models().withExistingParent(path, "block/cube_column")
                                  .texture("side", mcLoc(side))
                                  .texture("end", mcLoc(end));
        horizontalAxisModel(deferredBlock.get(), path, model, false);
    }

    protected void texturedPillarCube(DeferredHolder<Block, ? extends Block> deferredBlock, String all) {
        String path = deferredBlock.getId().getPath();
        ModelFile model = models().withExistingParent(path, "block/cube_all").texture("all", mcLoc(all));
        yAxisModel(deferredBlock.get(), path, model, false);
    }

}