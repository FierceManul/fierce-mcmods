package net.fiercemanul.fiercesource.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.DataFixer;
import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.config.ClientConfig;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.net.Proxy;

@Mixin(value = IntegratedServer.class, priority = 800)
public abstract class IntegratedServerSaveMixin extends MinecraftServer {


    @Unique
    private int fiercesource$lastSaveTick = -1;

    public IntegratedServerSaveMixin(
            Thread serverThread, LevelStorageSource.LevelStorageAccess storageSource,
            PackRepository packRepository, WorldStem worldStem, Proxy proxy,
            DataFixer fixerUpper, Services services,
            ChunkProgressListenerFactory progressListenerFactory
    ) {
        super(serverThread, storageSource, packRepository, worldStem, proxy, fixerUpper, services, progressListenerFactory);
    }

    @WrapOperation(
            method = "tickServer",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/server/IntegratedServer;saveEverything(ZZZ)Z")
    )
    private boolean limitSaveInterval(IntegratedServer instance, boolean suppressLog, boolean flush, boolean forced, Operation<Boolean> original) {
        int interval = ClientConfig.minSaveInterval;
        if (interval > 0) {
            int now = getTickCount();
            if (fiercesource$lastSaveTick >= 0 && now - fiercesource$lastSaveTick < interval) {
                FierceSource.LOGGER.info("存档太频繁了，给你夹掉！");
                return false;
            }
            fiercesource$lastSaveTick = now;
        }
        return original.call(instance, suppressLog, flush, forced);
    }

}
