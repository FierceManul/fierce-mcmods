package net.fiercemanul.fiercedecoration.world.level.block.entity;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.world.level.block.CabinetBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class CabinetBlockEntity extends RandomizableContainerBlockEntity {


    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);


    public CabinetBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FierceDecoration.CABINET_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("fiercedecoration.container.cabinet");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> pItems) {
        items = pItems;
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return ChestMenu.threeRows(pContainerId, pInventory, this);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public void startOpen(Player pPlayer) {
        playSound(getBlockState(), SoundEvents.BARREL_OPEN);
    }

    @Override
    public void stopOpen(Player pPlayer) {
        playSound(getBlockState(), SoundEvents.BARREL_CLOSE);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        if (!this.trySaveLootTable(pTag)) {
            ContainerHelper.saveAllItems(pTag, this.items, pRegistries);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(pTag)) {
            ContainerHelper.loadAllItems(pTag, this.items, pRegistries);
        }
    }

    protected void playSound(BlockState pState, SoundEvent pSound) {
        Vec3i vec3i = pState.getValue(BlockStateProperties.HORIZONTAL_FACING).getNormal();
        double d0 = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
        double d1 = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
        double d2 = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
        this.level.playSound(null, d0, d1, d2, pSound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public void setBlockState(BlockState pState) {
        BlockState oldState = getBlockState();
        super.setBlockState(pState);
        if (((CabinetBlock)pState.getBlock()).shouldInvalidateCapabilities(oldState, pState)) this.invalidateCapabilities();
    }
}
