package com.mayhem.rs2.content.minigames.raids;

import java.util.ArrayList;
import java.util.List;

import com.mayhem.core.definitions.ItemDefinition;
import com.mayhem.core.util.Utility;
import com.mayhem.rs2.content.minigames.inferno.InfernoController;
import com.mayhem.rs2.entity.Location;
import com.mayhem.rs2.entity.World;
import com.mayhem.rs2.entity.item.Item;
import com.mayhem.rs2.entity.mob.Mob;
import com.mayhem.rs2.entity.object.ObjectManager;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.controllers.ControllerManager;
import com.mayhem.rs2.entity.player.net.out.impl.SendInterface;
import com.mayhem.rs2.entity.player.net.out.impl.SendMessage;
import com.mayhem.rs2.entity.player.net.out.impl.SendString;

public class Raids {
	/**
	 * The player
	 */
	Player player;
	/**
	 * The raid leader
	 */
	public Player raidLeader;
	
	
	/**
	 * Raid points
	 */
	private int raidPoints;
	
	/**
	 * Group points
	 */
	public int groupPoints;
	/**
	 * Raid Group
	 */
	Player[] raidGroup;
	/**
	 * The current path
	 */
	private int path;
	/**
	 * Current room
	 */
	public int currentRoom;

	/**
	 * Reached room
	 */
	public int reachedRoom;
	/**
	 * Monster spawns (No Double Spawning)
	 */
	public boolean lizards = false;
	public boolean vasa = false;
	public boolean vanguard = false;
	public boolean ice = false;
	public boolean chest = false;
	public boolean mystic = false;
	public boolean tekton = false;
	public boolean mutta = false;
	public boolean archers = false;
	public boolean olm = false;
	public boolean olmDead = false;
	public boolean rightHand = false;
	public boolean leftHand = false;
	
	/**
	 * The door location of the current paths
	 */
	private ArrayList<Location> roomPaths= new ArrayList<Location>();
	
	/**
	 * The names of the current rooms in path
	 */
	private  ArrayList<String> roomNames = new ArrayList<String>();
	
	/**
	 * The raids controller
	 */
	public static final RaidsController CONTROLLER = new RaidsController();
	
	/**
	 * Current monsters needed to kill
	 */
	private List<Mob> mobs = new ArrayList<Mob>();
	
	/**
	 * Constructs the raids class for the player
	 * @param player The player
	 */
	public Raids(Player player) {
		this.player=player;
	}
	
	/**
	 * Gets the height for the raid
	 * @return the height
	 */
	public int getHeight(Player player) {
		return raidLeader.getIndex()*4;
	}
	
	/**
	 * Get points
	 */
	public int getPoints() {
		return raidPoints;
	}
	/**
	 * Add points
	 */
	public void addPoints(int points) {
		raidPoints += points;
	}
	/**
	 * Add a monster to monsters to kill
	 */
	public void addMob(Mob mob) {
		mobs.add(mob);
	}
	/**
	 * Gets current monsters left in room.
	 */
	public List<Mob> getMobs() {
		return mobs;
	}
	
	/**
	 * Removes Mob
	 */
	public boolean removeMob(Mob mob) {
		int index = mobs.indexOf(mob);

		if (index == -1) {
			return false;
		}

		mobs.remove(mob);
		return true;
	}
	/**
	 * Gets the current path
	 * @return the path
	 */
	public int getPath() {
		return path;
	}
	
	/**
	 * Sets the current path
	 * @param path
	 */
	public void setPath(int path) {
		this.path=path;
	}
	
