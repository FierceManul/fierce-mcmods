package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GardenChairBlock extends AbstractLongChairBlock {


    public static final MapCodec<GardenChairBlock> CODEC = simpleCodec(GardenChairBlock::new);

    protected static final VoxelShape[] SHAPES = buildShapes(
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(0.0, 8.0, 0.0, 2.0, 13.0, 13.0)
                                  .applyCube(14.0, 8.0, 0.0, 16.0, 13.0, 13.0)
                                  .applyCube(1.0, 8.0, 10.0, 15.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(0.0, 8.0, 0.0, 2.0, 13.0, 13.0)
                                  .applyCube(1.0, 8.0, 10.0, 16.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(14.0, 8.0, 0.0, 16.0, 13.0, 13.0)
                                  .applyCube(0.0, 8.0, 10.0, 15.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(0.0, 8.0, 10.0, 16.0, 16.0, 16.0)
    );
    protected static final VoxelShape[] COLLISION_SHAPES = buildShapes(
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(0.0, 8.0, 0.0, 2.0, 13.0, 13.0)
                                  .applyCube(14.0, 8.0, 0.0, 16.0, 13.0, 13.0)
                                  .applyCube(1.0, 8.0, 12.0, 15.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(0.0, 8.0, 0.0, 2.0, 13.0, 13.0)
                                  .applyCube(1.0, 8.0, 12.0, 16.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(14.0, 8.0, 0.0, 16.0, 13.0, 13.0)
                                  .applyCube(0.0, 8.0, 12.0, 15.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(0.0, 8.0, 12.0, 16.0, 16.0, 16.0)
    );
    protected static final VoxelShape[] OCCLUSION_SHAPES = buildShapes(
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(0.0, 8.0, 0.0, 2.0, 13.0, 13.0)
                                  .applyCube(14.0, 8.0, 0.0, 16.0, 13.0, 13.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(0.0, 8.0, 0.0, 2.0, 13.0, 13.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
                                  .applyCube(14.0, 8.0, 0.0, 16.0, 13.0, 13.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 8.0, 13.0)
    );
    protected static final Vec3 SIT_POS_NORTH = new Vec3(0.5, 0.5625, 0.4375);
    protected static final Vec3 SIT_POS_SOUTH = VoxelShapeHelper.south(SIT_POS_NORTH);
    protected static final Vec3 SIT_POS_WEST = VoxelShapeHelper.west(SIT_POS_NORTH);
    protected static final Vec3 SIT_POS_EAST = VoxelShapeHelper.east(SIT_POS_NORTH);

    public GardenChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends GardenChairBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[getShapeIndex(state)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPES[getShapeIndex(state)];
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return OCCLUSION_SHAPES[getShapeIndex(state)];
    }

    @Override
    protected Vec3 getSitPos(Direction direction) {
        return switch (direction) {
            case DOWN, UP -> SIT_POS_DEFAULT;
            case NORTH -> SIT_POS_NORTH;
            case SOUTH -> SIT_POS_SOUTH;
            case WEST -> SIT_POS_WEST;
            case EAST -> SIT_POS_EAST;
        };
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

}
