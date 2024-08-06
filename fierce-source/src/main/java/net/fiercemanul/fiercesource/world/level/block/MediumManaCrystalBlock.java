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

public class MediumManaCrystalBlock extends ManaCrystalBlock {

    public static final MapCodec<MediumManaCrystalBlock> CODEC = simpleCodec(MediumManaCrystalBlock::new);
    public static final VoxelShape SHAPE = box(6.5, 3.5, 6.5, 9.5, 12.5, 9.5);

    public MediumManaCrystalBlock(Properties pProperties) {
        super(pProperties.strength(1.5F, 6.0F).lightLevel(value -> 10).emissiveRendering(Utils::getTrue).sound(SoundType.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_LIGHT_BLUE));
    }

    @Override
    protected MapCodec<? extends MediumManaCrystalBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(4) == 0) SoulCrystalParticleProvider.spawnMediumSoulCrystalParticle(pPos, 0.66F, 0.92F, 0.95F);
    }
}
