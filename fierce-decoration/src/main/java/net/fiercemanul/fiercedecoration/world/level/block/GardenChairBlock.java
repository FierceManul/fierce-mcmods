package net.fiercemanul.fiercedecoration.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.entity.Seat;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.ChairType;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingBlock;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public class GardenChairBlock extends HorizonFacingModelBlock {


    public static final MapCodec<GardenChairBlock> CODEC = simpleCodec(GardenChairBlock::new);

    protected static final EnumProperty<ChairType> TYPE = FDBlockStateProperties.CHAIR_TYPE;
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    protected static final VoxelShapeHelper SHAPE_LEFT = new VoxelShapeHelper().applyCube(0.0, 8.0, 0.0, 2.0, 13.0, 12.0);
    protected static final VoxelShapeHelper SHAPE_RIGHT = new VoxelShapeHelper().applyCube(14.0, 8.0, 0.0, 16.0, 13.0, 12.0);
    protected static final VoxelShapeHelper SHAPE_BACK = new VoxelShapeHelper().applyCube(0.0, 8.0, 10.0, 16.0, 19.0, 16.0);
    protected static final VoxelShapeHelper COLLISION_SHAPE_BACK = new VoxelShapeHelper().applyCube(0.0, 8.0, 12.0, 16.0, 19.0, 16.0);
    protected static final ImmutableMap<Direction, ImmutableMap<ChairType, VoxelShape>> SHAPE_MAP = ImmutableMap.of(
            Direction.NORTH, buildDirectionShapes(Direction.NORTH),
            Direction.SOUTH, buildDirectionShapes(Direction.SOUTH),
            Direction.WEST, buildDirectionShapes(Direction.WEST),
            Direction.EAST, buildDirectionShapes(Direction.EAST)
    );
    protected static final ImmutableMap<Direction, ImmutableMap<ChairType, VoxelShape>> COLLISION_SHAPE_MAP = ImmutableMap.of(
            Direction.NORTH, buildCollisionShapes(Direction.NORTH),
            Direction.SOUTH, buildCollisionShapes(Direction.SOUTH),
            Direction.WEST, buildCollisionShapes(Direction.WEST),
            Direction.EAST, buildCollisionShapes(Direction.EAST)
    );
    protected static final Vec3 SIT_POS_NORTH = new Vec3(0.5, 0.5625, 0.4375);
    protected static final Vec3 SIT_POS_SOUTH = VoxelShapeHelper.south(SIT_POS_NORTH);
    protected static final Vec3 SIT_POS_WEST = VoxelShapeHelper.west(SIT_POS_NORTH);
    protected static final Vec3 SIT_POS_EAST = VoxelShapeHelper.east(SIT_POS_NORTH);

    protected static ImmutableMap<ChairType, VoxelShape> buildDirectionShapes(Direction direction) {
        Function<VoxelShapeHelper, VoxelShape> fun = VoxelShapeHelper.getShapeFun(direction);
        return ImmutableMap.of(
                ChairType.SINGLE, Shapes.or(SHAPE, fun.apply(SHAPE_BACK), fun.apply(SHAPE_LEFT), fun.apply(SHAPE_RIGHT)),
                ChairType.CENTER, Shapes.or(SHAPE, fun.apply(SHAPE_BACK)),
                ChairType.LEFT, Shapes.or(SHAPE, fun.apply(SHAPE_BACK), fun.apply(SHAPE_LEFT)),
                ChairType.RIGHT, Shapes.or(SHAPE, fun.apply(SHAPE_BACK), fun.apply(SHAPE_RIGHT))
        );
    }

    protected static ImmutableMap<ChairType, VoxelShape> buildCollisionShapes(Direction direction) {
        Function<VoxelShapeHelper, VoxelShape> fun = VoxelShapeHelper.getShapeFun(direction);
        return ImmutableMap.of(
                ChairType.SINGLE, Shapes.or(SHAPE, fun.apply(COLLISION_SHAPE_BACK), fun.apply(SHAPE_LEFT), fun.apply(SHAPE_RIGHT)),
                ChairType.CENTER, Shapes.or(SHAPE, fun.apply(COLLISION_SHAPE_BACK)),
                ChairType.LEFT, Shapes.or(SHAPE, fun.apply(COLLISION_SHAPE_BACK), fun.apply(SHAPE_LEFT)),
                ChairType.RIGHT, Shapes.or(SHAPE, fun.apply(COLLISION_SHAPE_BACK), fun.apply(SHAPE_RIGHT))
        );
    }

    public GardenChairBlock(Properties pProperties) {
        super(pProperties.noOcclusion(), HorizonFacingBlock.HORIZONTAL_DIRECTION_OPPOSITE);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChairType.SINGLE).setValue(WATERLOGGED, false));
    }

    @Override
    public MapCodec<? extends GardenChairBlock> codec() {
        return CODEC;
    }

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
        return SHAPE_MAP.getOrDefault(direction, ImmutableMap.of()).getOrDefault(type, Shapes.block());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        ChairType type = pState.getValue(TYPE);
        return COLLISION_SHAPE_MAP.getOrDefault(direction, ImmutableMap.of()).getOrDefault(type, Shapes.block());
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return Shapes.block();
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!WrenchAction.doDefaultWrenchAction(FACING, pState, pLevel, pPos, pPlayer, pHand)) {
            Vec3 vec3 = getSitPos(pState.getValue(FACING));
            Seat seat = new Seat(pLevel, pPos.getX() + vec3.x, pPos.getY() + vec3.y, pPos.getZ() + vec3.z);
            pPlayer.setYRot(pState.getValue(FACING).toYRot());
            pPlayer.setXRot(0.0F);
            if (!pLevel.isClientSide) {
                pLevel.addFreshEntity(seat);
                pPlayer.startRiding(seat);
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    protected Vec3 getSitPos(Direction direction) {
        return switch (direction) {
            case DOWN, UP -> Vec3.ZERO;
            case NORTH -> SIT_POS_NORTH;
            case SOUTH -> SIT_POS_SOUTH;
            case WEST -> SIT_POS_WEST;
            case EAST -> SIT_POS_EAST;
        };
    }
}
