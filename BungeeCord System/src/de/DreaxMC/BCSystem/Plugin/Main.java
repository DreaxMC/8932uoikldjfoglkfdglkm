package de.DreaxMC.BCSystem.Plugin;

import net.md_5.bungee.api.plugin.Plugin;
import de.DreaxMC.BCSystem.Listener.LISTENER_Join;
import de.DreaxMC.BCSystem.Mysql.MySQL;
import de.DreaxMC.BCSystem.Mysql.MySQLAPI;

public class Main extends Plugin{

	public void onEnable() {
		MySQL.connect();
		MySQLAPI.startUp();
		registerListener();
	}
	
	public void registerListener() {
		getProxy().getPluginManager().registerListener(this, new LISTENER_Join());
	}
	
	public void onDisable() {
		MySQL.close();
	}
	
}
