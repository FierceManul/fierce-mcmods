package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.client.particle.SoulCrystalParticleProvider;
import net.fiercemanul.fiercesource.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoulCrystalBlock {


    public static final VoxelShape SMALL_SHAPE = Block.box(6.0, 2.0, 6.0, 10.0, 14.0, 10.0);
    public static final VoxelShape MEDIUM_SHAPE = Block.box(5.5, 1, 5.5, 10.5, 15, 10.5);
    public static final VoxelShape LARGE_SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
    public static final VoxelShape BASE_SHAPE = Shapes.join(
            Block.box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 1.0, 11.0),
            BooleanOp.ONLY_FIRST
    );
    public static final VoxelShape SMALL_WITH_BASE_SHAPE = Shapes.or(SMALL_SHAPE, BASE_SHAPE);
    public static final VoxelShape MEDIUM_WITH_BASE_SHAPE = Shapes.or(MEDIUM_SHAPE, BASE_SHAPE);
    public static final VoxelShape LARGE_WITH_BASE_SHAPE = Shapes.or(LARGE_SHAPE, BASE_SHAPE);

    public static BlockBehaviour.Properties getProperties(int light, MapColor mapColor) {
        return BlockBehaviour.Properties.of().strength(1.5F, 20.0F).lightLevel(value -> light).emissiveRendering(Utils::getTrue).sound(SoundType.LARGE_AMETHYST_BUD).mapColor(mapColor).noOcclusion();
    }

    public static final class Small extends ModelBlock {


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
            return SMALL_SHAPE;
        }

        @Override
        public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            if (pRandom.nextInt(5) == 0) SoulCrystalParticleProvider.spawnEmptySmallSoulCrystalParticle(pPos);
        }
    }

    public static final class Medium extends ModelBlock {


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
            return MEDIUM_SHAPE;
        }

        @Override
        public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            if (pRandom.nextInt(4) == 0) SoulCrystalParticleProvider.spawnEmptyMediumSoulCrystalParticle(pPos);
        }
    }

    public static final class Large extends ModelBlock {


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
            return LARGE_SHAPE;
        }

        @Override
        public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            if (pRandom.nextInt(3) == 0) SoulCrystalParticleProvider.spawnEmptyLargeSoulCrystalParticle(pPos);
        }

        @Override
        protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
            return direction.getAxis() == Direction.Axis.Y && adjacentState.is(this);
        }
    }


    private SoulCrystalBlock() {}
}
