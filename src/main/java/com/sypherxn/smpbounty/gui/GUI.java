package com.sypherxn.smpbounty.gui;

import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import com.sypherxn.smpbounty.util.StatsUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;

// Why is this deprecated? Because this is accidentally paper and not spigot. Oops, no regrets!
@SuppressWarnings("deprecation")
public class GUI {
       
    public static Inventory getActiveRewardView(OfflinePlayer p) {

        Inventory inv = Bukkit.createInventory(null, 18, ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty View: " + p.getName());
        ArrayList<ItemStack> items = PlayerUtil.getRewardItems(p);

        items.stream()
                .forEach(inv::addItem);

        ItemStack cancel = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta cancelMeta = cancel.getItemMeta();
        ArrayList<String> cancelDesc = new ArrayList<>();
        cancelDesc.add(ChatColor.RED + "Click to go back");
        cancelMeta.setLore(cancelDesc);
        cancelMeta.setDisplayName(ChatColor.RED + "BACK");
        cancel.setItemMeta(cancelMeta);

        inv.setItem(13, cancel);

        return inv;

    }
    
    public static Inventory getRewardView(OfflinePlayer p) {

        Inventory inv = Bukkit.createInventory(null, 18, ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty View: " + p.getName());
        ArrayList<ItemStack> items = PlayerUtil.getRewardItems(p);

        items.stream()
                .forEach(inv::addItem);

        ItemStack confirm = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta confirmMeta = confirm.getItemMeta();
        ArrayList<String> confirmDesc = new ArrayList<>();
        confirmDesc.add(ChatColor.GREEN + "Click to accept the bounty");
        confirmMeta.setLore(confirmDesc);
        confirmMeta.setDisplayName(ChatColor.GREEN + "CONFIRM");
        confirm.setItemMeta(confirmMeta);

        ItemStack cancel = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta cancelMeta = cancel.getItemMeta();
        ArrayList<String> cancelDesc = new ArrayList<>();
        cancelDesc.add(ChatColor.RED + "Click to go back");
        cancelMeta.setLore(cancelDesc);
        cancelMeta.setDisplayName(ChatColor.RED + "CANCEL");
        cancel.setItemMeta(cancelMeta);

        inv.setItem(12, confirm);
        inv.setItem(14, cancel);

        return inv;

    }

    public static Inventory getCollectView(OfflinePlayer p) {

        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty Collect: " + p.getName());
        ArrayList<ItemStack> items = PlayerUtil.getCollectItems(p);

        items.stream()
                .forEach(inv::addItem);

        return inv;

    }

    public static Inventory getListView(String type) {

        ArrayList<OfflinePlayer> playerList = new ArrayList<OfflinePlayer>(Arrays.asList(Bukkit.getOfflinePlayers()));
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty List: " + type);

        switch(type) {

            case "Place":

                for(int i = 0; i < playerList.size(); i++) {

                    OfflinePlayer c = playerList.get(i);
                    if(PlayerUtil.isEnabled(c) && !PlayerUtil.hasBountyPlacer(c) && !PlayerUtil.hasShield(c)) {

                        ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD, 1);

                        ArrayList<String> desc = new ArrayList<>();
                        SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
                        meta.setOwningPlayer(playerList.get(i));
                        meta.setDisplayName(playerList.get(i).getName());
                        desc.add(ChatColor.GOLD + "Click to place bounty");
                        meta.setLore(desc);

                        playerSkull.setItemMeta(meta);

                        inv.addItem(playerSkull);

                    }

                }

                break;

            case "View":

                for(int i = 0; i < playerList.size(); i++) {

                    OfflinePlayer c = playerList.get(i);
                    if(PlayerUtil.isEnabled(c) && PlayerUtil.hasBountyPlacer(c) && !PlayerUtil.hasBountyHunter(c)) {

                        ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD, 1);

                        ArrayList<String> desc = new ArrayList<>();
                        SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
                        meta.setOwningPlayer(playerList.get(i));
                        meta.setDisplayName(playerList.get(i).getName());
                        desc.add(ChatColor.GOLD + "Click to view bounty");
                        desc.add(ChatColor.AQUA + "Bounty Placer: " + Bukkit.getOfflinePlayer(PlayerUtil.getBountyPlacer(playerList.get(i))).getName());
                        meta.setLore(desc);

                        playerSkull.setItemMeta(meta);

                        inv.addItem(playerSkull);

                    }

                }

                break;

            case "Active":

                for(int i = 0; i < playerList.size(); i++) {

                    OfflinePlayer c = playerList.get(i);
                    if(PlayerUtil.isEnabled(c) && PlayerUtil.hasBountyPlacer(c) && PlayerUtil.hasBountyHunter(c)) {

                        ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD, 1);

                        ArrayList<String> desc = new ArrayList<>();
                        SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
                        meta.setOwningPlayer(playerList.get(i));
                        meta.setDisplayName(playerList.get(i).getName());
                        desc.add(ChatColor.GOLD + "Click to view bounty");
                        desc.add(ChatColor.AQUA + "Bounty Placer: " + Bukkit.getOfflinePlayer(PlayerUtil.getBountyPlacer(c)).getName());
                        desc.add(ChatColor.AQUA + "Bounty Hunter: " + Bukkit.getOfflinePlayer(PlayerUtil.getBountyHunter(c)).getName());
                        meta.setLore(desc);

                        playerSkull.setItemMeta(meta);

                        inv.addItem(playerSkull);

                    }

                }

                break;

        }

