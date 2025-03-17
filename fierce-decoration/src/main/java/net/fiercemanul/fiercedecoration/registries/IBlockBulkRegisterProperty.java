package net.fiercemanul.fiercedecoration.registries;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.BiConsumer;

public interface IBlockBulkRegisterProperty {


    void register(BlockBulkRegisterKey key, BiConsumer<DeferredBlock<Block>, BlockBulkRegisterKey> dataGen);
}
