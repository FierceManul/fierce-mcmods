package net.fiercemanul.fiercesource.data.gathers;

import net.fiercemanul.fiercesource.data.FSRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

import java.util.concurrent.CompletableFuture;

import static net.fiercemanul.fiercesource.data.FSBlocks.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class RecipeGen extends FSRecipeProvider {


    public RecipeGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        stone(recipeOutput, HAO_STONE, POLISHED_HAO_STONE, SMOOTH_HAO_STONE);
        stone(recipeOutput, DEEP_STONE, POLISHED_DEEP_STONE, SMOOTH_DEEP_STONE);



        RecipeOutput backupRecipeOutput = recipeOutput.withConditions(new NotCondition(new ModLoadedCondition("fiercecraft")));
        
        shaped(RecipeCategory.MISC, HYPERCUBE)
                .define('#', Items.ENDER_CHEST)
                .define('X', SMALL_SOUL_CRYSTAL)
                .define('O', Items.ENDER_EYE)
                .pattern("OXO")
                .pattern("X#X")
                .pattern("OXO")
                .unlockedBy(getHasName(Items.ENDER_CHEST), has(Items.ENDER_CHEST))
                .save(backupRecipeOutput, applyBackup(HYPERCUBE));

        shaped(RecipeCategory.TOOLS, WORLD_LOCATOR)
                .define('#', HYPERCUBE)
                .define('X', Items.ENDER_EYE)
                .define('I', Items.CHAIN)
                .pattern("IXI")
                .pattern("X#X")
                .pattern("IXI")
                .unlockedBy(getHasName(HYPERCUBE), has(HYPERCUBE))
                .save(backupRecipeOutput, applyBackup(WORLD_LOCATOR));

        shapeless(RecipeCategory.MISC, SOUL_CRYSTAL_SHARD)
                .requires(Items.AMETHYST_SHARD)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(backupRecipeOutput, applyBackup(SOUL_CRYSTAL_SHARD));

        shaped(RecipeCategory.MISC, SMALL_SOUL_CRYSTAL)
                .define('X', Items.GLOWSTONE_DUST)
                .define('O', SOUL_CRYSTAL_SHARD)
                .pattern(" O ")
                .pattern("OXO")
                .pattern(" O ")
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .unlockedBy(getHasName(SOUL_CRYSTAL_SHARD), has(SOUL_CRYSTAL_SHARD))
                .save(backupRecipeOutput, applyBackup(SMALL_SOUL_CRYSTAL));

        shaped(RecipeCategory.MISC, MEDIUM_SOUL_CRYSTAL)
                .define('X', Items.GHAST_TEAR)
                .define('O', SMALL_SOUL_CRYSTAL)
                .pattern(" O ")
                .pattern("OXO")
                .pattern(" O ")
                .unlockedBy(getHasName(Items.GHAST_TEAR), has(Items.GHAST_TEAR))
                .unlockedBy(getHasName(SMALL_SOUL_CRYSTAL), has(SMALL_SOUL_CRYSTAL))
                .save(backupRecipeOutput, applyBackup(MEDIUM_SOUL_CRYSTAL));

        shaped(RecipeCategory.MISC, LARGE_SOUL_CRYSTAL)
                .define('X', Items.NETHER_STAR)
                .define('O', MEDIUM_SOUL_CRYSTAL)
                .pattern(" O ")
                .pattern("OXO")
                .pattern(" O ")
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .unlockedBy(getHasName(MEDIUM_SOUL_CRYSTAL), has(MEDIUM_SOUL_CRYSTAL))
                .save(backupRecipeOutput, applyBackup(LARGE_SOUL_CRYSTAL));

        shaped(RecipeCategory.MISC, SMALL_MANA_CRYSTAL)
                .define('X', Tags.Items.CHESTS)
                .define('O', SMALL_SOUL_CRYSTAL)
                .pattern("X")
                .pattern("O")
                .unlockedBy(getHasName(SMALL_SOUL_CRYSTAL), has(SMALL_SOUL_CRYSTAL))
                .save(backupRecipeOutput, applyBackup(SMALL_MANA_CRYSTAL));

        shaped(RecipeCategory.MISC, MEDIUM_MANA_CRYSTAL)
                .define('X', Tags.Items.CHESTS)
                .define('O', MEDIUM_SOUL_CRYSTAL)
                .pattern("X")
                .pattern("O")
                .unlockedBy(getHasName(MEDIUM_SOUL_CRYSTAL), has(MEDIUM_SOUL_CRYSTAL))
                .save(backupRecipeOutput, applyBackup(MEDIUM_MANA_CRYSTAL));

        shaped(RecipeCategory.MISC, LARGE_MANA_CRYSTAL)
                .define('X', Tags.Items.CHESTS)
                .define('O', LARGE_SOUL_CRYSTAL)
                .pattern("X")
                .pattern("O")
                .unlockedBy(getHasName(LARGE_SOUL_CRYSTAL), has(LARGE_SOUL_CRYSTAL))
                .save(backupRecipeOutput, applyBackup(LARGE_MANA_CRYSTAL));

    }

    private void stone(RecipeOutput recipeOutput, ItemLike stone, ItemLike polished, ItemLike smooth) {
        shaped(RecipeCategory.BUILDING_BLOCKS, polished, 4)
                .define('#', stone)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(stone), has(stone))
                .save(recipeOutput);
        stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, polished, stone);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(stone), RecipeCategory.BUILDING_BLOCKS, smooth, 0.1F, 200)
                                  .unlockedBy(getHasName(stone), has(stone))
                                  .save(recipeOutput);
    }

}
