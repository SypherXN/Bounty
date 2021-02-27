package com.sypherxn.smpbounty.util;

import com.sypherxn.smpbounty.SMPBounty;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.io.FileWriter;
import java.util.Scanner;

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


    // Saves hashMap to a txt file
    public static void saveBullshit() {

        File df = SMPBounty.getPlugin().getDataFolder();
        File file = new File(df, "UUIDMap");

        if (!df.exists()) df.mkdir();
        if (!file.exists()) {

            try {

                file.createNewFile();
                ChatUtil.sendConsoleLog("UUID file successfully created");

            } catch(Exception e) {

                ChatUtil.sendConsoleLog("Error creating UUID file");
                return;

            }
        }

        
        try {
            
            FileWriter writer = new FileWriter(df.toString() + File.separator + "UUIDMap");
            bullshitMap.forEach((k, v) -> {

                try {

                    writer.write(k + ":" + v.toString() + "\n");

                }
                catch (IOException e){

                    ChatUtil.sendConsoleLog("Error writing");
                    return;

                }

            });


            ChatUtil.sendConsoleLog("UUID save successful");
            writer.close();
        }       
        catch(IOException e) {

            ChatUtil.sendConsoleLog("Something went wrong with the save!~");

        }

        return;
    }

    /** 
     * Reads bullshit from file, saves in bullshitMap. If file does not exist, does nothing.
    */
    public static void readBullshit() {

        File df = SMPBounty.getPlugin().getDataFolder();

        try {

            File file = new File(df.toString() + File.separator + "UUIDMap");
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {

                String line = sc.nextLine();
                int index = line.indexOf(":");
                String playerName = line.substring(0, index);
                UUID id = UUID.fromString(line.substring(index+1, line.length()));

                bullshitMap.put(playerName, id);

            }

            sc.close();


        }
        catch (Exception e) {

            ChatUtil.sendConsoleLog("Couldn't read any bullshit. Is this the first time running the plugin?");
            return;

        }

        return;

    }

}
