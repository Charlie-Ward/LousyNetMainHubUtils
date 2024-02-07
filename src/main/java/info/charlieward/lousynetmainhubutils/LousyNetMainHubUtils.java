package info.charlieward.lousynetmainhubutils;

import info.charlieward.lousynetmainhubutils.Listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class LousyNetMainHubUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
//        System.out.println("Plugin Online");

        getServer().getPluginManager().registerEvents(new playerJoinTPListener(), this);
        getServer().getPluginManager().registerEvents(new dropBelowYLevel5(), this);
        getServer().getPluginManager().registerEvents(new playerRespawnTPListener(), this);
        getServer().getPluginManager().registerEvents(new playerDeathDropCancel(), this);
        getServer().getPluginManager().registerEvents(new cancelBlockBreak(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
