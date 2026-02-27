package net.fiercemanul.fiercesource.client.gui.screens;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class LoggerSlot extends Slot {


    public LoggerSlot(Container container, int slot, int x, int y) {
        super(container, slot, x, y);
    }

    @Override
    public void onQuickCraft(ItemStack oldStack, ItemStack newStack) {
        FierceSource.LOGGER.info("Slot {} onQuickCraft {} {}", getSlotIndex(), BuiltInRegistries.ITEM.getKey(oldStack.getItem()).getPath(), BuiltInRegistries.ITEM.getKey(newStack.getItem()).getPath());
        super.onQuickCraft(oldStack, newStack);
    }

    @Override
    protected void onQuickCraft(ItemStack stack, int amount) {
        FierceSource.LOGGER.info("Slot {} onQuickCraft {} {}", getSlotIndex(), BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath(), amount);
        super.onQuickCraft(stack, amount);
    }

    @Override
    protected void onSwapCraft(int numItemsCrafted) {
        FierceSource.LOGGER.info("Slot {} onSwapCraft {}", getSlotIndex(), numItemsCrafted);
        super.onSwapCraft(numItemsCrafted);
    }

    @Override
    protected void checkTakeAchievements(ItemStack stack) {
        FierceSource.LOGGER.info("Slot {} checkTakeAchievements {}", getSlotIndex(), BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath());
        super.checkTakeAchievements(stack);
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        FierceSource.LOGGER.info("Slot {} onTake {} {}", getSlotIndex(), player.getScoreboardName(), BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath());
        super.onTake(player, stack);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        FierceSource.LOGGER.info("Slot {} mayPlace {}", getSlotIndex(), BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath());
        return super.mayPlace(stack);
    }

    @Override
    public void setByPlayer(ItemStack stack) {
        FierceSource.LOGGER.info("Slot {} setByPlayer {}", getSlotIndex(), BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath());
        super.setByPlayer(stack);
    }

    @Override
    public void setByPlayer(ItemStack newStack, ItemStack oldStack) {
        FierceSource.LOGGER.info("Slot {} setByPlayer {} {}", getSlotIndex(), BuiltInRegistries.ITEM.getKey(newStack.getItem()).getPath(), BuiltInRegistries.ITEM.getKey(oldStack.getItem()).getPath());
        super.setByPlayer(newStack, oldStack);
    }

    @Override
    public void set(ItemStack stack) {
        FierceSource.LOGGER.info("Slot {} set {}", getSlotIndex(), BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath());
        super.set(stack);
    }

    @Override
    public ItemStack remove(int amount) {
        FierceSource.LOGGER.info("Slot {} remove {}", getSlotIndex(), amount);
        return super.remove(amount);
    }

    @Override
    public boolean mayPickup(Player player) {
        FierceSource.LOGGER.info("Slot {} mayPickup {}", getSlotIndex(), player.getScoreboardName());
        return super.mayPickup(player);
    }

    @Override
    public Optional<ItemStack> tryRemove(int count, int decrement, Player player) {
        FierceSource.LOGGER.info("Slot {} tryRemove {} {} {}", getSlotIndex(), count, decrement, player.getScoreboardName());
        return super.tryRemove(count, decrement, player);
    }

    @Override
    public ItemStack safeTake(int count, int decrement, Player player) {
        FierceSource.LOGGER.info("Slot {} safeTake {} {} {}", getSlotIndex(), count, decrement, player.getScoreboardName());
        return super.safeTake(count, decrement, player);
    }

    @Override
    public ItemStack safeInsert(ItemStack stack) {
        FierceSource.LOGGER.info("Slot {} safeInsert {}", getSlotIndex(), BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath());
        return super.safeInsert(stack);
    }

    @Override
    public ItemStack safeInsert(ItemStack stack, int increment) {
        FierceSource.LOGGER.info("Slot {} safeInsert {} {}", getSlotIndex(), BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath(), increment);
        return super.safeInsert(stack, increment);
    }
}
