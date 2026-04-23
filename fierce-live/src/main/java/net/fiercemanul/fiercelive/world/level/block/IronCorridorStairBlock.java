package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.fiercemanul.fiercesource.world.level.block.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IronCorridorStairBlock extends ThinStairBlock {


    protected static final VoxelShape[] COLLISION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 15.0, 0.0, 16.0, 16.0, 8.0)
                                  .applyCube(0.0, 7.0, 8.0, 16.0, 8.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 15.0, 0.0, 16.0, 16.0, 8.0)
                                  .applyCube(0.0, 15.0, 8.0, 8.0, 16.0, 16.0)
                                  .applyCube(8.0, 7.0, 8.0, 16.0, 8.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 15.0, 0.0, 8.0, 16.0, 8.0)
                                  .applyCube(8.0, 7.0, 0.0, 16.0, 8.0, 8.0)
                                  .applyCube(0.0, 7.0, 8.0, 16.0, 8.0, 16.0)
    );

    public IronCorridorStairBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPES[getShapeIndex(state)];
    }

    @Override
    public boolean isFamily(BlockState state) {
        return state.getBlock().getClass() == IronCorridorStairBlock.class;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return direction == Direction.DOWN && (
                adjacentState.is(FLBlockTags.FRAMES)
                        || (adjacentState.getBlock() instanceof IronCorridorSlabBlock && adjacentState.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.BOTTOM)
                        || (adjacentState.getBlock() instanceof IronCorridorStairBlock && !BlockUtils.shouldRenderFace(state, adjacentState, direction))
        );
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
}
