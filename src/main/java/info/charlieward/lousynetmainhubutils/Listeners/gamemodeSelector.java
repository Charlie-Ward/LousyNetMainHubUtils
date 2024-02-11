package info.charlieward.lousynetmainhubutils.Listeners;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class gamemodeSelector implements Listener {

    LousyNetMainHubUtils plugin;

    public gamemodeSelector(LousyNetMainHubUtils plugin){
        this.plugin = plugin;
    }

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
        Inventory gameSelector = Bukkit.createInventory(player, 54, ChatColor.GREEN + "LousyNet Game Selector");


        ItemStack Filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fMeta = Filler.getItemMeta();
        fMeta.setDisplayName(ChatColor.GRAY + "");
        Filler.setItemMeta(fMeta);

        gameSelector.setItem(0, Filler);
        gameSelector.setItem(1, Filler);
        gameSelector.setItem(2, Filler);
        gameSelector.setItem(3, Filler);

        UUID lousyBoi = UUID.fromString("40067669-5479-4f8c-aa87-d8ba9f1a65d6");
        OfflinePlayer lousyBoiPlayer = Bukkit.getOfflinePlayer(lousyBoi);
        ItemStack lousyHead = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta meta = (SkullMeta) lousyHead.getItemMeta();
        meta.setOwner("LousyBoi");
        lousyHead.setItemMeta(meta);

        gameSelector.setItem(4, lousyHead);

        player.openInventory(gameSelector);
    }
}
