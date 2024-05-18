package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;



public class WoodenGuardrailBlock extends GuardrailBlock {


    public static final MapCodec<GuardrailBlock> CODEC = simpleCodec(WoodenGuardrailBlock::new);

    public WoodenGuardrailBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
    }

    protected MapCodec<? extends GuardrailBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pDirection) {
        if (pAdjacentBlockState.getBlock() instanceof WoodenGuardrailBlock) {
            return defaultSkipRendering(pState, pAdjacentBlockState, pDirection);
        }
        return super.skipRendering(pState, pAdjacentBlockState, pDirection);
    }
}
