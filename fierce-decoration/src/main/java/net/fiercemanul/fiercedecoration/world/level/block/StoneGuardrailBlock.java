package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;



public class StoneGuardrailBlock extends GuardrailBlock {


    public static final MapCodec<GuardrailBlock> CODEC = simpleCodec(StoneGuardrailBlock::new);

    private static final VoxelShapeHelper SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 6.0D);
    protected static final VoxelShape SHAPE_NORTH = SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE_HELPER.east();
    private static final VoxelShapeHelper OCCLUSION_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 6.0D)
            .applyCube(1.5D, 0.0D, 0.5D, 6.5D, 3.0D, 5.5D)
            .applyCube(9.5D, 0.0D, 0.5D, 14.5, 3.0D, 5.5D);
    protected static final VoxelShape OCCLUSION_SHAPE_NORTH = OCCLUSION_SHAPE_HELPER.north();
    protected static final VoxelShape OCCLUSION_SHAPE_SOUTH = OCCLUSION_SHAPE_HELPER.south();
    protected static final VoxelShape OCCLUSION_SHAPE_WEST = OCCLUSION_SHAPE_HELPER.west();
    protected static final VoxelShape OCCLUSION_SHAPE_EAST = OCCLUSION_SHAPE_HELPER.east();
    private static final VoxelShapeHelper LOWER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, -8.0D, 0.0D, 16.0D, 8.0D, 6.0D);
    protected static final VoxelShape SHAPE_NORTH_LOWER = LOWER_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_LOWER = LOWER_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_LOWER = LOWER_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_LOWER = LOWER_SHAPE_HELPER.east();
    private static final VoxelShapeHelper OCCLUSION_LOWER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 4.0D, 0.0D, 16.0D, 8.0D, 6.0D);
    protected static final VoxelShape OCCLUSION_SHAPE_NORTH_LOWER = OCCLUSION_LOWER_SHAPE_HELPER.north();
    protected static final VoxelShape OCCLUSION_SHAPE_SOUTH_LOWER = OCCLUSION_LOWER_SHAPE_HELPER.south();
    protected static final VoxelShape OCCLUSION_SHAPE_WEST_LOWER = OCCLUSION_LOWER_SHAPE_HELPER.west();
    protected static final VoxelShape OCCLUSION_SHAPE_EAST_LOWER = OCCLUSION_LOWER_SHAPE_HELPER.east();
    private static final VoxelShapeHelper CORNER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 6.0D)
            .applyCube(10.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH_CORNER = CORNER_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_CORNER = CORNER_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_CORNER = CORNER_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_CORNER = CORNER_SHAPE_HELPER.east();
    private static final VoxelShapeHelper OCCLUSION_CORNER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 6.0D)
            .applyCube(10.0D, 12.0D, 6.0D, 16.0D, 16.0D, 16.0D)
            .applyCube(3.0D, 0.0D, 0.5D, 8.0D, 3.0D, 5.5D)
            .applyCube(10.5, 0.0D, 8.0D, 15.5D, 3.0D, 13.0D);
    protected static final VoxelShape OCCLUSION_SHAPE_NORTH_CORNER = OCCLUSION_CORNER_SHAPE_HELPER.north();
    protected static final VoxelShape OCCLUSION_SHAPE_SOUTH_CORNER = OCCLUSION_CORNER_SHAPE_HELPER.south();
    protected static final VoxelShape OCCLUSION_SHAPE_WEST_CORNER = OCCLUSION_CORNER_SHAPE_HELPER.west();
    protected static final VoxelShape OCCLUSION_SHAPE_EAST_CORNER = OCCLUSION_CORNER_SHAPE_HELPER.east();
    private static final VoxelShapeHelper LOWER_CORNER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, -8.0D, 0.0D, 16.0D, 8.0D, 6.0D)
            .applyCube(10.0D, -8.0D, 6.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.north();
    protected static final VoxelShape SHAPE_SOUTH_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.south();
    protected static final VoxelShape SHAPE_WEST_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.west();
    protected static final VoxelShape SHAPE_EAST_CORNER_LOWER = LOWER_CORNER_SHAPE_HELPER.east();
    private static final VoxelShapeHelper OCCLUSION_LOWER_CORNER_SHAPE_HELPER = new VoxelShapeHelper()
            .applyCube(0.0D, 4.0D, 0.0D, 16.0D, 8.0D, 6.0D)
            .applyCube(10.0D, 4.0D, 6.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape OCCLUSION_SHAPE_NORTH_CORNER_LOWER = OCCLUSION_LOWER_CORNER_SHAPE_HELPER.north();
    protected static final VoxelShape OCCLUSION_SHAPE_SOUTH_CORNER_LOWER = OCCLUSION_LOWER_CORNER_SHAPE_HELPER.south();
    protected static final VoxelShape OCCLUSION_SHAPE_WEST_CORNER_LOWER = OCCLUSION_LOWER_CORNER_SHAPE_HELPER.west();
    protected static final VoxelShape OCCLUSION_SHAPE_EAST_CORNER_LOWER = OCCLUSION_LOWER_CORNER_SHAPE_HELPER.east();

    public StoneGuardrailBlock(Properties pProperties) {
        super(pProperties);
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
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        if (pState.getValue(CORNER)) {
            if (pState.getValue(LOWER)) {
                return switch (pState.getValue(FACING)) {
                    case NORTH -> OCCLUSION_SHAPE_NORTH_CORNER_LOWER;
                    case SOUTH -> OCCLUSION_SHAPE_SOUTH_CORNER_LOWER;
                    case WEST -> OCCLUSION_SHAPE_WEST_CORNER_LOWER;
                    case EAST -> OCCLUSION_SHAPE_EAST_CORNER_LOWER;
                    default -> Shapes.empty();
                };
            }
            else {
                return switch (pState.getValue(FACING)) {
                    case NORTH -> OCCLUSION_SHAPE_NORTH_CORNER;
                    case SOUTH -> OCCLUSION_SHAPE_SOUTH_CORNER;
                    case WEST -> OCCLUSION_SHAPE_WEST_CORNER;
                    case EAST -> OCCLUSION_SHAPE_EAST_CORNER;
                    default -> Shapes.empty();
                };
            }
        }
        else {
            if (pState.getValue(LOWER)) {
                return switch (pState.getValue(FACING)) {
                    case NORTH -> OCCLUSION_SHAPE_NORTH_LOWER;
                    case SOUTH -> OCCLUSION_SHAPE_SOUTH_LOWER;
                    case WEST -> OCCLUSION_SHAPE_WEST_LOWER;
                    case EAST -> OCCLUSION_SHAPE_EAST_LOWER;
                    default -> Shapes.empty();
                };
            }
            else {
                return switch (pState.getValue(FACING)) {
                    case NORTH -> OCCLUSION_SHAPE_NORTH;
                    case SOUTH -> OCCLUSION_SHAPE_SOUTH;
                    case WEST -> OCCLUSION_SHAPE_WEST;
                    case EAST -> OCCLUSION_SHAPE_EAST;
                    default -> Shapes.empty();
                };
            }
        }
    }
}
