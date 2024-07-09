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
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;


public class CabinetTypeBBlock extends CompoundCabinetBlock {



    public static final MapCodec<CabinetTypeBBlock> CODEC = simpleCodec(CabinetTypeBBlock::new);
    protected static final EnumProperty<ComplexCabinetType> TYPE = FDBlockStateProperties.CABINET_TYPE;


    public CabinetTypeBBlock(Properties pProperties) {
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
    protected MapCodec<? extends CabinetTypeBBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        Direction facing = pContext.getHorizontalDirection().getOpposite();
        Direction clickFace = pContext.getClickedFace();
        Vec3 clickLocation = pContext.getClickLocation();
        ComplexCabinetType type = isMirrorPlace(facing, clickLocation.x - pos.getX(), clickLocation.z - pos.getZ(), clickFace) ? ComplexCabinetType.MIRROR : ComplexCabinetType.SINGLE;
        //不与原版箱子同特性，潜行时不连接，普通放置时优先连接点击面，然后下方，因为手持自身不会打开容器。
        if (!pContext.isSecondaryUseActive()) {
            ArrayList<Direction> directions = new ArrayList<>();
            directions.add(clickFace.getOpposite());
            if (!clickFace.equals(Direction.DOWN)) directions.add(Direction.DOWN);
            if (!clickFace.equals(Direction.UP)) directions.add(Direction.UP);
            if (!clickFace.equals(facing.getCounterClockWise())) directions.add(facing.getClockWise());
            if (!clickFace.equals(facing.getClockWise())) directions.add(facing.getCounterClockWise());
            for (Direction direction : directions) {
                BlockState otherState = level.getBlockState(pos.relative(direction));
                if (!otherState.is(this) || !otherState.getValue(FACING).equals(facing)) continue;
                ComplexCabinetType otherType = otherState.getValue(TYPE);
                if (!type.canConnectTo(direction, facing, otherType)) continue;
                type = type.getConnectToType(direction);
                break;
            }
        }
        return defaultBlockState().setValue(FACING, facing).setValue(TYPE, type);
    }

    private boolean isMirrorPlace(Direction direction, double x, double z, Direction face) {
        return switch (direction) {
            case NORTH -> {
                if (x < 0.5) yield false;
                else if (x > 0.5) yield true;
                else yield face.equals(Direction.EAST);
            }
            case SOUTH -> {
                if (x < 0.5) yield true;
                else if (x > 0.5) yield false;
                else yield face.equals(Direction.WEST);
            }
            case WEST -> {
                if (z < 0.5) yield true;
                else if (z > 0.5) yield false;
                else yield face.equals(Direction.NORTH);
            }
            case EAST -> {
                if (z < 0.5) yield false;
                else if (z > 0.5) yield true;
                else yield face.equals(Direction.SOUTH);
            }
            case DOWN, UP -> false;
        };
    }

    @Override
    public BlockState updateShape(
            BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos
    ) {
        ComplexCabinetType type = pState.getValue(TYPE);
        Direction facing = pState.getValue(FACING);
        if (type.isSingle()) {
            if (pFacingState.is(this) && facing.equals(pFacingState.getValue(FACING))) {
                ComplexCabinetType otherType = pFacingState.getValue(TYPE);
                if (!otherType.isSingle() && type.canConnectTo(pFacing, facing, otherType)) {
                    type = type.getConnectToType(pFacing);
                    return pState.setValue(TYPE, type);
                }
            }
        } else {
            BlockState otherState = pLevel.getBlockState(pCurrentPos.relative(type.getConnectDirection(facing)));
            if (otherState.is(this) && facing.equals(otherState.getValue(FACING)) && type.getOpposite().equals(otherState.getValue(TYPE))) return pState;
            return pState.setValue(TYPE, type.toSingle());
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
