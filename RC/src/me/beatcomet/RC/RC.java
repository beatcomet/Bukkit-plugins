package me.beatcomet.RC;

import java.io.File;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;

public class RC extends JavaPlugin{
	
	
	Logger log = Logger.getLogger("Minecraft");
	File configFile = new File("plugins/" + "RC" + "/codes.xD1");
	Configuration config = new Configuration(configFile);

	
	public static PermissionHandler permissionHandler;
	
	
	private void setupPermissions() {
	    if (permissionHandler != null) {
	        return;
	    }
	    
	    Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    
	    if (permissionsPlugin == null) {
	        log.info("Permission system not detected, defaulting to OP");
	        return;
	    }
	    
	    permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	    log.info("Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName());
	}

	
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info(pdf.getName() + " Version " + pdf.getVersion() + " is DISABLED!");
	}
	
	public void onEnable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info(pdf.getName() + " Version " + pdf.getVersion() + " is ENABLED!");
		setupPermissions();
		
		new File("plugins/" + "RC").mkdir();
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();

			} catch (Exception e) {
				// sending console message in case the data file could not be
				// created
				log.info("[RC] Error when creating data file.");
			}
		}
		// Loading data file
		config.load();
		if(config.getKeys("props")== null){
			config.setProperty("props.lng", 8);
		}
		config.save();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)){
			log.info("You cannot reddem or set codes using console commands!");
			return true;
		}
		if(sender instanceof Player){
			Player p = (Player) sender;
			String type;
			String amount;
			String name;
			String max;
			int lngg = (Integer) config.getProperty("props.lng");
			if(commandLabel.equalsIgnoreCase("code") && args.length == 5 && RC.permissionHandler.has(p, "code.set")){
				if(args[0].equalsIgnoreCase("set")){
					name = args[1];
					type = args[2];
					amount = args[3];
					max = args[4];
					config.setProperty(name + "." + "id", Integer.parseInt(type));
					config.setProperty(name + "." + "amount", Integer.parseInt(amount));
					config.setProperty(name + "." + "Uses left", Integer.parseInt(max));
					config.save();
					p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + name + ChatColor.AQUA + " Has Beed Added To The Code List!");
				}return true;
			}if(commandLabel.equalsIgnoreCase("code") && args.length == 1 && args[0].equalsIgnoreCase("generate") && RC.permissionHandler.has(p, "code.generate")){
				Random rand = new Random();
				int gid;
				int gamount = rand.nextInt(20)+1;
				int gmax = rand.nextInt(12 - 5 +1) + 5;
				String gname = "";
				String iletter = "";
				int letter;
				
				while (gname.length() < lngg){
					letter = rand.nextInt(37);
					switch (letter){
					case 1:
						iletter = "a";
						break;
					case 2:
						iletter = "b";
						break;
					case 3:
						iletter = "c";
						break;
					case 4:
						iletter = "d";
						break;
					case 5: 
						iletter = "e";
						break;
					case 6:
						iletter = "f";
						break;
					case 7:
						iletter = "g";
						break;
					case 8:
						iletter = "h";
						break;
					case 9:
						iletter = "i";
						break;
					case 10: 
						iletter = "j";
						break;
					case 11:
						iletter = "k";
						break;
					case 12:
						iletter = "l";
						break;
					case 13:
						iletter = "m";
						break;
					case 14:
						iletter = "n";
						break;
					case 15:
						iletter = "o";
						break;
					case 16:
						iletter = "p";
						break;
					case 17:
						iletter = "q";
						break;
					case 18:
						iletter = "r";
						break;
					case 19:
						iletter = "s";
						break;
					case 20:
						iletter = "t";
						break;
					case 21:
						iletter = "u";
						break;
					case 22:
						iletter = "v";
						break;
					case 23:
						iletter = "w";
						break;
					case 24:
						iletter = "x";
						break;
					case 25:
						iletter = "y";
						break;
					case 26:
						iletter = "z";
						break;
					case 27:
						iletter = "1";
						break;
					case 28:
						iletter = "2";
						break;
					case 29:
						iletter = "3";
						break;
					case 30:
						iletter = "4";
						break;
					case 31:
						iletter = "5";
						break;
					case 32:
						iletter = "6";
						break;
					case 33:
						iletter = "7";
						break;
					case 34:
						iletter = "8";
						break;
					case 35:
						iletter = "9";
						break;
					case 36:
						iletter = "-";
						break;
					}
					gname = gname + iletter;
				}
				
				int istype = rand.nextInt(4);
				if(istype == 1){
					gid = rand.nextInt(98);
					switch (gid){
					case 8:
					case 9:
					case 10:
					case 11:
					case 26:
					case 18:
					case 7:
					case 31:
					case 34:
					case 30:
					case 52:
					case 51:
					case 46:
					case 36:
					case 54:
					case 55:
					case 58:
					case 61:
					case 62:
					case 63:
					case 64:
					case 68:
					case 71:
					case 73:
					case 74:
					case 75:
					case 78:
					case 79:
					case 16:
					case 95:
					case 90:
					case 93:
					case 94:
						gid = 1;
						break;
					}
					config.setProperty(gname+"."+"id", gid);
					config.setProperty(gname+"."+"amount", gamount);
					config.setProperty(gname+"."+"Uses left", gmax);
					config.save();
					p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + gname + ChatColor.AQUA + " Has Been generated!");
					p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + config.getProperty(gname+"."+"Uses left") + ChatColor.AQUA + " Uses left!");
					
				}if(istype == 2){
					gid = rand.nextInt(359-256 +1) + (256);
					switch (gid){
					case 327:
						gid = gid-1;
						break;
					}
					config.setProperty(gname+"."+"id", gid);
					config.setProperty(gname+"."+"amount", gamount);
					config.setProperty(gname+"."+"Uses left", gmax);
					config.save();
					p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + gname + ChatColor.AQUA + " Has Been genrated!");
					p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + config.getProperty(gname+"."+"Uses left") + ChatColor.AQUA + " Uses left!");
					
				}if(istype == 3){
					gid = rand.nextInt(2257 - 2256 + 1) + (2256);
					config.setProperty(gname+"."+"id", gid);
					config.setProperty(gname+"."+"amount", gamount);
					config.setProperty(gname+"."+"Uses left", gmax);
					config.save();
					p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + gname + ChatColor.AQUA + " Has Been genrated!");
					p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + config.getProperty(gname+"."+"Uses left") + ChatColor.AQUA + " Uses left!");
					
					
				}return true;
			}
			if(commandLabel.equalsIgnoreCase("code") && args.length == 2 && RC.permissionHandler.has(p, "code.status") && args[0].equalsIgnoreCase("status")){
				name = args[1];
				if(config.getProperty(name+"."+"Uses left") == null){
					p.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + " No such code found");
				}else{
				p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + name + ChatColor.AQUA + " Can be redeemed " + config.getProperty(name+"."+"Uses left") + " more Times!" );
				}return true;
			}
			if(commandLabel.equalsIgnoreCase("lng") && args.length == 1 && RC.permissionHandler.has(p, "code.lenght")){
				config.setProperty("props.lng", Integer.parseInt(args[0]));
				p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + "Changed Length To " + ChatColor.DARK_PURPLE + args[0]);
				return true;
			}
			if(commandLabel.equalsIgnoreCase("redeem")){
				String code = args[0];
				if(args.length == 0){
					p.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + " No code inserted!");
				}
				if((Integer)config.getProperty(code+"."+"Uses left") == 0){
					
				}
				if(args.length > 1){
				p.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + " Invalid code inserted!");
				}
				if(args.length == 1){
					
					if(config.getKeys(code) == null || config.getProperty(code+"."+"id") == null || config.getProperty(code+"."+"amount") == null || config.getProperty(code+"."+"Uses left") == null ){
						p.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + "No such code found");
					}else{
						int amnt = (Integer) config.getProperty(args[0] + "." + "id");
						int typeid = (Integer) config.getProperty(args[0] + "." + "amount");
						int usesleft = (Integer) config.getProperty(args[0] + "." + "Uses left");
						if(usesleft == 0){
							p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN  + args[0] + " Fully used");
						}if(usesleft < 0){
							p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN  + args[0] + " Fully used");
							config.setProperty(args[0] + "." + "Uses left", 0);
						}
						p.getInventory().addItem(new ItemStack(amnt, typeid));
						config.setProperty(args[0] + "." + "Uses left", usesleft - 1);
						config.save();
						p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN  + args[0] + ChatColor.AQUA + " Redeemed!");
						p.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + config.getProperty(args[0]+"."+"Uses left") + ChatColor.AQUA + " Uses left!");
						
					}
				}return true;
			}if(commandLabel.equalsIgnoreCase("code") && args.length == 2 && RC.permissionHandler.has(p, "code.remove")){
				if(args[0].equalsIgnoreCase("Remove")){
					String key = args[1];
					if(config.getKeys(key) != null){
						config.removeProperty(args[1]+"."+"amount");
						config.removeProperty(args[1]+"."+"id");
						config.save();
						p.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.GREEN  + key + ChatColor.AQUA + " Removed!");
					}
					if(config.getKeys(key) == null){
						p.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.GREEN  + key + ChatColor.AQUA + " Does Not Exist!");
					}
				}return true;
			}
			if(args.length == 2 && commandLabel.equalsIgnoreCase("code") && args[0].equalsIgnoreCase("set")){
				p.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + " Invalid command!");
				return true;
			}if (args.length != 5 && args.length !=2 && commandLabel.equalsIgnoreCase("code") && !(args[0].equalsIgnoreCase("generate"))){
				p.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + " Invalid command!");
			}return true;
		}
		return false;
	}
}
