package com.sypherxn.smpbounty;

import com.sypherxn.smpbounty.commands.CommandManager;
import com.sypherxn.smpbounty.listeners.Listeners;
import com.sypherxn.smpbounty.util.PlayerListUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMPBounty extends JavaPlugin {

    private static SMPBounty plugin;
    public CommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        commandManager = new CommandManager();
        getCommand("bounty").setExecutor(commandManager);
        commandManager.setup();
        getServer().getPluginManager().registerEvents(new Listeners(), this);

        PlayerListUtil.readBullshit();

        for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {

            PlayerListUtil.updateEntry(p.getUniqueId());

        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PlayerListUtil.saveBullshit();

    }

    public static SMPBounty getPlugin() {
        return plugin;
    }
}
