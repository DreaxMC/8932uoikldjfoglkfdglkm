package de.DreaxMC.BCSystem.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQLAPI {

	public static void startUp() {
		MySQL.Update("CREATE TABLE IF NOT EXISTS uuids (name varchar(60),uuid varchar(60))");
    	MySQL.Update("CREATE TABLE IF NOT EXISTS names (uuid varchar(60),name varchar(60))");
    	MySQL.Update("CREATE TABLE IF NOT EXISTS ips (uuid varchar(60),ip varchar(60))");
    	MySQL.Update("CREATE TABLE IF NOT EXISTS coins (uuid varchar(60),coins int(20))");
    	MySQL.Update("CREATE TABLE IF NOT EXISTS friends (uuid varchar(60),friendUUID varchar(60))");
    	MySQL.Update("CREATE TABLE IF NOT EXISTS bans (uuid varchar(60),uuidby varchar(60),breason varchar(60),btype varchar(60),btime int(60),breceived int(60))");
	}
	

	public static void setupPlayer(ProxiedPlayer p) {
		String name = p.getName();
		if(getUUID(name).equalsIgnoreCase("ERROR")) {
			MySQL.Update("INSERT INTO uuids (name,uuid) VALUES ('"+p.getName()+"','"+p.getUniqueId().toString()+"')");
		}else if(!getUUID(p.getName()).equals(p.getUniqueId().toString())) {
			MySQL.Update("DELETE FROM uuids WHERE name='" + p.getName() + "'");
			MySQL.Update("INSERT INTO uuids (name,uuid) VALUES ('"+p.getName()+"','"+p.getUniqueId().toString()+"')");
		}
		if(getName(p.getUniqueId().toString()).equalsIgnoreCase("ERROR123456789101112")) {
			MySQL.Update("INSERT INTO names (uuid,name) VALUES ('"+p.getUniqueId().toString()+"','"+p.getName()+"')");
		}else if(!getName(p.getUniqueId().toString()).equals(p.getName())) {
			MySQL.Update("DELETE FROM names WHERE uuid='" + p.getUniqueId().toString() + "'");
			MySQL.Update("INSERT INTO names (uuid,name) VALUES ('"+p.getUniqueId().toString()+"','"+p.getName()+"')");
		}
		if(!getIP(p.getUniqueId().toString()).equalsIgnoreCase("ERROR")) {
			MySQL.Update("DELETE FROM ips WHERE uuid='" + p.getUniqueId().toString() + "'");
		}
		MySQL.Update("INSERT INTO ips (uuid,ip) VALUES ('"+p.getUniqueId().toString()+"','"+(p.getAddress().toString().split(":") [0])+"')");
	}
	
	public static String getIP(String uuid) {
		try {
			ResultSet rs = MySQL.Query("SELECT ip FROM ips WHERE uuid='" + uuid + "'");
			while(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
		}
		return "ERROR";
	}
	
	public static ArrayList<String> getUserNames() {
		ArrayList<String> us = new ArrayList<String>();
		try {
			ResultSet s = MySQL.Query("SELECT name FROM names");
			while(s.next()) {
					us.add(s.getString(1));
			}
		} catch (SQLException e) {}
		return us;
	}
	
	public static ArrayList<String> getUsers() {
		ArrayList<String> us = new ArrayList<String>();
		try {
			ResultSet s = MySQL.Query("SELECT uuid FROM uuids");
			while(s.next()) {
					us.add(s.getString(1));
			}
		} catch (SQLException e) {}
		return us;
	}
	
	public static ArrayList<String> getFriends(String uuid) {
		return getFriends(uuid, false);
	}
	
	public static synchronized ArrayList<String> getFriends(String uuid, boolean names) {
		ArrayList<String> friends = new ArrayList<String>();
		try {
			ResultSet rs = MySQL.Query("SELECT friendUUID FROM friends WHERE uuid='" + uuid + "'");
			while(rs.next()) {
				if(names) {
					friends.add(getName(rs.getString(1)));
				}else{
					friends.add(rs.getString(1));
				}
			}
		} catch (SQLException e) {
		}
		return friends;
	}
	
	public static boolean isFriend(String uuid, String friendUUID) {
		if(uuid.equalsIgnoreCase(friendUUID)) {
			return false;
		}
		if(getFriends(uuid).contains(friendUUID)) {
			return true;
		}
 		
		return false;
	}
	
	public static synchronized void setFriends(String uuid, ArrayList<String> friends) {
		MySQL.Update("DELETE FROM friends WHERE uuid='" + uuid + "'");
		for(String s : friends) {
			MySQL.Update("INSERT INTO friends (uuid,friendUUID) VALUES ('"+uuid+"','"+s+"')");
		}
	}
	
	public static int getCoins(String uuid) {
		try {
			ResultSet rs = MySQL.Query("SELECT coins FROM coins WHERE uuid='" + uuid + "'");
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
		}
		return 0;
	}
	
	public static void setCoins(String uuid, int coins) {
		MySQL.Update("DELETE FROM coins WHERE uuid='" + uuid + "'");
		MySQL.Update("INSERT INTO coins (uuid,coins) VALUES ('"+uuid+"','"+(coins + "")+"')");
	}
	
	public static String getUUID(String name) {
		try {
			ResultSet rs = MySQL.Query("SELECT uuid FROM uuids WHERE name='" + name + "'");
			while(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
		}
		return "ERROR";
	}
	
	public static String getName(String uuid) {
		try {
			ResultSet rs = MySQL.Query("SELECT name FROM names WHERE uuid='" + uuid + "'");
			while(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
		}
		return "ERROR123456789101112";
	}
	
	
}
