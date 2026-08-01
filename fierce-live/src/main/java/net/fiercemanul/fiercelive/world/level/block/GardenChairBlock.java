package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

public abstract class GardenChairBlock extends AbstractLongChairBlock {


    protected static final Vec3 SIT_POS_NORTH = new Vec3(0.5, 0.5625, 0.4375);
    protected static final Vec3 SIT_POS_SOUTH = VoxelShapeHelper.south(SIT_POS_NORTH);
    protected static final Vec3 SIT_POS_WEST = VoxelShapeHelper.west(SIT_POS_NORTH);
    protected static final Vec3 SIT_POS_EAST = VoxelShapeHelper.east(SIT_POS_NORTH);

    public GardenChairBlock(Properties properties) {
        super(properties);
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
