package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercelive.world.level.block.state.properties.FLBlockStateProperties;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.WeakHashMap;

public abstract class GuardrailBlock extends FLStairBlock {


    protected static final VoxelShape[] SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 0.0, 1.0, 16.0, 16.0, 4.0),
            new VoxelShapeHelper().applyCube(1.0, 0.0, 1.0, 16.0, 16.0, 4.0)
                                  .applyCube(1.0, 0.0, 4.0, 4.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 1.0, 4.0, 16.0, 4.0)
                                  .applyCube(1.0, 0.0, 0.0, 4.0, 16.0, 1.0)
    );
    protected static final VoxelShape[] BOTTOM_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, -8.0, 1.0, 16.0, 8.0, 4.0),
            new VoxelShapeHelper().applyCube(1.0, -8.0, 1.0, 16.0, 8.0, 4.0)
                                  .applyCube(1.0, -8.0, 4.0, 4.0, 8.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, -8.0, 1.0, 4.0, 8.0, 4.0)
                                  .applyCube(1.0, -8.0, 0.0, 4.0, 8.0, 1.0)
    );
    private static final VoxelShape HALF_FACE = Shapes.box(0, 0.375, 0, 1, 0.5, 1);
    private static final WeakHashMap<BlockState, Boolean> SLAB_LIKE_CACHE = new WeakHashMap<>();

    public GuardrailBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState belowState = context.getLevel().getBlockState(context.getClickedPos().below());
        BlockState blockstate = this.defaultBlockState()
                                    .setValue(FACING, context.getHorizontalDirection())
                                    .setValue(HALF, isSlabLike(belowState, context.getLevel(), blockpos) ? Half.BOTTOM : Half.TOP)
                                    .setValue(WATERLOGGED, context.getLevel().getFluidState(blockpos).getType() == Fluids.WATER);
        return blockstate.setValue(SHAPE, getStairsShape(blockstate, context.getLevel(), blockpos));
    }

    public boolean isFamily(BlockState state) {
        return state.getBlock() instanceof GuardrailBlock;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (state.getValue(HALF) == Half.TOP ? SHAPES : BOTTOM_SHAPES)[getShapeIndex(state)];
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        state = state.setValue(HALF, isSlabLike(level.getBlockState(currentPos.below()), level, currentPos) ? Half.BOTTOM : Half.TOP);
        return super.updateShape(state.setValue(SHAPE, getStairsShape(state, level, currentPos)), facing, facingState, level, currentPos, facingPos);
    }

    protected boolean isSlabLike(BlockState state, BlockGetter level, BlockPos pos) {
        if (SLAB_LIKE_CACHE.containsKey(state)) return SLAB_LIKE_CACHE.get(state);
        Block block = state.getBlock();
        boolean slabLike = (
                block instanceof SlabBlock && state.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM
        ) || (
                block instanceof OneCutBlock
                        && state.getValue(BlockStateProperties.FACING) == Direction.DOWN
                        && !state.getValue(FLBlockStateProperties.DOUBLE)
        );
        if (!slabLike) {
            VoxelShape shape = state.getCollisionShape(level, pos);
            boolean b = !shape.isEmpty() && shape.bounds().maxY == 0.5;
            if (b) slabLike = !Shapes.joinIsNotEmpty(shape, HALF_FACE, BooleanOp.ONLY_SECOND);
        }
        SLAB_LIKE_CACHE.put(state, slabLike);
        return slabLike;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return direction == Direction.DOWN
                && state.getValue(HALF) == Half.BOTTOM
                && adjacentState.canOcclude()
                && adjacentState.getBlock() instanceof SlabBlock
                && adjacentState.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM;
    }

}
