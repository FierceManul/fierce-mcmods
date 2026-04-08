package net.fiercemanul.fiercelive.data.registries;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.BiConsumer;

public interface IBlockBulkRegisterProperty {


    void register(BlockMaterial key, BiConsumer<DeferredBlock<Block>, BlockMaterial> dataGen);
}
