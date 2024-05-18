package net.fiercemanul.fiercedecoration.world.level.block;

import com.mojang.serialization.MapCodec;
import net.fiercemanul.fiercesource.world.level.block.SimpleCapabilityBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import org.jetbrains.annotations.Nullable;

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
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("fiercedecoration.tip.water_cobble"));
    }
}
