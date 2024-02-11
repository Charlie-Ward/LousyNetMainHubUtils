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
import org.bukkit.inventory.meta.SkullMeta;
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

        ItemStack gameSelectorCompass = new ItemStack(Material.COMPASS, 1);
        ItemMeta gameSelectorCompassItemMeta = gameSelectorCompass.getItemMeta();

        gameSelectorCompassItemMeta.setDisplayName(ChatColor.GREEN + "Game Selector");

        ArrayList<String> gameSelectorCompassLore = new ArrayList<String>();
        gameSelectorCompassLore.add("");
        gameSelectorCompassLore.add(ChatColor.GRAY + "Right Click this compass to choose which gamemode you want to go to");

        gameSelectorCompassItemMeta.setLore(gameSelectorCompassLore);
        gameSelectorCompass.setItemMeta(gameSelectorCompassItemMeta);
        Inventory inventory = player.getInventory();
        inventory.setItem(2, gameSelectorCompass);

        ItemStack gameSelectorPlayerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta gameSelectorPlayerHeadSkull = (SkullMeta) gameSelectorPlayerHead.getItemMeta();
        gameSelectorPlayerHeadSkull.setOwningPlayer(e.getPlayer());
        gameSelectorPlayerHead.setItemMeta(gameSelectorCompassItemMeta);
        inventory.setItem(6, gameSelectorPlayerHead);

        for(int count = 0; count < 45; count ++) {
            inventory.addItem(createFiller());
        }

        e.getPlayer().getInventory().setHeldItemSlot(4);
    }

    private static ItemStack createFiller() {
        ItemStack Filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fMeta = Filler.getItemMeta();
        fMeta.setDisplayName(ChatColor.GRAY + "");
        fMeta.getPersistentDataContainer().set(new NamespacedKey(LousyNetMainHubUtils.getPlugin(), "unique"), PersistentDataType.DOUBLE, Math.random());
        Filler.setItemMeta(fMeta);
        return Filler;
    }

}
