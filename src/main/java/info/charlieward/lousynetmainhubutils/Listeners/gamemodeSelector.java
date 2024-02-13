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
import java.util.Objects;

public class gamemodeSelector implements Listener {

    static LousyNetMainHubUtils plugin;

    public gamemodeSelector(LousyNetMainHubUtils plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void compassRightClick(PlayerInteractEvent event) {
         Player player = event.getPlayer();
         if(player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
            GameSelectorMenu(player);
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


        gameSelector.setItem(0, createFiller(false, ""));
        gameSelector.setItem(1, createFiller(false, ""));
        gameSelector.setItem(2, createFiller(false, ""));
        gameSelector.setItem(3, createFiller(false, ""));

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

        gameSelector.setItem(5, createFiller(false, ""));
        gameSelector.setItem(6, createFiller(false, ""));
        gameSelector.setItem(7, createFiller(false, ""));
        gameSelector.setItem(8, createFiller(false, ""));

        gameSelector.setItem(9, createFiller(true, "Featured Gamemodes"));
        gameSelector.setItem(10, makeGamemodeItem(Material.CRAFTING_TABLE, "Survival", "survival", "1.17", "Hop onto the survival server and make magnificent bases and super farms"));
        gameSelector.setItem(11, makeGamemodeItem(Material.BEDROCK, "Creative", "", "1.17", "Explore only the limits of your imagination with unlimited blocks and resources."));
        gameSelector.setItem(12, makeGamemodeItem(Material.RED_BED, "Bedwars", "", "1.17", "Destroy enemy beds whilst keeping yours safe to come out on top"));
        gameSelector.setItem(13, makeGamemodeItem(Material.GRASS_BLOCK, "Skywars", "", "1.17", "Fight to the death in a floating arena. Just be careful to not fall into the void"));
        gameSelector.setItem(14, makeGamemodeItem(Material.BOW, "Survival Games", "", "1.17", "Battle to the death in this minecraft classic gamemode"));
        gameSelector.setItem(15, makeGamemodeItem(Material.OAK_PLANKS, "Build Battle", "", "1.17", "Who's the best builder find out here"));
        gameSelector.setItem(16, makeGamemodeItem(Material.GOLDEN_APPLE, "Duels", "", "1.17", "1v1 players to become the best at PvP"));
        gameSelector.setItem(17, createFiller(true, "Featured Gamemodes"));

        gameSelector.setItem(18, createFiller(false, ""));
        gameSelector.setItem(19, createFiller(false, ""));
        gameSelector.setItem(20, createFiller(false, ""));
        gameSelector.setItem(21, createFiller(false, ""));
        gameSelector.setItem(22, createFiller(false, ""));
        gameSelector.setItem(23, createFiller(false, ""));
        gameSelector.setItem(24, createFiller(false, ""));
        gameSelector.setItem(25, createFiller(false, ""));
        gameSelector.setItem(26, createFiller(false, ""));

        gameSelector.setItem(27, createFiller(false, ""));
        gameSelector.setItem(35, createFiller(false, ""));

        gameSelector.setItem(36, createFiller(false, ""));
        gameSelector.setItem(44, createFiller(false, ""));

        gameSelector.setItem(45, createFiller(false, ""));
        gameSelector.setItem(46, createFiller(false, ""));
        gameSelector.setItem(47, createFiller(false, ""));
        gameSelector.setItem(48, createFiller(false, ""));
        gameSelector.setItem(49, makeGamemodeItem(Material.OAK_DOOR, "Main Hub", "mainHub", "1.17", "Return to the main lobby"));
        gameSelector.setItem(50, createFiller(false, ""));
        gameSelector.setItem(51, createFiller(false, ""));
        gameSelector.setItem(52, createFiller(false, ""));
        gameSelector.setItem(53, createFiller(false, ""));

        player.openInventory(gameSelector);
    }

    private static ItemStack createFiller(Boolean customName, String name) {
        ItemStack Filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fMeta = Filler.getItemMeta();
        if (customName == false) {
            fMeta.setDisplayName(ChatColor.GRAY + "");
        } else {
            fMeta.setDisplayName(ChatColor.GRAY + name);
        }
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

    public static ItemStack makeGamemodeItem(Material blockItem, String gamemodeName, String serverID, String serverMCversion, String description) {
        boolean serverExists;
        if (serverID.isEmpty()) {
            serverExists = false;
        } else {
            serverExists = true;
        }
        String playerCount = plugin.jedis.get(serverID);
        ItemStack item = new ItemStack(blockItem);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + gamemodeName);
        ArrayList<String> itemLore = new ArrayList<String>();
        itemLore.add("");
        if (!serverExists) {
            itemLore.add(ChatColor.WHITE + "Players: " + ChatColor.GRAY + "Not Yet Released");
        } else {
            if (playerCount.equals("null")) {
                itemLore.add(ChatColor.WHITE + "Players: " + ChatColor.GRAY + "Server Offline");
            } else {
                itemLore.add(ChatColor.WHITE + "Players: " + ChatColor.GRAY + playerCount);
            }
        }
        itemLore.add(ChatColor.WHITE + "Minecraft Version: " + ChatColor.GRAY + serverMCversion);
        itemLore.add("");
        itemLore.add(ChatColor.GRAY + description);
        itemLore.add(ChatColor.GREEN + "Play Now!");
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);
        return item;
    }
}