package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;



public abstract class GuardrailBlock extends HorizonFacingModelBlock {


    public static final BooleanProperty CORNER = BooleanProperty.create("corner");
    public static final BooleanProperty LOWER = BooleanProperty.create("lower");

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 1.0D, 16.0D, 16.0D, 4.0D);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.east();
    private static final VoxelShapeHelper LOWER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, -8.0D, 1.0D, 16.0D, 8.0D, 4.0D);
    protected static final VoxelShape SHAPE_NORTH_LOWER = LOWER_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_LOWER = LOWER_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_LOWER = LOWER_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_LOWER = LOWER_SHAPE_HELPER.east();
    private static final VoxelShapeHelper CORNER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 1.0D, 15.0D, 16.0D, 4.0D)
            .applyCube(12.0D, 0.0D, 4.0D, 15.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH_CORNER = CORNER_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_CORNER = CORNER_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_CORNER = CORNER_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_CORNER = CORNER_SHAPE_HELPER.east();
    private static final VoxelShapeHelper LOWER_CORNER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, -8.0D, 1.0D, 15.0D, 8.0D, 4.0D)
            .applyCube(12.0D, -8.0D, 4.0D, 15.0D, 8.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.east();

    public GuardrailBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(CORNER, false)
                .setValue(LOWER, false)
                .setValue(WATERLOGGED, false));
    }

    protected abstract MapCodec<? extends GuardrailBlock> codec();

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, CORNER, LOWER, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        BlockState belowBlockState = context.getLevel().getBlockState(context.getClickedPos().below());
        BlockState targetState;
        if (blockState.is(this)) {
            targetState = blockState;
            if (context.getHorizontalDirection().getClockWise().equals(blockState.getValue(FACING))) targetState = targetState.setValue(FACING, context.getHorizontalDirection()).setValue(CORNER, true);
            if (context.getHorizontalDirection().getCounterClockWise().equals(blockState.getValue(FACING))) targetState = targetState.setValue(CORNER, true);
        } else {
            targetState = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
        }
        if ((belowBlockState.getBlock() instanceof SlabBlock && belowBlockState.getValue(BlockStateProperties.SLAB_TYPE).equals(SlabType.BOTTOM))
                || (belowBlockState.getBlock() instanceof GuardrailBlock && belowBlockState.getValue(LOWER))) {
            targetState = targetState.setValue(LOWER, true);
        }
        return targetState.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return pUseContext.getItemInHand().is(this.asItem())
                && !pState.getValue(CORNER)
                && pUseContext.getHorizontalDirection().getClockWise().getAxis().equals(pState.getValue(FACING).getAxis());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(CORNER)) {
            if (pState.getValue(LOWER)) {
                return switch (pState.getValue(FACING)) {
                    case NORTH -> SHAPE_NORTH_CORNER_LOWER;
                    case SOUTH -> SHAPE_SOUTH_CORNER_LOWER;
                    case WEST -> SHAPE_WEST_CORNER_LOWER;
                    case EAST -> SHAPE_EAST_CORNER_LOWER;
                    default -> Shapes.block();
                };
            }
            else {
                return switch (pState.getValue(FACING)) {
                    case NORTH -> SHAPE_NORTH_CORNER;
                    case SOUTH -> SHAPE_SOUTH_CORNER;
                    case WEST -> SHAPE_WEST_CORNER;
                    case EAST -> SHAPE_EAST_CORNER;
                    default -> Shapes.block();
                };
            }
        }
        else {
            if (pState.getValue(LOWER)) {
                return switch (pState.getValue(FACING)) {
                    case NORTH -> SHAPE_NORTH_LOWER;
                    case SOUTH -> SHAPE_SOUTH_LOWER;
                    case WEST -> SHAPE_WEST_LOWER;
                    case EAST -> SHAPE_EAST_LOWER;
                    default -> Shapes.block();
                };
            }
            else {
                return switch (pState.getValue(FACING)) {
                    case NORTH -> SHAPE_NORTH;
                    case SOUTH -> SHAPE_SOUTH;
                    case WEST -> SHAPE_WEST;
                    case EAST -> SHAPE_EAST;
                    default -> Shapes.block();
                };
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.equals(Direction.DOWN)) {
            if (neighborState.getBlock() instanceof SlabBlock) return state.setValue(LOWER, neighborState.getValue(SlabBlock.TYPE).equals(SlabType.BOTTOM));
            else return state.setValue(LOWER, false);
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return switch (pMirror) {
            case NONE -> pState;
            case LEFT_RIGHT -> {
                if (pState.getValue(CORNER)) {
                    yield switch (pState.getValue(FACING)) {
                        case SOUTH -> pState.setValue(FACING, Direction.EAST);
                        case WEST -> pState.setValue(FACING, Direction.NORTH);
                        case EAST -> pState.setValue(FACING, Direction.SOUTH);
                        default -> pState.setValue(FACING, Direction.WEST);
                    };
                }
                else {
                    yield switch (pState.getValue(FACING)) {
                        case WEST -> pState.setValue(FACING, Direction.EAST);
                        case EAST -> pState.setValue(FACING, Direction.WEST);
                        default -> pState;
                    };
                }
            }
            case FRONT_BACK -> {
                if (pState.getValue(CORNER)) {
                    yield switch (pState.getValue(FACING)) {
                        case SOUTH -> pState.setValue(FACING, Direction.WEST);
                        case WEST -> pState.setValue(FACING, Direction.SOUTH);
                        case EAST -> pState.setValue(FACING, Direction.NORTH);
                        default -> pState.setValue(FACING, Direction.EAST);
                    };
                }
                else {
                    yield switch (pState.getValue(FACING)) {
                        case NORTH -> pState.setValue(FACING, Direction.NORTH);
                        case SOUTH -> pState.setValue(FACING, Direction.SOUTH);
                        default -> pState;
                    };
                }
            }
        };
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("fiercedecoration.tip.guardrail"));
    }

    protected boolean defaultSkipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pDirection) {
        Direction selfDirection = pState.getValue(FACING);
        Direction otherDirection = pAdjacentBlockState.getValue(FACING);
        if (selfDirection.getCounterClockWise().equals(pDirection)) {
            return (
                    (selfDirection.equals(otherDirection) && !pAdjacentBlockState.getValue(CORNER))
                            || (pAdjacentBlockState.getValue(CORNER) && selfDirection.equals(otherDirection.getClockWise()))
            )
                    && pState.getValue(LOWER).equals(pAdjacentBlockState.getValue(LOWER));
        }
        else if (selfDirection.getClockWise().equals(pDirection)) {
            return selfDirection.equals(otherDirection) && pState.getValue(LOWER).equals(pAdjacentBlockState.getValue(LOWER));
        }
        else if (selfDirection.getOpposite().equals(pDirection)) {
            return selfDirection.equals(otherDirection.getCounterClockWise()) && pState.getValue(LOWER).equals(pAdjacentBlockState.getValue(LOWER));
        }
        else if (pDirection.equals(Direction.DOWN)) {
            return (selfDirection.equals(otherDirection) && pState.getValue(CORNER).equals(pAdjacentBlockState.getValue(CORNER)))
                    || (selfDirection.equals(otherDirection.getClockWise()) && pAdjacentBlockState.getValue(CORNER) && !pState.getValue(CORNER));
        }
        return super.skipRendering(pState, pAdjacentBlockState, pDirection);
    }

    @Override
    public boolean isPathfindable(BlockState pState, PathComputationType pType) {
        return false;
    }
}
