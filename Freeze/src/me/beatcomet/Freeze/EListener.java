package me.beatcomet.Freeze;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class EListener extends EntityListener{
	Frz plugin;
	public EListener (Frz instance){
		plugin = instance;
	}
	
	public void onEntityDamage(EntityDamageEvent event){
		if (event instanceof EntityDamageByEntityEvent) {
		    EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
		    if (e.getDamager() instanceof Player) {
		       Player damager = (Player) e.getDamager();
		       String name = damager.getName();
		       if((Boolean) plugin.config.getProperty(name) == true){
			       e.setCancelled(true); 	
		       }
		    }
		}
	}
}
