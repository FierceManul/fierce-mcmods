package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.registries.FSBlocksAndItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.function.BiConsumer;

public class BlockLootGen extends BlockLootSubProvider {


    public BlockLootGen(HolderLookup.Provider provider) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {}

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> pGenerator) {
        dropSelf(FSBlocksAndItems.LARGE_SOUL_CRYSTAL.getBlock());
        dropSelf(FSBlocksAndItems.MEDIUM_SOUL_CRYSTAL.getBlock());
        dropSelf(FSBlocksAndItems.SMALL_SOUL_CRYSTAL.getBlock());
        dropSelf(FSBlocksAndItems.LARGE_MANA_CRYSTAL.getBlock());
        dropSelf(FSBlocksAndItems.MEDIUM_MANA_CRYSTAL.getBlock());
        dropSelf(FSBlocksAndItems.SMALL_MANA_CRYSTAL.getBlock());
        dropSelf(FSBlocksAndItems.CREATIVE_MANA_BLOCK.getBlock());
        dropSelf(FSBlocksAndItems.CREATIVE_MANA_OUTPUT.getBlock());
        dropSelf(FSBlocksAndItems.WORLD_LOCATOR.getBlock());

        dropSelf(FSBlocksAndItems.SOUL_CRYSTAL_SHARD.getBlock());
        dropSelf(FSBlocksAndItems.SOUL_CRYSTAL_DUST.getBlock());

        this.map.forEach((key, builder) -> {
            if (builder != null) pGenerator.accept(key, builder);
        });
        this.map.clear();
    }
}
