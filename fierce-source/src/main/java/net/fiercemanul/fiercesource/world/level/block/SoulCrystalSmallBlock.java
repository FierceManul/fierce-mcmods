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

public class SoulCrystalSmallBlock extends ModelBlock {

    public static final MapCodec<SoulCrystalSmallBlock> CODEC = simpleCodec(SoulCrystalSmallBlock::new);
    public static final VoxelShape SHAPE = Shapes.or(
            box(7.0, 1.0, 7.0, 9.0, 7.0, 9.0),
            box(4.0, 0.0, 4.0, 12.0, 1.0, 12.0),
            box(6.0, 1.0, 6.0, 10.0, 2.0, 10.0)
    );

    public SoulCrystalSmallBlock(Properties pProperties) {
        super(pProperties.strength(1.5F, 6.0F).lightLevel(value -> 5).emissiveRendering(Utils::getTrue).sound(SoundType.LARGE_AMETHYST_BUD).mapColor(MapColor.WOOL));
    }

    @Override
    protected MapCodec<? extends SoulCrystalSmallBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(5) == 0) SoulCrystalParticleProvider.spawnEmptySmallSoulCrystalParticle(pPos);
    }
}
