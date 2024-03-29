package info.charlieward.lousynetmainhubutils.Listeners;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class playerRespawnTPListener implements Listener {

    Location spawn = new Location(Bukkit.getWorld("hub"),-74.5,35,-7.5,90,0);
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        e.setRespawnLocation(spawn);
        e.getPlayer().getInventory().setHeldItemSlot(2);

        Player player = e.getPlayer();

        ItemStack gameSelectorCompass = new ItemStack(Material.COMPASS, 1);
        ItemMeta gameSelectorCompassItemMeta = gameSelectorCompass.getItemMeta();
        gameSelectorCompassItemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "LousyNet Game Selector");
        ArrayList<String> gameSelectorCompassLore = new ArrayList<String>();
        gameSelectorCompassLore.add("");
        gameSelectorCompassLore.add(ChatColor.GRAY + "Right Click to access the game selector");
        gameSelectorCompassLore.add("");
        gameSelectorCompassItemMeta.setLore(gameSelectorCompassLore);
        gameSelectorCompass.setItemMeta(gameSelectorCompassItemMeta);
        Inventory inventory = player.getInventory();
        inventory.setItem(2, gameSelectorCompass);

        ItemStack gameSelectorSocial = new ItemStack(Material.PAPER, 1);
        ItemMeta gameSelectorSocialMeta = gameSelectorSocial.getItemMeta();
        gameSelectorSocialMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Social Menu - WIP");
        ArrayList<String> gameSelectorSocialLore = new ArrayList<String>();
        gameSelectorSocialLore.add("");
        gameSelectorSocialLore.add(ChatColor.GRAY + "Right click to access the social menu");
        gameSelectorSocialLore.add("");
        gameSelectorSocialMeta.setLore(gameSelectorSocialLore);
        gameSelectorSocial.setItemMeta(gameSelectorSocialMeta);
        inventory.setItem(6, gameSelectorSocial);
        e.getPlayer().getInventory().setHeldItemSlot(2);
    }
}
