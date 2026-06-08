package net.fiercemanul.fiercelive.data;

import net.fiercemanul.fiercesource.world.level.block.InfiniteProjectileDispenseBehavior;
import net.minecraft.world.level.block.DispenserBlock;

public interface FLDispenseItemBehaviors {


    static void init() {
        DispenserBlock.registerProjectileBehavior(FLItems.GLOW_PEARL_ARROW);
        DispenserBlock.registerBehavior(FLItems.INFINITE_SNOWBALL, new InfiniteProjectileDispenseBehavior(FLItems.INFINITE_SNOWBALL.asItem()));
    }

}
