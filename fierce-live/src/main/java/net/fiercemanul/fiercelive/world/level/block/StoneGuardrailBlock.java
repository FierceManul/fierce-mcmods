package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StoneGuardrailBlock extends GuardrailBlock {


    public static final MapCodec<StoneGuardrailBlock> CODEC = simpleCodec(StoneGuardrailBlock::new);
    protected static final VoxelShape[] SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 16.0, 6.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 16.0, 6.0)
                                  .applyCube(0.0, 0.0, 6.0, 6.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 6.0, 16.0, 6.0)
    );
    protected static final VoxelShape[] BOTTOM_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, -8.0, 0.0, 16.0, 8.0, 6.0),
            new VoxelShapeHelper().applyCube(0.0, -8.0, 0.0, 16.0, 8.0, 6.0)
                                  .applyCube(0.0, -8.0, 6.0, 6.0, 8.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, -8.0, 0.0, 6.0, 8.0, 6.0)
    );
    protected static final VoxelShape[] OCCLUSION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 12.0, 0.0, 16.0, 16.0, 6.0)
                                  .applyCube(1.5, 0.0, 0.5, 6.5, 3.0, 5.5)
                                  .applyCube(9.5, 0.0, 0.5, 14.5, 3.0, 5.5),
            new VoxelShapeHelper().applyCube(0.0, 12.0, 0.0, 16.0, 16.0, 6.0)
                                  .applyCube(0.0, 12.0, 6.0, 6.0, 16.0, 16.0)
                                  .applyCube(9.5, 0, 0.5, 14.5, 3, 5.5)
                                  .applyCube(0.5, 0, 9.5, 5.5, 3, 14.5),
            new VoxelShapeHelper().applyCube(0.0, 12.0, 0.0, 6.0, 16.0, 6.0)
    );
    protected static final VoxelShape[] BOTTOM_OCCLUSION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 4.0, 0.0, 16.0, 8.0, 6.0),
            new VoxelShapeHelper().applyCube(0.0, 4.0, 0.0, 16.0, 8.0, 6.0)
                                  .applyCube(0.0, 4.0, 6.0, 6.0, 8.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 4.0, 0.0, 6.0, 8.0, 6.0)
    );

    public StoneGuardrailBlock(Properties properties) {
        super(properties);
    }

    public MapCodec<? extends GuardrailBlock> codec() {
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
