package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.level.block.entity.StarBlockEntity;
import net.fiercemanul.fiercesource.util.Utils;
import net.fiercemanul.fiercesource.world.level.block.WrenchDismantleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;



public class StarBlock extends WrenchDismantleBlock implements EntityBlock {


    public static final MapCodec<StarBlock> CODEC = simpleCodec(StarBlock::new);

    public StarBlock(Properties pProperties) {
        super(pProperties
                      .strength(0.3F, 6.0F)
                      .lightLevel(value -> 7)
                      .isValidSpawn(Utils::getFalse)
                      .isRedstoneConductor(Utils::getFalse)
                      .sound(SoundType.GLASS)
                      .noOcclusion());
    }

    @Override
    protected MapCodec<? extends StarBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new StarBlockEntity(pPos, pState);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pCurrentPos);
            if (blockEntity instanceof StarBlockEntity starBlock) starBlock.updateFace(pNeighborPos, pDirection);
        }
        return pState;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentState, Direction pDirection) {
        return pAdjacentState.is(this) || super.skipRendering(pState, pAdjacentState, pDirection);
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }
}
