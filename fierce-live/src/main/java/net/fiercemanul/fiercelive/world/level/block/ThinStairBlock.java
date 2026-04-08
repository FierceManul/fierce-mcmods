package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class ThinStairBlock extends Block implements SimpleWaterloggedBlock {


    public static final MapCodec<ThinStairBlock> CODEC = simpleCodec(ThinStairBlock::new);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape[] SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 8.0, 0.0, 16.0, 16.0, 8.0)
                                  .applyCube(0.0, 0.0, 8.0, 16.0, 8.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 8.0, 0.0, 16.0, 16.0, 8.0)
                                  .applyCube(0.0, 8.0, 8.0, 8.0, 16.0, 16.0)
                                  .applyCube(8.0, 0.0, 8.0, 16.0, 8.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 8.0, 0.0, 8.0, 16.0, 8.0)
                                  .applyCube(8.0, 0.0, 0.0, 16.0, 8.0, 8.0)
                                  .applyCube(0.0, 0.0, 8.0, 16.0, 8.0, 16.0)
    );

    public static VoxelShape[] makeShapes(VoxelShapeHelper straight, VoxelShapeHelper inner, VoxelShapeHelper outer) {
        VoxelShape[] shapes = new VoxelShape[]{
                straight.north(),
                straight.south(),
                straight.west(),
                straight.east(),
                inner.north(),
                inner.south(),
                inner.west(),
                inner.east(),
                outer.north(),
                outer.south(),
                outer.west(),
                outer.east()
        };
        int north = Direction.NORTH.get2DDataValue();
        int south = Direction.SOUTH.get2DDataValue();
        int west = Direction.WEST.get2DDataValue();
        int east = Direction.EAST.get2DDataValue();
        int straightIndex = StairsShape.STRAIGHT.ordinal() << 2;
        int innerLeft = StairsShape.INNER_LEFT.ordinal() << 2;
        int innerRight = StairsShape.INNER_RIGHT.ordinal() << 2;
        int outerLeft = StairsShape.OUTER_LEFT.ordinal() << 2;
        int outerRight = StairsShape.OUTER_RIGHT.ordinal() << 2;
        VoxelShape[] shapes1 = new VoxelShape[20];
        shapes1[straightIndex + north] = shapes[0];
        shapes1[straightIndex + south] = shapes[1];
        shapes1[straightIndex + west] = shapes[2];
        shapes1[straightIndex + east] = shapes[3];
        shapes1[innerLeft + north] = shapes[4];
        shapes1[innerLeft + south] = shapes[5];
        shapes1[innerLeft + west] = shapes[6];
        shapes1[innerLeft + east] = shapes[7];
        shapes1[innerRight + north] = shapes[7];
        shapes1[innerRight + south] = shapes[6];
        shapes1[innerRight + west] = shapes[4];
        shapes1[innerRight + east] = shapes[5];
        shapes1[outerLeft + north] = shapes[8];
        shapes1[outerLeft + south] = shapes[9];
        shapes1[outerLeft + west] = shapes[10];
        shapes1[outerLeft + east] = shapes[11];
        shapes1[outerRight + north] = shapes[11];
        shapes1[outerRight + south] = shapes[10];
        shapes1[outerRight + west] = shapes[8];
        shapes1[outerRight + east] = shapes[9];
        return shapes1;
    }

    public int getShapeIndex(BlockState state) {
        return state.getValue(SHAPE).ordinal() << 2 | state.getValue(FACING).get2DDataValue();
    }

    public ThinStairBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SHAPE, StairsShape.STRAIGHT).setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends ThinStairBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SHAPE, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockPos blockpos = context.getClickedPos();
        Direction facing = context.getHorizontalDirection();
        BlockState blockstate = this.defaultBlockState()
                                    .setValue(
                                            FACING,
                                            direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double)blockpos.getY() > 0.5))
                                            ? facing : facing.getOpposite()
                                    )
                                    .setValue(WATERLOGGED, context.getLevel().getFluidState(blockpos).getType() == Fluids.WATER);
        return blockstate.setValue(SHAPE, getStairsShape(blockstate, context.getLevel(), blockpos));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        return facing.getAxis().isHorizontal()
               ? state.setValue(SHAPE, getStairsShape(state, level, currentPos))
               : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    protected StairsShape getStairsShape(BlockState state, BlockGetter level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockState blockstate = level.getBlockState(pos.relative(direction));
        if (isFamily(blockstate)) {
            Direction direction1 = blockstate.getValue(FACING);
            if (direction1.getAxis() != state.getValue(FACING).getAxis() && canTakeShape(state, level, pos, direction1.getOpposite())) {
                if (direction1 == direction.getCounterClockWise()) return StairsShape.OUTER_LEFT;
                return StairsShape.OUTER_RIGHT;
            }
        }

        BlockState otherState = level.getBlockState(pos.relative(direction.getOpposite()));
        if (isFamily(otherState)) {
            Direction direction2 = otherState.getValue(FACING);
            if (direction2.getAxis() != state.getValue(FACING).getAxis() && canTakeShape(state, level, pos, direction2)) {
                if (direction2 == direction.getCounterClockWise()) return StairsShape.INNER_LEFT;
                return StairsShape.INNER_RIGHT;
            }
        }
        return StairsShape.STRAIGHT;
    }

    protected boolean canTakeShape(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        BlockState blockstate = level.getBlockState(pos.relative(face));
        return !isFamily(blockstate) || blockstate.getValue(FACING) != state.getValue(FACING);
    }

    public boolean isFamily(BlockState state) {
        return state.getBlock().getClass() == ThinStairBlock.class;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[getShapeIndex(state)];
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        Direction direction = state.getValue(FACING);
        StairsShape stairsshape = state.getValue(SHAPE);
        switch (mirror) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {
                    return switch (stairsshape) {
                        case INNER_LEFT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
                        case INNER_RIGHT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
                        case OUTER_LEFT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
                        default -> state.rotate(Rotation.CLOCKWISE_180);
                    };
                }
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {
                    return switch (stairsshape) {
                        case INNER_LEFT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
                        case INNER_RIGHT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
                        case OUTER_LEFT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT -> state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
                        case STRAIGHT -> state.rotate(Rotation.CLOCKWISE_180);
                    };
                }
        }

        return super.mirror(state, mirror);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchUtils.interactRotate(stack, state, level, pos, player);
    }

}
