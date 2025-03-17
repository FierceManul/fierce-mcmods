package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.OpenType;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.WindowBType;
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
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
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
    protected static final EnumProperty<WindowBType> TYPE = FDBlockStateProperties.WINDOW_B_TYPE;
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
                        .setValue(TYPE, WindowBType.SINGLE)
        );
    }

    @Override
    public MapCodec<? extends WindowTypeBBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN_TYPE, HINGE, TYPE, WATERLOGGED);
    }

    public BlockSetType type() {
        return this.type;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();

        BlockState state = null;
        //非潜行时连接下上
        if (context.getPlayer() == null || !context.getPlayer().isShiftKeyDown()) {
            BlockState bottomState = level.getBlockState(blockpos.below());
            BlockState topState = level.getBlockState(blockpos.above());
            boolean bottom = bottomState.is(this);
            boolean top = topState.is(this);
            if (bottom && !top) state = bottomState.setValue(TYPE, WindowBType.getTypeForPlacement(bottomState.getValue(TYPE), null));
            else if (!bottom && top) state = topState.setValue(TYPE, WindowBType.getTypeForPlacement(bottomState.getValue(TYPE), null));
            else if (bottom) {
                state = bottomState.setValue(TYPE, WindowBType.getTypeForPlacement(
                        bottomState.getValue(TYPE),
                        canTryConnect(bottomState, topState) && bottomState.getValue(OPEN_TYPE).isOpen() == topState.getValue(OPEN_TYPE).isOpen() ? topState.getValue(TYPE) : null
                ));
            }
        }
        if (state == null) {
            Direction direction = context.getHorizontalDirection();
            Direction face = context.getClickedFace();
            Vec3 clickLocation = context.getClickLocation();
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
                        .setValue(TYPE, WindowBType.SINGLE);
        }
        return state.setValue(WATERLOGGED, level.getFluidState(blockpos).getType() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockState newState = state;
        if (direction.equals(Direction.UP)) {
            BlockState downState = level.getBlockState(pos.below());
            WindowBType downType = downState.is(this) && canTryConnect(state, downState) && WindowBType.isValidConnectType(Direction.DOWN, downState.getValue(TYPE)) ? downState.getValue(TYPE) : WindowBType.SINGLE;
            if (neighborState.is(this)
                    && canTryConnect(state, neighborState)
                    && WindowBType.isValidConnectType(Direction.UP, neighborState.getValue(TYPE))
            )
                return super.updateShape(
                        state.setValue(TYPE, WindowBType.updateType(neighborState.getValue(TYPE), downType)).setValue(OPEN_TYPE, neighborState.getValue(OPEN_TYPE)),
                        Direction.UP,
                        neighborState,
                        level,
                        pos,
                        neighborPos
                );
            else newState = state.setValue(TYPE, WindowBType.updateType(WindowBType.SINGLE, downType));
        }
        else if (direction.equals(Direction.DOWN)) {
            BlockState upState = level.getBlockState(pos.above());
            WindowBType upType = upState.is(this) && canTryConnect(state, upState) && WindowBType.isValidConnectType(Direction.UP, upState.getValue(TYPE)) ? upState.getValue(TYPE) : WindowBType.SINGLE;
            if (neighborState.is(this)
                    && canTryConnect(state, neighborState)
                    && WindowBType.isValidConnectType(Direction.DOWN, neighborState.getValue(TYPE))
            )
                return super.updateShape(
                        state.setValue(TYPE, WindowBType.updateType(upType, neighborState.getValue(TYPE))).setValue(OPEN_TYPE, neighborState.getValue(OPEN_TYPE)),
                        Direction.DOWN,
                        neighborState,
                        level,
                        pos,
                        neighborPos
                );
            else newState = state.setValue(TYPE, WindowBType.updateType(upType, WindowBType.SINGLE));
        }
        boolean redstone = hasRedstoneSignal(level, pos);
        OpenType oldOpenType = state.getValue(OPEN_TYPE);
        OpenType newOpenType = oldOpenType.update(redstone);
        newState = newState.setValue(OPEN_TYPE, newOpenType);
        boolean open = newOpenType.isOpen();
        if (oldOpenType.isOpen() != open) {
            playSound(null, (Level) level, pos, open);
            level.gameEvent(null, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }
        return super.updateShape(newState, direction, neighborState, level, pos, neighborPos);
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

    private static boolean canTryConnect(BlockState pState, BlockState pState2) {
        return pState.getValue(FACING).equals(pState2.getValue(FACING)) && pState.getValue(HINGE).equals(pState2.getValue(HINGE));
    }

    private boolean hasRedstoneSignal(LevelAccessor pLevel, BlockPos pPos) {
        if (pLevel.hasNeighborSignal(pPos)) return true;
        for (int i = 1; i < pLevel.getHeight(); i++) {
            BlockPos pos = pPos.above(i);
            BlockState state = pLevel.getBlockState(pos);
            if (state.is(this) && WindowBType.isValidConnectType(Direction.UP, state.getValue(TYPE))) {
                if (pLevel.hasNeighborSignal(pos)) return true;
            }
            else break;
        }
        for (int i = 1; i < pLevel.getHeight(); i++) {
            BlockPos pos = pPos.below(i);
            BlockState state = pLevel.getBlockState(pos);
            if (state.is(this) && WindowBType.isValidConnectType(Direction.DOWN, state.getValue(TYPE))) {
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
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock().equals(this))
            return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        if (explosion.getBlockInteraction() == Explosion.BlockInteraction.TRIGGER_BLOCK
                && !level.isClientSide()
                && state.getValue(OPEN_TYPE).isPowered()
                && this.type.canOpenByWindCharge()
        ) {
            toggle(state, level, pos, null);
        }
        super.onExplosionHit(state, level, pos, explosion, dropConsumer);
    }

    private void toggle(BlockState state, Level level, BlockPos pos, @Nullable Entity entity) {
        OpenType openType = state.getValue(OPEN_TYPE).toggleByHand();
        level.setBlock(pos, state.setValue(OPEN_TYPE, openType), 2);
        if (entity != null) {
            Direction direction = state.getValue(FACING);
            DoorHingeSide doorHingeSide = state.getValue(HINGE);
            direction = doorHingeSide == DoorHingeSide.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
            BlockPos pos1 = pos.relative(direction);
            BlockState state1 = level.getBlockState(pos1);
            if (state1.getBlock() instanceof WindowTypeBBlock
                    && state1.getValue(HINGE) != doorHingeSide
                    && state1.getValue(FACING).equals(state.getValue(FACING)))
                level.setBlock(pos1, state1.setValue(OPEN_TYPE, openType), 2);
        }
        boolean open = openType.isOpen();
        playSound(entity, level, pos, open);
        level.gameEvent(entity, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
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
        return pState.getValue(OPEN_TYPE).isOpen();
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pMirror == Mirror.NONE ? pState : pState.rotate(pMirror.getRotation(pState.getValue(FACING))).cycle(HINGE);
    }

}