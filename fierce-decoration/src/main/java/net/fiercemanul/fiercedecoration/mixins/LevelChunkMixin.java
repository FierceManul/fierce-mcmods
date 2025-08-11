package net.fiercemanul.fiercedecoration.mixins;

import net.fiercemanul.fiercedecoration.world.level.block.FDBlocks;
import net.fiercemanul.fiercedecoration.world.level.block.entity.ClientStarBlockEntity;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;


@Mixin(LevelChunk.class)
public abstract class LevelChunkMixin {


    @Shadow public abstract BlockState getBlockState(BlockPos pos);

    @Shadow @Final Level level;

    @Shadow public abstract Map<BlockPos, BlockEntity> getBlockEntities();

    @Shadow public abstract void addAndRegisterBlockEntity(BlockEntity blockEntity);

    @Inject(method = "getBlockEntity(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/chunk/LevelChunk$EntityCreationType;)Lnet/minecraft/world/level/block/entity/BlockEntity;", at = @At("HEAD"), cancellable = true)
    private void getBlockEntity(BlockPos pos, LevelChunk.EntityCreationType creationType, CallbackInfoReturnable<BlockEntity> ci) {
        if (level instanceof ClientLevel) {
            BlockPos immutablePos = pos.immutable();
            BlockState blockstate = getBlockState(immutablePos);
            if (blockstate.is(FDBlocks.STAR_BLOCK) && !(getBlockEntities().get(immutablePos) instanceof ClientStarBlockEntity)) {
                ClientStarBlockEntity starBlockEntity = new ClientStarBlockEntity(immutablePos, blockstate);
                addAndRegisterBlockEntity(starBlockEntity);
                ci.setReturnValue(starBlockEntity);
            }
        }
    }

}
