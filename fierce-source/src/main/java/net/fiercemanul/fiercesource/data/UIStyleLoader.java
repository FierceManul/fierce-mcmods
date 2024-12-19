package net.fiercemanul.fiercesource.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.client.gui.style.UIStyles;
import net.fiercemanul.fiercesource.client.gui.style.UIStyle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class UIStyleLoader implements ResourceManagerReloadListener {


    public static final String PATH = "textures/gui/ui_styles";
    private static final Gson GSON = new Gson();

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        try {
            Map<ResourceLocation, Resource> resourceMap = resourceManager.listResources(PATH, resourceLocation -> true);
            Map<ResourceLocation, UIStyle> styleMap = new HashMap<>();
            for (Map.Entry<ResourceLocation, Resource> entry : resourceMap.entrySet()) {
                ResourceLocation location = entry.getKey();
                try (InputStream inputStream = entry.getValue().open()) {
                    JsonObject jsonObject = GSON.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8), JsonObject.class);
                    UIStyle style = UIStyle.CODEC.parse(JsonOps.INSTANCE, jsonObject).getOrThrow();
                    styleMap.put(ResourceLocation.fromNamespaceAndPath(
                            location.getNamespace(), location.getPath()
                                                             .replace("textures/gui/ui_styles/", "")
                                                             .replace(".json", "")
                                                             .replace("/", "_")
                    ), style);
                } catch (Exception exception) {
                    FierceSource.LOGGER.error("Failed to load UIStyle : " + location.toString());
                    FierceSource.LOGGER.error(exception.getMessage());
                }
            }
            UIStyles.setStyles(styleMap);
        } catch (Exception exception) {
            FierceSource.LOGGER.error("Failed to load UIStyles");
            FierceSource.LOGGER.error(exception.getMessage());
        }
    }

}
