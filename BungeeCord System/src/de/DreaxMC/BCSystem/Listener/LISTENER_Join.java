package de.DreaxMC.BCSystem.Listener;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import de.DreaxMC.BCSystem.Mysql.MySQLAPI;

public class LISTENER_Join implements Listener{

	@EventHandler
	public void onJoin(ServerConnectEvent e) {
		ProxiedPlayer p = e.getPlayer();
		MySQLAPI.setupPlayer(p);
	}
	
}
