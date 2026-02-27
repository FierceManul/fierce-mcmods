package net.fiercemanul.fiercedecoration.data.gathers;

import net.fiercemanul.fiercedecoration.data.registries.BlockBulkRegister;
import net.fiercemanul.fiercedecoration.data.registries.BlockMaterial;
import net.fiercemanul.fiercedecoration.data.registries.BlockMaterialTag;
import net.fiercemanul.fiercedecoration.data.FDItems;
import net.fiercemanul.fiercesource.data.FSRecipeProvider;
import net.fiercemanul.fiercesource.data.registries.FSBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;


public class RecipeGen extends FSRecipeProvider {


    public static final HashSet<BiConsumer<RecipeGen, RecipeOutput>> ROWS = new HashSet<>();
    public RecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        soulCrystalOrnament(recipeOutput);
        pC(recipeOutput);
        bookAndLamp(recipeOutput);
        starBlock(recipeOutput);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.ITEM_FRAME_SHELL_THIN.get(), Items.GLASS, 2);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.ITEM_FRAME_SHELL_BIG.get(), Items.GLASS);
        polished(recipeOutput, RecipeCategory.DECORATIONS, FDItems.GLOWSTONE_LAMP.get(), Items.GLOWSTONE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.GLOWSTONE_LAMP.get(), Items.GLOWSTONE);
        reinforcedLamp(recipeOutput, FDItems.REINFORCED_SMOOTH_GLOWSTONE, FDItems.GLOWSTONE_LAMP);
        reinforcedLamp(recipeOutput, FDItems.REINFORCED_SEA_LANTERN, Items.SEA_LANTERN);
        lightTube(recipeOutput);
        lightPlate(recipeOutput);
        greenFunRoof(recipeOutput);
        fireplaceHeart(recipeOutput);
        waterloggedCobblestone(recipeOutput);
        heavyChains(recipeOutput);
        neoForge(recipeOutput);
        rottenFleshBlock(recipeOutput);
        foxCarrot(recipeOutput);
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_GRASS_BLOCK.get(), Items.GRASS_BLOCK, 2);
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_PATH.get(), Items.DIRT_PATH, 2);
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_PODZOL.get(), Items.PODZOL, 2);
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_DIRT.get(), Items.DIRT, 2);
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, FDItems.HALF_MYCELIUM.get(), Items.MYCELIUM, 2);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.FIREWOOD.get(), Items.OAK_LOG, 2);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.ROCK_PATH, Items.COBBLESTONE, 16);
        wallFlowerPot(recipeOutput, FDItems.WALL_FLOWER_POT_A.get(), Items.AZURE_BLUET, Items.DANDELION, Items.POPPY);
        wallFlowerPot(recipeOutput, FDItems.WALL_FLOWER_POT_B.get(), Items.LILY_OF_THE_VALLEY, Items.CORNFLOWER, Items.OXEYE_DAISY);
        wallFlowerPot(recipeOutput, FDItems.WALL_FLOWER_POT_C.get(), Items.RED_TULIP, Items.PINK_TULIP, Items.ORANGE_TULIP);
        wallFlowerPot(recipeOutput, FDItems.WALL_FLOWER_POT_D.get(), Items.BLUE_ORCHID, Items.ALLIUM, Items.WHITE_TULIP);
        wallFlowerPot(recipeOutput, FDItems.WALL_FLOWER_POT_E.get(), Items.CRIMSON_FUNGUS, Items.WITHER_ROSE, Items.WARPED_FUNGUS);
        wallFlowerPot(recipeOutput, FDItems.WALL_FLOWER_POT_F.get(), Items.BROWN_MUSHROOM, Items.DEAD_BUSH, Items.RED_MUSHROOM);
        rainbow(recipeOutput, FDItems.RAINBOW_WOOL, Items.RED_WOOL, Items.GREEN_WOOL, Items.BLUE_WOOL);
        rainbow(recipeOutput, FDItems.RAINBOW_TERRACOTTA, Items.RED_TERRACOTTA, Items.GREEN_TERRACOTTA, Items.BLUE_TERRACOTTA);
        rainbow(recipeOutput, FDItems.RAINBOW_CONCRETE, Items.RED_CONCRETE, Items.GREEN_CONCRETE, Items.BLUE_CONCRETE);
        rainbow(
                recipeOutput,
                FDItems.RAINBOW_SEA_LANTERN,
                BlockBulkRegister.getColoredSeaLamp(DyeColor.RED),
                BlockBulkRegister.getColoredSeaLamp(DyeColor.GREEN),
                BlockBulkRegister.getColoredSeaLamp(DyeColor.BLUE)
        );
        rainbow(
                recipeOutput,
                FDItems.RAINBOW_REINFORCED_SEA_LANTERN,
                BlockBulkRegister.getColoredReinforcedSeaLamp(DyeColor.RED),
                BlockBulkRegister.getColoredReinforcedSeaLamp(DyeColor.GREEN),
                BlockBulkRegister.getColoredReinforcedSeaLamp(DyeColor.BLUE)
        );
        rainbow(recipeOutput, FDItems.RAINBOW_GLASS, Items.RED_STAINED_GLASS, Items.GREEN_STAINED_GLASS, Items.BLUE_STAINED_GLASS);
        doubleBlock(recipeOutput, FDItems.OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE, Items.OAK_PLANKS, Items.LIGHT_GRAY_CONCRETE);
        doubleBlock(recipeOutput, FDItems.SPRUCE_PLANKS_AND_GRAY_CONCRETE, Items.SPRUCE_PLANKS, Items.GRAY_CONCRETE);
        doubleBlock(recipeOutput, FDItems.OAK_PLANKS_AND_SPRUCE_PLANKS, Items.OAK_PLANKS, Items.SPRUCE_PLANKS);
        doubleBlock(recipeOutput, FDItems.WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE, Items.WHITE_CONCRETE, Items.LIGHT_GRAY_CONCRETE);
        doubleBlock(recipeOutput, FDItems.DEEPSLATE_TILES_AND_SPRUCE_PLANKS, Items.DEEPSLATE_TILES, Items.SPRUCE_PLANKS);
        doubleBlock(recipeOutput, FDItems.DEEPSLATE_TILES_AND_MANGROVE_PLANKS, Items.DEEPSLATE_TILES, Items.MANGROVE_PLANKS);
        doubleBlock(recipeOutput, FDItems.DARK_PRISMARINE_AND_SPRUCE_PLANKS, Items.DARK_PRISMARINE, Items.SPRUCE_PLANKS);
        doubleBlock(recipeOutput, FDItems.DARK_PRISMARINE_AND_MANGROVE_PLANKS, Items.DARK_PRISMARINE, Items.MANGROVE_PLANKS);
        doubleBlock(recipeOutput, FDItems.BRICKS_AND_BIRCH_PLANKS, Items.BRICKS, Items.BIRCH_PLANKS);
        fakeBlocks(recipeOutput);
        textureBlocks(recipeOutput);
        colorLamps(recipeOutput);
        craftingBlock(recipeOutput);

        smoothPlanks(recipeOutput, FDItems.SMOOTH_OAK_PLANKS.get(), Items.OAK_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_SPRUCE_PLANKS.get(), Items.SPRUCE_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_BIRCH_PLANKS.get(), Items.BIRCH_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_JUNGLE_PLANKS.get(), Items.JUNGLE_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_ACACIA_PLANKS.get(), Items.ACACIA_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_DARK_OAK_PLANKS.get(), Items.DARK_OAK_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_MANGROVE_PLANKS.get(), Items.MANGROVE_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_BAMBOO_PLANKS.get(), Items.BAMBOO_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_CHERRY_PLANKS.get(), Items.CHERRY_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_CRIMSON_PLANKS.get(), Items.CRIMSON_PLANKS);
        smoothPlanks(recipeOutput, FDItems.SMOOTH_WARPED_PLANKS.get(), Items.WARPED_PLANKS);

        ROWS.forEach(consumer -> consumer.accept(this, recipeOutput));

    }

    public void buildStair(RecipeOutput recipeOutput, ItemLike stair, BlockMaterial material) {
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, stair, material.getBlock());
        if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)) stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, stair, material.getBlock());
    }

    public void buildSlab(RecipeOutput recipeOutput, ItemLike slab, BlockMaterial material) {
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, material.getBlock());
        if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)) stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, material.getBlock(), 2);
    }

    public void buildFence(RecipeOutput recipeOutput, ItemLike fence, BlockMaterial material) {
        Block block = material.getBlock();
        fenceBuilder(fence, Ingredient.of(block)).unlockedBy(getHasName(block), has(block)).save(recipeOutput);
    }

    public void buildFenceGate(RecipeOutput recipeOutput, ItemLike fenceGate, BlockMaterial material) {
        Block block = material.getBlock();
        fenceGateBuilder(fenceGate, Ingredient.of(block)).unlockedBy(getHasName(block), has(block)).save(recipeOutput);
    }

    public void buildPressurePlate(RecipeOutput recipeOutput, ItemLike pressurePlate, BlockMaterial material) {
        pressurePlate(recipeOutput, pressurePlate, material.getBlock());
    }

    public void buildButton(RecipeOutput recipeOutput, ItemLike button, BlockMaterial material) {
        Block block = material.getBlock();
        buttonBuilder(button, Ingredient.of(block)).unlockedBy(getHasName(block), has(block)).save(recipeOutput);
    }

    public void cutOneToOne(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, material);
    }

    public void cutOneToTwo(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, material, 2);
    }

    public void cutD(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, result, material);
    }

    public void cutD(RecipeOutput recipeOutput, ItemLike result, ItemLike material, int count) {
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, result, material, count);
    }

    public void cut(RecipeOutput recipeOutput, ItemLike result, ItemLike material, int count) {
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, result, material, count);
    }

    public void cut(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike material, int count) {
        stonecutting(recipeOutput, category, result, material, count);
    }

    public void glassWindow(RecipeOutput recipeOutput, ItemLike result, ItemLike item) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, result, 4)
                .define('X', item)
                .define('Y', Items.GLASS)
                .pattern(" X ")
                .pattern("XYX")
                .pattern(" X ")
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .save(recipeOutput);
    }

    public void woolSofa(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, result, 5)
                .define('#', material)
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    public void buildGlassLamp(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, result)
                .define('X', Items.GLASS)
                .define('Y', material)
                .pattern("X")
                .pattern("Y")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    public void colorSeaLantern(RecipeOutput recipeOutput, ItemLike result, ItemLike dye) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, result, 8)
                .define('X', Items.SEA_LANTERN)
                .define('D', dye)
                .pattern("XXX")
                .pattern("XDX")
                .pattern("XXX")
                .unlockedBy(getHasName(Blocks.SEA_LANTERN), has(Blocks.SEA_LANTERN))
                .save(recipeOutput);
    }

    public void reinforcedLamp(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, result)
                .define('#', ItemTags.PLANKS)
                .define('X', Items.IRON_INGOT)
                .define('O', material)
                .pattern("X#X")
                .pattern("#O#")
                .pattern("X#X")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private void soulCrystalOrnament(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.SOUL_CRYSTAL_ORNAMENT)
                .define('#', FSBlocks.SMALL_SOUL_CRYSTAL)
                .define('X', FSBlocks.SOUL_CRYSTAL_SHARD)
                .define('P', FDItems.SMOOTH_MANGROVE_PLANKS)
                .pattern(" X ")
                .pattern("X#X")
                .pattern(" P ")
                .unlockedBy(getHasName(FSBlocks.SMALL_SOUL_CRYSTAL), has(FSBlocks.SMALL_SOUL_CRYSTAL))
                .save(recipeOutput);
    }

    private void pC(RecipeOutput recipeOutput) {
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
                .save(recipeOutput);
        oneToOne(recipeOutput, RecipeCategory.DECORATIONS, FDItems.LAPTOP_TERMINAL.get(), FDItems.PORTABLE_WORKSTATION.get(), "");
        oneToOne(recipeOutput, RecipeCategory.DECORATIONS, FDItems.PORTABLE_WORKSTATION.get(), FDItems.LAPTOP_TERMINAL.get(), "");
    }

    private void bookAndLamp(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.BOOK_AND_LAMP.get())
                .define('B', Items.BOOK)
                .define('C', Items.COPPER_INGOT)
                .define('D', Items.GLOWSTONE_DUST)
                .pattern("D ")
                .pattern("CB")
                .unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
                .save(recipeOutput);
    }

    private void starBlock(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.STAR_BLOCK.get(), 8)
                .define('#', Items.GLASS)
                .define('X', Items.ENDER_EYE)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                .save(recipeOutput);
    }

    private void lightTube(RecipeOutput recipeOutput) {
        Item smooth_glowstone = FDItems.GLOWSTONE_LAMP.get();
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIGHT_TUBE.get(), 32)
                .define('#', smooth_glowstone)
                .define('X', Items.IRON_INGOT)
                .pattern("X")
                .pattern("#")
                .pattern("X")
                .unlockedBy(getHasName(smooth_glowstone), has(smooth_glowstone))
                .save(recipeOutput);
    }

    private void lightPlate(RecipeOutput recipeOutput) {
        Item smooth_glowstone = FDItems.GLOWSTONE_LAMP.get();
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIGHT_PLATE.get(), 16)
                .define('#', smooth_glowstone)
                .define('X', Items.IRON_INGOT)
                .pattern("X#X")
                .unlockedBy(getHasName(smooth_glowstone), has(smooth_glowstone))
                .save(recipeOutput);
    }

    private void greenFunRoof(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.GREEN_FUN_ROOF.get(), 8)
                .define('#', Items.AZALEA_LEAVES)
                .define('X', Items.OAK_LOG)
                .pattern("###")
                .pattern("X X")
                .unlockedBy(getHasName(Items.OAK_LOG), has(Items.OAK_LOG))
                .save(recipeOutput);
    }

    private void fireplaceHeart(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.FIREPLACE_HEART.get(), 1)
                .define('#', Items.IRON_INGOT)
                .define('X', Items.IRON_BARS)
                .define('C', ItemTags.COALS)
                .pattern("XCX")
                .pattern("###")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
    }

    private void foxCarrot(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FDItems.FOX_CARROT_SHEAF)
                           .define('#', FDItems.FOX_CARROT)
                           .pattern("##")
                           .pattern("##")
                           .unlockedBy(getHasName(FDItems.FOX_CARROT), has(FDItems.FOX_CARROT))
                           .save(recipeOutput);
        oneToOne(recipeOutput, RecipeCategory.DECORATIONS, FDItems.FOX_CARROT, FDItems.FOX_CARROT_SHEAF, "", 4);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FDItems.FOX_CARROT_BASKET)
                           .define('#', FDItems.FOX_CARROT_SHEAF)
                           .pattern("###")
                           .pattern("# #")
                           .pattern("###")
                           .unlockedBy(getHasName(FDItems.FOX_CARROT_SHEAF), has(FDItems.FOX_CARROT_SHEAF))
                           .save(recipeOutput);
        oneToOne(recipeOutput, RecipeCategory.DECORATIONS, FDItems.FOX_CARROT_SHEAF, FDItems.FOX_CARROT_BASKET, "", 8);
    }

    private void craftingBlock(RecipeOutput recipeOutput) {
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.CRAFTING_BLOCK, Items.CRAFTING_TABLE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.CRAFTING_DESK, Items.CRAFTING_TABLE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.CRAFTING_PAD, Items.CRAFTING_TABLE, 16);
    }

    private void rottenFleshBlock(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.ROTTEN_FLESH_BLOCK.get())
                .define('#', Items.ROTTEN_FLESH)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH))
                .save(recipeOutput);
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(FDItems.ROTTEN_FLESH_BLOCK), RecipeCategory.MISC, Items.LEATHER, 0.3F, 400)
                .unlockedBy(getHasName(FDItems.ROTTEN_FLESH_BLOCK), has(FDItems.ROTTEN_FLESH_BLOCK))
                .save(recipeOutput, getItemName(Items.LEATHER) + "_from_smelting_" + getItemName(FDItems.ROTTEN_FLESH_BLOCK));
        SimpleCookingRecipeBuilder
                .smoking(Ingredient.of(FDItems.ROTTEN_FLESH_BLOCK), RecipeCategory.MISC, Items.LEATHER, 0.3F, 200)
                .unlockedBy(getHasName(FDItems.ROTTEN_FLESH_BLOCK), has(FDItems.ROTTEN_FLESH_BLOCK))
                .save(recipeOutput, getItemName(Items.LEATHER) + "_from_smoking_" + getItemName(FDItems.ROTTEN_FLESH_BLOCK));
        SimpleCookingRecipeBuilder
                .campfireCooking(Ingredient.of(FDItems.ROTTEN_FLESH_BLOCK), RecipeCategory.MISC, Items.LEATHER, 0.3F, 1200)
                .unlockedBy(getHasName(FDItems.ROTTEN_FLESH_BLOCK), has(FDItems.ROTTEN_FLESH_BLOCK))
                .save(recipeOutput, getItemName(Items.LEATHER) + "_from_camping_" + getItemName(FDItems.ROTTEN_FLESH_BLOCK));
    }

    private static void waterloggedCobblestone(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.WATERLOGGED_COBBLESTONE.get())
                .define('#', Items.MOSSY_COBBLESTONE_SLAB)
                .define('X', Items.WATER_BUCKET)
                .pattern("X#")
                .pattern("#X")
                .unlockedBy("in_water", insideOf(Blocks.WATER))
                .save(recipeOutput);
    }

    private static void wallFlowerPot(RecipeOutput recipeOutput, ItemLike result, Item flowerA, Item flowerB, Item flowerC) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, result)
                .define('a', flowerA)
                .define('b', flowerB)
                .define('c', flowerC)
                .define('X', Items.FLOWER_POT)
                .pattern("abc")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.FLOWER_POT), has(Items.FLOWER_POT))
                .save(recipeOutput);
    }

    private static void heavyChains(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.HEAVY_CHAINS)
                .define('#', Items.CHAIN)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(Items.CHAIN), has(Items.CHAIN))
                .save(recipeOutput);
    }

    private static void neoForge(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.NEO_FORGE)
                .define('#', Items.IRON_INGOT)
                .define('a', Items.ORANGE_WOOL)
                .define('b', Items.WHITE_WOOL)
                .pattern("aab")
                .pattern(" # ")
                .pattern("###")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
    }

    private static void fakeBlocks(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.FAKE_HOPPER, 10)
                .define('#', Items.IRON_INGOT)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_FURNACE, Items.FURNACE, 8);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_FURNACE)
                .define('a', FDItems.FAKE_FURNACE)
                .define('b', Items.GLOWSTONE_DUST)
                .pattern("a")
                .pattern("b")
                .unlockedBy(getHasName(FDItems.FAKE_FURNACE), has(FDItems.FAKE_FURNACE))
                .save(recipeOutput);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.FAKE_BLAST_FURNACE, 5)
                .define('#', Blocks.SMOOTH_STONE)
                .define('X', FDItems.FAKE_FURNACE)
                .define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("IXI")
                .pattern("###")
                .unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE))
                .save(recipeOutput);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_BLAST_FURNACE)
                .define('a', FDItems.FAKE_BLAST_FURNACE)
                .define('b', Items.GLOWSTONE_DUST)
                .pattern("a")
                .pattern("b")
                .unlockedBy(getHasName(FDItems.FAKE_BLAST_FURNACE), has(FDItems.FAKE_BLAST_FURNACE))
                .save(recipeOutput);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.FAKE_SMOKER, 8)
                .define('#', ItemTags.LOGS)
                .define('X', FDItems.FAKE_FURNACE)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy("has_furnace", has(Blocks.FURNACE))
                .save(recipeOutput);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_SMOKER)
                .define('a', FDItems.FAKE_SMOKER)
                .define('b', Items.GLOWSTONE_DUST)
                .pattern("a")
                .pattern("b")
                .unlockedBy(getHasName(FDItems.FAKE_SMOKER), has(FDItems.FAKE_SMOKER))
                .save(recipeOutput);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_BARREL, Items.BARREL, 8);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FDItems.FAKE_CAMPFIRE, 8)
                           .define('L', ItemTags.LOGS)
                           .define('S', Items.STICK)
                           .pattern(" S ")
                           .pattern("S S")
                           .pattern("LLL")
                           .unlockedBy("has_stick", has(Items.STICK))
                           .unlockedBy("has_coal", has(ItemTags.COALS))
                           .save(recipeOutput);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_CAMPFIRE, Items.CAMPFIRE, 8);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.LIT_FAKE_SOUL_CAMPFIRE, Items.SOUL_CAMPFIRE, 8);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_CHEST, Items.CHEST, 8);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_CHISELED_BOOKSHELF, Items.CHISELED_BOOKSHELF, 8);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_LECTERN, Items.LECTERN, 16);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.FAKE_BEEHIVE, Items.BEEHIVE, 16);
        fakeBlocks(recipeOutput, FDItems.FAKE_IRON_BLOCK, Items.GRAY_DYE);
        fakeBlocks(recipeOutput, FDItems.FAKE_GOLD_BLOCK, Items.YELLOW_DYE);
        fakeBlocks(recipeOutput, FDItems.FAKE_DIAMOND_BLOCK, Items.LIGHT_BLUE_DYE);
        fakeBlocks(recipeOutput, FDItems.FAKE_NETHERITE_BLOCK, Items.BROWN_DYE);
        fakeBlocks(recipeOutput, FDItems.FAKE_BEDROCK, Items.BLACK_DYE);
    }

    private static void fakeBlocks(RecipeOutput recipeOutput, ItemLike fakeBlock, Item dye) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, fakeBlock, 8)
                           .define('S', Items.STONE)
                           .define('D', dye)
                           .pattern("SSS")
                           .pattern("SDS")
                           .pattern("SSS")
                           .unlockedBy(getHasName(dye), has(dye))
                           .save(recipeOutput);
    }

    private static void textureBlocks(RecipeOutput recipeOutput) {
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_CHISELED_BOOKSHELF, FDItems.FAKE_CHISELED_BOOKSHELF);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_CHISELED_BOOKSHELF_TOP, FDItems.FAKE_CHISELED_BOOKSHELF);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_CHISELED_BOOKSHELF_SIDE, FDItems.FAKE_CHISELED_BOOKSHELF);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_LOOM, Items.LOOM);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_BEEHIVE_TOP, FDItems.FAKE_BEEHIVE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_SMITHING_TABLE_BOTTOM, Items.SMITHING_TABLE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_COMPOSTER_BOTTOM, Items.COMPOSTER);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_BEE_NEST_TOP, FDItems.TEXTURE_BEEHIVE_TOP);

        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_FURNACE, FDItems.FAKE_FURNACE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_FURNACE_TOP, FDItems.FAKE_FURNACE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_BLAST_FURNACE, FDItems.FAKE_BLAST_FURNACE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_BLAST_FURNACE_TOP, FDItems.FAKE_BLAST_FURNACE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_SMOKER, FDItems.FAKE_SMOKER);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_SMITHING_TABLE_TOP, Items.SMITHING_TABLE);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_LODESTONE, Items.CHISELED_STONE_BRICKS);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_LODESTONE_SIDE, Items.CHISELED_STONE_BRICKS);
        stonecutting(recipeOutput, RecipeCategory.DECORATIONS, FDItems.TEXTURE_LODESTONE_TOP, Items.CHISELED_STONE_BRICKS);
    }

    private static void rainbow(RecipeOutput recipeOutput, ItemLike rainbowBlock, ItemLike r, ItemLike g, ItemLike b) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, rainbowBlock, 3)
                .define('#', FSBlocks.SOUL_CRYSTAL_SHARD)
                .define('r', r)
                .define('g', g)
                .define('b', b)
                .pattern(" r ")
                .pattern(" # ")
                .pattern("g b")
                .unlockedBy(getHasName(FSBlocks.SOUL_CRYSTAL_SHARD), has(FSBlocks.SOUL_CRYSTAL_SHARD))
                .save(recipeOutput);
    }

    private static void colorLamp(RecipeOutput recipeOutput, ItemLike lamp, ItemLike dye) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, lamp)
                .define('#', FSBlocks.SOUL_CRYSTAL_SHARD)
                .define('d', dye)
                .pattern(" # ")
                .pattern("#d#")
                .pattern(" # ")
                .unlockedBy(getHasName(FSBlocks.SOUL_CRYSTAL_SHARD), has(FSBlocks.SOUL_CRYSTAL_SHARD))
                .save(recipeOutput);
    }

    private static void colorLamps(RecipeOutput recipeOutput) {
        colorLamp(recipeOutput, FDItems.RED_LAMP, Items.RED_DYE);
        colorLamp(recipeOutput, FDItems.GREEN_LAMP, Items.GREEN_DYE);
        colorLamp(recipeOutput, FDItems.BLUE_LAMP, Items.BLUE_DYE);
        colorLamp(recipeOutput, FDItems.YELLOW_LAMP, Items.YELLOW_DYE);
        colorLamp(recipeOutput, FDItems.CYAN_LAMP, Items.CYAN_DYE);
        colorLamp(recipeOutput, FDItems.PURPLE_LAMP, Items.PURPLE_DYE);
        rainbow(recipeOutput, FDItems.RAINBOW_LAMP, FDItems.RED_LAMP, FDItems.GREEN_LAMP, FDItems.BLUE_LAMP);
    }

    private static void doubleBlock(RecipeOutput recipeOutput, ItemLike doubleBlock, ItemLike up, ItemLike down) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, doubleBlock, 2)
                .define('u', up)
                .define('d', down)
                .pattern("u")
                .pattern("d")
                .unlockedBy(getHasName(up), has(up))
                .save(recipeOutput);
    }

    private void smoothPlanks(RecipeOutput recipeOutput, ItemLike smoothPlanks, ItemLike wood) {
        cut(recipeOutput, RecipeCategory.BUILDING_BLOCKS, smoothPlanks, wood);
    }

    public void woodenGuardrail(RecipeOutput recipeOutput, ItemLike woodenGuardrail, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, woodenGuardrail, 4)
                .define('X', material)
                .define('#', Items.STICK)
                .pattern("#X#")
                .pattern(" # ")
                .group("guardrail")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    public void woodenGuardrailB(RecipeOutput recipeOutput, ItemLike woodenGuardrail, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, woodenGuardrail, 4)
                .define('X', material)
                .define('#', Items.STICK)
                .pattern("X#X")
                .group("guardrail")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    public void pillar12px(RecipeOutput recipeOutput, ItemLike pillar, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.BUILDING_BLOCKS, pillar, 5)
                .define('#', material)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .group("pillar")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, pillar, material);
    }

}
