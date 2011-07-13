package me.beatcomet.GP;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class EListener extends EntityListener {
	Glass plugin;

	public EListener(Glass instance) {
		plugin = instance;
	}

	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			plugin.getServer().broadcastMessage(ChatColor.RED + "[Server] " + ChatColor.DARK_PURPLE +  p.getName() + ChatColor.AQUA + " " + plugin.dmsg);
		}
	}
}
