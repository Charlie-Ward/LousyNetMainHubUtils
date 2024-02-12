package info.charlieward.lousynetmainhubutils.Listeners;

import com.sun.jdi.CharType;
import info.charlieward.lousynetmainhubutils.LousyNetMainHubUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

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
        Inventory gameSelector = Bukkit.createInventory(player, 54, ChatColor.GOLD  + "" + ChatColor.BOLD + "LousyNet Game Selector");


        gameSelector.setItem(0, createFiller());
        gameSelector.setItem(1, createFiller());
        gameSelector.setItem(2, createFiller());
        gameSelector.setItem(3, createFiller());

        ItemStack welcomeBlock = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta welcomeMeta = welcomeBlock.getItemMeta();
        welcomeMeta.setDisplayName(ChatColor.GOLD + "Welcome to LousyNet");
        ArrayList<String> welcomeLore = new ArrayList<String>();
        welcomeLore.add("");
        welcomeLore.add(ChatColor.GRAY + "Use this menu to see what gamemodes you can play and select one to start the fun");
        welcomeLore.add("");
        welcomeMeta.setLore(welcomeLore);
        welcomeBlock.setItemMeta(welcomeMeta);
        gameSelector.setItem(4, welcomeBlock);

        gameSelector.setItem(5, createFiller());
        gameSelector.setItem(6, createFiller());
        gameSelector.setItem(7, createFiller());
        gameSelector.setItem(8, createFiller());

        gameSelector.setItem(9, createFiller());
        gameSelector.setItem(10, makeGamemodeItem(Material.DIAMOND_BLOCK, "Survival", "https://api.mcsrvstat.us/3/194.163.179.210:25566", "1.17", "Hop onto the survival server and make magnificent bases and super farms"));

        player.openInventory(gameSelector);
    }

    private static ItemStack createFiller() {
        ItemStack Filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fMeta = Filler.getItemMeta();
        fMeta.setDisplayName(ChatColor.GRAY + "");
        fMeta.getPersistentDataContainer().set(new NamespacedKey(LousyNetMainHubUtils.getPlugin(), "unique"), PersistentDataType.DOUBLE, Math.random());
        Filler.setItemMeta(fMeta);
        return Filler;
    }

    private static String getServerCount(String URL) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String[] split = response.body().split("online", 2);
        String[] split2 = split[1].split(":", 2);

        if (split2[1].contains("max")) {
            String[] split3 = split2[1].split(",", 2);
            return split3[0];
        } else {
            return "Server Offline";
        }
    }

    public static ItemStack makeGamemodeItem(Material blockItem, String gamemodeName, String URL, String serverMCversion, String description) {
        String serverInfo = getServerCount(URL);

        ItemStack item = new ItemStack(blockItem);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + gamemodeName);
        ArrayList<String> itemLore = new ArrayList<String>();
        itemLore.add("");
        itemLore.add(ChatColor.WHITE + "Players" + ChatColor.GRAY + serverInfo);
        itemLore.add(ChatColor.WHITE + "Minecraft Version" + ChatColor.GRAY + serverMCversion);
        itemLore.add("");
        itemLore.add(ChatColor.GRAY + description);
        itemLore.add("");
        if (serverInfo.equals("Server Offline")) {
            itemLore.add(ChatColor.RED + "Server Offline");
        } else {
            itemLore.add(ChatColor.GREEN + "Play Now!");
        }
        itemLore.add("");
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);
        return item;
    }
}

//"https://api.mcsrvstat.us/3/194.163.179.210:25566"
