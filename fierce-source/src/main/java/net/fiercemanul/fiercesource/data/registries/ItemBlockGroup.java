package net.fiercemanul.fiercesource.data.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

public record ItemBlockGroup<F extends Block, S extends BlockItem>(DeferredBlock<F> deferredBlock, DeferredItem<S> deferredItem) implements ItemLike {


    public F getBlock() {
        return deferredBlock.get();
    }

    public S getItem() {
        return deferredItem.get();
    }

    @Override
    public @NotNull Item asItem() {
        return deferredItem.get();
    }

    public BlockState defaultBlockState() {
        return deferredBlock.get().defaultBlockState();
    }

    public ItemStack defaultStack() {
        return deferredItem.toStack();
    }

    public ItemStack defaultStack(int i) {
        return deferredItem.toStack(i);
    }

    public ResourceLocation getId() {
        return deferredBlock.getId();
    }

}
