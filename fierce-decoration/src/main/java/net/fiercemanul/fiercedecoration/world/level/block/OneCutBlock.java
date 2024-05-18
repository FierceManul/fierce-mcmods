package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.level.block.state.properties.FDBlockStateProperties;
import net.fiercemanul.fiercesource.world.level.block.FacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class OneCutBlock extends FacingModelBlock {


    public static final MapCodec<OneCutBlock> CODEC = simpleCodec(OneCutBlock::new);

    public static final BooleanProperty DOUBLE = FDBlockStateProperties.DOUBLE;
    public static final VoxelShape SHAPE_NORTH = box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    public static final VoxelShape SHAPE_SOUTH = box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SHAPE_WEST = box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    public static final VoxelShape SHAPE_EAST = box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SHAPE_DOWN = box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    public static final VoxelShape SHAPE_UP = box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public OneCutBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                                                      .setValue(FACING, Direction.NORTH)
                                                      .setValue(DOUBLE, false)
                                                      .setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends OneCutBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, DOUBLE, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockState = pContext.getLevel().getBlockState(pContext.getClickedPos());
        if (blockState.is(this)) return blockState.setValue(DOUBLE, true).setValue(WATERLOGGED, false);
        else {
            blockState = defaultBlockState().setValue(WATERLOGGED, pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER);
            return blockState.setValue(FACING, getPlaceDirection(pContext));
        }
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return pUseContext.getItemInHand().is(this.asItem())
                && !pState.getValue(DOUBLE)
                && getPlaceDirection(pUseContext).equals(pState.getValue(FACING).getOpposite());
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
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(DOUBLE)) return Shapes.block();
        return switch (pState.getValue(FACING)) {
            case DOWN -> SHAPE_DOWN;
            case UP -> SHAPE_UP;
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
        };
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("fiercedecoration.tip.snake_horizontal"));
    }
}
