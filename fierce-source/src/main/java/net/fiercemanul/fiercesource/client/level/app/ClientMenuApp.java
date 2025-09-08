package net.fiercemanul.fiercesource.client.level.app;

import net.fiercemanul.fiercesource.client.level.menu.ClientFierceMediaMenu;
import net.fiercemanul.fiercesource.world.level.app.MenuApp;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface ClientMenuApp extends MenuApp {


    interface Builder {
        ClientMenuApp build(ClientFierceMediaMenu menu, Player player, Inventory playerInventory);
    }
}
