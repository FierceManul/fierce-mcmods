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
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        level.invalidateCapabilities(pos);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        super.onRemove(state, level, pos, newState, movedByPiston);
        level.invalidateCapabilities(pos);
    }

}
