package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WoodenGardenChairBlock extends GardenChairBlock {


    public static final MapCodec<WoodenGardenChairBlock> CODEC = simpleCodec(WoodenGardenChairBlock::new);
    protected static final VoxelShape[] SHAPES = buildShapes(
            new VoxelShapeHelper().applyCube(1, 0, 1, 3, 6, 3)
                                  .applyCube(1, 0, 10, 3, 6, 12)
                                  .applyCube(1, 6, 1, 3, 7, 12)
                                  .applyCube(13, 0, 1, 15, 6, 3)
                                  .applyCube(13, 0, 10, 15, 6, 12)
                                  .applyCube(13, 6, 1, 15, 7, 12)
                                  .applyCube(0, 7, 0, 16, 8, 11)
                                  .applyCube(0.5, 8, 1, 1.5, 12, 2)
                                  .applyCube(0, 12, 0, 2, 13, 13)
                                  .applyCube(14.5, 8, 1, 15.5, 12, 2)
                                  .applyCube(14, 12, 0, 16, 13, 13)
                                  .applyCube(1, 7, 11, 15, 16, 15),
            new VoxelShapeHelper().applyCube(3, 0, 1, 5, 6, 3)
                                  .applyCube(3, 0, 10, 5, 6, 12)
                                  .applyCube(3, 6, 1, 5, 7, 12)
                                  .applyCube(0, 7, 0, 16, 8, 11)
                                  .applyCube(0.5, 8, 1, 1.5, 12, 2)
                                  .applyCube(0, 12, 0, 2, 13, 13)
                                  .applyCube(1, 7, 11, 16, 16, 15),
            new VoxelShapeHelper().applyCube(11, 0, 1, 13, 6, 3)
                                  .applyCube(11, 0, 10, 13, 6, 12)
                                  .applyCube(11, 6, 1, 13, 7, 12)
                                  .applyCube(0, 7, 0, 16, 8, 11)
                                  .applyCube(14.5, 8, 1, 15.5, 12, 2)
                                  .applyCube(14, 12, 0, 16, 13, 13)
                                  .applyCube(0, 7, 11, 15, 16, 15),
            new VoxelShapeHelper().applyCube(7, 0, 1, 9, 6, 3)
                                  .applyCube(7, 0, 10, 9, 6, 12)
                                  .applyCube(7, 6, 1, 9, 7, 12)
                                  .applyCube(0, 7, 0, 16, 8, 11)
                                  .applyCube(0, 7, 11, 16, 16, 15)
    );
    protected static final VoxelShape[] COLLISION_SHAPES = buildShapes(
            new VoxelShapeHelper().applyCube(1, 0, 1, 3, 6, 3)
                                  .applyCube(1, 0, 10, 3, 6, 12)
                                  .applyCube(1, 6, 1, 3, 7, 12)
                                  .applyCube(13, 0, 1, 15, 6, 3)
                                  .applyCube(13, 0, 10, 15, 6, 12)
                                  .applyCube(13, 6, 1, 15, 7, 12)
                                  .applyCube(0, 7, 0, 16, 8, 11)
                                  .applyCube(0, 8, 0, 2, 13, 13)
                                  .applyCube(14, 8, 0, 16, 13, 13)
                                  .applyCube(1, 9, 12, 15, 15, 14),
            new VoxelShapeHelper().applyCube(3, 0, 1, 5, 6, 3)
                                  .applyCube(3, 0, 10, 5, 6, 12)
                                  .applyCube(3, 6, 1, 5, 7, 12)
                                  .applyCube(0, 7, 0, 16, 8, 11)
                                  .applyCube(0, 8, 0, 2, 13, 13)
                                  .applyCube(1, 9, 12, 16, 15, 14),
            new VoxelShapeHelper().applyCube(11, 0, 1, 13, 6, 3)
                                  .applyCube(11, 0, 10, 13, 6, 12)
                                  .applyCube(11, 6, 1, 13, 7, 12)
                                  .applyCube(0, 7, 0, 16, 8, 11)
                                  .applyCube(14, 8, 0, 16, 13, 13)
                                  .applyCube(0, 9, 12, 15, 15, 14),
            new VoxelShapeHelper().applyCube(7, 0, 1, 9, 6, 3)
                                  .applyCube(7, 0, 10, 9, 6, 12)
                                  .applyCube(7, 6, 1, 9, 7, 12)
                                  .applyCube(0, 7, 0, 16, 8, 11)
                                  .applyCube(0, 9, 12, 16, 15, 14)
    );
    protected static final VoxelShape[] OCCLUSION_SHAPES = buildShapes(
            new VoxelShapeHelper().applyCube(1, 0, 1, 3, 6, 3)
                                  .applyCube(1, 0, 10, 3, 6, 12)
                                  .applyCube(13, 0, 1, 15, 6, 3)
                                  .applyCube(13, 0, 10, 15, 6, 12)
                                  .applyCube(0, 12, 0, 2, 13, 13)
                                  .applyCube(14, 12, 0, 16, 13, 13)
                                  .applyCube(0, 7, 0, 16, 8, 5)
                                  .applyCube(0, 7, 6, 16, 8, 11),
            new VoxelShapeHelper().applyCube(3, 0, 1, 5, 6, 3)
                                  .applyCube(3, 0, 10, 5, 6, 12)
                                  .applyCube(0, 12, 0, 2, 13, 13)
                                  .applyCube(0, 7, 0, 16, 8, 5)
                                  .applyCube(0, 7, 6, 16, 8, 11),
            new VoxelShapeHelper().applyCube(11, 0, 1, 13, 6, 3)
                                  .applyCube(11, 0, 10, 13, 6, 12)
                                  .applyCube(14, 12, 0, 16, 13, 13)
                                  .applyCube(0, 7, 0, 16, 8, 5)
                                  .applyCube(0, 7, 6, 16, 8, 11),
            new VoxelShapeHelper().applyCube(7, 0, 1, 9, 6, 3)
                                  .applyCube(7, 0, 10, 9, 6, 12)
                                  .applyCube(0, 7, 0, 16, 8, 5)
                                  .applyCube(0, 7, 6, 16, 8, 11)
    );

    public WoodenGardenChairBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends WoodenGardenChairBlock> codec() {
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
