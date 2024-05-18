package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.FacingBlock;
import net.fiercemanul.fiercesource.world.level.block.FacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;



public class PeepWindowBlock extends FacingModelBlock {


    public static final MapCodec<PeepWindowBlock> CODEC = simpleCodec(PeepWindowBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 0.0D, 7.0D, 7.0D, 1.0D)
            .applyCube(9.0D, 0.0D, 0.0D, 16.0D, 7.0D, 1.0D)
            .applyCube(0.0D, 9.0D, 0.0D, 7.0D, 16.0D, 1.0D)
            .applyCube(9.0D, 9.0D, 0.0D, 16.0D, 16.0D, 1.0D)
            .applyCube(7.0D, 0.0D, 0.0D, 9.0D, 2.0D, 1.0D)
            .applyCube(0.0D, 7.0D, 0.0D, 2.0D, 9.0D, 1.0D)
            .applyCube(14.0D, 7.0D, 0.0D, 16.0D, 9.0D, 1.0D)
            .applyCube(7.0D, 14.0D, 0.0D, 9.0D, 16.0D, 1.0D);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.east();
    protected static final VoxelShape SHAPE_UP = SHAPE_HELPER.up();
    protected static final VoxelShape SHAPE_DOWN = SHAPE_HELPER.down();
    private static final VoxelShapeHelper COLLISION_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    protected static final VoxelShape COLLISION_SHAPE_NORTH = COLLISION_SHAPE_HELPER.north();
    protected static final VoxelShape COLLISION_SHAPE_SOUTH = COLLISION_SHAPE_HELPER.south();
    protected static final VoxelShape COLLISION_SHAPE_WEST = COLLISION_SHAPE_HELPER.west();
    protected static final VoxelShape COLLISION_SHAPE_EAST = COLLISION_SHAPE_HELPER.east();
    protected static final VoxelShape COLLISION_SHAPE_UP = COLLISION_SHAPE_HELPER.up();
    protected static final VoxelShape COLLISION_SHAPE_DOWN = COLLISION_SHAPE_HELPER.down();

    public PeepWindowBlock(Properties properties) {
        super(properties, FacingBlock.LOOKING_DIRECTION);
    }

    @Override
    protected MapCodec<? extends PeepWindowBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            case UP -> SHAPE_UP;
            case DOWN -> SHAPE_DOWN;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> COLLISION_SHAPE_NORTH;
            case SOUTH -> COLLISION_SHAPE_SOUTH;
            case WEST -> COLLISION_SHAPE_WEST;
            case EAST -> COLLISION_SHAPE_EAST;
            case UP -> COLLISION_SHAPE_UP;
            case DOWN -> COLLISION_SHAPE_DOWN;
        };
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return pState.getValue(FACING).equals(Direction.DOWN);
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentState, Direction pDirection) {
        if (!pAdjacentState.is(this)) return false;
        Direction selfDirection = pState.getValue(FACING);
        Direction otherDirection = pAdjacentState.getValue(FACING);
        if (pDirection.equals(selfDirection.getOpposite())) return false;
        if (pDirection.equals(selfDirection)) return otherDirection.getOpposite().equals(selfDirection);
        return otherDirection.equals(selfDirection) || otherDirection.getOpposite().equals(pDirection);
    }
}
