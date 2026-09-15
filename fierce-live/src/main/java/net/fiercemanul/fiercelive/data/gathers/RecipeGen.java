package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLItems;
import net.fiercemanul.fiercelive.data.registries.BlockMaterial;
import net.fiercemanul.fiercelive.data.registries.BlockMaterialTag;
import net.fiercemanul.fiercelive.data.tags.FLItemTags;
import net.fiercemanul.fiercesource.data.FSBlocks;
import net.fiercemanul.fiercesource.data.FSItems;
import net.fiercemanul.fiercesource.data.FSRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.fiercemanul.fiercelive.data.FLBlocks.*;
import static net.minecraft.data.recipes.RecipeCategory.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

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
        //starBlock(recipeOutput);
        stonecutting(recipeOutput, DECORATIONS, ITEM_FRAME_SHELL_THIN, Items.GLASS, 2);
        stonecutting(recipeOutput, DECORATIONS, ITEM_FRAME_SHELL_BIG, Items.GLASS);
        polished(recipeOutput, DECORATIONS, GLOWSTONE_LAMP, Items.GLOWSTONE);
        stonecutting(recipeOutput, DECORATIONS, GLOWSTONE_LAMP, Items.GLOWSTONE);
        reinforcedLamp(recipeOutput, REINFORCED_GLOWSTONE_LAMP, GLOWSTONE_LAMP);
        reinforcedLamp(recipeOutput, REINFORCED_SEA_LANTERN, Items.SEA_LANTERN);
        lightTube(recipeOutput);
        lightPlate(recipeOutput);
        stonecutting(recipeOutput, DECORATIONS, GLOW_PEARL, Items.GLOWSTONE, 4);
        glowPearlArrow(recipeOutput);
        greenFunRoof(recipeOutput);
        fireplaceHeart(recipeOutput);
        waterloggedCobblestone(recipeOutput);
        heavyChains(recipeOutput);
        neoForge(recipeOutput);
        meatBlock(recipeOutput);
        foxCarrot(recipeOutput);
        ironScaffolding(recipeOutput);
        stonecutting(recipeOutput, DECORATIONS, QUARTZ_SQUAT_TOILET, Items.QUARTZ_BLOCK);
        stonecutting(recipeOutput, DECORATIONS, IRON_SQUAT_TOILET, Items.IRON_BLOCK);
        stonecutting(recipeOutput, DECORATIONS, QUARTZ_TOILET, Items.QUARTZ_BLOCK);
        stonecutting(recipeOutput, DECORATIONS, IRON_TOILET, Items.IRON_BLOCK);
        stonecutting(recipeOutput, DECORATIONS, SINK, Items.QUARTZ_BLOCK);
        shaped(DECORATIONS, MANGROVE_SINK)
                .define('Y', SINK)
                .define('X', SMOOTH_MANGROVE_PLANKS)
                .pattern("Y")
                .pattern("X")
                .unlockedBy(getHasName(SINK), has(SINK))
                .save(recipeOutput);
        mirror(recipeOutput);
        stonecutting(recipeOutput, DECORATIONS, SHOWER_SET, Items.IRON_BARS);
        stonecutting(recipeOutput, BUILDING_BLOCKS, HALF_GRASS_BLOCK, Items.GRASS_BLOCK, 2);
        stonecutting(recipeOutput, BUILDING_BLOCKS, HALF_DIRT_PATH, Items.DIRT_PATH, 2);
        stonecutting(recipeOutput, BUILDING_BLOCKS, HALF_PODZOL, Items.PODZOL, 2);
        stonecutting(recipeOutput, BUILDING_BLOCKS, HALF_DIRT, Items.DIRT, 2);
        stonecutting(recipeOutput, BUILDING_BLOCKS, HALF_MYCELIUM, Items.MYCELIUM, 2);
        stonecutting(recipeOutput, DECORATIONS, FIREWOOD, Items.OAK_LOG, 2);
        stonecutting(recipeOutput, DECORATIONS, ROCK_PATH, Items.STONE, 16);
        stonecutting(recipeOutput, DECORATIONS, IRON_GUARDRAIL, Items.IRON_BARS);
        stonecutting(recipeOutput, DECORATIONS, IRON_FRAME, Items.IRON_BARS);
        stonecutting(recipeOutput, DECORATIONS, IRON_CORRIDOR, Items.IRON_BARS);
        corridorSlab(recipeOutput, IRON_CORRIDOR_SLAB, IRON_CORRIDOR);
        stonecutting(recipeOutput, DECORATIONS, IRON_CORRIDOR_STAIRS, IRON_CORRIDOR, 2);
        stonecutting(recipeOutput, DECORATIONS, IRON_LADDER, Items.IRON_BARS);
        stonecutting(recipeOutput, BUILDING_BLOCKS, SPIRAL_STONE, Items.STONE);
        stonecutting(recipeOutput, BUILDING_BLOCKS, SPIRAL_DEEPSLATE, Items.DEEPSLATE);
        stonecutting(recipeOutput, BUILDING_BLOCKS, SPIRAL_DEEPSLATE, Items.COBBLED_DEEPSLATE);
        stonecutting(recipeOutput, BUILDING_BLOCKS, SPIRAL_DEEPSLATE, Items.POLISHED_DEEPSLATE);
        stonecutting(recipeOutput, BUILDING_BLOCKS, SPIRAL_TUFF, Items.TUFF);
        stonecutting(recipeOutput, BUILDING_BLOCKS, SPIRAL_TUFF, Items.POLISHED_TUFF);
        stonecutting(recipeOutput, BUILDING_BLOCKS, SPIRAL_BLACKSTONE, Items.BLACKSTONE);
        stonecutting(recipeOutput, BUILDING_BLOCKS, SPIRAL_BLACKSTONE, Items.POLISHED_BLACKSTONE);
        stonecutting(recipeOutput, BUILDING_BLOCKS, SPIRAL_END_STONE, Items.END_STONE);
        stonecutting(recipeOutput, BUILDING_BLOCKS, INTERLACE_BRICKS, Items.BRICKS);
        stonecutting(recipeOutput, BUILDING_BLOCKS, BLACK_IRON_BLOCK, Items.IRON_BLOCK);
        wallFlowerPot(recipeOutput, A_WALL_FLOWER_POT, Items.AZURE_BLUET, Items.DANDELION, Items.POPPY);
        wallFlowerPot(recipeOutput, B_WALL_FLOWER_POT, Items.LILY_OF_THE_VALLEY, Items.CORNFLOWER, Items.OXEYE_DAISY);
        wallFlowerPot(recipeOutput, C_WALL_FLOWER_POT, Items.RED_TULIP, Items.PINK_TULIP, Items.ORANGE_TULIP);
        wallFlowerPot(recipeOutput, D_WALL_FLOWER_POT, Items.BLUE_ORCHID, Items.ALLIUM, Items.WHITE_TULIP);
        wallFlowerPot(recipeOutput, E_WALL_FLOWER_POT, Items.CRIMSON_FUNGUS, Items.WITHER_ROSE, Items.WARPED_FUNGUS);
        wallFlowerPot(recipeOutput, F_WALL_FLOWER_POT, Items.BROWN_MUSHROOM, Items.DEAD_BUSH, Items.RED_MUSHROOM);
        rainbowDye(recipeOutput);
        rainbow(recipeOutput, RAINBOW_WOOL, Items.WHITE_WOOL);
        rainbow(recipeOutput, RAINBOW_TERRACOTTA, ItemTags.TERRACOTTA);
        rainbow(recipeOutput, RAINBOW_CONCRETE, Tags.Items.CONCRETES);
        rainbow(recipeOutput, RAINBOW_SEA_LANTERN, Items.SEA_LANTERN);
        rainbow(recipeOutput, RAINBOW_REINFORCED_SEA_LANTERN, FLItemTags.REINFORCED_SEA_LANTERNS);
        rainbow(recipeOutput, RAINBOW_GLASS, Tags.Items.GLASS_BLOCKS);
        stainedGlassPaneFromStainedGlass(recipeOutput, RAINBOW_GLASS_PANE, RAINBOW_GLASS);
        stainedGlassPaneFromGlassPaneAndDye(recipeOutput, RAINBOW_GLASS_PANE, FLItems.RAINBOW_DYE);
        doubleBlock(recipeOutput, OAK_PLANKS_AND_LIGHT_GRAY_CONCRETE, Items.OAK_PLANKS, Items.LIGHT_GRAY_CONCRETE);
        doubleBlock(recipeOutput, SPRUCE_PLANKS_AND_GRAY_CONCRETE, Items.SPRUCE_PLANKS, Items.GRAY_CONCRETE);
        doubleBlock(recipeOutput, OAK_PLANKS_AND_SPRUCE_PLANKS, Items.OAK_PLANKS, Items.SPRUCE_PLANKS);
        doubleBlock(recipeOutput, WHITE_CONCRETE_AND_LIGHT_GRAY_CONCRETE, Items.WHITE_CONCRETE, Items.LIGHT_GRAY_CONCRETE);
        doubleBlock(recipeOutput, DEEPSLATE_TILES_AND_SPRUCE_PLANKS, Items.DEEPSLATE_TILES, Items.SPRUCE_PLANKS);
        doubleBlock(recipeOutput, DEEPSLATE_TILES_AND_MANGROVE_PLANKS, Items.DEEPSLATE_TILES, Items.MANGROVE_PLANKS);
        doubleBlock(recipeOutput, DARK_PRISMARINE_AND_SPRUCE_PLANKS, Items.DARK_PRISMARINE, Items.SPRUCE_PLANKS);
        doubleBlock(recipeOutput, DARK_PRISMARINE_AND_MANGROVE_PLANKS, Items.DARK_PRISMARINE, Items.MANGROVE_PLANKS);
        doubleBlock(recipeOutput, BRICKS_AND_BIRCH_PLANKS, Items.BRICKS, Items.BIRCH_PLANKS);
        concrete(recipeOutput);
        stonecutting(recipeOutput, BUILDING_BLOCKS, VILLAGE_PATTERNED_TILES, Items.LIGHT_GRAY_GLAZED_TERRACOTTA);
        stonecutting(recipeOutput, BUILDING_BLOCKS, VILLAGE_MOSAIC_TILES, Items.LIGHT_GRAY_GLAZED_TERRACOTTA);
        shaped(BUILDING_BLOCKS, BIG_FLOWER_POT)
                .define('Y', Items.DIRT)
                .define('X', VILLAGE_MOSAIC_TILES)
                .pattern("Y")
                .pattern("X")
                .unlockedBy(getHasName(VILLAGE_MOSAIC_TILES), has(VILLAGE_MOSAIC_TILES))
                .save(recipeOutput);
        fakeBlocks(recipeOutput);
        textureBlocks(recipeOutput);
        colorLamps(recipeOutput);
        craftingBlock(recipeOutput);

        smoothPlanks(recipeOutput, SMOOTH_OAK_PLANKS, Items.OAK_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_SPRUCE_PLANKS, Items.SPRUCE_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_BIRCH_PLANKS, Items.BIRCH_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_JUNGLE_PLANKS, Items.JUNGLE_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_ACACIA_PLANKS, Items.ACACIA_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_DARK_OAK_PLANKS, Items.DARK_OAK_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_MANGROVE_PLANKS, Items.MANGROVE_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_BAMBOO_PLANKS, Items.BAMBOO_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_CHERRY_PLANKS, Items.CHERRY_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_CRIMSON_PLANKS, Items.CRIMSON_PLANKS);
        smoothPlanks(recipeOutput, SMOOTH_WARPED_PLANKS, Items.WARPED_PLANKS);

        toolsAndCombat(recipeOutput);
        food(recipeOutput);

        duper(recipeOutput);


        ROWS.forEach(consumer -> consumer.accept(this, recipeOutput));

    }

    public void buildStair(RecipeOutput recipeOutput, ItemLike stair, BlockMaterial material) {
        stairBuilder(stair, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(recipeOutput);
        if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)) stonecutting(recipeOutput, BUILDING_BLOCKS, stair, material.getBlock());
    }

    public void buildSlab(RecipeOutput recipeOutput, ItemLike slab, BlockMaterial material) {
        slab(recipeOutput, BUILDING_BLOCKS, slab, material.getBlock());
        if (material.hasTag(BlockMaterialTag.TOOL_PICKAXE)) stonecutting(recipeOutput, BUILDING_BLOCKS, slab, material.getBlock(), 2);
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

    public void buildWall(RecipeOutput recipeOutput, ItemLike wall, BlockMaterial material) {
        wall(recipeOutput, BUILDING_BLOCKS, wall, material.getBlock());
        cutOneToOne(recipeOutput, wall, material);
    }

    public void cutOneToOne(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        stonecutting(recipeOutput, BUILDING_BLOCKS, result, material);
    }

    public void cutOneToTwo(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        stonecutting(recipeOutput, BUILDING_BLOCKS, result, material, 2);
    }

    public void cutD(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        stonecutting(recipeOutput, DECORATIONS, result, material);
    }

    public void cutD(RecipeOutput recipeOutput, ItemLike result, ItemLike material, int count) {
        stonecutting(recipeOutput, DECORATIONS, result, material, count);
    }

    public void cut(RecipeOutput recipeOutput, ItemLike result, ItemLike material, int count) {
        stonecutting(recipeOutput, BUILDING_BLOCKS, result, material, count);
    }

    public void cut(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike material, int count) {
        stonecutting(recipeOutput, category, result, material, count);
    }

    public void roof(RecipeOutput recipeOutput, ItemLike result, ItemLike material, ItemLike material2) {
        ShapedRecipeBuilder
                .shaped(BUILDING_BLOCKS, result, 4)
                .define('X', material)
                .define('#', material2)
                .pattern("X ")
                .pattern("#X")
                .pattern(" #")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    public void glassWindow(RecipeOutput recipeOutput, ItemLike result, ItemLike item) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, result, 4)
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
                .shaped(DECORATIONS, result, 5)
                .define('#', material)
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    public void buildGlassLamp(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, result)
                .define('X', Items.GLASS)
                .define('Y', material)
                .pattern("X")
                .pattern("Y")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    public void colorDyed(RecipeOutput recipeOutput, ItemLike result, ItemLike material, ItemLike dye) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, result, 8)
                .define('X', material)
                .define('D', dye)
                .pattern("XXX")
                .pattern("XDX")
                .pattern("XXX")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    public void reinforcedLamp(RecipeOutput recipeOutput, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, result)
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
                .shaped(DECORATIONS, SOUL_CRYSTAL_ORNAMENT)
                .define('#', FSBlocks.SMALL_SOUL_CRYSTAL)
                .define('X', FSItems.SOUL_CRYSTAL_SHARD)
                .define('P', SMOOTH_MANGROVE_PLANKS)
                .pattern(" X ")
                .pattern("X#X")
                .pattern(" P ")
                .unlockedBy(getHasName(FSBlocks.SMALL_SOUL_CRYSTAL), has(FSBlocks.SMALL_SOUL_CRYSTAL))
                .save(recipeOutput);
    }

    private void pC(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, PORTABLE_WORKSTATION.get())
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.GLASS_PANE)
                .define('C', Items.GLOWSTONE_DUST)
                .define('D', Items.QUARTZ)
                .pattern("ABA")
                .pattern("ACA")
                .pattern("ADA")
                .unlockedBy("has_quartz", has(Items.QUARTZ))
                .save(recipeOutput);
        oneToOne(recipeOutput, DECORATIONS, LAPTOP_TERMINAL.get(), PORTABLE_WORKSTATION.get(), "");
        oneToOne(recipeOutput, DECORATIONS, PORTABLE_WORKSTATION.get(), LAPTOP_TERMINAL.get(), "");
    }

    private void bookAndLamp(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, BOOK_AND_LAMP.get())
                .define('B', Items.BOOK)
                .define('C', Items.COPPER_INGOT)
                .define('D', Items.GLOWSTONE_DUST)
                .pattern("D ")
                .pattern("CB")
                .unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
                .save(recipeOutput);
    }

    /*private void starBlock(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, FLBlocks.STAR_BLOCK.get(), 8)
                .define('#', Items.GLASS)
                .define('X', Items.ENDER_EYE)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                .save(recipeOutput);
    }*/

    private void lightTube(RecipeOutput recipeOutput) {
        ItemLike smooth_glowstone = GLOWSTONE_LAMP;
        ShapedRecipeBuilder
                .shaped(DECORATIONS, LIGHT_TUBE.get(), 32)
                .define('#', smooth_glowstone)
                .define('X', Items.IRON_INGOT)
                .pattern("X")
                .pattern("#")
                .pattern("X")
                .unlockedBy(getHasName(smooth_glowstone), has(smooth_glowstone))
                .save(recipeOutput);
    }

    private void lightPlate(RecipeOutput recipeOutput) {
        ItemLike smooth_glowstone = GLOWSTONE_LAMP;
        ShapedRecipeBuilder
                .shaped(DECORATIONS, LIGHT_PLATE.get(), 16)
                .define('#', smooth_glowstone)
                .define('X', Items.IRON_INGOT)
                .pattern("X#X")
                .unlockedBy(getHasName(smooth_glowstone), has(smooth_glowstone))
                .save(recipeOutput);
    }

    private void glowPearlArrow(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, FLItems.GLOW_PEARL_ARROW)
                .define('#', GLOW_PEARL)
                .define('X', Items.STICK)
                .define('Y', Items.FEATHER)
                .pattern("#")
                .pattern("X")
                .pattern("Y")
                .unlockedBy(getHasName(GLOW_PEARL), has(GLOW_PEARL))
                .save(recipeOutput);
    }

    private void greenFunRoof(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, GREEN_FUN_ROOF.get(), 8)
                .define('#', Items.AZALEA_LEAVES)
                .define('X', Items.OAK_LOG)
                .pattern("###")
                .pattern("X X")
                .unlockedBy(getHasName(Items.OAK_LOG), has(Items.OAK_LOG))
                .save(recipeOutput);
    }

    private void fireplaceHeart(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, FIREPLACE_HEART.get(), 1)
                .define('#', Items.IRON_INGOT)
                .define('X', Items.IRON_BARS)
                .define('C', ItemTags.COALS)
                .pattern("XCX")
                .pattern("###")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
    }

    private void foxCarrot(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(DECORATIONS, FOX_CARROT_SHEAF)
                           .define('#', FLItems.FOX_CARROT)
                           .pattern("##")
                           .pattern("##")
                           .unlockedBy(getHasName(FLItems.FOX_CARROT), has(FLItems.FOX_CARROT))
                           .save(recipeOutput);
        oneToOne(recipeOutput, DECORATIONS, FLItems.FOX_CARROT, FOX_CARROT_SHEAF, "", 4);
        ShapedRecipeBuilder.shaped(DECORATIONS, FOX_CARROT_BASKET)
                           .define('#', FOX_CARROT_SHEAF)
                           .pattern("###")
                           .pattern("# #")
                           .pattern("###")
                           .unlockedBy(getHasName(FOX_CARROT_SHEAF), has(FOX_CARROT_SHEAF))
                           .save(recipeOutput);
        oneToOne(recipeOutput, DECORATIONS, FOX_CARROT_SHEAF, FOX_CARROT_BASKET, "", 8);
    }

    private void ironScaffolding(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(DECORATIONS, IRON_SCAFFOLDING, 6)
                           .define('~', Items.IRON_INGOT)
                           .define('I', Items.IRON_BARS)
                           .pattern("I~I")
                           .pattern("I I")
                           .pattern("I I")
                           .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                           .save(recipeOutput);
    }

    private void craftingBlock(RecipeOutput recipeOutput) {
        stonecutting(recipeOutput, DECORATIONS, CRAFTING_BLOCK, Items.CRAFTING_TABLE);
        stonecutting(recipeOutput, DECORATIONS, CRAFTING_DESK, Items.CRAFTING_TABLE);
        stonecutting(recipeOutput, DECORATIONS, CRAFTING_PAD, Items.CRAFTING_TABLE, 16);
    }

    private void meatBlock(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, MEAT_BLOCK.get())
                .define('#', Tags.Items.FOODS_RAW_MEAT)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_raw_meat", has(Tags.Items.FOODS_RAW_MEAT))
                .save(recipeOutput);
        ShapedRecipeBuilder
                .shaped(DECORATIONS, ROTTEN_FLESH_BLOCK.get())
                .define('#', Items.ROTTEN_FLESH)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH))
                .save(recipeOutput);
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ROTTEN_FLESH_BLOCK), MISC, Items.LEATHER, 0.3F, 400)
                .unlockedBy(getHasName(ROTTEN_FLESH_BLOCK), has(ROTTEN_FLESH_BLOCK))
                .save(recipeOutput, getItemName(Items.LEATHER) + "_from_smelting_" + getItemName(ROTTEN_FLESH_BLOCK));
        SimpleCookingRecipeBuilder
                .smoking(Ingredient.of(ROTTEN_FLESH_BLOCK), MISC, Items.LEATHER, 0.3F, 200)
                .unlockedBy(getHasName(ROTTEN_FLESH_BLOCK), has(ROTTEN_FLESH_BLOCK))
                .save(recipeOutput, getItemName(Items.LEATHER) + "_from_smoking_" + getItemName(ROTTEN_FLESH_BLOCK));
        SimpleCookingRecipeBuilder
                .campfireCooking(Ingredient.of(ROTTEN_FLESH_BLOCK), MISC, Items.LEATHER, 0.3F, 1200)
                .unlockedBy(getHasName(ROTTEN_FLESH_BLOCK), has(ROTTEN_FLESH_BLOCK))
                .save(recipeOutput, getItemName(Items.LEATHER) + "_from_camping_" + getItemName(ROTTEN_FLESH_BLOCK));
    }

    private static void waterloggedCobblestone(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, WATERLOGGED_COBBLESTONE.get())
                .define('#', Items.MOSSY_COBBLESTONE_SLAB)
                .define('X', Items.WATER_BUCKET)
                .pattern("X#")
                .pattern("#X")
                .unlockedBy("in_water", insideOf(Blocks.WATER))
                .save(recipeOutput);
    }

    private static void mirror(RecipeOutput recipeOutput) {
        shaped(DECORATIONS, MIRROR)
                .define('A', Items.IRON_INGOT)
                .define('B', Items.GLASS_PANE)
                .define('C', Items.GLOWSTONE_DUST)
                .pattern(" C")
                .pattern("AB")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
        shaped(DECORATIONS, CABINET_MIRROR)
                .define('A', SMOOTH_MANGROVE_PLANKS)
                .define('B', MIRROR)
                .pattern("AB")
                .unlockedBy(getHasName(MIRROR), has(MIRROR))
                .save(recipeOutput);
    }

    private static void wallFlowerPot(RecipeOutput recipeOutput, ItemLike result, Item flowerA, Item flowerB, Item flowerC) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, result)
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
                .shaped(DECORATIONS, HEAVY_CHAINS)
                .define('#', Items.CHAIN)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(Items.CHAIN), has(Items.CHAIN))
                .save(recipeOutput);
    }

    private static void neoForge(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, NEO_FORGE)
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
                .shaped(DECORATIONS, FAKE_HOPPER, 10)
                .define('#', Items.IRON_INGOT)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
        stonecutting(recipeOutput, DECORATIONS, FAKE_FURNACE, Items.FURNACE, 8);
        ShapedRecipeBuilder
                .shaped(DECORATIONS, LIT_FAKE_FURNACE)
                .define('a', FAKE_FURNACE)
                .define('b', Items.GLOWSTONE_DUST)
                .pattern("a")
                .pattern("b")
                .unlockedBy(getHasName(FAKE_FURNACE), has(FAKE_FURNACE))
                .save(recipeOutput);
        ShapedRecipeBuilder
                .shaped(DECORATIONS, FAKE_BLAST_FURNACE, 5)
                .define('#', Blocks.SMOOTH_STONE)
                .define('X', FAKE_FURNACE)
                .define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("IXI")
                .pattern("###")
                .unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE))
                .save(recipeOutput);
        ShapedRecipeBuilder
                .shaped(DECORATIONS, LIT_FAKE_BLAST_FURNACE)
                .define('a', FAKE_BLAST_FURNACE)
                .define('b', Items.GLOWSTONE_DUST)
                .pattern("a")
                .pattern("b")
                .unlockedBy(getHasName(FAKE_BLAST_FURNACE), has(FAKE_BLAST_FURNACE))
                .save(recipeOutput);
        ShapedRecipeBuilder
                .shaped(DECORATIONS, FAKE_SMOKER, 8)
                .define('#', ItemTags.LOGS)
                .define('X', FAKE_FURNACE)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .unlockedBy("has_furnace", has(Blocks.FURNACE))
                .save(recipeOutput);
        ShapedRecipeBuilder
                .shaped(DECORATIONS, LIT_FAKE_SMOKER)
                .define('a', FAKE_SMOKER)
                .define('b', Items.GLOWSTONE_DUST)
                .pattern("a")
                .pattern("b")
                .unlockedBy(getHasName(FAKE_SMOKER), has(FAKE_SMOKER))
                .save(recipeOutput);
        stonecutting(recipeOutput, DECORATIONS, FAKE_BARREL, Items.BARREL, 8);
        ShapedRecipeBuilder.shaped(DECORATIONS, FAKE_CAMPFIRE, 8)
                           .define('L', ItemTags.LOGS)
                           .define('S', Items.STICK)
                           .pattern(" S ")
                           .pattern("S S")
                           .pattern("LLL")
                           .unlockedBy("has_stick", has(Items.STICK))
                           .unlockedBy("has_coal", has(ItemTags.COALS))
                           .save(recipeOutput);
        stonecutting(recipeOutput, DECORATIONS, LIT_FAKE_CAMPFIRE, Items.CAMPFIRE, 8);
        stonecutting(recipeOutput, DECORATIONS, LIT_FAKE_SOUL_CAMPFIRE, Items.SOUL_CAMPFIRE, 8);
        stonecutting(recipeOutput, DECORATIONS, FAKE_CHEST, Items.CHEST, 8);
        stonecutting(recipeOutput, DECORATIONS, FAKE_CHISELED_BOOKSHELF, Items.CHISELED_BOOKSHELF, 8);
        stonecutting(recipeOutput, DECORATIONS, FAKE_LECTERN, Items.LECTERN, 16);
        stonecutting(recipeOutput, DECORATIONS, FAKE_BEEHIVE, Items.BEEHIVE, 16);
        fakeBlocks(recipeOutput, FAKE_IRON_BLOCK, Items.GRAY_DYE);
        fakeBlocks(recipeOutput, FAKE_GOLD_BLOCK, Items.YELLOW_DYE);
        fakeBlocks(recipeOutput, FAKE_DIAMOND_BLOCK, Items.LIGHT_BLUE_DYE);
        fakeBlocks(recipeOutput, FAKE_NETHERITE_BLOCK, Items.BROWN_DYE);
        fakeReinforcedDeepslate(recipeOutput);
        fakeBlocks(recipeOutput, FAKE_BEDROCK, Items.BLACK_DYE);
    }

    private static void fakeBlocks(RecipeOutput recipeOutput, ItemLike fakeBlock, Item dye) {
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, fakeBlock, 8)
                           .define('S', Items.STONE)
                           .define('D', dye)
                           .pattern("SSS")
                           .pattern("SDS")
                           .pattern("SSS")
                           .unlockedBy(getHasName(dye), has(dye))
                           .save(recipeOutput);
    }

    private static void fakeReinforcedDeepslate(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, FAKE_REINFORCED_DEEPSLATE, 8)
                           .define('S', Items.DEEPSLATE)
                           .define('D', Items.YELLOW_DYE)
                           .pattern("SSS")
                           .pattern("SDS")
                           .pattern("SSS")
                           .unlockedBy(getHasName(Items.YELLOW_DYE), has(Items.YELLOW_DYE))
                           .save(recipeOutput);
    }

    private static void textureBlocks(RecipeOutput recipeOutput) {
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_CHISELED_BOOKSHELF, FAKE_CHISELED_BOOKSHELF);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_CHISELED_BOOKSHELF_TOP, FAKE_CHISELED_BOOKSHELF);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_CHISELED_BOOKSHELF_SIDE, FAKE_CHISELED_BOOKSHELF);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_LOOM, Items.LOOM);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_BEEHIVE_TOP, FAKE_BEEHIVE);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_SMITHING_TABLE_BOTTOM, Items.SMITHING_TABLE);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_COMPOSTER_BOTTOM, Items.COMPOSTER);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_BEE_NEST_TOP, TEXTURE_BEEHIVE_TOP);

        stonecutting(recipeOutput, DECORATIONS, TEXTURE_FURNACE, FAKE_FURNACE);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_FURNACE_TOP, FAKE_FURNACE);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_BLAST_FURNACE, FAKE_BLAST_FURNACE);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_BLAST_FURNACE_TOP, FAKE_BLAST_FURNACE);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_SMOKER, FAKE_SMOKER);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_SMITHING_TABLE_TOP, Items.SMITHING_TABLE);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_LODESTONE, Items.CHISELED_STONE_BRICKS);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_LODESTONE_SIDE, Items.CHISELED_STONE_BRICKS);
        stonecutting(recipeOutput, DECORATIONS, TEXTURE_LODESTONE_TOP, Items.CHISELED_STONE_BRICKS);
    }

    private static void rainbowDye(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(MISC, FLItems.RAINBOW_DYE, 3)
                .define('#', Items.PRISMARINE_CRYSTALS)
                .define('r', Items.RED_DYE)
                .define('g', Items.GREEN_DYE)
                .define('b', Items.BLUE_DYE)
                .pattern(" r ")
                .pattern(" # ")
                .pattern("g b")
                .unlockedBy(getHasName(Items.PRISMARINE_CRYSTALS), has(Items.PRISMARINE_CRYSTALS))
                .save(recipeOutput);
    }

    private static void rainbow(RecipeOutput recipeOutput, ItemLike rainbowBlock, TagKey<Item> Block) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, rainbowBlock, 8)
                .define('#', Block)
                .define('D', FLItems.RAINBOW_DYE)
                .pattern("###")
                .pattern("#D#")
                .pattern("###")
                .unlockedBy(getHasName(FLItems.RAINBOW_DYE), has(FLItems.RAINBOW_DYE))
                .save(recipeOutput);
    }

    private static void rainbow(RecipeOutput recipeOutput, ItemLike rainbowBlock, Item Block) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, rainbowBlock, 8)
                .define('#', Block)
                .define('D', FLItems.RAINBOW_DYE)
                .pattern("###")
                .pattern("#D#")
                .pattern("###")
                .unlockedBy(getHasName(FLItems.RAINBOW_DYE), has(FLItems.RAINBOW_DYE))
                .save(recipeOutput);
    }

    private static void colorLamp(RecipeOutput recipeOutput, ItemLike lamp, ItemLike dye) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, lamp)
                .define('#', Items.AMETHYST_SHARD)
                .define('d', dye)
                .pattern(" # ")
                .pattern("#d#")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(recipeOutput);
    }

    private static void colorLamps(RecipeOutput recipeOutput) {
        colorLamp(recipeOutput, RED_LAMP, Items.RED_DYE);
        colorLamp(recipeOutput, GREEN_LAMP, Items.GREEN_DYE);
        colorLamp(recipeOutput, BLUE_LAMP, Items.BLUE_DYE);
        colorLamp(recipeOutput, YELLOW_LAMP, Items.YELLOW_DYE);
        colorLamp(recipeOutput, CYAN_LAMP, Items.CYAN_DYE);
        colorLamp(recipeOutput, PURPLE_LAMP, Items.PURPLE_DYE);
        rainbow(recipeOutput, RAINBOW_LAMP, FLItemTags.COLOR_LAMPS);
    }

    private static void doubleBlock(RecipeOutput recipeOutput, ItemLike doubleBlock, ItemLike up, ItemLike down) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, doubleBlock, 2)
                .define('u', up)
                .define('d', down)
                .pattern("u")
                .pattern("d")
                .unlockedBy(getHasName(up), has(up))
                .save(recipeOutput);
    }

    private void smoothPlanks(RecipeOutput recipeOutput, ItemLike smoothPlanks, ItemLike wood) {
        stonecutting(recipeOutput, BUILDING_BLOCKS, smoothPlanks, wood);
    }

    public void woodenGuardrail(RecipeOutput recipeOutput, ItemLike woodenGuardrail, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(DECORATIONS, woodenGuardrail, 4)
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
                .shaped(DECORATIONS, woodenGuardrail, 4)
                .define('X', material)
                .define('#', Items.STICK)
                .pattern("X#X")
                .group("guardrail")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    public void pillar12px(RecipeOutput recipeOutput, ItemLike pillar, ItemLike material) {
        ShapedRecipeBuilder
                .shaped(BUILDING_BLOCKS, pillar, 5)
                .define('#', material)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .group("pillar")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
        stonecutting(recipeOutput, BUILDING_BLOCKS, pillar, material);
    }

    public void corridorSlab(RecipeOutput recipeOutput, ItemLike slab, ItemLike corridor) {
        slab(recipeOutput, DECORATIONS, slab, corridor);
        stonecutting(recipeOutput, DECORATIONS, slab, corridor, 2);
    }

    private static void toolsAndCombat(RecipeOutput recipeOutput) {
        shaped(TOOLS, FLItems.CROWBAR)
                .define('.', Items.IRON_NUGGET)
                .define('X', Items.IRON_INGOT)
                .pattern(".. ")
                .pattern(" X ")
                .pattern(" X ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
        netheriteSmithing(recipeOutput, FLItems.CROWBAR.get(), TOOLS, FLItems.NETHERITE_CROWBAR.get());
        shaped(COMBAT, FLItems.PUFFERFISH_ROD)
                .define('X', Items.PUFFERFISH)
                .define('I', Items.STICK)
                .pattern("X")
                .pattern("I")
                .pattern("I")
                .unlockedBy(getHasName(Items.PUFFERFISH), has(Items.PUFFERFISH))
                .save(recipeOutput);
        shaped(COMBAT, FLItems.METEOR_HAMMER)
                .define('I', Items.IRON_INGOT)
                .define('X', FLItems.PUFFERFISH_ROD)
                .pattern(" I ")
                .pattern("IXI")
                .unlockedBy(getHasName(FLItems.PUFFERFISH_ROD), has(FLItems.PUFFERFISH_ROD))
                .save(recipeOutput);
        netheriteSmithing(recipeOutput, FLItems.METEOR_HAMMER.get(), TOOLS, FLItems.NETHERITE_METEOR_HAMMER.get());
        shaped(COMBAT, FLItems.SACABAMBASPIS)
                .define('I', Items.IRON_INGOT)
                .define('X', ItemTags.FISHES)
                .define('A', Tags.Items.DYES_GRAY)
                .define('B', Tags.Items.DYES_LIGHT_GRAY)
                .define('C', Tags.Items.DYES_WHITE)
                .define('Y', Items.BONE_MEAL)
                .pattern("AIB")
                .pattern("IXI")
                .pattern("CYC")
                .unlockedBy("has_fishes", has(ItemTags.FISHES))
                .save(recipeOutput);
        shaped(TOOLS, FLItems.PARROT)
                .define('I', Items.IRON_INGOT)
                .define('A', Tags.Items.DYES_RED)
                .define('B', Tags.Items.DYES_BLUE)
                .pattern("AI")
                .pattern("AI")
                .pattern("BI")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
        oneToOne(recipeOutput, COMBAT, FLItems.WOODEN_DOOR, Items.OAK_DOOR, "");
        oneToOne(recipeOutput, COMBAT, FLItems.IRON_DOOR, Items.IRON_DOOR, "");
        shaped(TOOLS, FLItems.GLASS_KNIFE)
                .define('X', Items.STICK)
                .define('I', Items.IRON_INGOT)
                .define('D', Items.DIAMOND)
                .pattern("DI")
                .pattern(" X")
                .pattern(" X")
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(recipeOutput);
        glassKnifeCut(recipeOutput, Items.GLASS, Items.GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.WHITE_STAINED_GLASS, Items.WHITE_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.ORANGE_STAINED_GLASS, Items.ORANGE_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.MAGENTA_STAINED_GLASS, Items.MAGENTA_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.LIGHT_BLUE_STAINED_GLASS, Items.LIGHT_BLUE_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.YELLOW_STAINED_GLASS, Items.YELLOW_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.LIME_STAINED_GLASS, Items.LIME_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.PINK_STAINED_GLASS, Items.PINK_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.GRAY_STAINED_GLASS, Items.GRAY_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.LIGHT_GRAY_STAINED_GLASS, Items.LIGHT_GRAY_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.CYAN_STAINED_GLASS, Items.CYAN_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.PURPLE_STAINED_GLASS, Items.PURPLE_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.BLUE_STAINED_GLASS, Items.BLUE_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.BROWN_STAINED_GLASS, Items.BROWN_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.GREEN_STAINED_GLASS, Items.GREEN_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.RED_STAINED_GLASS, Items.RED_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, Items.BLACK_STAINED_GLASS, Items.BLACK_STAINED_GLASS_PANE);
        glassKnifeCut(recipeOutput, RAINBOW_GLASS, RAINBOW_GLASS_PANE);
    }

    private static void glassKnifeCut(RecipeOutput recipeOutput, ItemLike glassBlock, ItemLike glassPane) {
        var rl = BuiltInRegistries.ITEM.getKey(glassPane.asItem());
        String id = FierceLive.MODID + ":";
        if (!rl.getNamespace().equals(FierceLive.MODID)) id = id + rl.getNamespace() + "_";
        id = id + rl.getPath() + "_from_glass_knife";
        shaped(BUILDING_BLOCKS, glassPane, 8)
                .define('X', FLItems.GLASS_KNIFE)
                .define('#', glassBlock.asItem())
                .pattern("X")
                .pattern("#")
                .unlockedBy(getHasName(FLItems.GLASS_KNIFE), has(FLItems.GLASS_KNIFE))
                .save(recipeOutput, id);
    }

    private static void duper(RecipeOutput recipeOutput) {
        shaped(REDSTONE, INFINITE_TNT)
                .define('#', Items.OBSERVER)
                .define('A', Items.NOTE_BLOCK)
                .define('B', Items.REDSTONE)
                .define('C', Items.PISTON)
                .define('D', Items.TNT)
                .pattern("#A ")
                .pattern("B#B")
                .pattern("CDC")
                .unlockedBy(getHasName(Items.TNT), has(Items.TNT))
                .save(recipeOutput, "infinite_tnt_a");
        shaped(REDSTONE, INFINITE_TNT)
                .define('#', Items.STICKY_PISTON)
                .define('A', Items.SLIME_BLOCK)
                .define('B', Items.DETECTOR_RAIL)
                .define('C', Items.MINECART)
                .define('D', Items.TNT)
                .define('E', FLItemTags.DEAD_CORAL_FANS)
                .pattern("CA#")
                .pattern("BAD")
                .pattern("AAE")
                .unlockedBy(getHasName(Items.TNT), has(Items.TNT))
                .save(recipeOutput, "infinite_tnt_b");
        shaped(REDSTONE, INFINITE_RAIL_CARPET)
                .define('#', Items.STICKY_PISTON)
                .define('A', Items.SLIME_BLOCK)
                .define('B', Items.OBSERVER)
                .define('C', FLItemTags.DEAD_CORAL_FANS)
                .pattern(" A#")
                .pattern(" AB")
                .pattern("CA ")
                .unlockedBy(getHasName(Items.STICKY_PISTON), has(Items.STICKY_PISTON))
                .save(recipeOutput);
        shaped(REDSTONE, SNOWBALL_GENERATOR)
                .define('#', Items.PISTON)
                .define('A', Items.SNOW_BLOCK)
                .define('B', Items.CARVED_PUMPKIN)
                .pattern(" B ")
                .pattern("#A#")
                .pattern("#A#")
                .unlockedBy(getHasName(Items.CARVED_PUMPKIN), has(Items.CARVED_PUMPKIN))
                .save(recipeOutput);
        shaped(COMBAT, FLItems.INFINITE_SNOWBALL)
                .define('#', Items.SNOW_BLOCK)
                .define('A', Items.CARVED_PUMPKIN)
                .define('B', Items.SNOWBALL)
                .pattern(" A")
                .pattern("B#")
                .pattern(" #")
                .unlockedBy(getHasName(Items.CARVED_PUMPKIN), has(Items.CARVED_PUMPKIN))
                .save(recipeOutput);
    }

    private static void cookie(RecipeOutput recipeOutput, ItemLike cookie, ItemLike material) {
        shaped(FOOD, cookie, 8)
                .define('#', Items.WHEAT)
                .define('X', material)
                .pattern("#X#")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private static void food(RecipeOutput recipeOutput) {
        shaped(FOOD, FLItems.FROSTED_BREAD)
                .define('Y', Items.SUGAR)
                .define('X', Items.BREAD)
                .pattern("Y")
                .pattern("X")
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(recipeOutput);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.SUGAR), FOOD, FLItems.CARAMEL, 0.35F, 200)
                                  .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                                  .save(recipeOutput);
        simpleCookingRecipe(recipeOutput, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, Items.SUGAR, FLItems.CARAMEL, 0.35F);
        cookie(recipeOutput, FLItems.SWEET_BERRY_COOKIE, Items.SWEET_BERRIES);
        cookie(recipeOutput, FLItems.GLOW_BERRY_COOKIE, Items.GLOW_BERRIES);
        cookie(recipeOutput, FLItems.CARAMEL_COOKIE, FLItems.CARAMEL);
    }

    private static void concrete(RecipeOutput recipeOutput) {
        shapeless(BUILDING_BLOCKS, CONCRETE_POWDER.get(), 8)
                .requires(Items.SAND, 4)
                .requires(Items.GRAVEL, 4)
                .unlockedBy(getHasName(Items.SAND), has(Items.SAND))
                .save(recipeOutput);
        shapeless(BUILDING_BLOCKS, GRAVEL_CONCRETE_POWDER.get(), 9)
                .requires(Items.SAND, 4)
                .requires(Items.GRAVEL, 5)
                .unlockedBy(getHasName(Items.GRAVEL), has(Items.GRAVEL))
                .save(recipeOutput);
    }

}
