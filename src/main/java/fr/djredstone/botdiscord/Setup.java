package fr.djredstone.botdiscord;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
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
import fr.djredstone.botdiscord.listener.OnDiscordOPCommand;
import fr.djredstone.botdiscord.mysql.DatabaseManager;
import fr.djredstone.botdiscord.tasks.messageByMinuteTest;
import org.jetbrains.annotations.NotNull;

public class Setup implements EventListener, Listener {

	private static final FileConfiguration fc = Main.getInstance().getConfig();

	public Setup(Main main) {

		checkYML(main);

		// MySQL (Database) Login
		DBConnect();

		// Admin Message ID Set
		Main.setAdminIDChannel(fc.getString("adminMessageID"));

		// Normal Bot Login
		JDABuilder builder = JDABuilder.createDefault(fc.getString("token"), GatewayIntent.GUILD_MESSAGES);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
	    builder.setActivity(Activity.playing("loading..."));
	    try {
			Main.setJda(builder.build());
		} catch (LoginException e) {
			e.printStackTrace();
		}

		// MEE6 Bot Login
	    JDABuilder builderMEE6 = JDABuilder.createDefault(fc.getString("tokenMEE6"), GatewayIntent.GUILD_MESSAGES);
	    
	    builderMEE6.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE, CacheFlag.ACTIVITY);
	    try {
			Main.setMee6(builderMEE6.build());
		} catch (LoginException e) {
			e.printStackTrace();
		}
		Main.getMee6().getPresence().setStatus(OnlineStatus.INVISIBLE);

		// Listeners and tasks Adds
	    Main.getJda().addEventListener(this);
	    
	    Main.getJda().addEventListener(new OnDiscordCommand());
		Main.getMee6().addEventListener(new OnDiscordOPCommand());

	    Main.getJda().addEventListener(new CommandFindNumber(null, null, null, null));
		Main.getJda().addEventListener(new CommandQuitteOuDouble(null, null, null, null));
		CommandP4.setup();
		Main.getJda().addEventListener(new CommandP4(null, null));

		new messageByMinuteTest(main);

		Main.getJda().addEventListener(new MessageReceivedListener(main));

		// Normal Commands Adds
		Main.getJda().updateCommands().addCommands(
				Commands.slash("number", "Démarre une partie de find number").addOptions(new OptionData(OptionType.INTEGER, "nb_max", "Nombre maximum").setRequired(false)),
				Commands.slash("quitteoudouble", "Démarre une partie de quitte ou double").addOptions(new OptionData(OptionType.INTEGER, "nb_depart_mise", "Nombre de départ de la mise").setRequired(true)),
				Commands.slash("p4", "Démarre une partie de puissance 4"),
				Commands.slash("money", "Affiche son nombre de redstones"),
				Commands.slash("dashboard", "Affiche les 10 membres ayant le plus de redstone"),
	    		Commands.slash("daily", "Récupère sa redstone quotidienne"),
	    		Commands.slash("aide", "Liste des commandos"),
	    		Commands.slash("send", "Envoie un message aux personnes de puissances").addOptions(new OptionData(OptionType.STRING, "send_message", "Message").setRequired(true)),
	    		Commands.slash("ping", "Lance une balle de ping pong, voit en combien de temps je la renvoie"),
	    		Commands.slash("link", "Affiche des liens en rapport à allcraft0r"),
	    		Commands.slash("tank", "AMERICA ! F*CK YEAHH !!"),
	    		Commands.slash("eyes", "I'm watching you...")
				).queue();

		// MEE6 Commands Adds
		Main.getMee6().updateCommands().addCommands(
				Commands.slash("ask", "Demande prise en compte"),
				Commands.slash("oui", "Demande acceptée").addOptions(new OptionData(OptionType.STRING, "oui_message", "Message").setRequired(false)),
				Commands.slash("non", "Demande refusée").addOptions(new OptionData(OptionType.STRING, "non_message", "Message").setRequired(false)),
				Commands.slash("text", "Message personnalisé").addOptions(new OptionData(OptionType.CHANNEL, "text_channel", "Channel").setRequired(true), new OptionData(OptionType.STRING, "text", "Texte").setRequired(true)),
				Commands.slash("fakeban", "Faux message de ban").addOptions(new OptionData(OptionType.USER, "fakeban_user", "User").setRequired(true), new OptionData(OptionType.STRING, "fakeban_raison", "Raison").setRequired(false)),
				Commands.slash("fakeresetxp", "Faux message de reset d'XP").addOptions(new OptionData(OptionType.USER, "fakeresetxp_user", "User").setRequired(true)),
				Commands.slash("lock", "Lock un channel").addOptions(new OptionData(OptionType.CHANNEL, "lock_channel", "Channel").setRequired(true), new OptionData(OptionType.STRING, "lock_message", "Message").setRequired(false)),
				Commands.slash("unlock", "Unlock un channel").addOptions(new OptionData(OptionType.CHANNEL, "unlock_channel", "Channel").setRequired(true)),
				Commands.slash("stopp4", "Arrête la partie de puissance 4 en cours")
				).queue();
	    
	    try {
	    	Main.getJda().awaitReady();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    new BukkitRunnable() {
	    	
	    	int message = 0;
			
			@Override
			public void run() {

				if(message == 0) {
					Main.getJda().getPresence().setActivity(Activity.watching("les vidéos d'allcraft0r"));
					message = 1;
				} else {
					Main.getJda().getPresence().setActivity(Activity.playing("faire de la redstone"));
					message = 0;
				}
				
			}
		}.runTaskTimer(main, 0, 200);
		
	}

	private static void checkYML(Main main) {

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

		// Max Message Warn
		if(fc.getString("minNbMessageWarn") == null) {
			fc.set("minNbMessageWarn", 4);
		}

		// Bot Token
		if(fc.getString("token") == null) {
			fc.set("token", "YOUR TOKEN HERE");
		}
		if(fc.getString("tokenMEE6") == null) {
			fc.set("tokenMEE6", "YOUR MEE6 TOKEN HERE");
		}

		// Admin Message ID
		if (fc.get("adminMessageID") == null) {
			fc.set("adminMessageID", "YOUR ID HERE");
		}

		main.saveConfig();

	}

	public static void DBConnect() {
		if (Main.getDatabaseManager() != null) Main.getDatabaseManager().close();
		Main.setDatabaseManager(new DatabaseManager(fc.getString("SQL.host"), fc.getString("SQL.user"), fc.getString("SQL.password"), fc.getString("SQL.dbName")));
	}
	
	@Override
	public void onEvent(@NotNull GenericEvent event) {
		if(event instanceof ReadyEvent) Bukkit.getLogger().info("§cBot discord allcraft0r prêt !");
    }

}
