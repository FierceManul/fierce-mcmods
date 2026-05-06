package net.fiercemanul.fiercelive.data.gathers;

import net.fiercemanul.fiercelive.FierceLive;
import net.fiercemanul.fiercelive.data.FLEnchantments;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
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
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface EnchantmentGen {

    static DatapackBuiltinEntriesProvider getProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        return new DatapackBuiltinEntriesProvider(
                output,
                lookupProvider,
                getBuilder(),
                Set.of(FierceLive.MODID)
        );
    }

    static RegistrySetBuilder getBuilder() {
        return new RegistrySetBuilder().add(
                Registries.ENCHANTMENT,
                context -> {
                    HolderGetter<Item> itemHolder = context.lookup(Registries.ITEM);
                    context.register(FLEnchantments.POISON_ASPECT, Enchantment.enchantment(Enchantment.definition(
                            itemHolder.getOrThrow(ItemTags.FIRE_ASPECT_ENCHANTABLE),
                            itemHolder.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                            2, 2,
                            Enchantment.dynamicCost(10, 20),
                            Enchantment.dynamicCost(60, 20),
                            4,
                            EquipmentSlotGroup.MAINHAND
                    )).withEffect(
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
                    ).build(FLEnchantments.POISON_ASPECT.location()));
                }
        );
    }
}
