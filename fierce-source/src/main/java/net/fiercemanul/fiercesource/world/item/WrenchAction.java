package net.fiercemanul.fiercesource.world.item;

import net.fiercemanul.fiercesource.tags.FSItemTags;
import net.fiercemanul.fiercesource.world.level.block.BlockUtils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Collections;
import java.util.List;

public class WrenchAction {


    public static <T extends Comparable<T>> ItemInteractionResult defaultUseOn(
            Property<T> property, ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer
    ) {
        boolean success = doDefaultWrenchAction(property, pStack, pState, pLevel, pPos, pPlayer);
        return success ? ItemInteractionResult.sidedSuccess(pLevel.isClientSide) : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static ItemInteractionResult defaultUseOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        boolean success = doWrenchDismantleAction(pStack, pState, pLevel, pPos, pPlayer);
        return success ? ItemInteractionResult.sidedSuccess(pLevel.isClientSide) : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    /**
     * @return 成功?
     */
    public static <T extends Comparable<T>> boolean doDefaultWrenchAction(Property<T> property, ItemStack pStack,  BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        if (notWrench(pStack)) return false;
        if (pPlayer.isCrouching()) return wrenchDismantle(pStack, pState, pLevel, pPos, pPlayer);
        else return pLevel.setBlock(pPos, pState.setValue(property, Util.findNextInIterable(property.getPossibleValues(), pState.getValue(property))), 11);
    }

    /**
     * @return 成功?
     */
    public static boolean doDefaultWrenchAction(BooleanProperty property, ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        if (notWrench(pStack)) return false;
        if (pPlayer.isCrouching()) return wrenchDismantle(pStack, pState, pLevel, pPos, pPlayer);
        else return pLevel.setBlock(pPos, pState.setValue(property, !pState.getValue(property)), 11);
    }


    /**
     * 扳手拆除
     */
    public static boolean doWrenchDismantleAction(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        if (notWrench(pStack)) return false;
        if (pPlayer.isCrouching()) return wrenchDismantle(pStack, pState, pLevel, pPos, pPlayer);
        return false;
    }

    public static boolean wrenchDismantle(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        List<ItemStack> items = Collections.emptyList();
        if (pLevel instanceof ServerLevel serverLevel) {
            items = Block.getDrops(pState, serverLevel, pPos, pLevel.getBlockEntity(pPos), pPlayer, pStack);
        }
        if (pLevel.destroyBlock(pPos, false, pPlayer)) {
            items.forEach(pPlayer::addItem);
            return true;
        }
        return false;
    }


    /**
     * 扳手调整六向开关
     */
    public static boolean doWrenchConnectAction(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, float apothem, BlockHitResult pHit) {
        if (notWrench(pStack)) return false;
        if (pPlayer.isCrouching()) return wrenchDismantle(pStack, pState, pLevel, pPos, pPlayer);
        else {
            BooleanProperty property = BlockUtils.getInteractionDirectionProperty(
                    apothem, pHit.getLocation().subtract(pPos.getX(), pPos.getY(), pPos.getZ()), PipeBlock.PROPERTY_BY_DIRECTION.get(pHit.getDirection()));
            return pLevel.setBlock(pPos, pState.setValue(property, !pState.getValue(property)), 11);
        }
    }

    /**
     * 扳手调整机器面
     */
    public static boolean doWrenchMachineAction(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        if (notWrench(pStack)) return false;
        if (pPlayer.isCrouching()) return wrenchDismantle(pStack, pState, pLevel, pPos, pPlayer);
        else {
            BooleanProperty property = PipeBlock.PROPERTY_BY_DIRECTION.get(pHit.getDirection());
            return pLevel.setBlock(pPos, pState.setValue(property, !pState.getValue(property)), 11);
        }
    }

    private static boolean notWrench(ItemStack pStack) {
        return pStack.getTags().noneMatch(tagKey -> tagKey.equals(FSItemTags.WRENCH_ITEM));
    }
}
