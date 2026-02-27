package net.fiercemanul.fiercesource.world.level.app;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class TestMenuApp implements MenuApp {


    protected final TestContainer container = new TestContainer();

    @Override
    public Slot[] buildHardSlots() {
        Slot[] slots = new Slot[27];
        for (int i = 0; i < 27; i++) slots[i] = new Slot(container, i, 0, 0);
        return slots;
    }

    public static class TestContainer implements RandomizableContainer {


        private final Random random = new Random();
        private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

        @Override
        public int getContainerSize() {
            return 27;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public ItemStack getItem(int slot) {
            return items.get(slot);
        }

        @Override
        public ItemStack removeItem(int slot, int amount) {
            ItemStack split = items.get(slot).split(amount);
            setChanged();
            return split;
        }

        @Override
        public ItemStack removeItemNoUpdate(int slot) {
            ItemStack stack = items.get(slot);
            items.set(slot, ItemStack.EMPTY);
            return stack;
        }

        @Override
        public void setItem(int slot, ItemStack stack) {
            items.set(slot, stack);
            setChanged();
        }

        @Override
        public void setChanged() {}

        @Override
        public boolean stillValid(Player player) {
            return true;
        }

        @Override
        public void clearContent() {
            items = NonNullList.withSize(27, ItemStack.EMPTY);
        }

        @Override
        public ResourceKey<LootTable> getLootTable() {
            return BuiltInLootTables.SIMPLE_DUNGEON;
        }

        @Override
        public void setLootTable(@Nullable ResourceKey<LootTable> lootTable) {}

        @Override
        public long getLootTableSeed() {
            return random.nextLong();
        }

        @Override
        public void setLootTableSeed(long seed) {}

        @Override
        public BlockPos getBlockPos() {
            return BlockPos.ZERO;
        }

        @Override
        public @Nullable Level getLevel() {
            return null;
        }

        @Override
        public void unpackLootTable(@Nullable Player player) {
            if (player == null || player.getServer() == null) return;
            LootTable loottable = player.getServer().reloadableRegistries().getLootTable(getLootTable());
            LootParams.Builder builder = new LootParams.Builder((ServerLevel)player.level())
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(getBlockPos()))
                    .withLuck(player.getLuck())
                    .withParameter(LootContextParams.THIS_ENTITY, player);
            loottable.fill(this, builder.create(LootContextParamSets.CHEST), this.getLootTableSeed());
        }
    }
}
