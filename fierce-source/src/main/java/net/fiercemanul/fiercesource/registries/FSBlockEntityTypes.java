package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.world.level.block.entity.CreativeManaOutputBlockEntity;
import net.fiercemanul.fiercesource.world.level.block.entity.TestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class FSBlockEntityTypes {


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CreativeManaOutputBlockEntity>>
            CREATIVE_MANA_OUTPUT_BLOCK_ENTITY = FCRegistries.BLOCK_ENTITY_TYPE.register(
            "creative_mana_output_block_entity",
            () -> BlockEntityType.Builder.of(CreativeManaOutputBlockEntity::new, FSBlocksAndItems.CREATIVE_MANA_OUTPUT.getBlock()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TestBlockEntity>>
            TEST_BLOCK_ENTITY = FCRegistries.BLOCK_ENTITY_TYPE.register(
            "test_block_entity",
            () -> BlockEntityType.Builder.of(TestBlockEntity::new, FSBlocksAndItems.TEST_BLOCK.getBlock()).build(null));

    public static void init() {}

}
