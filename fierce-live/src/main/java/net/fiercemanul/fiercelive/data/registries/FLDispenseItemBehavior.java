package net.fiercemanul.fiercelive.data.registries;

import net.fiercemanul.fiercelive.data.FLItems;
import net.minecraft.world.level.block.DispenserBlock;

public interface FLDispenseItemBehavior {


    static void init() {
        DispenserBlock.registerProjectileBehavior(FLItems.GLOW_PEARL_ARROW);
    }

}
