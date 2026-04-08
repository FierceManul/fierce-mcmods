package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fiercemanul.fiercelive.world.level.block.state.properties.FLBlockStateProperties;
import net.fiercemanul.fiercelive.world.level.block.state.properties.OpenType;
import net.fiercemanul.fiercelive.world.level.block.state.properties.TallBlockType;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingWaterloggedBlock;
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

public class GlassWindowBlock extends HorizonFacingWaterloggedBlock {


    public static final MapCodec<GlassWindowBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(BlockSetType.CODEC.fieldOf("block_set_type").forGetter(GlassWindowBlock::type),
                                       propertiesCodec()).apply(instance, GlassWindowBlock::new));
    protected static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    protected static final EnumProperty<OpenType> OPEN_TYPE = FLBlockStateProperties.OPEN_TYPE;
    protected static final EnumProperty<TallBlockType> TYPE = FLBlockStateProperties.TALL_BLOCK_TYPE;
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

    public GlassWindowBlock(BlockSetType blockSetType, BlockBehaviour.Properties properties) {
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
    public MapCodec<? extends GlassWindowBlock> codec() {
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
            if (bottom || top) {
                state = bottom ? bottomState : topState;
                TallBlockType type = TallBlockType.SINGLE;
                if (bottom) type = type.connect(Direction.DOWN);
                if (top) type = type.connect(Direction.UP);
                state = state.setValue(TYPE, type);
            }
        }
        if (state == null) {
            Direction direction = context.getHorizontalDirection();
            Vec3 clickLocation = context.getClickLocation();
            DoorHingeSide hinge = getHinge(
                    direction,
                    clickLocation.x - blockpos.getX(),
                    clickLocation.z - blockpos.getZ(),
                    context.getClickedFace()
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
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockState newState = state;
        if (direction.getAxis().isVertical()) {
            TallBlockType myType = state.getValue(TYPE);
            if (neighborState.is(this) && canTryConnect(state, neighborState)) {
                TallBlockType neighborType = neighborState.getValue(TYPE);
                if (TallBlockType.isConnectedByOther(direction, neighborType)) myType = myType.connect(direction);
                return super.updateShape(
                        newState.setValue(TYPE, myType)
                                .setValue(OPEN_TYPE, neighborState.getValue(OPEN_TYPE)),
                        direction, neighborState, level, pos, neighborPos);
            }
            else myType = myType.unConnect(direction);
            newState = newState.setValue(TYPE, myType);
        }
        OpenType oldOpenType = state.getValue(OPEN_TYPE);
        OpenType newOpenType = oldOpenType.redstoneUpdate(hasRedstoneSignal(state, level, pos));
        newState = newState.setValue(OPEN_TYPE, newOpenType);
        boolean open = newOpenType.isOpen();
        if (oldOpenType.isOpen() != open) {
            playSound(null, (Level) level, pos, open);
            level.gameEvent(null, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }
        return super.updateShape(newState, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        OpenType oldOpenType = state.getValue(OPEN_TYPE);
        OpenType newOpenType = oldOpenType.redstoneUpdate(hasRedstoneSignal(state, level, pos));
        boolean open = newOpenType.isOpen();
        if (oldOpenType.isOpen() != open) {
            playSound(null, level, pos, open);
            level.gameEvent(null, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }
        level.setBlock(pos, state.setValue(OPEN_TYPE, newOpenType), 2);
    }

    private static DoorHingeSide getHinge(Direction direction, double x, double z, Direction face) {
        switch (direction) {
            case NORTH -> {
                if (x < 0.5) return DoorHingeSide.LEFT;
                else if (x > 0.5) return DoorHingeSide.RIGHT;
                else if (face.equals(Direction.EAST)) return DoorHingeSide.LEFT;
            }
            case SOUTH -> {
                if (x < 0.5) return DoorHingeSide.RIGHT;
                else if (x > 0.5) return DoorHingeSide.LEFT;
                else if (face.equals(Direction.WEST)) return DoorHingeSide.LEFT;
            }
            case WEST -> {
                if (z < 0.5) return DoorHingeSide.RIGHT;
                else if (z > 0.5) return DoorHingeSide.LEFT;
                else if (face.equals(Direction.NORTH)) return DoorHingeSide.LEFT;
            }
            case EAST -> {
                if (z < 0.5) return DoorHingeSide.LEFT;
                else if (z > 0.5) return DoorHingeSide.RIGHT;
                else if (face.equals(Direction.SOUTH)) return DoorHingeSide.LEFT;
            }
            case DOWN, UP -> {
                return DoorHingeSide.LEFT;
            }
        }
        return DoorHingeSide.LEFT;
    }

    private static boolean canTryConnect(BlockState state, BlockState neighborState) {
        return state.getValue(FACING).equals(neighborState.getValue(FACING)) && state.getValue(HINGE).equals(neighborState.getValue(HINGE));
    }

    private boolean hasRedstoneSignal(BlockState state, LevelAccessor level, BlockPos pos) {
        if (level.hasNeighborSignal(pos)) return true;
        TallBlockType type = state.getValue(TYPE);
        if (type != TallBlockType.SINGLE) {
            if (type != TallBlockType.TOP) for (int i = 1; i < level.getHeight(); i++) {
                BlockPos pos1 = pos.above(i);
                if (level.hasNeighborSignal(pos1)) return true;
                BlockState state1 = level.getBlockState(pos1);
                if (!state1.is(this)) break;
                TallBlockType type1 = state1.getValue(TYPE);
                if (type1 == TallBlockType.TOP) break;
            }
            if (type != TallBlockType.BOTTOM) for (int i = 1; i < level.getHeight(); i++) {
                BlockPos pos1 = pos.below(i);
                if (level.hasNeighborSignal(pos1)) return true;
                BlockState state1 = level.getBlockState(pos1);
                if (!state1.is(this)) break;
                TallBlockType type1 = state1.getValue(TYPE);
                if (type1 == TallBlockType.BOTTOM) break;
            }
        }
        return false;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!this.type.canOpenByHand()) return InteractionResult.PASS;
        toggle(state, level, pos, player);
        return InteractionResult.sidedSuccess(level.isClientSide);
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
            if (state1.getBlock() instanceof GlassWindowBlock
                    && state1.getValue(HINGE) != doorHingeSide
                    && state1.getValue(FACING).equals(state.getValue(FACING)))
                level.setBlock(pos1, state1.setValue(OPEN_TYPE, openType), 2);
        }
        boolean open = openType.isOpen();
        playSound(entity, level, pos, open);
        level.gameEvent(entity, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    private void playSound(@Nullable Entity source, Level level, BlockPos pos, boolean isOpening) {
        level.playSound(
                source,
                pos,
                isOpening ? this.type.doorOpen() : this.type.doorClose(),
                SoundSource.BLOCKS,
                1.0F,
                level.getRandom().nextFloat() * 0.1F + 0.9F
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> state.getValue(OPEN_TYPE).isOpen() ? state.getValue(HINGE).equals(DoorHingeSide.LEFT) ? SHAPE_NORTH_OPEN : SHAPE_NORTH_OPEN_MIRROR : SHAPE_NORTH;
            case SOUTH -> state.getValue(OPEN_TYPE).isOpen() ? state.getValue(HINGE).equals(DoorHingeSide.LEFT) ? SHAPE_SOUTH_OPEN : SHAPE_SOUTH_OPEN_MIRROR : SHAPE_SOUTH;
            case WEST -> state.getValue(OPEN_TYPE).isOpen() ? state.getValue(HINGE).equals(DoorHingeSide.LEFT) ? SHAPE_WEST_OPEN : SHAPE_WEST_OPEN_MIRROR : SHAPE_WEST;
            case EAST -> state.getValue(OPEN_TYPE).isOpen() ? state.getValue(HINGE).equals(DoorHingeSide.LEFT) ? SHAPE_EAST_OPEN : SHAPE_EAST_OPEN_MIRROR : SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return state.getValue(OPEN_TYPE).isOpen();
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return mirror == Mirror.NONE ? state : state.rotate(mirror.getRotation(state.getValue(FACING))).cycle(HINGE);
    }

}