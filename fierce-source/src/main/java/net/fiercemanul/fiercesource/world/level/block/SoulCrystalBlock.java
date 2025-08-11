package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.client.particle.SoulCrystalParticleProvider;
import net.fiercemanul.fiercesource.util.FSUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class SoulCrystalBlock extends ModelBlock {


    public static final VoxelShape SMALL_SHAPE = Block.box(6.0, 2.0, 6.0, 10.0, 14.0, 10.0);
    public static final VoxelShape MEDIUM_SHAPE = Block.box(5.5, 1, 5.5, 10.5, 15, 10.5);
    public static final VoxelShape LARGE_SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
    public static final SoulCrystalType SMALL = new SoulCrystalType(
            SMALL_SHAPE,
            (state, level, pos, random) -> {
                if (random.nextInt(5) == 0) SoulCrystalParticleProvider.spawnEmptySmallSoulCrystalParticle(pos);
            }
    );
    public static final SoulCrystalType MEDIUM = new SoulCrystalType(
            MEDIUM_SHAPE,
            (state, level, pos, random) -> {
                if (random.nextInt(4) == 0) SoulCrystalParticleProvider.spawnEmptyMediumSoulCrystalParticle(pos);
            }
    );
    public static final SoulCrystalType LARGE = new SoulCrystalType(
            LARGE_SHAPE,
            (state, level, pos, random) -> {
                if (random.nextInt(3) == 0) SoulCrystalParticleProvider.spawnEmptyLargeSoulCrystalParticle(pos);
            }
    );
    public static final MapCodec<SoulCrystalBlock> CODEC = simpleCodec(SoulCrystalBlock::new);

    public static BlockBehaviour.Properties getProperties(int light, MapColor mapColor) {
        return BlockBehaviour.Properties.of().strength(1.5F, 20.0F).lightLevel(value -> light).emissiveRendering(FSUtils::getTrue).sound(
                SoundType.LARGE_AMETHYST_BUD).mapColor(mapColor).noOcclusion();
    }

    protected final SoulCrystalType soulCrystalType;


    public SoulCrystalBlock(Properties pProperties) {
        super(pProperties);
        this.soulCrystalType = LARGE;
    }

    public SoulCrystalBlock(Properties properties, SoulCrystalType soulCrystalType) {
        super(properties);
        this.soulCrystalType = soulCrystalType;
    }


    @Override
    protected MapCodec<? extends SoulCrystalBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return soulCrystalType.shape();
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        soulCrystalType.animateTick(state, level, pos, random);
    }

}
