package net.fiercemanul.fiercesource.server.level.app;

import net.fiercemanul.fiercesource.server.level.menu.ServerFierceMediaMenu;
import net.fiercemanul.fiercesource.world.level.app.MenuApp;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;

public interface ServerMenuApp extends MenuApp {


    void encode(RegistryFriendlyByteBuf buf);

    interface Builder {
        ServerMenuApp build(ServerFierceMediaMenu menu, ServerPlayer player, Inventory playerInventory);
    }

}
