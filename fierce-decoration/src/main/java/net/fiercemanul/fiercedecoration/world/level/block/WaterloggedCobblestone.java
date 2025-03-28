package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.SimpleCapabilityBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;



public class WaterloggedCobblestone extends SimpleCapabilityBlock {


    public static final MapCodec<WaterloggedCobblestone> CODEC = simpleCodec(WaterloggedCobblestone::new);

    public WaterloggedCobblestone(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends WaterloggedCobblestone> codec() {
        return CODEC;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("fiercedecoration.tip.water_cobble"));
    }
}
