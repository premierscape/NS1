package com.mayhem.core.network.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mayhem.core.util.Utility;
import com.mayhem.rs2.content.membership.RankHandler;
import com.mayhem.rs2.entity.World;
import com.mayhem.rs2.entity.item.Item;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Using this class:
 * To call this class, it's best to make a new thread. You can do it below like so:
 * new Thread(new Donation(player)).start();
 */
public class Donation implements Runnable {

	public static final String HOST = "45.43.7.61";
	public static final String USER = "premiers_storev1";
	public static final String PASS = "a.)WZWlUyN8P";
	public static final String DATABASE = "premiers_store";

	private Player player;
	private Connection conn;
	private Statement stmt;

	/**
	 * The constructor
	 * @param player
	 */
	public Donation(Player player) {
		this.player = player;
	}

	
	@Override
	public void run() {
		try {
			player.send(new SendMessage("Your donation is being processed....Please wait."));
			if (!connect(HOST, DATABASE, USER, PASS)) {
				player.send(new SendMessage("Donation claim failed, please contact an administrator."));
				return;
			}
			String name = player.getUsername().replace("_", " ");
			ResultSet rs = executeQuery("SELECT * FROM payments WHERE player_name='"+name+"' AND status='Completed' AND claimed=0");
			
			while (rs.next()) {
				int item_number = rs.getInt("item_number");
				double paid = rs.getDouble("amount");
				int quantity = rs.getInt("quantity"); 
				
				switch (item_number) {// add products according to their ID in the ACP

				case 18: // 100 GrandLegion bucks
					player.getInventory().add(13190, 1 * quantity); 
					//player.setMoneySpent(player.getMoneySpent() + (int)paid);
					//RankHandler.upgrade(player);
					//player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 19: // 300 GrandLegion bucks
					player.getInventory().add(13191, 1 * quantity); 
					//player.setMoneySpent(player.getMoneySpent() + (int)paid);
					//RankHandler.upgrade(player);
					//player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");

				break;
				case 20: // 500 GrandLegion bucks
					player.getInventory().add(13192, 1 * quantity); 
					//player.setMoneySpent(player.getMoneySpent() + (int)paid);
				//	RankHandler.upgrade(player);
				//	player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 21: // 1000 GrandLegion bucks
					player.getInventory().add(14000, 1 * quantity); 
				//	player.setMoneySpent(player.getMoneySpent() + (int)paid);
				//	RankHandler.upgrade(player);
				//	player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 22: // 2500 GrandLegion bucks
					player.getInventory().add(13195, 1 * quantity); 
				//	player.setMoneySpent(player.getMoneySpent() + (int)paid);
				//	RankHandler.upgrade(player);
				//	player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 23: // 5000 GrandLegion bucks
					player.getInventory().add(13196, 1 * quantity); 
			//		player.setMoneySpent(player.getMoneySpent() + (int)paid);
				//	RankHandler.upgrade(player);
				//	player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 24: // 10,000 GrandLegion bucks
					player.getInventory().add(5444, 1 * quantity); 
				//	player.setMoneySpent(player.getMoneySpent() + (int)paid);
				//	RankHandler.upgrade(player);
				//	player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 25: // 20,000 GrandLegion bucks
					player.getInventory().add(13198, 1 * quantity); 
				//	player.setMoneySpent(player.getMoneySpent() + (int)paid);
				//	RankHandler.upgrade(player);
				//	player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 26: // fury
					player.getInventory().add(6585, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 27: // fire cape
					player.getInventory().add(6570, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 28: // Saradomin GodSword
					player.getInventory().add(11806, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 29: //Whip
					player.getInventory().add(990, 100 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 30: // dragon warhammer
					player.getInventory().add(13576, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 44: // partyhat
					player.getInventory().add(8152, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 45: // partyhat
					player.getInventory().add(8167, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 46: // partyhat
					player.getInventory().add(12785, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 47: // partyhat
					player.getInventory().add(10833, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 48: // partyhat
					player.getInventory().add(1048, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 49: // mystery box
					player.getInventory().add(6199, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 50: // mystery box x10
					if (player.getInventory().hasSpaceFor(new Item(6199, 10 * quantity))) {
					player.getInventory().add(6199, 10 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
					} else if (player.getBank().hasSpaceFor((new Item(6199, 10 * quantity)))) {
						player.getBank().add(6199, 10 * quantity); 
						player.setMoneySpent(player.getMoneySpent() + (int)paid);
						RankHandler.upgrade(player);
						player.setMember(true);
						player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion. Your donation has been sent to your bank."));
					}
				break;
				case 31: // armadyl godsword
					player.getInventory().add(11802, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 32: // dragon defender
					player.getInventory().add(12954, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 33: // dh
					player.getInventory().add(12877, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)paid);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 34: // Karil
					player.getInventory().add(12883, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)1);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 35: // Torag armor
					player.getInventory().add(12879, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)3);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 36: // Guthan's armour set
					player.getInventory().add(12873, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)5);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 37: // Verac's Armour
					player.getInventory().add(12875, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)10);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 38: // Ahrim's armour set
					player.getInventory().add(12881, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)2);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 39: // 	Dexterous Prayer Scroll
					player.getInventory().add(21034, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)10);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 40: // Arcane Prayer Scroll
					player.getInventory().add(21079, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)18);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 41: // 50x super combat
					player.getInventory().add(12696, 50 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)35);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 42: // dragonstone bolt e
					player.getInventory().add(9244, 250 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)35);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				case 43: //party hat set
					player.getInventory().add(13173, 1 * quantity); 
					player.setMoneySpent(player.getMoneySpent() + (int)35);
					RankHandler.upgrade(player);
					player.setMember(true);
					player.getClient().queueOutgoingPacket(new SendMessage("Thank you for supporting GrandLegion."));
					World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				break;
				
				
								
				}
				player.send(new SendMessage("You've claimed your donation."));
				World.sendGlobalMessage("</col>[ @dre@GrandLegion </col>] @dre@" + player.determineIcon(player) + " " + Utility.formatPlayerName(player.getUsername()) + " has just Donated to GrandLegion! Thank you for your contribution! ");
				rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
				rs.updateRow();
			}

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param host the host ip address or url
	 * @param database the name of the database
	 * @param user the user attached to the database
	 * @param pass the users password
	 * @return true if connected
	 */
	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
			return true;
		} catch (SQLException e) {
			System.out.println("[Donation]: Failed connecting to database!");
			return false;
		}
	}

	/**
	 * Disconnects from the MySQL server and destroy the connection
	 * and statement instances
	 */
	public void destroy() {
        try {
    		conn.close();
        	conn = null;
        	if (stmt != null) {
    			stmt.close();
        		stmt = null;
        	}
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * Executes an update query on the database
	 * @param query
	 * @see {@link Statement#executeUpdate}
	 */
	public int executeUpdate(String query) {
        try {
        	this.stmt = this.conn.createStatement(1005, 1008);
            int results = stmt.executeUpdate(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

	/**
	 * Executres a query on the database
	 * @param query
	 * @see {@link Statement#executeQuery(String)}
	 * @return the results, never null
	 */
	public ResultSet executeQuery(String query) {
        try {
        	this.stmt = this.conn.createStatement(1005, 1008);
            ResultSet results = stmt.executeQuery(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