	/**
	 * Gets the start location for the path
	 * @return path
	 */
	public Location getStartLocation() {
		switch(path) {
		case 0:
			return RaidRooms.STARTING_ROOM.doorLocation;
		case 1:
			return RaidRooms.STARTING_ROOM_2.doorLocation;
		}
		return RaidRooms.STARTING_ROOM.doorLocation;
	}
	/**
	 * Handles raid rooms
	 * @author Goon
	 *
	 */
	public enum RaidRooms{
		STARTING_ROOM("start_room",0,new Location(3279,5187)),
		LIZARDMEN_SHAMANS("lizardmen",0,new Location(3280,5249)),
		VASA_NISTIRIO("vasa",0,new Location(3280,5281)),
		VANGUARDS("vanguard",0,new Location(3280,5313)),
		ICE_DEMON("ice",0,new Location(3280,5345)),
		CHEST_ROOM("chest",0,new Location(3280,5378)),
		//SCAVENGER_ROOM("scavenger",0,new Location(3279,5217,1)),
		SKELETAL_MYSTIC_2("skeletal",0,new Location(3279,5248,1)),
		TEKTON("tekton",0,new Location(3279,5280,1)),
		MUTTADILE("muttadile",0,new Location(3279,5312,1)),
		ARCHERS_AND_MAGERS("archer",0,new Location(3279,5345,1)),
		ENERGY_ROOM("energy",0,new Location(3275,5159)),
		OLM_ROOM_WAIT("olm_wait",0,new Location(3232,5721)),
		OLM_ROOM("olm",0,new Location(3232,5730)),
		
		STARTING_ROOM_2("start_room",1,new Location(3299,5189)),
		LIZARDMEN_SHAMANS_2("lizardmen",1,new Location(3308,5208)),
		VASA_NISTIRIO_2("vasa",1,new Location(3312,5279)),
		VANGUARDS_2("vanguard",1,new Location(3312,5311)),
		ICE_DEMON_2("ice",1,new Location(3313,5346)),
		CHEST_ROOM_2("chest",1,new Location(3311,5374)),
		//SCAVENGER_ROOM_2("scavenger",1,new Location(3343,5217,1)),
		SKELETAL_MYSTIC("skeletal",1,new Location(3312,5217,1)),
		TEKTON_2("tekton",1,new Location(3310,5277,1)),
		MUTTADILE_2("muttadile",1,new Location(3311,5309,1)),
		ARCHERS_AND_MAGERS_2("archer",1,new Location(3309,5340,1)),
		ENERGY_ROOM_2("energy",1,new Location(3275,5159)),
		OLM_ROOM_WAIT_2("olm_wait",1,new Location(3232,5721)),
		OLM_ROOM_2("olm",1,new Location(3232,5730));
		
		private Location doorLocation;
		private int path;
		private String roomName;
		
		private RaidRooms(String name,int path1,Location door) {
			doorLocation=door;
			roomName=name;
			path=path1;
			
		}
		
		public Location getDoor() {
            return doorLocation;
        }

        public int getPath() {
            return path;
        }

        public String getRoomName() {
            return roomName;
        }
		
		
	}
	
	public void updateRaidPoints() {
		player.send(new SendString("Total: @whi@"+groupPoints,17502));
		player.send(new SendString(player.getUsername()+": @whi@"+raidPoints,17503));
	}
	/**
	 * Kill all spawns for the raid leader if left
	 * @param player
	 */
	public void killAllSpawns(Player player) {
		for (Mob i : getMobs()) {
			if (i != null) {
				i.remove();
			}
		}
		getMobs().clear();

		player.setController(ControllerManager.DEFAULT_CONTROLLER);
	}
	
	/**
	 * Leaves the raid.
	 * @param player
	 */
	public void leaveGame(Player player) {
		if (System.currentTimeMillis() - player.infernoLeaveTimer < 15000) {
			player.send(new SendMessage("You cannot leave yet, wait a couple of seconds and try again."));
			return;
		}
		killAllSpawns(player);
		getMobs().clear();
		player.setController(ControllerManager.DEFAULT_CONTROLLER);
		player.send(new SendMessage("@red@You have left the Chambers of Xeric."));
		player.teleport(new Location(1245, 3561, 0));
		reachedRoom = 0;
		lizards = false;
		vasa = false;
		vanguard = false;
		ice = false;
		chest = false;
		mystic = false;
		tekton = false;
		mutta = false;
		archers = false;
		olm = false;
		olmDead = false;
		rightHand = false;
		leftHand = false;
	
	}
	/**
	 * Starts the raid.
	 */
	public void startRaid() {
		if (player.clan == null || !player.clan.isFounder(player.getUsername())) {
			player.sendMessage("You're not in a clan that you own, and can not pass the door.");
			return;
		}
		
		int memberCount = player.clan.activeMembers.size();
		
		if (memberCount < 1) {
			player.sendMessage("You don't have enough people in your clan to start a raid.");
			return;
		}
		
		if (memberCount > 22) {
			player.sendMessage("Your clan exceeds the max limit of 22 players in Raids.");
			return;
		}
		
		raidLeader=player;
		int path1 = Utility.random(1);
		path = path1;
		raidPoints = 0;
		for (String username : player.clan.activeMembers) {
			Player p = World.getPlayerByName(username);
			if (p == null || !p.inRaidsMountain()) {
				continue;
			}
					p.getRaids().raidLeader = player;
					p.getRaids().path = path1;
					p.getRaids().raidPoints=0;
					for(RaidRooms room : RaidRooms.values()) {
						if(room.getPath() == path) {
						p.getRaids().roomNames.add(room.getRoomName());
						p.getRaids().roomPaths.add(room.getDoor());
						}
					}
					p.getRaids().updateRaidPoints();
					p.teleport(new Location(getStartLocation().getX(),getStartLocation().getY(),getHeight(player)));
					p.sendMessage("@red@Welcome to the Chambers of Xeric!");
					p.setController(ControllerManager.RAIDS_CONTROLLER);
			}
	}
	
