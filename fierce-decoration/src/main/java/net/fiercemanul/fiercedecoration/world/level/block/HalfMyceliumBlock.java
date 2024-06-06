package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class HalfMyceliumBlock extends HalfSpreadingBlock {


    public static final MapCodec<HalfMyceliumBlock> CODEC = simpleCodec(HalfMyceliumBlock::new);

    public HalfMyceliumBlock(Properties pProperties) {
        super(pProperties, Blocks.MYCELIUM);
    }

    @Override
    protected MapCodec<? extends HalfMyceliumBlock> codec() {
        return CODEC;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);
        if (pRandom.nextInt(10) == 0) {
            pLevel.addParticle(
                    ParticleTypes.MYCELIUM,
                    (double)pPos.getX() + pRandom.nextDouble(),
                    (double)pPos.getY() + 0.6,
                    (double)pPos.getZ() + pRandom.nextDouble(),
                    0.0,
                    0.0,
                    0.0
            );
        }
    }
}
