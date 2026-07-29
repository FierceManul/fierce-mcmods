package net.fiercemanul.fiercesource.client.particle;

import net.fiercemanul.fiercesource.world.level.block.SoulCrystalBlock;
import net.fiercemanul.fiercesource.world.level.block.SoulCrystalType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SoulCrystalParticleProvider implements ParticleProvider<BlockParticleOption> {


    private static final RandomSource RANDOM = RandomSource.create();
    private final SpriteSet spriteSet;

    public SoulCrystalParticleProvider(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }

    @Nullable
    @Override
    public Particle createParticle(
            BlockParticleOption type, ClientLevel level,
            double x, double y, double z,
            double xSpeed, double ySpeed, double zSpeed
    ) {
        BlockState state = type.getState();
        BlockPos blockpos = BlockPos.containing(x, y, z);
        if (!(state.getBlock() instanceof SoulCrystalBlock block)) return null;
        SoulCrystalType.ParticleDate date = block.getSoulCrystalType().getParticleDate(state);
        int lightEmission = state.getLightEmission(level, blockpos);
        if (lightEmission <= 0) return null;

        float pr = 1 - RANDOM.nextFloat() * date.offset();

        Vec3 camPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        AABB aabb = date.particleAABB();
        double aabbX = aabb.getXsize();
        double aabbY = aabb.getYsize();
        double aabbZ = aabb.getZsize();
        double dx = camPos.x + 0.5 - x;
        double dy = camPos.y + 0.5 - y;
        double dz = camPos.z + 0.5 - z;
        //面向摄像机的面
        Direction directionX = dx >= 0 ? Direction.EAST : Direction.WEST;
        Direction directionY = dy >= 0 ? Direction.UP : Direction.DOWN;
        Direction directionZ = dz >= 0 ? Direction.SOUTH : Direction.NORTH;
        //面权重
        double xw = Math.abs(dx * aabbY * aabbZ);
        double yw = Math.abs(dy * aabbX * aabbZ);
        double zw = Math.abs(dz * aabbX * aabbY);
        double r1 = RANDOM.nextDouble() * (xw + zw + yw);
        Direction direction = r1 <= xw ? directionX : r1 <= xw + zw ? directionZ : directionY;
        //遮挡时忽略
        var ds = date.coveredDirection();
        if (ds.contains(direction)) return null;

        double stepX = direction.getStepX();
        double stepY = direction.getStepY();
        double stepZ = direction.getStepZ();
        float size = date.size() * pr;
        double x1 = stepX == 0 ? RANDOM.nextDouble() * aabbX + aabb.minX : stepX < 0 ? aabb.minX - size : aabb.maxX + size;
        double y1 = stepY == 0 ? RANDOM.nextDouble() * aabbY + aabb.minY : stepY < 0 ? aabb.minY - size : aabb.maxY + size;
        double z1 = stepZ == 0 ? RANDOM.nextDouble() * aabbZ + aabb.minZ : stepZ < 0 ? aabb.minZ - size : aabb.maxZ + size;

        float maxAlpha = lightEmission / 15F * pr;
        int lifetime = Math.round(date.lifeTick() * pr);
        return new SoulCrystalParticle(
                level, x + x1, y + y1, z + z1,
                date.r(), date.g(), date.b(), maxAlpha,
                size, lifetime,
                spriteSet
        );
    }

}
