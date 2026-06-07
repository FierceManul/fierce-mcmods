package net.fiercemanul.fiercesource.data.gathers;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.data.FSDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface DamageTypeGen {


    static DatapackBuiltinEntriesProvider getProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        return new DatapackBuiltinEntriesProvider(
                output,
                lookupProvider,
                getBuilder(),
                Set.of(FierceSource.FC_MODID)
        );
    }

    static RegistrySetBuilder getBuilder() {
        return new RegistrySetBuilder().add(
                Registries.DAMAGE_TYPE,
                context -> {
                    context.register(FSDamageTypes.THROWN_BLOCK, new DamageType(FSDamageTypes.THROWN_BLOCK.location().getPath(), 0.1F));

                }
        );
    }

}
