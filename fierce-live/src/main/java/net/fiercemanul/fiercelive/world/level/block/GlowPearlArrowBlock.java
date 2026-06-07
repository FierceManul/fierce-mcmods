package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.FacingWaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GlowPearlArrowBlock extends FacingWaterloggedBlock {


    public static final MapCodec<? extends GlowPearlArrowBlock> CODEC = simpleCodec(GlowPearlArrowBlock::new);
    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(6.0, 6.0, 0.0, 10.0, 10.0, 16.0);
    protected static final VoxelShape SHAPE_X = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_Y = SHAPE_HELPER.up();
    protected static final VoxelShape SHAPE_Z = SHAPE_HELPER.north();
    private static final VoxelShapeHelper COLLISION_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(6.0, 6.0, 0.0, 10.0, 10.0, 4.0);
    protected static final VoxelShape SHAPE_NORTH = COLLISION_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH = COLLISION_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST = COLLISION_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST = COLLISION_SHAPE_HELPER.east();
    protected static final VoxelShape SHAPE_UP = COLLISION_SHAPE_HELPER.up();
    protected static final VoxelShape SHAPE_DOWN = COLLISION_SHAPE_HELPER.down();

    public GlowPearlArrowBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends GlowPearlArrowBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FACING, context.getClickedFace().getOpposite());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING).getAxis()) {
            case X -> SHAPE_X;
            case Y -> SHAPE_Y;
            case Z -> SHAPE_Z;
        };
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            case UP -> SHAPE_UP;
            case DOWN -> SHAPE_DOWN;
        };
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return direction == state.getValue(FACING) && !canSurvive(state, level, pos)
               ? Blocks.AIR.defaultBlockState()
               : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        return canSupportCenter(level, pos.relative(facing), facing.getOpposite());
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        if (pathComputationType == PathComputationType.LAND) return state.getValue(FACING) == Direction.DOWN;
        return super.isPathfindable(state, pathComputationType);
    }

}
