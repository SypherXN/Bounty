package com.sypherxn.smpbounty.util;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerUtil {

    /**
     * Initializes file data for player
     * @param p player to initialize data for
     */
    public static void initializePlayer(OfflinePlayer p) {
        DataUtil.setDataString(p, "Name", p.getName());
        DataUtil.setDataUUID(p, "UUID", p.getUniqueId());

        DataUtil.setDataString(p, "EnableState", "Disabled");

        DataUtil.setDataUUID(p,"BountyPlacer", DataUtil.defaultUUID);
        DataUtil.setDataUUID(p, "BountyHunter", DataUtil.defaultUUID);
        DataUtil.setDataUUID(p, "Targeting", DataUtil.defaultUUID);
        DataUtil.setDataUUID(p, "Hunting", DataUtil.defaultUUID);

        DataUtil.setDataLong(p, "PlaceTime", 0L);
        DataUtil.setDataLong(p, "ShieldTime", 0L);
        DataUtil.setDataLong(p, "CombatTime", 0L);

        DataUtil.setDataItemList(p,"RewardItems", new ArrayList<ItemStack>());
        DataUtil.setDataItemList(p,"CollectItems", new ArrayList<ItemStack>());

        DataUtil.setDataLong(p, "BountyKills", 0L);
        DataUtil.setDataLong(p, "BountyFails", 0L);
        DataUtil.setDataLong(p, "BountySurvived", 0L);

    }

    /**
     * Finds out if player is currently bounty-enabled
     * @param p player to check bounty state for
     * @return true if enabled and false if disabled
     */
    public static Boolean isEnabled(OfflinePlayer p) {

        if(DataUtil.getDataString(p, "EnableState").equalsIgnoreCase("Enabled")) return true;
        return false;

    }

    /**
     * Get enable state string
     * @param p player you want data from
     * @return String for enable state
     */
    public static String getEnableState(OfflinePlayer p) {

        return DataUtil.getDataString(p, "EnableState");

    }

    /**
     * Set enable state
     * @param p player to set data for
     * @param data data to set
     */
    public static void setEnableState(OfflinePlayer p, String data) {

        DataUtil.setDataString(p, "EnableState", data);

    }

    /**
     * Resets EnableState to Disabled
     * @param p player to reset
     */
    public static void resetEnableState(OfflinePlayer p) {

        DataUtil.setDataString(p, "EnableState", "Disabled");

    }

    /**
     * Finds out if the player has a bounty on them
     * @param p player to check if they have a bounty on them
     * @return true if bounty is on them and false if no bounty on them
     */
    public static Boolean hasBountyPlacer(OfflinePlayer p) {

        if(DataUtil.getDataUUID(p, "BountyPlacer").equals(DataUtil.defaultUUID)) return false;
        return true;

    }

    /**
     * Get bounty placer UUID
     * @param p player you want data from
     * @return UUID of bounty placer
     */
    public static UUID getBountyPlacer(OfflinePlayer p) {

        return DataUtil.getDataUUID(p, "BountyPlacer");

    }

    /**
     * Set Bounty Placer
     * @param p player to set data for
     * @param data UUID to set data to
     */
    public static void setBountyPlacer(OfflinePlayer p, UUID data) {

        DataUtil.setDataUUID(p, "BountyPlacer", data);

    }

    /**
     * Resets bounty placer to SypherXN
     * @param p player to reset
     */
    public static void resetBountyPlacer(OfflinePlayer p) {

        DataUtil.setDataUUID(p, "BountyPlacer", DataUtil.defaultUUID);

    }

    /**
     * Finds out if the player has a player hunting them
     * @param p player to check if they have a player hunting them
     * @return true if player is hunting them and false if no player is hunting them
     */
    public static Boolean hasBountyHunter(OfflinePlayer p) {

        if(DataUtil.getDataUUID(p, "BountyHunter").equals(DataUtil.defaultUUID)) return false;
        return true;

    }

    /**
     * Get bounty hunter UUID
     * @param p player you want data from
     * @return UUID of bounty hunter
     */
    public static UUID getBountyHunter(OfflinePlayer p) {

        return DataUtil.getDataUUID(p, "BountyHunter");

    }

    /**
     * Set Bounty Hunter
     * @param p player to set data for
     * @param data UUID to set data to
     */
    public static void setBountyHunter(OfflinePlayer p, UUID data) {

        DataUtil.setDataUUID(p, "BountyHunter", data);

    }

    /**
     * Resets bounty hunter to SypherXN
     * @param p player to reset
     */
    public static void resetBountyHunter(OfflinePlayer p) {

        DataUtil.setDataUUID(p, "BountyHunter", DataUtil.defaultUUID);

    }

    /**
     * Finds out if the player has placed a bounty
     * @param p player to check if they have placed a bounty
     * @return true if they have placed a bounty and false if they have not placed a bounty
     */
    public static Boolean hasTargeting(OfflinePlayer p) {

        if(DataUtil.getDataUUID(p, "Targeting").equals(DataUtil.defaultUUID)) return false;
        return true;

    }

    /**
     * Get target UUID
     * @param p player you want data from
     * @return UUID of target
     */
    public static UUID getTargeting(OfflinePlayer p) {

        return DataUtil.getDataUUID(p, "Targeting");

    }

    /**
     * Set Targeting
     * @param p player to set data for
     * @param data UUID to set data to
     */
    public static void setTargeting(OfflinePlayer p, UUID data) {

        DataUtil.setDataUUID(p, "Targeting", data);

    }

    /**
     * Resets targeting to SypherXN
     * @param p player to reset
     */
    public static void resetTargeting(OfflinePlayer p) {

        DataUtil.setDataUUID(p, "Targeting", DataUtil.defaultUUID);

    }

    /**
     * Finds out if the player is hunting
     * @param p player to check if they are hunting
     * @return true if hunting and false if not hunting
     */
    public static Boolean hasHunting(OfflinePlayer p) {

        if(DataUtil.getDataUUID(p, "Hunting").equals(DataUtil.defaultUUID)) return false;
        return true;

    }

    /**
     * Get hunting UUID
     * @param p player you want data from
     * @return UUID of hunting
     */
    public static UUID getHunting(OfflinePlayer p) {

        return DataUtil.getDataUUID(p, "Hunting");

    }

    /**
     * Set Hunting
     * @param p player to set data for
     * @param data UUID to set data to
     */
    public static void setHunting(OfflinePlayer p, UUID data) {

        DataUtil.setDataUUID(p, "Hunting", data);

    }

    /**
     * Resets hunting to SypherXN
     * @param p player to reset
     */
    public static void resetHunting(OfflinePlayer p) {

        DataUtil.setDataUUID(p, "Hunting", DataUtil.defaultUUID);

    }

    /**
     * Finds out if the player has bounty rewards on them
     * @param p player to check if they have a bounty reward on them
     * @return true if they have bounty rewards and false if they don't have bounty rewards
     */
    public static Boolean hasRewardItems(OfflinePlayer p) {

        if(!DataUtil.getDataItemList(p, "RewardItems").isEmpty()) return false;
        return true;

    }

    /**
     * Get reward items ArrayList
     * @param p player you want data from
     * @return ArrayList of reward items
     */
    public static ArrayList<ItemStack> getRewardItems(OfflinePlayer p) {

        return DataUtil.getDataItemList(p, "RewardItems");

    }

    /**
     * Set reward items
     * @param p player to set data for
     * @param data ArrayList to set data to
     */
    public static void setRewardItems(OfflinePlayer p, ArrayList<ItemStack> data) {

        DataUtil.setDataItemList(p, "RewardItems", data);

    }

    /**
     * Resets rewards items to empty
     * @param p player to reset
     */
    public static void resetRewardItems(OfflinePlayer p) {

        DataUtil.setDataItemList(p, "RewardItems", new ArrayList<ItemStack>());

    }

    /**
     * Finds out if the player has collect items on them
     * @param p player to check if they have a collect items on them
     * @return true if they have collect items and false if they don't have collect items
     */
    public static Boolean hasCollectItems(OfflinePlayer p) {

        if(!DataUtil.getDataItemList(p, "CollectItems").isEmpty()) return false;
        return true;

    }

    /**
     * Get collect items ArrayList
     * @param p player you want data from
     * @return ArrayList of collect items
     */
    public static ArrayList<ItemStack> getCollectItems(OfflinePlayer p) {

        return DataUtil.getDataItemList(p, "CollectItems");

    }

    /**
     * Set collect items
     * @param p player to set data for
     * @param data ArrayList to set data to
     */
    public static void setCollectItems(OfflinePlayer p, ArrayList<ItemStack> data) {

        DataUtil.setDataItemList(p, "CollectItems", data);

    }

    /**
     * Resets collect items to empty
     * @param p player to reset
     */
    public static void resetCollectItems(OfflinePlayer p) {

        DataUtil.setDataItemList(p, "CollectItems", new ArrayList<ItemStack>());

    }

    /**
     * Finds out if the player has a shield
     * @param p player to check if they have a shield
     * @return true if they have a shield and false if they don't have a shield
     */
    public static Boolean hasShield(OfflinePlayer p) {

        if((DataUtil.getDataLong(p, "ShieldTime") / 1000) > DataUtil.shieldDuration) return true;
        return false;

    }

    /**
     * Get a String for the remaing shield
     * @param p player that you want the remaining shield of
     * @return String with remaining shield
     */
    public static String getRemainingShield(OfflinePlayer p) {

        String remainingShield = "";
        long placeTimeMillis = DataUtil.getDataLong(p, "ShieldTime");
        long cooldownSeconds = ((placeTimeMillis / 1000) + DataUtil.shieldDuration) - (System.currentTimeMillis() / 1000);
        long hours = cooldownSeconds / 3600;
        long minutes = (cooldownSeconds / 60) % 60;
        long seconds = cooldownSeconds % 60;
        remainingShield = "" + hours + " hours, " + minutes + " minutes, and " + seconds + " seconds";
        if(cooldownSeconds < 1) {
            return "You do not have a shield";
        }
        return remainingShield;

    }

    /**
     * Set shield time to current time
     * @param p player to set data for
     */
    public static void setShieldTimeCurrent(OfflinePlayer p) {

        DataUtil.setDataLong(p, "ShieldTime", System.currentTimeMillis());

    }

    /**
     * Resets shield time to 0
     * @param p player to reset
     */
    public static void resetShieldTime(OfflinePlayer p) {

        DataUtil.setDataLong(p, "ShieldTime", 0L);

    }

    /**
     * Finds out if the player is on place cooldown
     * @param p player to check if they are on place cooldown
     * @return true if they are on place cooldown and false if they are not on place cooldown
     */
    public static Boolean onPlaceCooldown(OfflinePlayer p) {

        if((DataUtil.getDataLong(p, "PlaceTime") / 1000) > DataUtil.placeCooldown) return false;
        return true;

    }

    /**
     * Get a String for the remaining place cooldown
     * @param p player that you want the remaining place cooldown of
     * @return String with remaining place cooldown
     */
    public static String getRemainingPlaceCooldown(OfflinePlayer p) {

        String remainingCooldown = "";
        long placeTimeMillis = DataUtil.getDataLong(p, "PlaceTime");
        long cooldownSeconds = ((placeTimeMillis / 1000) + DataUtil.placeCooldown) - (System.currentTimeMillis() / 1000);
        long hours = cooldownSeconds / 3600;
        long minutes = (cooldownSeconds / 60) % 60;
        long seconds = cooldownSeconds % 60;
        remainingCooldown = "" + hours + " hours, " + minutes + " minutes, and " + seconds + " seconds";
        if(cooldownSeconds < 1) {
            return "You are off place cooldown";
        }
        return remainingCooldown;

    }

    /**
     * Set place time to current time
     * @param p player to set data for
     */
    public static void setPlaceTimeCurrent(OfflinePlayer p) {

        DataUtil.setDataLong(p, "PlaceTime", System.currentTimeMillis());

    }

    /**
     * Resets place cooldown time to 0
     * @param p player to reset
     */
    public static void resetPlaceCooldownTime(OfflinePlayer p) {

        DataUtil.setDataLong(p, "PlaceTime", 0L);

    }

    /**
     * Finds out if the player is in bounty combat
     * @param p player to check if they are in bounty combat
     * @return true if they are in bounty combat and false if they are not in bounty combat
     */
    public static Boolean inBountyCombat(OfflinePlayer p) {

        if((DataUtil.getDataLong(p, "CombatTime") / 1000) > DataUtil.combatDuration) return false;
        return true;

    }

    /**
     * Gets a string for the remaining time in bounty combat
     * @param p player that you want the remaining bounty combat tag of
     * @return String with remaining combat tag
     */
    public static String getRemainingCombatTag(OfflinePlayer p) {

        String combatTag = "";
        long placeTimeMillis = DataUtil.getDataLong(p, "CombatTime");
        long cooldownSeconds = ((placeTimeMillis / 1000) + DataUtil.combatDuration) - (System.currentTimeMillis() / 1000);
        long hours = cooldownSeconds / 3600;
        long minutes = (cooldownSeconds / 60) % 60;
        long seconds = cooldownSeconds % 60;
        combatTag = "" + hours + " hours, " + minutes + " minutes, and " + seconds + " seconds";
        if(cooldownSeconds < 1) {
            return "You are not in bounty combat";
        }
        return combatTag;
    }

    /**
     * Set combat time to current time
     * @param p player to set data for
     */
    public static void setCombatTimeCurrent(OfflinePlayer p) {

        DataUtil.setDataLong(p, "CombatTime", System.currentTimeMillis());

    }

    /**
     * Resets combat time to 0
     * @param p player to reset
     */
    public static void resetCombatTime(OfflinePlayer p) {

        DataUtil.setDataLong(p, "CombatTime", 0L);

    }

}
