package me.beatcomet.FT;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
//permissions imports
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;

public class FT extends JavaPlugin{
	Logger log = Logger.getLogger("Minecraft");
	BListener blockListener = new BListener (this);
	EListener entityListener = new EListener (this);
	
	File configFile = new File("plugins/" + "FT" + "/config.yml");
	Configuration config = new Configuration(configFile);
	
	public boolean usepermissions;
	
	
	public static PermissionHandler permissionHandler;
	private void setupPermissions() {
	    if (permissionHandler != null) {
	        return;
	    }
	    
	    Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    
	    if (permissionsPlugin == null) {
	        log.info("Permission system not detected, Everyone can use Fire Tools!");
	        usepermissions = false;
	        return;
	    }
	    
	    permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	    log.info("Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName());
	    usepermissions = true;
	}
	
	//Booleans
	boolean Ignition;
	boolean wooddrop;
	boolean oredrop;
	boolean sanddrop;
	boolean stonedrop;
	
	//Integers
	int stonemin;
	int stonemax;
	int sandmin;
	int sandmax;
	int oremin;
	int oremax;
	int woodmin;
	int woodmax;
	int length;
	
	//Chances
	int stonec;
	int sandc;
	int orec;
	int woodc;
	int ignitionc;
	
	public void onDisable(){
		log.info(this.getDescription().getName() + " Version " + this.getDescription().getVersion() + " is DISABLED!");
	}
	public void onEnable(){
		log.info(this.getDescription().getName() + " Version " + this.getDescription().getVersion() + " is ENABLED!");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
		
		setupPermissions();
		
		new File("plugins/" + "FT").mkdir();
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();

			} catch (Exception e) {
				// sending console message in case the data file could not be
				// created
				log.info("[FT] Error when creating config file.");
			}
		}
		// Loading data file
		config.load();
		
		if(config.getKeys("Allow") == null){
			config.setProperty("Allow.Ignition", true);
			config.setProperty("Allow.Wood drops coal", false);
			config.setProperty("Allow.Ores drops Ingots", true);
			config.setProperty("Allow.Sand drops Glass", true);
			config.setProperty("Allow.Stone drops Stone", true);
		}
		if(config.getKeys("Drops") == null){
			config.setProperty("Drops.Stone drop minimum", 1);
			config.setProperty("Drops.Stone drop maximum", 1);
			config.setProperty("Drops.Sand drop minimum", 1);
			config.setProperty("Drops.Sand drop maximum", 1);
			config.setProperty("Drops.Ores drop minimum", 1);
			config.setProperty("Drops.Ores drop maximum", 1);
			config.setProperty("Drops.Wood drop minimum", 1);
			config.setProperty("Drops.Wood drop maximum", 1);
		}
		if(config.getKeys("Damage") == null){
			config.setProperty("Damage.Ignition Length", 3);
		}
		if(config.getList("Chance") == null){
			config.setProperty("Chance.Ignition Chance", 60);
			config.setProperty("Chance.Stone Drop Chance", 50);
			config.setProperty("Chance.Sand Drop Chance", 50);
			config.setProperty("Chance.Ore Drop Chance", 50);
			config.setProperty("Chance.Wood Drop Chance", 50);
		}
		
		config.save();
		
		//Booleans
		Ignition = (Boolean) config.getProperty("Allow.Ignition");
		wooddrop = (Boolean) config.getProperty("Allow.Wood drops coal");
		oredrop = (Boolean) config.getProperty("Allow.Ores drops Ingots");
		sanddrop = (Boolean) config.getProperty("Allow.Sand drops Glass");
		stonedrop = (Boolean) config.getProperty("Allow.Stone drops Stone");
		
		//Integers
		stonemin = (Integer) config.getProperty("Drops.Stone drop minimum");
		stonemax = (Integer) config.getProperty("Drops.Stone drop maximum");
		sandmin = (Integer) config.getProperty("Drops.Sand drop minimum");
		sandmax = (Integer) config.getProperty("Drops.Sand drop maximum");
		oremin = (Integer) config.getProperty("Drops.Ores drop minimum");
		oremax = (Integer) config.getProperty("Drops.Ores drop maximum");
		woodmin = (Integer)config.getProperty("Drops.Wood drop minimum");
		woodmax = (Integer) config.getProperty("Drops.Wood drop maximum");
		length = (Integer) config.getProperty("Damage.Ignition Length");
		
		//Chances
		stonec = (Integer) config.getProperty("Chance.Stone Drop Chance");
		sandc = (Integer) config.getProperty("Chance.Sand Drop Chance");
		orec = (Integer) config.getProperty("Chance.Ore Drop Chance");
		woodc = (Integer) config.getProperty("Chance.Wood Drop Chance");
		ignitionc = (Integer) config.getProperty("Chance.Ignition Chance");
	}

}
