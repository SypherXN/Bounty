package com.sypherxn.smpbounty.util;

import com.sypherxn.smpbounty.SMPBounty;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    private File pFile;
    private FileConfiguration config;
    private File df = SMPBounty.getPlugin().getDataFolder();
    private File folder = new File(df, "playerdata" + File.separator);

    private OfflinePlayer p;

    /**
     * Constructor for an offline player
     * @param player offline player that a file is needed for
     */
    public FileUtil(OfflinePlayer player) {

        p = player;

    }

    /**
     * Constructor for an online player
     * @param player player that a file is needed for
     */
    public FileUtil(Player player) {

        p = (OfflinePlayer) player;

    }

    /**
     * Creates .yml file for the player
     */
    public void create() {

        pFile = new File(df, "playerdata" + File.separator + p.getUniqueId() + ".yml");
        if(!df.exists()) df.mkdir();
        if(!folder.exists()) folder.mkdir();
        if(!pFile.exists()) {

            try {

                pFile.createNewFile();
                Bukkit.getConsoleSender().sendMessage(("File Create: " + pFile.getName()));

            } catch(Exception e) {

                Bukkit.getConsoleSender().sendMessage("Error creating " + pFile.getName());

            }

        }

    }

    /**
     * Get the file for a player
     * @return the File associated with the player's UUID
     */
    public File getFile() {

        if(pFile == null) { create(); }
        return pFile;

    }

    /**
     * Get the config from the file
     * @return the file configuration
     */
    public FileConfiguration getConfig() {

        if(config == null) {

            config = YamlConfiguration.loadConfiguration(getFile());

        }

        return config;

    }

    /**
     * Sets an object to a certain path
     * @param path the prefix in the .yml
     * @param object the object to set
     */
    public void set(String path, Object object) {

        getConfig().set(path, object);
        saveConfig();

    }

    /**
     * Get object back based on path
     * @param path prefix in the .yml
     * @return the object attached to the key
     */
    public String get(String path) {

        return (String) getConfig().get(path);

    }

    /**
     * Saves config to playerdata file
     */
    public void saveConfig() {

        try {

            config.save(pFile);
            Bukkit.getConsoleSender().sendMessage(pFile.toString() + " Successfully Saved");

        } catch(IOException e) {

            Bukkit.getConsoleSender().sendMessage(pFile.toString() + " Failed To Save");

        }

    }

    /**
     * Reload the config
     */
    public void reloadConfig() {

        YamlConfiguration.loadConfiguration(pFile);

    }

}
