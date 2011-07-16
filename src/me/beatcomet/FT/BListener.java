package me.beatcomet.FT;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class BListener extends BlockListener{
	FT plugin;
	public BListener(FT instance){
		plugin = instance;//wrong way around XD try now... can't believe I didd the dame mistake once again....
	}
	
	public void onBlockBreak(BlockBreakEvent event){
		Random rand = new Random();
		Block block = event.getBlock();
		Player player = event.getPlayer();//Where do you get the error? What line? line 21
		Location location = block.getLocation();
		int chance = rand.nextInt(101);
		
		if(plugin.usepermissions == true){
			if(block.getType() == Material.IRON_ORE && plugin.oredrop == true){
				int ch = plugin.orec;
				int drop = rand.nextInt(plugin.oremax - plugin.oremin + 1) + (plugin.oremin);
				if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance <= ch && FT.permissionHandler.has(player, "gold.pickaxe")){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_INGOT, drop));
				}
				if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance > ch && FT.permissionHandler.has(player, "gold.pickaxe")){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_ORE, 1));
				}
			}
			if(block.getTypeId() == 17 && plugin.wooddrop == true){
				int ch = plugin.woodc;
				int drop = rand.nextInt(plugin.woodmax - plugin.woodmin + 1) + (plugin.woodmin);
				if(player.getItemInHand().getTypeId() == 286 && chance <= ch && FT.permissionHandler.has(player, "gold.axe")){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.COAL, drop));
				}
			}
			if(block.getType() == Material.GOLD_ORE && plugin.oredrop == true){
				int ch = plugin.orec;
				int drop = rand.nextInt(plugin.oremax - plugin.oremin + 1) + (plugin.oremin);
				if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance <= ch && FT.permissionHandler.has(player, "gold.pickaxe")){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.GOLD_INGOT, drop));
				}if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance > ch && FT.permissionHandler.has(player, "gold.pickaxe")){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.GOLD_ORE, 1));
				}
			}
			if(block.getType() == Material.SAND && plugin.sanddrop == true){
				int ch = plugin.sandc;
				int drop = rand.nextInt(plugin.sandmax - plugin.sandmin + 1) + (plugin.sandmin);
				if(player.getItemInHand().getType() == Material.GOLD_SPADE && chance <= ch && FT.permissionHandler.has(player, "gold.shovel")){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.GLASS, drop));
				}
			}
			if(block.getType() == Material.STONE && plugin.stonedrop == true){
				int ch = plugin.stonec;
				int drop = rand.nextInt(plugin.stonemax - plugin.stonemin + 1 ) + (plugin.stonemin);
				if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance <= ch && FT.permissionHandler.has(player, "gold.pickaxe")){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.STONE, drop));
				}
			}
		}
		else if(plugin.usepermissions == false){
			if(block.getType() == Material.IRON_ORE && plugin.oredrop == true){
				int ch = plugin.orec;
				int drop = rand.nextInt(plugin.oremax - plugin.oremin + 1) + (plugin.oremin);
				if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance <= ch ){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_INGOT, drop));
				}
				if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance > ch ){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_ORE, 1));
				}
			}
			if(block.getTypeId() == 17 && plugin.wooddrop == true){
				int ch = plugin.woodc;
				int drop = rand.nextInt(plugin.woodmax - plugin.woodmin + 1) + (plugin.woodmin);
				if(player.getItemInHand().getTypeId() == 286 && chance <= ch ){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.COAL, drop));
				}
			}
			if(block.getType() == Material.GOLD_ORE && plugin.oredrop == true){
				int ch = plugin.orec;
				int drop = rand.nextInt(plugin.oremax - plugin.oremin + 1) + (plugin.oremin);
				if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance <= ch){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.GOLD_INGOT, drop));
				}if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance > ch){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.GOLD_ORE, 1));
				}
			}
			if(block.getType() == Material.SAND && plugin.sanddrop == true){
				int ch = plugin.sandc;
				int drop = rand.nextInt(plugin.sandmax - plugin.sandmin + 1) + (plugin.sandmin);
				if(player.getItemInHand().getType() == Material.GOLD_SPADE && chance <= ch){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.GLASS, drop));
				}
			}
			if(block.getType() == Material.STONE && plugin.stonedrop == true){
				int ch = plugin.stonec;
				int drop = rand.nextInt(plugin.stonemax - plugin.stonemin + 1 ) + (plugin.stonemin);
				if(player.getItemInHand().getType() == Material.GOLD_PICKAXE && chance <= ch ){
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItemNaturally(location, new ItemStack(Material.STONE, drop));
				}
			}
		}
	}
}
