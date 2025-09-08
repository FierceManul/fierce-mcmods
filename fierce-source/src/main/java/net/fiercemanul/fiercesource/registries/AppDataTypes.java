package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.world.level.app.DataType.AppData;
import net.fiercemanul.fiercesource.world.level.app.DataType.AppDataType;
import net.fiercemanul.fiercesource.world.level.app.DataType.IntData;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;

import static net.fiercemanul.fiercesource.registries.FCRegistries.APP_DATA_TYPES;

public class AppDataTypes {


    public static final DeferredHolder<AppDataType<?>, AppDataType<IntData>> INT = register("int", IntData.STREAM_CODEC);

    public static void init() {}

    private static <T extends AppData> DeferredHolder<AppDataType<?>, AppDataType<T>> register(String path, StreamCodec<? super RegistryFriendlyByteBuf, T> dataCodec) {
        return APP_DATA_TYPES.register(path, rl -> new AppDataType<>(rl, dataCodec));
    }

}
