package com.mayhem.core.network.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mayhem.rs2.entity.World;
import com.mayhem.rs2.entity.item.Item;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.net.out.impl.SendMessage;




public class Voting implements Runnable {

	public static final String HOST = "45.43.7.61";
	public static final String USER = "premiers_voter";
	public static final String PASS = "premier123!";
	public static final String DATABASE = "premiers_vote";

	private Player player;
	private Connection conn;
	private Statement stmt;

	public Voting(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		try {

			if (!connect(HOST, DATABASE, USER, PASS)) {
				System.out.println("The connection to the voting database has failed!");
				return;
			}
			
			boolean message = true;
			boolean found = false;
			String name = player.getUsername().replaceAll(" ", "_");
			//@SuppressWarnings("resource")
			ResultSet rs = executeQuery("SELECT * FROM fx_votes WHERE username='"+name+"' AND claimed=0");

			while (rs.next()) {
				player.getInventory().addItems(new Item(4067, 3));
				if (!found) {
					found = true;
				}
				if (message) {
					World.sendGlobalMessage("<col=1F8C26>" + player.getUsername() + " Has just voted: ");
					System.out.println(player.getUsername()+" has just voted.");
					message = false;
				}
				

				

				rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
				rs.updateRow();
			}
			
			if (!found) {
				player.send(new SendMessage("Found no pending vote rewards. Try again, or wait a few minutes."));
			}

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
			return true;
		} catch (SQLException e) {
			System.out.println("Failing connecting to database!");
			return false;
		}
	}

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
