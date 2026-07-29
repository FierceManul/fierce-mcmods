package net.fiercemanul.fiercesource.data;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.data.registries.FCRegistries;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Function;

public class FSParticleTypes {


    public static final DeferredHolder<ParticleType<?>, ParticleType<BlockParticleOption>>
            SOUL_CRYSTAL_PARTICLE = FCRegistries.PARTICLE_TYPES.register("soul_crystal", () -> makeType(false, BlockParticleOption::codec, BlockParticleOption::streamCodec));

    public static void init() {}

    private static <T extends ParticleOptions> ParticleType<T> makeType(
            boolean overrideLimiter,
            final Function<ParticleType<T>, MapCodec<T>> codecGetter,
            final Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> streamCodecGetter
    ) {
        return new ParticleType<>(overrideLimiter) {

            @Override
            public MapCodec<T> codec() {
                return codecGetter.apply(this);
            }

            @Override
            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return streamCodecGetter.apply(this);
            }

        };
    }

}
