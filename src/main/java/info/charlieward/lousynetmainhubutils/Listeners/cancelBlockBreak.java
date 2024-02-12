package info.charlieward.lousynetmainhubutils.Listeners;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class cancelBlockBreak implements Listener {

    LousyNetMainHubUtils plugin;

    public cancelBlockBreak(LousyNetMainHubUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!(plugin.staffModeList.contains(player.getDisplayName()))) {
            event.setCancelled(true);
        }
    }
}
