package net.fiercemanul.fiercelive.data.registries;

import net.fiercemanul.fiercelive.FierceLive;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class FLRegister {


    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FierceLive.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FierceLive.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FierceLive.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, FierceLive.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FierceLive.MODID);
    /*public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, MODID);*/

    private FLRegister() {}

}
