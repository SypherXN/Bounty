package com.sypherxn.smpbounty.util;

import org.bukkit.OfflinePlayer;

public class StatsUtil {

    /**
     * Increments Bounty Kills Stat by 1
     * @param p player to increment
     */
    public static void incrementBountyKills(OfflinePlayer p) {

        long initial = DataUtil.getDataLong(p, "BountyKills");
        DataUtil.setDataLong(p, "BountyKills",  initial + 1);

    }

    /**
     * Get Bounty Kills stat from data
     * @param p player to get data
     * @return Long for Bounty Kill Stat
     */
    public static Long getBountyKills(OfflinePlayer p) {

        return DataUtil.getDataLong(p, "BountyKills");

    }

    /**
     * Reset Bounty Kills Stat to 0
     * @param p player to reset data
     */
    public static void resetBountyKills(OfflinePlayer p) {

        DataUtil.setDataLong(p, "BountyKills", 0L);

    }

    /**
     * Increments Bounty Fails Stat by 1
     * @param p player to increment
     */
    public static void incrementBountyFails(OfflinePlayer p) {

        long initial = DataUtil.getDataLong(p, "BountyFails");
        DataUtil.setDataLong(p, "BountyFails",  initial + 1);

    }

    /**
     * Get Bounty Fails stat from data
     * @param p player to get data
     * @return Long for Bounty Fails Stat
     */
    public static Long getBountyFails(OfflinePlayer p) {

        return DataUtil.getDataLong(p, "BountyFails");

    }

    /**
     * Reset Bounty Fails Stat to 0
     * @param p player to reset data
     */
    public static void resetBountyFails(OfflinePlayer p) {

        DataUtil.setDataLong(p, "BountyFails", 0L);

    }

    /**
     * Increments Bounty Survived Stat by 1
     * @param p player to increment
     */
    public static void incrementBountySurvived(OfflinePlayer p) {

        long initial = DataUtil.getDataLong(p, "BountyKills");
        DataUtil.setDataLong(p, "BountySurvived",  initial + 1);

    }

    /**
     * Get Bounty Survived stat from data
     * @param p player to get data
     * @return Long for Bounty Survived Stat
     */
    public static Long getBountySurvived(OfflinePlayer p) {

        return DataUtil.getDataLong(p, "BountySurvived");

    }

    /**
     * Reset Bounty Survived Stat to 0
     * @param p player to reset data
     */
    public static void resetBountySurvived(OfflinePlayer p) {

        DataUtil.setDataLong(p, "BountySurvived", 0L);

    }

}
