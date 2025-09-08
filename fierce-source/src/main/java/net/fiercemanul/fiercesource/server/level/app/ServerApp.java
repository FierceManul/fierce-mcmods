package net.fiercemanul.fiercesource.server.level.app;

import net.fiercemanul.fiercesource.world.level.app.App;
import net.minecraft.server.level.ServerLevel;

import java.util.List;

public interface ServerApp extends App {


    void serverTick(ServerLevel level);

    List<ServerMenuApp.Builder> getMenuAppConstructors();

}
