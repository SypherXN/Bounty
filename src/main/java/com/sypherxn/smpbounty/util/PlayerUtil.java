package com.sypherxn.smpbounty.util;

import org.bukkit.OfflinePlayer;

public class PlayerUtil {

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
     * Finds out if the player has a bounty on them
     * @param p player to check if they have a bounty on them
     * @return true if bounty is on them and false if no bounty on them
     */
    public static Boolean hasBountyPlacer(OfflinePlayer p) {

        if(DataUtil.getDataUUID(p, "BountyPlacer").equals(DataUtil.defaultUUID)) return false;
        return true;

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
     * Finds out if the player has placed a bounty
     * @param p player to check if they have placed a bounty
     * @return true if they have placed a bounty and false if they have not placed a bounty
     */
    public static Boolean hasTargeting(OfflinePlayer p) {

        if(DataUtil.getDataUUID(p, "Targeting").equals(DataUtil.defaultUUID)) return false;
        return true;

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
     * Finds out if the player has bounty rewards on them
     * @param p player to check if they have a bounty reward on them
     * @return true if they have bounty rewards and false if they don't have bounty rewards
     */
    public static Boolean hasRewardItems(OfflinePlayer p) {

        if(!DataUtil.getDataItemList(p, "RewardItems").isEmpty()) return false;
        return true;

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

}
