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



public class ItemFrameShellBigBlock extends FacingModelBlock {


    public static final MapCodec<ItemFrameShellBigBlock> CODEC = simpleCodec(ItemFrameShellBigBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    protected static final VoxelShape PANEL_NORTH = SHAPE_HELPER.north();
    protected static final VoxelShape PANEL_SOUTH = SHAPE_HELPER.south();
    protected static final VoxelShape PANEL_WEST = SHAPE_HELPER.west();
    protected static final VoxelShape PANEL_EAST = SHAPE_HELPER.east();
    protected static final VoxelShape PANEL_UP = SHAPE_HELPER.up();
    protected static final VoxelShape PANEL_DOWN = SHAPE_HELPER.down();
    protected static final VoxelShape SHAPE_NORTH_COLLISION = Shapes.or(
            PANEL_NORTH,
            PANEL_WEST,
            PANEL_EAST,
            PANEL_UP,
            PANEL_DOWN
    );
    protected static final VoxelShape SHAPE_SOUTH_COLLISION = Shapes.or(
            PANEL_SOUTH,
            PANEL_WEST,
            PANEL_EAST,
            PANEL_UP,
            PANEL_DOWN
    );
    protected static final VoxelShape SHAPE_WEST_COLLISION = Shapes.or(
            PANEL_NORTH,
            PANEL_SOUTH,
            PANEL_WEST,
            PANEL_UP,
            PANEL_DOWN
    );
    protected static final VoxelShape SHAPE_EAST_COLLISION = Shapes.or(
            PANEL_NORTH,
            PANEL_SOUTH,
            PANEL_EAST,
            PANEL_UP,
            PANEL_DOWN
    );
    protected static final VoxelShape SHAPE_UP_COLLISION = Shapes.or(
            PANEL_NORTH,
            PANEL_SOUTH,
            PANEL_WEST,
            PANEL_EAST,
            PANEL_UP
    );
    protected static final VoxelShape SHAPE_DOWN_COLLISION = Shapes.or(
            PANEL_NORTH,
            PANEL_SOUTH,
            PANEL_WEST,
            PANEL_EAST,
            PANEL_DOWN
    );

    public ItemFrameShellBigBlock(Properties pProperties) {
        super(pProperties, FacingBlock.CLICKED_DIRECTION);
    }

    @Override
    protected MapCodec<? extends ItemFrameShellBigBlock> codec() {
        return CODEC;
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

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return pState.getFluidState().isEmpty();
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }
}
