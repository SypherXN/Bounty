package com.sypherxn.smpbounty;

import com.sypherxn.smpbounty.listeners.Listeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMPBounty extends JavaPlugin {

    private static SMPBounty plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new Listeners(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SMPBounty getPlugin() {
        return plugin;
    }
}
