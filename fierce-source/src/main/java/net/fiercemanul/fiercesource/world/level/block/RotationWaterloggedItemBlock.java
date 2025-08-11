package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RotationWaterloggedItemBlock extends ModelBlock {


    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    private static final int MAX = RotationSegment.getMaxSegmentIndex();
    private static final int ROTATIONS = MAX + 1;
    public static final VoxelShape CRYSTAL_SHARD_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0);
    public static final MapCodec<RotationWaterloggedItemBlock> CODEC = simpleCodec(properties -> new RotationWaterloggedItemBlock(properties, CRYSTAL_SHARD_SHAPE));
    private final VoxelShape shape;


    public RotationWaterloggedItemBlock(Properties properties , VoxelShape shape) {
        super(properties);
        this.shape = shape;
        this.registerDefaultState(this.defaultBlockState().setValue(ROTATION, 0));
    }

    @Override
    protected MapCodec<? extends RotationWaterloggedItemBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ROTATION);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(ROTATION, RotationSegment.convertToSegment(context.getRotation()));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !canSurvive(state, level, pos)
               ? Blocks.AIR.defaultBlockState()
               : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSupportRigidBlock(level, pos.below());
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return true;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(ROTATION, rotation.rotate(state.getValue(ROTATION), ROTATIONS));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(ROTATION, mirror.mirror(state.getValue(ROTATION), ROTATIONS));
    }
}
