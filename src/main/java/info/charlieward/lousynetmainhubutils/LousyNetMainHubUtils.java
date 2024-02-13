package info.charlieward.lousynetmainhubutils;

import info.charlieward.lousynetmainhubutils.Listeners.*;
import info.charlieward.lousynetmainhubutils.commands.staffMode;
import info.charlieward.lousynetmainhubutils.commands.testInfo;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;

public final class LousyNetMainHubUtils extends JavaPlugin {

    public ArrayList<String> staffModeList = new ArrayList<String>();

    private static LousyNetMainHubUtils plugin;

    public Jedis jedis = new Jedis();

    @Override
    public void onEnable() {

        plugin = this;

        getLogger().info("LousyNet-MainHub-Utils v." + this.getDescription().getVersion() + " has loaded.");

        getCommand("staffMode").setExecutor(new staffMode(this));
        getCommand("testInfo").setExecutor(new testInfo(this));

        getServer().getPluginManager().registerEvents(new playerJoinTPListener(this), this);
        getServer().getPluginManager().registerEvents(new dropBelowYLevel5(this), this);
        getServer().getPluginManager().registerEvents(new playerRespawnTPListener(), this);
        getServer().getPluginManager().registerEvents(new cancelBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new gamemodeSelector(this), this);
        getServer().getPluginManager().registerEvents(new noItemMove(this), this);

        jedis.set("hubPlayerCount", "1");

    }

    @Override
    public void onDisable() {
        getLogger().info("LousyNet-MainHub-Utils v." + this.getDescription().getVersion() + " has been disabled.");
    }

    public static LousyNetMainHubUtils getPlugin() {
        return plugin;
    }
}
