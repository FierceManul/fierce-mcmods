package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.data.FLEnchantments;
import net.fiercemanul.fiercelive.data.tags.FLEnchantmentTags;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.ApplyMobEffect;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;

public interface EnchantmentGen {


    static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> itemHolder = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchantmentHolder = context.lookup(Registries.ENCHANTMENT);

        context.register(
                FLEnchantments.POISON_ASPECT,
                Enchantment.enchantment(
                                   Enchantment.definition(
                                           itemHolder.getOrThrow(ItemTags.FIRE_ASPECT_ENCHANTABLE),
                                           itemHolder.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                           2, 2,
                                           Enchantment.dynamicCost(10, 20),
                                           Enchantment.dynamicCost(60, 20),
                                           4,
                                           EquipmentSlotGroup.MAINHAND
                                   )
                           )
                           .withEffect(
                                   EnchantmentEffectComponents.POST_ATTACK,
                                   EnchantmentTarget.ATTACKER,
                                   EnchantmentTarget.VICTIM,
                                   new ApplyMobEffect(
                                           HolderSet.direct(MobEffects.POISON),
                                           LevelBasedValue.constant(3F),
                                           LevelBasedValue.perLevel(3F, 3F),
                                           LevelBasedValue.constant(0F),
                                           LevelBasedValue.perLevel(0F, 1F)
                                   ),
                                   DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().isDirect(true))
                           )
                           .exclusiveWith(enchantmentHolder.getOrThrow(FLEnchantmentTags.ASPECT))
                           .build(FLEnchantments.POISON_ASPECT.location())
        );
    }

}
