package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PlayerListUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class GetCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(!p.isOp()) {

            ChatUtil.sendMessage(p, "You do not have permission to use this command");
            return;

        }

        if(args.length < 2) {

            ChatUtil.sendMessage(p, "Correct Usage: /bounty get <Player Name>");
            return;

        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(PlayerListUtil.getUUID(args[1]));

        if(target == null) { 

            ChatUtil.sendMessage(p, "Player cannot be found D:");
            return; 

        }

        ChatUtil.sendMessage(p, target.getName() + " Bounty Data");
        ChatUtil.sendMessage(p, "EnableState: " + PlayerUtil.getEnableState(target));
        ChatUtil.sendMessage(p, "Hunting: " + PlayerUtil.getHunting(target).toString());
        ChatUtil.sendMessage(p, "Targeting: " + PlayerUtil.getTargeting(target).toString());
        ChatUtil.sendMessage(p, "BountyHunter: " + PlayerUtil.getBountyHunter(target).toString());
        ChatUtil.sendMessage(p, "BountyPlacer: " + PlayerUtil.getBountyPlacer(target).toString());
        ChatUtil.sendMessage(p, "PlaceCooldown: " + PlayerUtil.getRemainingPlaceCooldown(target));
        ChatUtil.sendMessage(p, "ShieldTime: " + PlayerUtil.getRemainingShield(target));
        ChatUtil.sendMessage(p, "CombatTime: " + PlayerUtil.getRemainingCombatTag(target));
        ChatUtil.sendMessage(p, "RewardItems: " + PlayerUtil.getRewardItems(target).toString());
        ChatUtil.sendMessage(p, "CollectItems: " + PlayerUtil.getCollectItems(target).toString());


    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.get; }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}