	int[] rarerewards = {22296, 20517, 20520, 20595, 20784, 21000, 21043, 21009, 21012, 21015, 21018, 21021, 21024, 21028, 20849, 20997, 9067, 11035};
	int[] commonrewards = {560,6038,565,566,892,11212,3050,208,210,212,214,3052,216,2486,218,220,443, 454, 445, 448, 450, 452, 1624, 1622, 1620, 1618, 13391, 7937, 2724}; //{item, maxAmount}
	
	/**
	 * Handles giving the raid reward
	 */
	public void giveReward() {
		int rewardChance = Utility.random(200);
		if(rewardChance >= 197) {
			giveRareReward();
		}else {
			giveCommonReward();
		}
	}
	/**
	 * Handles giving a rare reward.
	 */
	public void giveRareReward() {
		//p.gfx0(1368);
		int rareitem = 0;
		rareitem = Utility.random(rarerewards.length-1);
		if(rareitem < 0) {
			rareitem = Utility.random(rarerewards.length);
		}
		player.raidReward[0][0] = rarerewards[rareitem];
		World.sendGlobalMessage("@red@" + player.getUsername() + " has received a rare item @red@"+ Item.getDefinition(player.raidReward[0][0]).getName() + " from raids!");
		if(player.raidReward[0][0] == 20849) {
			player.raidReward[0][1] = 500;
		}else {
			player.raidReward[0][1] = 1;
		}
		
		//p.getItems().addItem(player.raidReward[0][0], player.raidReward[0][1]);
	}
	/**
	 * Handles giving a common reward
	 */
	public void giveCommonReward() {
		//p.gfx0(277);
		int commonitem = 0;
		commonitem = Utility.random(commonrewards.length-1);
		player.raidReward[0][0] = commonrewards[commonitem];
		
		switch(player.raidReward[0][0]) {
		case 560://death rune
		player.raidReward[0][1] = Utility.random(2500);
		break;
		case 565://blood rune
		player.raidReward[0][1] = Utility.random(1250);
		break;
		case 566://soul rune
		player.raidReward[0][1] = Utility.random(1000);	
		break;
		case 892://rune arrow
		player.raidReward[0][1] = Utility.random(2000);	
		break;
		case 11212://dragon arrow
		player.raidReward[0][1] = Utility.random(926);	
		break;
		case 3050://grimy toadflax
		player.raidReward[0][1] = Utility.random(354);		
		break;
		case 208://grimy rannar
		player.raidReward[0][1] = Utility.random(164);			
		break;
		case 6038://bow string
		player.raidReward[0][1] = Utility.random(1);			
		break;
		case 20997://pray scroll
		player.raidReward[0][1] = Utility.random(1);			
		break;
		case 210://grimy irit
		player.raidReward[0][1] = Utility.random(668);			
		break;
		case 212://grimy avantoe
		player.raidReward[0][1] = Utility.random(354);				
		break;
		case 214://grimy kwuarm
		player.raidReward[0][1] = Utility.random(323);				
		break;
		case 3052://grimy snapdragon
		player.raidReward[0][1] = Utility.random(131);					
		break;
		case 216://grimy cadatine
		player.raidReward[0][1] = Utility.random(319);					
		break;
		case 2486://grimy lantadyme
		player.raidReward[0][1] = Utility.random(446);	
		break;
		case 218://grimy dwarf weed
		player.raidReward[0][1] = Utility.random(616);					
		break;
		case 220://grimy torsol
		player.raidReward[0][1] = Utility.random(153);						
		break;
		case 443://silver ore
		player.raidReward[0][1] = Utility.random(1000);						
		break;
		case 454://coal
		player.raidReward[0][1] = Utility.random(2500);	
		break;
		case 445://gold ore
		player.raidReward[0][1] = Utility.random(500);		
		break;
		case 448://mithril ore
		player.raidReward[0][1] = Utility.random(1000);						
		break;
		case 450://adamant ore
		player.raidReward[0][1] = Utility.random(729);	
		break;
		case 452://runite ore
		player.raidReward[0][1] = Utility.random(87);		
		break;
		case 1624://uncut sapphire
			player.raidReward[0][1] = Utility.random(642);			
		break;
		case 1622://uncut emerald
			player.raidReward[0][1] = Utility.random(923);							
		break;
		case 1620://uncut ruby
			player.raidReward[0][1] = Utility.random(524);	
		break;	
		case 1618: //uncut diamond
		player.raidReward[0][1] = Utility.random(253);
		break;	
		case 13391: //lizard fang
			player.raidReward[0][1] = Utility.random(4898);	
		break;
		case 7937://pure ess
			player.raidReward[0][1] = Utility.random(10000);
		break;	
		case 2724://hard casket
			player.raidReward[0][1] = 1;	
		break;	
				
							
		}
	}
	