        ItemStack cancel = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta cancelMeta = cancel.getItemMeta();
        ArrayList<String> cancelDesc = new ArrayList<>();
        cancelDesc.add(ChatColor.RED + "Click to go back");
        cancelMeta.setLore(cancelDesc);
        cancelMeta.setDisplayName(ChatColor.RED + "BACK");
        cancel.setItemMeta(cancelMeta);

        inv.setItem(53, cancel);

        return inv;

    }

    // WHEN YOU CHANGE ANY NAME, DON'T FORGET TO CHANGE THE LISTENER! ! ! ! !
    public static Inventory getMainView(OfflinePlayer p) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + "Bounty Office");

        // Enable
        if(!PlayerUtil.isEnabled(p)){

            ItemStack enableButton = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            ArrayList<String> enableDesc = new ArrayList<>();
            ItemMeta meta = enableButton.getItemMeta();

            enableDesc.add(ChatColor.GOLD + "Click to enable Bounty mode");
            enableDesc.add(ChatColor.RED + "WARNING: Once you opt in, there's no opting out");
            meta.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Bounty System Disabled");
            meta.setLore(enableDesc);

            enableButton.setItemMeta(meta);
            
            inv.setItem(22, enableButton);

        }
        else {

            ItemStack enableButton = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
            ArrayList<String> enableDesc = new ArrayList<>();
            ItemMeta meta = enableButton.getItemMeta();

            enableDesc.add(ChatColor.GREEN + "You are already in bounty mode");
            enableDesc.add(ChatColor.GREEN + "You will exit bounty mode when someone places and collects a bounty on you");
            meta.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Bounty System Enabled");
            meta.setLore(enableDesc);

            enableButton.setItemMeta(meta);
            
            inv.setItem(22, enableButton);

        }

        // Place bounty
        ItemStack placeButton = new ItemStack(Material.PAPER, 1);
        ArrayList<String> placeDesc = new ArrayList<>();
        ItemMeta placeMeta = placeButton.getItemMeta();

        placeMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Place Bounty");
        placeDesc.add("Click here to place a bounty or view available targets");
        placeMeta.setLore(placeDesc);

        placeButton.setItemMeta(placeMeta);

        inv.setItem(39, placeButton);

        // View/Accept
        ItemStack viewButton = new ItemStack(Material.OAK_SIGN, 1);
        ArrayList<String> viewDesc = new ArrayList<>();
        ItemMeta viewMeta = viewButton.getItemMeta();

        viewMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "View Bounties");
        viewDesc.add("Click here to view available bounties");
        viewMeta.setLore(viewDesc);

        viewButton.setItemMeta(viewMeta);

        inv.setItem(40, viewButton);

        ItemStack activeButton = new ItemStack(Material.DIAMOND, 1);
        ItemMeta activeMeta = activeButton.getItemMeta();
        ArrayList<String> activeDesc = new ArrayList<>();

        activeMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Currently Active Bounties");
        activeDesc.add("Click here to view already accepted bounties");
        activeMeta.setLore(activeDesc);

        activeButton.setItemMeta(activeMeta);

        inv.setItem(41, activeButton);

        // Collect page
        ItemStack collectButton = new ItemStack(Material.CHEST, 1);
        ArrayList<String> collectDesc = new ArrayList<>();
        ItemMeta collectMeta = collectButton.getItemMeta();

        if(PlayerUtil.getCollectItems(p).size() > 0){ 
            collectButton = new ItemStack(Material.CHEST, 1);
            collectDesc.add("Collect your hard earned rewards here");
            collectMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Collect Rewards");
        }
        else{
            collectButton = new ItemStack(Material.ENDER_CHEST, 1);
            collectDesc.add("You have no items to collect");
            collectMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "No Rewards Pending");
        }

 
        collectMeta.setLore(collectDesc);

        collectButton.setItemMeta(collectMeta);

        inv.setItem(49, collectButton);

        // Stats
        ItemStack statsButton = new ItemStack(Material.PLAYER_HEAD, 1);
        ArrayList<String> statsDesc = new ArrayList<>();
        SkullMeta statsSkullMeta = (SkullMeta) statsButton.getItemMeta();
        statsSkullMeta.setOwningPlayer(p);
        statsSkullMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Your Stats:");

        statsDesc.add("Total Bounties Succeeded: " + StatsUtil.getBountyKills(p));
        statsDesc.add("Total Bounties Failed: " + StatsUtil.getBountyFails(p));
        statsDesc.add("Total Bounties Survived: " + StatsUtil.getBountySurvived(p));

        statsSkullMeta.setLore(statsDesc);

        statsButton.setItemMeta(statsSkullMeta);

        inv.setItem(25, statsButton);

        // Help
        ItemStack helpButton = new ItemStack(Material.BOOK, 1);
        ArrayList<String> helpDesc = new ArrayList<>();
        ItemMeta helpMeta = helpButton.getItemMeta();
        helpMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "How Bounties WorK:");

        helpDesc.add("All participants must enable the bounty system");
        helpDesc.add("Then one player puts a bounty on another");
        helpDesc.add("Another player must accept the bounty, then hunt down the target");
        helpDesc.add("If the player succeeds in killing their target they receive the bounty reward");
        helpDesc.add("If the would-be assassin loses, the target recieves the bounty");


        helpMeta.setLore(helpDesc);

        helpButton.setItemMeta(helpMeta);

        inv.setItem(19, helpButton);

        // Place cd
        ItemStack placeCooldownButton = new ItemStack(Material.PAPER, 1);
        ArrayList<String> placeCooldownDesc = new ArrayList<>();
        ItemMeta placeCooldownMeta = placeCooldownButton.getItemMeta();
        placeCooldownMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Bounty Place Cooldown");

        if(PlayerUtil.getRemainingShield(p).equals("You are off place cooldown")) {

            placeCooldownDesc.add("You are off place cooldown");

        }
        else {

            placeCooldownDesc.add("Time remaining: " + PlayerUtil.getRemainingPlaceCooldown(p));
            
        }

        placeCooldownMeta.setLore(placeCooldownDesc);

        placeCooldownButton.setItemMeta(placeCooldownMeta);

        inv.setItem(3, placeCooldownButton);

        // Shield cd
        ItemStack shieldRemainingButton = new ItemStack(Material.SHIELD, 1);
        ArrayList<String> shieldRemainingDesc = new ArrayList<>();
        ItemMeta shieldRemainingMeta = shieldRemainingButton.getItemMeta();
        shieldRemainingMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Shield Duration Remaining: ");

        if (PlayerUtil.getRemainingShield(p).equals("You do not have a shield")) {

            shieldRemainingDesc.add("No shield");

        }
        else {

            shieldRemainingDesc.add("Time remaining: " + PlayerUtil.getRemainingShield(p));
            
        }

        shieldRemainingMeta.setLore(shieldRemainingDesc);

        shieldRemainingButton.setItemMeta(shieldRemainingMeta);

        inv.setItem(5, shieldRemainingButton);

        return inv;

    }

}