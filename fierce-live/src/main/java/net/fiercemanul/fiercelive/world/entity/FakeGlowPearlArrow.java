package net.fiercemanul.fiercelive.world.entity;

import net.fiercemanul.fiercelive.data.FLBlocks;
import net.fiercemanul.fiercelive.data.FLEntities;
import net.fiercemanul.fiercesource.data.FSEntities;
import net.fiercemanul.fiercesource.world.entity.ConvertedArrow;
import net.fiercemanul.fiercesource.world.entity.ThrownBlock;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FakeGlowPearlArrow extends ConvertedArrow {


    public FakeGlowPearlArrow(EntityType<? extends ConvertedArrow> entityType, Level level) {
        super(entityType, level);
    }

    public FakeGlowPearlArrow(LivingEntity owner, Level level, @Nullable ItemStack firedFromWeapon) {
        super(FLEntities.FAKE_GLOW_PEARL_ARROW.get(), owner, level, ItemStack.EMPTY, firedFromWeapon);
    }

    public FakeGlowPearlArrow(Level level) {
        super(FLEntities.FAKE_GLOW_PEARL_ARROW.get(), level);
    }

    @Override
    protected void doConversion() {
        ThrownBlock thrownBlock = new ThrownBlock(
                FSEntities.THROWN_BLOCK.get(), position(), level(),
                FLBlocks.GLOW_PEARL_ARROW.get().defaultBlockState()
        );
        thrownBlock.setDeltaMovement(getDeltaMovement());
        thrownBlock.setOwner(getOwner());
        level().addFreshEntity(thrownBlock);
    }

}
