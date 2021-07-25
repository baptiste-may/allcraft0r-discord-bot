package fr.djredstone.botdiscord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.botdiscord.commands.CommandEyes;
import fr.djredstone.botdiscord.commands.CommandFakeBan;
import fr.djredstone.botdiscord.commands.CommandHask;
import fr.djredstone.botdiscord.commands.CommandHelp;
import fr.djredstone.botdiscord.commands.CommandLink;
import fr.djredstone.botdiscord.commands.CommandNon;
import fr.djredstone.botdiscord.commands.CommandOui;
import fr.djredstone.botdiscord.commands.CommandP4;
import fr.djredstone.botdiscord.commands.CommandPing;
import fr.djredstone.botdiscord.commands.CommandPrefix;
import fr.djredstone.botdiscord.commands.CommandSend;
import fr.djredstone.botdiscord.commands.CommandTank;
import fr.djredstone.botdiscord.commands.CommandText;
import fr.djredstone.botdiscord.listener.messageReactionAddListener;
import fr.djredstone.botdiscord.listener.messageRecivedListener;
import fr.djredstone.botdiscord.tasks.messageByMinuteTest;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main extends JavaPlugin {
	
	static String token = "ODY1NTAyODczNTU5NjI5ODM0.YPE8XQ.d2X4XXJ_9tyeNnqjg7V73Tq21-w";
	public static String prefix = "!h";
	public static String noPermMessage = "Vous n'êtes pas une personne de puissance.";
	public HashMap<User, Integer> messageByMinute = new HashMap<User, Integer>();
	
	public List<String> P4startMessageID = new ArrayList<String>();
	public HashMap<String, Boolean> P4gameStart = new HashMap<String, Boolean>();
	public HashMap<String, String> P4gameMessageID = new HashMap<String, String>();
	public HashMap<String, User> P4firstPlayer = new HashMap<String, User>();
	public HashMap<String, User> P4secondPlayer = new HashMap<String, User>();
	
	public static JDA jda;
	
	@Override
	public void onEnable() {
		
		if(!this.getConfig().contains("minNbMessageWarn")) {
			this.getConfig().set("minNbMessageWarn", 4);
		}
		
		this.saveConfig();
		
		super.onEnable();
		
		try {
			jda = JDABuilder.createDefault(token).build();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(token);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
	    try {
			builder.build();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    jda.addEventListener(new CommandP4(this));
	    
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
	    
	    jda.addEventListener(new CommandFakeBan());
	    
	    jda.addEventListener(new CommandPrefix());
	    
	    jda.upsertCommand("haide", "Affiche de l'aide").queue();
	    
	    new messageByMinuteTest(this);
	    
	    jda.addEventListener(new messageRecivedListener(this));
	    jda.addEventListener(new messageReactionAddListener(this));
	    
		
	}
	
	@Override
	public void onDisable() {
		
		jda.shutdownNow();
		super.onDisable();
		
	}

}
