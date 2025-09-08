package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.client.level.app.ClientMenuApp;
import net.fiercemanul.fiercesource.client.level.app.TestClientMenuApp;
import net.fiercemanul.fiercesource.server.level.app.ServerMenuApp;
import net.fiercemanul.fiercesource.world.level.app.MenuAppType;
import net.fiercemanul.fiercesource.world.level.block.entity.TestBlockEntity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamDecoder;
import net.minecraft.network.codec.StreamEncoder;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static net.fiercemanul.fiercesource.registries.FCRegistries.MENU_APP_TYPES;

public class MenuAppTypes {


    public static final DeferredHolder<MenuAppType<?>, MenuAppType<TestBlockEntity.TestServerMenuApp>> TEST = register(
            "test", TestBlockEntity.TestServerMenuApp.ENCODER, () -> TestClientMenuApp.DECODER);

    public static void init() {}

    private static <T extends ServerMenuApp> DeferredHolder<MenuAppType<?>, MenuAppType<T>> register(
            String path,
            StreamEncoder<RegistryFriendlyByteBuf, T> encoder,
            Supplier<StreamDecoder<RegistryFriendlyByteBuf, ClientMenuApp.Builder>> decoder
    ) {
        return MENU_APP_TYPES.register(path, rl -> new MenuAppType<>(rl, encoder, decoder));
    }
}
