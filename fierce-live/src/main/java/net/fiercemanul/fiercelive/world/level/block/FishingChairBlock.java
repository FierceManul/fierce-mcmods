package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FishingChairBlock extends SimpleChairBlock {


    public static final MapCodec<FishingChairBlock> CODEC = simpleCodec(FishingChairBlock::new);
    protected static final VoxelShapeHelper SHAPE = new VoxelShapeHelper()
            .applyCube(1, 0, 1, 15, 7, 15)
            .applyCube(1, 7, 14, 15, 16, 15)
            .applyCube(1, 7, 3, 2, 13, 14)
            .applyCube(14, 7, 3, 15, 13, 14);
    protected static final VoxelShape SHAPE_NORTH = SHAPE.north();
    protected static final VoxelShape SHAPE_SOUTH = SHAPE.south();
    protected static final VoxelShape SHAPE_WEST = SHAPE.west();
    protected static final VoxelShape SHAPE_EAST = SHAPE.east();
    protected static final VoxelShapeHelper COLLISION_SHAPE = new VoxelShapeHelper()
            .applyCube(1, 0, 1, 3, 7, 15)
            .applyCube(13, 0, 1, 15, 7, 15)
            .applyCube(1, 6, 1, 15, 7, 15)
            .applyCube(1, 7, 14, 15, 16, 15)
            .applyCube(1, 7, 3, 2, 13, 14)
            .applyCube(14, 7, 3, 15, 13, 14);
    protected static final VoxelShape COLLISION_SHAPE_NORTH = COLLISION_SHAPE.north();
    protected static final VoxelShape COLLISION_SHAPE_SOUTH = COLLISION_SHAPE.south();
    protected static final VoxelShape COLLISION_SHAPE_WEST = COLLISION_SHAPE.west();
    protected static final VoxelShape COLLISION_SHAPE_EAST = COLLISION_SHAPE.east();
    protected static final VoxelShapeHelper OCCLUSION_SHAPE = new VoxelShapeHelper().applyCube(1, 7, 14, 15, 16, 15);
    protected static final VoxelShape OCCLUSION_SHAPE_NORTH = OCCLUSION_SHAPE.north();
    protected static final VoxelShape OCCLUSION_SHAPE_SOUTH = OCCLUSION_SHAPE.south();
    protected static final VoxelShape OCCLUSION_SHAPE_WEST = OCCLUSION_SHAPE.west();
    protected static final VoxelShape OCCLUSION_SHAPE_EAST = OCCLUSION_SHAPE.east();

    public FishingChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends FishingChairBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> COLLISION_SHAPE_SOUTH;
            case WEST -> COLLISION_SHAPE_WEST;
            case EAST -> COLLISION_SHAPE_EAST;
            default -> COLLISION_SHAPE_NORTH;
        };
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> OCCLUSION_SHAPE_SOUTH;
            case WEST -> OCCLUSION_SHAPE_WEST;
            case EAST -> OCCLUSION_SHAPE_EAST;
            default -> OCCLUSION_SHAPE_NORTH;
        };
    }
}
