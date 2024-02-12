package info.charlieward.lousynetmainhubutils;

import info.charlieward.lousynetmainhubutils.Listeners.*;
import info.charlieward.lousynetmainhubutils.commands.staffMode;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class LousyNetMainHubUtils extends JavaPlugin {

    public ArrayList<String> staffModeList = new ArrayList<String>();

    private static LousyNetMainHubUtils plugin;

    @Override
    public void onEnable() {

        plugin = this;

        getLogger().info("LousyNet-MainHub-Utils v." + this.getDescription().getVersion() + " has loaded.");

        getCommand("staffMode").setExecutor(new staffMode(this));

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
