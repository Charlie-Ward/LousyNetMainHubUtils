package info.charlieward.lousynetmainhubutils.Listeners;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class gamemodeSelectorListener implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD  + "" + ChatColor.BOLD + "LousyNet Game Selector")){
            Material item = Objects.requireNonNull(event.getCurrentItem()).getType();
            switch (item) {
                case CRAFTING_TABLE:
                    player.sendMessage(ChatColor.BLUE + "[LousyNet] " + ChatColor.WHITE + "Sending you to survival");
                    LousyNetMainHubUtils.sendPlayerToServer(player, "survival");
                    break;
                case BEDROCK:
                    player.sendMessage(ChatColor.BLUE + "[LousyNet] " + ChatColor.WHITE + "Server not currently up");
            }
        }
    }
}
