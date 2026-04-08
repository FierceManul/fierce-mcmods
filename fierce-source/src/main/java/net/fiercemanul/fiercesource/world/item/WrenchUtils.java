package net.fiercemanul.fiercesource.world.item;

import net.fiercemanul.fiercesource.data.tags.FSItemTags;
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
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Collections;
import java.util.List;

public final class WrenchUtils {


    public static ItemInteractionResult interactRotate(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player) {
        if (isWrench(stack) && player.isShiftKeyDown()) return wrenchRotate(state, level, pos)
                                                               ? ItemInteractionResult.sidedSuccess(level.isClientSide)
                                                               : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static boolean wrenchRotate(BlockState state, Level level, BlockPos pos) {
        return level.setBlock(pos, state.rotate(level, pos, Rotation.CLOCKWISE_90), 11);
    }

    public static <T extends Comparable<T>> ItemInteractionResult interact(
            Property<T> property, ItemStack stack, BlockState state, Level level, BlockPos pos, Player player
    ) {
        if (isWrench(stack) && player.isShiftKeyDown()) return wrenchInteract(property, state, level, pos)
                                                               ? ItemInteractionResult.sidedSuccess(level.isClientSide)
                                                               : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static <T extends Comparable<T>> boolean wrenchInteract(Property<T> property, BlockState state, Level level, BlockPos pos) {
        return level.setBlock(pos, state.setValue(property, Util.findNextInIterable(property.getPossibleValues(), state.getValue(property))), 11);
    }

    public static ItemInteractionResult interact(
            BooleanProperty property, ItemStack stack, BlockState state, Level level, BlockPos pos, Player player
    ) {
        if (isWrench(stack) && player.isShiftKeyDown()) return wrenchInteract(property, state, level, pos)
                                                               ? ItemInteractionResult.sidedSuccess(level.isClientSide)
                                                               : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static boolean wrenchInteract(BooleanProperty property, BlockState state, Level level, BlockPos pos) {
        return level.setBlock(pos, state.setValue(property, !state.getValue(property)), 11);
    }

    public static ItemInteractionResult interact(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player) {
        if (isWrench(stack) && player.isShiftKeyDown()) return dismantle(stack, state, level, pos, player)
                                                               ? ItemInteractionResult.sidedSuccess(level.isClientSide)
                                                               : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static boolean dismantle(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player) {
        List<ItemStack> items = Collections.emptyList();
        if (level instanceof ServerLevel serverLevel) items = Block.getDrops(state, serverLevel, pos, level.getBlockEntity(pos), player, stack);
        if (level.destroyBlock(pos, false, player)) {
            items.forEach(player::addItem);
            return true;
        }
        return false;
    }

    public static ItemInteractionResult interactConnect(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, float apothem, BlockHitResult hit) {
        if (isWrench(stack) && player.isShiftKeyDown()) return wrenchConnect(state, level, pos, apothem, hit)
                                                               ? ItemInteractionResult.sidedSuccess(level.isClientSide)
                                                               : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    /**
     * 扳手调整六向开关
     */
    public static boolean wrenchConnect(BlockState state, Level level, BlockPos pos, float apothem, BlockHitResult hit) {
        BooleanProperty property = BlockUtils.getInteractionDirectionProperty(
                apothem,
                hit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ()),
                PipeBlock.PROPERTY_BY_DIRECTION.get(hit.getDirection())
        );
        return level.setBlock(pos, state.setValue(property, !state.getValue(property)), 11);
    }

    public static ItemInteractionResult interactMachine(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (isWrench(stack) && player.isShiftKeyDown()) return wrenchMachine(state, level, pos, hit)
                                                               ? ItemInteractionResult.sidedSuccess(level.isClientSide)
                                                               : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    /**
     * 扳手调整机器面
     */
    public static boolean wrenchMachine(BlockState state, Level level, BlockPos pos, BlockHitResult hit) {
        BooleanProperty property = PipeBlock.PROPERTY_BY_DIRECTION.get(hit.getDirection());
        return level.setBlock(pos, state.setValue(property, !state.getValue(property)), 11);
    }

    public static boolean isWrench(ItemStack stack) {
        return stack.is(FSItemTags.WRENCH_ITEM);
    }

    private WrenchUtils() {}

}
