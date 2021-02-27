package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import org.bukkit.OfflinePlayer;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CancelCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        //Check to see if player can run this command
        if(!PlayerUtil.hasTargeting(p)) {

            ChatUtil.sendMessage(p,"You have not placed a bounty yet");
            return;

        }
        
        UUID targetUUID = PlayerUtil.getTargeting(p);
        OfflinePlayer target = Bukkit.getOfflinePlayer(targetUUID);

        if(target == null) { 

            ChatUtil.sendMessage(p, "Player cannot be found D:");
            return; 

        }

        if(PlayerUtil.hasBountyHunter(target)) {

            ChatUtil.sendMessage(p, "You cannot cancel a bounty somebody has already accepted");
            return;

        }

        //Update information for the player running the command
        PlayerUtil.resetTargeting(p);
        PlayerUtil.addCollectItems(p, PlayerUtil.getRewardItems(target));
        ChatUtil.sendMessage(p, "You have successfully removed the bounty off of " + target.getName());
        ChatUtil.sendMessage(p, "Your bounty has been placed into your /bounty collect");

        //Update information for the player being hunted
        PlayerUtil.resetBountyPlacer(target);
        PlayerUtil.resetRewardItems(target);
        ChatUtil.sendMessage(target, p.getName() + " has removed their bounty on you");

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.cancel; }

    @Override
    public String info() { return "Cancel a bounty you have put out"; }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}