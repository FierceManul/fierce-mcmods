package net.fiercemanul.fiercesource.client.level.menu;

import net.fiercemanul.fiercesource.client.gui.screens.LoggerSlot;
import net.fiercemanul.fiercesource.client.level.app.ClientMenuApp;
import net.fiercemanul.fiercesource.network.protocol.game.FierceMenuData;
import net.fiercemanul.fiercesource.util.TwoInt;
import net.fiercemanul.fiercesource.world.level.app.DataType.AppData;
import net.fiercemanul.fiercesource.world.level.app.MenuAppType;
import net.fiercemanul.fiercesource.world.level.menu.FierceMediaMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ClientFierceMediaMenu extends FierceMediaMenu {


    public static FierceMediaMenu decode(int containerId, Inventory inv, @Nullable RegistryFriendlyByteBuf data) {
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
    public static final TwoInt DEFAULT_SLOT_POS = new TwoInt(0, -32768);
    private final Player player;
    private final List<ClientMenuApp> menuApps = new ArrayList<>();


    public ClientFierceMediaMenu(int containerId, Player player, Inventory playerInv, List<ClientMenuApp.Builder> menuAppsBuilder) {
        super(containerId, playerInv);
        this.player = player;
        for (ClientMenuApp.Builder builder : menuAppsBuilder) menuApps.add(builder.build(this, player, playerInv));
        addHardSlots(menuApps.getFirst());
    }

    @Override
    public List<ClientMenuApp> getMenuApps() {
        return menuApps;
    }

    public void posMenuAppData(ClientMenuApp menuApp, AppData data) {
        if (menuApps.contains(menuApp)) PacketDistributor.sendToServer(new FierceMenuData(containerId, (byte) menuApps.indexOf(menuApp), data));
        else menuApp.setRemove();
    }

    public void rebuildSlots(TwoInt[] posArray) {
        for (int i = 0; i < slots.size(); i++) {
            TwoInt twoInt = i < posArray.length ? posArray[i] : DEFAULT_SLOT_POS;

            Slot oldSlot = slots.get(i);
            Slot slot = new LoggerSlot(oldSlot.container, oldSlot.getSlotIndex(), twoInt.a(), twoInt.b());
            slot.index = i;
            slots.set(i, slot);
        }
    }
}
