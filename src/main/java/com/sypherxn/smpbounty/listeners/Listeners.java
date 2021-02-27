package com.sypherxn.smpbounty.listeners;

import com.sypherxn.smpbounty.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class Listeners implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerLoginEvent e) {

        new FileUtil(e.getPlayer()).create();

    }

}
