package net.fiercemanul.fiercedecoration.world.level.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class HalfDirtBlock extends HalfPodzolBlock {


    private static final Map<Block, BlockState> BLOCK_MAP = ImmutableMap
            .<Block, BlockState>builder()
            .put(Blocks.GRASS_BLOCK, FDBlocks.HALF_GRASS_BLOCK.get().defaultBlockState())
            .put(FDBlocks.HALF_GRASS_BLOCK.get(), FDBlocks.HALF_GRASS_BLOCK.get().defaultBlockState())
            .put(Blocks.MYCELIUM, FDBlocks.HALF_MYCELIUM.get().defaultBlockState())
            .put(FDBlocks.HALF_MYCELIUM.get(), FDBlocks.HALF_MYCELIUM.get().defaultBlockState())
            .build();

    public HalfDirtBlock(Properties pProperties) {
        super(pProperties, Blocks.DIRT);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.isAreaLoaded(pPos, 3)) {
            for(int i = 0; i < 4; ++i) {
                BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 1, pRandom.nextInt(3) - 1);
                BlockState blockstate = pLevel.getBlockState(blockpos);
                if (BLOCK_MAP.containsKey(blockstate.getBlock()) && pLevel.getMaxLocalRawBrightness(pPos) >= 9) {
                    BlockState blockStateNew = BLOCK_MAP.get(blockstate.getBlock());
                    pLevel.setBlockAndUpdate(
                            pPos,
                            blockStateNew.setValue(LAYERS, pState.getValue(LAYERS))
                                         .setValue(WATERLOGGED, pState.getValue(WATERLOGGED))
                    );
                    break;
                }
            }
        }
    }
}
