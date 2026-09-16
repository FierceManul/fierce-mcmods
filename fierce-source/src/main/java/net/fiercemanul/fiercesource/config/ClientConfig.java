package net.fiercemanul.fiercesource.config;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.SharedConstants;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = FierceSource.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientConfig {


    private static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.IntValue MIN_SAVE_INTERVAL = CLIENT_BUILDER
            .comment("保存世界的最短间隔（秒），0为不限制，仅客户端生效")
            .translation("config.fiercesource.minSaveInterval")
            .defineInRange("minSaveInterval", 30, 0, 60);

    public static final ModConfigSpec CLIENT_SPEC = CLIENT_BUILDER.build();

    public static int minSaveInterval;

    @SubscribeEvent
    public static void onLoad(ModConfigEvent event) {
        if (event.getConfig().getSpec() != CLIENT_SPEC) return;
        minSaveInterval = MIN_SAVE_INTERVAL.get() * SharedConstants.TICKS_PER_SECOND;
    }

}
