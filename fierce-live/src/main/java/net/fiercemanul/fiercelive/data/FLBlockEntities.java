package net.fiercemanul.fiercelive.data;

import com.google.common.collect.ImmutableSet;
import net.fiercemanul.fiercelive.data.registries.BlockBulkRegister;
import net.fiercemanul.fiercelive.world.level.block.entity.CabinetBlockEntity;
import net.fiercemanul.fiercelive.world.level.block.entity.TextButtonBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

import static net.fiercemanul.fiercelive.data.registries.FLRegister.BLOCK_ENTITIES;

public final class FLBlockEntities {


    //public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StarBlockEntity>> STAR_BLOCK_ENTITY = BLOCK_ENTITIES.register(
    //        "star_block", () -> BlockEntityType.Builder.of(StarBlockEntity::new, FLBlocks.STAR_BLOCK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CabinetBlockEntity>> CABINET_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "cabinet", () -> {
                ImmutableSet.Builder<Block> validBlocksBuilder = new ImmutableSet.Builder<>();
                BlockBulkRegister.CABINETS.forEach(deferredBlock -> validBlocksBuilder.add(deferredBlock.get()));
                return new BlockEntityType<>(CabinetBlockEntity::new, validBlocksBuilder.build(), null);
            }
    );
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TextButtonBlockEntity>> TEXT_BUTTON = BLOCK_ENTITIES.register(
            "text_button", () -> {
                ImmutableSet.Builder<Block> validBlocksBuilder = new ImmutableSet.Builder<>();
                BlockBulkRegister.TEXT_BUTTON.forEach(deferredBlock -> validBlocksBuilder.add(deferredBlock.get()));
                return new BlockEntityType<>(TextButtonBlockEntity::new, validBlocksBuilder.build(), null);
            }
    );



}
