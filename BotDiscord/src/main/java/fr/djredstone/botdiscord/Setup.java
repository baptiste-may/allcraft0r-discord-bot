package fr.djredstone.botdiscord;

import javax.security.auth.login.LoginException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import fr.djredstone.botdiscord.commands.CommandFindNumber;
import fr.djredstone.botdiscord.commands.CommandP4;
import fr.djredstone.botdiscord.commands.CommandQuitteOuDouble;
import fr.djredstone.botdiscord.listener.MessageReceivedListener;
import fr.djredstone.botdiscord.listener.OnDiscordCommand;
import fr.djredstone.botdiscord.mysql.DatabaseManager;
import fr.djredstone.botdiscord.tasks.messageByMinuteTest;
import org.jetbrains.annotations.NotNull;

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
	    
	    Main.jda.addEventListener(this);
	    
	    Main.jda.addEventListener(new OnDiscordCommand());
	    
	    Main.jda.addEventListener(new CommandP4(main));

	    Main.jda.addEventListener(new CommandFindNumber(null, null, null, null));
		Main.jda.addEventListener(new CommandQuitteOuDouble(null, null, null, null));

		Main.jda.updateCommands().addCommands(Commands.slash("number", "Démarre une partie de find number").addOptions(new OptionData(OptionType.INTEGER, "nb_max", "Nombre maximum").setRequired(false)))
			.addCommands(Commands.slash("quitteoudouble", "Démarre une partie de quitte ou double").addOptions(new OptionData(OptionType.INTEGER, "nb_depart_mise", "Nombre de départ de la mise").setRequired(true)))
	    	.addCommands(Commands.slash("money", "Affiche son nombre de redstones"))
			.addCommands(Commands.slash("dashboard", "???"))
	    	.addCommands(Commands.slash("daily", "Récupère sa redstone quotidienne"))
	    	.addCommands(Commands.slash("aide", "Liste des commandes"))
	    	.addCommands(Commands.slash("send", "Envoie un message aux personnes de puissances").addOptions(new OptionData(OptionType.STRING, "send_message", "Message").setRequired(true)))
	    	.addCommands(Commands.slash("ping", "Lance une balle de ping pong, voit en combien de temps je la renvoie"))
	    	.addCommands(Commands.slash("link", "Affiche des liens en rapport à allcraft0r"))
	    	.addCommands(Commands.slash("ask", "Demande prise en compte"))
	    	.addCommands(Commands.slash("oui", "Demande acceptée").addOptions(new OptionData(OptionType.STRING, "oui_message", "Message").setRequired(false)))
	    	.addCommands(Commands.slash("non", "Demande refusée").addOptions(new OptionData(OptionType.STRING, "non_message", "Message").setRequired(false)))
	    	.addCommands(Commands.slash("text", "Message personnalisé").addOptions(new OptionData(OptionType.CHANNEL, "text_channel", "Channel").setRequired(true), new OptionData(OptionType.STRING, "text", "Texte").setRequired(true)))
	    	.addCommands(Commands.slash("tank", "AMERICA ! F*CK YEAHH !!"))
	    	.addCommands(Commands.slash("eyes", "I'm watching you..."))
			.addCommands(Commands.slash("fakeban", "Faux message de ban").addOptions(new OptionData(OptionType.USER, "fakeban_user", "User").setRequired(true), new OptionData(OptionType.STRING, "fakeban_raison", "Raison").setRequired(false)))
	    	.addCommands(Commands.slash("fakeresetxp", "Faux message de reset d'XP").addOptions(new OptionData(OptionType.USER, "fakeresetxp_user", "User").setRequired(true)))
			.addCommands(Commands.slash("lock", "Lock un channel").addOptions(new OptionData(OptionType.CHANNEL, "lock_channel", "Channel").setRequired(true), new OptionData(OptionType.STRING, "lock_message", "Message").setRequired(false)))
			.addCommands(Commands.slash("unlock", "Unlock un channel").addOptions(new OptionData(OptionType.CHANNEL, "unlock_channel", "Channel").setRequired(true)))
			.queue();

	    new messageByMinuteTest(main);
	    
	    Main.jda.addEventListener(new MessageReceivedListener(main));
	    
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
