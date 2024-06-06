package net.fiercemanul.fiercedecoration.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.ChairType;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public abstract class AbstractLongChairBlock extends AbstractChairBlock {


    protected static final EnumProperty<ChairType> TYPE = FDBlockStateProperties.CHAIR_TYPE;
    protected static final VoxelShape SHAPE_DEFAULT_BASE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);

    protected static ImmutableMap<Direction, ImmutableMap<ChairType, VoxelShape>> buildShapes(VoxelShape base, VoxelShapeHelper back, VoxelShapeHelper left, VoxelShapeHelper right) {
        ImmutableMap.Builder<Direction, ImmutableMap<ChairType, VoxelShape>> builder = ImmutableMap.builder();
        FACING.getPossibleValues().forEach(direction -> {
            Function<VoxelShapeHelper, VoxelShape> fun = VoxelShapeHelper.getShapeFun(direction);
            builder.put(direction, ImmutableMap.of(
                    ChairType.SINGLE, Shapes.or(base, fun.apply(back), fun.apply(left), fun.apply(right)),
                    ChairType.CENTER, Shapes.or(base, fun.apply(back)),
                    ChairType.LEFT, Shapes.or(base, fun.apply(back), fun.apply(left)),
                    ChairType.RIGHT, Shapes.or(base, fun.apply(back), fun.apply(right))
            ));
        });
        return builder.build();
    }

    public AbstractLongChairBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChairType.SINGLE).setValue(WATERLOGGED, false));
    }
    @Override
    public abstract MapCodec<? extends AbstractLongChairBlock> codec();
    protected abstract ImmutableMap<Direction, ImmutableMap<ChairType, VoxelShape>> getShapeMap();
    protected abstract ImmutableMap<Direction, ImmutableMap<ChairType, VoxelShape>> getCollisionShapeMap();

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level level = pContext.getLevel();
        Direction chairDirection = pContext.getHorizontalDirection().getOpposite();
        BlockPos chairPos = pContext.getClickedPos();

        BlockState state = this.defaultBlockState()
                               .setValue(FACING, chairDirection)
                               .setValue(WATERLOGGED, level.getFluidState(chairPos).getType() == Fluids.WATER);

        return getChairState(state, chairPos, level);
    }

    @Override
    public BlockState updateShape(
            BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos
    ) {
        return getChairState(
                super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos),
                pCurrentPos,
                pLevel
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

        if (left && right) return state.setValue(TYPE, ChairType.CENTER);
        else if (left && !right) return state.setValue(TYPE, ChairType.RIGHT);
        else if (!left && right) return state.setValue(TYPE, ChairType.LEFT);

        return state.setValue(TYPE, ChairType.SINGLE);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        ChairType type = pState.getValue(TYPE);
        return getShapeMap().getOrDefault(direction, ImmutableMap.of()).getOrDefault(type, Shapes.block());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        ChairType type = pState.getValue(TYPE);
        return getCollisionShapeMap().getOrDefault(direction, ImmutableMap.of()).getOrDefault(type, Shapes.block());
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return Shapes.block();
    }

}
