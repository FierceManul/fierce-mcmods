package net.fiercemanul.fiercelive.client;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.FLEntities;
import net.fiercemanul.fiercelive.data.FLItems;
import net.fiercemanul.fiercelive.data.registries.BlockBulkRegister;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = FierceLive.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {


    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemPropertyFunction itemPropertyFunction = (stack, level, entity, seed)
                    -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
            ItemProperties.register(
                    FLItems.WOODEN_DOOR.get(),
                    ResourceLocation.withDefaultNamespace("blocking"),
                    itemPropertyFunction
            );
            ItemProperties.register(
                    FLItems.IRON_DOOR.get(),
                    ResourceLocation.withDefaultNamespace("blocking"),
                    itemPropertyFunction
            );
        });
    }

    @SubscribeEvent
    public static void registerColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register(
                (state, level, pos, tintindex) -> level != null && pos != null
                                                      ? BiomeColors.getAverageGrassColor(level, pos)
                                                      : GrassColor.getDefaultColor(),
                FLBlocks.HALF_GRASS_BLOCK.get()
        );
        BlockBulkRegister.BLOCK_TINT_MAP.forEach((block, integer) -> event.register(
                        (state, level, pos, tintIndex) -> integer,
                        block.get()
        ));
    }

    @SubscribeEvent
    public static void registerColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, tintIndex) -> GrassColor.getDefaultColor(),
                FLBlocks.HALF_GRASS_BLOCK
        );
        BlockBulkRegister.BLOCK_TINT_MAP.forEach((block, integer) -> event.register(
                (stack, tintIndex) -> integer,
                block.get().asItem()
        ));
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers register) {
        //register.registerBlockEntityRenderer(FierceLive.STAR_BLOCK_ENTITY.get(), StarBlockRender::new);
        register.registerEntityRenderer(FLEntities.SEAT.get(), NoopRenderer::new);
        register.registerEntityRenderer(FLEntities.FAKE_GLOW_PEARL_ARROW.get(), NoopRenderer::new);
    }

    /*@SubscribeEvent
    public static void modifyBakingResult(ModelEvent.ModifyBakingResult event) {
        Map<ModelResourceLocation, BakedModel> models = event.getModels();
        models.put(
                new ModelResourceLocation(FLBlocks.STAR_BLOCK.getId(), ""),
                new StarBlockModel(models.get(ModelResourceLocation.vanilla("glass", "")))
        );
        models.put(
                ModelResourceLocation.inventory(FLBlocks.STAR_BLOCK.getId()),
                new StarBlockModel(models.get(ModelResourceLocation.vanilla("glass", ModelResourceLocation.INVENTORY_VARIANT)))
        );
    }*/

}
