package info.charlieward.lousynetmainhubutils.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class playerDeathDropCancel implements Listener {

    @EventHandler
    public void onDeathDrop(PlayerDeathEvent event) {
        event.getEntity().getPlayer().getInventory().clear();
    }
}
