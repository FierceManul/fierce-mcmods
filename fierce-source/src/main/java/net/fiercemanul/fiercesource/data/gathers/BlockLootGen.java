package net.fiercemanul.fiercesource.data.gathers;

import net.fiercemanul.fiercesource.data.FSBlocks;
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
        dropSelf(FSBlocks.LARGE_SOUL_CRYSTAL.getBlock());
        dropSelf(FSBlocks.MEDIUM_SOUL_CRYSTAL.getBlock());
        dropSelf(FSBlocks.SMALL_SOUL_CRYSTAL.getBlock());
        dropSelf(FSBlocks.LARGE_MANA_CRYSTAL.getBlock());
        dropSelf(FSBlocks.MEDIUM_MANA_CRYSTAL.getBlock());
        dropSelf(FSBlocks.SMALL_MANA_CRYSTAL.getBlock());
        dropSelf(FSBlocks.CREATIVE_MANA_BLOCK.getBlock());
        dropSelf(FSBlocks.CREATIVE_MANA_OUTPUT.getBlock());
        dropSelf(FSBlocks.WORLD_LOCATOR.getBlock());

        dropSelf(FSBlocks.SOUL_CRYSTAL_SHARD.getBlock());
        dropSelf(FSBlocks.SOUL_CRYSTAL_DUST.getBlock());

        dropSelf(FSBlocks.HAO_STONE.getBlock());
        dropSelf(FSBlocks.POLISHED_HAO_STONE.getBlock());
        dropSelf(FSBlocks.SMOOTH_HAO_STONE.getBlock());
        dropSelf(FSBlocks.DEEP_STONE.getBlock());
        dropSelf(FSBlocks.POLISHED_DEEP_STONE.getBlock());
        dropSelf(FSBlocks.SMOOTH_DEEP_STONE.getBlock());

        this.map.forEach((key, builder) -> {
            if (builder != null) pGenerator.accept(key, builder);
        });
        this.map.clear();
    }
}
