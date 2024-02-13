package info.charlieward.lousynetmainhubutils.Listeners;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class noItemDrops implements Listener {
    LousyNetMainHubUtils plugin;
    public noItemDrops(LousyNetMainHubUtils plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void itemDropped(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!(plugin.staffModeList.contains(player.getDisplayName()))) {
            event.setCancelled(true);
        }
    }

}
