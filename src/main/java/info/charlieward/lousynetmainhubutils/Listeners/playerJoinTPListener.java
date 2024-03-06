package info.charlieward.lousynetmainhubutils.Listeners;

import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.*;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class playerJoinTPListener implements Listener {

    LousyNetMainHubUtils plugin;

    public playerJoinTPListener(LousyNetMainHubUtils plugin) {
        this.plugin = plugin;
    }
    Location spawn = new Location(Bukkit.getWorld("hub"),-74.5,35,-7.5,90,0);
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.teleport(spawn);
        player.getInventory().clear();
        player.sendMessage(ChatColor.GREEN + "WELCOME TO LOUSYNET!");
        if (!(plugin.staffModeList.contains(player.getDisplayName()))){
            player.setGameMode(GameMode.ADVENTURE);
        }

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

        ItemStack socialSelector = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) socialSelector.getItemMeta();
        meta.setOwningPlayer(player);
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Social Menu - WIP");
        ArrayList<String> SocialLore = new ArrayList<String>();
        SocialLore.add("");
        SocialLore.add(ChatColor.GRAY + "Right click to access the social menu");
        SocialLore.add("");
        meta.setLore(SocialLore);
        socialSelector.setItemMeta(meta);
        inventory.setItem(6, socialSelector);

        e.getPlayer().getInventory().setHeldItemSlot(2);
    }
}
