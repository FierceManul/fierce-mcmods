package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;



public class WoodenGuardrailTypeBBlock extends GuardrailBlock {


    public static final MapCodec<GuardrailBlock> CODEC = simpleCodec(WoodenGuardrailTypeBBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 1.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.east();
    private static final VoxelShapeHelper LOWER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, -8.0D, 1.0D, 16.0D, 8.0D, 3.0D);
    protected static final VoxelShape SHAPE_NORTH_LOWER = LOWER_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_LOWER = LOWER_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_LOWER = LOWER_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_LOWER = LOWER_SHAPE_HELPER.east();
    private static final VoxelShapeHelper CORNER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 1.0D, 15.0D, 16.0D, 3.0D)
            .applyCube(12.0D, 0.0D, 1.0D, 15.0D, 16.0D, 4.0D)
            .applyCube(13.0D, 0.0D, 4.0D, 15.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH_CORNER = CORNER_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_CORNER = CORNER_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_CORNER = CORNER_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_CORNER = CORNER_SHAPE_HELPER.east();
    private static final VoxelShapeHelper LOWER_CORNER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, -8.0D, 1.0D, 15.0D, 8.0D, 3.0D)
            .applyCube(12.0D, -8.0D, 1.0D, 15.0D, 8.0D, 4.0D)
            .applyCube(13.0D, -8.0D, 4.0D, 15.0D, 8.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.east();

    public WoodenGuardrailTypeBBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
    }

    protected MapCodec<? extends GuardrailBlock> codec() {
        return CODEC;
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
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pDirection) {
        if (pAdjacentBlockState.getBlock() instanceof WoodenGuardrailTypeBBlock) {
            Direction selfDirection = pState.getValue(FACING);
            Direction otherDirection = pAdjacentBlockState.getValue(FACING);
            if (selfDirection.getCounterClockWise().equals(pDirection)) {
                return ((selfDirection.equals(otherDirection) && !pAdjacentBlockState.getValue(CORNER))
                        || (pAdjacentBlockState.getValue(CORNER) && selfDirection.equals(otherDirection.getClockWise())))
                        && pState.getValue(LOWER).equals(pAdjacentBlockState.getValue(LOWER));
            }
            else if (selfDirection.getClockWise().equals(pDirection)) {
                return selfDirection.equals(otherDirection) && pState.getValue(LOWER).equals(pAdjacentBlockState.getValue(LOWER));
            }
            else if (selfDirection.getOpposite().equals(pDirection)) {
                return selfDirection.equals(otherDirection.getCounterClockWise()) && pState.getValue(LOWER).equals(pAdjacentBlockState.getValue(LOWER));
            }
            else if (pDirection.getAxis().equals(Direction.Axis.Y)) {
                return selfDirection.equals(otherDirection)
                        && pState.getValue(CORNER).equals(pAdjacentBlockState.getValue(CORNER))
                        && pState.getValue(LOWER).equals(pAdjacentBlockState.getValue(LOWER));
            }
            return super.skipRendering(pState, pAdjacentBlockState, pDirection);
        }
        return super.skipRendering(pState, pAdjacentBlockState, pDirection);
    }
}
