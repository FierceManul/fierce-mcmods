package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.function.BiConsumer;

public class BlockLootGen extends BlockLootSubProvider {


    public BlockLootGen() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {}

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        dropSelf(FierceSource.LARGE_SOUL_CRYSTAL_BLOCK.get());
        dropSelf(FierceSource.MEDIUM_SOUL_CRYSTAL_BLOCK.get());
        dropSelf(FierceSource.SMALL_SOUL_CRYSTAL_BLOCK.get());
        dropSelf(FierceSource.CREATIVE_MANA_BLOCK.get());
        dropSelf(FierceSource.CREATIVE_MANA_OUTPUT_BLOCK.get());
        dropSelf(FierceSource.WORLD_LOCATOR_BLOCK.get());

        this.map.forEach((location, builder) -> {
            if (builder != null) pOutput.accept(location, builder);
        });
        this.map.clear();
    }
}
