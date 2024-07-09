package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.OpenType;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.TallBlockType;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class WindowTypeBBlock extends HorizonFacingModelBlock {


    public static final MapCodec<WindowTypeBBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(BlockSetType.CODEC.fieldOf("block_set_type").forGetter(WindowTypeBBlock::type),
                                       propertiesCodec()).apply(instance, WindowTypeBBlock::new));
    protected static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    protected static final EnumProperty<OpenType> OPEN_TYPE = FDBlockStateProperties.OPEN_TYPE;
    protected static final EnumProperty<TallBlockType> TYPE = FDBlockStateProperties.TALL_BLOCK_TYPE;
    protected static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper().applyCube(0.0, 0.0, 5.0, 16.0, 16.0, 8.0);
    protected static final VoxelShapeHelper SHAPE_HELPER_OPEN = new VoxelShapeHelper().applyCube(0.0, 0.0, -8.0, 3.0, 16.0, 8.0);
    protected static final VoxelShapeHelper SHAPE_HELPER_OPEN_MIRROR = new VoxelShapeHelper().applyCube(13.0, 0.0, -8.0, 16.0, 16.0, 8.0);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_NORTH_OPEN = SHAPE_HELPER_OPEN.north();
    protected static final VoxelShape SHAPE_NORTH_OPEN_MIRROR = SHAPE_HELPER_OPEN_MIRROR.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_SOUTH_OPEN = SHAPE_HELPER_OPEN.south();
    protected static final VoxelShape SHAPE_SOUTH_OPEN_MIRROR = SHAPE_HELPER_OPEN_MIRROR.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_WEST_OPEN = SHAPE_HELPER_OPEN.west();
    protected static final VoxelShape SHAPE_WEST_OPEN_MIRROR = SHAPE_HELPER_OPEN_MIRROR.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.east();
    protected static final VoxelShape SHAPE_EAST_OPEN = SHAPE_HELPER_OPEN.east();
    protected static final VoxelShape SHAPE_EAST_OPEN_MIRROR = SHAPE_HELPER_OPEN_MIRROR.east();

    private final BlockSetType type;

    public WindowTypeBBlock(BlockSetType blockSetType, BlockBehaviour.Properties properties) {
        super(properties);
        this.type = blockSetType;
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(OPEN_TYPE, OpenType.CLOSE)
                        .setValue(HINGE, DoorHingeSide.LEFT)
                        .setValue(TYPE, TallBlockType.SINGLE)
        );
    }

    @Override
    public MapCodec<? extends WindowTypeBBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, OPEN_TYPE, HINGE, TYPE, WATERLOGGED);
    }

    public BlockSetType type() {
        return this.type;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Level level = pContext.getLevel();

        BlockState bottomState = level.getBlockState(blockpos.below());
        BlockState topState = level.getBlockState(blockpos.above());
        boolean bottom = bottomState.is(this);
        boolean top = topState.is(this);
        boolean snake = pContext.getPlayer() != null && pContext.getPlayer().isShiftKeyDown();

        BlockState state;
        //非潜行时连接下上
        if (!snake && bottom && !top) state = bottomState.setValue(TYPE, TallBlockType.TOP);
        else if (!snake && !bottom && top) state = topState.setValue(TYPE, TallBlockType.BOTTOM);
        else if (!snake && bottom) {
            if (canConnectTo(bottomState, topState) && bottomState.getValue(OPEN_TYPE).isOpen() == topState.getValue(OPEN_TYPE).isOpen())
                state = bottomState.setValue(TYPE, TallBlockType.CENTER);
            else state = bottomState.setValue(TYPE, TallBlockType.TOP);
        }
        else {
            Direction direction = pContext.getHorizontalDirection();
            Direction face = pContext.getClickedFace();
            Vec3 clickLocation = pContext.getClickLocation();
            DoorHingeSide hinge = getHinge(
                    direction,
                    clickLocation.x - blockpos.getX(),
                    clickLocation.z - blockpos.getZ(),
                    face
            );

            state = this.defaultBlockState()
                        .setValue(FACING, direction)
                        .setValue(HINGE, hinge)
                        .setValue(OPEN_TYPE, level.hasNeighborSignal(blockpos) ? OpenType.OPEN : OpenType.CLOSE)
                        .setValue(TYPE, TallBlockType.SINGLE);
        }
        return state.setValue(WATERLOGGED, level.getFluidState(blockpos).getType() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        BlockState newState = pState;
        if (pFacing.getAxis().equals(Direction.Axis.Y)) {
            TallBlockType tallBlockType = pState.getValue(TYPE);
            if (pFacingState.is(this)
                    && canConnectTo(pState, pFacingState)
                    && TallBlockType.isConnectedByOther(pFacing, pFacingState.getValue(TYPE))
            )
                return super.updateShape(
                        pState.setValue(TYPE, tallBlockType.connect(pFacing)).setValue(OPEN_TYPE, pFacingState.getValue(OPEN_TYPE)),
                        pFacing,
                        pFacingState,
                        pLevel,
                        pCurrentPos,
                        pFacingPos
                );
            else newState = pState.setValue(TYPE, tallBlockType.unConnect(pFacing));
        }
        boolean redstone = hasRedstoneSignal(pLevel, pCurrentPos);
        OpenType oldOpenType = pState.getValue(OPEN_TYPE);
        OpenType newOpenType = oldOpenType.update(redstone);
        newState = newState.setValue(OPEN_TYPE, newOpenType);
        boolean open = newOpenType.isOpen();
        if (oldOpenType.isOpen() != open) {
            playSound(null, (Level) pLevel, pCurrentPos, open);
            pLevel.gameEvent(null, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pCurrentPos);
        }
        return super.updateShape(newState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    private static DoorHingeSide getHinge(Direction direction, double x, double z, Direction face) {
        switch (direction) {
            case NORTH -> {
                if (x < 0.5) return DoorHingeSide.RIGHT;
                else if (x > 0.5) return DoorHingeSide.LEFT;
                else if (face.equals(Direction.EAST)) return DoorHingeSide.RIGHT;
            }
            case SOUTH -> {
                if (x < 0.5) return DoorHingeSide.LEFT;
                else if (x > 0.5) return DoorHingeSide.RIGHT;
                else if (face.equals(Direction.WEST)) return DoorHingeSide.RIGHT;
            }
            case WEST -> {
                if (z < 0.5) return DoorHingeSide.LEFT;
                else if (z > 0.5) return DoorHingeSide.RIGHT;
                else if (face.equals(Direction.NORTH)) return DoorHingeSide.RIGHT;
            }
            case EAST -> {
                if (z < 0.5) return DoorHingeSide.RIGHT;
                else if (z > 0.5) return DoorHingeSide.LEFT;
                else if (face.equals(Direction.SOUTH)) return DoorHingeSide.RIGHT;
            }
            case DOWN, UP -> {
                return DoorHingeSide.LEFT;
            }
        }
        return DoorHingeSide.LEFT;
    }

    private static boolean canConnectTo(BlockState pState, BlockState pState2) {
        return pState.getValue(FACING).equals(pState2.getValue(FACING)) && pState.getValue(HINGE).equals(pState2.getValue(HINGE));
    }

    private boolean hasRedstoneSignal(LevelAccessor pLevel, BlockPos pPos) {
        if (pLevel.hasNeighborSignal(pPos)) return true;
        for (int i = 1; i < pLevel.getHeight(); i++) {
            BlockPos pos = pPos.above(i);
            BlockState state = pLevel.getBlockState(pos);
            if (state.is(this) && TallBlockType.isConnectedByOther(Direction.UP, state.getValue(TYPE))) {
                if (pLevel.hasNeighborSignal(pos)) return true;
            }
            else break;
        }
        for (int i = 1; i < pLevel.getHeight(); i++) {
            BlockPos pos = pPos.below(i);
            BlockState state = pLevel.getBlockState(pos);
            if (state.is(this) && TallBlockType.isConnectedByOther(Direction.DOWN, state.getValue(TYPE))) {
                if (pLevel.hasNeighborSignal(pos)) return true;
            }
            else break;
        }
        return false;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (!this.type.canOpenByHand()) return InteractionResult.PASS;
        toggle(pState, pLevel, pPos, pPlayer);
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        if (pStack.getItem() instanceof BlockItem blockItem && blockItem.getBlock().equals(this))
            return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHitResult);
    }

    @Override
    protected void onExplosionHit(BlockState pState, Level pLevel, BlockPos pPos, Explosion pExplosion, BiConsumer<ItemStack, BlockPos> pDropConsumer) {
        if (pExplosion.getBlockInteraction() == Explosion.BlockInteraction.TRIGGER_BLOCK
                && !pLevel.isClientSide()
                && pState.getValue(OPEN_TYPE).isPowered()
                && this.type.canOpenByWindCharge()
        ) {
            toggle(pState, pLevel, pPos, null);
        }
        super.onExplosionHit(pState, pLevel, pPos, pExplosion, pDropConsumer);
    }

    private void toggle(BlockState pState, Level pLevel, BlockPos pPos, @Nullable Entity entity) {
        OpenType openType = pState.getValue(OPEN_TYPE).toggleByHand();
        pLevel.setBlock(pPos, pState.setValue(OPEN_TYPE, openType), 2);
        boolean open = openType.isOpen();
        playSound(entity, pLevel, pPos, open);
        pLevel.gameEvent(entity, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pPos);
    }

    private void playSound(@Nullable Entity pSource, Level pLevel, BlockPos pPos, boolean pIsOpening) {
        pLevel.playSound(
                pSource,
                pPos,
                pIsOpening ? this.type.doorOpen() : this.type.doorClose(),
                SoundSource.BLOCKS,
                1.0F,
                pLevel.getRandom().nextFloat() * 0.1F + 0.9F
        );
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> pState.getValue(OPEN_TYPE).isOpen() ? pState.getValue(HINGE).equals(DoorHingeSide.LEFT) ? SHAPE_NORTH_OPEN : SHAPE_NORTH_OPEN_MIRROR : SHAPE_NORTH;
            case SOUTH -> pState.getValue(OPEN_TYPE).isOpen() ? pState.getValue(HINGE).equals(DoorHingeSide.LEFT) ? SHAPE_SOUTH_OPEN : SHAPE_SOUTH_OPEN_MIRROR : SHAPE_SOUTH;
            case WEST -> pState.getValue(OPEN_TYPE).isOpen() ? pState.getValue(HINGE).equals(DoorHingeSide.LEFT) ? SHAPE_WEST_OPEN : SHAPE_WEST_OPEN_MIRROR : SHAPE_WEST;
            case EAST -> pState.getValue(OPEN_TYPE).isOpen() ? pState.getValue(HINGE).equals(DoorHingeSide.LEFT) ? SHAPE_EAST_OPEN : SHAPE_EAST_OPEN_MIRROR : SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return switch (pPathComputationType) {
            case LAND, AIR -> pState.getValue(OPEN_TYPE).isOpen();
            case WATER -> false;
        };
    }

    @Override
    protected boolean skipRendering(BlockState pState, BlockState pAdjacentState, Direction pDirection) {
        if (pDirection.getAxis().equals(Direction.Axis.Y) && pAdjacentState.is(this))
            return canConnectTo(pState, pAdjacentState)
                    && TallBlockType.isConnectedByOther(pDirection, pAdjacentState.getValue(TYPE))
                    && pState.getValue(OPEN_TYPE).isOpen() == pAdjacentState.getValue(OPEN_TYPE).isOpen();
        return super.skipRendering(pState, pAdjacentState, pDirection);
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pMirror == Mirror.NONE ? pState : pState.rotate(pMirror.getRotation(pState.getValue(FACING))).cycle(HINGE);
    }

}