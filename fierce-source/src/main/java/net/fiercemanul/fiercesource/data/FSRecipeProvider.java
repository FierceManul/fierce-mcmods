package net.fiercemanul.fiercesource.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public abstract class FSRecipeProvider extends RecipeProvider {


    protected static ResourceLocation applyBackup(ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), location.getPath() + "_backup");
    }

    protected static void netheriteSmithing(RecipeOutput pRecipeOutput, Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                                              Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                                              Ingredient.of(pIngredientItem),
                                              Ingredient.of(Items.NETHERITE_INGOT),
                                              pCategory,
                                              pResultItem
                                      )
                                      .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                                      .save(pRecipeOutput, BuiltInRegistries.ITEM.getKey(pResultItem) + "_smithing");
    }

    protected static void oneToOne(RecipeOutput pRecipeOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pIngredient, @Nullable String pGroup) {
        oneToOne(pRecipeOutput, pCategory, pResult, pIngredient, pGroup, 1);
    }

    protected static void oneToOne(RecipeOutput pRecipeOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pIngredient, @Nullable String pGroup, int pResultCount) {
        ShapelessRecipeBuilder.shapeless(pCategory, pResult, pResultCount)
                              .requires(pIngredient)
                              .group(pGroup)
                              .unlockedBy(getHasName(pIngredient), has(pIngredient))
                              .save(pRecipeOutput, BuiltInRegistries.ITEM.getKey(pResult.asItem()).getNamespace() + ":" + getConversionRecipeName(pResult, pIngredient));
    }

    protected static void stonecutting(RecipeOutput pRecipeOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial) {
        stonecutting(pRecipeOutput, pCategory, pResult, pMaterial, 1);
    }

    protected static void stonecutting(RecipeOutput pRecipeOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial, int pResultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pMaterial), pCategory, pResult, pResultCount)
                               .unlockedBy(getHasName(pMaterial), has(pMaterial))
                               .save(pRecipeOutput, BuiltInRegistries.ITEM.getKey(pResult.asItem()).getNamespace() + ":stonecutting/" + getConversionRecipeName(pResult, pMaterial));
    }

    public FSRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }
}
