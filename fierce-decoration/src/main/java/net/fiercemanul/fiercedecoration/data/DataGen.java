package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.world.level.block.BlockMaterial;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = FierceDecoration.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGen {


    public static final Map<DeferredBlock<Block>, BlockMaterial> BLOCKS_AND_MATERIALS = new LinkedHashMap<>();
    public static final String[] COLOR_NAMES = {
            "white",
            "orange",
            "magenta",
            "light_blue",
            "yellow",
            "lime",
            "pink",
            "gray",
            "light_gray",
            "cyan",
            "purple",
            "blue",
            "brown",
            "green",
            "red",
            "black"
    };


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> pRegistries = event.getLookupProvider();
        boolean includeServer = event.includeServer();
        boolean includeClient = event.includeClient();

        generator.addProvider(includeServer, new LootTableProvider(
                packOutput,
                Collections.emptySet(),
                List.of(
                        new LootTableProvider.SubProviderEntry(BlockLootGen::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(ChestLootGen::new, LootContextParamSets.CHEST)
                ),
                pRegistries
        ));
        generator.addProvider(includeServer, new RecipeGen(packOutput, pRegistries));
        BlockTagsGen blockTagsGen = new BlockTagsGen(packOutput, event.getLookupProvider(), existingFileHelper);
        generator.addProvider(includeServer, blockTagsGen);
        generator.addProvider(includeServer, new ItemTagsGen(packOutput, event.getLookupProvider(), blockTagsGen.contentsGetter(), existingFileHelper));
        generator.addProvider(includeServer, new DataMapGen(packOutput, event.getLookupProvider()));
        generator.addProvider(includeClient, new BlockStateGen(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new LangGan(packOutput));

    }
}
