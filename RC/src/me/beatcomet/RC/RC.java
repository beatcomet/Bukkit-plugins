package me.beatcomet.RC;

import java.io.File;
import java.util.Random;
import java.util.logging.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

@SuppressWarnings("unchecked")
public class RC extends JavaPlugin{
	
        Random rand = new Random();
	Logger log = Logger.getLogger("Minecraft");
	Configuration config = null;
	File codesFile;
	HashMap<String,CodeRecord> codeRecords = null;
	
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
		
		getDataFolder().mkdir();
		File configFile = new File(getDataFolder(),"codes.cfg");
	        codesFile = new File(getDataFolder(),"codes.dat");
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
	        config = new Configuration(configFile);

		config.load();
		if(config.getKeys("props")== null){
			config.setProperty("props.lng", 8);
		}

		config.save();

		loadCodes();
	}

	public String generateRandomName(int length) {
            final String chars = "abcdefghijklmnopqrstuvwxyz123456789-";
            String word = "";
            while(word.length() < length){
                int x = rand.nextInt(chars.length());
                word = word + chars.charAt(x);
//                System.out.println(word.toUpperCase());
            }
            return word;
	}

	public int getRndFromTo(int min, int max, boolean inclusive) { //get random from min to max including or excluding max
	    return rand.nextInt(max-min+(inclusive?1:0))+min;
	}

	public boolean checkPermission(CommandSender who,String type) {
	    if(who instanceof Player) {
	        if(permissionHandler!=null)
	            if(permissionHandler.has((Player)who,type))
	                return true;
	            else {
       			who.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.RED + " permissions You do not have!"); //Yoda style :D
	                return false;
	            }
	        else
	            if(who.isOp()) return true;
	            else {
       			who.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.RED + " operator must You be!"); //Yoda style :D
       			return false;
	            }
	    } else {
     		who.sendMessage("Prohibited is console use!"); //Yoda style :D
	        return false;
	    }
	}

	public int getRandomId() {
	    final int[][] ranges = {{1,96},{256,359},{2256,2257}};
	    int total=0;
	    for(int i=0;i<ranges.length;i++) {
	        total+=ranges[i][1]-ranges[i][0]+1;
	    }
	    int newid=getRndFromTo(0,total,true)+ranges[0][0];
	    for(int i=0;i<ranges.length-1;i++) {
	        if(newid>ranges[i][1]) {
	            newid+=ranges[i+1][0]-ranges[i][1]-1;
	        }
	        else break;
	    }
	    return newid;
	}
	public boolean isForbiddenID(int id) {
	    final int[] forbid={ 7,8,9,10,11,16,18,26,30,31,34,36,46,51,52,54,55,58,61,62,63,64,68,71,73,74,75,78,79,90,93,94,95,327};

	    for(int i=0;i<forbid.length;i++)
	        if(id==forbid[i])
	            return true;

	    return false;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        	int lngg = 8;
        	try{ lngg=(Integer) config.getProperty("props.lng"); } catch(Exception e){log.warning("props.lng is not set");}
        	
        	if(commandLabel.equalsIgnoreCase("code"))
        	{
        	    if(args.length==0) return false;

        	    if(args[0].equalsIgnoreCase("set") && args.length>1 && checkPermission(sender, "code.set")) {
        		String name = args[1];
        	        if(name.equalsIgnoreCase("generate")) {
        	            name=generateRandomName(lngg);
        	        }
        	        int id=-1, amount=-1, redeems=-1; // now we initialise given args and set ungiven to -1
        	        String err="";
        		if(args.length>2) {
        		    try{id=Integer.parseInt(args[2]);} catch(Exception e) {err="id ";}
        		}
        		if(args.length>3) {
        		    try{amount=Integer.parseInt(args[3]);} catch(Exception e) {err+="amount ";}
        		}
        		if(args.length>4) {
        		    try{redeems=Integer.parseInt(args[4]);} catch(Exception e) {err+="redeems ";}
        		}
        		if(err.length()>0)
       			    sender.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.RED + err + "not numerical and would be generated!");

        		//and make -1 to random
        		if(id==-1) {
        		    do {
        		        id = getRandomId();
        		        log.info("Random ID generator returned id "+id+", it is "+(isForbiddenID(id)?"forbidden":"allright"));
        		    } while(isForbiddenID(id));
        		}
        		if(amount==-1) {
        		    amount=getRndFromTo(1,20,true);
        		}
        		if(redeems==-1) {
        		    redeems=getRndFromTo(5,12,true);
        		}

        		CodeRecord record = new CodeRecord(id,amount,redeems);
        		codeRecords.put(name.toUpperCase(),record);
        		saveCodes();

       			sender.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + name + " id:" + record.id + " amount:" + record.amount + " redeem times:" + record.redeems + ChatColor.AQUA + " added to list!");

       			return true;
        	    } else if(args[0].equalsIgnoreCase("status") && args.length>1 && checkPermission(sender, "code.status")) {
        	        String name=args[1].toUpperCase();
        	        CodeRecord record = codeRecords.get(name);
        	        if(record==null)
        		    sender.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + " No such code found");
        	        else
        		    sender.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + name + ChatColor.AQUA + " Can be redeemed " + record.redeems + " more Times!" );

        		return true;
        	    } else if(args[0].equalsIgnoreCase("remove") && args.length>1 && checkPermission(sender, "code.remove")) {
        	        String name=args[1].toUpperCase();
        	        if(!codeRecords.containsKey(name))
        		    sender.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + " No such code existed");
        	        else {
        	            codeRecords.remove(name);
        		saveCodes();
        		    sender.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + name + ChatColor.AQUA + " was removed!" );
        		}

        		return true;
        	    }
        	}
        	else if(commandLabel.equalsIgnoreCase("redeem") && sender instanceof Player) {
        	    Player p = (Player)sender;

        	    if(args.length==0) {
        		sender.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + " No code provided!");
        		return true;
        	    }
       		    String code = args[0].toUpperCase();
       		    CodeRecord record = codeRecords.get(code);
       		    if(record==null) {
        		sender.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.RED  + " No such code exists");
        		return true;
       		    }
       		    if(record.redeems<=0) {
        		sender.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.GREEN + code + ChatColor.RED + " code all used up!");
        		codeRecords.remove(code);
       		    }
       		    else {
        		p.getInventory().addItem(new ItemStack(record.id, record.amount));
       		        record.redeems--;
        		sender.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + code + ChatColor.AQUA + " Redeemed!");
       		        if(record.redeems<=0) {
        		    sender.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.GREEN + code + ChatColor.AQUA + " code all used up!");
        		    codeRecords.remove(code);
       		        } else
        		    sender.sendMessage(ChatColor.DARK_PURPLE + "[RC] " + ChatColor.GREEN + record.redeems + ChatColor.AQUA + " Uses left!");
        		saveCodes();
       		    }

       		    return true;
        	}
        	else if(commandLabel.equalsIgnoreCase("rcodelength") && args.length>1 && checkPermission(sender,"code.lenght")) {
        	    int newLength = -1;
        	    try {newLength=Integer.parseInt(args[0]);}
        	    finally {
        		config.setProperty("props.lng", newLength);
        		config.save();
        		sender.sendMessage(ChatColor.DARK_PURPLE + "[RC]" + ChatColor.GREEN + " random code length set to "+newLength);
        	    }
        	}

        	return false;
	}

	public void loadCodes() {
          if (!codesFile.exists()) {
            try {
              codesFile.createNewFile();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }

          Yaml yaml = new Yaml();

          if (codeRecords==null) {
            codeRecords = new HashMap<String, CodeRecord>();
          } else {
            codeRecords.clear();
          }

          try {
            log.info("Loading codes data...");
            HashMap<String, List<Integer>> loadData = (HashMap<String, List<Integer>>) yaml.load(new FileInputStream(codesFile));
            if(loadData!=null && !loadData.isEmpty())
              for(String name : loadData.keySet()) {
                codeRecords.put(name,new CodeRecord(loadData.get(name)));
              }

            log.info("Loading done.");

          } catch (Exception ex) {
            ex.printStackTrace();
          }
	}

	public synchronized void saveCodes() {
          if (!codesFile.exists()) {
            try {
              codesFile.createNewFile();
            } catch (Exception ex) {
              ex.printStackTrace();
            }
          }

          DumperOptions options = new DumperOptions();
          options.setDefaultFlowStyle(DumperOptions.FlowStyle.FLOW);
          Yaml yaml = new Yaml(options);

          HashMap<String, List<Integer>> saveData = new HashMap<String, List<Integer>>();

          synchronized(codeRecords) {
            for(String name : codeRecords.keySet()) {
              CodeRecord record = codeRecords.get(name);
              List<Integer> list = record.toList();
              saveData.put(name,list);
            }
          }

          try {
            yaml.dump(saveData, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(codesFile), "UTF-8")));
          } catch (Exception e) {
            e.printStackTrace();
          }
	}
}

class CodeRecord {
    public int id;
    public int amount;
    public int redeems;

    public CodeRecord() {
    }

    public CodeRecord(List<Integer> in) {
        this.id = in.get(0);
        this.amount = in.get(1);
        this.redeems = in.get(2);
    }

    public CodeRecord(int id, int amount, int redeems) {
        this.id=id;
        this.amount=amount;
        this.redeems=redeems;
        if(redeems<1) redeems=1;
    }

    public List<Integer> toList() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(this.id);
        list.add(this.amount);
        list.add(this.redeems);
        return list;
    }
}
