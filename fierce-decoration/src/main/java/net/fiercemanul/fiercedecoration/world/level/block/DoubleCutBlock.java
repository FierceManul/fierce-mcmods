package net.fiercemanul.fiercedecoration.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class DoubleCutBlock extends ModelBlock {


    public static final MapCodec<DoubleCutBlock> CODEC = simpleCodec(DoubleCutBlock::new);

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty PART_A = FDBlockStateProperties.PART_A;
    public static final BooleanProperty PART_B = FDBlockStateProperties.PART_B;
    public static final BooleanProperty PART_C = FDBlockStateProperties.PART_C;
    public static final BooleanProperty PART_D = FDBlockStateProperties.PART_D;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final ImmutableMap<BlockState, VoxelShape> shapeMap;

    public DoubleCutBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                                          .setValue(AXIS, Direction.Axis.X)
                                          .setValue(PART_A, false)
                                          .setValue(PART_B, false)
                                          .setValue(PART_C, false)
                                          .setValue(PART_D, false)
                                          .setValue(WATERLOGGED, false));
        this.shapeMap = getShapeForEachState(DoubleCutBlock::buildShape);
    }

    @Override
    protected MapCodec<? extends DoubleCutBlock> codec() {
        return CODEC;
    }

    private static VoxelShape buildShape(BlockState state) {
        VoxelShape shape = Shapes.empty();
        switch (state.getValue(AXIS)) {
            case X -> {
                if (state.getValue(PART_A)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_NWD, TripleCutBlock.SHAPE_NED);
                if (state.getValue(PART_B)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_SWD, TripleCutBlock.SHAPE_SED);
                if (state.getValue(PART_C)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_NWU, TripleCutBlock.SHAPE_NEU);
                if (state.getValue(PART_D)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_SWU, TripleCutBlock.SHAPE_SEU);
            }
            case Y -> {
                if (state.getValue(PART_A)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_NWD, TripleCutBlock.SHAPE_NWU);
                if (state.getValue(PART_B)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_NED, TripleCutBlock.SHAPE_NEU);
                if (state.getValue(PART_C)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_SWD, TripleCutBlock.SHAPE_SWU);
                if (state.getValue(PART_D)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_SED, TripleCutBlock.SHAPE_SEU);
            }
            case Z -> {
                if (state.getValue(PART_A)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_NWD, TripleCutBlock.SHAPE_SWD);
                if (state.getValue(PART_B)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_NED, TripleCutBlock.SHAPE_SED);
                if (state.getValue(PART_C)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_NWU, TripleCutBlock.SHAPE_SWU);
                if (state.getValue(PART_D)) shape = Shapes.or(shape, TripleCutBlock.SHAPE_NEU, TripleCutBlock.SHAPE_SEU);
            }
        }
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS, PART_A, PART_B, PART_C, PART_D, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = shapeMap.get(pState);
        if (shape != null) return shape;
        return Shapes.block();
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction.Axis axis;
        BlockState blockState = pContext.getLevel().getBlockState(pContext.getClickedPos());

        if (blockState.is(this)) {
            axis = blockState.getValue(AXIS);
        }
        else {
            blockState = defaultBlockState();
            if (pContext.getPlayer() != null)
                axis = pContext.getPlayer().isShiftKeyDown() ? Direction.Axis.Y : pContext.getHorizontalDirection().getClockWise().getAxis();
            else axis = pContext.getHorizontalDirection().getAxis();
            blockState = blockState.setValue(AXIS, axis);
        }

        double a, b;
        switch (axis) {
            case X -> {
                a = pContext.getClickLocation().z - pContext.getClickedPos().getZ();
                b = pContext.getClickLocation().y - pContext.getClickedPos().getY();
                if (a == 0.5) a = pContext.getClickedFace().equals(Direction.SOUTH) ? 0.6 : 0.4;
                if (b == 0.5) b = pContext.getClickedFace().equals(Direction.UP) ? 0.6 : 0.4;
            }
            case Y -> {
                a = pContext.getClickLocation().x - pContext.getClickedPos().getX();
                b = pContext.getClickLocation().z - pContext.getClickedPos().getZ();
                if (a == 0.5) a = pContext.getClickedFace().equals(Direction.EAST) ? 0.6 : 0.4;
                if (b == 0.5) b = pContext.getClickedFace().equals(Direction.SOUTH) ? 0.6 : 0.4;
            }
            default -> {
                a = pContext.getClickLocation().x - pContext.getClickedPos().getX();
                b = pContext.getClickLocation().y - pContext.getClickedPos().getY();
                if (a == 0.5) a = pContext.getClickedFace().equals(Direction.EAST) ? 0.6 : 0.4;
                if (b == 0.5) b = pContext.getClickedFace().equals(Direction.UP) ? 0.6 : 0.4;
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

        return blockState.setValue(WATERLOGGED, pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER && !isShapeFullBlock(
                blockState.getShape(pContext.getLevel(), pContext.getClickedPos())));
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
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return switch (pRot) {
            case NONE -> pState;
            case CLOCKWISE_90 -> switch (pState.getValue(AXIS)) {
                case X -> pState.setValue(AXIS, Direction.Axis.Z)
                        .setValue(PART_A, pState.getValue(PART_B))
                        .setValue(PART_B, pState.getValue(PART_A))
                        .setValue(PART_C, pState.getValue(PART_D))
                        .setValue(PART_D, pState.getValue(PART_C));
                case Y -> pState
                        .setValue(PART_A, pState.getValue(PART_C))
                        .setValue(PART_B, pState.getValue(PART_A))
                        .setValue(PART_C, pState.getValue(PART_D))
                        .setValue(PART_D, pState.getValue(PART_B));
                case Z -> pState.setValue(AXIS, Direction.Axis.X);
            };
            case CLOCKWISE_180 -> pState
                    .setValue(PART_A, pState.getValue(PART_B))
                    .setValue(PART_B, pState.getValue(PART_A))
                    .setValue(PART_C, pState.getValue(PART_D))
                    .setValue(PART_D, pState.getValue(PART_C));
            case COUNTERCLOCKWISE_90 -> switch (pState.getValue(AXIS)) {
                case X -> pState.setValue(AXIS, Direction.Axis.Z);
                case Y -> pState
                        .setValue(PART_A, pState.getValue(PART_B))
                        .setValue(PART_B, pState.getValue(PART_D))
                        .setValue(PART_C, pState.getValue(PART_A))
                        .setValue(PART_D, pState.getValue(PART_C));
                case Z -> pState.setValue(AXIS, Direction.Axis.X)
                        .setValue(PART_A, pState.getValue(PART_B))
                        .setValue(PART_B, pState.getValue(PART_A))
                        .setValue(PART_C, pState.getValue(PART_D))
                        .setValue(PART_D, pState.getValue(PART_C));
            };
        };
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return switch (pMirror) {
            case NONE -> pState;
            case LEFT_RIGHT -> switch (pState.getValue(AXIS)) {
                case Y, Z -> pState
                        .setValue(PART_A, pState.getValue(PART_B))
                        .setValue(PART_B, pState.getValue(PART_A))
                        .setValue(PART_C, pState.getValue(PART_D))
                        .setValue(PART_D, pState.getValue(PART_C));
                default -> pState;
            };
            case FRONT_BACK -> switch (pState.getValue(AXIS)) {
                case X -> pState
                        .setValue(PART_A, pState.getValue(PART_B))
                        .setValue(PART_B, pState.getValue(PART_A))
                        .setValue(PART_C, pState.getValue(PART_D))
                        .setValue(PART_D, pState.getValue(PART_C));
                case Y -> pState
                        .setValue(PART_A, pState.getValue(PART_C))
                        .setValue(PART_B, pState.getValue(PART_D))
                        .setValue(PART_C, pState.getValue(PART_A))
                        .setValue(PART_D, pState.getValue(PART_B));
                default -> pState;
            };
        };
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTootipComponents, TooltipFlag pTooltipFlag) {
        pTootipComponents.add(Component.translatable("fiercedecoration.tip.snake_vertical").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(pStack, pContext, pTootipComponents, pTooltipFlag);
    }

    @Override
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return false;
    }
}
