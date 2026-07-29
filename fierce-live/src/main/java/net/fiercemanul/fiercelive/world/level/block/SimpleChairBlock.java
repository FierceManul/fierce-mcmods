package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SimpleChairBlock extends AbstractChairBlock {


    public static final MapCodec<SimpleChairBlock> CODEC = simpleCodec(SimpleChairBlock::new);
    protected static final VoxelShape SHAPE = Shapes.or(
            Block.box(2, 6, 2, 14, 8, 14),
            Block.box(2, 0, 2, 4, 6, 4),
            Block.box(12, 0, 2, 14, 6, 4),
            Block.box(2, 0, 12, 4, 6, 14),
            Block.box(12, 0, 12, 14, 6, 14)
    );
    protected static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper().applyCube(2, 8, 13, 14, 16, 14);
    protected static final VoxelShape SHAPE_NORTH = Shapes.or(SHAPE, SHAPE_HELPER.north());
    protected static final VoxelShape SHAPE_SOUTH = Shapes.or(SHAPE, SHAPE_HELPER.south());
    protected static final VoxelShape SHAPE_WEST = Shapes.or(SHAPE, SHAPE_HELPER.west());
    protected static final VoxelShape SHAPE_EAST = Shapes.or(SHAPE, SHAPE_HELPER.east());


    public SimpleChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends SimpleChairBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE;
        };
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
}
