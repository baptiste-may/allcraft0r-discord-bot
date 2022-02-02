package fr.djredstone.botdiscord;

import javax.security.auth.login.LoginException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import fr.djredstone.botdiscord.commands.CommandDashboard;
import fr.djredstone.botdiscord.commands.CommandFakeBan;
import fr.djredstone.botdiscord.commands.CommandFakeResetXP;
import fr.djredstone.botdiscord.commands.CommandFindNumber;
import fr.djredstone.botdiscord.commands.CommandP4;
import fr.djredstone.botdiscord.commands.CommandQuitteOuDouble;
import fr.djredstone.botdiscord.listener.MessageReceivedListener;
import fr.djredstone.botdiscord.listener.OnDiscordCommand;
import fr.djredstone.botdiscord.mysql.DatabaseManager;
import fr.djredstone.botdiscord.tasks.messageByMinuteTest;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Setup implements EventListener, Listener {

	public Setup(Main main) {

		// MySQL (Database)
		FileConfiguration fc = main.getConfig();
		if (!fc.contains("SQL.host")) {
			fc.set("SQL.host", "HOST HERE");
		}

		if (!fc.contains("SQL.user")) {
			fc.set("SQL.user", "USER HERE");
		}

		if (!fc.contains("SQL.password")) {
			fc.set("SQL.password", "PASSWORD HERE");
		}

		if (!fc.contains("SQL.dbName")) {
			fc.set("SQL.dbName", "DATABASE NAME HERE");
		}
		
		if(Main.main.getConfig().getString("minNbMessageWarn") == null) {
			Main.main.getConfig().set("minNbMessageWarn", 4);
		}
		if(Main.main.getConfig().getString("token") == null) {
			Main.main.getConfig().set("token", "YOUR TOKEN HERE");
		}
		if(Main.main.getConfig().getString("tokenMEE6") == null) {
			Main.main.getConfig().set("tokenMEE6", "YOUR MEE6 TOKEN HERE");
		}
		
		Main.main.saveConfig();
		
		Main.databaseManager = new DatabaseManager(fc.getString("SQL.host"), fc.getString("SQL.user"), fc.getString("SQL.password"), fc.getString("SQL.dbName"));
		
		Main.token = Main.main.getConfig().getString("token");
		Main.tokenMEE6 = Main.main.getConfig().getString("tokenMEE6");
		
		try {
			Main.jda = JDABuilder.createDefault(Main.token).build();
			Main.mee6 = JDABuilder.createDefault(Main.tokenMEE6).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(Main.token, GatewayIntent.GUILD_MESSAGES);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
	    builder.setActivity(Activity.playing("loading..."));
	    try {
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    JDABuilder builderMEE6 = JDABuilder.createDefault(Main.tokenMEE6, GatewayIntent.GUILD_MESSAGES);
	    
	    builderMEE6.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE, CacheFlag.ACTIVITY);
	    try {
	    	builderMEE6.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    new P4Game(main);
	    
	    CommandListUpdateAction commands = Main.jda.updateCommands();
	    
	    Main.jda.addEventListener(this);
	    
	    Main.jda.addEventListener(new OnDiscordCommand());
	    
	    Main.jda.addEventListener(new CommandP4(main));
	    Main.jda.addEventListener(new CommandFindNumber(null, null, null, null));
	    commands.addCommands(new CommandData("number", "Démarre une partie de find number").addOptions(new OptionData(OptionType.INTEGER, "nb_max", "Nombre maximum").setRequired(false)));
	    Main.jda.addEventListener(new CommandQuitteOuDouble(null, null, null, null));
	    commands.addCommands(new CommandData("quitteoudouble", "Démarre une partie de quitte ou double").addOptions(new OptionData(OptionType.INTEGER, "nb_depart_mise", "Nombre de départ de la mise").setRequired(true)));
	    
	    commands.addCommands(new CommandData("money", "Affiche son nombre de redstones"));
	    Main.jda.addEventListener(new CommandDashboard());
	    commands.addCommands(new CommandData("dashboard", "???"));
	    commands.addCommands(new CommandData("daily", "Récupère sa redstone quotidienne"));
	    
	    commands.addCommands(new CommandData("aide", "Liste des commandes"));
	    commands.addCommands(new CommandData("send", "Envoie un message aux personnes de puissances").addOptions(new OptionData(OptionType.STRING, "send_message", "Message").setRequired(true)));
	    commands.addCommands(new CommandData("ping", "Lance une balle de ping pong, voit en combien de temps je la renvoie"));
	    commands.addCommands(new CommandData("link", "Affiche des liens en rapport à allcraft0r"));
	    
	    commands.addCommands(new CommandData("ask", "Demande prise en compte"));
	    commands.addCommands(new CommandData("oui", "Demande acceptée").addOptions(new OptionData(OptionType.STRING, "oui_message", "Message").setRequired(false)));
	    commands.addCommands(new CommandData("non", "Demande refusée").addOptions(new OptionData(OptionType.STRING, "non_message", "Message").setRequired(false)));
	    commands.addCommands(new CommandData("text", "Message personnalisé").addOptions(new OptionData(OptionType.CHANNEL, "text_channel", "Channel").setRequired(true), new OptionData(OptionType.STRING, "text", "Texte").setRequired(true)));
	    
	    commands.addCommands(new CommandData("tank", "AMERICA ! F*CK YEAHH !!"));
	    commands.addCommands(new CommandData("eyes", "I'm watching you..."));
	    
	    Main.mee6.addEventListener(new CommandFakeBan());
	    Main.mee6.addEventListener(new CommandFakeResetXP());
	    
	    new messageByMinuteTest(main);
	    
	    Main.jda.addEventListener(new MessageReceivedListener(main));
	    
	    commands.queue();
	    
	    try {
	    	Main.jda.awaitReady();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    new BukkitRunnable() {
	    	
	    	int message = 0;
			
			@Override
			public void run() {

				if(message == 0) {
					Main.jda.getPresence().setActivity(Activity.watching("les vidéos d'allcraft0r"));
					message = 1;
				} else {
					Main.jda.getPresence().setActivity(Activity.playing("faire de la redstone"));
					message = 0;
				}
				
			}
		}.runTaskTimer(main, 0, 200);
		
	}
	
	@Override
	public void onEvent(@NotNull GenericEvent event) {
		if(event instanceof ReadyEvent) System.out.println("§cBot discord allcraft0r prêt !");
    }

}
