package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;

public abstract class ManaCrystalBlock extends ModelBlock {

    public ManaCrystalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected abstract MapCodec<? extends ManaCrystalBlock> codec();
}
