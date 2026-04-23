package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ConcreteGuardrailBlock extends GuardrailBlock {


    public static final MapCodec<ConcreteGuardrailBlock> CODEC = simpleCodec(ConcreteGuardrailBlock::new);
    protected static final VoxelShape[] SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 16.0, 3.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 16.0, 16.0, 3.0)
                                  .applyCube(0.0, 0.0, 3.0, 3.0, 16.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, 0.0, 0.0, 3.0, 16.0, 3.0)
    );
    protected static final VoxelShape[] BOTTOM_SHAPES = makeShapes(
            new VoxelShapeHelper().applyCube(0.0, -8.0, 0.0, 16.0, 8.0, 3.0),
            new VoxelShapeHelper().applyCube(0.0, -8.0, 0.0, 16.0, 8.0, 3.0)
                                  .applyCube(0.0, -8.0, 3.0, 3.0, 8.0, 16.0),
            new VoxelShapeHelper().applyCube(0.0, -8.0, 0.0, 3.0, 8.0, 3.0)
    );

    public ConcreteGuardrailBlock(Properties properties) {
        super(properties);
    }

    public MapCodec<? extends GuardrailBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (state.getValue(HALF) == Half.TOP ? SHAPES : BOTTOM_SHAPES)[getShapeIndex(state)];
    }

}
