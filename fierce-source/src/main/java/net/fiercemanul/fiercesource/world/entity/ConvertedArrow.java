package net.fiercemanul.fiercesource.world.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class ConvertedArrow extends AbstractArrow {


    public ConvertedArrow(EntityType<? extends ConvertedArrow> entityType, Level level) {
        super(entityType, level);
    }

    public ConvertedArrow(
            EntityType<? extends AbstractArrow> entityType, double x, double y, double z, Level level, ItemStack pickupItemStack,
            @Nullable ItemStack firedFromWeapon
    ) {
        super(entityType, x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    public ConvertedArrow(
            EntityType<? extends AbstractArrow> entityType, LivingEntity owner, Level level, ItemStack pickupItemStack,
            @Nullable ItemStack firedFromWeapon
    ) {
        super(entityType, owner, level, pickupItemStack, firedFromWeapon);
    }

    protected abstract void doConversion();

    @Override
    public void tick() {
        if (!level().isClientSide) {
            doConversion();
            discard();
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemStack.EMPTY;
    }

}
