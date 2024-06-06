package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HalfGrassBlock extends HalfSpreadingBlock implements BonemealableBlock {


    public static final MapCodec<HalfGrassBlock> CODEC = simpleCodec(HalfGrassBlock::new);

    public HalfGrassBlock(Properties pProperties) {
        super(pProperties, Blocks.GRASS_BLOCK);
    }

    @Override
    protected MapCodec<? extends HalfGrassBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        ((BonemealableBlock) Blocks.GRASS_BLOCK).performBonemeal(pLevel, pRandom, pPos, pState);
    }
}
