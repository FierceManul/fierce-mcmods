package net.fiercemanul.fiercedecoration.mixins;

import net.fiercemanul.fiercedecoration.world.level.block.FDBlocks;
import net.fiercemanul.fiercedecoration.world.level.block.entity.ClientStarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;


@Mixin(Block.class)
public abstract class BlockMixin {


    @Inject(method = "shouldRenderFace", at = @At("RETURN"))
    private static void shouldRenderFace(BlockState state, BlockGetter level, BlockPos pos, Direction face, BlockPos offset, CallbackInfoReturnable<Boolean> ci) {
        //原方法的pos和offset反混淆命名是反的，这里的是对的。
        if (state.is(FDBlocks.STAR_BLOCK) && Minecraft.getInstance().level != null) {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            BlockPos immutablePos = pos.immutable();
            Map<BlockPos, BlockEntity> blockEntities = clientLevel.getChunkAt(immutablePos).getBlockEntities();
            BlockEntity blockEntity = blockEntities.get(immutablePos);
            if (blockEntity instanceof ClientStarBlockEntity starBlock) {
                starBlock.updateFace(face, ci.getReturnValue());
            }
            else {
                ClientStarBlockEntity starBlockEntity = new ClientStarBlockEntity(immutablePos, state);
                starBlockEntity.setLevel(clientLevel);
                starBlockEntity.updateFace(face, ci.getReturnValue());
                blockEntities.put(immutablePos, starBlockEntity);
            }
        }
    }

}