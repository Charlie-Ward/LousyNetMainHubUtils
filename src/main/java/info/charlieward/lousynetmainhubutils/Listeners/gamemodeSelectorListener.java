package info.charlieward.lousynetmainhubutils.Listeners;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class gamemodeSelectorListener implements Listener {

    static LousyNetMainHubUtils plugin;

    public gamemodeSelectorListener(LousyNetMainHubUtils plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {

        String serverStatus;

        Player player = (Player) event.getWhoClicked();

        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD  + "" + ChatColor.BOLD + "LousyNet Game Selector")){
            Material item = Objects.requireNonNull(event.getCurrentItem()).getType();
            switch (item) {
                case CRAFTING_TABLE:
                    itemClicked("survival", "Survival", player);
                    break;
                case BEDROCK:
                    itemClicked("", "Creative", player);
                    break;
                case RED_BED:
                    itemClicked("", "Bedwars", player);
                    break;
                case GRASS_BLOCK:
                    itemClicked("", "Skywars", player);
                    break;
                case BOW:
                    itemClicked("", "Survival Games", player);
                    break;
                case OAK_PLANKS:
                    itemClicked("", "Build Battle", player);
                    break;
                case GOLDEN_APPLE:
                    itemClicked("", "Duels", player);
                    break;
            }
        }
    }

    public void itemClicked(String serverID, String serverName, Player player) {
        String serverStatus = plugin.jedis.get(serverID);
        if (serverStatus == null || serverStatus.equals("offline")) {
            player.sendMessage(ChatColor.BLUE + "[LousyNet] " + "This server is not currently online");
        } else {
            player.sendMessage(ChatColor.BLUE + "[LousyNet] " + ChatColor.WHITE + "Sending you to " + serverName);
            LousyNetMainHubUtils.sendPlayerToServer(player, serverID);
        }
    }
}
