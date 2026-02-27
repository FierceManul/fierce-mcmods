package net.fiercemanul.fiercedecoration.client;

import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.fiercemanul.fiercedecoration.client.renderer.blockentity.StarBlockRender;
import net.fiercemanul.fiercedecoration.client.resources.model.StarBlockModel;
import net.fiercemanul.fiercedecoration.data.FDBlocks;
import net.fiercemanul.fiercedecoration.data.FDItems;
import net.fiercemanul.fiercedecoration.data.registries.BlockBulkRegister;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.Map;

@EventBusSubscriber(modid = FierceDecoration.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {


    @SubscribeEvent
    public static void registerColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register(
                (state, level, pos, tintindex) -> level != null && pos != null
                                                      ? BiomeColors.getAverageGrassColor(level, pos)
                                                      : GrassColor.getDefaultColor(),
                FDBlocks.HALF_GRASS_BLOCK.get()
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
                FDItems.HALF_GRASS_BLOCK
        );
        BlockBulkRegister.BLOCK_TINT_MAP.forEach((block, integer) -> event.register(
                (stack, tintIndex) -> integer,
                block.get().asItem()
        ));
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers register) {
        register.registerBlockEntityRenderer(FierceDecoration.STAR_BLOCK_ENTITY.get(), StarBlockRender::new);
        register.registerEntityRenderer(FierceDecoration.SEAT.get(), NoopRenderer::new);
    }

    @SubscribeEvent
    public static void modifyBakingResult(ModelEvent.ModifyBakingResult event) {
        Map<ModelResourceLocation, BakedModel> models = event.getModels();
        models.put(
                new ModelResourceLocation(FDBlocks.STAR_BLOCK.getId(), ""),
                new StarBlockModel(models.get(ModelResourceLocation.vanilla("glass", "")))
        );
        models.put(
                ModelResourceLocation.inventory(FDBlocks.STAR_BLOCK.getId()),
                new StarBlockModel(models.get(ModelResourceLocation.vanilla("glass", ModelResourceLocation.INVENTORY_VARIANT)))
        );
    }

}
