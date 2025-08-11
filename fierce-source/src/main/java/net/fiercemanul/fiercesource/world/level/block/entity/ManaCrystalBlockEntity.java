package net.fiercemanul.fiercesource.world.level.block.entity;

import net.fiercemanul.fiercesource.registries.FSBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class ManaCrystalBlockEntity extends BlockEntity {


    private long max_mana;

    public ManaCrystalBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FSBlockEntityTypes.CREATIVE_MANA_OUTPUT_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }


}
