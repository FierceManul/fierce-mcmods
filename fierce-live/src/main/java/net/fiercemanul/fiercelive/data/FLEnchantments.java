package net.fiercemanul.fiercelive.data;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public interface FLEnchantments {

    ResourceKey<Enchantment> POISON_ASPECT = ResourceKey.create(Registries.ENCHANTMENT, FSUtils.rl(FierceLive.MODID, "poison_aspect"));

}
