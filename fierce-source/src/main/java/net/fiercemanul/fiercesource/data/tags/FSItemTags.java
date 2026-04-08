package net.fiercemanul.fiercesource.data.tags;

import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class FSItemTags {


    public static final TagKey<Item> WRENCH_ITEM = ItemTags.create(FSUtils.rl("c", "tools/wrench"));
    public static final TagKey<Item> HAO_STONES = ItemTags.create(FSUtils.rl("hao_stones"));
    public static final TagKey<Item> DEEP_STONES = ItemTags.create(FSUtils.rl("deep_stones"));
    public static final TagKey<Item> SOUL_CRYSTALS = ItemTags.create(FSUtils.rl("soul_crystals"));

}
