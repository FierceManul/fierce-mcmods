package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

public class RecipeGen extends FSRecipeProvider {


    public RecipeGen(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FierceSource.CROWBAR_ITEM)
                .define('.', Items.IRON_NUGGET)
                .define('X', Items.IRON_INGOT)
                .pattern(".. ")
                .pattern(" X ")
                .pattern(" X ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pRecipeOutput);
        netheriteSmithing(pRecipeOutput, FierceSource.CROWBAR_ITEM.get(), RecipeCategory.TOOLS, FierceSource.NETHERITE_CROWBAR_ITEM.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FierceSource.CLAW_HAMMER_ITEM)
                .define('.', Items.IRON_NUGGET)
                .define('X', Items.IRON_INGOT)
                .define('I', Items.STICK)
                .pattern("XX.")
                .pattern(" I ")
                .pattern(" I ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pRecipeOutput);
        netheriteSmithing(pRecipeOutput, FierceSource.CLAW_HAMMER_ITEM.get(), RecipeCategory.TOOLS, FierceSource.NETHERITE_CLAW_HAMMER_ITEM.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FierceSource.WORLD_LOCATOR_BLOCK_ITEM)
                .define('O', FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM)
                .define('I', Items.IRON_INGOT)
                .define('E', Items.ENDER_EYE)
                .define('R', Items.RED_DYE)
                .define('G', Items.GREEN_DYE)
                .define('B', Items.BLUE_DYE)
                .pattern("REG")
                .pattern("IOI")
                .pattern("EBE")
                .unlockedBy(getHasName(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM), has(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM))
                .save(pRecipeOutput);


        RecipeOutput backupRecipeOutput = pRecipeOutput.withConditions(new NotCondition(new ModLoadedCondition("fiercecraft")));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FierceSource.SOUL_CRYSTAL_SHARD_ITEM)
                .requires(Items.AMETHYST_SHARD)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(backupRecipeOutput, applyBackup(FierceSource.SOUL_CRYSTAL_SHARD_ITEM.getId()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM)
                .define('X', Items.GLOWSTONE_DUST)
                .define('O', FierceSource.SOUL_CRYSTAL_SHARD_ITEM)
                .pattern(" O ")
                .pattern("OXO")
                .pattern(" O ")
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .unlockedBy(getHasName(FierceSource.SOUL_CRYSTAL_SHARD_ITEM), has(FierceSource.SOUL_CRYSTAL_SHARD_ITEM))
                .save(backupRecipeOutput, applyBackup(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM.getId()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FierceSource.MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM)
                .define('X', Items.GHAST_TEAR)
                .define('O', FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM)
                .pattern(" O ")
                .pattern("OXO")
                .pattern(" O ")
                .unlockedBy(getHasName(Items.GHAST_TEAR), has(Items.GHAST_TEAR))
                .unlockedBy(getHasName(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM), has(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM))
                .save(backupRecipeOutput, applyBackup(FierceSource.MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM.getId()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FierceSource.LARGE_SOUL_CRYSTAL_BLOCK_ITEM)
                .define('X', Items.NETHER_STAR)
                .define('O', FierceSource.MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM)
                .pattern(" O ")
                .pattern("OXO")
                .pattern(" O ")
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .unlockedBy(getHasName(FierceSource.MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM), has(FierceSource.MEDIUM_SOUL_CRYSTAL_BLOCK_ITEM))
                .save(backupRecipeOutput, applyBackup(FierceSource.LARGE_SOUL_CRYSTAL_BLOCK_ITEM.getId()));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FierceSource.POS_RECORDER_ITEM)
                .define('O', FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM)
                .define('I', Items.IRON_INGOT)
                .define('L', FierceSource.WORLD_LOCATOR_BLOCK_ITEM)
                .define('P', Items.GLASS_PANE)
                .define('Q', Items.QUARTZ)
                .define('B', ItemTags.BUTTONS)
                .define('G', Items.GOLD_INGOT)
                .pattern("QPL")
                .pattern("IOI")
                .pattern("BGB")
                .unlockedBy(getHasName(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM), has(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK_ITEM))
                .save(backupRecipeOutput, applyBackup(FierceSource.POS_RECORDER_ITEM.getId()));

    }

}
