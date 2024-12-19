package net.fiercemanul.fiercesource.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class SoulCrystalParticleProvider implements ParticleProvider<SimpleParticleType> {



    private static final RandomSource RANDOM = RandomSource.create();
    public static final Supplier<Integer> DEFAULT_LIFETIME = () -> 16 + RANDOM.nextInt(8);
    public static final Supplier<Float> DEFAULT_SIZE = () -> RANDOM.nextFloat() * 0.03F + 0.07F;
    public static final Supplier<Float> DEFAULT_SIZE_MEDIUM = () -> RANDOM.nextFloat() * 0.02F + 0.06F;
    public static final Supplier<Float> DEFAULT_SIZE_SMALL = () -> RANDOM.nextFloat() * 0.01F + 0.05F;
    @Nullable
    private static SpriteSet spriteSet;

    public SoulCrystalParticleProvider(SpriteSet spriteSet) {
        SoulCrystalParticleProvider.spriteSet = spriteSet;
    }

    @Nullable
    @Override
    public Particle createParticle(
            SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed
    ) {
        return spriteSet != null ? new SoulCrystalParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, spriteSet) : null;
    }

    public static void spawnParticle(double pX, double pY, double pZ, float pRed, float pGreen, float pBlue, float pSize) {
        spawnParticle(pX, pY, pZ, 0.0, 0.0, 0.0, pRed, pGreen, pBlue, pSize, DEFAULT_LIFETIME.get());
    }

    public static void spawnParticle(double pX, double pY, double pZ, float pRed, float pGreen, float pBlue, float pSize, int pLifeTick) {
        spawnParticle(pX, pY, pZ, 0.0, 0.0, 0.0, pRed, pGreen, pBlue, pSize, pLifeTick);
    }

    public static void spawnParticle(double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, float pRed, float pGreen, float pBlue, float pSize, int pLifeTick) {
        if (spriteSet == null) return;
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;
        Minecraft.getInstance().particleEngine.add(
                new SoulCrystalParticle(
                        level,
                        spriteSet,
                        pX,
                        pY,
                        pZ,
                        pXSpeed,
                        pYSpeed,
                        pZSpeed,
                        pRed,
                        pGreen,
                        pBlue,
                        pSize,
                        pLifeTick
                ));
    }

    public static void spawnEmptyLargeSoulCrystalParticle(BlockPos pPos) {
        spawnLargeSoulCrystalParticle(pPos, 1.0F, 1.0F, 1.0F);
    }

    public static void spawnLargeSoulCrystalParticle(BlockPos pPos, float r, float g, float b) {
        Direction direction = Direction.getRandom(RANDOM);
        double stepX = direction.getStepX();
        double stepY = direction.getStepY();
        double stepZ = direction.getStepZ();
        double x = stepX == 0 ? RANDOM.nextDouble() * 0.625 + 0.1875 : 0.5 + stepX * 0.1875 + stepX * RANDOM.nextDouble() * 0.125;
        double y = stepY == 0 ? RANDOM.nextDouble() * 1.125 - 0.0625 : 0.5 + stepY * 0.5 + stepY * RANDOM.nextDouble() * 0.0625;
        double z = stepZ == 0 ? RANDOM.nextDouble() * 0.625 + 0.1875 : 0.5 + stepZ * 0.1875 + stepZ * RANDOM.nextDouble() * 0.125;
        spawnParticle(
                pPos.getX() + x,
                pPos.getY() + y,
                pPos.getZ() + z,
                r, g, b,
                DEFAULT_SIZE.get()
        );
    }

    public static void spawnEmptyMediumSoulCrystalParticle(BlockPos pPos) {
        spawnMediumSoulCrystalParticle(pPos, 1.0F, 1.0F, 1.0F);
    }

    public static void spawnMediumSoulCrystalParticle(BlockPos pPos, float r, float g, float b) {
        Direction direction = Direction.getRandom(RANDOM);
        double stepX = direction.getStepX();
        double stepY = direction.getStepY();
        double stepZ = direction.getStepZ();
        double x = stepX == 0 ? RANDOM.nextDouble() * 0.5625 + 0.21875 : 0.5 + stepX * 0.15625 + stepX * RANDOM.nextDouble() * 0.125;
        double y = stepY == 0 ? RANDOM.nextDouble() : 0.5 + stepY * 0.28125 + stepY * RANDOM.nextDouble() * 0.0625;
        double z = stepZ == 0 ? RANDOM.nextDouble() * 0.5625 + 0.21875 : 0.5 + stepZ * 0.15625 + stepZ * RANDOM.nextDouble() * 0.125;
        spawnParticle(
                pPos.getX() + x,
                pPos.getY() + y,
                pPos.getZ() + z,
                r, g, b,
                DEFAULT_SIZE_MEDIUM.get()
        );
    }

    public static void spawnEmptySmallSoulCrystalParticle(BlockPos pPos) {
        spawnSmallSoulCrystalParticle(pPos, 1.0F, 1.0F, 1.0F);
    }

    public static void spawnSmallSoulCrystalParticle(BlockPos pPos, float r, float g, float b) {
        Direction direction = Direction.getRandom(RANDOM);
        double stepX = direction.getStepX();
        double stepY = direction.getStepY();
        double stepZ = direction.getStepZ();
        double x = stepX == 0 ? RANDOM.nextDouble() * 0.5 + 0.25 : 0.5 + stepX * 0.125 + stepX * RANDOM.nextDouble() * 0.125;
        double y = stepY == 0 ? RANDOM.nextDouble() * 0.875 + 0.0625 : 0.5 + stepY * 0.375 + stepY * RANDOM.nextDouble() * 0.0625;
        double z = stepZ == 0 ? RANDOM.nextDouble() * 0.5 + 0.25 : 0.5 + stepZ * 0.125 + stepZ * RANDOM.nextDouble() * 0.125;
        spawnParticle(
                pPos.getX() + x,
                pPos.getY() + y,
                pPos.getZ() + z,
                r, g, b,
                DEFAULT_SIZE_SMALL.get()
        );
    }
}
