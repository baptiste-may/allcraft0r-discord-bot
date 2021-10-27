package fr.djredstone.botdiscord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class Main extends JavaPlugin implements EventListener, Listener {
	
	static String token;
	static String tokenMEE6;
	public static String prefix = "!";
	public static String noPermMessage = "Vous n'étes pas une personne de puissance.";
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
	
	@Override
	public void onEvent(@NotNull GenericEvent event) {
		if(event instanceof ReadyEvent) System.out.println("§cBot discord allcraft0r prêt !");
    }
	
	public static int getMoney(User user) {
		
		Main.main.reloadConfig();
    	FileConfiguration config = Main.main.getConfig();
		
    	if(!config.contains("money." + user.getId())) {
			config.set("money." + user.getId(), 1000);
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
