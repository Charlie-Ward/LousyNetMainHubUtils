package info.charlieward.lousynetmainhubutils.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerJoinTPListener implements Listener {

    Location spawn = new Location(Bukkit.getWorld("hub"),-74,35,-7,90,0);
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.teleport(spawn);
    }
}
