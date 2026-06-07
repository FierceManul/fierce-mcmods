package net.fiercemanul.fiercesource.world.level.block;

import net.fiercemanul.fiercesource.world.entity.ThrownBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface ThrowableBlock {


    BlockState getThrowedBlockState(BlockState state);

    BlockState getHitOnBlockState(Level level, BlockPos pos, Direction facing, BlockState state);

    default float getBaseDamage(BlockState state) {
        return ThrownBlock.DEFAULT_DAMAGE;
    }

    default double getGravity(BlockState state) {
        return ThrownBlock.DEFAULT_GRAVITY;
    }

    default ThrownBlock.FacingAnimation getFacingAnimation(BlockState state) {
        return ThrownBlock.FacingAnimation.NONE;
    }

}
