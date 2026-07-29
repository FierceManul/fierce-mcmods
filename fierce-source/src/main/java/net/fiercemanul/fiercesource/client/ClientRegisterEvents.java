package net.fiercemanul.fiercesource.client;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.client.gui.screens.FierceMediaScreen;
import net.fiercemanul.fiercesource.client.level.menu.ClientFierceMediaMenu;
import net.fiercemanul.fiercesource.client.particle.SoulCrystalParticleProvider;
import net.fiercemanul.fiercesource.client.renderer.entity.ThrownBlockRenderer;
import net.fiercemanul.fiercesource.data.*;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = FierceSource.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientRegisterEvents {


    //@SubscribeEvent
    //public static void onClientSetup(FMLClientSetupEvent event) {
    //    FierceSource.LOGGER.info("HELLO FROM CLIENT SETUP");
    //    FierceSource.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    //}

    @SubscribeEvent
    public static void registerColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register(
                (state, level, pos, tintIndex) -> tintIndex == 0 ? 0xFFA0FFFF : 0xFFFF00FF,
                FSBlocks.TEST_BLOCK.get()
        );
    }

    @SubscribeEvent
    public static void registerColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, tintIndex) -> tintIndex == 0 ? 0xFFA0FFFF : 0xFF00FFFF,
                FSBlocks.TEST_BLOCK.get()
        );
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(FSParticleTypes.SOUL_CRYSTAL_PARTICLE.get(), SoulCrystalParticleProvider::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        //noinspection unchecked
        event.register((MenuType<ClientFierceMediaMenu>) FSMenuTypes.FIERCE_MEDIA_MENU.get(), FierceMediaScreen::new);
    }

    @SubscribeEvent
    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new UIStyleLoader());
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers register) {
        register.registerEntityRenderer(FSEntities.THROWN_BLOCK.get(), ThrownBlockRenderer::new);
    }

}
