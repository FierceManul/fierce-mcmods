package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.util.Group;
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

public class ItemBlockGroup<F extends Block, S extends BlockItem> extends Group<DeferredBlock<F>, DeferredItem<S>> implements ItemLike {


    public ItemBlockGroup(DeferredBlock<F> block, DeferredItem<S> blockItem) {
        super(block, blockItem);
    }

    public F getBlock() {
        return f.get();
    }

    public S getItem() {
        return s.get();
    }

    @Override
    public void setFirst(DeferredBlock<F> block) {
        throw new UnsupportedOperationException("ItemBlockGroup instances are immutable!");
    }

    @Override
    public void setSecond(DeferredItem<S> blockItem) {
        throw new UnsupportedOperationException("ItemBlockGroup instances are immutable!");
    }

    @Override
    public @NotNull Item asItem() {
        return s.get();
    }

    public BlockState defaultBlockState() {
        return f.get().defaultBlockState();
    }

    public ItemStack defaultStack() {
        return s.toStack();
    }

    public ItemStack defaultStack(int i) {
        return s.toStack(i);
    }

    public ResourceLocation getId() {
        return s.getId();
    }

}
