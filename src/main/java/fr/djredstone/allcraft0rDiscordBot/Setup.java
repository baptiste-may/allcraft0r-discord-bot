package fr.djredstone.allcraft0rDiscordBot;

import javax.security.auth.login.LoginException;

import java.util.EnumSet;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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

import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandFindNumber;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandP4;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandSlot;
import fr.djredstone.allcraft0rDiscordBot.listener.MessageReceivedListener;
import fr.djredstone.allcraft0rDiscordBot.listener.OnDiscordCommand;
import fr.djredstone.allcraft0rDiscordBot.listener.OnDiscordOPCommand;
import fr.djredstone.allcraft0rDiscordBot.mysql.DatabaseManager;
import fr.djredstone.allcraft0rDiscordBot.tasks.messageByMinuteTest;
import org.jetbrains.annotations.NotNull;

public class Setup implements EventListener {

	private static final Map<String, String> env = System.getenv();

	public Setup() {

		// MySQL (Database) Login
		DBConnect();

		// Admin Message ID Set
		Main.setAdminIDChannel(env.get("ADMIN_CHANNEL_ID"));

		// Normal Bot Login
		EnumSet<GatewayIntent> intents = EnumSet.of(
				GatewayIntent.GUILD_MESSAGES,
				GatewayIntent.GUILD_VOICE_STATES
		);
		JDABuilder builder = JDABuilder.createDefault(env.get("TOKEN"), intents);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.EMOTE);
		builder.enableCache(CacheFlag.VOICE_STATE);
	    builder.setActivity(Activity.playing("loading..."));
	    try {
			Main.setJda(builder.build());
		} catch (LoginException e) {
			e.printStackTrace();
		}

		// MEE6 Bot Login
	    JDABuilder builderMEE6 = JDABuilder.createDefault(env.get("TOKEN_MEE6"), GatewayIntent.GUILD_MESSAGES);
	    
	    builderMEE6.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE, CacheFlag.ACTIVITY, CacheFlag.EMOTE);
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
		Main.getJda().addEventListener(new CommandSlot(null, null));
		CommandP4.setup();
		Main.getJda().addEventListener(new CommandP4(null, null));

		new messageByMinuteTest();

		Main.getJda().addEventListener(new MessageReceivedListener());

		// Normal Commands Adds
		Main.getJda().updateCommands().addCommands(
				Commands.slash("number", "Démarre une partie de find number").addOptions(new OptionData(OptionType.INTEGER, "nb_max", "Nombre maximum").setRequired(false)),
				Commands.slash("slot", "Démarre une partie de machine à sous"),
				Commands.slash("p4", "Démarre une partie de puissance 4"),
				Commands.slash("money", "Affiche son nombre de redstones"),
				Commands.slash("dashboard", "Affiche les 10 membres ayant le plus de redstone"),
	    		Commands.slash("daily", "Récupère sa redstone quotidienne"),
	    		Commands.slash("aide", "Liste des commandos"),
	    		Commands.slash("send", "Envoie un message aux personnes de puissances").addOptions(new OptionData(OptionType.STRING, "send_message", "Message").setRequired(true)),
	    		Commands.slash("ping", "Lance une balle de ping pong, voit en combien de temps je la renvoie"),
	    		Commands.slash("link", "Affiche des liens en rapport à allcraft0r"),
	    		Commands.slash("tank", "AMERICA ! F*CK YEAHH !!"),
	    		Commands.slash("eyes", "I'm watching you..."),
				Commands.slash("play", "Démarre une musique depuis le nom de la chanson, ou par son URL").addOptions(new OptionData(OptionType.STRING, "play_search", "Le titre ou l'URL d'une chanson").setRequired(true)),
				Commands.slash("stop", "Arrête la musique en cours"),
				Commands.slash("skip", "Passe la musique en cours"),
				Commands.slash("disconnect", "Se déconnecte du channel"),
				Commands.slash("pause", "Mette en pause ou remet la musique en cours"),
				Commands.slash("repeat", "Met ou retire la boucle de la liste"),
				Commands.slash("now", "Affiche la musique en cours"),
				Commands.slash("queue", "Affiche les musiques de la liste"),
				Commands.slash("volume", "Modifie le volume de la musique").addOptions(new OptionData(OptionType.INTEGER, "volume", "Le volume soité")),
				Commands.slash("8ball", "Seul l'avenir est ici").addOptions(new OptionData(OptionType.STRING, "8ball", "La phrase de l'avenir"))
				).queue();
	    
	    try {
	    	Main.getJda().awaitReady();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Timer().schedule(new TimerTask() {
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
		}, 0, 100*1000);
		
	}

	public static void DBConnect() {
		if (Main.getDatabaseManager() != null) Main.getDatabaseManager().close();
		Main.setDatabaseManager(new DatabaseManager(env.get("DB-HOST"), env.get("DB-USER"), env.get("DB-PASSWORD"), env.get("DB-NAME")));
	}
	
	@Override
	public void onEvent(@NotNull GenericEvent event) {
		if(event instanceof ReadyEvent) System.out.println("Allcraft0r bot ready !");
    }

}
