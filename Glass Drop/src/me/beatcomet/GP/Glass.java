package me.beatcomet.GP;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class Glass extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	BListener blockListener = new BListener(this);
	EListener entityListener = new EListener(this);

	int MinDamage;
	int MaxDamage;
	int Chance;
	public String Message;
	public String dmsg;
	boolean Baredamage;
	boolean showdamagemsg;

	public void onDisable() {
		PluginDescriptionFile PDFile = this.getDescription();
		log.info(PDFile.getName() + " Version " + PDFile.getVersion()
				+ " is DISABLED!");
	}

	public void onEnable() {

		PluginDescriptionFile PDFile = this.getDescription();
		log.info(PDFile.getName() + " Version " + PDFile.getVersion()
				+ " is ENABLED!");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Normal, this);

		new File("plugins/" + PDFile.getName()).mkdir();
		File configFile = new File("plugins/" + PDFile.getName()
				+ "/config.yml");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();

			} catch (Exception e) {
				log.info("[GlassBreak] Error when creating config file.");
			}
		}

		Configuration config = new Configuration(configFile);
		config.load();
		if (config.getKeys("props") == null) {
			config.setProperty("props.Minimum Damage", 2);
			config.setProperty("props.Maximum Damage", 2);
			config.setProperty("props.Chance", 80);
			config.setProperty("props.Message",
					"You have been cut by the glass block");
			config.setProperty("props.BareHandDamage", true);
			config.setProperty("props.DeathMessage",
			"just Died from glass shards");
			config.setProperty("props.ShowDamageMessage", false);
			config.save();
		}

		Chance = (Integer) config.getProperty("props.Chance");
		MinDamage = (Integer) config.getProperty("props.Minimum Damage");
		MaxDamage = (Integer) config.getProperty("props.Maximum Damage");
		Message = (String) config.getProperty("props.Message");
		Baredamage = (Boolean) config.getProperty("props.BareHandDamage");
		dmsg = (String) config.getProperty("props.DeathMessage");
		showdamagemsg = (Boolean) config.getProperty("props.ShowDamageMessage");

	}

}
