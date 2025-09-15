package net.fiercemanul.fiercesource;

import com.mojang.logging.LogUtils;
import net.fiercemanul.fiercesource.network.protocol.game.FierceMenuData;
import net.fiercemanul.fiercesource.registries.FCRegistries;
import net.fiercemanul.fiercesource.registries.FSBlocksAndItems;
import net.fiercemanul.fiercesource.world.level.capabilities.FSCapabilities;
import net.fiercemanul.fiercesource.world.level.capabilities.InfiniteManaContainer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

@Mod(FierceSource.MODID)
public class FierceSource {


    public static final String MODID = "fiercesource";
    public static final String FC_MODID = "fiercecraft";
    public static final Logger LOGGER = LogUtils.getLogger();


    public FierceSource(IEventBus modEventBus, ModContainer modContainer) {

        FCRegistries.initRegistries(modEventBus, modContainer);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (FierceSource) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the commonSetup method for modloading
        modEventBus.addListener(FCRegistries::registerRegistries);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerCapabilitiesEvent);
        modEventBus.addListener(this::registerPayloadHandlersEvent);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void registerCapabilitiesEvent(RegisterCapabilitiesEvent event) {
        event.registerBlock(
                FSCapabilities.BLOCK_MANA_CAP,
                (level, pos, state, blockEntity, context) -> InfiniteManaContainer.INSTANCE,
                FSBlocksAndItems.CREATIVE_MANA_BLOCK.getBlock()
        );
    }

    private void registerPayloadHandlersEvent(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(FierceMenuData.TYPE, FierceMenuData.STREAM_CODEC, FierceMenuData.HANDLER);
    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        // Do something when the server started
        LOGGER.info("HELLO from server started");
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onDig(PlayerEvent.BreakSpeed breakSpeedEvent) {
        Player player = breakSpeedEvent.getEntity();
        //
        //TODO:冒险维度预留
        //
    }


}
