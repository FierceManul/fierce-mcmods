package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public interface FSDamageTypes {


    ResourceKey<DamageType> THROWN_BLOCK = ResourceKey.create(Registries.DAMAGE_TYPE, FSUtils.rl("thrown_block"));

}
