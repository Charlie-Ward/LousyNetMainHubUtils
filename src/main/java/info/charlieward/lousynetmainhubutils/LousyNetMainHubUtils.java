package info.charlieward.lousynetmainhubutils;

import info.charlieward.lousynetmainhubutils.Listeners.*;
import info.charlieward.lousynetmainhubutils.commands.staffMode;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

public final class LousyNetMainHubUtils extends JavaPlugin {

    public ArrayList<String> staffModeList = new ArrayList<String>();

    private static LousyNetMainHubUtils plugin;

    public Jedis jedis = new Jedis();

    @Override
    public void onEnable() {

        plugin = this;

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        getLogger().info("LousyNet-MainHub-Utils v." + this.getDescription().getVersion() + " has loaded.");

        getCommand("staffMode").setExecutor(new staffMode(this));

        getServer().getPluginManager().registerEvents(new playerJoinTPListener(this), this);
        getServer().getPluginManager().registerEvents(new dropBelowYLevel5(this), this);
        getServer().getPluginManager().registerEvents(new playerRespawnTPListener(), this);
        getServer().getPluginManager().registerEvents(new cancelBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new gamemodeSelector(this), this);
        getServer().getPluginManager().registerEvents(new noItemMove(this), this);
        getServer().getPluginManager().registerEvents(new noItemDrops(this), this);
        getServer().getPluginManager().registerEvents(new gamemodeSelectorListener(this), this);
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);

        getLogger().info("LousyNet-MainHub-Utils v." + this.getDescription().getVersion() + " has been disabled.");
    }

    public static LousyNetMainHubUtils getPlugin() {
        return plugin;
    }

    public static void sendPlayerToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(LousyNetMainHubUtils.getPlugin(), "BungeeCord", b.toByteArray());
            b.close();
            out.close();
        }
        catch (Exception e) {
            player.sendMessage(ChatColor.RED+"Error when trying to connect to "+server);
        }
    }
}
