package info.charlieward.lousynetmainhubutils;

import info.charlieward.lousynetmainhubutils.Listeners.*;
import org.bukkit.plugin.java.JavaPlugin;
import sun.security.util.Debug;

public final class LousyNetMainHubUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("LousyNet-MainHub-Utils v." + this.getDescription().getVersion() + " has loaded.");

        getServer().getPluginManager().registerEvents(new playerJoinTPListener(), this);
        getServer().getPluginManager().registerEvents(new dropBelowYLevel5(), this);
        getServer().getPluginManager().registerEvents(new playerRespawnTPListener(), this);
        getServer().getPluginManager().registerEvents(new cancelBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new gamemodeSelector(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("LousyNet-MainHub-Utils v." + this.getDescription().getVersion() + " has been disabled.");
    }
}
