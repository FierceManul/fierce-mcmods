package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.FacingBlock;
import net.fiercemanul.fiercesource.world.level.block.FacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class ItemFrameShellThinBlock extends FacingModelBlock {


    public static final MapCodec<ItemFrameShellThinBlock> CODEC = simpleCodec(ItemFrameShellThinBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 6.0D);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.east();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_UP = SHAPE_HELPER.down();
    protected static final VoxelShape SHAPE_DOWN = SHAPE_HELPER.up();
    private static final VoxelShapeHelper COLLISION_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 5.0D, 16.0D, 16.0D, 6.0D)
            .applyCube(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 5.0D)
            .applyCube(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 5.0D)
            .applyCube(0.0D, 1.0D, 0.0D, 1.0D, 15.0D, 5.0D)
            .applyCube(15.0D, 1.0D, 0.0D, 16.0D, 15.0D, 5.0D);
    protected static final VoxelShape SHAPE_NORTH_COLLISION = COLLISION_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_SOUTH_COLLISION = COLLISION_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_WEST_COLLISION = COLLISION_SHAPE_HELPER.east();
    protected static final VoxelShape SHAPE_EAST_COLLISION = COLLISION_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_UP_COLLISION = COLLISION_SHAPE_HELPER.down();
    protected static final VoxelShape SHAPE_DOWN_COLLISION = COLLISION_SHAPE_HELPER.up();


    public ItemFrameShellThinBlock(Properties pProperties) {
        super(pProperties, FacingBlock.CLICKED_DIRECTION);
    }

    @Override
    protected MapCodec<? extends ItemFrameShellThinBlock> codec() {
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
            case NORTH -> SHAPE_NORTH_COLLISION;
            case SOUTH -> SHAPE_SOUTH_COLLISION;
            case WEST -> SHAPE_WEST_COLLISION;
            case EAST -> SHAPE_EAST_COLLISION;
            case UP -> SHAPE_UP_COLLISION;
            case DOWN -> SHAPE_DOWN_COLLISION;
        };
    }

    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }
}
