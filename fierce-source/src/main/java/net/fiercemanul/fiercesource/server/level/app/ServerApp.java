package net.fiercemanul.fiercesource.server.level.app;

import net.fiercemanul.fiercesource.world.level.app.App;
import net.minecraft.server.level.ServerLevel;

public interface ServerApp extends App, ServerMenuAppHolder {


    void serverTick(ServerLevel level);

}
