package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.world.item.FDItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class DataMapGen extends DataMapProvider {


    protected DataMapGen(
            PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(FDItems.FOX_CARROT_SEED, new Compostable(0.3F), false)
                .add(FDItems.FOX_CARROT, new Compostable(0.65F), false);
    }
}
