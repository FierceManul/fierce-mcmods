package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ToiletBlock extends AbstractChairBlock {


    public static final MapCodec<ToiletBlock> CODEC = simpleCodec(ToiletBlock::new);
    protected static final VoxelShapeHelper SHAPE = new VoxelShapeHelper()
            .applyCube(4, 0, 2, 12, 5, 16)
            .applyCube(3, 5, 1, 13, 8, 16)
            .applyCube(3, 8, 13, 13, 11, 16)
            .applyCube(3, 7, 9, 15, 8, 13);
    protected static final VoxelShape SHAPE_NORTH = SHAPE.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE.east();

    public ToiletBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends ToiletBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

}