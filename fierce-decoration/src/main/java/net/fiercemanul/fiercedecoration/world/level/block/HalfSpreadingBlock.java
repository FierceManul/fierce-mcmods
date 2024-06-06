package net.fiercemanul.fiercedecoration.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class HalfSpreadingBlock extends HalfPodzolBlock {


    public HalfSpreadingBlock(Properties pProperties, Block doubleBlock) {
        super(pProperties, doubleBlock);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.randomTick(pState, pLevel, pPos, pRandom);
        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        if (pState.getValue(LAYERS) != 1)
            if (pLevel.getBlockState(pPos.above()).getFluidState().getAmount() == 8
                    || pState.getValue(WATERLOGGED)
                    || pLevel.getMaxLocalRawBrightness(pPos) <= 0
            ) pLevel.setBlockAndUpdate(
                    pPos,
                    FDBlocks.HALF_DIRT.get().defaultBlockState()
                                      .setValue(LAYERS, pState.getValue(LAYERS))
                                      .setValue(WATERLOGGED, pState.getValue(WATERLOGGED))
            );

        if (pLevel.isAreaLoaded(pPos, 3) && pLevel.getMaxLocalRawBrightness(pPos) >= 9) {
            BlockState blockstate = doubleBlock.defaultBlockState();

            for(int i = 0; i < 4; ++i) {
                BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                if (blockstate.hasProperty(BlockStateProperties.SNOWY))
                    blockstate = blockstate.setValue(BlockStateProperties.SNOWY, pLevel.getBlockState(blockpos.above()).is(Blocks.SNOW));
                if (pLevel.getBlockState(blockpos).is(Blocks.DIRT) && SpreadingSnowyDirtBlock.canPropagate(blockstate, pLevel, blockpos)) {
                    pLevel.setBlockAndUpdate(blockpos, blockstate);
                }
            }
        }
    }
}
