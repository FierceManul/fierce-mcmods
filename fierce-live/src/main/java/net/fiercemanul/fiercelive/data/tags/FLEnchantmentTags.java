package net.fiercemanul.fiercelive.data.tags;

import net.fiercemanul.fiercelive.FierceLive;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class FLEnchantmentTags {


    public static final TagKey<Enchantment> SILK_TOUCH = create("silk_touch");

    private static TagKey<Enchantment> create(String name) {
        return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(FierceLive.MODID, name));
    }

    private static TagKey<Enchantment> createCommunity(String name) {
        return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("c", name));
    }

    private FLEnchantmentTags() {}
}
