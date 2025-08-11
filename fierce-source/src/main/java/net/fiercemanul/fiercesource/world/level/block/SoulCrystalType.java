package net.fiercemanul.fiercesource.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public record SoulCrystalType(VoxelShape shape, IAnimateTick animateFun) {

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        animateFun.animateTick(state, level, pos, random);
    }

    public interface IAnimateTick {
        void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random);
    }
}
