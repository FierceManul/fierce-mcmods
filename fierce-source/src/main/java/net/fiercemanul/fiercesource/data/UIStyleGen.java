package net.fiercemanul.fiercesource.data;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.client.gui.style.UIStyles;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class UIStyleGen extends UIStyleProvider{


    public UIStyleGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void applyStyles() {
        apply(ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "test_one"), UIStyles.DEFAULT);
        apply(ResourceLocation.fromNamespaceAndPath("example", "test_two"), UIStyles.DEFAULT);
    }
}
