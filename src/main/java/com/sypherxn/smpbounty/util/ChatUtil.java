package com.sypherxn.smpbounty.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class ChatUtil {

    private static String prefix = ChatColor.GOLD.toString() + ChatColor.BOLD + ChatColor.ITALIC.toString() + "<SMPBounty>" + ChatColor.RESET.toString() + ChatColor.AQUA;

    /**
     * Send message to player with prefix
     * @param p player to send message to
     * @param message message to send
     */
    public static void sendMessage(OfflinePlayer p, String message) {

        Player player = Bukkit.getPlayer(p.getUniqueId());

        if(player.isOnline()) {

            player.sendMessage(prefix + message);

        }

    }

    /**
     * Broadcast message to server with prefix
     * @param message message to broadcast
     */
    public static void sendBroadcast(String message) {

        Bukkit.broadcastMessage(prefix + message);

    }

    /**
     * Send message in server console
     * @param message message to send
     */
    public static void sendConsoleLog(String message) {

        Bukkit.getConsoleSender().sendMessage("<SMPBounty>: " + message);

    }

}
