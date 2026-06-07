package net.fiercemanul.fiercesource.world.item;

import net.fiercemanul.fiercesource.world.entity.ThrownBlock;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TestItem extends Item {


    public TestItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (level instanceof ServerLevel serverLevel) {
            testThrowBlock(serverLevel, player);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide);
    }

    private void testThrowBlock(ServerLevel level, Player player) {
        ThrownBlock thrownBlock = ThrownBlock.rotationThrowFromItem(
                player.getItemInHand(InteractionHand.OFF_HAND).copyWithCount(1),
                level,
                player,
                player.getXRot(),
                player.getYRot(),
                0, -0.1, 0.25, 1, 1
        );
        level.addFreshEntity(thrownBlock);
    }

}
