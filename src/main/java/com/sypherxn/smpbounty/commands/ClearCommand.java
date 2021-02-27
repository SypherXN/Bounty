package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.OfflinePlayer;
import org.bukkit.Bukkit;

public class ClearCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(!p.isOp()) {

            ChatUtil.sendMessage(p, "You do not have permission to use this command");
            return;

        }

        if(args.length < 2) {

            ChatUtil.sendMessage(p, "Correct Usage: /bounty clear <Player Name>");
            return;

        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

        if(target == null) { 

            ChatUtil.sendMessage(p, "Player cannot be found D:");
            return; 

        }

        PlayerUtil.setEnableState(target, "Disabled");
        PlayerUtil.resetPlaceCooldownTime(target);
        PlayerUtil.resetShieldTime(target);
        PlayerUtil.resetBountyPlacer(target);
        PlayerUtil.resetRewardItems(target);
        PlayerUtil.resetTargeting(target);
        PlayerUtil.resetHunting(target);
        PlayerUtil.resetCollectItems(target);
        PlayerUtil.resetBountyHunter(target);

        ChatUtil.sendMessage(p, "You have successfully cleared " + target.getName() + "'s bounty data");

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.clear; }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}