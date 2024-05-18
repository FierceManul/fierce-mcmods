package net.fiercemanul.fiercedecoration.world.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.block.Block;


public class CustomCraftingMenu extends CraftingMenu {


    private final ContainerLevelAccess access;
    private final Block craftingBlock;

    public CustomCraftingMenu(int pContainerId, Inventory pPlayerInventory, Block craftingBlock) {
        super(pContainerId, pPlayerInventory);
        this.access = ContainerLevelAccess.NULL;
        this.craftingBlock = craftingBlock;
    }

    public CustomCraftingMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess, Block craftingBlock) {
        super(pContainerId, pPlayerInventory, pAccess);
        this.access = pAccess;
        this.craftingBlock = craftingBlock;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access, pPlayer, craftingBlock);
    }
}
