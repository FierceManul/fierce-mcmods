package net.fiercemanul.fiercelive.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.SoulCrystalBlock;
import net.fiercemanul.fiercesource.world.level.block.SoulCrystalType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Set;

public class SoulCrystalOrnamentBlock extends SoulCrystalBlock {


    public static final MapCodec<SoulCrystalOrnamentBlock> CODEC = simpleCodec(SoulCrystalOrnamentBlock::new);
    public static final VoxelShape SHAPE = box(2.0, 0.0, 2.0, 14.0, 14.0, 14.0);
    private static final SoulCrystalType TYPE = new SoulCrystalType.SimpleType(
            SHAPE,
            new SoulCrystalType.ParticleDate(
                    new AABB(0.375, 0.3125, 0.375, 0.625, 0.875, 0.625),
                    Set.of(),
                    1, 1, 1,
                    0.08F, 14,
                    0.3F
            ),
            4
    );

    public SoulCrystalOrnamentBlock(Properties properties) {
        super(properties, TYPE);
    }

    @Override
    protected MapCodec<? extends SoulCrystalOrnamentBlock> codec() {
        return CODEC;
    }

}
