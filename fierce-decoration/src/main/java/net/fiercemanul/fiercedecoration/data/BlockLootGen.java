package net.fiercemanul.fiercedecoration.data;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.world.level.block.DoubleCutBlock;
import net.fiercemanul.fiercedecoration.world.level.block.GuardrailBlock;
import net.fiercemanul.fiercedecoration.world.level.block.OneCutBlock;
import net.fiercemanul.fiercedecoration.world.level.block.TripleCutBlock;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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

        FierceDecoration.BLOCKS.getEntries().forEach(deferredBlock -> {
            Block block = deferredBlock.get();
            if (block instanceof GuardrailBlock) {
                LootPool.Builder pool1 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(ExplosionCondition.survivesExplosion())
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool2 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(ExplosionCondition.survivesExplosion())
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                         StatePropertiesPredicate.Builder.properties().hasProperty(GuardrailBlock.CORNER, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootTable.Builder table = LootTable.lootTable().withPool(pool1).withPool(pool2).setParamSet(LootContextParamSets.BLOCK);
                map.put(block.getLootTable(), table);
            }
            else if (block instanceof OneCutBlock) {
                LootPool.Builder pool1 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(ExplosionCondition.survivesExplosion())
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool2 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.DOUBLE, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootTable.Builder table = LootTable.lootTable().withPool(pool1).withPool(pool2).setParamSet(LootContextParamSets.BLOCK);
                map.put(block.getLootTable(), table);
            }
            else if (block instanceof DoubleCutBlock) {
                LootPool.Builder pool1 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_A, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool2 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_B, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool3 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_C, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool4 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_D, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootTable.Builder table = LootTable.lootTable().withPool(pool1).withPool(pool2).withPool(pool3).withPool(pool4).setParamSet(LootContextParamSets.BLOCK);
                map.put(block.getLootTable(), table);
            }
            else if (block instanceof TripleCutBlock) {
                LootPool.Builder pool1 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_A, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool2 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_B, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool3 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_C, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool4 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_D, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool5 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_E, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool6 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_F, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool7 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_G, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootPool.Builder pool8 = LootPool.lootPool()
                                                 .setRolls(ConstantValue.exactly(1))
                                                 .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                         FDBlockStateProperties.PART_H, true)))
                                                 .add(LootItem.lootTableItem(block));
                LootTable.Builder table = LootTable.lootTable().withPool(pool1).withPool(pool2).withPool(pool3).withPool(pool4)
                                                   .withPool(pool5).withPool(pool6).withPool(pool7).withPool(pool8).setParamSet(LootContextParamSets.BLOCK);
                map.put(block.getLootTable(), table);
            }
            else if (block instanceof CropBlock) {}
            else dropSelf(block);
        });

        this.map.forEach((location, builder) -> {
            if (builder != null) pGenerator.accept(location, builder);
        });
        this.map.clear();
    }
}
