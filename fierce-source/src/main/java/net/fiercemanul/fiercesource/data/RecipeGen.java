package net.fiercemanul.fiercesource.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

import java.util.concurrent.CompletableFuture;

import static net.fiercemanul.fiercesource.registries.FSBlocksAndItems.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class RecipeGen extends FSRecipeProvider {


    public RecipeGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(packOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {

        shaped(RecipeCategory.TOOLS, CROWBAR_ITEM)
                .define('.', Items.IRON_NUGGET)
                .define('X', Items.IRON_INGOT)
                .pattern(".. ")
                .pattern(" X ")
                .pattern(" X ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pRecipeOutput);

        netheriteSmithing(pRecipeOutput, CROWBAR_ITEM.get(), RecipeCategory.TOOLS, NETHERITE_CROWBAR_ITEM.get());

        shaped(RecipeCategory.TOOLS, CLAW_HAMMER_ITEM)
                .define('.', Items.IRON_NUGGET)
                .define('X', Items.IRON_INGOT)
                .define('I', Items.STICK)
                .pattern("XX.")
                .pattern(" I ")
                .pattern(" I ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pRecipeOutput);

        netheriteSmithing(pRecipeOutput, CLAW_HAMMER_ITEM.get(), RecipeCategory.TOOLS, NETHERITE_CLAW_HAMMER_ITEM.get());



        RecipeOutput backupRecipeOutput = pRecipeOutput.withConditions(new NotCondition(new ModLoadedCondition("fiercecraft")));
        
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

}
