package net.fiercemanul.fiercesource.world.level.block.entity;

import net.fiercemanul.fiercesource.registries.FSBlockEntityTypes;
import net.fiercemanul.fiercesource.registries.MenuAppTypes;
import net.fiercemanul.fiercesource.server.level.app.ServerApp;
import net.fiercemanul.fiercesource.server.level.app.ServerAppHolder;
import net.fiercemanul.fiercesource.server.level.app.ServerMenuApp;
import net.fiercemanul.fiercesource.server.level.menu.ServerFierceMediaMenu;
import net.fiercemanul.fiercesource.world.level.app.DataType.IntData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamEncoder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.WeakHashMap;


public class TestBlockEntity extends BlockEntity implements ServerAppHolder {


    private static final RandomSource RANDOM_SOURCE = RandomSource.create();
    private long mana;
    private int fe;
    @Nullable
    private TestServerApp serverApp;

    public TestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(FSBlockEntityTypes.TEST_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        mana = tag.getLong("Mana");
        fe = tag.getInt("Fe");
        if (serverApp != null) serverApp.random = tag.getInt("Random");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putLong("Mana", mana);
        tag.putInt("Fe", fe);
        if (serverApp != null) tag.putInt("Random", serverApp.random);
    }

    public void serverTick(ServerLevel level, BlockPos pos, BlockState state) {
        if (serverApp == null) serverApp = new TestServerApp();
        serverApp.serverTick(level);
    }

    @Override
    public ServerApp[] getServerApp() {
        return new ServerApp[]{serverApp};
    }

    private static class TestServerApp implements ServerApp {


        private final WeakHashMap<TestServerMenuApp, Boolean> serverMenuApps = new WeakHashMap<>();
        private int random;

        @Override
        public void serverTick(ServerLevel level) {
            if (level.getServer().getTickCount() % 20 == 0) {
                random = RANDOM_SOURCE.nextInt(Integer.MAX_VALUE);
                sendRandom(random);
            }
        }

        @Override
        public List<ServerMenuApp.Builder> getMenuAppConstructors() {
            return List.of(
                    (menu, player, playerInventory) -> new TestServerMenuApp(this, menu, player, playerInventory)
            );
        }

        public void addMenuApp(TestServerMenuApp menuApp) {
            serverMenuApps.put(menuApp, Boolean.TRUE);
        }

        private void sendRandom(int random) {
            serverMenuApps.keySet().forEach(menuApp -> menuApp.posRandom(random));
        }

        public void removeMenuApp(TestServerMenuApp app) {
            serverMenuApps.remove(app);
        }

    }

    public static class TestServerMenuApp implements ServerMenuApp {


        public static final StreamEncoder<RegistryFriendlyByteBuf, TestServerMenuApp> ENCODER = (buffer, app) -> buffer.writeVarInt(app.app.random);
        private final TestServerApp app;
        private final ServerFierceMediaMenu menu;
        private final ServerPlayer player;
        private final Inventory playerInv;

        public TestServerMenuApp(TestServerApp app, ServerFierceMediaMenu menu, ServerPlayer player, Inventory playerInventory) {
            this.app = app;
            this.menu = menu;
            this.player = player;
            this.playerInv = playerInventory;
            app.addMenuApp(this);
        }

        private void posRandom(int rnd) {
            menu.posMenuAppData(this, new IntData(0, rnd));
        }

        @Override
        public void handleData(Object data) {

        }

        @Override
        public void cleanSelf() {
            app.removeMenuApp(this);
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf) {
            MenuAppTypes.TEST.get().encodeApp(buf, this);
        }
    }

}
