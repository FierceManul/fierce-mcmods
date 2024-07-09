package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.ComplexCabinetType;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;


public class CabinetTypeABlock extends CompoundCabinetBlock {



    public static final MapCodec<CabinetTypeABlock> CODEC = simpleCodec(CabinetTypeABlock::new);
    protected static final EnumProperty<ComplexCabinetType> TYPE = FDBlockStateProperties.CABINET_TYPE_A;


    public CabinetTypeABlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(TYPE, ComplexCabinetType.SINGLE)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE);
    }

    @Override
    protected MapCodec<? extends CabinetTypeABlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        Direction clickFace = pContext.getClickedFace();
        Direction facing = pContext.getHorizontalDirection().getOpposite();
        //不与原版箱子同特性，潜行时不连接，普通放置时优先连接下方，除非点击上方并且上方是同类，因为手持自身不会打开容器。
        if (!pContext.isSecondaryUseActive()) {
            if (clickFace.equals(Direction.DOWN)) {
                BlockState aboveState = level.getBlockState(pos.above());
                if (aboveState.is(this)
                        && !aboveState.getValue(TYPE).equals(ComplexCabinetType.BOTTOM)
                        && aboveState.getValue(FACING).equals(facing)
                ) return defaultBlockState().setValue(FACING, facing).setValue(TYPE, ComplexCabinetType.BOTTOM);
                BlockState belowState = level.getBlockState(pos.below());
                if (belowState.is(this)
                        && !belowState.getValue(TYPE).equals(ComplexCabinetType.TOP)
                        && belowState.getValue(FACING).equals(facing)
                ) return defaultBlockState().setValue(FACING, facing).setValue(TYPE, ComplexCabinetType.TOP);
            } else {
                BlockState belowState = level.getBlockState(pos.below());
                if (belowState.is(this)
                        && !belowState.getValue(TYPE).equals(ComplexCabinetType.TOP)
                        && belowState.getValue(FACING).equals(facing)
                ) return defaultBlockState().setValue(FACING, facing).setValue(TYPE, ComplexCabinetType.TOP);
                BlockState aboveState = level.getBlockState(pos.above());
                if (aboveState.is(this)
                        && !aboveState.getValue(TYPE).equals(ComplexCabinetType.BOTTOM)
                        && aboveState.getValue(FACING).equals(facing)
                ) return defaultBlockState().setValue(FACING, facing).setValue(TYPE, ComplexCabinetType.BOTTOM);
            }
        }
        return defaultBlockState().setValue(FACING, facing).setValue(TYPE, ComplexCabinetType.SINGLE);
    }

    @Override
    public BlockState updateShape(
            BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos
    ) {
        if (pState.getValue(TYPE).equals(ComplexCabinetType.SINGLE)) {
            if (pFacing.equals(Direction.UP)
                    && pFacingState.is(this)
                    && pFacingState.getValue(FACING).equals(pState.getValue(FACING))
                    && pFacingState.getValue(TYPE).equals(ComplexCabinetType.TOP))
                return pState.setValue(TYPE, ComplexCabinetType.BOTTOM);
            if (pFacing.equals(Direction.DOWN)
                    && pFacingState.is(this)
                    && pFacingState.getValue(FACING).equals(pState.getValue(FACING))
                    && pFacingState.getValue(TYPE).equals(ComplexCabinetType.BOTTOM))
                return pState.setValue(TYPE, ComplexCabinetType.TOP);
        }
        else if (
                (
                        (pState.getValue(TYPE).equals(ComplexCabinetType.TOP) && pFacing.equals(Direction.DOWN))
                                || (pState.getValue(TYPE).equals(ComplexCabinetType.BOTTOM) && pFacing.equals(Direction.UP))
                )
                &&
                (
                        !pFacingState.is(this) || !pFacingState.getValue(FACING).equals(pState.getValue(FACING))
                )
        ) return pState.setValue(TYPE, ComplexCabinetType.SINGLE);
        return pState;
    }

    @Override
    protected DoubleBlockCombiner.BlockType getCombinerType(BlockState pState) {
        return pState.getValue(TYPE).getCombinerType();
    }

    @Override
    protected Direction getConnectedDirection(BlockState pState) {
        return pState.getValue(TYPE) == ComplexCabinetType.TOP ? Direction.DOWN : Direction.UP;
    }

    @Override
    public boolean shouldInvalidateCapabilities(BlockState oldState, BlockState newState) {
        return (oldState.getValue(FACING) != newState.getValue(FACING)) || (oldState.getValue(TYPE) != newState.getValue(TYPE));
    }

}
