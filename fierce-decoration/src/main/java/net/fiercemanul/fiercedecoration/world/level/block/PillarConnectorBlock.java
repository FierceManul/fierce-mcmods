package net.fiercemanul.fiercedecoration.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.tags.FDBlockTags;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public abstract class PillarConnectorBlock extends ModelBlock {


    private static final Direction[] DIRECTIONS = Direction.values();
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    protected static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final ImmutableMap<Direction, VoxelShape> SUPPORT_SHAPES = ImmutableMap.of(
            Direction.NORTH, Block.box(7.0, 7.0, 0.0, 9.0, 9.0, 1.0),
            Direction.SOUTH, Block.box(7.0, 7.0, 15.0, 9.0, 9.0, 16.0),
            Direction.WEST, Block.box(0.0, 7.0, 7.0, 1.0, 9.0, 9.0),
            Direction.EAST, Block.box(15.0, 7.0, 7.0, 16.0, 9.0, 9.0),
            Direction.DOWN, Block.box(7.0, 0.0, 7.0, 9.0, 1.0, 9.0),
            Direction.UP, Block.box(7.0, 15.0, 7.0, 9.0, 16.0, 9.0)
    );

    protected final float apothem;
    protected final VoxelShape[] shapeByIndex;
    public final VoxelShape SUPPORT_SHAPE;

    public PillarConnectorBlock(float pApothem, VoxelShape[] shapeByIndex, VoxelShape supportShape, Properties pProperties) {
        super(pProperties);
        this.apothem = pApothem;
        this.shapeByIndex = shapeByIndex;
        this.SUPPORT_SHAPE = supportShape;
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(NORTH, false)
                        .setValue(EAST, false)
                        .setValue(SOUTH, false)
                        .setValue(WEST, false)
                        .setValue(UP, false)
                        .setValue(DOWN, false)
                        .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected abstract MapCodec<? extends PillarConnectorBlock> codec();

    public static VoxelShape[] makeShapes(float pApothem) {
        float f = 0.5F - pApothem;
        float f1 = 0.5F + pApothem;
        VoxelShape voxelshape = Shapes.box(f, f, f, f1, f1, f1);
        VoxelShape[] avoxelshape = new VoxelShape[DIRECTIONS.length];

        for(int i = 0; i < DIRECTIONS.length; ++i) {
            Direction direction = DIRECTIONS[i];
            avoxelshape[i] = Shapes.box(
                    0.5 + Math.min(-pApothem, (double)direction.getStepX() * 0.5),
                    0.5 + Math.min(-pApothem, (double)direction.getStepY() * 0.5),
                    0.5 + Math.min(-pApothem, (double)direction.getStepZ() * 0.5),
                    0.5 + Math.max(pApothem, (double)direction.getStepX() * 0.5),
                    0.5 + Math.max(pApothem, (double)direction.getStepY() * 0.5),
                    0.5 + Math.max(pApothem, (double)direction.getStepZ() * 0.5)
            );
        }

        VoxelShape[] aVoxelShape1 = new VoxelShape[64];

        for(int k = 0; k < 64; ++k) {
            VoxelShape voxelShape1 = voxelshape;

            for(int j = 0; j < DIRECTIONS.length; ++j) {
                if ((k & 1 << j) != 0) {
                    voxelShape1 = Shapes.or(voxelShape1, avoxelshape[j]);
                }
            }

            aVoxelShape1[k] = voxelShape1;
        }

        return aVoxelShape1;
    }

    public static VoxelShape makeSupportShape(float pApothem) {
        float a = 0.5F - pApothem;
        float b = 0.5F + pApothem;
        return Shapes.or(
                Shapes.box(a, a, 0.0, b, b, 1.0),
                Shapes.box(a, 0.0, a, b, 1.0, b),
                Shapes.box(0.0, a, a, 1.0, b, b)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return this.defaultBlockState()
                   .setValue(NORTH, canSupport(level, pos.north(), Direction.SOUTH))
                   .setValue(SOUTH, canSupport(level, pos.south(), Direction.NORTH))
                   .setValue(WEST, canSupport(level, pos.west(), Direction.EAST))
                   .setValue(EAST, canSupport(level, pos.east(), Direction.WEST))
                   .setValue(UP, canSupport(level, pos.above(), Direction.DOWN))
                   .setValue(DOWN, canSupport(level, pos.below(), Direction.UP))
                   .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    public static boolean canSupport(LevelReader pLevel, BlockPos pPos, Direction pDirection) {
        BlockState state = pLevel.getBlockState(pPos);
        if (state.getBlock() instanceof PillarConnectorBlock) return true;
        if (state.is(getPillarConnectTag(pDirection))) return true;
        return state.isFaceSturdy(pLevel, pPos, pDirection, SupportType.FULL)
                || !Shapes.joinIsNotEmpty(state.getBlockSupportShape(pLevel, pPos).getFaceShape(pDirection),
                                          SUPPORT_SHAPES.get(pDirection.getOpposite()), BooleanOp.ONLY_SECOND);
    }

    public static TagKey<Block> getPillarConnectTag(Direction direction) {
        return switch (direction) {
            case UP -> FDBlockTags.PILLAR_FORCE_CONNECT_UP;
            case DOWN -> FDBlockTags.PILLAR_FORCE_CONNECT_DOWN;
            default -> FDBlockTags.PILLAR_FORCE_CONNECT_SIDE;
        };
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.shapeByIndex[this.getAABBIndex(pState)];
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return SUPPORT_SHAPE;
    }

    protected int getAABBIndex(BlockState pState) {
        int i = 0;

        for(int j = 0; j < DIRECTIONS.length; ++j) {
            if (pState.getValue(PipeBlock.PROPERTY_BY_DIRECTION.get(DIRECTIONS[j]))) {
                i |= 1 << j;
            }
        }

        return i;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return super.updateShape(
                state.setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(direction), canSupport(level, neighborPos, direction.getOpposite())),
                direction,
                neighborState,
                level,
                pos,
                neighborPos
        );
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return pState.getFluidState().isEmpty();
    }

    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return false;
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        boolean success = WrenchAction.doWrenchConnectAction(stack, state, level, pos, player, apothem, hitResult);
        return success ? ItemInteractionResult.sidedSuccess(level.isClientSide) : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
