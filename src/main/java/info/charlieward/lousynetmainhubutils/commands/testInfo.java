package info.charlieward.lousynetmainhubutils.commands;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class testInfo implements CommandExecutor {

    LousyNetMainHubUtils plugin;

    public testInfo(LousyNetMainHubUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        System.out.println("Player count info");
        System.out.println(plugin.jedis.get("HubPlayerCount"));
        return true;
    }
}
