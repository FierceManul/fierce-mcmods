package net.fiercemanul.fiercesource.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Set;

import static net.fiercemanul.fiercesource.data.FSParticleTypes.SOUL_CRYSTAL_PARTICLE;

public interface SoulCrystalType {


    AABB SMALL_AABB = SoulCrystalBlock.SMALL_SHAPE.toAabbs().getFirst();
    AABB MEDIUM_AABB = SoulCrystalBlock.MEDIUM_SHAPE.toAabbs().getFirst();
    AABB LARGE_AABB = SoulCrystalBlock.LARGE_SHAPE.toAabbs().getFirst();
    SoulCrystalType SMALL = new SimpleType(SoulCrystalBlock.SMALL_SHAPE, ParticleDate.SMALL, 6);
    SoulCrystalType MEDIUM = new SimpleType(SoulCrystalBlock.MEDIUM_SHAPE, ParticleDate.MEDIUM, 4);
    SoulCrystalType LARGE = new SimpleType(SoulCrystalBlock.LARGE_SHAPE, ParticleDate.LARGE, 3);
    SoulCrystalType MANA_SMALL = new SimpleType(SoulCrystalBlock.SMALL_SHAPE, ParticleDate.MANA_SMALL, 6);
    SoulCrystalType MANA_MEDIUM = new SimpleType(SoulCrystalBlock.MEDIUM_SHAPE, ParticleDate.MANA_MEDIUM, 4);
    SoulCrystalType MANA_LARGE = new SimpleType(SoulCrystalBlock.LARGE_SHAPE, ParticleDate.MANA_LARGE, 3);

    VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext collisionContext);

    void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random);

    ParticleDate getParticleDate(BlockState state);

    record ParticleDate(
            AABB particleAABB,
            Set<Direction> coveredDirection,
            float r, float g, float b,
            float size, int lifeTick,
            float offset
    ) {

        public static final ParticleDate SMALL = new ParticleDate(
                SMALL_AABB, Set.of(),
                1, 1, 1,
                0.06F, 12,
                0.2F
        );
        public static final ParticleDate MEDIUM = new ParticleDate(
                MEDIUM_AABB, Set.of(),
                1, 1, 1,
                0.08F, 16,
                0.3F
        );
        public static final ParticleDate LARGE = new ParticleDate(
                LARGE_AABB, Set.of(),
                1, 1, 1,
                0.1F, 20,
                0.4F
        );
        public static final ParticleDate MANA_SMALL = new ParticleDate(
                SMALL_AABB, Set.of(),
                0.66F, 0.92F, 0.95F,
                0.06F, 12,
                0.2F
        );
        public static final ParticleDate MANA_MEDIUM = new ParticleDate(
                MEDIUM_AABB, Set.of(),
                0.66F, 0.92F, 0.95F,
                0.08F, 16,
                0.3F
        );
        public static final ParticleDate MANA_LARGE = new ParticleDate(
                LARGE_AABB, Set.of(),
                0.66F, 0.92F, 0.95F,
                0.1F, 20,
                0.4F
        );

    }

    class SimpleType implements SoulCrystalType {


        private final VoxelShape shape;
        private final ParticleDate date;
        private final int rate;

        public SimpleType(VoxelShape shape, ParticleDate date, int rate) {
            this.shape = shape;
            this.date = date;
            this.rate = rate;
        }

        @Override
        public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext collisionContext) {
            return shape;
        }

        @Override
        public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
            if (random.nextInt(rate) == 0) level.addParticle(
                    new BlockParticleOption(SOUL_CRYSTAL_PARTICLE.get(), state).setPos(pos),
                    pos.getX(), pos.getY(), pos.getZ(),
                    0, 0, 0
            );
        }

        @Override
        public ParticleDate getParticleDate(BlockState state) {
            return date;
        }

    }

}
