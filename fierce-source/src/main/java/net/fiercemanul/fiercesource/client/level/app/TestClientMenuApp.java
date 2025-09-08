package net.fiercemanul.fiercesource.client.level.app;

import net.fiercemanul.fiercesource.client.level.menu.ClientFierceMediaMenu;
import net.fiercemanul.fiercesource.world.level.app.DataType.IntData;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamDecoder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TestClientMenuApp implements ClientMenuApp {


    public static final StreamDecoder<RegistryFriendlyByteBuf, ClientMenuApp.Builder> DECODER =
            buf -> (menu, player, playerInventory) -> new TestClientMenuApp(
                    buf.readVarInt(), menu, player, playerInventory
            );
    private int random;
    private final ClientFierceMediaMenu menu;

    public TestClientMenuApp(int random, ClientFierceMediaMenu menu, Player player, Inventory playerInventory) {
        this.random = random;
        this.menu = menu;
        menu.random = random;
    }

    @Override
    public void handleData(Object data) {
        switch (data) {
            case IntData intData -> {
                random = intData.i();
                menu.random = random;
            }
            default -> {}
        }
    }

    @Override
    public void cleanSelf() {

    }
}
