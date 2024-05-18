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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoulCrystalMediumBlock extends ModelBlock {

    public static final MapCodec<SoulCrystalMediumBlock> CODEC = simpleCodec(SoulCrystalMediumBlock::new);
    public static final VoxelShape SHAPE = Shapes.or(
            box(6.5, 2.0, 6.5, 9.5, 11.0, 9.5),
            box(2.5, 0.0, 2.5, 13.5, 2.0, 13.5),
            box(5.5, 2.0, 5.5, 10.5, 4.0, 10.5)
    );

    public SoulCrystalMediumBlock(Properties pProperties) {
        super(pProperties.strength(1.5F, 6.0F).lightLevel(value -> 10).emissiveRendering(Utils::getTrue).sound(SoundType.LARGE_AMETHYST_BUD).mapColor(MapColor.WOOL));
    }

    @Override
    protected MapCodec<? extends SoulCrystalMediumBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(4) == 0) SoulCrystalParticleProvider.spawnEmptyMediumSoulCrystalParticle(pPos);
    }
}
