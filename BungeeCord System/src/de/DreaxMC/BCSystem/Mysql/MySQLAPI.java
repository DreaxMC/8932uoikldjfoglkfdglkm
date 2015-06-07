package de.DreaxMC.BCSystem.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQLAPI {

	public static void startUp() {
		MySQL.Update("CREATE TABLE IF NOT EXISTS uuids (name varchar(60),uuid varchar(60))");
    	MySQL.Update("CREATE TABLE IF NOT EXISTS names (uuid varchar(60),name varchar(60))");
	}
	

	public void setupPlayer(ProxiedPlayer p) {
		
	}
	
	public String getUUID(String name) {
		try {
			ResultSet rs = MySQL.Query("");
			while(rs.next()) {
				rs.getString(1);
			}
		} catch (SQLException e) {
		}
		return "ERROR";
	}
	
	public String getName(String uuid) {
		try {
			ResultSet rs = MySQL.Query("");
			while(rs.next()) {
				rs.getString(1);
			}
		} catch (SQLException e) {
		}
		return "ERROR";
	}
	
	
}
