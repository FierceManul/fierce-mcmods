package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.client.particle.SoulCrystalParticleProvider;
import net.fiercemanul.fiercesource.util.Utils;
import net.fiercemanul.fiercesource.world.level.block.ModelBlock;
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

public class SoulCrystalOrnamentBlock extends ModelBlock {

    public static final MapCodec<SoulCrystalOrnamentBlock> CODEC = simpleCodec(SoulCrystalOrnamentBlock::new);
    public static final VoxelShape SHAPE = box(2.0, 0.0, 2.0, 14.0, 14.0, 14.0);

    public SoulCrystalOrnamentBlock(Properties pProperties) {
        super(pProperties.strength(1.5F, 6.0F).lightLevel(value -> 15).emissiveRendering(Utils::getTrue).sound(SoundType.LARGE_AMETHYST_BUD).mapColor(MapColor.WOOL));
    }

    @Override
    protected MapCodec<? extends SoulCrystalOrnamentBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(3) == 0) {
            double x = pRandom.nextDouble() * 0.75 + 0.125;
            double y = pRandom.nextDouble() * 0.8125 + 0.125;
            double z = pRandom.nextDouble() * 0.75 + 0.125;
            SoulCrystalParticleProvider.spawnParticle(
                    pPos.getX() + x,
                    pPos.getY() + y,
                    pPos.getZ() + z,
                    1.0F, 1.0F, 1.0F,
                    SoulCrystalParticleProvider.DEFAULT_SIZE.get()
            );
        }
    }
}
