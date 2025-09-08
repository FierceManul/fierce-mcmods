package net.fiercemanul.fiercesource.server.level.menu;

import net.fiercemanul.fiercesource.network.protocol.game.FierceMenuData;
import net.fiercemanul.fiercesource.server.level.app.ServerMenuApp;
import net.fiercemanul.fiercesource.world.level.app.DataType.AppData;
import net.fiercemanul.fiercesource.world.level.menu.FierceMediaMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class ServerFierceMediaMenu extends FierceMediaMenu {


    private final ServerPlayer player;
    private final ServerLevel level;
    private final List<ServerMenuApp> menuApps = new ArrayList<>();


    public ServerFierceMediaMenu(
            int containerId, ServerPlayer player, Inventory playerInv, ServerLevel level,
            List<ServerMenuApp.Builder> menuAppsBuilder
    ) {
        super(containerId, playerInv);
        this.player = player;
        this.level = level;
        for (int i = 0; i < menuAppsBuilder.size(); i++) {
            menuApps.add(menuAppsBuilder.get(i).build(this, player, playerInv));
            if (i >= Byte.MAX_VALUE - 1) break;
        }
    }

    public void posMenuAppData(ServerMenuApp menuApp, AppData data) {
        if (menuApps.contains(menuApp)) PacketDistributor.sendToPlayer(player, new FierceMenuData(containerId, (byte) menuApps.indexOf(menuApp), data));
        else menuApp.cleanSelf();
    }

    @Override
    public List<ServerMenuApp> getMenuApps() {
        return menuApps;
    }

    public void writeClientInitData(RegistryFriendlyByteBuf buf) {
        buf.writeByte(menuApps.size());
        for (ServerMenuApp menuApp : menuApps) menuApp.encode(buf);
    }

}
