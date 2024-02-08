package info.charlieward.lousynetmainhubutils.Listeners;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class playerJoinTPListener implements Listener {

    Location spawn = new Location(Bukkit.getWorld("hub"),-74.5,35,-7.5,90,0);
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.teleport(spawn);
        player.getInventory().clear();
        player.sendMessage(ChatColor.GREEN + "WELCOME TO LOUSYNET!");
        if (!player.hasPermission("lousyNetMainHubUtils.enforceGamemode")){
            player.setGameMode(GameMode.ADVENTURE);
        }

        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Game Selector");

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Right Click this compass to choose which gamemode you want to go to");

        meta.setLore(lore);
        item.setItemMeta(meta);
        Inventory inventory = player.getInventory();
        inventory.setItem(4, item);

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta2 = filler.getItemMeta();

        meta2.setDisplayName("");
        setItemTag(meta2, "unique", Math.random());

        filler.setItemMeta(meta);

        inventory.setItem(1, filler);
        inventory.setItem(2, filler);
    }

    private static void setItemTag(ItemMeta meta, String key, double value) {
        meta.getPersistentDataContainer().set(new NamespacedKey(LousyNetMainHubUtils.getPlugin(), key), PersistentDataType.DOUBLE, value);
    }
}
