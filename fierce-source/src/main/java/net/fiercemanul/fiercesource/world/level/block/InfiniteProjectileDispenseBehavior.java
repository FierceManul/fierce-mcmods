package net.fiercemanul.fiercesource.world.level.block;

import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InfiniteProjectileDispenseBehavior extends ProjectileDispenseBehavior {


    public InfiniteProjectileDispenseBehavior(Item projectile) {
        super(projectile);
    }

    @Override
    public ItemStack execute(BlockSource blockSource, ItemStack item) {
        super.execute(blockSource, item.copy());
        return item;
    }
}
