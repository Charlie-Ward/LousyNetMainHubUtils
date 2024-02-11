package info.charlieward.lousynetmainhubutils.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class noItemMove implements Listener {

    @EventHandler
    public void playerClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
