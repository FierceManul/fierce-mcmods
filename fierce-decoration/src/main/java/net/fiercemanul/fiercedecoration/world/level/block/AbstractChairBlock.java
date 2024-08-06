package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercedecoration.world.entity.Seat;
import net.fiercemanul.fiercesource.world.item.WrenchAction;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingBlock;
import net.fiercemanul.fiercesource.world.level.block.HorizonFacingModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractChairBlock extends HorizonFacingModelBlock {


    protected static final Vec3 SIT_POS_DEFAULT = new Vec3(0.5, 0.5625, 0.5);

    public AbstractChairBlock(Properties pProperties) {
        super(pProperties, HorizonFacingBlock.HORIZONTAL_DIRECTION_OPPOSITE);
    }

    @Override
    public abstract MapCodec<? extends AbstractChairBlock> codec();

    protected Vec3 getSitPos(Direction direction) {
        return SIT_POS_DEFAULT;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        Vec3 vec3 = getSitPos(pState.getValue(FACING));
        Seat seat = new Seat(pLevel, pPos.getX() + vec3.x, pPos.getY() + vec3.y, pPos.getZ() + vec3.z);
        pPlayer.setYRot(pState.getValue(FACING).toYRot());
        pPlayer.setXRot(0.0F);
        if (!pLevel.isClientSide) {
            pLevel.addFreshEntity(seat);
            pPlayer.startRiding(seat);
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }
}
