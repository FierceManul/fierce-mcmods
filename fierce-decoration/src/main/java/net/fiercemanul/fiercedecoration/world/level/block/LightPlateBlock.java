package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.FacingBlock;
import net.fiercemanul.fiercesource.world.level.block.FacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class LightPlateBlock extends FacingModelBlock {


    public static final MapCodec<LightPlateBlock> CODEC = simpleCodec(LightPlateBlock::new);

    private static final VoxelShapeHelper SHAPE_SIDE_HELPER = new VoxelShapeHelper()
            .applyCube(1.0D, 6.0D, 15.0D, 15.0D, 8.0D, 16.0D)
            .applyCube(2.0D, 5.0D, 14.0D, 14.0D, 9.0D, 16.0D);
    private static final VoxelShapeHelper SHAPE_Y_HELPER = new VoxelShapeHelper()
            .applyCube(4.0D, 4.0D, 14.0D, 12.0D, 12.0D, 16.0D)
            .applyCube(3.0D, 7.0D, 15.0D, 13.0D, 9.0D, 16.0D)
            .applyCube(7.0D, 3.0D, 15.0D, 9.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_SIDE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_SIDE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE_SIDE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE_SIDE_HELPER.east();
    protected static final VoxelShape SHAPE_UP = SHAPE_Y_HELPER.up();
    protected static final VoxelShape SHAPE_DOWN = SHAPE_Y_HELPER.down();


    public LightPlateBlock(Properties pProperties) {
        super(pProperties
                      .strength(0.3F)
                      .lightLevel(value -> 15)
                      .noCollission(),
              FacingBlock.CLICKED_DIRECTION
        );
    }

    @Override
    protected MapCodec<? extends LightPlateBlock> codec() {
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
}
