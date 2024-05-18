package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleGlowingBlock extends WrenchDismantleBlock {


    public static final MapCodec<SimpleGlowingBlock> CODEC = simpleCodec(SimpleGlowingBlock::new);

    public SimpleGlowingBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends SimpleGlowingBlock> codec() {
        return CODEC;
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }
}
