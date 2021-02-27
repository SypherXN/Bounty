package com.sypherxn.smpbounty.listeners;

import com.sypherxn.smpbounty.commands.AcceptCommand;
import com.sypherxn.smpbounty.commands.CollectCommand;
import com.sypherxn.smpbounty.commands.PlaceCommand;
import com.sypherxn.smpbounty.commands.SubCommand;
import com.sypherxn.smpbounty.gui.GUI;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.FileUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import com.sypherxn.smpbounty.util.StatsUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@SuppressWarnings("deprecated")
public class Listeners implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerLoginEvent e) {

        new FileUtil(e.getPlayer()).create();

    }

    //Check closing of Bounty Place and ensure that there are items inside the inventory
    @EventHandler
    public void bountyPlaceClose(InventoryCloseEvent e) {

        if(e.getView().getTitle().length() < 17) { return; }

        String placeCheck = e.getView().getTitle().substring(0,17);
        Player p = (Player) e.getPlayer();

        if(placeCheck.equalsIgnoreCase(ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty Place:")) {

            if(e.getInventory().isEmpty()) {

                ChatUtil.sendMessage(p, "You cannot place a bounty with no reward!");
                return;

            }

            ArrayList<ItemStack> items = new ArrayList<>();

            Arrays.stream(e.getInventory().getContents())
                    .filter(itemStack -> {

                        if(itemStack == null) {

                            return false;

                        }

                        return true;

                    })
                    .forEach(items::add);

            String targetName = e.getView().getTitle().substring(18);
            OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
            UUID targetUUID = target.getUniqueId();

            PlayerUtil.setRewardItems(target, items);
            PlayerUtil.setBountyPlacer(target, p.getUniqueId());
            ChatUtil.sendMessage(target, p.getName() + " has placed a bounty on you!");

            PlayerUtil.setTargeting(p, targetUUID);
            PlayerUtil.setPlaceTimeCurrent(p);
            ChatUtil.sendMessage(p, "You have successfully placed a bounty on " + targetName);

        }

    }

    //Check closing of Bounty Collect and ensure that there are items
    @EventHandler
    public void bountyCollectClose(InventoryCloseEvent e) {

        if(e.getView().getTitle().length() < 19) { return; }

        String collectCheck = e.getView().getTitle().substring(0,19);
        Player p = (Player) e.getPlayer();

        if(collectCheck.equalsIgnoreCase(ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty Collect:")) {

            if (e.getInventory().isEmpty()) {

                PlayerUtil.resetCollectItems(p);
                return;

            }

            ChatUtil.sendMessage(p, "You will not be able to perform other bounty tasks with items left in your collection!");

            ArrayList<ItemStack> items = new ArrayList<>();

            Arrays.stream(e.getInventory().getContents())
                    .filter(itemStack -> {

                        if (itemStack == null) {

                            return false;

                        }

                        return true;

                    })
                    .forEach(items::add);

            PlayerUtil.setCollectItems(p, items);

        }

    }

    //Cancel Taking items out of Bounty View
    @EventHandler
    public void viewClick(InventoryClickEvent e) {

        if(e.getView().getTitle().length() < 16) { return; }

        String viewCheck = e.getView().getTitle().substring(0,16);

        if(viewCheck.equalsIgnoreCase(ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty View:")) {

            e.setCancelled(true);

            ItemStack item = e.getCurrentItem();
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "CONFIRM")) {

                String target = e.getView().getTitle().substring(17);

                SubCommand cmd = new AcceptCommand();
                cmd.onCommand((Player) e.getWhoClicked(), new String[]{"", target});
                e.getWhoClicked().closeInventory();

            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "CANCEL")) {

                Inventory viewInv = GUI.getListView("View");
                e.getWhoClicked().openInventory(viewInv);

            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "BACK")) {

                Inventory activeInv = GUI.getListView("Active");
                e.getWhoClicked().openInventory(activeInv);

            }

        }

    }

    //Open bounty view from bounty list and cancel click
    @EventHandler
    public void listToViewClick(InventoryClickEvent e) {

        String listCheck = e.getView().getTitle();
        ItemStack clickedItem = e.getCurrentItem();

        String name = clickedItem.getItemMeta().getDisplayName();
        Player p = (Player) e.getWhoClicked();
        OfflinePlayer target = Bukkit.getOfflinePlayer(name);

        if(listCheck.equalsIgnoreCase(ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty List: View")) {

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "BACK")) {

                Inventory inv = GUI.getMainView(p);
                p.openInventory(inv);
                return;

            }

            Inventory bountyView = GUI.getRewardView(target);
            p.openInventory(bountyView);
            e.setCancelled(true);

        } else if(listCheck.equalsIgnoreCase(ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty List: Place")) {

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "BACK")) {

                Inventory inv = GUI.getMainView(p);
                p.openInventory(inv);
                return;

            }

            e.setCancelled(true);
            SubCommand cmd = new PlaceCommand();
            cmd.onCommand(p, new String[]{"", target.getName()});

        } else if(listCheck.equalsIgnoreCase(ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty List: Active")) {

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "BACK")) {

                Inventory inv = GUI.getMainView(p);
                p.openInventory(inv);
                return;

            }

            e.setCancelled(true);
            Inventory activeView = GUI.getActiveRewardView(target);
            p.openInventory(activeView);

        }

    }

    @EventHandler
    public void mainGUIClick(InventoryClickEvent e) {

        if(e.getView().getTitle().length() < 17) { return; }

        String listCheck = e.getView().getTitle().substring(0, 17);
        ItemStack clickedItem = e.getCurrentItem();
        String name = clickedItem.getItemMeta().getDisplayName();
        Player p = (Player) e.getWhoClicked();

        if(listCheck.equalsIgnoreCase(ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty Office")) {
            e.setCancelled(true);
            switch (ChatColor.stripColor(name)) {
                case "Bounty System Disabled":
                    PlayerUtil.setEnableState(p, "Enabled");
                    Inventory inv = GUI.getMainView(p);
                    p.openInventory(inv);
                    break;

                case "Place Bounty":
                    Inventory inv2 = GUI.getListView("Place");
                    p.openInventory(inv2);
                    break;

                case "View Bounties":
                    Inventory inv3 = GUI.getListView("View");
                    p.openInventory(inv3);
                    break;
                case "Currently Active Bounties":
                    Inventory inv4 = GUI.getListView("Active");
                    p.openInventory(inv4);
                    break;
                case "Collect Rewards":
                    SubCommand cmd = new CollectCommand();
                    cmd.onCommand(p, new String[0]);
                    break;

            }

        }

    }

    //Check if player death impacts bounties
    @EventHandler
    public void playerDeathCheck(PlayerDeathEvent e) {

        if(e.getEntity().getKiller() instanceof Player) {

            Player death = e.getEntity();
            Player killer = e.getEntity().getKiller();

            UUID killerUUID = killer.getUniqueId();

            UUID bountyPlacerUUID = PlayerUtil.getBountyPlacer(death);
            UUID deathHunterUUID = PlayerUtil.getBountyHunter(death);

            OfflinePlayer bountyPlacer = Bukkit.getOfflinePlayer(bountyPlacerUUID);
            if(deathHunterUUID.equals(killerUUID)) {

                ChatUtil.sendBroadcast(killer.getName() + " has killed " + death.getName() + " and has collected their reward from " + bountyPlacer.getName());

                ArrayList<ItemStack> reward = PlayerUtil.getRewardItems(death);
                PlayerUtil.setCollectItems(killer, reward);
                PlayerUtil.resetHunting(killer);
                StatsUtil.incrementBountyKills(killer);
                ChatUtil.sendMessage(killer, "Use \"/bounty collect\" to retrieve your reward items!");

                PlayerUtil.resetBountyHunter(death);
                PlayerUtil.resetBountyPlacer(death);
                PlayerUtil.resetRewardItems(death);
                PlayerUtil.setEnableState(death, "Disabled");
                ChatUtil.sendMessage(death, "You are no longer bounty-enabled!");

                PlayerUtil.resetTargeting(bountyPlacer);

                ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD, 1);
                ArrayList<String> desc = new ArrayList<>();
                SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
                meta.setOwningPlayer(death);
                meta.setDisplayName(death.getName());
                desc.add(ChatColor.GOLD + "Bounty Completed");
                meta.setLore(desc);
                playerSkull.setItemMeta(meta);

                ArrayList<ItemStack> single = new ArrayList<>();
                single.add(playerSkull);

                PlayerUtil.setCollectItems(bountyPlacer, single);

                return;

            }

            UUID deathHuntingUUID = PlayerUtil.getHunting(death);
            bountyPlacerUUID = PlayerUtil.getBountyPlacer(killer);
            bountyPlacer = Bukkit.getOfflinePlayer(bountyPlacerUUID);

            if(deathHuntingUUID.equals(killerUUID)) {

                ChatUtil.sendBroadcast(death.getName() + " has failed to complete " + bountyPlacer.getName() + "'s bounty on " + killer.getName());

                PlayerUtil.resetHunting(death);
                StatsUtil.incrementBountyFails(death);

                ArrayList<ItemStack> reward = PlayerUtil.getRewardItems(killer);
                PlayerUtil.setCollectItems(killer, reward);
                ChatUtil.sendMessage(killer, "Use \"/bounty collect\" to retrieve your reward items!");
                PlayerUtil.resetTargeting(bountyPlacer);

                PlayerUtil.resetBountyHunter(killer);
                PlayerUtil.resetBountyPlacer(killer);
                PlayerUtil.resetRewardItems(killer);
                PlayerUtil.setShieldTimeCurrent(killer);
                StatsUtil.incrementBountySurvived(killer);

                return;

            }

        }

    }

    @EventHandler
    public void combatTagProtection(EntityDamageByEntityEvent e) {

        if(!((e.getEntity() instanceof Player) && (e.getDamager() instanceof Player))) { return; }

        Player target = (Player) e.getEntity();
        Player hitter = (Player) e.getDamager();

        if(PlayerUtil.inBountyCombat(hitter) || PlayerUtil.inBountyCombat(target)) {

            if(!((PlayerUtil.getBountyHunter(target).equals(hitter.getUniqueId()) || (PlayerUtil.getHunting(target).equals(hitter.getUniqueId()))))) {

                e.setCancelled(true);

                if(PlayerUtil.inBountyCombat(target)) {

                    ChatUtil.sendMessage(hitter, target.getName() + " is currently taking part in bounty combat!");

                }

                if(!PlayerUtil.inBountyCombat(hitter)) {

                    ChatUtil.sendMessage(hitter, "You cannot attack others while in bounty combat!");

                }

            }

        }

        if(PlayerUtil.getBountyHunter(target).equals(hitter.getUniqueId()) || PlayerUtil.getBountyHunter(hitter).equals(target.getUniqueId())) {

            PlayerUtil.setCombatTimeCurrent(target);
            PlayerUtil.setCombatTimeCurrent(hitter);

        }

    }

}