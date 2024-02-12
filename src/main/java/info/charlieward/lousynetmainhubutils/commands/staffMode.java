package info.charlieward.lousynetmainhubutils.commands;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class staffMode implements CommandExecutor {

    LousyNetMainHubUtils plugin;

    public staffMode(LousyNetMainHubUtils plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("LousyNetMainHubUtils.staffMode")) {
                if(!(plugin.staffModeList.contains(player.getDisplayName()))) {
                    plugin.staffModeList.add(player.getDisplayName());
                    player.sendMessage(ChatColor.GREEN + "[LousyNet] " + ChatColor.WHITE + "Staff mode has been enabled");
                } else {
                    plugin.staffModeList.remove(player.getDisplayName());
                    player.sendMessage(ChatColor.GREEN + "[LousyNet] " + ChatColor.WHITE + "Staff mode has been disabled");
                }
            } else{
                player.sendMessage(ChatColor.GREEN + "[LousyNet] " + ChatColor.WHITE + "You do not have the correct permission to run this command");
            }
        }
        return true;
    }
}
