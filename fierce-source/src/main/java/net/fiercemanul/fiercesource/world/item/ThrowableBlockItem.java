package net.fiercemanul.fiercesource.world.item;

import net.fiercemanul.fiercesource.world.entity.ThrownBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ThrowableBlockItem extends BlockItem {


    private final double throwLimit;
    private final float inaccuracy;

    public ThrowableBlockItem(Block block, Properties properties) {
        this(block, properties, 0.75, 4);
    }

    public ThrowableBlockItem(Block block, Properties properties, double throwLimit, float inaccuracy) {
        super(block, properties);
        this.throwLimit = throwLimit;
        this.inaccuracy = inaccuracy;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if (!level.isClientSide) {
            ThrownBlock thrownBlock = ThrownBlock.rotationThrowFromItem(
                    itemstack.copy(),
                    level,
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0, -0.1, throwLimit, inaccuracy, 1
            );
            level.addFreshEntity(thrownBlock);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult ir = this.place(new BlockPlaceContext(context));
        if (ir.consumesAction()) return ir;
        InteractionResult ir1 = use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
        return ir1 == InteractionResult.CONSUME ? InteractionResult.CONSUME_PARTIAL : ir1;
    }

}
