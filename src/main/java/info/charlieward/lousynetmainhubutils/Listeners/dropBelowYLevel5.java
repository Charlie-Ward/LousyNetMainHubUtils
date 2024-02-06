package info.charlieward.lousynetmainhubutils.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class dropBelowYLevel5 implements Listener{
    Location spawn = new Location(Bukkit.getWorld("hub"),-74.5,35,-7.5,90,0);
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(event.getTo().getBlockY() <= 5) {
            Player player = event.getPlayer();
            if (!player.hasPermission("lousyNetMainHubUtils.goBelowY5")) {
                player.teleport(spawn);
                player.setGameMode(GameMode.ADVENTURE);
            }
        }
    }
}
