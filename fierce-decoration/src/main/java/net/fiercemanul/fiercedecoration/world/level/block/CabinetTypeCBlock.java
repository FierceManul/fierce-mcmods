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


public class CabinetTypeCBlock extends CompoundCabinetBlock {



    public static final MapCodec<CabinetTypeCBlock> CODEC = simpleCodec(CabinetTypeCBlock::new);
    protected static final EnumProperty<ComplexCabinetType> TYPE = FDBlockStateProperties.CABINET_TYPE_C;


    public CabinetTypeCBlock(Properties pProperties) {
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
    protected MapCodec<? extends CabinetTypeCBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        Direction facing = pContext.getHorizontalDirection().getOpposite();
        Direction clickFace = pContext.getClickedFace();
        ComplexCabinetType type = ComplexCabinetType.SINGLE;
        //不与原版箱子同特性，潜行时不连接，普通放置时优先连接点击面，因为手持自身不会打开容器。
        if (!pContext.isSecondaryUseActive()) {
            if (clickFace.equals(facing.getClockWise())) {
                BlockState rightState = level.getBlockState(pos.relative(facing.getCounterClockWise()));
                if (rightState.is(this)
                        && !rightState.getValue(TYPE).equals(ComplexCabinetType.RIGHT)
                        && rightState.getValue(FACING).equals(facing)
                ) return defaultBlockState().setValue(FACING, facing).setValue(TYPE, ComplexCabinetType.RIGHT);
                BlockState leftState = level.getBlockState(pos.relative(facing.getClockWise()));
                if (leftState.is(this)
                        && !leftState.getValue(TYPE).equals(ComplexCabinetType.LEFT)
                        && leftState.getValue(FACING).equals(facing)
                ) return defaultBlockState().setValue(FACING, facing).setValue(TYPE, ComplexCabinetType.LEFT);
            } else {
                BlockState leftState = level.getBlockState(pos.relative(facing.getClockWise()));
                if (leftState.is(this)
                        && !leftState.getValue(TYPE).equals(ComplexCabinetType.LEFT)
                        && leftState.getValue(FACING).equals(facing)
                ) return defaultBlockState().setValue(FACING, facing).setValue(TYPE, ComplexCabinetType.LEFT);
                BlockState rightState = level.getBlockState(pos.relative(facing.getCounterClockWise()));
                if (rightState.is(this)
                        && !rightState.getValue(TYPE).equals(ComplexCabinetType.RIGHT)
                        && rightState.getValue(FACING).equals(facing)
                ) return defaultBlockState().setValue(FACING, facing).setValue(TYPE, ComplexCabinetType.RIGHT);
            }
        }
        return defaultBlockState().setValue(FACING, facing).setValue(TYPE, type);
    }

    @Override
    public BlockState updateShape(
            BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos
    ) {
        Direction facing = pState.getValue(FACING);
        ComplexCabinetType myType = pState.getValue(TYPE);
        if (myType.equals(ComplexCabinetType.SINGLE)) {
            if (pFacing.equals(facing.getCounterClockWise())
                    && pFacingState.is(this)
                    && pFacingState.getValue(FACING).equals(facing)
                    && pFacingState.getValue(TYPE).equals(ComplexCabinetType.LEFT))
                return pState.setValue(TYPE, ComplexCabinetType.RIGHT);
            if (pFacing.equals(facing.getClockWise())
                    && pFacingState.is(this)
                    && pFacingState.getValue(FACING).equals(facing)
                    && pFacingState.getValue(TYPE).equals(ComplexCabinetType.RIGHT))
                return pState.setValue(TYPE, ComplexCabinetType.LEFT);
        }
        else {
            Direction checkFace = myType.equals(ComplexCabinetType.RIGHT) ? facing.getCounterClockWise() : facing.getClockWise();
            BlockState checkState =  pLevel.getBlockState(pCurrentPos.relative(checkFace));
            if (!checkState.is(this) || !checkState.getValue(FACING).equals(facing) || !checkState.getValue(TYPE).equals(myType.getOpposite()))
                return pState.setValue(TYPE, ComplexCabinetType.SINGLE);
        }
        return pState;
    }

    @Override
    protected DoubleBlockCombiner.BlockType getCombinerType(BlockState pState) {
        return pState.getValue(TYPE).getCombinerType();
    }

    @Override
    protected Direction getConnectedDirection(BlockState pState) {
        return pState.getValue(TYPE).getConnectDirection(pState.getValue(FACING));
    }

    @Override
    public boolean shouldInvalidateCapabilities(BlockState oldState, BlockState newState) {
        return (oldState.getValue(FACING) != newState.getValue(FACING)) || (oldState.getValue(TYPE) != newState.getValue(TYPE));
    }

}
