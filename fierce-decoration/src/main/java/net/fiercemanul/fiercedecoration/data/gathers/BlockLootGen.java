package net.fiercemanul.fiercedecoration.data.gathers;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.data.FDBlocks;
import net.fiercemanul.fiercedecoration.data.registries.BlockMaterial;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class BlockLootGen extends BlockLootSubProvider {


    public static final HashMap<DeferredHolder<Block, ? extends Block>, Consumer<BlockLootGen>> ROWS = new HashMap<>();
    private final HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

    public BlockLootGen(HolderLookup.Provider provider) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {}

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> generator) {
        ROWS.put(FDBlocks.FOX_CARROT, b -> {});

        FierceDecoration.BLOCKS.getEntries().forEach(deferredBlock -> {
            if (ROWS.containsKey(deferredBlock)) ROWS.get(deferredBlock).accept(this);
            else dropSelf(deferredBlock.get());
            /*
            Block block = deferredBlock.get();
            switch (block) {
                case GuardrailBlock guardrailBlock -> {
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
                case OneCutBlock oneCutBlock -> {
                    LootPool.Builder pool1 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(ExplosionCondition.survivesExplosion())
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool2 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.DOUBLE, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootTable.Builder table = LootTable.lootTable().withPool(pool1).withPool(pool2).setParamSet(LootContextParamSets.BLOCK);
                    map.put(block.getLootTable(), table);
                }
                case DoubleCutBlock doubleCutBlock -> {
                    LootPool.Builder pool1 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_A, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool2 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_B, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool3 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_C, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool4 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_D, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootTable.Builder table = LootTable.lootTable().withPool(pool1).withPool(pool2).withPool(pool3).withPool(pool4).setParamSet(
                            LootContextParamSets.BLOCK);
                    map.put(block.getLootTable(), table);
                }
                case TripleCutBlock tripleCutBlock -> {
                    LootPool.Builder pool1 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_A, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool2 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_B, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool3 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_C, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool4 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_D, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool5 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_E, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool6 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_F, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool7 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_G, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootPool.Builder pool8 = LootPool.lootPool()
                                                     .setRolls(ConstantValue.exactly(1))
                                                     .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                                             StatePropertiesPredicate.Builder.properties().hasProperty(
                                                                     FDBlockStateProperties.PART_H, true)))
                                                     .add(LootItem.lootTableItem(block));
                    LootTable.Builder table = LootTable.lootTable().withPool(pool1).withPool(pool2).withPool(pool3).withPool(pool4)
                                                       .withPool(pool5).withPool(pool6).withPool(pool7).withPool(pool8).setParamSet(LootContextParamSets.BLOCK);
                    map.put(block.getLootTable(), table);
                }
                case CropBlock cropBlock -> {
                }
                default -> dropSelf(block);
            }
            */
        });
        //TODO:玻璃灯loot

        this.map.forEach((location, builder) -> {
            if (builder != null) generator.accept(location, builder);
        });
        this.map.clear();
    }

    public void glassLamp(DeferredBlock<? extends Block> deferredBlock, ItemLike item) {
        Block block = deferredBlock.get();
        add(block, createSingleItemTableWithSilkTouch(block, item));
    }

    public void seaLantern(DeferredBlock<? extends Block> deferredBlock) {
        Block block = deferredBlock.get();
        add(block, createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                .apply(LimitCount.limitCount(IntRange.range(1, 5)))
                )
        ));
    }
}
