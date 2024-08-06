package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.client.particle.SoulCrystalParticleProvider;
import net.fiercemanul.fiercesource.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LargeManaCrystalBlock extends ManaCrystalBlock {

    public static final MapCodec<LargeManaCrystalBlock> CODEC = simpleCodec(LargeManaCrystalBlock::new);
    public static final VoxelShape SHAPE = box(6.0, 2.0, 6.0, 10.0, 14.0, 10.0);

    public LargeManaCrystalBlock(Properties pProperties) {
        super(pProperties.strength(1.5F, 6.0F).lightLevel(value -> 15).emissiveRendering(Utils::getTrue).sound(SoundType.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_LIGHT_BLUE));
    }

    @Override
    protected MapCodec<? extends LargeManaCrystalBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(3) == 0) SoulCrystalParticleProvider.spawnLargeSoulCrystalParticle(pPos, 0.66F, 0.92F, 0.95F);
    }
}
