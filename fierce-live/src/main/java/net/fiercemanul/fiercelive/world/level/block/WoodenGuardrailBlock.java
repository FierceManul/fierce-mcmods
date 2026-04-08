package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WoodenGuardrailBlock extends GuardrailBlock {


    public static final MapCodec<WoodenGuardrailBlock> CODEC = simpleCodec(WoodenGuardrailBlock::new);
    protected static final VoxelShape[] OCCLUSION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 14.0, 1.0, 16.0, 16.0, 4.0)
                                  .applyCube(0.0, 7.0, 2, 16, 9, 3)
                                  .applyCube(7, 0, 1.5, 9, 14, 3.5),
            new VoxelShapeHelper().applyCube(1.0, 14.0, 1.0, 16.0, 16.0, 4.0)
                                  .applyCube(1, 14, 4, 4, 16, 16)
                                  .applyCube(2, 7, 2, 16, 9, 3)
                                  .applyCube(2, 7, 3, 3, 9, 16)
                                  .applyCube(7, 0, 1.5, 9, 14, 3.5)
                                  .applyCube(1.5, 0, 7, 3.5, 14, 9),
            new VoxelShapeHelper().applyCube(0.0, 14.0, 1.0, 4.0, 16.0, 4.0)
                                  .applyCube(1.0, 14.0, 0.0, 4.0, 16.0, 1.0)
                                  .applyCube(0, 7, 2, 3, 9, 3)
                                  .applyCube(2, 7, 0, 3, 9, 2)
    );
    protected static final VoxelShape[] BOTTOM_OCCLUSION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 6.0, 1.0, 16.0, 8.0, 4.0)
                                  .applyCube(0.0, -1.0, 2, 16, 1, 3),
            new VoxelShapeHelper().applyCube(1.0, 6.0, 1.0, 16.0, 8.0, 4.0)
                                  .applyCube(1, 6, 4, 4, 8, 16)
                                  .applyCube(2, -1, 2, 16, 1, 3)
                                  .applyCube(2, -1, 3, 3, 1, 16),
            new VoxelShapeHelper().applyCube(0.0, 6.0, 1.0, 4.0, 8.0, 4.0)
                                  .applyCube(1.0, 6.0, 0.0, 4.0, 8.0, 1.0)
                                  .applyCube(0, -1, 2, 3, 1, 3)
                                  .applyCube(2, -1, 0, 3, 1, 2)
    );

    public WoodenGuardrailBlock(Properties properties) {
        super(properties);
    }

    public MapCodec<? extends GuardrailBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return (state.getValue(HALF) == Half.TOP ? OCCLUSION_SHAPES : BOTTOM_OCCLUSION_SHAPES)[getShapeIndex(state)];
    }

}
