package net.fiercemanul.fiercelive.world.item;

import net.fiercemanul.fiercelive.data.tags.FLBlockTags;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class GlassKnifeItem extends DiggerItem {


    public static final Properties DEFAULT_PROPERTIES = new Properties();


    public GlassKnifeItem(Properties properties) {
        super(Tiers.DIAMOND, FLBlockTags.MINEABLE_WITH_GLASS_KNIFE, properties);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return enchantment.is(Enchantments.EFFICIENCY)
                || enchantment.is(Enchantments.UNBREAKING)
                || enchantment.is(Enchantments.MENDING)
                || enchantment.is(Enchantments.SILK_TOUCH);
    }

    @Override
    public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
        return supportsEnchantment(stack, enchantment);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        itemStack = itemStack.copy();
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null && server.isRunning()) for (ServerLevel level : server.getAllLevels()) {
            itemStack.hurtAndBreak(1, level, CommonHooks.getCraftingPlayer(), item -> {});
            break;
        }
        return itemStack;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    public void applySilkTouch(RegistryAccess registryAccess) {
        registryAccess.registry(Registries.ENCHANTMENT).flatMap(enchantments -> enchantments.getHolder(Enchantments.SILK_TOUCH)).ifPresent(holder -> {
            var cmp = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
            cmp.set(holder, 1);
            DEFAULT_PROPERTIES.component(DataComponents.ENCHANTMENTS, cmp.toImmutable());
            components = DEFAULT_PROPERTIES.buildAndValidateComponents();
        });
    }

}
