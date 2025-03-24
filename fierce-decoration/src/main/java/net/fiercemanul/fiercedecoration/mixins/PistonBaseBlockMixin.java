package net.fiercemanul.fiercedecoration.mixins;

import net.fiercemanul.fiercedecoration.world.level.block.FDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PistonBaseBlock.class)
public abstract class PistonBaseBlockMixin extends DirectionalBlock {


    protected PistonBaseBlockMixin(Properties properties) {
        super(properties);
    }


    @Inject(method = "isPushable", at = @At(value = "RETURN", ordinal = 10), cancellable = true)
    private static void isPushable(
            BlockState state, Level level, BlockPos pos, Direction movementDirection, boolean allowDestroy, Direction pistonFacing,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (state.is(FDBlocks.STAR_BLOCK)) cir.setReturnValue(true);
    }

}
