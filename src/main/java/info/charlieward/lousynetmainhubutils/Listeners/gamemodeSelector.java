package info.charlieward.lousynetmainhubutils.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class gamemodeSelector implements Listener {

    @EventHandler
    public void compassRightClick(PlayerInteractEvent event) {
         Player player = event.getPlayer();
         if(player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
            GameSelectorMenu(player);
         } else {
             return;
         }
    }

    @EventHandler
    public static void compassClickInInventory(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() != Material.COMPASS) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        GameSelectorMenu(player);
    }

    private static void GameSelectorMenu(Player player) {
        Inventory gameSelector = Bukkit.createInventory(player, 27, ChatColor.GREEN + "LousyNet Game Selector");
        player.openInventory(gameSelector);
    }
}
