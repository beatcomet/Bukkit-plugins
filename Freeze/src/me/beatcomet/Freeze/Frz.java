package me.beatcomet.Freeze;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class Frz extends JavaPlugin {

	// Creating a new data file
	File configFile = new File("plugins/" + "Freeze" + "/data.ass");
	File cfgfile = new File("plugins/" + "Freeze" + "/status.ass");

	Configuration config = new Configuration(configFile);

	Configuration cfg = new Configuration(cfgfile);

	// getting Minecraft logger to send console messages
	Logger log = Logger.getLogger("Minecraft");

	// creating Player Listener reference
	PListener playerListener = new PListener(this);
	EListener entityListener = new EListener(this);

	// What happens when the plugin is being disabled
	public void onDisable() {
		// saving the data
		config.save();

		// Getting info from plugin.yml
		PluginDescriptionFile PDFile = this.getDescription();

		// sending console message saying the plugin has been disabled

		log.info(PDFile.getName() + " Version " + PDFile.getVersion()
				+ " is DISABLED!");
	}

	public void onEnable() {
		// Sending a console message saying the plugin has been enabled

		// Getting info from plugin.yml
		PluginDescriptionFile PDFile = this.getDescription();

		log.info(PDFile.getName() + " Version " + PDFile.getVersion()
				+ " is ENABLED!");

		// making a new file if a data file dose not exist
		new File("plugins/" + PDFile.getName()).mkdir();
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();

			} catch (Exception e) {
				// sending console message in case the data file could not be
				// created
				log.info("[Freeze] Error when creating config file.");
			}
		}
		// Loading data file
		config.load();

		new File("plugins/" + PDFile.getName()).mkdir();
		if (!cfgfile.exists()) {
			try {
				cfgfile.createNewFile();

			} catch (Exception e) {
				// sending console message in case the data file could not be
				// created
				log.info("[Freeze] Error when creating config file.");
			}
		}
		// Loading data file
		config.load();

		// getting the plugin manager to register events
		PluginManager pm = this.getServer().getPluginManager();

		// player join event registration
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener,
				Event.Priority.Normal, this);
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			if (commandLabel.equalsIgnoreCase("Freeze")
					|| commandLabel.equalsIgnoreCase("f")) {
				if (args.length == 0) {
					this.log.info("No player name inserted");
				} else {
					String name = args[0];
					if ((Boolean) this.config.getProperty(name) == false) {
						this.config.setProperty(name, true);
						this.log.info(name + " Has been freezed!");
						this.config.save();
						return true;
					}
					if ((Boolean) this.config.getProperty(name) == true) {
						this.config.setProperty(name, false);
						this.log.info(name + " Has been unfreezed!");
						this.config.save();
						return true;
					} else {
						this.log.info(name + " Does not exist in the data file");
						this.config.setProperty(name, false);
						this.config.save();
						return true;
					}
				}
			}
			// try now
		}
		if (sender instanceof Player) {
			if (sender.isOp()) {
				if (commandLabel.equalsIgnoreCase("Freeze")
						|| commandLabel.equalsIgnoreCase("f")) {

					if (args.length == 0) {
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "[Freeze] "
								+ ChatColor.AQUA + " No name inserted!");
					} else {

						String name = args[0];
						if ((Boolean) this.config.getProperty(name) == false) {
							this.config.setProperty(name, true);
							this.config.save();
							sender.sendMessage(ChatColor.LIGHT_PURPLE
									+ "[Freeze] " + ChatColor.AQUA + name
									+ " Has been freezed!");
							return true;
						}
						if ((Boolean) this.config.getProperty(name) == true) {
							this.config.setProperty(name, false);
							this.config.save();
							sender.sendMessage(ChatColor.LIGHT_PURPLE
									+ "[Freeze] " + ChatColor.AQUA + name
									+ " Has been unfreezed!");
							return true;
						} else {
							sender.sendMessage(ChatColor.LIGHT_PURPLE
									+ "[Freeze] " + ChatColor.AQUA + name
									+ " Does not exist in the data file");
							this.config.setProperty(name, false);
							this.config.save();
							return true;
						}
					}
				}
			}
		}
		if (sender instanceof Player && sender.isOp()) {
			if (commandLabel.equalsIgnoreCase("check")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "[Freeze] "
							+ ChatColor.AQUA
							+ "Pleas insert a players name to get status");
				} else {
					String name = args[0];
					if ((Boolean) this.config.getProperty(name) == true) {
						sender.sendMessage(ChatColor.GOLD + name
								+ ChatColor.RED + " - Freezed");
						return true;
					}
					if ((Boolean) this.config.getProperty(name) == false) {
						sender.sendMessage(ChatColor.GOLD + name
								+ ChatColor.BLUE + " - Not Freezed");
						return true;
					}
				}
			}
		}
		if (sender instanceof Player && sender.isOp()) {
			if (commandLabel.equalsIgnoreCase("status")) {
				String construct = null;
				for (Player p : getServer().getOnlinePlayers()) {
					if ((Boolean) this.config.getProperty(p.getName()) == true) {
						this.cfg.setProperty(p.getName(), ": Freezed");
					}
					if ((Boolean) this.config.getProperty(p.getName()) == false) {
						this.cfg.setProperty(p.getName(), ": Not Freezed");
					}
					construct = ChatColor.GOLD + p.getName() + ChatColor.AQUA
							+ this.cfg.getProperty(p.getName())
							+ ChatColor.BLACK + " | ";
				}
				sender.sendMessage(construct);

			}

		}
		return true;

	}

}
