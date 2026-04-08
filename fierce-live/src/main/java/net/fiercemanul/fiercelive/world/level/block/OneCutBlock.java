package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.item.WrenchUtils;
import net.fiercemanul.fiercesource.world.level.block.WaterloggedBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class OneCutBlock extends WaterloggedBlock {


    public static final MapCodec<OneCutBlock> CODEC = simpleCodec(OneCutBlock::new);

    private static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    private static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
    public static final VoxelShape SHAPE_NORTH = box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    public static final VoxelShape SHAPE_SOUTH = box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SHAPE_WEST = box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    public static final VoxelShape SHAPE_EAST = box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SHAPE_DOWN = box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    public static final VoxelShape SHAPE_UP = box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public OneCutBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Z).setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends OneCutBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, TYPE, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        if (blockState.is(this)) return blockState.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, false);
        else {
            blockState = defaultBlockState().setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
            Direction direction = getPlaceDirection(context);
            return blockState.setValue(TYPE, getPlaceType(direction)).setValue(AXIS, direction.getAxis());
        }
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        if (!useContext.getItemInHand().is(this.asItem()) || state.getValue(TYPE).equals(SlabType.DOUBLE)) return false;
        Direction direction = getPlaceDirection(useContext);
        return direction.getAxis() == state.getValue(AXIS) && state.getValue(TYPE) != getPlaceType(direction);
    }

    private SlabType getPlaceType(Direction direction) {
        return switch (direction) {
            case NORTH, WEST, DOWN -> SlabType.BOTTOM;
            case SOUTH, EAST, UP -> SlabType.TOP;
        };
    }

    private Direction getPlaceDirection(BlockPlaceContext context) {
        Direction direction;
        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
            double y = context.getClickLocation().y - context.getClickedPos().getY();
            if (y > 0.5) direction = Direction.UP;
            else if (y < 0.5) direction = Direction.DOWN;
            else direction = context.getClickedFace().equals(Direction.UP) ? Direction.UP : Direction.DOWN;
        }
        else {
            Direction.Axis axis = context.getHorizontalDirection().getAxis();
            if (axis.equals(Direction.Axis.X)) {
                double x = context.getClickLocation().x - context.getClickedPos().getX();
                if (x > 0.5) direction = Direction.EAST;
                else if (x < 0.5) direction = Direction.WEST;
                else direction = context.getClickedFace().equals(Direction.EAST) ? Direction.EAST : Direction.WEST;
            }
            else {
                double z = context.getClickLocation().z - context.getClickedPos().getZ();
                if (z > 0.5) direction = Direction.SOUTH;
                else if (z < 0.5) direction = Direction.NORTH;
                else direction = context.getClickedFace().equals(Direction.SOUTH) ? Direction.SOUTH : Direction.NORTH;
            }
        }
        return direction;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        SlabType type = state.getValue(TYPE);
        if (type == SlabType.DOUBLE) return Shapes.block();
        Direction.Axis axis = state.getValue(AXIS);
        if (type == SlabType.BOTTOM) return switch (axis) {
            case X -> SHAPE_WEST;
            case Y -> SHAPE_DOWN;
            case Z -> SHAPE_NORTH;
        };
        return switch (axis) {
            case X -> SHAPE_EAST;
            case Y -> SHAPE_UP;
            case Z -> SHAPE_SOUTH;
        };
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case NONE -> state;
            case CLOCKWISE_90 -> switch (state.getValue(AXIS)) {
                case X -> state.setValue(AXIS, Direction.Axis.Z);
                case Z -> switch (state.getValue(TYPE)) {
                    case BOTTOM -> state.setValue(TYPE, SlabType.TOP).setValue(AXIS, Direction.Axis.X);
                    case TOP -> state.setValue(TYPE, SlabType.BOTTOM).setValue(AXIS, Direction.Axis.X);
                    default -> state.setValue(AXIS, Direction.Axis.X);
                };
                default -> state;
            };
            case CLOCKWISE_180 -> switch (state.getValue(TYPE)) {
                case BOTTOM -> state.setValue(TYPE, SlabType.TOP);
                case TOP -> state.setValue(TYPE, SlabType.BOTTOM);
                default -> state;
            };
            case COUNTERCLOCKWISE_90 -> switch (state.getValue(AXIS)) {
                case X -> switch (state.getValue(TYPE)) {
                    case BOTTOM -> state.setValue(TYPE, SlabType.TOP).setValue(AXIS, Direction.Axis.Z);
                    case TOP -> state.setValue(TYPE, SlabType.BOTTOM).setValue(AXIS, Direction.Axis.Z);
                    default -> state.setValue(AXIS, Direction.Axis.X);
                };
                case Z -> state.setValue(AXIS, Direction.Axis.Z);
                default -> state;
            };
        };
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        if ((mirror == Mirror.LEFT_RIGHT && state.getValue(AXIS) == Direction.Axis.X)
                || (mirror == Mirror.FRONT_BACK && state.getValue(AXIS) == Direction.Axis.Z))
            switch (state.getValue(TYPE)) {
                case BOTTOM -> {
                    return state.setValue(TYPE, SlabType.TOP);
                }
                case TOP -> {
                    return state.setValue(TYPE, SlabType.BOTTOM);
                }
            }
        return state;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("fiercelive.tip.snake_horizontal").withStyle(ChatFormatting.GRAY));
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        if (WrenchUtils.isWrench(stack) && player.isShiftKeyDown()) {
            SlabType type = state.getValue(TYPE);
            if (type == SlabType.DOUBLE) return WrenchUtils.wrenchInteract(AXIS, state, level, pos)
                                                ? ItemInteractionResult.sidedSuccess(level.isClientSide)
                                                : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            Direction.Axis axis = state.getValue(AXIS);
            switch (axis) {
                case X -> state = state.setValue(AXIS, type == SlabType.BOTTOM ? Direction.Axis.Y : Direction.Axis.Z);
                case Y -> {
                    if (type == SlabType.TOP) state = state.setValue(AXIS, Direction.Axis.Z).setValue(TYPE, SlabType.BOTTOM);
                    else state = state.setValue(TYPE, SlabType.TOP);
                }
                case Z -> state = state.setValue(AXIS, Direction.Axis.X)
                                       .setValue(TYPE, type == SlabType.TOP ? SlabType.BOTTOM : SlabType.TOP);
            }
            return level.setBlock(pos, state, 11)
                   ? ItemInteractionResult.sidedSuccess(level.isClientSide)
                   : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return state.getValue(TYPE) != SlabType.DOUBLE && state.getValue(AXIS) == Direction.Axis.Y;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(TYPE) != SlabType.DOUBLE && state.getValue(AXIS).isHorizontal() && state.getFluidState().isEmpty();
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        if (pathComputationType == PathComputationType.LAND) return state.getValue(AXIS) == Direction.Axis.Y && state.getValue(TYPE) == SlabType.BOTTOM;
        return super.isPathfindable(state, pathComputationType);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        return state.getValue(TYPE) != SlabType.DOUBLE && super.canPlaceLiquid(player, level, pos, state, fluid);
    }
}