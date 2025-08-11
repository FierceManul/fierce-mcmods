package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.world.inventory.FierceContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FSMenuTypes {


    public static final DeferredHolder<MenuType<?>, MenuType<FierceContainerMenu>>
            FIERCE_MENU =  FCRegistries.MENU_TYPE.register("fierce_menu", () -> IMenuTypeExtension.create(FierceContainerMenu::new));

    public static void init() {}
}
