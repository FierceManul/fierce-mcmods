package net.fiercemanul.fiercesource.server;

import net.minecraft.server.MinecraftServer;

import javax.annotation.Nullable;
import java.util.function.Function;

public final class ServerValueProvider {


    @Nullable
    private static ServerValueProvider instance;

    public static void init(MinecraftServer server) {
        if (instance == null) instance = new ServerValueProvider(server);
    }

    public static void kill() {
        instance = null;
    }

    public static ServerValueProvider get() {
        if (instance == null) throw new NullPointerException("Don't get ServerValueProvider out of Server");
        if (instance.server.isShutdown()) {
            instance = null;
            throw new NullPointerException("Don't get ServerValueProvider out of Server");
        }
        return instance;
    }


    public final MinecraftServer server;
    public long baseManaStorage = 10000;
    public double baseManaStoragePreLvl = 1.1;
    public final Function<Integer, Long> levelManaStorage = i -> i == 0 ? 0 : (long) (baseManaStorage * Math.pow(baseManaStoragePreLvl, i - 1));
    public long smallSoulCrystalMaxMana = levelManaStorage.apply(30);
    public long mediumSoulCrystalMaxMana = levelManaStorage.apply(60);
    public long largeSoulCrystalMaxMana = levelManaStorage.apply(90);


    private ServerValueProvider(MinecraftServer server) {
        this.server = server;
    }
}
