package net.fiercemanul.fiercesource.client.level.menu;

import net.fiercemanul.fiercesource.client.level.app.ClientMenuApp;
import net.fiercemanul.fiercesource.network.protocol.game.FierceMenuData;
import net.fiercemanul.fiercesource.util.TwoInt;
import net.fiercemanul.fiercesource.world.level.app.DataType.AppData;
import net.fiercemanul.fiercesource.world.level.menu.FierceMediaMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class ClientFierceMediaMenu extends FierceMediaMenu {


    public static final TwoInt DEFAULT_SLOT_POS = new TwoInt(0, -32768);
    private final Player player;
    private final List<ClientMenuApp> menuApps = new ArrayList<>();


    public ClientFierceMediaMenu(int containerId, Player player, Inventory playerInv, List<ClientMenuApp.Builder> menuAppsBuilder) {
        super(containerId, playerInv);
        this.player = player;
        for (ClientMenuApp.Builder builder : menuAppsBuilder) menuApps.add(builder.build(this, player, playerInv));
    }

    @Override
    public List<ClientMenuApp> getMenuApps() {
        return menuApps;
    }

    public void posMenuAppData(ClientMenuApp menuApp, AppData data) {
        if (menuApps.contains(menuApp)) PacketDistributor.sendToServer(new FierceMenuData(containerId, (byte) menuApps.indexOf(menuApp), data));
        else menuApp.cleanSelf();
    }

    public void rebuildSlots(TwoInt[] posArray) {
        for (int i = 0; i < playerInv.getContainerSize(); i++) {
            TwoInt twoInt = posArray[i];
            Slot slot = new Slot(playerInv, i, twoInt.a(), twoInt.b());
            slot.index = i;
            slots.set(i, slot);
        }
    }
}
