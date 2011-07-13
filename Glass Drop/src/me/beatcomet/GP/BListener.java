package me.beatcomet.GP;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class BListener extends BlockListener {
	Glass plugin;

	BListener(Glass instnace) {
		plugin = instnace;
	}

	public void onBlockBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		int mindamage = plugin.MinDamage;
		int maxdamage = plugin.MaxDamage;
		int chance = plugin.Chance;
		if (b.getType() == Material.GLASS) {
			Location loc = b.getLocation();
			ItemStack is = new ItemStack(Material.GLASS, 1);
			b.getWorld().dropItemNaturally(loc, is);
			int x = (int) (Math.random() * 100) + 1;
			Random rand = new Random();
			if (plugin.Baredamage == true) {
				if (p.getItemInHand().getTypeId() == 0) {
					if (x <= chance) {
						int y = rand.nextInt(maxdamage - mindamage + 1)
								+ (mindamage);
						int health = p.getHealth();
						if(health - y < 0){
							p.setHealth(0);
						}
						p.setHealth(health -y);
						if(plugin.showdamagemsg == true){
							p.sendMessage(ChatColor.RED + "[Server] " + ChatColor.DARK_GRAY + plugin.Message);
						}	
					}
				}
			}
			if (plugin.Baredamage == false) {
				if (x <= chance) {
					int y = rand.nextInt(maxdamage - mindamage + 1)
							+ (mindamage);
					int Health = p.getHealth();
					if(Health - y < 0){
						p.setHealth(Health - y);
					}
					if(plugin.showdamagemsg == true){
						p.sendMessage(ChatColor.RED + "[Server] " + ChatColor.DARK_GRAY + plugin.Message);
					}
					
				}
			}
		}
	}
}
