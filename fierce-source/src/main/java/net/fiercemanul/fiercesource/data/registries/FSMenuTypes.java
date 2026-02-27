package net.fiercemanul.fiercesource.data.registries;

import net.fiercemanul.fiercesource.world.level.menu.FierceMediaMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FSMenuTypes {


    public static final DeferredHolder<MenuType<?>, MenuType<? extends FierceMediaMenu>>
            FIERCE_MEDIA_MENU =  FCRegistries.MENU_TYPE.register("media_menu", () -> FierceMediaMenu.MENU_TYPE);

    public static void init() {}
}
