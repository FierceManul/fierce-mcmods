package net.fiercemanul.fiercelive.world.item;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class MeteorHammerItem extends SimpleTieredWeaponItem {


    private static final int COOLDOWN_TICKS = 40; // 2秒 = 40 tick
    private static final float SWEEP_DAMAGE_RATIO = 1.0F; // 横扫伤害比例：100%

    public MeteorHammerItem(Tier tier, Properties properties, float attackDamage, float attackSpeed) {
        super(tier, properties.attributes(SwordItem.createAttributes(tier, attackDamage, attackSpeed)));
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
        return enchantment.value().isSupportedItem(stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        // 横扫条件：站稳 && 攻击>90%
        if (player.onGround() && player.getAttackStrengthScale(0.0F) > 0.9F) {
            if (level instanceof ServerLevel serverLevel) {
                performSweepAttack(serverLevel, player, stack);
                player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
                stack.hurtAndBreak(2, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                player.resetAttackStrengthTicker();
                return InteractionResultHolder.consume(stack);
            }
            else {
                player.resetAttackStrengthTicker();
                return InteractionResultHolder.success(stack);
            }
        }
        return InteractionResultHolder.fail(stack);
    }
    
    private void performSweepAttack(ServerLevel level, Player player, ItemStack stack) {
            
        float sweepDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * SWEEP_DAMAGE_RATIO;
        double interactionRange = player.entityInteractionRange();
            
        // 获取玩家朝向向量（水平面）
        Vec3 lookVector = player.getLookAngle();
        Vec3 horizontalLook = new Vec3(lookVector.x, 0, lookVector.z).normalize();
            
        // 计算扇形区域的cos值（120度 = ±60度，cos(60°) = 0.5）
        float cosHalfAngle = 0.5F;
                
        // 获取范围内的所有实体
        AABB searchBox = player.getBoundingBox().inflate(interactionRange, 0.25, interactionRange);
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, searchBox, 
            entity -> {
                if (entity == player) return false;
                if (!entity.isAlive() || entity.isAlliedTo(player)) return false;
                if (entity instanceof ArmorStand stand && stand.isMarker()) return false;
                if (entity.distanceToSqr(player) > Mth.square(interactionRange)) return false;
                        
                // 计算从玩家指向目标的向量（水平面）
                Vec3 toEntity = new Vec3(entity.getX() - player.getX(), 0, entity.getZ() - player.getZ()).normalize();
                    
                // 使用点积计算夹角余弦值
                double dotProduct = horizontalLook.dot(toEntity);
                    
                // 检查是否在120度扇形范围内（cos值 >= cos(60°)）
                return dotProduct >= cosHalfAngle;
            });

        DamageSource damageSource = player.damageSources().playerAttack(player);
                
        for (LivingEntity target : targets) {
            // 计算击退方向（从玩家指向目标）
            double dx = target.getX() - player.getX();
            double dz = target.getZ() - player.getZ();
            double distance = Math.sqrt(dx * dx + dz * dz);

            target.knockback(0.4F, dx / distance, dz / distance);
            target.hurt(damageSource, EnchantmentHelper.modifyDamage(level, stack, target, damageSource, sweepDamage));
            EnchantmentHelper.doPostAttackEffects(level, target, damageSource);
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.0F, 1.0F);
        player.sweepAttack();
    }

}
