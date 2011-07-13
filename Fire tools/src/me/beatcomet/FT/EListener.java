package me.beatcomet.FT;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class EListener extends EntityListener{
	FT plugin;
	public EListener (FT instance){
		plugin = instance;
	}
	
	public void onEntityDamage(EntityDamageEvent event){
		if(event instanceof EntityDamageByEntityEvent){
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
			Entity damager = e.getDamager();
			int length = plugin.length * 20;
			Random rand = new Random();
			int chance = rand.nextInt(101);
			int igchance = plugin.ignitionc;
			if(damager instanceof Player){
				Player player = (Player) damager;
				if(player.getItemInHand().getType() == Material.GOLD_SWORD && chance <= igchance && plugin.Ignition == true){
					Entity damaged = event.getEntity();
					damaged.setFireTicks(length);
				}
			}
		}
	}
}
