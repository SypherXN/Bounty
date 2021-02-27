package com.sypherxn.smpbounty.util;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class DataUtil {

    public static UUID defaultUUID = UUID.fromString("8205c038-273f-42d9-86f9-45a909c5fbe2");
    public static long shieldDuration = 3600;
    public static long placeCooldown = 21600;

    /**
     * Initializes file data for player
     * @param p player to initialize data for
     */
    public static void initializeData(OfflinePlayer p) {
        setDataString(p, "Name", p.getName());
        setDataUUID(p, "UUID", p.getUniqueId());

        setDataString(p, "EnableState", "Disabled");

        setDataUUID(p,"BountyPlacer", defaultUUID);
        setDataUUID(p, "BountyHunter", defaultUUID);
        setDataUUID(p, "Targeting", defaultUUID);
        setDataUUID(p, "Hunting", defaultUUID);

        setDataLong(p, "PlaceTime", 0L);
        setDataLong(p, "ShieldTime", 0L);
        setDataLong(p, "CombatTime", 0L);

        setDataItemList(p,"RewardItems", new ArrayList<ItemStack>());
        setDataItemList(p,"CollectItems", new ArrayList<ItemStack>());

        setDataLong(p, "BountyKills", 0L);
        setDataLong(p, "BountyFails", 0L);
        setDataLong(p, "BountySurvived", 0L);

    }

    /**
     * Returns string from playerdata file
     * @param p player that we want the data for
     * @param path EnableState/Name
     * @return String with data from file
     */
    public static String getDataString(OfflinePlayer p, String path) {

        return new FileUtil(p).get(path);

    }

    /**
     * Sets playerdata from a String
     * @param p player that we want to set the data for
     * @param path EnableState/Name
     * @param data String to set file data to
     */
    public static void setDataString(OfflinePlayer p, String path, String data) {

        new FileUtil(p).set(path, data);

    }

    /**
     * Returns a long from playerdata file
     * @param p player that we want the data for
     * @param path PlaceTime/ShieldTime/CombatTime/BountyKills/BountyFails/BountySurvived
     * @return Long with data from file
     */
    public static Long getDataLong(OfflinePlayer p, String path) {

        Long number = Long.parseLong(new FileUtil(p).get(path));
        return number;

    }

    /**
     * Sets playerdata from a Long
     * @param p player that we want to set the data for
     * @param path PlaceTime/ShieldTime/CombatTime/BountyKills/BountyFails/BountySurvived
     * @param data long to set file data to
     */
    public static void setDataLong(OfflinePlayer p, String path, Long data) {

        new FileUtil(p).set(path, data.toString());

    }

    /**
     * Returns a UUID from playerdata file
     * @param p player that we want the data for
     * @param path UUID/BountyPlacer/BountyHunter/Targeting/Hunting
     * @return UUID with data from file
     */
    public static UUID getDataUUID(OfflinePlayer p, String path) {

        UUID id = UUID.fromString(new FileUtil(p).get(path));
        return id;

    }

    /**
     * Sets playerdata from a UUID
     * @param p player that we want to set the data for
     * @param path UUID/BountyPlacer/BountyHunter/Targeting/Hunting
     * @param data UUID to set file data to
     */
    public static void setDataUUID(OfflinePlayer p, String path, UUID data) {

        new FileUtil(p).set(path, data.toString());

    }

    /**
     * Returns an ArrayList of items from playerdata file
     * @param p player that we want the data for
     * @param path RewardItems/CollectItems
     * @return ArrayList of items with data from file
     */
    public static ArrayList<ItemStack> getDataItemList(OfflinePlayer p, String path) {

        ArrayList<ItemStack> items = InventoryUtil.stringToItemList(new FileUtil(p).get(path));
        return items;

    }

    /**
     * Sets playerdata from a ArrayList of items
     * @param p player that we want to set the data for
     * @param path RewardItems/CollectItems
     * @param data ArrayList of items to set file data to
     */
    public static void setDataItemList(OfflinePlayer p, String path, ArrayList<ItemStack> data) {

        new FileUtil(p).set(path, InventoryUtil.itemListToString(data));

    }

}
