package net.fiercemanul.fiercesource.world.level.menu;

import net.fiercemanul.fiercesource.client.level.menu.ClientFierceMediaMenu;
import net.fiercemanul.fiercesource.server.level.app.ServerMenuApp;
import net.fiercemanul.fiercesource.server.level.menu.ServerFierceMediaMenu;
import net.fiercemanul.fiercesource.world.level.app.MenuApp;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class FierceMediaMenu extends AbstractContainerMenu {


    public static final MenuType<FierceMediaMenu> MENU_TYPE = IMenuTypeExtension.create(FierceMediaMenu::clientMenu);
    public static FierceMediaMenu clientMenu(int containerId, Inventory inv, @Nullable RegistryFriendlyByteBuf data) {
        return ClientFierceMediaMenu.decode(containerId, inv, data);
    }
    public static final int FAKE_SLOT_SIZE = 64;

    public final Inventory playerInv;
    public final int invSize;
    protected int hardSlotsSize;
    public int random;

    public FierceMediaMenu(int containerId, Inventory playerInv) {
        super(MENU_TYPE, containerId);
        this.playerInv = playerInv;
        this.invSize = playerInv.getContainerSize();
        for (int i = 0; i < invSize; i++) addSlot(new Slot(playerInv, i, 0, 0));
    }

    public abstract List<? extends MenuApp> getMenuApps();

    protected void addHardSlots(MenuApp app) {
        Slot[] hardSlots = app.buildHardSlots();
        for (Slot hardSlot : hardSlots) addSlot(hardSlot);
        hardSlotsSize = slots.size();
    }

    protected void addFakeSlots(MenuApp app) {
        Slot[] fakeSlots = app.getFakeSlots();
        for (Slot fakeSlot : fakeSlots) addSlot(fakeSlot);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        for (MenuApp menuApp : getMenuApps()) if (!menuApp.menuStillValid(player)) return false;
        return true;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        getMenuApps().forEach(MenuApp::setRemove);
    }

    public void handleRemoteData(byte menuAppIndex, Object data) {
        getMenuApps().get(menuAppIndex).handleData(data);
    }

    public int getHardSlotsSize() {
        return hardSlotsSize;
    }


    public static class Provider implements MenuProvider {


        private final ServerLevel level;
        private final ArrayList<ServerMenuApp.Builder> menuApps;
        @Nullable
        private ServerFierceMediaMenu menu;

        public Provider(ServerLevel level, ArrayList<ServerMenuApp.Builder> menuApps) {
            this.level = level;
            this.menuApps = menuApps;
        }

        @Override
        public Component getDisplayName() {
            return Component.literal("-_-");
        }

        @Override
        public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {

            // item apps
            menu = new ServerFierceMediaMenu(containerId, (ServerPlayer) player, playerInventory, level, menuApps);
            return menu;
        }

        public void writeBuf(RegistryFriendlyByteBuf buf) {
            if (menu != null) menu.writeClientInitData(buf);
        }

    }


}
