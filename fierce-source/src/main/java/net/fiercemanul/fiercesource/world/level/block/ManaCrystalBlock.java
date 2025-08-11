package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.client.particle.SoulCrystalParticleProvider;


public class ManaCrystalBlock extends SoulCrystalBlock {


    public static final SoulCrystalType SMALL = new SoulCrystalType(
            SMALL_SHAPE,
            (state, level, pos, random) -> {
                if (random.nextInt(5) == 0) SoulCrystalParticleProvider.spawnSmallSoulCrystalParticle(pos, 0.66F, 0.92F, 0.95F);
            }
    );
    public static final SoulCrystalType MEDIUM = new SoulCrystalType(
            MEDIUM_SHAPE,
            (state, level, pos, random) -> {
                if (random.nextInt(4) == 0) SoulCrystalParticleProvider.spawnMediumSoulCrystalParticle(pos, 0.66F, 0.92F, 0.95F);
            }
    );
    public static final SoulCrystalType LARGE = new SoulCrystalType(
            LARGE_SHAPE,
            (state, level, pos, random) -> {
                if (random.nextInt(3) == 0) SoulCrystalParticleProvider.spawnLargeSoulCrystalParticle(pos, 0.66F, 0.92F, 0.95F);
            }
    );
    public static final MapCodec<ManaCrystalBlock> CODEC = simpleCodec(ManaCrystalBlock::new);


    public ManaCrystalBlock(Properties pProperties) {
        super(pProperties, LARGE);
    }

    public ManaCrystalBlock(Properties properties, SoulCrystalType soulCrystalType) {
        super(properties, soulCrystalType);
    }


    @Override
    protected MapCodec<? extends ManaCrystalBlock> codec() {
        return CODEC;
    }

}
