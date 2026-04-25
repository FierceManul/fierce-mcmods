package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.FLItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
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
                .add(FLItems.FOX_CARROT_SEED, new Compostable(0.3F), false)
                .add(FLItems.FOX_CARROT, new Compostable(0.65F), false)
                .add(FLItems.FROSTED_BREAD, new Compostable(0.85F), false)
                .add(FLItems.SWEET_BERRY_COOKIE, new Compostable(0.85F), false)
                .add(FLItems.GLOW_BERRY_COOKIE, new Compostable(0.85F), false)
                .add(FLItems.CARAMEL_COOKIE, new Compostable(0.85F), false);
        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(FLBlocks.CRAFTING_DESK.getId(), new FurnaceFuel(300), false)
                .add(FLBlocks.CRAFTING_BLOCK.getId(), new FurnaceFuel(300), false)
                .add(FLBlocks.FIREWOOD.getId(), new FurnaceFuel(600), false);
    }
}
