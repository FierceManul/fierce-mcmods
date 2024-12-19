package net.fiercemanul.fiercesource.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import net.fiercemanul.fiercesource.client.gui.style.UIStyle;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class UIStyleProvider implements DataProvider {


    protected final CompletableFuture<HolderLookup.Provider> lookupProvider;
    protected final PackOutput.PathProvider pathProvider;
    private final Map<ResourceLocation, UIStyle> styleMap = new HashMap<>();

    public UIStyleProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        this.lookupProvider = lookupProvider;
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, UIStyleLoader.PATH);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        applyStyles();
        return lookupProvider.thenCompose(provider -> {
            DynamicOps<JsonElement> dynamicOps = RegistryOps.create(JsonOps.INSTANCE, provider);
            return CompletableFuture.allOf(styleMap.entrySet().stream().map(
                    entry -> CompletableFuture.supplyAsync(
                            () -> UIStyle.CODEC.encodeStart(dynamicOps, entry.getValue()).getOrThrow()
                    ).thenComposeAsync(json -> DataProvider.saveStable(output, json, pathProvider.json(entry.getKey())))
            ).toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return "UIStyles";
    }

    protected abstract void applyStyles();

    protected final void apply(ResourceLocation location, UIStyle style) {
        styleMap.put(location, style);
    }
}
