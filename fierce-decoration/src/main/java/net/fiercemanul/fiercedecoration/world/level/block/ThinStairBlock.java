package net.fiercemanul.fiercedecoration.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class ThinStairBlock extends HorizonFacingModelBlock {


    public static final MapCodec<ThinStairBlock> CODEC = simpleCodec(ThinStairBlock::new);

    public static final EnumProperty<ThinStairsShape> SHAPE = EnumProperty.create("shape", ThinStairsShape.class);
    private final ImmutableMap<BlockState, VoxelShape> shapeMap;


    public ThinStairBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
                                                      .setValue(SHAPE, ThinStairsShape.STRAIGHT).setValue(WATERLOGGED, false));
        this.shapeMap = getShapeForEachState(ThinStairBlock::buildShape);
    }

    @Override
    protected MapCodec<? extends ThinStairBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, SHAPE, WATERLOGGED);
    }

    private static VoxelShape buildShape(BlockState state) {
        return switch (state.getValue(FACING)) {
            case NORTH -> {
                VoxelShape shape = Shapes.or(TripleCutBlock.SHAPE_NED, TripleCutBlock.SHAPE_SEU);
                yield switch (state.getValue(SHAPE)) {
                    case STRAIGHT -> Shapes.or(shape, TripleCutBlock.SHAPE_NWD, TripleCutBlock.SHAPE_SWU);
                    case INNER -> Shapes.or(shape, TripleCutBlock.SHAPE_NWU, TripleCutBlock.SHAPE_SWU);
                    case OUTER -> Shapes.or(shape, TripleCutBlock.SHAPE_NWD, TripleCutBlock.SHAPE_SWD);
                };
            }
            case SOUTH -> {
                VoxelShape shape = Shapes.or(TripleCutBlock.SHAPE_NWU, TripleCutBlock.SHAPE_SWD);
                yield switch (state.getValue(SHAPE)) {
                    case STRAIGHT -> Shapes.or(shape, TripleCutBlock.SHAPE_NEU, TripleCutBlock.SHAPE_SED);
                    case INNER -> Shapes.or(shape, TripleCutBlock.SHAPE_NEU, TripleCutBlock.SHAPE_SEU);
                    case OUTER -> Shapes.or(shape, TripleCutBlock.SHAPE_NED, TripleCutBlock.SHAPE_SED);
                };
            }
            case WEST -> {
                VoxelShape shape = Shapes.or(TripleCutBlock.SHAPE_NWD, TripleCutBlock.SHAPE_NEU);
                yield switch (state.getValue(SHAPE)) {
                    case STRAIGHT -> Shapes.or(shape, TripleCutBlock.SHAPE_SWD, TripleCutBlock.SHAPE_SEU);
                    case INNER -> Shapes.or(shape, TripleCutBlock.SHAPE_SWU, TripleCutBlock.SHAPE_SEU);
                    case OUTER -> Shapes.or(shape, TripleCutBlock.SHAPE_SWD, TripleCutBlock.SHAPE_SED);
                };
            }
            case EAST -> {
                VoxelShape shape = Shapes.or(TripleCutBlock.SHAPE_SWU, TripleCutBlock.SHAPE_SED);
                yield switch (state.getValue(SHAPE)) {
                    case STRAIGHT -> Shapes.or(shape, TripleCutBlock.SHAPE_NWU, TripleCutBlock.SHAPE_NED);
                    case INNER -> Shapes.or(shape, TripleCutBlock.SHAPE_NWU, TripleCutBlock.SHAPE_NEU);
                    case OUTER -> Shapes.or(shape, TripleCutBlock.SHAPE_NWD, TripleCutBlock.SHAPE_NED);
                };
            }
            default -> Shapes.block();
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        BlockPos blockpos = placeContext.getClickedPos();
        BlockState blockstate = defaultBlockState().setValue(WATERLOGGED, placeContext.getLevel().getFluidState(blockpos).getType() == Fluids.WATER);

        Direction direction;
        double y = placeContext.getClickLocation().y - blockpos.getY();
        if (y < 0.5D || (placeContext.getClickedFace().equals(Direction.UP) && y == 0.5D)) {
            direction = placeContext.getHorizontalDirection().getOpposite();
        }
        else direction = placeContext.getHorizontalDirection();
        blockstate = blockstate.setValue(FACING, direction);

        BlockState other = placeContext.getLevel().getBlockState(blockpos.relative(direction));
        boolean flag = true;
        if (other.getBlock() instanceof ThinStairBlock) {
            Direction otherDirection = other.getValue(FACING);
            ThinStairsShape otherShape = other.getValue(SHAPE);
            if (otherDirection.equals(direction.getClockWise())) {
                blockstate = blockstate.setValue(SHAPE, ThinStairsShape.INNER);
                flag = false;
            }
            else if ((otherDirection.equals(direction.getOpposite()) && otherShape.equals(ThinStairsShape.INNER))
                    || (otherDirection.equals(direction.getCounterClockWise()) && otherShape.equals(ThinStairsShape.STRAIGHT))
                    || (otherDirection.equals(direction) && otherShape.equals(ThinStairsShape.OUTER))) {
                blockstate = blockstate.setValue(SHAPE, ThinStairsShape.INNER).setValue(FACING, direction.getCounterClockWise());
                flag = false;
            }
        }
        if (flag) {
            other = placeContext.getLevel().getBlockState(blockpos.relative(direction.getOpposite()));
            if (other.getBlock() instanceof ThinStairBlock) {
                Direction otherDirection = other.getValue(FACING);
                ThinStairsShape otherShape = other.getValue(SHAPE);
                if (otherDirection.equals(direction.getCounterClockWise())) {
                    blockstate = blockstate.setValue(SHAPE, ThinStairsShape.OUTER);
                }
                else if ((otherDirection.equals(direction) && otherShape.equals(ThinStairsShape.INNER))
                        || (otherDirection.equals(direction.getClockWise()) && otherShape.equals(ThinStairsShape.STRAIGHT))
                        || (otherDirection.equals(direction.getOpposite()) && otherShape.equals(ThinStairsShape.OUTER))) {
                    blockstate = blockstate.setValue(SHAPE, ThinStairsShape.OUTER).setValue(FACING, direction.getClockWise());
                }
            }
        }

        return blockstate;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {

        Direction myDirection = pState.getValue(FACING);

        if (pState.getValue(SHAPE).equals(ThinStairsShape.STRAIGHT) && pNeighborState.getBlock() instanceof ThinStairBlock) {

            Direction otherDirection = pNeighborState.getValue(FACING);
            ThinStairsShape otherShape = pNeighborState.getValue(SHAPE);

            if (pDirection.equals(myDirection)) {
                if (otherDirection.equals(myDirection.getClockWise())) {
                    return pState.setValue(SHAPE, ThinStairsShape.INNER);
                }
                else if ((otherDirection.equals(myDirection.getOpposite()) && otherShape.equals(ThinStairsShape.INNER))
                        || (otherDirection.equals(myDirection.getCounterClockWise()) && otherShape.equals(ThinStairsShape.STRAIGHT))
                        || (otherDirection.equals(myDirection) && otherShape.equals(ThinStairsShape.OUTER))) {
                    return pState.setValue(SHAPE, ThinStairsShape.INNER).setValue(FACING, myDirection.getCounterClockWise());
                }
            }
            else if (pDirection.equals(myDirection.getOpposite())) {
                if (otherDirection.equals(myDirection.getCounterClockWise())) {
                    return pState.setValue(SHAPE, ThinStairsShape.OUTER);
                }
                else if ((otherDirection.equals(myDirection) && otherShape.equals(ThinStairsShape.INNER))
                        || (otherDirection.equals(myDirection.getClockWise()) && otherShape.equals(ThinStairsShape.STRAIGHT))
                        || (otherDirection.equals(myDirection.getOpposite()) && otherShape.equals(ThinStairsShape.OUTER))) {
                    return pState.setValue(SHAPE, ThinStairsShape.OUTER).setValue(FACING, myDirection.getClockWise());
                }
            }
        }

        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = shapeMap.get(pState);
        if (shape != null) return shape;
        return Shapes.block();
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case NONE -> state;
            case LEFT_RIGHT -> switch (state.getValue(SHAPE)) {
                case STRAIGHT -> switch (state.getValue(FACING)) {
                    case WEST -> state.setValue(FACING, Direction.EAST);
                    case EAST -> state.setValue(FACING, Direction.WEST);
                    default -> state;
                };
                case INNER -> switch (state.getValue(FACING)) {
                    case NORTH -> state.setValue(FACING, Direction.WEST);
                    case SOUTH -> state.setValue(FACING, Direction.EAST);
                    case WEST -> state.setValue(FACING, Direction.NORTH);
                    case EAST -> state.setValue(FACING, Direction.SOUTH);
                    default -> state;
                };
                case OUTER -> switch (state.getValue(FACING)) {
                    case NORTH -> state.setValue(FACING, Direction.EAST);
                    case SOUTH -> state.setValue(FACING, Direction.WEST);
                    case WEST -> state.setValue(FACING, Direction.SOUTH);
                    case EAST -> state.setValue(FACING, Direction.NORTH);
                    default -> state;
                };
            };
            case FRONT_BACK -> switch (state.getValue(SHAPE)) {
                case STRAIGHT -> switch (state.getValue(FACING)) {
                    case NORTH -> state.setValue(FACING, Direction.SOUTH);
                    case SOUTH -> state.setValue(FACING, Direction.NORTH);
                    default -> state;
                };
                case INNER -> switch (state.getValue(FACING)) {
                    case NORTH -> state.setValue(FACING, Direction.EAST);
                    case SOUTH -> state.setValue(FACING, Direction.WEST);
                    case WEST -> state.setValue(FACING, Direction.SOUTH);
                    case EAST -> state.setValue(FACING, Direction.NORTH);
                    default -> state;
                };
                case OUTER -> switch (state.getValue(FACING)) {
                    case NORTH -> state.setValue(FACING, Direction.WEST);
                    case SOUTH -> state.setValue(FACING, Direction.EAST);
                    case WEST -> state.setValue(FACING, Direction.NORTH);
                    case EAST -> state.setValue(FACING, Direction.SOUTH);
                    default -> state;
                };
            };
        };
    }

    public enum ThinStairsShape implements StringRepresentable {

        STRAIGHT("straight"),
        INNER("inner"),
        OUTER("outer");

        private final String name;

        ThinStairsShape(String pName) {
            this.name = pName;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }

    }

}
