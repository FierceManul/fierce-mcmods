package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;


public class ManaCrystalBlock extends SoulCrystalBlock {


    public static final MapCodec<ManaCrystalBlock> CODEC = simpleCodec(ManaCrystalBlock::new);


    public ManaCrystalBlock(Properties properties) {
        super(properties, SoulCrystalType.MANA_SMALL);
    }

    public ManaCrystalBlock(Properties properties, SoulCrystalType soulCrystalType) {
        super(properties, soulCrystalType);
    }


    @Override
    protected MapCodec<? extends ManaCrystalBlock> codec() {
        return CODEC;
    }

}
