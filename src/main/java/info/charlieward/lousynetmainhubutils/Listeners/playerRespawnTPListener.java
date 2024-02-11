package info.charlieward.lousynetmainhubutils.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class playerRespawnTPListener implements Listener {

    Location spawn = new Location(Bukkit.getWorld("hub"),-74.5,35,-7.5,90,0);
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        event.setRespawnLocation(spawn);
        event.getPlayer().getInventory().setHeldItemSlot(2);

        Player player = event.getPlayer();

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
    }
}
