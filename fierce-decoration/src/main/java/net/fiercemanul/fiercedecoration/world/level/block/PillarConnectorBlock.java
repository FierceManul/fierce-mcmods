package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.fiercemanul.fiercesource.world.level.block.BlockUtils;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
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
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
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
    protected final float apothem;
    protected final VoxelShape[] shapeByIndex;

    public PillarConnectorBlock(float pApothem, VoxelShape[] shapeByIndex, Properties pProperties) {
        super(pProperties);
        apothem = pApothem;
        this.shapeByIndex = shapeByIndex;
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
        VoxelShape voxelshape = Block.box(
                f * 16.0F, f * 16.0F, f * 16.0F, f1 * 16.0F, f1 * 16.0F, f1 * 16.0F
        );
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        return this.defaultBlockState()
                   .setValue(NORTH, BlockUtils.hasCollision(level, pos.north()))
                   .setValue(SOUTH, BlockUtils.hasCollision(level, pos.south()))
                   .setValue(WEST, BlockUtils.hasCollision(level, pos.west()))
                   .setValue(EAST, BlockUtils.hasCollision(level, pos.east()))
                   .setValue(UP, BlockUtils.hasCollision(level, pos.above()))
                   .setValue(DOWN, BlockUtils.hasCollision(level, pos.below()))
                   .setValue(WATERLOGGED, pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.shapeByIndex[this.getAABBIndex(pState)];
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
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return super.updateShape(
                pState.setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(pFacing), !pFacingState.getCollisionShape(pLevel, pFacingPos).isEmpty()),
                pFacing,
                pFacingState,
                pLevel,
                pCurrentPos,
                pFacingPos
        );
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return pState.getFluidState().isEmpty();
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        boolean success = WrenchAction.doWrenchConnectAction(pState, pLevel, pPos, pPlayer, pHand, apothem, pHit);
        return success ? InteractionResult.sidedSuccess(pLevel.isClientSide) : InteractionResult.PASS;
    }

}
