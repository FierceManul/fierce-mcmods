package net.fiercemanul.fiercesource.server.level.app;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;

import java.util.List;
import java.util.WeakHashMap;

public class TestServerApp implements ServerApp {


    private static final RandomSource RANDOM_SOURCE = RandomSource.create();
    private final WeakHashMap<TestServerMenuApp, Boolean> serverMenuApps = new WeakHashMap<>();
    public int random;

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
