package net.fiercemanul.fiercesource.world.level.app.DataType;

import net.fiercemanul.fiercesource.registries.FCRegistries;
import net.minecraft.Util;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public record AppDataType<T extends AppData>(ResourceLocation rl, StreamCodec<? super RegistryFriendlyByteBuf, T> dataCodec) {


    public static final StreamCodec<RegistryFriendlyByteBuf, AppDataType<?>> STREAM_CODEC = StreamCodec.recursive(codec -> ByteBufCodecs.registry(FCRegistries.APP_DATA_TYPE_KEY));

    public static AppData decodeData(RegistryFriendlyByteBuf buffer) {
        return STREAM_CODEC.decode(buffer).dataCodec().decode(buffer);
    }

    public void encodeData(RegistryFriendlyByteBuf buf, T object) {
        STREAM_CODEC.encode(buf, this);
        dataCodec().encode(buf, object);
    }

    @Override
    public String toString() {
        return Util.getRegisteredName(FCRegistries.APP_DATA_TYPE_REGISTRY, this);
    }

}
