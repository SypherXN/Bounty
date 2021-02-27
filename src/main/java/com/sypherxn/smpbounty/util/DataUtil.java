package com.sypherxn.smpbounty.util;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class DataUtil {

    /**
     * Initializes file data for player
     * @param p
     */
    public static void initializeData(OfflinePlayer p) {

        FileUtil use = new FileUtil(p);
        use.set("Name", p.getName());
        use.set("UUID", p.getUniqueId().toString());

        use.set("EnableState", "Disabled");

        use.set("BountyPlacer", "8205c038-273f-42d9-86f9-45a909c5fbe2");
        use.set("BountyHunter", "8205c038-273f-42d9-86f9-45a909c5fbe2");
        use.set("Targeting", "8205c038-273f-42d9-86f9-45a909c5fbe2");
        use.set("Hunting", "8205c038-273f-42d9-86f9-45a909c5fbe2");

        use.set("PlaceTime", "0");
        use.set("ShieldTime", "0");
        use.set("CombatTime", "0");

        use.set("RewardItems", "");
        use.set("CollectItems", "");

        use.set("BountyKills", "0");
        use.set("BountyFails", "0");
        use.set("BountySurvived", "0");

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
     * Returns an ArrayList of items from playerdata file
     * @param p player that we want the data for
     * @param path RewardItems/CollectItems
     * @return ArrayList of items with data from file
     */
    public static ArrayList<ItemStack> getDataItemList(OfflinePlayer p, String path) {

        ArrayList<ItemStack> items = InventoryUtil.stringToItemList(new FileUtil(p).get(path));
        return items;

    }

}
