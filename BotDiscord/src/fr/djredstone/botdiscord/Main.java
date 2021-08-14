package fr.djredstone.botdiscord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import fr.djredstone.botdiscord.commands.CommandSend;
import fr.djredstone.botdiscord.commands.CommandTank;
import fr.djredstone.botdiscord.commands.CommandText;
import fr.djredstone.botdiscord.listener.lostMemberListener;
import fr.djredstone.botdiscord.listener.messageReactionAddListener;
import fr.djredstone.botdiscord.listener.messageRecivedListener;
import fr.djredstone.botdiscord.listener.newMemberListener;
import fr.djredstone.botdiscord.tasks.messageByMinuteTest;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main extends JavaPlugin implements EventListener {
	
	static String token;
	public static String prefix = "!";
	public static String noPermMessage = "Vous n'étes pas une personne de puissance.";
	public HashMap<User, Integer> messageByMinute = new HashMap<User, Integer>();
	
	public List<String> P4startMessageID = new ArrayList<String>();
	public HashMap<String, String> P4firstPlayer = new HashMap<String, String>();
	public HashMap<String, String> P4secondPlayer = new HashMap<String, String>();
	public HashMap<String, String> P4startMessageUser = new HashMap<String, String>();
	public HashMap<String, String> P4tour = new HashMap<String, String>();
	
	public static int messagesByDay = 0;
	public static int newMembers = 0;
	public static int lostMembers = 0;
	public static List<User> activeMembers = new ArrayList<User>();
	public static List<String> activeChannels = new ArrayList<String>();
	
	public static JDA jda;
	
	@Override
	public void onEnable() {
		
		if(!this.getConfig().contains("minNbMessageWarn")) {
			this.getConfig().set("minNbMessageWarn", 4);
		}
		if(!this.getConfig().contains("token")) {
			this.getConfig().set("token", "YOUR TOKEN HERE");
		}
		
		this.saveConfig();
		
		token = this.getConfig().getString("token");
		
		try {
			jda = JDABuilder.createDefault(token).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(token);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
	    try {
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    new P4Game(this);
	    
	    jda.addEventListener(this);
	    
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
	    
	    new messageByMinuteTest(this);
	    
	    jda.addEventListener(new messageRecivedListener(this));
	    jda.addEventListener(new messageReactionAddListener(this));
	    jda.addEventListener(new newMemberListener());
	    jda.addEventListener(new lostMemberListener());
	    
	    try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onDisable() {
		
		Calendar c = new GregorianCalendar();
	    c.add(Calendar.DATE,-1);
	    
	    EmbedBuilder embed = new EmbedBuilder();
	    embed.setTitle("**Information de la journée d'hier**");
	    embed.addField("**Membres actuel**", "" + jda.getGuildById("497090628639916032").getMemberCount(), true);
	    embed.addField("**Nombre de messages**", "" + messagesByDay, true);
	    embed.addField("**Nouveaux membres**", "" + newMembers, true);
	    embed.addField("**Membres perdus**", "" + lostMembers, true);
	    embed.addField("**Membres actifs**", "" + activeMembers.size(), true);
	    embed.addField("**Channels actifs**", "" + activeChannels.size(), true);
	    
	    jda.getTextChannelById("497141089480998912").sendMessage(embed.build()).queue();
		
		jda.shutdown();
		
	}
	
	public void onReadyEvent(ReadyEvent event) {
        System.out.println("§cBot discord allcraft0r prêt !");
    }

	@Override
	public void onEvent(GenericEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
