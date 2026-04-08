package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WoodenGuardrailTypeBBlock extends GuardrailBlock {


    public static final MapCodec<GuardrailBlock> CODEC = simpleCodec(WoodenGuardrailTypeBBlock::new);
    protected static final VoxelShape[] SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 0.0, 1.0, 16.0, 16.0, 3.0),
            new VoxelShapeHelper().applyCube(4.0, 0.0, 1.0, 16.0, 16.0, 3.0)
                                  .applyCube(1.0, 0.0, 1.0, 4.0, 16.0, 4.0)
                                  .applyCube(1.0, 0.0, 4.0, 3.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 1.0, 3.0, 16.0, 3.0)
                                  .applyCube(1.0, 0.0, 0.0, 3.0, 16.0, 1.0)
    );
    protected static final VoxelShape[] BOTTOM_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, -8.0, 1.0, 16.0, 8.0, 3.0),
            new VoxelShapeHelper().applyCube(4.0, -8.0, 1.0, 16.0, 8.0, 3.0)
                                  .applyCube(1.0, -8.0, 1.0, 4.0, 8.0, 4.0)
                                  .applyCube(1.0, -8.0, 3.0, 3.0, 8.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, -8.0, 1.0, 3.0, 8.0, 3.0)
                                  .applyCube(1.0, -8.0, 0.0, 3.0, 8.0, 1.0)
    );
    protected static final VoxelShape[] OCCLUSION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.75, 0, 1, 4.75, 16, 3)
                                  .applyCube(6, 0, 1, 10, 16, 3)
                                  .applyCube(11.25, 0, 1, 15.25, 16, 3)
                                  .applyCube(0, 5, 1.5, 16, 8, 2.5)
                                  .applyCube(0, 10, 1.5, 16, 13, 2.5),
            new VoxelShapeHelper().applyCube(1, 0, 1, 4, 16, 4)
                                  .applyCube(6, 0, 1, 10, 16, 3)
                                  .applyCube(11.25, 0, 1, 15.25, 16, 3)
                                  .applyCube(4, 5, 1.5, 16, 8, 2.5)
                                  .applyCube(4, 10, 1.5, 16, 13, 2.5)
                                  .applyCube(1, 0, 6, 3, 16, 10)
                                  .applyCube(1, 0, 11.25, 3, 16, 15.25)
                                  .applyCube(1.5, 5, 4, 2.5, 8, 16)
                                  .applyCube(1.5, 10, 4, 2.5, 13, 16),
            new VoxelShapeHelper().applyCube(1, 0, 1, 3, 16, 3)
                                  .applyCube(0, 10, 1.5, 1, 13, 2.5)
                                  .applyCube(0, 5, 1.5, 1, 8, 2.5)
                                  .applyCube(1.5, 10, 0, 2.5, 13, 1)
                                  .applyCube(1.5, 5, 0, 2.5, 8, 1)

    );
    protected static final VoxelShape[] BOTTOM_OCCLUSION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.75, -8, 1, 4.75, 8, 3)
                                  .applyCube(6, -8, 1, 10, 8, 3)
                                  .applyCube(11.25, -8, 1, 15.25, 8, 3)
                                  .applyCube(0, -3, 1.5, 16, 0, 2.5)
                                  .applyCube(0, 2, 1.5, 16, 5, 2.5),
            new VoxelShapeHelper().applyCube(4, -3, 1.5, 16, 0, 2.5)
                                  .applyCube(4, 2, 1.5, 16, 5, 2.5)
                                  .applyCube(1.5, -3, 4, 2.5, 0, 16)
                                  .applyCube(1.5, 2, 4, 2.5, 5, 16),
            new VoxelShapeHelper().applyCube(0, 2, 1.5, 1, 5, 2.5)
                                  .applyCube(0, -3, 1.5, 1, 0, 2.5)
                                  .applyCube(1.5, 2, 0, 2.5, 5, 1)
                                  .applyCube(1.5, -3, 0, 2.5, 0, 1)
    );

    public WoodenGuardrailTypeBBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends GuardrailBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (state.getValue(HALF) == Half.TOP ? SHAPES : BOTTOM_SHAPES)[getShapeIndex(state)];
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return (state.getValue(HALF) == Half.TOP ? OCCLUSION_SHAPES : BOTTOM_OCCLUSION_SHAPES)[getShapeIndex(state)];
    }
    
}
