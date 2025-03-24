package net.fiercemanul.fiercedecoration.mixins;

import net.fiercemanul.fiercedecoration.world.level.block.FDBlocks;
import net.fiercemanul.fiercesource.FierceSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Block.class)
public abstract class BlockMixin extends BlockBehaviour implements ItemLike, IBlockExtension {


    public BlockMixin(Properties properties) {
        super(properties);
    }


    @Inject(method = "shouldRenderFace", at = @At("HEAD"), cancellable = true)
    private static void shouldRenderFace(BlockState state, BlockGetter level, BlockPos offset, Direction face, BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
        if (state.is(FDBlocks.STAR_BLOCK)) {
            FierceSource.LOGGER.info("test");
            ci.setReturnValue(false);
        }
    }

}