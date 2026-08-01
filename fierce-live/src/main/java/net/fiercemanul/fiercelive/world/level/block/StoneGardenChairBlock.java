package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StoneGardenChairBlock extends GardenChairBlock {


    public static final MapCodec<StoneGardenChairBlock> CODEC = simpleCodec(StoneGardenChairBlock::new);
    protected static final VoxelShape[] SHAPES = buildShapes(
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(0, 8, 0, 2, 13, 13)
                                  .applyCube(14, 8, 0, 16, 13, 13)
                                  .applyCube(1, 8, 10, 15, 16, 16),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(0, 8, 0, 2, 13, 13)
                                  .applyCube(1, 8, 10, 16, 16, 16),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(14, 8, 0, 16, 13, 13)
                                  .applyCube(0, 8, 10, 15, 16, 16),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(0, 8, 10, 16, 16, 16)
    );
    protected static final VoxelShape[] COLLISION_SHAPES = buildShapes(
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(0, 8, 0, 2, 13, 13)
                                  .applyCube(14, 8, 0, 16, 13, 13)
                                  .applyCube(1, 8, 12, 15, 15, 15),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(0, 8, 0, 2, 13, 13)
                                  .applyCube(1, 8, 12, 16, 15, 15),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(14, 8, 0, 16, 13, 13)
                                  .applyCube(0, 8, 12, 15, 15, 15),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(0, 8, 12, 16, 15, 15)
    );
    protected static final VoxelShape[] OCCLUSION_SHAPES = buildShapes(
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(0, 8, 0, 2, 13, 13)
                                  .applyCube(14, 8, 0, 16, 13, 13),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(0, 8, 0, 2, 13, 13),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
                                  .applyCube(14, 8, 0, 16, 13, 13),
            new VoxelShapeHelper().applyCube(0, 0, 0, 16, 8, 13)
    );

    public StoneGardenChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends StoneGardenChairBlock> codec() {
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

}
