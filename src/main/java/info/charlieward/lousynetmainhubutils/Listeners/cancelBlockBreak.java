package info.charlieward.lousynetmainhubutils.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class cancelBlockBreak implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("LousyNetMainHubUtils.BlockBreak")) {
            event.setCancelled(true);
        }
    }
}
