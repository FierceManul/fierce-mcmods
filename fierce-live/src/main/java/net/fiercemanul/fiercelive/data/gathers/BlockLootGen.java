package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.registries.FLRegister;
import net.fiercemanul.fiercelive.world.level.block.IronLadderBlock;
import net.fiercemanul.fiercelive.world.level.block.state.properties.FLBlockStateProperties;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collections;
import java.util.HashMap;
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
        //TODO:搞不懂麻酱的 孤立survivesExplosion，似乎完全没用。
        ROWS.put(FLBlocks.FOX_CARROTS, b -> {});
        ROWS.put(FLBlocks.IRON_LADDER, b -> ironLadder(FLBlocks.IRON_LADDER));

        FLRegister.BLOCKS.getEntries().forEach(deferredBlock -> {
            if (ROWS.containsKey(deferredBlock)) ROWS.get(deferredBlock).accept(this);
            else dropSelf(deferredBlock.get());
        });

        this.map.forEach((location, builder) -> {
            if (builder != null) generator.accept(location, builder);
        });
        this.map.clear();
    }

    public void oneCutBlock(DeferredBlock<? extends Block> deferredBlock) {
        Block block = deferredBlock.get();
        add(block, createSlabItemTable(block));
    }

    public void doubleCutBlock(DeferredBlock<? extends Block> deferredBlock) {
        Block block = deferredBlock.get();
        add(block, LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(block)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                           .when(stateCondition(block, FLBlockStateProperties.PART_A, false)))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                           .when(stateCondition(block, FLBlockStateProperties.PART_B, false)))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                           .when(stateCondition(block, FLBlockStateProperties.PART_C, false)))
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                           .when(stateCondition(block, FLBlockStateProperties.PART_D, false)))
                ))
            )
        );
    }

    public void tripleCutBlock(DeferredBlock<? extends Block> deferredBlock) {
        Block block = deferredBlock.get();
        add(block, LootTable.lootTable().withPool(
                    LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(
                            block,
                            LootItem.lootTableItem(block)
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(8.0F)))
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                               .when(stateCondition(block, FLBlockStateProperties.PART_A, false)))
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                               .when(stateCondition(block, FLBlockStateProperties.PART_B, false)))
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                               .when(stateCondition(block, FLBlockStateProperties.PART_C, false)))
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                               .when(stateCondition(block, FLBlockStateProperties.PART_D, false)))
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                               .when(stateCondition(block, FLBlockStateProperties.PART_E, false)))
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                               .when(stateCondition(block, FLBlockStateProperties.PART_F, false)))
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                               .when(stateCondition(block, FLBlockStateProperties.PART_G, false)))
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)
                                                               .when(stateCondition(block, FLBlockStateProperties.PART_H, false)))
                    ))
            )
        );
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

    public void ironLadder(DeferredBlock<? extends IronLadderBlock> deferredBlock) {
        var block = deferredBlock.get();
        add(block, LootTable.lootTable().withPool(
                LootPool.lootPool().add(applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(block.frameBlock).when(stateCondition(block, FLBlockStateProperties.FRAMED, true))
                ))
        ).withPool(
                LootPool.lootPool().add(applyExplosionCondition(
                        block,
                        LootItem.lootTableItem(block)
                ))
        ));
    }

    public <T extends Comparable<T> & StringRepresentable> LootItemCondition.Builder stateCondition(Block block, Property<T> property, T value) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                StatePropertiesPredicate.Builder.properties().hasProperty(property, value));
    }

    public LootItemCondition.Builder stateCondition(Block block, BooleanProperty property, boolean value) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                StatePropertiesPredicate.Builder.properties().hasProperty(property, value));
    }
}
