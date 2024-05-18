package net.fiercemanul.fiercesource.capabilities;

import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import org.jetbrains.annotations.Nullable;

public final class ManaCapabilities {

    public static final BlockCapability<IManaStorage, @Nullable Direction> BLOCK = BlockCapability.createSided(new ResourceLocation(FierceSource.FC_MODID, "mana"), IManaStorage.class);
    public static final EntityCapability<IManaStorage, Void> ENTITY = EntityCapability.createVoid(new ResourceLocation(FierceSource.FC_MODID, "mana"), IManaStorage.class);
    public static final ItemCapability<IManaStorage, Void> ITEM = ItemCapability.createVoid(new ResourceLocation(FierceSource.FC_MODID, "mana"), IManaStorage.class);

    private ManaCapabilities() {}
}
