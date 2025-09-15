package net.fiercemanul.fiercesource.mixins;

import net.fiercemanul.fiercesource.world.item.CrowbarItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {


    @Final
    @Shadow
    private Minecraft minecraft;
    @Shadow
    private int destroyDelay;


    @Inject(method = "lambda$startDestroyBlock$1", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;destroyBlock(Lnet/minecraft/core/BlockPos;)Z",
            shift = At.Shift.AFTER
    ))
    private void crowbarDelay (
            BlockState state, PlayerInteractEvent.LeftClickBlock event, BlockPos loc, Direction face, int sequence, CallbackInfoReturnable<Packet> cir
    ) {
        if (minecraft.player.getMainHandItem().getItem() instanceof CrowbarItem) destroyDelay = 5;
    }
}
