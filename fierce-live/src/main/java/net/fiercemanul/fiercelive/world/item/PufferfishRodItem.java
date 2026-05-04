package net.fiercemanul.fiercelive.world.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class PufferfishRodItem extends SimpleWeaponItem {


    public PufferfishRodItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide) target.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
        return true;
    }

}
