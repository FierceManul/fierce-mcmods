package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;


public class GlassGuardrailBlock extends GuardrailBlock {


    public static final MapCodec<GuardrailBlock> CODEC = simpleCodec(GlassGuardrailBlock::new);

    public GlassGuardrailBlock(Properties pProperties) {
        super(pProperties.mapColor(MapColor.NONE).noOcclusion());
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
