package net.fiercemanul.fiercelive.data.registries;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.FLCreativeModeTab;
import net.fiercemanul.fiercelive.data.FLEntities;
import net.fiercemanul.fiercelive.data.FLItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface FLRegister {


    DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FierceLive.MODID);
    DeferredRegister.Items ITEMS = DeferredRegister.createItems(FierceLive.MODID);
    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FierceLive.MODID);
    DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, FierceLive.MODID);
    DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FierceLive.MODID);
    /*DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, MODID);*/
    
    static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        FLBlocks.init();
        FLItems.init();
        FLEntities.init();
        FLCreativeModeTab.init();
        BlockBulkRegister.starRegister();

        modEventBus.addListener(FLRegister::afterRegister);
    }

    static void afterRegister(FMLCommonSetupEvent event) {
        FLDispenseItemBehavior.init();
    }

}
