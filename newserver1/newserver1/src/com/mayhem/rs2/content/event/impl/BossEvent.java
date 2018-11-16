package com.mayhem.rs2.content.event.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.mayhem.core.util.Utility;
import com.mayhem.rs2.content.event.Event;
import com.mayhem.rs2.entity.Location;
import com.mayhem.rs2.entity.World;
import com.mayhem.rs2.entity.item.Item;
import com.mayhem.rs2.entity.item.impl.GroundItem;
import com.mayhem.rs2.entity.item.impl.GroundItemHandler;
import com.mayhem.rs2.entity.mob.Mob;
import com.mayhem.rs2.entity.mob.impl.EnragedCerberus;
import com.mayhem.rs2.entity.mob.impl.EnragedGeneralGraador;
import com.mayhem.rs2.entity.mob.impl.EnragedSkitzo;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.net.out.impl.SendMessage;

/**
 * @author Andy || ReverendDread Mar 29, 2017
 */
public class BossEvent extends Event {
					
	/**
	 * Enum containing spawn locations for event bosses.
	 * @author Andy || ReverendDread Mar 29, 2017
	 */
	private enum SpawnLocations {
		
		BLACK_NIGHTS_FORTRESS("Black Knights Fortess@red@(15)", new Location(3005, 3633)),
		CHAOS_ATLAR("Chaos Altar@red@(13)", new Location(3238, 3621)),
		ROGUES_CASTLE("Rogue's Castle@red@(52)", new Location(3306, 3934)),
		DEMONIC_RUINS("Demonic Ruins@red@(45)", new Location(3255, 3875)),
		EAST_DRAGONS("East dragons@red@(19)", new Location(3305, 3667)),
		;
		
		private String locationName;
		private Location spawnLocation;
		
		private SpawnLocations(String locationName, Location spawnLocation) {
			this.locationName = locationName;
			this.spawnLocation = spawnLocation;
		}

		/**
		 * Gets the locationName.
		 * @return the locationName
		 */
		public String getLocationName() {
			return locationName;
		}

		/**
		 * Gets the spawnLocation.
		 * @return the spawnLocation
		 */
		public Location getSpawnLocation() {
			return spawnLocation;
		}
		
		private static final List<SpawnLocations> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
		private static final int SIZE = VALUES.size();
		private static final Random RANDOM = new Random();

		/**
		 * Gets a random location.
		 * @return the location data.
		 */
		public static SpawnLocations getRandomLocation()  {
			return VALUES.get(RANDOM.nextInt(SIZE));
		}
		
	}
	
	//Fields.
	private Mob boss;
	SpawnLocations location = SpawnLocations.getRandomLocation();
	
	@Override
	public boolean start() {	
		int random = Utility.random(3);	
		GroundItem bloodKey = new GroundItem(new Item(20526,1), location.spawnLocation, -1);
		GroundItemHandler.add(bloodKey);
		bloodKey.isGlobal=true;
		sendMessage("A blood key @red@has spawned near " + location.getLocationName() + "!");
		return true;
	}

	@Override
	public boolean preStartupCheck() {

		System.err.println("---[EVENT]--- Boss has spawned null.");
		return false;
	}

	private int duration;
	
	@Override
	public int process() {
		duration++;
		if (boss.isDead()) {
			sendMessage("@red@The event is now over, the boss has been killed.");
			//ObjectManager.spawnWithObject(new BossChest(boss.getX(), boss.getY(), boss.getZ()));
			for (Player player : World.getPlayers()) {
				if (player == null)
					continue;
				if (player.getLocation().inBossEvent()) {
					GroundItemHandler.add(new Item(20526, 1), player.getLocation(), player, player.ironPlayer() ? player : null);
					player.send(new SendMessage("@red@Use the Key on the Event Chest at home to receive your rewards!"));
				}
			}
			return -1; //Ends event.
		}
		if (duration >= 3000) {
			sendMessage("The event is now over, the boss has disappeared.");
			stop();
			return -1;
		}
		return 1;
	}

	@Override
	public void stop() {
			
	}

}
