package net.fiercemanul.fiercesource.world.item;

import net.minecraft.world.level.ItemLike;

public class CreativeModeTabItem implements Comparable<CreativeModeTabItem> {

    private final ItemLike itemLike;
    private final int priority;

    public CreativeModeTabItem(ItemLike itemLike, int priority) {
        this.itemLike = itemLike;
        this.priority = priority;
    }

    public ItemLike getItem() {
        return itemLike;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(CreativeModeTabItem o) {
        return Integer.compare(this.priority, o.priority);
    }
}
