package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;



public class GlassGuardrailBlock extends GuardrailBlock {


    public static final MapCodec<GuardrailBlock> CODEC = simpleCodec(GlassGuardrailBlock::new);

    public GlassGuardrailBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
    }

    protected MapCodec<? extends GuardrailBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pDirection) {
        if (pAdjacentBlockState.getBlock() instanceof GlassGuardrailBlock) {
            return defaultSkipRendering(pState, pAdjacentBlockState, pDirection);
        }
        return super.skipRendering(pState, pAdjacentBlockState, pDirection);
    }
}
