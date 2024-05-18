package net.fiercemanul.fiercesource.world.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.TierSortingRegistry;

public class CrowbarItem extends Item implements Vanishable {


    public final boolean is_netherite;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;


    public CrowbarItem(Properties pProperties, boolean is_netherite) {
        super(pProperties.durability(is_netherite ? 4000 : 500));
        this.is_netherite = is_netherite;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", is_netherite ? 6 : 4, AttributeModifier.Operation.ADDITION)
        );
        this.defaultModifiers = builder.build();
    }

    @Override
    public boolean isFireResistant() {
        return is_netherite;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(2, pAttacker, livingEntity -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        //看event，这里备用。
        return 4.0F;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState pBlock) {
        if (TierSortingRegistry.isTierSorted(Tiers.DIAMOND)) {
            return TierSortingRegistry.isCorrectTierForDrops(Tiers.DIAMOND, pBlock);
        }
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
            pStack.hurtAndBreak(1, pEntityLiving, livingEntity -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }
        return true;
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return is_netherite ? 15 : 14;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return is_netherite ? 15 : 14;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.equals(Enchantments.SILK_TOUCH) || enchantment.equals(Enchantments.MENDING) || enchantment.equals(Enchantments.UNBREAKING);
    }
}
