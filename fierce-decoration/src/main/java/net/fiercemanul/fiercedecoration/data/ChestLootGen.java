package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.data.loot.packs.VanillaChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.Tags;

import java.util.function.BiConsumer;


public class ChestLootGen extends VanillaChestLoot {


    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        pOutput.accept(
                new ResourceLocation(FierceDecoration.MODID, "chests/rundown_hut"),
                LootTable.lootTable()
                         .withPool(
                                 LootPool.lootPool()
                                         .setRolls(BinomialDistributionGenerator.binomial(5, 0.6F))
                                         .add(TagEntry.tagContents(Tags.Items.SEEDS)
                                                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                         )
                         .withPool(
                                 LootPool.lootPool()
                                         .setRolls(BinomialDistributionGenerator.binomial(5, 0.6F))
                                         .add(LootItem.lootTableItem(Items.DEAD_BUSH)
                                                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                                         .add(LootItem.lootTableItem(Items.COBWEB)
                                                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                                         .add(LootItem.lootTableItem(Items.DIRT)
                                                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                                         .add(LootItem.lootTableItem(Items.GRAVEL)
                                                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                                         .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                         )
        );
    }
}
