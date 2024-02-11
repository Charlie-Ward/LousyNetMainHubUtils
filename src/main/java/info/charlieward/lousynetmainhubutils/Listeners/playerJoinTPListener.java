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
