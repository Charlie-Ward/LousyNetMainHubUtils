package info.charlieward.lousynetmainhubutils;

import info.charlieward.lousynetmainhubutils.Listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class LousyNetMainHubUtils extends JavaPlugin {

    private static LousyNetMainHubUtils plugin;

    @Override
    public void onEnable() {

        plugin = this;

        getLogger().info("LousyNet-MainHub-Utils v." + this.getDescription().getVersion() + " has loaded.");

        getServer().getPluginManager().registerEvents(new playerJoinTPListener(), this);
        getServer().getPluginManager().registerEvents(new dropBelowYLevel5(), this);
        getServer().getPluginManager().registerEvents(new playerRespawnTPListener(), this);
        getServer().getPluginManager().registerEvents(new cancelBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new gamemodeSelector(this), this);
        getServer().getPluginManager().registerEvents(new noItemMove(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("LousyNet-MainHub-Utils v." + this.getDescription().getVersion() + " has been disabled.");
    }

    public static LousyNetMainHubUtils getPlugin() {
        return plugin;
    }
}
