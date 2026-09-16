package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLEnchantments;
import net.fiercemanul.fiercelive.data.tags.FLEnchantmentTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;


public class EnchantmentTagsGen extends EnchantmentTagsProvider {


    public EnchantmentTagsGen(
            PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FierceLive.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(FLEnchantmentTags.ASPECT).add(Enchantments.FIRE_ASPECT, FLEnchantments.POISON_ASPECT);
        tag(EnchantmentTags.NON_TREASURE).add(FLEnchantments.POISON_ASPECT);
        tag(EnchantmentTags.TRADES_SWAMP_COMMON).add(FLEnchantments.POISON_ASPECT);
    }
}
