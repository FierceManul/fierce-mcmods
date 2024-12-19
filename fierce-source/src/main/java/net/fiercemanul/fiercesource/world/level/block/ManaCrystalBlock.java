package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.client.particle.SoulCrystalParticleProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class ManaCrystalBlock extends ModelBlock {


    public ManaCrystalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected abstract MapCodec<? extends ManaCrystalBlock> codec();


    public static class Small extends ManaCrystalBlock {


        public static final MapCodec<Small> CODEC = simpleCodec(Small::new);


        public Small(Properties pProperties) {
            super(pProperties);
        }

        @Override
        protected MapCodec<? extends Small> codec() {
            return CODEC;
        }

        @Override
        public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
            return SoulCrystalBlock.SMALL_SHAPE;
        }

        @Override
        public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            if (pRandom.nextInt(5) == 0) SoulCrystalParticleProvider.spawnSmallSoulCrystalParticle(pPos, 0.66F, 0.92F, 0.95F);
        }
    }

    public static class Medium extends ManaCrystalBlock {


        public static final MapCodec<Medium> CODEC = simpleCodec(Medium::new);


        public Medium(Properties pProperties) {
            super(pProperties);
        }

        @Override
        protected MapCodec<? extends Medium> codec() {
            return CODEC;
        }

        @Override
        public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
            return SoulCrystalBlock.MEDIUM_SHAPE;
        }

        @Override
        public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            if (pRandom.nextInt(4) == 0) SoulCrystalParticleProvider.spawnMediumSoulCrystalParticle(pPos, 0.66F, 0.92F, 0.95F);
        }
    }

    public static class Large extends ManaCrystalBlock {


        public static final MapCodec<Large> CODEC = simpleCodec(Large::new);


        public Large(Properties pProperties) {
            super(pProperties);
        }

        @Override
        protected MapCodec<? extends Large> codec() {
            return CODEC;
        }

        @Override
        public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
            return SoulCrystalBlock.LARGE_SHAPE;
        }

        @Override
        public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            if (pRandom.nextInt(3) == 0) SoulCrystalParticleProvider.spawnLargeSoulCrystalParticle(pPos, 0.66F, 0.92F, 0.95F);
        }

        @Override
        protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
            return direction.getAxis() == Direction.Axis.Y && adjacentState.is(this);
        }
    }
}
