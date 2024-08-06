package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.world.item.FDItems;
import net.fiercemanul.fiercedecoration.world.level.block.*;
import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.data.FSRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;

import java.util.concurrent.CompletableFuture;


public class RecipeGen extends FSRecipeProvider {

    public RecipeGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        soulCrystalOrnament(pRecipeOutput);
        pC(pRecipeOutput);
        bookAndLamp(pRecipeOutput);
        starBlock(pRecipeOutput);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.ITEM_FRAME_SHELL_THIN.get(), Items.GLASS, 2);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.ITEM_FRAME_SHELL_BIG.get(), Items.GLASS);
        polished(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.SMOOTH_GLOWSTONE.get(), Items.GLOWSTONE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.SMOOTH_GLOWSTONE.get(), Items.GLOWSTONE);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_SMOOTH_GLOWSTONE, FDItems.SMOOTH_GLOWSTONE);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_SEA_LANTERN, Items.SEA_LANTERN);
        lightTube(pRecipeOutput);
        lightPlate(pRecipeOutput);
        greenFunRoof(pRecipeOutput);
        fireplaceHeart(pRecipeOutput);
        waterloggedCobblestone(pRecipeOutput);
        heavyChains(pRecipeOutput);
        neoForge(pRecipeOutput);
        rottenFleshBlock(pRecipeOutput);
        foxCarrot(pRecipeOutput);
        stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_GRASS_BLOCK.get(), Items.GRASS_BLOCK, 2);
        stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_PATH.get(), Items.DIRT_PATH, 2);
        stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_PODZOL.get(), Items.PODZOL, 2);
        stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_DIRT.get(), Items.DIRT, 2);
        stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_MYCELIUM.get(), Items.MYCELIUM, 2);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.FIREWOOD.get(), Items.OAK_LOG, 2);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.ROCK_PATH, Items.COBBLESTONE, 16);
        wallFlowerPot(pRecipeOutput, FDItems.WALL_FLOWER_POT_A.get(), Items.AZURE_BLUET, Items.DANDELION, Items.POPPY);
        wallFlowerPot(pRecipeOutput, FDItems.WALL_FLOWER_POT_B.get(), Items.LILY_OF_THE_VALLEY, Items.CORNFLOWER, Items.OXEYE_DAISY);
        wallFlowerPot(pRecipeOutput, FDItems.WALL_FLOWER_POT_C.get(), Items.RED_TULIP, Items.PINK_TULIP, Items.ORANGE_TULIP);
        wallFlowerPot(pRecipeOutput, FDItems.WALL_FLOWER_POT_D.get(), Items.BLUE_ORCHID, Items.ALLIUM, Items.WHITE_TULIP);
        wallFlowerPot(pRecipeOutput, FDItems.WALL_FLOWER_POT_E.get(), Items.CRIMSON_FUNGUS, Items.WITHER_ROSE, Items.WARPED_FUNGUS);
        wallFlowerPot(pRecipeOutput, FDItems.WALL_FLOWER_POT_F.get(), Items.BROWN_MUSHROOM, Items.DEAD_BUSH, Items.RED_MUSHROOM);
        rainbow(pRecipeOutput, FDItems.RAINBOW_WOOL, Items.RED_WOOL, Items.GREEN_WOOL, Items.BLUE_WOOL);
        rainbow(pRecipeOutput, FDItems.RAINBOW_TERRACOTTA, Items.RED_TERRACOTTA, Items.GREEN_TERRACOTTA, Items.BLUE_TERRACOTTA);
        rainbow(pRecipeOutput, FDItems.RAINBOW_CONCRETE, Items.RED_CONCRETE, Items.GREEN_CONCRETE, Items.BLUE_CONCRETE);
        rainbow(pRecipeOutput, FDItems.RAINBOW_GLASS, FDItems.RED_SEA_LANTERN, FDItems.GREEN_SEA_LANTERN, FDItems.BLUE_SEA_LANTERN);
        rainbow(pRecipeOutput, FDItems.RAINBOW_SEA_LANTERN, FDItems.REINFORCED_RED_SEA_LANTERN, FDItems.REINFORCED_GREEN_SEA_LANTERN, FDItems.REINFORCED_BLUE_SEA_LANTERN);
        rainbow(pRecipeOutput, FDItems.RAINBOW_REINFORCED_SEA_LANTERN, Items.RED_STAINED_GLASS, Items.GREEN_STAINED_GLASS, Items.BLUE_STAINED_GLASS);
        doubleBlock(pRecipeOutput, FDItems.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE, Items.OAK_PLANKS, Items.LIGHT_GRAY_CONCRETE);
        doubleBlock(pRecipeOutput, FDItems.SPRUCE_PLANKS_AND_GRAY_CONCRETE, Items.SPRUCE_PLANKS, Items.GRAY_CONCRETE);
        doubleBlock(pRecipeOutput, FDItems.OAK_PLANKS_AND_SPRUCE_PLANKS, Items.OAK_PLANKS, Items.SPRUCE_PLANKS);
        doubleBlock(pRecipeOutput, FDItems.WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE, Items.WHITE_CONCRETE, Items.LIGHT_GRAY_CONCRETE);
        doubleBlock(pRecipeOutput, FDItems.DEEPSLATE_TILES_AND_SPRUCE_PLANKS, Items.DEEPSLATE_TILES, Items.SPRUCE_PLANKS);
        doubleBlock(pRecipeOutput, FDItems.DEEPSLATE_TILES_AND_MANGROVE_PLANKS, Items.DEEPSLATE_TILES, Items.MANGROVE_PLANKS);
        doubleBlock(pRecipeOutput, FDItems.DARK_PRISMARINE_AND_SPRUCE_PLANKS, Items.DARK_PRISMARINE, Items.SPRUCE_PLANKS);
        doubleBlock(pRecipeOutput, FDItems.DARK_PRISMARINE_AND_MANGROVE_PLANKS, Items.DARK_PRISMARINE, Items.MANGROVE_PLANKS);
        doubleBlock(pRecipeOutput, FDItems.BRICKS_AND_BIRCH_PLANKS, Items.BRICKS, Items.BIRCH_PLANKS);
        fakeBlocks(pRecipeOutput);
        textureBlocks(pRecipeOutput);
        colorSeaLantern(pRecipeOutput, FDItems.WHITE_SEA_LANTERN, Items.WHITE_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.ORANGE_SEA_LANTERN, Items.ORANGE_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.MAGENTA_SEA_LANTERN, Items.MAGENTA_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.LIGHT_BLUE_SEA_LANTERN, Items.LIGHT_BLUE_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.YELLOW_SEA_LANTERN, Items.YELLOW_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.LIME_SEA_LANTERN, Items.LIME_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.PINK_SEA_LANTERN, Items.PINK_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.GRAY_SEA_LANTERN, Items.GRAY_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.LIGHT_GRAY_SEA_LANTERN, Items.LIGHT_GRAY_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.CYAN_SEA_LANTERN, Items.CYAN_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.PURPLE_SEA_LANTERN, Items.PURPLE_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.BLUE_SEA_LANTERN, Items.BLUE_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.BROWN_SEA_LANTERN, Items.BROWN_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.GREEN_SEA_LANTERN, Items.GREEN_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.RED_SEA_LANTERN, Items.RED_DYE);
        colorSeaLantern(pRecipeOutput, FDItems.BLACK_SEA_LANTERN, Items.BLACK_DYE);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_WHITE_SEA_LANTERN, FDItems.WHITE_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_ORANGE_SEA_LANTERN, FDItems.ORANGE_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_MAGENTA_SEA_LANTERN, FDItems.MAGENTA_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_LIGHT_BLUE_SEA_LANTERN, FDItems.LIGHT_BLUE_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_YELLOW_SEA_LANTERN, FDItems.YELLOW_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_LIME_SEA_LANTERN, FDItems.LIME_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_PINK_SEA_LANTERN, FDItems.PINK_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_GRAY_SEA_LANTERN, FDItems.GRAY_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_LIGHT_GRAY_SEA_LANTERN, FDItems.LIGHT_GRAY_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_CYAN_SEA_LANTERN, FDItems.CYAN_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_PURPLE_SEA_LANTERN, FDItems.PURPLE_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_BLUE_SEA_LANTERN, FDItems.BLUE_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_BROWN_SEA_LANTERN, FDItems.BROWN_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_GREEN_SEA_LANTERN, FDItems.GREEN_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_RED_SEA_LANTERN, FDItems.RED_SEA_LANTERN);
        reinforcedLamp(pRecipeOutput, FDItems.REINFORCED_BLACK_SEA_LANTERN, FDItems.BLACK_SEA_LANTERN);
        colorLamps(pRecipeOutput);
        craftingBlock(pRecipeOutput);



        smoothPlanks(pRecipeOutput, FDItems.SMOOTH_OAK_PLANKS.get(), Items.STRIPPED_OAK_WOOD);
        smoothPlanks(pRecipeOutput, FDItems.SMOOTH_SPRUCE_PLANKS.get(), Items.STRIPPED_SPRUCE_WOOD);
        smoothPlanks(pRecipeOutput, FDItems.SMOOTH_BIRCH_PLANKS.get(), Items.STRIPPED_BIRCH_WOOD);
        smoothPlanks(pRecipeOutput, FDItems.SMOOTH_JUNGLE_PLANKS.get(), Items.STRIPPED_JUNGLE_WOOD);
        smoothPlanks(pRecipeOutput, FDItems.SMOOTH_ACACIA_PLANKS.get(), Items.STRIPPED_ACACIA_WOOD);
        smoothPlanks(pRecipeOutput, FDItems.SMOOTH_DARK_OAK_PLANKS.get(), Items.STRIPPED_DARK_OAK_WOOD);
        smoothPlanks(pRecipeOutput, FDItems.SMOOTH_MANGROVE_PLANKS.get(), Items.STRIPPED_MANGROVE_WOOD);
        smoothPlanks(pRecipeOutput, FDItems.SMOOTH_CRIMSON_PLANKS.get(), Items.STRIPPED_CRIMSON_HYPHAE);
        smoothPlanks(pRecipeOutput, FDItems.SMOOTH_WARPED_PLANKS.get(), Items.STRIPPED_WARPED_HYPHAE);

        DataGen.BLOCKS_AND_MATERIALS.forEach((deferredBlock, blockMaterial) -> {
            Block block = deferredBlock.get();
            Block materialBlock = blockMaterial.getMaterialBlock();

            if (block instanceof LampInGlassBlock) buildLampInGlass(pRecipeOutput, block, materialBlock);
            else if (block instanceof WoodenGuardrailBlock) woodenGuardrail(pRecipeOutput, block, materialBlock);
            else if (block instanceof WoodenGuardrailTypeBBlock) woodenGuardrailB(pRecipeOutput, block, materialBlock);
            else if (block instanceof StoneGuardrailBlock) stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock, 2);
            else if (block instanceof GlassGuardrailBlock) stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock, 6);
            else if (block instanceof PeepWindowBlock) {
                switch (blockMaterial.getMaterialType()) {
                    case STONE, GLASS -> peepWindowWithCutter(pRecipeOutput, block, materialBlock);
                    default -> peepWindow(pRecipeOutput, block, materialBlock);
                }
            }
            else if (block instanceof OneCutBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 2);
            else if (block instanceof DoubleCutBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 4);
            else if (block instanceof TripleCutBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 8);
            else if (block instanceof ThinStairBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 2);
            else if (block instanceof Panel4PXBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 4);
            else if (block instanceof Panel2PXBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 8);
            else if (block instanceof Pillar12PXBlock) pillar12px(pRecipeOutput, block, materialBlock);
            else if (block instanceof Pillar8PXBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 4);
            else if (block instanceof Pillar6PXBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 7);
            else if (block instanceof Pillar4PXBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 14);
            else if (block instanceof PillarConnector12PXBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock);
            else if (block instanceof PillarConnector8PXBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 2);
            else if (block instanceof PillarConnector6PXBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 3);
            else if (block instanceof PillarConnector4PXBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 5);
            else if (block instanceof HorizonPanelBlock) stonecutting(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock, 8);
            else if (block instanceof WindowTypeABlock) stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock, 3);
            else if (block instanceof WindowTypeBBlock) stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock, 4);
            else if (block instanceof TableBlock) stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock, 5);
            else if (block instanceof CabinetBlock) stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock);
            else if (block instanceof SimpleChairBlock) stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock, 4);
            else if (block instanceof GardenChairBlock) {
                if (blockMaterial.getMaterialType().equals(BlockMaterial.MaterialType.WOOD))
                    stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock, 2);
                else stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock);
            }
            else if (block instanceof WoolSofaBlock) stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, block, materialBlock);
            else if (block instanceof SlabBlock) slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, block, materialBlock);
            else if (block instanceof StairBlock) stairBuilder(block, Ingredient.of(materialBlock)).unlockedBy(getHasName(materialBlock), has(materialBlock)).save(pRecipeOutput);
            else if (block instanceof FenceBlock) fenceBuilder(block, Ingredient.of(materialBlock)).unlockedBy(getHasName(materialBlock), has(materialBlock)).save(pRecipeOutput);
            else if (block instanceof FenceGateBlock) fenceGateBuilder(block, Ingredient.of(materialBlock)).unlockedBy(getHasName(materialBlock), has(materialBlock)).save(pRecipeOutput);
            else if (block instanceof ButtonBlock) buttonBuilder(block, Ingredient.of(materialBlock)).unlockedBy(getHasName(materialBlock), has(materialBlock)).save(pRecipeOutput);
            else if (block instanceof PressurePlateBlock) pressurePlate(pRecipeOutput, block, materialBlock);
        });
    }

    private static void buildLampInGlass(RecipeOutput pRecipeOutput, ItemLike pResult, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, pResult, 4)
                .define('X', Items.GLASS)
                .define('Y', material)
                .define('Z', Items.IRON_INGOT)
                .pattern("X")
                .pattern("Y")
                .pattern("Z")
                .unlockedBy(getHasName(material), has(material))
                .save(pRecipeOutput);
    }

    private static void colorSeaLantern(RecipeOutput pRecipeOutput, ItemLike pResult, ItemLike dye) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, pResult, 8)
                .define('X', pResult)
                .define('D', dye)
                .pattern("XXX")
                .pattern("XDX")
                .pattern("XXX")
                .unlockedBy(getHasName(Blocks.SEA_LANTERN), has(Blocks.SEA_LANTERN))
                .save(pRecipeOutput);
    }

    private static void reinforcedLamp(RecipeOutput pRecipeOutput, ItemLike pResult, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, pResult)
                .define('#', Items.STICK)
                .define('X', Items.IRON_NUGGET)
                .define('O', material)
                .pattern("X#X")
                .pattern("#O#")
                .pattern("X#X")
                .unlockedBy(getHasName(material), has(material))
                .save(pRecipeOutput);
    }

    private static void soulCrystalOrnament(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.SOUL_CRYSTAL_ORNAMENT)
                .define('#', FierceSource.LARGE_SOUL_CRYSTAL_BLOCK_ITEM)
                .define('X', FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM)
                .define('P', FDItems.SMOOTH_MANGROVE_PLANKS)
                .pattern(" X ")
                .pattern("X#X")
                .pattern(" XP")
                .unlockedBy(getHasName(FierceSource.LARGE_SOUL_CRYSTAL_BLOCK_ITEM), has(FierceSource.LARGE_SOUL_CRYSTAL_BLOCK_ITEM))
                .save(pRecipeOutput);
    }

    private static void pC(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.PORTABLE_WORKSTATION.get())
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.GLASS_PANE)
                .define('C', Items.GLOWSTONE_DUST)
                .define('D', Items.QUARTZ)
                .pattern("ABA")
                .pattern("ACA")
                .pattern("ADA")
                .unlockedBy("has_quartz", has(Items.QUARTZ))
                .save(pRecipeOutput);
        oneToOne(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.LAPTOP_TERMINAL.get(), FDItems.PORTABLE_WORKSTATION.get(), "");
        oneToOne(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.PORTABLE_WORKSTATION.get(), FDItems.LAPTOP_TERMINAL.get(), "");
    }

    private static void bookAndLamp(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.BOOK_AND_LAMP.get())
                .define('B', Items.BOOK)
                .define('C', Items.COPPER_INGOT)
                .define('D', Items.GLOWSTONE_DUST)
                .pattern("D ")
                .pattern("CB")
                .unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
                .save(pRecipeOutput);
    }

    private static void starBlock(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.STAR_BLOCK.get(), 8)
                .define('#', Items.TINTED_GLASS)
                .define('X', Items.ENDER_EYE)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy(getHasName(Items.TINTED_GLASS), has(Items.TINTED_GLASS))
                .save(pRecipeOutput);
    }

    private static void lightTube(RecipeOutput pRecipeOutput) {
        Item smooth_glowstone = FDItems.SMOOTH_GLOWSTONE.get();
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIGHT_TUBE.get(), 32)
                .define('#', smooth_glowstone)
                .define('X', Items.IRON_INGOT)
                .pattern("X")
                .pattern("#")
                .pattern("X")
                .unlockedBy(getHasName(smooth_glowstone), has(smooth_glowstone))
                .save(pRecipeOutput);
    }

    private static void lightPlate(RecipeOutput pRecipeOutput) {
        Item smooth_glowstone = FDItems.SMOOTH_GLOWSTONE.get();
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIGHT_PLATE.get(), 16)
                .define('#', smooth_glowstone)
                .define('X', Items.IRON_INGOT)
                .pattern("X#X")
                .unlockedBy(getHasName(smooth_glowstone), has(smooth_glowstone))
                .save(pRecipeOutput);
    }

    private static void greenFunRoof(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.GREEN_FUN_ROOF.get(), 8)
                .define('#', Items.AZALEA_LEAVES)
                .define('X', Items.OAK_LOG)
                .pattern("###")
                .pattern("X X")
                .unlockedBy(getHasName(Items.OAK_LOG), has(Items.OAK_LOG))
                .save(pRecipeOutput);
    }

    private static void fireplaceHeart(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.FIREPLACE_HEART.get(), 1)
                .define('#', Items.IRON_INGOT)
                .define('X', Items.IRON_BARS)
                .define('C', ItemTags.COALS)
                .pattern("XCX")
                .pattern("###")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pRecipeOutput);
    }

    private static void foxCarrot(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FDItems.FOX_CARROT_SHEAF)
                           .define('#', FDItems.FOX_CARROT)
                           .pattern("##")
                           .pattern("##")
                           .unlockedBy(getHasName(FDItems.FOX_CARROT), has(FDItems.FOX_CARROT))
                           .save(pRecipeOutput);
        oneToOne(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.FOX_CARROT, FDItems.FOX_CARROT_SHEAF, "", 4);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FDItems.FOX_CARROT_BASKET)
                           .define('#', FDItems.FOX_CARROT_SHEAF)
                           .pattern("###")
                           .pattern("# #")
                           .pattern("###")
                           .unlockedBy(getHasName(FDItems.FOX_CARROT_SHEAF), has(FDItems.FOX_CARROT_SHEAF))
                           .save(pRecipeOutput);
        oneToOne(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.FOX_CARROT_SHEAF, FDItems.FOX_CARROT_BASKET, "", 8);
    }

    private static void craftingBlock(RecipeOutput pRecipeOutput) {
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.CRAFTING_BLOCK, Items.CRAFTING_TABLE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.CRAFTING_DESK, Items.CRAFTING_TABLE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.CRAFTING_PAD, Items.CRAFTING_TABLE, 16);
    }

    private static void rottenFleshBlock(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.ROTTEN_FLESH_BLOCK.get())
                .define('#', Items.ROTTEN_FLESH)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH))
                .save(pRecipeOutput);
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(FDItems.ROTTEN_FLESH_BLOCK), RecipeCategory.MISC, Items.LEATHER, 0.3F, 400)
                .unlockedBy(getHasName(FDItems.ROTTEN_FLESH_BLOCK), has(FDItems.ROTTEN_FLESH_BLOCK))
                .save(pRecipeOutput, getItemName(Items.LEATHER) + "_from_smelting_" + getItemName(FDItems.ROTTEN_FLESH_BLOCK));
        SimpleCookingRecipeBuilder
                .smoking(Ingredient.of(FDItems.ROTTEN_FLESH_BLOCK), RecipeCategory.MISC, Items.LEATHER, 0.3F, 200)
                .unlockedBy(getHasName(FDItems.ROTTEN_FLESH_BLOCK), has(FDItems.ROTTEN_FLESH_BLOCK))
                .save(pRecipeOutput, getItemName(Items.LEATHER) + "_from_smoking_" + getItemName(FDItems.ROTTEN_FLESH_BLOCK));
        SimpleCookingRecipeBuilder
                .campfireCooking(Ingredient.of(FDItems.ROTTEN_FLESH_BLOCK), RecipeCategory.MISC, Items.LEATHER, 0.3F, 1200)
                .unlockedBy(getHasName(FDItems.ROTTEN_FLESH_BLOCK), has(FDItems.ROTTEN_FLESH_BLOCK))
                .save(pRecipeOutput, getItemName(Items.LEATHER) + "_from_camping_" + getItemName(FDItems.ROTTEN_FLESH_BLOCK));
    }

    private static void waterloggedCobblestone(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.WATERLOGGED_COBBLESTONE.get())
                .define('#', Items.MOSSY_COBBLESTONE_SLAB)
                .define('X', Items.WATER_BUCKET)
                .pattern("X#")
                .pattern("#X")
                .unlockedBy("in_water", insideOf(Blocks.WATER))
                .save(pRecipeOutput);
    }

    private static void wallFlowerPot(RecipeOutput pRecipeOutput, ItemLike pResult, Item flowerA, Item flowerB, Item flowerC) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, pResult)
                .define('a', flowerA)
                .define('b', flowerB)
                .define('c', flowerC)
                .define('X', Items.FLOWER_POT)
                .pattern("abc")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.FLOWER_POT), has(Items.FLOWER_POT))
                .save(pRecipeOutput);
    }

    private static void heavyChains(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.HEAVY_CHAINS)
                .define('#', Items.CHAIN)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(Items.CHAIN), has(Items.CHAIN))
                .save(pRecipeOutput);
    }

    private static void neoForge(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.NEO_FORGE)
                .define('#', Items.IRON_INGOT)
                .define('a', Items.ORANGE_WOOL)
                .define('b', Items.WHITE_WOOL)
                .pattern("aab")
                .pattern(" # ")
                .pattern("###")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pRecipeOutput);
    }

    private static void fakeBlocks(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.FAKE_HOPPER, 10)
                .define('#', Items.IRON_INGOT)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pRecipeOutput);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_FURNACE, Items.FURNACE, 8);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_FURNACE)
                .define('a', FDItems.FAKE_FURNACE)
                .define('b', Items.GLOWSTONE_DUST)
                .pattern("a")
                .pattern("b")
                .unlockedBy(getHasName(FDItems.FAKE_FURNACE), has(FDItems.FAKE_FURNACE))
                .save(pRecipeOutput);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.FAKE_BLAST_FURNACE, 5)
                .define('#', Blocks.SMOOTH_STONE)
                .define('X', FDItems.FAKE_FURNACE)
                .define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("IXI")
                .pattern("###")
                .unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE))
                .save(pRecipeOutput);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_BLAST_FURNACE)
                .define('a', FDItems.FAKE_BLAST_FURNACE)
                .define('b', Items.GLOWSTONE_DUST)
                .pattern("a")
                .pattern("b")
                .unlockedBy(getHasName(FDItems.FAKE_BLAST_FURNACE), has(FDItems.FAKE_BLAST_FURNACE))
                .save(pRecipeOutput);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.FAKE_SMOKER, 8)
                .define('#', ItemTags.LOGS)
                .define('X', FDItems.FAKE_FURNACE)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy("has_furnace", has(Blocks.FURNACE))
                .save(pRecipeOutput);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_SMOKER)
                .define('a', FDItems.FAKE_SMOKER)
                .define('b', Items.GLOWSTONE_DUST)
                .pattern("a")
                .pattern("b")
                .unlockedBy(getHasName(FDItems.FAKE_SMOKER), has(FDItems.FAKE_SMOKER))
                .save(pRecipeOutput);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_BARREL, Items.BARREL, 8);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FDItems.FAKE_CAMPFIRE, 8)
                           .define('L', ItemTags.LOGS)
                           .define('S', Items.STICK)
                           .pattern(" S ")
                           .pattern("S S")
                           .pattern("LLL")
                           .unlockedBy("has_stick", has(Items.STICK))
                           .unlockedBy("has_coal", has(ItemTags.COALS))
                           .save(pRecipeOutput);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_CAMPFIRE, Items.CAMPFIRE, 8);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_SOUL_CAMPFIRE, Items.SOUL_CAMPFIRE, 8);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_CHEST, Items.CHEST, 8);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_CHISELED_BOOKSHELF, Items.CHISELED_BOOKSHELF, 8);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_LECTERN, Items.LECTERN, 16);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_BEEHIVE, Items.BEEHIVE, 16);
        fakeBlocks(pRecipeOutput, FDItems.FAKE_IRON_BLOCK, Items.GRAY_DYE);
        fakeBlocks(pRecipeOutput, FDItems.FAKE_GOLD_BLOCK, Items.YELLOW_DYE);
        fakeBlocks(pRecipeOutput, FDItems.FAKE_DIAMOND_BLOCK, Items.LIGHT_BLUE_DYE);
        fakeBlocks(pRecipeOutput, FDItems.FAKE_NETHERITE_BLOCK, Items.BROWN_DYE);
        fakeBlocks(pRecipeOutput, FDItems.FAKE_BEDROCK, Items.BLACK_DYE);
    }

    private static void fakeBlocks(RecipeOutput pRecipeOutput, ItemLike fakeBlock, Item dye) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, fakeBlock, 8)
                           .define('S', Items.STONE)
                           .define('D', dye)
                           .pattern("SSS")
                           .pattern("SDS")
                           .pattern("SSS")
                           .unlockedBy(getHasName(dye), has(dye))
                           .save(pRecipeOutput);
    }

    private static void textureBlocks(RecipeOutput pRecipeOutput) {
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_CHISELED_BOOKSHELF, FDItems.FAKE_CHISELED_BOOKSHELF);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_CHISELED_BOOKSHELF_TOP, FDItems.FAKE_CHISELED_BOOKSHELF);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_CHISELED_BOOKSHELF_SIDE, FDItems.FAKE_CHISELED_BOOKSHELF);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_LOOM, Items.LOOM);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_BEEHIVE_TOP, FDItems.FAKE_BEEHIVE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_SMITHING_TABLE_BOTTOM, Items.SMITHING_TABLE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_COMPOSTER_BOTTOM, Items.COMPOSTER);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_BEE_NEST_TOP, FDItems.TEXTURE_BEEHIVE_TOP);

        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_FURNACE, FDItems.FAKE_FURNACE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_FURNACE_TOP, FDItems.FAKE_FURNACE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_BLAST_FURNACE, FDItems.FAKE_BLAST_FURNACE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_BLAST_FURNACE_TOP, FDItems.FAKE_BLAST_FURNACE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_SMOKER, FDItems.FAKE_SMOKER);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_SMITHING_TABLE_TOP, Items.SMITHING_TABLE);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_LODESTONE, Items.CHISELED_STONE_BRICKS);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_LODESTONE_SIDE, Items.CHISELED_STONE_BRICKS);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_LODESTONE_TOP, Items.CHISELED_STONE_BRICKS);
    }

    private static void rainbow(RecipeOutput pRecipeOutput, ItemLike rainbowBlock, ItemLike r, ItemLike g, ItemLike b) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, rainbowBlock, 3)
                .define('#', FierceSource.SOUL_CRYSTAL_SHARD_ITEM)
                .define('r', r)
                .define('g', g)
                .define('b', b)
                .pattern(" r ")
                .pattern(" # ")
                .pattern("g b")
                .unlockedBy(getHasName(FierceSource.SOUL_CRYSTAL_SHARD_ITEM), has(FierceSource.SOUL_CRYSTAL_SHARD_ITEM))
                .save(pRecipeOutput);
    }

    private static void colorLamp(RecipeOutput pRecipeOutput, ItemLike lamp, ItemLike dye) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, lamp)
                .define('#', FierceSource.SOUL_CRYSTAL_SHARD_ITEM)
                .define('d', dye)
                .pattern(" # ")
                .pattern("#d#")
                .pattern(" # ")
                .unlockedBy(getHasName(FierceSource.SOUL_CRYSTAL_SHARD_ITEM), has(FierceSource.SOUL_CRYSTAL_SHARD_ITEM))
                .save(pRecipeOutput);
    }

    private static void colorLamps(RecipeOutput pRecipeOutput) {
        colorLamp(pRecipeOutput, FDItems.RED_LAMP, Items.RED_DYE);
        colorLamp(pRecipeOutput, FDItems.GREEN_LAMP, Items.GREEN_DYE);
        colorLamp(pRecipeOutput, FDItems.BLUE_LAMP, Items.BLUE_DYE);
        colorLamp(pRecipeOutput, FDItems.YELLOW_LAMP, Items.YELLOW_DYE);
        colorLamp(pRecipeOutput, FDItems.CYAN_LAMP, Items.CYAN_DYE);
        colorLamp(pRecipeOutput, FDItems.PURPLE_LAMP, Items.PURPLE_DYE);
        rainbow(pRecipeOutput, FDItems.RAINBOW_LAMP, FDItems.RED_LAMP, FDItems.GREEN_LAMP, FDItems.BLUE_LAMP);
    }

    private static void doubleBlock(RecipeOutput pRecipeOutput, ItemLike doubleBlock, ItemLike up, ItemLike down) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, doubleBlock, 2)
                .define('u', up)
                .define('d', down)
                .pattern("u")
                .pattern("d")
                .unlockedBy(getHasName(up), has(up))
                .save(pRecipeOutput);
    }

    private static void smoothPlanks(RecipeOutput pRecipeOutput, ItemLike smoothPlanks, ItemLike wood) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.BUILDING_BLOCKS, smoothPlanks, 21)
                .define('#', wood)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(wood), has(wood))
                .save(pRecipeOutput);
    }

    private static void woodenGuardrail(RecipeOutput pRecipeOutput, ItemLike woodenGuardrail, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, woodenGuardrail, 4)
                .define('X', material)
                .define('#', Items.STICK)
                .pattern("#X#")
                .pattern(" # ")
                .group("guardrail")
                .unlockedBy(getHasName(material), has(material))
                .save(pRecipeOutput);
    }

    private static void woodenGuardrailB(RecipeOutput pRecipeOutput, ItemLike woodenGuardrail, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, woodenGuardrail, 4)
                .define('X', material)
                .define('#', Items.STICK)
                .pattern("X#X")
                .group("guardrail")
                .unlockedBy(getHasName(material), has(material))
                .save(pRecipeOutput);
    }

    private static void peepWindow(RecipeOutput pRecipeOutput, ItemLike peepWindow, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, peepWindow, 16)
                .define('#', material)
                .pattern("# #")
                .pattern("   ")
                .pattern("# #")
                .group("peep_window")
                .unlockedBy(getHasName(material), has(material))
                .save(pRecipeOutput);
    }

    private static void peepWindowWithCutter(RecipeOutput pRecipeOutput, ItemLike peepWindow, ItemLike material) {
        peepWindow(pRecipeOutput, peepWindow, material);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, peepWindow, material, 4);
    }

    private static void pillar12px(RecipeOutput pRecipeOutput, ItemLike pillar, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.BUILDING_BLOCKS, pillar, 5)
                .define('#', material)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .group("pillar")
                .unlockedBy(getHasName(material), has(material))
                .save(pRecipeOutput);
        stonecutting(pRecipeOutput, RecipeCategory.DECORATIONS, pillar, material);
    }

}
