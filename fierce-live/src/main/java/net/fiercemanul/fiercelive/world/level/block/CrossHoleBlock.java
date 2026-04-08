package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.fiercemanul.fiercesource.world.level.block.FacingWaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrossHoleBlock extends FacingWaterloggedBlock {


    public static final MapCodec<CrossHoleBlock> CODEC = simpleCodec(CrossHoleBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0, 0, 0, 7, 7, 2)
            .applyCube(9, 0, 0, 16, 7, 2)
            .applyCube(0, 9, 0, 7, 16, 2)
            .applyCube(9, 9, 0, 16, 16, 2)
            .applyCube(7, 0, 0, 9, 3, 2)
            .applyCube(0, 7, 0, 3, 9, 2)
            .applyCube(13, 7, 0, 16, 9, 2)
            .applyCube(7, 13, 0, 9, 16, 2);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.east();
    protected static final VoxelShape SHAPE_UP = SHAPE_HELPER.up();
    protected static final VoxelShape SHAPE_DOWN = SHAPE_HELPER.down();
    private static final VoxelShapeHelper COLLISION_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0, 0, 0, 16, 16, 2);
    protected static final VoxelShape COLLISION_SHAPE_NORTH = COLLISION_SHAPE_HELPER.north();
    protected static final VoxelShape COLLISION_SHAPE_SOUTH = COLLISION_SHAPE_HELPER.south();
    protected static final VoxelShape COLLISION_SHAPE_WEST = COLLISION_SHAPE_HELPER.west();
    protected static final VoxelShape COLLISION_SHAPE_EAST = COLLISION_SHAPE_HELPER.east();
    protected static final VoxelShape COLLISION_SHAPE_UP = COLLISION_SHAPE_HELPER.up();
    protected static final VoxelShape COLLISION_SHAPE_DOWN = COLLISION_SHAPE_HELPER.down();

    public CrossHoleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends CrossHoleBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                   .setValue(FACING, context.getNearestLookingDirection())
                   .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
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
    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        if (!adjacentState.is(this)) return false;
        Direction selfDirection = state.getValue(FACING);
        Direction otherDirection = adjacentState.getValue(FACING);
        if (direction.equals(selfDirection.getOpposite())) return false;
        if (direction.equals(selfDirection)) return otherDirection.getOpposite().equals(selfDirection);
        return otherDirection.equals(selfDirection) || otherDirection.getOpposite().equals(direction);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        return WrenchUtils.interact(FACING, stack, state, level, pos, player);
    }
}
