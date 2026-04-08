package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercelive.world.level.block.state.properties.FLBlockStateProperties;
import net.fiercemanul.fiercelive.world.level.block.state.properties.LongBlockType;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractLongChairBlock extends AbstractChairBlock {


    protected static final EnumProperty<LongBlockType> TYPE = FLBlockStateProperties.LONG_BLOCK_TYPE;

    protected static VoxelShape[] buildShapes(VoxelShapeHelper single, VoxelShapeHelper left, VoxelShapeHelper right, VoxelShapeHelper center) {
        int north = Direction.NORTH.get2DDataValue();
        int south = Direction.SOUTH.get2DDataValue();
        int west = Direction.WEST.get2DDataValue();
        int east = Direction.EAST.get2DDataValue();
        int singleIndex = LongBlockType.SINGLE.ordinal() << 2;
        int leftIndex = LongBlockType.LEFT.ordinal() << 2;
        int rightIndex = LongBlockType.RIGHT.ordinal() << 2;
        int centerIndex = LongBlockType.CENTER.ordinal() << 2;
        VoxelShape[] shapes = new VoxelShape[16];
        shapes[singleIndex + north] = single.north();
        shapes[singleIndex + south] = single.south();
        shapes[singleIndex + west] = single.west();
        shapes[singleIndex + east] = single.east();
        shapes[leftIndex + north] = left.north();
        shapes[leftIndex + south] = left.south();
        shapes[leftIndex + west] = left.west();
        shapes[leftIndex + east] = left.east();
        shapes[rightIndex + north] = right.north();
        shapes[rightIndex + south] = right.south();
        shapes[rightIndex + west] = right.west();
        shapes[rightIndex + east] = right.east();
        shapes[centerIndex + north] = center.north();
        shapes[centerIndex + south] = center.south();
        shapes[centerIndex + west] = center.west();
        shapes[centerIndex + east] = center.east();
        return shapes;
    }

    protected static int getShapeIndex(BlockState state) {
        return state.getValue(TYPE).ordinal() << 2 | state.getValue(FACING).get2DDataValue();
    }

    public AbstractLongChairBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, LongBlockType.SINGLE).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos chairPos = context.getClickedPos();
        BlockState state = this.defaultBlockState()
                               .setValue(FACING, context.getHorizontalDirection().getOpposite())
                               .setValue(WATERLOGGED, level.getFluidState(chairPos).getType() == Fluids.WATER);

        return getChairState(state, chairPos, level);
    }

    @Override
    public BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos
    ) {
        return getChairState(
                super.updateShape(state, direction, neighborState, level, pos, neighborPos),
                pos,
                level
        );
    }

    private BlockState getChairState(BlockState state, BlockPos chairPos, LevelAccessor level) {
        Direction chairDirection = state.getValue(FACING);
        boolean left = false;
        boolean right = false;

        BlockState testState = level.getBlockState(chairPos.relative(chairDirection.getCounterClockWise()));
        if (testState.is(this) && testState.getValue(FACING).equals(chairDirection)) left = true;

        testState = level.getBlockState(chairPos.relative(chairDirection.getClockWise()));
        if (testState.is(this) && testState.getValue(FACING).equals(chairDirection)) right = true;

        if (left && right) return state.setValue(TYPE, LongBlockType.CENTER);
        else if (left) return state.setValue(TYPE, LongBlockType.RIGHT);
        else if (right) return state.setValue(TYPE, LongBlockType.LEFT);

        return state.setValue(TYPE, LongBlockType.SINGLE);
    }


}
