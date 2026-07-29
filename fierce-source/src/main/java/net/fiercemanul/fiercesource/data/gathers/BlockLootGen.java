package net.fiercemanul.fiercesource.data.gathers;

import net.fiercemanul.fiercesource.data.FSItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.function.BiConsumer;

import static net.fiercemanul.fiercesource.data.FSBlocks.*;

public class BlockLootGen extends BlockLootSubProvider {


    public BlockLootGen(HolderLookup.Provider provider) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {}

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> generator) {
        dropSelf(LARGE_SOUL_CRYSTAL.get());
        dropSelf(MEDIUM_SOUL_CRYSTAL.get());
        dropSelf(SMALL_SOUL_CRYSTAL.get());
        dropSelf(LARGE_MANA_CRYSTAL.get());
        dropSelf(MEDIUM_MANA_CRYSTAL.get());
        dropSelf(SMALL_MANA_CRYSTAL.get());
        dropSelf(CREATIVE_MANA_BLOCK.get());
        dropSelf(CREATIVE_MANA_PROVIDER.get());
        dropSelf(WORLD_LOCATOR.get());

        dropSelf(FSItems.SOUL_CRYSTAL_SHARD.get());
        dropSelf(FSItems.SOUL_CRYSTAL_DUST.get());

        dropSelf(HAO_STONE.get());
        dropSelf(POLISHED_HAO_STONE.get());
        dropSelf(SMOOTH_HAO_STONE.get());
        dropSelf(DEEP_STONE.get());
        dropSelf(POLISHED_DEEP_STONE.get());
        dropSelf(SMOOTH_DEEP_STONE.get());

        this.map.forEach((key, builder) -> {
            if (builder != null) generator.accept(key, builder);
        });
        this.map.clear();
    }
}
