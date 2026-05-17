package net.fiercemanul.fiercelive.client;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

@EventBusSubscriber(modid = FierceLive.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientGameEvents {


    @SubscribeEvent
    public static void joinedServer(ClientPlayerNetworkEvent.LoggingIn event) {
        if (!event.getConnection().isMemoryConnection())
            FLItems.GLASS_KNIFE.get().applySilkTouch(event.getPlayer().registryAccess());
    }

}
