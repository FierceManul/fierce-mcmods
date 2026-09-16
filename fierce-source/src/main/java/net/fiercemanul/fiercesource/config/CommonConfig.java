package net.fiercemanul.fiercesource.config;

import net.fiercemanul.fiercesource.FierceSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = FierceSource.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CommonConfig {


    private static final ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.BooleanValue FORCE_MANA_CONSUME = COMMON_BUILDER
            .comment("是否无主模块时依然消耗灵能")
            .translation("config.fiercesource.forceManaConsume")
            .define("forceManaConsume", false);
    public static final ModConfigSpec.BooleanValue THROWN_BLOCK_SAVE_MODE = COMMON_BUILDER
            .comment("如果投掷方块引起崩溃可以开启，优先使用黑名单tag#fiercecraft:thrown_block/ban")
            .translation("config.fiercesource.thrownBlockSaveMode")
            .define("thrownBlockSaveMode", false);
    public static final ModConfigSpec.BooleanValue FORCE_VANILLA_CREATIVE_MODE_TAB_FRONT = COMMON_BUILDER
            .comment("是否强制原版创造模式库存标签在首页")
            .translation("config.fiercesource.forceVanillaCreativeModeTabFront")
            .define("forceVanillaCreativeModeTabFront", false);

    public static final ModConfigSpec COMMON_SPEC = COMMON_BUILDER.build();

    public static boolean forceManaConsume;
    public static boolean thrownBlockSaveMode;
    public static boolean forceVanillaCreativeModeTabFront;

    @SubscribeEvent
    public static void onLoad(ModConfigEvent event) {
        if (event.getConfig().getSpec() != COMMON_SPEC) return;
        forceManaConsume = FORCE_MANA_CONSUME.get();
        thrownBlockSaveMode = THROWN_BLOCK_SAVE_MODE.get();
        forceVanillaCreativeModeTabFront = FORCE_VANILLA_CREATIVE_MODE_TAB_FRONT.get();
    }

}
