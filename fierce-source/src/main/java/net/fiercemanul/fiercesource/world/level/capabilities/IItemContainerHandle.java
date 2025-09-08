package net.fiercemanul.fiercesource.world.level.capabilities;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public interface IItemContainerHandle extends IItemHandler, IStackLikeContainerHandle<Item, ItemStack> {


    @Override
    default int getMaxSlots() {
        return getSlots();
    }

    @Override
    default long getSlotMaxAmount(int slot) {
        return getSlotLimit(slot);
    }
}
