package net.fiercemanul.fiercesource.capabilities;

import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

public interface IManaContainer extends IManaStorage {

    @Nullable
    default IManaStorage getManaCapability(Direction direction) {
        return getManaCapability();
    }

    @Nullable
    IManaStorage getManaCapability();

}
