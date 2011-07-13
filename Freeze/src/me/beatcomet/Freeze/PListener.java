package me.beatcomet.Freeze;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PListener extends PlayerListener{
	Frz plugin;
	
	public PListener(Frz instance){
		plugin = instance;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String name = player.getName();
		if(plugin.config.getProperty(name) == null)
		{
			plugin.config.setProperty(name, false);
			plugin.config.save();
		}	
	}
	//this should be in your main class
	
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		String name = player.getName();
		if((Boolean) plugin.config.getProperty(name) == true){
			event.setCancelled(true);
		}
	}
}
