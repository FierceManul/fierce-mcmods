package net.fiercemanul.fiercesource.world.level.app;

import net.fiercemanul.fiercesource.client.level.app.ClientMenuApp;
import net.fiercemanul.fiercesource.registries.FCRegistries;
import net.fiercemanul.fiercesource.server.level.app.ServerMenuApp;
import net.minecraft.Util;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.StreamDecoder;
import net.minecraft.network.codec.StreamEncoder;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public record MenuAppType<T extends ServerMenuApp>(
        ResourceLocation rl,
        StreamEncoder<RegistryFriendlyByteBuf, T> encoder,
        Supplier<StreamDecoder<RegistryFriendlyByteBuf, ClientMenuApp.Builder>> decoder
) {


    public static final StreamCodec<RegistryFriendlyByteBuf, MenuAppType<?>> STREAM_CODEC = StreamCodec.recursive(codec -> ByteBufCodecs.registry(FCRegistries.MENU_APP_TYPE_KEY));

    public static ClientMenuApp.Builder decodeApp(RegistryFriendlyByteBuf buffer) {
        return STREAM_CODEC.decode(buffer).decoder.get().decode(buffer);
    }

    public void encodeApp(RegistryFriendlyByteBuf buffer, T app) {
        STREAM_CODEC.encode(buffer, this);
        encoder.encode(buffer, app);
    }

    @Override
    public String toString() {
        return Util.getRegisteredName(FCRegistries.MENU_APP_TYPE_REGISTRY, this);
    }
}
