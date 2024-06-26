package net.fiercemanul.fiercesource.capabilities;

import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

public interface IManaContainer extends IManaHandler {

    @Nullable
    default IManaHandler getManaCapability(Direction direction) {
        return getManaCapability();
    }

    @Nullable
    IManaHandler getManaCapability();

}
