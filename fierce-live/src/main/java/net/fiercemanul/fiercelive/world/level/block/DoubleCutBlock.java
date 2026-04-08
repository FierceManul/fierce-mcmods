package net.fiercemanul.fiercelive.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercelive.world.level.block.state.properties.FLBlockStateProperties;
import net.fiercemanul.fiercesource.world.level.block.BlockUtils;
import net.fiercemanul.fiercesource.world.level.block.WaterloggedBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class DoubleCutBlock extends WaterloggedBlock {


    public static final MapCodec<DoubleCutBlock> CODEC = simpleCodec(DoubleCutBlock::new);

    private static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    private static final BooleanProperty PART_A = FLBlockStateProperties.PART_A;
    private static final BooleanProperty PART_B = FLBlockStateProperties.PART_B;
    private static final BooleanProperty PART_C = FLBlockStateProperties.PART_C;
    private static final BooleanProperty PART_D = FLBlockStateProperties.PART_D;
    private static final VoxelShape[] SHAPES = buildShape();
    private final ImmutableMap<BlockState, ImmutableMap<Rotation, BlockState>> rotateMap;
    private final ImmutableMap<BlockState, ImmutableMap<Mirror, BlockState>> mirrorMap;

    private static VoxelShape[] buildShape() {
        VoxelShape[] shapes = new VoxelShape[48];
        for (Direction.Axis axis : Direction.Axis.values()) for (int i = 0; i < 0b10000; i++) {
            VoxelShape shape = Shapes.empty();
            switch (axis) {
                case X -> {
                    if (i == 0b1111) shape = Shapes.block();
                    else {
                        if ((i & 0b1) != 0) shape = Shapes.or(shape, box(0, 0, 0, 16, 8, 8));
                        if ((i & 0b10) != 0) shape = Shapes.or(shape, box(0, 0, 8, 16, 8, 16));
                        if ((i & 0b100) != 0) shape = Shapes.or(shape, box(0, 8, 0, 16, 16, 8));
                        if ((i & 0b1000) != 0) shape = Shapes.or(shape, box(0, 8, 8, 16, 16, 16));
                    }
                }
                case Y -> {
                    if (i == 0b1111) shape = Shapes.block();
                    else {
                        if ((i & 0b1) != 0) shape = Shapes.or(shape, box(0, 0, 0, 8, 16, 8));
                        if ((i & 0b10) != 0) shape = Shapes.or(shape, box(8, 0, 0, 16, 16, 8));
                        if ((i & 0b100) != 0) shape = Shapes.or(shape, box(0, 0, 8, 8, 16, 16));
                        if ((i & 0b1000) != 0) shape = Shapes.or(shape, box(8, 0, 0, 16, 16, 16));
                    }
                }
                case Z -> {
                    if (i == 0b1111) shape = Shapes.block();
                    else {
                        if ((i & 0b1) != 0) shape = Shapes.or(shape, box(0, 0, 0, 8, 8, 16));
                        if ((i & 0b10) != 0) shape = Shapes.or(shape, box(8, 0, 0, 16, 8, 16));
                        if ((i & 0b100) != 0) shape = Shapes.or(shape, box(0, 8, 0, 8, 16, 16));
                        if ((i & 0b1000) != 0) shape = Shapes.or(shape, box(8, 8, 0, 16, 16, 16));
                    }
                }
            }
            shapes[axis.ordinal() << 4 | i] = shape;
        }
        return shapes;
    }

    private static int getShapeIndex(BlockState state) {
        int value = 0;
        if (state.getValue(PART_A)) value |= 0b1;
        if (state.getValue(PART_B)) value |= 0b10;
        if (state.getValue(PART_C)) value |= 0b100;
        if (state.getValue(PART_D)) value |= 0b1000;
        value |= state.getValue(AXIS).ordinal() << 4;
        return value;
    }

    public DoubleCutBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                                          .setValue(AXIS, Direction.Axis.X)
                                          .setValue(PART_A, false)
                                          .setValue(PART_B, false)
                                          .setValue(PART_C, false)
                                          .setValue(PART_D, false)
                                          .setValue(WATERLOGGED, false));
        this.rotateMap = BlockUtils.forEachStatesWith(this, Rotation.class, DoubleCutBlock::getRotatedState);
        this.mirrorMap = BlockUtils.forEachStatesWith(this, Mirror.class, DoubleCutBlock::getMirroredState);
    }

    @Override
    protected MapCodec<? extends DoubleCutBlock> codec() {
        return CODEC;
    }

    private static BlockState getRotatedState(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case NONE -> state;
            case CLOCKWISE_90 -> switch (state.getValue(AXIS)) {
                case X -> state.setValue(AXIS, Direction.Axis.Z)
                               .setValue(PART_A, state.getValue(PART_B))
                               .setValue(PART_B, state.getValue(PART_A))
                               .setValue(PART_C, state.getValue(PART_D))
                               .setValue(PART_D, state.getValue(PART_C));
                case Y -> state
                        .setValue(PART_A, state.getValue(PART_C))
                        .setValue(PART_B, state.getValue(PART_A))
                        .setValue(PART_C, state.getValue(PART_D))
                        .setValue(PART_D, state.getValue(PART_B));
                case Z -> state.setValue(AXIS, Direction.Axis.X);
            };
            case CLOCKWISE_180 -> state
                    .setValue(PART_A, state.getValue(PART_B))
                    .setValue(PART_B, state.getValue(PART_A))
                    .setValue(PART_C, state.getValue(PART_D))
                    .setValue(PART_D, state.getValue(PART_C));
            case COUNTERCLOCKWISE_90 -> switch (state.getValue(AXIS)) {
                case X -> state.setValue(AXIS, Direction.Axis.Z);
                case Y -> state
                        .setValue(PART_A, state.getValue(PART_B))
                        .setValue(PART_B, state.getValue(PART_D))
                        .setValue(PART_C, state.getValue(PART_A))
                        .setValue(PART_D, state.getValue(PART_C));
                case Z -> state.setValue(AXIS, Direction.Axis.X)
                               .setValue(PART_A, state.getValue(PART_B))
                               .setValue(PART_B, state.getValue(PART_A))
                               .setValue(PART_C, state.getValue(PART_D))
                               .setValue(PART_D, state.getValue(PART_C));
            };
        };
    }

    private static BlockState getMirroredState(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case NONE -> state;
            case LEFT_RIGHT -> switch (state.getValue(AXIS)) {
                case Y, Z -> state
                        .setValue(PART_A, state.getValue(PART_B))
                        .setValue(PART_B, state.getValue(PART_A))
                        .setValue(PART_C, state.getValue(PART_D))
                        .setValue(PART_D, state.getValue(PART_C));
                default -> state;
            };
            case FRONT_BACK -> switch (state.getValue(AXIS)) {
                case X -> state
                        .setValue(PART_A, state.getValue(PART_B))
                        .setValue(PART_B, state.getValue(PART_A))
                        .setValue(PART_C, state.getValue(PART_D))
                        .setValue(PART_D, state.getValue(PART_C));
                case Y -> state
                        .setValue(PART_A, state.getValue(PART_C))
                        .setValue(PART_B, state.getValue(PART_D))
                        .setValue(PART_C, state.getValue(PART_A))
                        .setValue(PART_D, state.getValue(PART_B));
                default -> state;
            };
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, PART_A, PART_B, PART_C, PART_D, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[getShapeIndex(state)];
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction.Axis axis;
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());

        if (blockState.is(this)) {
            axis = blockState.getValue(AXIS);
        }
        else {
            blockState = defaultBlockState();
            if (context.getPlayer() != null)
                axis = context.getPlayer().isShiftKeyDown() ? Direction.Axis.Y : context.getHorizontalDirection().getClockWise().getAxis();
            else axis = context.getHorizontalDirection().getAxis();
            blockState = blockState.setValue(AXIS, axis);
        }

        double a, b;
        switch (axis) {
            case X -> {
                a = context.getClickLocation().z - context.getClickedPos().getZ();
                b = context.getClickLocation().y - context.getClickedPos().getY();
                if (a == 0.5) a = context.getClickedFace().equals(Direction.SOUTH) ? 0.6 : 0.4;
                if (b == 0.5) b = context.getClickedFace().equals(Direction.UP) ? 0.6 : 0.4;
            }
            case Y -> {
                a = context.getClickLocation().x - context.getClickedPos().getX();
                b = context.getClickLocation().z - context.getClickedPos().getZ();
                if (a == 0.5) a = context.getClickedFace().equals(Direction.EAST) ? 0.6 : 0.4;
                if (b == 0.5) b = context.getClickedFace().equals(Direction.SOUTH) ? 0.6 : 0.4;
            }
            default -> {
                a = context.getClickLocation().x - context.getClickedPos().getX();
                b = context.getClickLocation().y - context.getClickedPos().getY();
                if (a == 0.5) a = context.getClickedFace().equals(Direction.EAST) ? 0.6 : 0.4;
                if (b == 0.5) b = context.getClickedFace().equals(Direction.UP) ? 0.6 : 0.4;
            }
        }

        if (a > 0.5) {
            if (b > 0.5) blockState = blockState.setValue(PART_D, true);
            if (b < 0.5) blockState = blockState.setValue(PART_B, true);
        }
        else if (a < 0.5) {
            if (b > 0.5) blockState = blockState.setValue(PART_C, true);
            if (b < 0.5) blockState = blockState.setValue(PART_A, true);
        }

        return blockState.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER && !isShapeFullBlock(
                blockState.getShape(context.getLevel(), context.getClickedPos())));
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        if (!pUseContext.getItemInHand().is(this.asItem())) return false;
        Direction.Axis clickAxis;
        if (pUseContext.getPlayer() != null && pUseContext.getPlayer().isShiftKeyDown()) clickAxis = Direction.Axis.Y;
        else clickAxis = pUseContext.getHorizontalDirection().getClockWise().getAxis();
        if (pState.getValue(AXIS) != clickAxis) return false;

        double a, b;
        switch (pState.getValue(AXIS)) {
            case X -> {
                a = pUseContext.getClickLocation().z - pUseContext.getClickedPos().getZ();
                b = pUseContext.getClickLocation().y - pUseContext.getClickedPos().getY();
                if (a == 0.5) a = pUseContext.getClickedFace().equals(Direction.SOUTH) ? 0.6 : 0.4;
                if (b == 0.5) b = pUseContext.getClickedFace().equals(Direction.UP) ? 0.6 : 0.4;
            }
            case Y -> {
                a = pUseContext.getClickLocation().x - pUseContext.getClickedPos().getX();
                b = pUseContext.getClickLocation().z - pUseContext.getClickedPos().getZ();
                if (a == 0.5) a = pUseContext.getClickedFace().equals(Direction.EAST) ? 0.6 : 0.4;
                if (b == 0.5) b = pUseContext.getClickedFace().equals(Direction.SOUTH) ? 0.6 : 0.4;
            }
            default -> {
                a = pUseContext.getClickLocation().x - pUseContext.getClickedPos().getX();
                b = pUseContext.getClickLocation().y - pUseContext.getClickedPos().getY();
                if (a == 0.5) a = pUseContext.getClickedFace().equals(Direction.EAST) ? 0.6 : 0.4;
                if (b == 0.5) b = pUseContext.getClickedFace().equals(Direction.UP) ? 0.6 : 0.4;
            }
        }

        if (a > 0.5) {
            if (b > 0.5) return !pState.getValue(PART_D);
            else return !pState.getValue(PART_B);
        }
        else {
            if (b > 0.5) return !pState.getValue(PART_C);
            else return !pState.getValue(PART_A);
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        var map = rotateMap.get(state);
        if (map != null) {
            var sta = map.get(rotation);
            if (sta != null) return sta;
        }
        return state;
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        var map = mirrorMap.get(state);
        if (map != null) {
            var sta = map.get(mirror);
            if (sta != null) return sta;
        }
        return state;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("fiercelive.tip.snake_vertical").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        if (pathComputationType == PathComputationType.LAND) return state.getValue(AXIS).isHorizontal() && ((state.getValue(PART_A)) || (state.getValue(PART_B)));
        return false;
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        return !isShapeFullBlock(state.getShape(level, pos)) && super.canPlaceLiquid(player, level, pos, state, fluid);
    }
}
