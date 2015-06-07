package de.DreaxMC.BCSystem.Plugin;

import net.md_5.bungee.api.plugin.Plugin;
import de.DreaxMC.BCSystem.Mysql.MySQL;

public class Main extends Plugin{

	public void onEnable() {
		MySQL.connect();
	}
	
	public void onDisable() {
		MySQL.close();
	}
	
}
