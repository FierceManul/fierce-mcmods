package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.client.level.menu.ClientFierceMediaMenu;
import net.fiercemanul.fiercesource.world.level.menu.FierceMediaMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FSMenuTypes {


    public static final DeferredHolder<MenuType<?>, MenuType<ClientFierceMediaMenu>>
            FIERCE_MEDIA_MENU =  FCRegistries.MENU_TYPE.register("fierce_menu", () -> FierceMediaMenu.MENU_TYPE);

    public static void init() {}
}
