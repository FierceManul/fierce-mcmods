package net.fiercemanul.fiercelive.world.item;

import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.world.entity.FakeGlowPearlArrow;
import net.fiercemanul.fiercesource.data.FSEntities;
import net.fiercemanul.fiercesource.world.entity.ThrownBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class GlowPearlArrowItem extends ArrowItem {


    public GlowPearlArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        ThrownBlock thrownBlock = new ThrownBlock(
                FSEntities.THROWN_BLOCK.get(), pos, level,
                FLBlocks.GLOW_PEARL_ARROW.get().defaultBlockState()
        );
        thrownBlock.setSpeedLimit(1);
        return thrownBlock;
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        return new FakeGlowPearlArrow(shooter, level, weapon);
    }

}
