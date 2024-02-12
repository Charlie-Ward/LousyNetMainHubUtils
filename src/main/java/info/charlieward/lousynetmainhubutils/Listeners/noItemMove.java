package info.charlieward.lousynetmainhubutils.Listeners;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class noItemMove implements Listener {
    LousyNetMainHubUtils plugin;

    public noItemMove(LousyNetMainHubUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!(plugin.staffModeList.contains(player.getDisplayName()))){
            event.setCancelled(true);
        }
    }
}
