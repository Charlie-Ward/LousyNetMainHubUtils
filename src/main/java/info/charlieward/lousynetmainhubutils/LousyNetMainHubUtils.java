package info.charlieward.lousynetmainhubutils;

import info.charlieward.lousynetmainhubutils.Listeners.playerJoinTPListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class LousyNetMainHubUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin Online");

        getServer().getPluginManager().registerEvents(new playerJoinTPListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
