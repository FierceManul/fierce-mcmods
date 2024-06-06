package net.fiercemanul.fiercedecoration.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class FakeBlockEntityItem extends BlockItem {


    public FakeBlockEntityItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTootipComponents, TooltipFlag pTooltipFlag) {
        pTootipComponents.add(Component.translatable("fiercedecoration.tip.no_more_be").withStyle(ChatFormatting.DARK_PURPLE));
        super.appendHoverText(pStack, pContext, pTootipComponents, pTooltipFlag);
    }
}
