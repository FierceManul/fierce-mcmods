package net.fiercemanul.fiercesource.tags;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class FSItemTags {


    public static final TagKey<Item> WRENCH_ITEM = ItemTags.create(new ResourceLocation("c", "tools/wrench"));
    public static final TagKey<Item> SOUL_CRYSTALS = ItemTags.create(new ResourceLocation(FierceSource.FC_MODID, "soul_crystals"));
}
