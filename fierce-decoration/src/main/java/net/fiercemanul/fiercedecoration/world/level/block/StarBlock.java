package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.level.block.entity.StarBlockEntity;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.fiercemanul.fiercesource.world.level.block.WrenchDismantleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;


public class StarBlock extends WrenchDismantleBlock implements EntityBlock {


    public static final MapCodec<StarBlock> CODEC = simpleCodec(StarBlock::new);

    public StarBlock(Properties pProperties) {
        super(pProperties
                      .strength(0.3F, 6.0F)
                      .lightLevel(value -> 7)
                      .isValidSpawn(FSUtils::getFalse)
                      .isRedstoneConductor(FSUtils::getFalse)
                      .sound(SoundType.GLASS)
                      .noOcclusion());
    }

    @Override
    protected MapCodec<? extends StarBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
        //return new StarBlockEntity(pPos, pState);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            //if (blockEntity instanceof StarBlockEntity starBlock) starBlock.updateFace(pNeighborPos, pDirection);

        }
        return state;
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
        return RenderShape.MODEL;
    }
}
