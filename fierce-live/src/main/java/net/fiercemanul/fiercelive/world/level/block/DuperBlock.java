package net.fiercemanul.fiercelive.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public abstract class DuperBlock extends Block {


    protected static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public DuperBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        boolean signal = level.hasNeighborSignal(pos);
        if (state.getValue(POWERED) == signal) return;
        level.setBlock(pos, state.setValue(POWERED, signal), Block.UPDATE_ALL);
        level.playSound(
                null,
                pos,
                SoundEvents.PISTON_EXTEND,
                SoundSource.BLOCKS,
                0.5F,
                level.random.nextFloat() * 0.25F + 0.6F
        );
        doDupe(state, level, pos);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.playSound(
                null,
                pos,
                SoundEvents.PISTON_CONTRACT,
                SoundSource.BLOCKS,
                0.5F,
                level.random.nextFloat() * 0.15F + 0.6F
        );
        doDupe(state, level, pos);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    protected abstract void doDupe(BlockState state, Level level, BlockPos pos);

}
