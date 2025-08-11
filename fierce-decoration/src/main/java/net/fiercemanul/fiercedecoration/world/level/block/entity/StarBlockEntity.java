package net.fiercemanul.fiercedecoration.world.level.block.entity;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class StarBlockEntity extends BlockEntity {


    public StarBlockEntity(BlockPos pos, BlockState state) {
        super(FierceDecoration.STAR_BLOCK_ENTITY.get(), pos, state);
    }

}
