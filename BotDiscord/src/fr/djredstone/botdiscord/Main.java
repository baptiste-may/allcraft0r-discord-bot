package fr.djredstone.botdiscord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import fr.djredstone.botdiscord.commands.CommandDaily;
import fr.djredstone.botdiscord.commands.CommandDashboard;
import fr.djredstone.botdiscord.commands.CommandEyes;
import fr.djredstone.botdiscord.commands.CommandFakeBan;
import fr.djredstone.botdiscord.commands.CommandFakeResetXP;
import fr.djredstone.botdiscord.commands.CommandFindNumber;
import fr.djredstone.botdiscord.commands.CommandHask;
import fr.djredstone.botdiscord.commands.CommandHelp;
import fr.djredstone.botdiscord.commands.CommandLink;
import fr.djredstone.botdiscord.commands.CommandMoney;
import fr.djredstone.botdiscord.commands.CommandNon;
import fr.djredstone.botdiscord.commands.CommandOui;
import fr.djredstone.botdiscord.commands.CommandP4;
import fr.djredstone.botdiscord.commands.CommandPing;
import fr.djredstone.botdiscord.commands.CommandQuitteOuDouble;
import fr.djredstone.botdiscord.commands.CommandSend;
import fr.djredstone.botdiscord.commands.CommandTank;
import fr.djredstone.botdiscord.commands.CommandText;
import fr.djredstone.botdiscord.listener.messageReactionAddListener;
import fr.djredstone.botdiscord.tasks.messageByMinuteTest;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

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
		
		if(!this.getConfig().contains("minNbMessageWarn")) {
			this.getConfig().set("minNbMessageWarn", 4);
		}
		if(!this.getConfig().contains("token")) {
			this.getConfig().set("token", "YOUR TOKEN HERE");
		}
		if(!this.getConfig().contains("tokenMEE6")) {
			this.getConfig().set("tokenMEE6", "YOUR MEE6 TOKEN HERE");
		}
		
		this.saveConfig();
		
		token = this.getConfig().getString("token");
		tokenMEE6 = this.getConfig().getString("tokenMEE6");
		
		try {
			jda = JDABuilder.createDefault(token).build();
			mee6 = JDABuilder.createDefault(tokenMEE6).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(token);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
	    builder.setActivity(Activity.playing("faire de la redstone"));
	    try {
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    JDABuilder builderMEE6 = JDABuilder.createDefault(tokenMEE6);
	    
	    builderMEE6.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE, CacheFlag.ACTIVITY);
	    try {
	    	builderMEE6.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    new P4Game(this);
	    
	    jda.addEventListener(this);
	    
	    jda.addEventListener(new CommandP4(this));
	    jda.addEventListener(new CommandFindNumber());
	    jda.addEventListener(new CommandQuitteOuDouble());
	    
	    jda.addEventListener(new CommandMoney());
	    jda.addEventListener(new CommandDashboard());
	    jda.addEventListener(new CommandDaily());;
	    
	    jda.addEventListener(new CommandHelp());
	    jda.addEventListener(new CommandSend());
	    jda.addEventListener(new CommandPing());
	    jda.addEventListener(new CommandLink());
	    
	    jda.addEventListener(new CommandHask());
	    jda.addEventListener(new CommandOui());
	    jda.addEventListener(new CommandNon());
	    jda.addEventListener(new CommandText());
	    
	    jda.addEventListener(new CommandTank());
	    jda.addEventListener(new CommandEyes());
	    
	    mee6.addEventListener(new CommandFakeBan());
		mee6.addEventListener(new CommandFakeResetXP());
	    
	    new messageByMinuteTest(this);
	    
	    jda.addEventListener(new messageReactionAddListener(this));
	    
	    try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    Bukkit.getPluginManager().registerEvents(this, this);
		
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
