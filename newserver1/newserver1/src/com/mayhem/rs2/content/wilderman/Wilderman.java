package com.mayhem.rs2.content.wilderman;

import com.mayhem.rs2.entity.Location;
import com.mayhem.rs2.entity.player.Player;

public class Wilderman {

    public void enterBoat(Player player){
        if(player.getCombat().inCombat()){
            player.sendMessage("@red@You can not enter the boat while you are in combat you will let your guard down!");
        return;
        }
        player.sendMessage("Welcome back to the Vault! You have 30 minutes before you have to return to shore.");
        player.teleport(new Location(3779 ,3897,0));
    }

    public void startWilderman(Player player) {
        player.teleport(new Location(3779 ,3897,0));
        player.sendMessage("Hello welcome to the WilderMan Mode! This is very unique and only to GrandLegion.");
        player.sendMessage("Please talk to the Info Npc for more info on it if you change your mind and do");
        player.sendMessage("not want to play in this mode this will be your only time to do as this is @red@PERMANENT @bla@after.");
    }

    public void leaveWilderman(Player player){
    player.sendMessage("Please consider trying Wilderman on a alternate account later as we really suggest it!");
    player.sendMessage("It is a great experience but regular mode is just as great! Have fun.");
    //TODO: RETURN TO EDGE HOME ADD STARTER KIT ETC LIKE THEY JUST LOGGED IN
    }
}
