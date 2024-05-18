package net.fiercemanul.fiercedecoration.server.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fiercemanul.fiercedecoration.world.entity.Seat;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

public class SitCommand {
    public static void register(CommandDispatcher<CommandSourceStack> pDispatcher) {
        pDispatcher.register(Commands.literal("sit").executes(context -> sit(context.getSource())));
    }

    private static int sit(CommandSourceStack pSource) {
        if (pSource.getEntity() instanceof Player player) {
            Seat seat = new Seat(pSource.getLevel(), player.getX(), player.getY(), player.getZ());
            if (pSource.getLevel().addFreshEntity(seat) && player.startRiding(seat)) return 1;
        }
        return 0;
    }
}
