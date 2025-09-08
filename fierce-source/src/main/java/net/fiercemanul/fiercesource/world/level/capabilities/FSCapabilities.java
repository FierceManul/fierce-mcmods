package net.fiercemanul.fiercesource.world.level.capabilities;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import org.jetbrains.annotations.Nullable;

public final class FSCapabilities {

    public static final BlockCapability<IManaHandler, @Nullable Direction> BLOCK_MANA_CAP = BlockCapability.createSided(ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "mana_handler"), IManaHandler.class);
    public static final EntityCapability<IManaHandler, Void> ENTITY_MANA_CAP = EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "mana_handler"), IManaHandler.class);
    public static final ItemCapability<IManaHandler, Void> ITEM_MANA_CAP = ItemCapability.createVoid(ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "mana_handler"), IManaHandler.class);

    public static final BlockCapability<IItemContainerHandle, @Nullable Direction> BLOCK_ITEM_CAP = BlockCapability.createSided(ResourceLocation.fromNamespaceAndPath(FierceSource.FC_MODID, "security_item_handler"), IItemContainerHandle.class);

    private FSCapabilities() {}
}
