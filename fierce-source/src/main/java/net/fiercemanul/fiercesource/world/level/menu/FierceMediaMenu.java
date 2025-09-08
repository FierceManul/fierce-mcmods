package net.fiercemanul.fiercesource.world.level.menu;

import net.fiercemanul.fiercesource.client.level.app.ClientMenuApp;
import net.fiercemanul.fiercesource.client.level.menu.ClientFierceMediaMenu;
import net.fiercemanul.fiercesource.registries.FSMenuTypes;
import net.fiercemanul.fiercesource.server.level.app.ServerApp;
import net.fiercemanul.fiercesource.server.level.app.ServerMenuApp;
import net.fiercemanul.fiercesource.server.level.menu.ServerFierceMediaMenu;
import net.fiercemanul.fiercesource.world.level.app.MenuApp;
import net.fiercemanul.fiercesource.world.level.app.MenuAppType;
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


    public static final MenuType<ClientFierceMediaMenu> MENU_TYPE = IMenuTypeExtension.create(FierceMediaMenu::clientMenu);
    public static ClientFierceMediaMenu clientMenu(int containerId, Inventory inv, @Nullable RegistryFriendlyByteBuf data) {
        if (data != null) {
            int c = data.readByte();
            if (c > 0) {
                List<ClientMenuApp.Builder> menuAppsBuilder = new ArrayList<>();
                for (int i = 0; i < c; i++) menuAppsBuilder.add(MenuAppType.decodeApp(data));
                return new ClientFierceMediaMenu(containerId, inv.player, inv, menuAppsBuilder);
            }
        }
        return new ClientFierceMediaMenu(containerId, inv.player, inv, List.of());
    }

    public final Inventory playerInv;
    public int random;

    public FierceMediaMenu(int containerId, Inventory playerInv) {
        super(FSMenuTypes.FIERCE_MEDIA_MENU.get(), containerId);
        this.playerInv = playerInv;
        for (int i = 0; i < playerInv.getContainerSize(); i++) addSlot(new Slot(playerInv, i, 0, 0));
    }

    public abstract List<? extends MenuApp> getMenuApps();

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
        getMenuApps().forEach(MenuApp::cleanSelf);
    }

    public void handleRemoteData(byte menuAppIndex, Object data) {
        getMenuApps().get(menuAppIndex).handleData(data);
    }

    public static class Provider implements MenuProvider {


        private final ServerLevel level;
        private final ServerApp[] apps;
        @Nullable
        private ServerFierceMediaMenu menu;

        public Provider(ServerLevel level, ServerApp[] apps) {
            this.level = level;
            this.apps = apps;
        }

        @Override
        public Component getDisplayName() {
            return Component.literal("-_-");
        }

        @Override
        public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
            List<ServerMenuApp.Builder> menuApps = new ArrayList<>();
            for (ServerApp app : apps) menuApps.addAll(app.getMenuAppConstructors());
            // item apps
            menu = new ServerFierceMediaMenu(containerId, (ServerPlayer) player, playerInventory, level, menuApps);
            return menu;
        }

        public void writeBuf(RegistryFriendlyByteBuf buf) {
            if (menu != null) menu.writeClientInitData(buf);
        }

    }
}
