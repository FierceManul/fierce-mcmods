package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.shapes.VoxelShape;


public class GlassGuardrailBlock extends GuardrailBlock {


    public static final MapCodec<GuardrailBlock> CODEC = simpleCodec(GlassGuardrailBlock::new);
    public static final Properties DEFAULT_PROPERTIES = Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL);
    protected static final VoxelShape[] OCCLUSION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 14.0, 1.0, 16.0, 16.0, 4.0)
                                  .applyCube(0.0, 0.0, 1.5, 1.0, 14.0, 3.5)
                                  .applyCube(15.0, 0.0, 1.5, 16.0, 14.0, 3.5),
            new VoxelShapeHelper().applyCube(1.0, 14.0, 1.0, 16.0, 16.0, 4.0)
                                  .applyCube(1.0, 14.0, 4, 4.0, 16.0, 16)
                                  .applyCube(1.5, 0.0, 1.5, 3.5, 14.0, 3.5)
                                  .applyCube(15.0, 0.0, 1.5, 16.0, 14.0, 3.5)
                                  .applyCube(1.5, 0.0, 15.0, 3.5, 14.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 14.0, 1.0, 4.0, 16.0, 4.0)
                                  .applyCube(1.0, 14.0, 0.0, 4.0, 16.0, 1.0)
    );
    protected static final VoxelShape[] BOTTOM_OCCLUSION_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 6.0, 1.0, 16.0, 8.0, 4.0)
                                  .applyCube(0.0, -8.0, 1.5, 1.0, 6.0, 3.5)
                                  .applyCube(15.0, -8.0, 1.5, 16.0, 6.0, 3.5),
            new VoxelShapeHelper().applyCube(1.0, 6.0, 1.0, 16.0, 8.0, 4.0)
                                  .applyCube(1.0, 6.0, 4, 4.0, 8.0, 16)
                                  .applyCube(15.0, -8.0, 1.5, 16.0, 6.0, 3.5)
                                  .applyCube(1.5, -8.0, 15.0, 3.5, 6.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 6.0, 1.0, 4.0, 8.0, 4.0)
                                  .applyCube(1.0, 6.0, 0.0, 4.0, 8.0, 1.0)
    );

    public GlassGuardrailBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends GuardrailBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return (state.getValue(HALF) == Half.TOP ? OCCLUSION_SHAPES : BOTTOM_OCCLUSION_SHAPES)[getShapeIndex(state)];
    }

}
