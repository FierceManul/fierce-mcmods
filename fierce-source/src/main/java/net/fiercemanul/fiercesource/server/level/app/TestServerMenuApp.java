package net.fiercemanul.fiercesource.server.level.app;

import net.fiercemanul.fiercesource.data.registries.MenuAppTypes;
import net.fiercemanul.fiercesource.server.level.menu.ServerFierceMediaMenu;
import net.fiercemanul.fiercesource.world.level.app.DataType.IntData;
import net.fiercemanul.fiercesource.world.level.app.TestMenuApp;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamEncoder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class TestServerMenuApp extends TestMenuApp implements ServerMenuApp {


    public static final StreamEncoder<RegistryFriendlyByteBuf, TestServerMenuApp> ENCODER = (buffer, app) -> buffer.writeVarInt(app.app.random);
    private final TestServerApp app;
    private final ServerFierceMediaMenu menu;
    private final ServerPlayer player;
    private final Inventory playerInv;
    private boolean removed = false;


    public TestServerMenuApp(TestServerApp app, ServerFierceMediaMenu menu, ServerPlayer player, Inventory playerInventory) {
        this.app = app;
        this.menu = menu;
        this.player = player;
        this.playerInv = playerInventory;
        app.addMenuApp(this);
    }

    @Override
    public Slot[] buildHardSlots() {
        container.unpackLootTable(playerInv.player);
        return super.buildHardSlots();
    }

    protected void posRandom(int rnd) {
        menu.posMenuAppData(this, new IntData(0, rnd));
    }

    @Override
    public void handleData(Object data) {

    }

    @Override
    public void setRemove() {
        removed = true;
    }

    @Override
    public boolean isRemoved() {
        return removed;
    }

    @Override
    public void encode(RegistryFriendlyByteBuf buf) {
        MenuAppTypes.TEST.get().encodeApp(buf, this);
    }
}
