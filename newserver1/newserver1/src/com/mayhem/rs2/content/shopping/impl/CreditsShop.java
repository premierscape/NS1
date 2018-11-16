package com.mayhem.rs2.content.shopping.impl;

import com.mayhem.rs2.content.interfaces.InterfaceHandler;
import com.mayhem.rs2.content.interfaces.impl.QuestTab;
import com.mayhem.rs2.content.shopping.Shop;
import com.mayhem.rs2.entity.item.Item;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Shop for pest credits
 * 
 * @author Daniel
 */
public class CreditsShop extends Shop {

	/**
	 * Id of shop
	 */
	public static final int SHOP_ID = 94;

	/**
	 * Prices of item in shop
	 * 6
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
switch (id) {
case 8152:
	return 2000;
case 8167:
	return 1000;
case 12785:
	return 2000;
case 21034:
case 21079:
	return 3000;
case 1038:
	return 2000;
case 1040:
	return 2000;
case 1042:
	return 2000;
case 1044:
	return 2000;
case 1046:
	return 2000;
case 1048:
	return 2000;
case 12399:
	return 2500;
case 11862:
	return 3000;
case 11863:
	return 3000;
	case 7142:
		return 1500;
	case 11770:
	case 11771:
	case 11772:
	case 11773:
		return 600;
	case 12020:
		return 1000;
	case 2577:
		return 250;
	case 12439:
	case 12397:
	case 12393:
	case 12395:
	case 12319:
	case 12351:
	case 12441:
	case 12443:
		return 120;
	case 13239:
	case 13237:
	case 13235:
		return 600;
	case 4566:
	case 4565:
		return 250;
	case 12373:
	case 12335:
	case 12337:
	case 12432:
		return 300;
	case 12357:
		return 500;
		
	case 9946:
		return 100;
	case 19553:
		return 700;
	case 19547:
		return 700;
	case 19550:
		return 700;
	case 19544:
		return 700;
	case 21006:
	case 20997:
	case 21003:
	case 21000:
	case 21015:
		return 1500;
	case 21012:
		return 900;
	}

	return 15000;
}

/**
 * Items in shop
 */
public CreditsShop() {
	super(SHOP_ID, new Item[] { 
	}, false, "GrandLegion Credits Store");
}

	@Override
	public void buy(Player player, int slot, int id, int amount) {
		if (!hasItem(slot, id))
			return;
		if (get(slot).getAmount() == 0)
			return;
		if (amount > get(slot).getAmount()) {
			amount = get(slot).getAmount();
		}

		Item buying = new Item(id, amount);

		if (!player.getInventory().hasSpaceFor(buying)) {
			if (!buying.getDefinition().isStackable()) {
				int slots = player.getInventory().getFreeSlots();
				if (slots > 0) {
					buying.setAmount(slots);
					amount = slots;
				} else {
					player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough inventory space to buy this item."));
				}
			} else {
				player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough inventory space to buy this item."));
				return;
			}
		}

		if (player.getCredits() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough GrandLegion Credits to buy that."));
			return;
		}

		player.setCredits(player.getCredits() - (amount * getPrice(id)));

		//InterfaceHandler.writeText(new QuestTab(player));

		player.getInventory().add(buying);
		update();
	}

	@Override
	public int getBuyPrice(int id) {
		return 0;
	}

	@Override
	public String getCurrencyName() {
		return "Credits";
	}

	@Override
	public int getSellPrice(int id) {
		return getPrice(id);
	}

	@Override
	public boolean sell(Player player, int id, int amount) {
		player.getClient().queueOutgoingPacket(new SendMessage("You cannot sell items to this shop."));
		return false;
	}
}