	final int OLM = 7554;
	final int OLM_RIGHT_HAND= 7553;
	final int OLM_LEFT_HAND = 7555;
	
	public void handleMobDeath(Mob mob) {
		player.getRaids().raidLeader.getRaids().removeMob(mob);
		switch(mob.getId()) {
		case OLM:
		//ObjectManager.deleteWithObject(3220, 5743, getHeight(player));
		ObjectManager.deleteWithObject( 3232, 5749, getHeight(player));
			ObjectManager.spawnWithObject(29885, 3220, 5743, getHeight(player), 10, 3);
			ObjectManager.spawnWithObject(29888, 3220, 5733, getHeight(player), 10, 3);
			ObjectManager.spawnWithObject(29882, 3220, 5738, getHeight(player), 10, 3);
			ObjectManager.spawnWithObject(30028, 3233, 5751, getHeight(player), 10, 4);
			player.getUpdateFlags().setUpdateRequired(true);
		player.getObjects().onRegionChange();
		for (String username : player.getRaids().raidLeader.clan.activeMembers) {
		Player p = World.getPlayerByName(username);
		if (!p.inRaids()) {
			continue;
		}
				p.getRaids().giveReward();
				p.raidCount+=1;
				p.sendMessage("@red@Congratulations you have defeated The Great Olm and completed the raid!");
				p.sendMessage("@red@You have completed "+p.raidCount+" raids." );
				p.getInventory().add(p.raidReward[0][0], p.raidReward[0][1]);
			}
		
		return;
		case OLM_RIGHT_HAND:
			player.getRaids().raidLeader.getRaids().rightHand = true;
			if(player.getRaids().raidLeader.getRaids().leftHand == true) {
				player.sendMessage("@red@ You have defeated both of The Great Olm's hands he is now vulnerable.");
			}else {
					player.sendMessage("@red@ You have defeated one of The Great Olm's hands destroy the other one quickly!");	
				}	
		return;
		case OLM_LEFT_HAND:
			player.getRaids().raidLeader.getRaids().leftHand = true;
		if(player.getRaids().raidLeader.getRaids().rightHand == true) {
		player.sendMessage("@red@ You have defeated both of The Great Olm's hands he is now vulnerable.");
		}else {
			player.sendMessage("@red@ You have defeated one of The Great Olm's hands destroy the other one quickly!");	
		}
		return;
		}
		int randomPoints = Utility.random(500);
		player.getRaids().addPoints(randomPoints);
		player.sendMessage("@red@You have killed a "+mob.getDefinition().getName()+". +"+randomPoints+" points");
		if(player.getRaids().raidLeader.getRaids().getMobs().size() == 0) {
			player.sendMessage("@red@The room has been cleared and you are free to pass.");
		}else {
			player.sendMessage("@red@There are "+player.getRaids().raidLeader.getRaids().getMobs().size()+" enemies remaining.");	
		}
		//player.sendMessage("test");
		player.getRaids().updateRaidPoints();
	}
	/**
	 * Spawns npc for the current room
	 * @param currentRoom The room
	 */
	public void spawnNpcs(int currentRoom) {

		Mob mob = null;
		Mob mob1 = null;
		Mob mob2 = null;
		
		int height = player.getRaids().getHeight(player);
		
		switch(player.getRaids().roomNames.get(currentRoom)) {
		case "lizardmen":
			if(lizards) {
				return;
			}
			if(path == 0) {
			mob = new Mob(7573, false,false, new Location(3274,5262,height));
			mob1 = new Mob(7573, false,false, new Location(3282,5266,height));
			mob2 = new Mob(7573, false,false, new Location(3275,5269,height));
			}else {
			mob = new Mob(7573, false,false, new Location(3307,5265,height));
			mob1 = new Mob(7573, false,false, new Location(3314,5265,height));
			mob2 = new Mob(7573, false,false, new Location(3314,5261,height));
			}
		lizards = true;
		break;
		case "vasa":
			if(vasa) {
				return;
			}
			if(path == 0) {
				mob = new Mob(7566, false,false, new Location(3280,5295,height));
				}else {
				mob = new Mob(7566, false,false, new Location(3311,5295,height));
				}
		vasa = true;
		break;
		case "vanguard":
			if(vanguard) {
				return;
			}
			if(path == 0) {
				mob = new Mob(7527, false,false, new Location(3277,5326,height));
				mob1 = new Mob(7528, false,false, new Location(3277,5332,height));
				mob2 = new Mob(7529, false,false, new Location(3285,5329,height));
				}else {
				mob = new Mob(7527, false,false, new Location(3310,5324,height));
				mob1 = new Mob(7528, false,false, new Location(3310,5331,height));
				mob2 = new Mob(7529, false,false, new Location(3316,5331,height));
				}
			vanguard = true;
		break;
		case "ice":
			if(ice) {
				return;
			}
			if(path == 0) {
				mob = new Mob(7584, false,false, new Location(3273,5365,height));
				}else {
				mob = new Mob(7584, false,false, new Location(3310,5367,height));
				}
		ice = true;
		break;
		case "chest":
			
		break;
		case "scavenger":
			
		break;
		case "skeletal":
			if(mystic) {
				return;
			}
			if(path == 0) {
				mob = new Mob(7604, false,false, new Location(3279,5271,height+1));
				mob1 = new Mob(7605, false,false, new Location(3290,5268,height+1));
				mob2 = new Mob(7606, false,false, new Location(3279,5264,height+1));
				}else {
				mob = new Mob(7604, false,false, new Location(3318,5262,height+1));
				mob1 = new Mob(7605, false,false, new Location(3307,5258,height+1));
				mob2 = new Mob(7606, false,false, new Location(3301,5262,height+1));
				}	
			mystic = true;
		break;
		case "tekton":
			if(tekton) {
				return;
			}
			if(path == 0) {
				mob = new Mob(7542, false,false, new Location(3280,5295,height+1));
				}else {
				mob = new Mob(7542, false,false, new Location(3312,5293,height+1));
				}
		tekton = true;
		break;
		case "muttadile":
			if(mutta) {
				return;
			}
			if(path == 0) {
				mob = new Mob(7563, false,false, new Location(3276,5331,height+1));
				}else {
				mob = new Mob(7563, false,false, new Location(3308,5331,height+1));
				}
		mutta = true;
		break;
		case "archer":
			if(archers) {
				return;
			}
			if(path == 0) {
				Mob archer1 = new Mob(7559, false, false, new Location(3287,5364,height+1));
				Mob archer2 = new Mob(7559, false, false, new Location(3287,5363,height+1));
				Mob archer3 = new Mob(7559, false, false, new Location(3285,5363,height+1));
				Mob archer4 = new Mob(7559, false,false, new Location(3285,5364,height+1));
				Mob mage1 = new Mob(7560, false,false, new Location(3286,5369,height+1));
				Mob mage2 = new Mob(7560, false,false, new Location(3284,5369,height+1));
				Mob mage3 = new Mob(7560, false,false, new Location(3286,5370,height+1));
				Mob mage4 = new Mob(7560, false,false, new Location(3284,5370,height+1));
				addMob(archer1);
				addMob(archer2);
				addMob(archer3);
				addMob(archer4);
				addMob(mage1);
				addMob(mage2);
				addMob(mage3);
				addMob(mage4);
				}else {
				Mob archer1 = new Mob(7559, false,false, new Location(3319,5363,height+1));
				Mob archer2 = new Mob(7559, false,false, new Location(3317,5363,height+1));
				Mob archer3 = new Mob(7559, false,false, new Location(3317,5364,height+1));
				Mob archer4 = new Mob(7559, false,false, new Location(3319,5364,height+1));			
				Mob mage1 = new Mob(7560, false,false, new Location(3318,5370,height+1));
				Mob mage2 = new Mob(7560, false,false, new Location(3318,5369,height+1));
				Mob mage3 = new Mob(7560, false,false, new Location(3316,5369,height+1));
				Mob mage4 = new Mob(7560, false,false, new Location(3316,5370,height+1));
				addMob(archer1);
				addMob(archer2);
				addMob(archer3);
				addMob(archer4);
				addMob(mage1);
				addMob(mage2);
				addMob(mage3);
				addMob(mage4);
				
				}	
		archers = true;
		break;
		case "olm":
		if(olm) {
			return;
		}
		
		mob = new Mob(7553, false,false, new Location(3224,5734,height));
		mob1 = new Mob(7554, false,false, new Location(3224,5737,height));
		mob2 = new Mob(7555, false,false, new Location(3224,5743,height));
		ObjectManager.spawnWithObject(29884, 3220, 5743, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(29887, 3220, 5733, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(29881, 3220, 5737, getHeight(player), 10, 3);
		
		ObjectManager.spawnWithObject(2376, 3227, 5732, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5733, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5734, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5735, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5736, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5737, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5738, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5739, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5740, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5741, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5742, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5743, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5744, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5745, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5746, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5747, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5748, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5749, getHeight(player), 10, 3);
		ObjectManager.spawnWithObject(2376, 3227, 5750, getHeight(player), 10, 3);
		mob.setWalks(false);
		mob.setRetaliate(false);
		mob2.setRetaliate(false);
		mob2.setWalks(false);
		mob1.setWalks(false);
		olm = true;
		
		break;
		}
		if(mob!=null) {
		addMob(mob);
		}
		if(mob1!=null) {
		addMob(mob1);
		}
		if(mob2!=null) {
		addMob(mob2);
		}
		reachedRoom+=1;
	}
	/**
	 * Handles object clicking for raid objects
	 * @param player The player
	 * @param objectId The object id
	 * @return
	 */
	public boolean handleObjectClick(Player player, int objectId) {
		switch(objectId) {
		case 29789:
		case 29734:
		case 29879:
		player.getRaids().nextRoom();
		return true;
		case 29777:
		player.getRaids().startRaid();	
		return true;
		case 30066:
			
		return true;
		
		case 29778:
		player.getRaids().leaveGame(player);
		break;
		
		case 30028:
		player.send(new SendInterface(57000));
		return true;
		}
		return false;
	}
	/**
	 * Goes to the next room, Handles spawning etc.
	 */
	public void nextRoom() {
		if(player.getRaids().raidLeader.getRaids().getMobs().size() > 0 && currentRoom != 0 && player.getRaids().currentRoom == player.getRaids().raidLeader.getRaids().currentRoom) {
		player.sendMessage("@red@Please defeat all the monsters before going to the next room.");
		return;
		}
		if(player.getRaids().raidLeader.getUsername() != player.getUsername()) {
		if(player.getRaids().currentRoom + 1 < player.getRaids().raidLeader.getRaids().reachedRoom){
			return;
		}}
		
		player.teleport(new Location(roomPaths.get(currentRoom+1).getX(),roomPaths.get(currentRoom+1).getY(),roomPaths.get(currentRoom+1).getZ() == 1 ? getHeight(player) + 1 :getHeight(player)));
		
		player.getRaids().updateRaidPoints();
		currentRoom+=1;
		
		if(player.getRaids().raidLeader.getUsername() != player.getUsername()) {
			return;
		}
		spawnNpcs(currentRoom);
	}
}
