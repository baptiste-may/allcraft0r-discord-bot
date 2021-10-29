package fr.djredstone.botdiscord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class Main extends JavaPlugin {
	
	static String token;
	static String tokenMEE6;
	public static String prefix = "/";
	public static String noPermMessage = "Vous n'étes pas une personne de puissance.";
	public static String redstoneEmoji = "<:redstone:503978809645727745>";
	public HashMap<User, Integer> messageByMinute = new HashMap<>();
	
	public List<String> P4startMessageID = new ArrayList<>();
	public HashMap<String, String> P4startMessageUser = new HashMap<>();

	public static JDA jda;
	public static JDA mee6;
	
	public static Main main;
	
	@Override
	public void onEnable() {
		
		Main.main = this;
		
		new Setup(this);
		
	}
	
	@Override
	public void onDisable() {
			
		jda.shutdownNow();
		mee6.shutdownNow();
		
	}
	
	public static int getMoney(User user) {
		
		Main.main.reloadConfig();
    	FileConfiguration config = Main.main.getConfig();
		
    	if(!config.contains("money." + user.getId())) {
			config.set("money." + user.getId(), 100);
			Main.main.saveConfig();
		}
		
		return config.getInt("money." + user.getId());
	}
	
	public static void setMoney(User user, int money) {
		
		Main.main.reloadConfig();
    	Main.main.getConfig().set("money." + user.getId(), money);
		main.saveConfig();

	}

}
