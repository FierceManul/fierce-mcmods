package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.world.level.block.FacingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;



public class LampInGlassBlock extends FacingBlock {


    public static final MapCodec<LampInGlassBlock> CODEC = simpleCodec(LampInGlassBlock::new);

    public LampInGlassBlock(Properties pProperties) {
        super(pProperties
                      .strength(0.5F, 1.5F)
                      .isSuffocating(FSUtils::getFalse)
                      .isValidSpawn(FSUtils::getFalse)
                      .isRedstoneConductor(FSUtils::getFalse)
                      .sound(SoundType.GLASS)
                      .noOcclusion(),
              FacingBlock.CLICKED_DIRECTION);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    protected MapCodec<? extends LampInGlassBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return true;
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.getBlock() instanceof LampInGlassBlock || super.skipRendering(pState, pAdjacentBlockState, pSide);
    }
}
