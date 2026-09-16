package net.fiercemanul.fiercesource.mixins;

import net.fiercemanul.fiercesource.config.CommonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.common.CreativeModeTabRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

@Mixin(CreativeModeTabRegistry.class)
public abstract class CreativeModeTabRegistryMixin {


    @Shadow
    public static ResourceLocation getName(CreativeModeTab tab) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Shadow
    private static void setCreativeModeTabOrder(List<CreativeModeTab> tierList) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Redirect(method = "recalculateItemCreativeModeTabs", at = @At(value = "INVOKE",
            target = "Lnet/neoforged/neoforge/common/CreativeModeTabRegistry;setCreativeModeTabOrder(Ljava/util/List;)V"))
    private static void redirectSetCreativeModeTabOrder(List<CreativeModeTab> tierList) {
        if (CommonConfig.forceVanillaCreativeModeTabFront) {
            List<CreativeModeTab> vanillaTabs = new ArrayList<>();
            for (CreativeModeTab tab : tierList) if (getName(tab).getNamespace().equals("minecraft")) vanillaTabs.add(tab);
            for (int i = 0; i < vanillaTabs.size(); i++) {
                CreativeModeTab tab = vanillaTabs.get(i);
                tierList.remove(tab);
                tierList.add(i, tab);
            }
        }
        setCreativeModeTabOrder(tierList);
    }

}
