package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleCapabilityBlock extends WrenchDismantleBlock {


    public static final MapCodec<SimpleCapabilityBlock> CODEC = simpleCodec(SimpleCapabilityBlock::new);

    public SimpleCapabilityBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends SimpleCapabilityBlock> codec() {
        return CODEC;
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        pLevel.invalidateCapabilities(pPos);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        pLevel.invalidateCapabilities(pPos);
    }
}
