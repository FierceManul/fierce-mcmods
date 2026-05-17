package net.fiercemanul.fiercelive.mixins;

import net.fiercemanul.fiercelive.data.FLItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Zombie.class)
public abstract class ZombieEquipmentMixin {


    /**
     * 拦截第二个 setItemSlot 调用（铁锹）
     */
    @Redirect(
        method = "populateDefaultEquipmentSlots",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/monster/Zombie;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V",
            ordinal = 1  // 第二个 setItemSlot 调用（铁锹）
        )
    )
    private void redirectIronShovel(Zombie instance, EquipmentSlot slot, ItemStack stack, RandomSource random) {
        if (random.nextBoolean()) instance.setItemSlot(slot, new ItemStack(FLItems.IRON_DOOR.get()));
        else instance.setItemSlot(slot, new ItemStack(Items.IRON_SHOVEL));
    }
}
