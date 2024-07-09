package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
        dropSelf(FierceSource.LARGE_SOUL_CRYSTAL_BLOCK.get());
        dropSelf(FierceSource.MEDIUM_SOUL_CRYSTAL_BLOCK.get());
        dropSelf(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK.get());
        dropSelf(FierceSource.CREATIVE_MANA_BLOCK.get());
        dropSelf(FierceSource.CREATIVE_MANA_OUTPUT_BLOCK.get());
        dropSelf(FierceSource.WORLD_LOCATOR_BLOCK.get());

        this.map.forEach((key, builder) -> {
            if (builder != null) pGenerator.accept(key, builder);
        });
        this.map.clear();
    }
}
