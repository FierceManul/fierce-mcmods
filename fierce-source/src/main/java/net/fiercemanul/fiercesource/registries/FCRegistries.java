package net.fiercemanul.fiercesource.registries;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FCRegistries {


    public static final DeferredRegister.Blocks FIERCE_CRAFT_BLOCK_REGISTER = DeferredRegister.createBlocks(FierceSource.FC_MODID);
    public static final DeferredRegister.Items FIERCE_CRAFT_ITEM_REGISTER = DeferredRegister.createItems(FierceSource.FC_MODID);
    public static final DeferredRegister<CreativeModeTab> FIERCE_CRAFT_CREATIVE_MODE_TAB_REGISTER = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB, FierceSource.FC_MODID);
    public static final DeferredRegister<BlockEntityType<?>> FIERCE_CRAFT_BLOCK_ENTITY_TYPE_REGISTER = DeferredRegister.create(
            Registries.BLOCK_ENTITY_TYPE, FierceSource.FC_MODID);

    public static final DeferredRegister<MenuType<?>> FIERCE_CRAFT_MENU_TYPE_REGISTER = DeferredRegister.create(
            Registries.MENU, FierceSource.FC_MODID);
    public static final DeferredRegister<ParticleType<?>> FIERCE_CRAFT_PARTICLE_TYPE_REGISTER = DeferredRegister.create(
            Registries.PARTICLE_TYPE, FierceSource.FC_MODID);
}
