package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = FierceDecoration.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGen {


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean includeServer = event.includeServer();
        boolean includeClient = event.includeClient();

        generator.addProvider(includeServer, new LootTableProvider(
                packOutput,
                Collections.emptySet(),
                List.of(
                        new LootTableProvider.SubProviderEntry(BlockLootGen::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(ChestLootGen::new, LootContextParamSets.CHEST)
                ),
                lookupProvider
        ));
        generator.addProvider(includeServer, new RecipeGen(packOutput, lookupProvider));
        BlockTagsGen blockTagsGen = new BlockTagsGen(packOutput, event.getLookupProvider(), existingFileHelper);
        generator.addProvider(includeServer, blockTagsGen);
        generator.addProvider(includeServer, new ItemTagsGen(packOutput, event.getLookupProvider(), blockTagsGen.contentsGetter(), existingFileHelper));
        generator.addProvider(includeServer, new DataMapGen(packOutput, event.getLookupProvider()));
        generator.addProvider(includeClient, new BlockStateGen(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new LangGanENUS(packOutput));
        generator.addProvider(includeClient, new LangGanZHCN(existingFileHelper, packOutput));

    }
}
