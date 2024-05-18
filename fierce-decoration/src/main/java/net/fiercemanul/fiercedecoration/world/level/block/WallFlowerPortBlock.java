package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingBlock;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallFlowerPortBlock extends HorizonFacingModelBlock {


    public static final MapCodec<WallFlowerPortBlock> CODEC = simpleCodec(WallFlowerPortBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(1.0D, 0.0D, 10.0D, 15.0D, 6.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.east();


    public WallFlowerPortBlock(Properties pProperties) {
        super(pProperties.strength(0.5F), HorizonFacingBlock.CLICKED_DIRECTION);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }
}
