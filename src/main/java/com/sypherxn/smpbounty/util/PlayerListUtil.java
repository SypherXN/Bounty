package com.sypherxn.smpbounty.util;

import com.sypherxn.smpbounty.SMPBounty;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerListUtil {

    private static HashMap<String, UUID> bullshitMap = new HashMap<String, UUID>();


    public static void updateEntry(UUID id) {

        ChatUtil.sendConsoleLog("Added " + Bukkit.getOfflinePlayer(id).getName() + " to the HashMap");
        bullshitMap.put(Bukkit.getOfflinePlayer(id).getName(), id);
        return;

    }

    public static UUID getUUID(String name) {

        return bullshitMap.get(name);

    }

}
