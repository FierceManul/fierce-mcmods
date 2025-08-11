package net.fiercemanul.fiercesource.world.item;

import net.minecraft.world.level.ItemLike;

public record CreativeModeTabItem(ItemLike itemLike, int priority) implements Comparable<CreativeModeTabItem> {

    @Override
    public int compareTo(CreativeModeTabItem o) {
        return Integer.compare(this.priority, o.priority);
    }
}
