package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SimpleChairBlock extends AbstractChairBlock {


    public static final MapCodec<SimpleChairBlock> CODEC = simpleCodec(SimpleChairBlock::new);
    protected static final VoxelShape SHAPE = Shapes.or(
            Block.box(2.0, 6.0, 2.0, 14.0, 8.0, 14.0),
            Block.box(2.0, 0.0, 2.0, 4.0, 6.0, 4.0),
            Block.box(12.0, 0.0, 2.0, 14.0, 6.0, 4.0),
            Block.box(2.0, 0.0, 12.0, 4.0, 6.0, 14.0),
            Block.box(12.0, 0.0, 12.0, 14.0, 6.0, 14.0)
    );
    protected static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper().applyCube(2.0, 8.0, 13.0, 14.0, 19.0, 14.0);
    protected static final VoxelShape SHAPE_NORTH = Shapes.or(SHAPE, SHAPE_HELPER.north());
    protected static final VoxelShape SHAPE_SOUTH = Shapes.or(SHAPE, SHAPE_HELPER.south());
    protected static final VoxelShape SHAPE_WEST = Shapes.or(SHAPE, SHAPE_HELPER.west());
    protected static final VoxelShape SHAPE_EAST = Shapes.or(SHAPE, SHAPE_HELPER.east());


    public SimpleChairBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public MapCodec<? extends SimpleChairBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE;
        };
    }
}
