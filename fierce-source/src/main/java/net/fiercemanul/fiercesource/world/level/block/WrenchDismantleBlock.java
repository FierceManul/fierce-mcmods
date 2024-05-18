package net.fiercemanul.fiercesource.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;


/**
 * 可被扳手拆除
 */
public class WrenchDismantleBlock extends Block {


    public static final MapCodec<WrenchDismantleBlock> CODEC = simpleCodec(WrenchDismantleBlock::new);

    public WrenchDismantleBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends WrenchDismantleBlock> codec() {
        return CODEC;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        boolean success = WrenchAction.doWrenchDismantleAction(pState, pLevel, pPos, pPlayer, pHand);
        return success ? InteractionResult.sidedSuccess(pLevel.isClientSide) : InteractionResult.PASS;
    }
}
