package net.fiercemanul.fiercelive.world.level.block;

import net.fiercemanul.fiercelive.world.level.block.entity.TextButtonBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class TextButtonBlock extends BigButtonBlock implements EntityBlock {


    public TextButtonBlock(BlockSetType type, int ticksToStayPressed, Properties properties) {
        super(type, ticksToStayPressed, properties);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TextButtonBlockEntity(pos, state);
    }

}
