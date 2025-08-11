package net.fiercemanul.fiercesource.registries;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FSParticleTypes {


    public static final DeferredHolder<ParticleType<?>, SimpleParticleType>
            SOUL_CRYSTAL_PARTICLE = FCRegistries.PARTICLE_TYPE.register("soul_crystal", () -> new SimpleParticleType(false));

    public static void init() {}
}
