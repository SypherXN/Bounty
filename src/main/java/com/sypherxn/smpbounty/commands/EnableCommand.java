package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.entity.Player;

public class EnableCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(PlayerUtil.isEnabled(p)) {

            ChatUtil.sendMessage(p, "You are already bounty-enabled!");
            return;

        }

        PlayerUtil.setEnableState(p, "Enabled");
        ChatUtil.sendMessage(p, "You are now bounty-enabled!");
        ChatUtil.sendMessage(p, "Use /bounty help for more information!");

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.enable; }

    @Override
    public String info() {
        return "Enables participation in bounty system";
    }

    @Override
    public String[] aliases() {
        String[] alias = {"e"};
        return alias;
    }
}