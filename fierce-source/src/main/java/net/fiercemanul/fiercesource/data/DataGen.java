package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = FierceSource.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        boolean includeServer = event.includeServer();
        boolean includeClient = event.includeClient();

        generator.addProvider(includeServer, new LootTableProvider(
                packOutput,
                Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(BlockLootGen::new, LootContextParamSets.BLOCK))
        ));
        generator.addProvider(includeServer, new RecipeGen(generator.getPackOutput()));
        BlockTagsGen blockTagsGen = new BlockTagsGen(packOutput, event.getLookupProvider(), existingFileHelper);
        generator.addProvider(includeServer, blockTagsGen);
        generator.addProvider(includeServer, new ItemTagsGen(packOutput, event.getLookupProvider(), blockTagsGen.contentsGetter(), existingFileHelper));
        generator.addProvider(includeClient, new BlockStateGen(packOutput, existingFileHelper));
    }
}
