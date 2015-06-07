package de.DreaxMC.BCSystem.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQLAPI {

	public static void startUp() {
		MySQL.Update("CREATE TABLE IF NOT EXISTS uuids (name varchar(60),uuid varchar(60))");
    	MySQL.Update("CREATE TABLE IF NOT EXISTS names (uuid varchar(60),name varchar(60))");
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
