package net.fiercemanul.fiercesource.util;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public final class FSUtils {


    private FSUtils() {}

    public static boolean getFalse(Object... ignored) {
        return false;
    }

    public static boolean getTrue(Object... ignored) {
        return true;
    }

    public static boolean itemHasData(ItemStack stack) {
        return !stack.getComponentsPatch().isEmpty();
    }

    public static ResourceLocation rl(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, path);
    }

    public static boolean inArea(double xS, double xE, double yS, double yE, double pX, double pY) {
        return pX >= xS && pX < xE && pY >= yS && pY < yE;
    }


    //datagen:
    //net.neoforged.fml.loading.FMLLoader.getLaunchHandler().isData()
    //minecraft.getWindow().getGuiScaledWidth()
    //DataPackRegistryEvent
}
