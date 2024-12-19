package net.fiercemanul.fiercesource.util;

import net.minecraft.world.item.ItemStack;

public final class Utils {


    private Utils() {}

    public static boolean getFalse(Object... ignored) {
        return false;
    }

    public static boolean getTrue(Object... ignored) {
        return true;
    }

    public static boolean itemHasData(ItemStack stack) {
        return !stack.getComponentsPatch().isEmpty();
    }


    //datagen:
    //net.neoforged.fml.loading.FMLLoader.getLaunchHandler().isData()
    //minecraft.getWindow().getGuiScaledWidth()
    //DataPackRegistryEvent
}
