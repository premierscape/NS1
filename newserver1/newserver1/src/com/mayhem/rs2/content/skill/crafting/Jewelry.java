package com.mayhem.rs2.content.skill.crafting;

import java.util.HashMap;
import java.util.Map;

import com.mayhem.rs2.entity.item.Item;

public enum Jewelry {
	
	/* Rings */
	GOLD_RING(1635, 5, 15,	2357),
	SAPPHIRE_RING(1637, 20, 40, 2357, 1607),
	EMERALD_RING(1639, 27, 55, 2357, 1605),
	RUBY_RING(1641, 34, 70, 2357, 1603),
	DIAMOND_RING(1643, 43, 85, 2357, 1601),
	DRAGONSTONE_RING(1645, 55, 100, 2357, 1615),
	ONYX_RING(6575, 67, 85, 2357, 6573),
	
	/* Necklace */
	GOLD_NECKLACE(1654, 6, 20, 2357),
	SAPPHIRE_NECKLACE(1656, 22, 55, 2357, 1607),
	EMERALD_NECKLACE(1658, 29, 60, 2357, 1605),
	RUBY_NECKLACE(1660, 40, 75, 1597, 2357, 1603),
	DIAMOND_NECKLACE(1662, 56, 90, 2357, 1601),
	DRAGONSTONE_NECKLACE(1664, 72, 105, 2357, 1615),
	ONYX_NECKLACE(6577, 82, 120, 2357, 6573),
	
	/* Amulet */
	GOLD_AMULET(1673, 8, 30, 2357),
	SAPPHIRE_AMULET(1675, 24, 65, 2357, 1607),
	EMERALD_AMULET(1677, 31, 61, 2357, 1605),
	RUBY_AMULET(1679, 50, 85, 2357, 1603),
	DIAMOND_AMULET(1681, 70, 100, 2357, 1601),
	DRAGONSTONE_AMULET(1683, 85, 125, 2357, 1615),
	ONYX_AMULET(6579, 90, 150, 2357, 6573);
	

	public static final void declare() {
		for (Jewelry jewel : values()) {
			jewelry.put(Integer.valueOf(jewel.getReward().getId()), jewel);
		}
	}

	private Item reward;
	private short levelRequired;
	private double experienceGain;
	private int[] materialsRequired;

	private static Map<Integer, Jewelry> jewelry = new HashMap<Integer, Jewelry>();

	public static Jewelry forReward(int id) {
		return jewelry.get(Integer.valueOf(id));
	}

	private Jewelry(int rewardId, int levelRequired, double experienceGain, int...materialsRequired) {
		this.reward = new Item(rewardId);
		this.levelRequired = ((short) levelRequired);
		this.experienceGain = experienceGain;
		this.materialsRequired = materialsRequired;
	}

	public double getExperience() {
		return experienceGain;
	}

	public int[] getMaterialsRequired() {
		return materialsRequired;
	}

	public short getRequiredLevel() {
		return levelRequired;
	}

	public Item getReward() {
		return reward;
	}
}
