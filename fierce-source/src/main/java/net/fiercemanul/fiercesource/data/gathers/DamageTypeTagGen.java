package net.fiercemanul.fiercesource.data.gathers;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.data.FSDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagGen extends TagsProvider<DamageType> {


    protected DamageTypeTagGen(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, Registries.DAMAGE_TYPE, lookupProvider, FierceSource.FC_MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DamageTypeTags.IS_PROJECTILE).add(FSDamageTypes.THROWN_BLOCK);
        tag(DamageTypeTags.PANIC_CAUSES).add(FSDamageTypes.THROWN_BLOCK);
        tag(DamageTypeTags.NO_KNOCKBACK).add(FSDamageTypes.THROWN_BLOCK);
        tag(DamageTypeTags.ALWAYS_KILLS_ARMOR_STANDS).add(FSDamageTypes.THROWN_BLOCK);
    }

}
