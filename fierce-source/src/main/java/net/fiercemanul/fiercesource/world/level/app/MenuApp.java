package net.fiercemanul.fiercesource.world.level.app;

import net.minecraft.world.entity.player.Player;

public interface MenuApp {


    void handleData(Object data);

    default boolean menuStillValid(Player player) {
        return true;
    }

    void cleanSelf();

}
