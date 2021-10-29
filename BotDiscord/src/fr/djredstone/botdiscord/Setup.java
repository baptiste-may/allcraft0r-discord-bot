package fr.djredstone.botdiscord;

import javax.security.auth.login.LoginException;

import org.bukkit.event.Listener;
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
import fr.djredstone.botdiscord.commands.CommandLock;
import fr.djredstone.botdiscord.commands.CommandMoney;
import fr.djredstone.botdiscord.commands.CommandNon;
import fr.djredstone.botdiscord.commands.CommandOui;
import fr.djredstone.botdiscord.commands.CommandP4;
import fr.djredstone.botdiscord.commands.CommandPing;
import fr.djredstone.botdiscord.commands.CommandQuitteOuDouble;
import fr.djredstone.botdiscord.commands.CommandSend;
import fr.djredstone.botdiscord.commands.CommandTank;
import fr.djredstone.botdiscord.commands.CommandText;
import fr.djredstone.botdiscord.listener.MessageReceivedListener;
import fr.djredstone.botdiscord.tasks.messageByMinuteTest;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Setup implements EventListener, Listener {

	public Setup(Main main) {

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
		
		Main.token = Main.main.getConfig().getString("token");
		Main.tokenMEE6 = Main.main.getConfig().getString("tokenMEE6");
		
		try {
			Main.jda = JDABuilder.createDefault(Main.token).build();
			Main.mee6 = JDABuilder.createDefault(Main.tokenMEE6).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(Main.token);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
	    builder.setActivity(Activity.playing("faire de la redstone"));
	    try {
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    JDABuilder builderMEE6 = JDABuilder.createDefault(Main.tokenMEE6);
	    
	    builderMEE6.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE, CacheFlag.ACTIVITY);
	    try {
	    	builderMEE6.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    new P4Game(main);
	    
	    Main.jda.updateCommands().queue();
	    
	    Main.jda.addEventListener(this);
	    
	    Main.jda.addEventListener(new CommandP4(main));
	    Main.jda.addEventListener(new CommandFindNumber());
	    Main.jda.upsertCommand("number", "Démarre une partie de find number").addOption(OptionType.INTEGER, "nb_max", "Nombre maximum").queue();
	    Main.jda.addEventListener(new CommandQuitteOuDouble());
	    Main.jda.upsertCommand("quitteoudouble", "Démarre une partie de quitte ou double").addOption(OptionType.INTEGER, "nb_depart_mise", "Nombre de départ de la mise").queue();
	    
	    Main.jda.addEventListener(new CommandMoney());
	    Main.jda.upsertCommand("money", "Affiche son nombre de redstones").queue();
	    Main.jda.addEventListener(new CommandDashboard());
	    Main.jda.upsertCommand("dashboard", "???").queue();
	    Main.jda.addEventListener(new CommandDaily());
	    Main.jda.upsertCommand("daily", "Récupère sa redstone quotidienne").queue();
	    
	    Main.jda.addEventListener(new CommandHelp());
	    Main.jda.upsertCommand("aide", "Liste des commandes").queue();
	    Main.jda.addEventListener(new CommandSend());
	    Main.jda.upsertCommand("send", "Envoie un message aux personnes de puissances").addOption(OptionType.STRING, "send_message", "Message").queue();
	    Main.jda.addEventListener(new CommandPing());
	    Main.jda.upsertCommand("ping", "Lance une balle de ping pong, voit en combien de temps je la renvoie").queue();
	    Main.jda.addEventListener(new CommandLink());
	    Main.jda.upsertCommand("link", "Affiche des liens en rapport à allcraft0r").queue();
	    
	    Main.jda.addEventListener(new CommandHask());
	    Main.jda.upsertCommand("ask", "Demande prise en compte").queue();
	    Main.jda.addEventListener(new CommandOui());
	    Main.jda.upsertCommand("oui", "Demande acceptée").addOption(OptionType.STRING, "oui_message", "Message").queue();
	    Main.jda.addEventListener(new CommandNon());
	    Main.jda.upsertCommand("non", "Demande refusée").addOption(OptionType.STRING, "non_message", "Message").queue();
	    Main.jda.addEventListener(new CommandText());
	    Main.jda.upsertCommand("text", "Message personnalisé").addOption(OptionType.CHANNEL, "text_channel", "Channel").addOption(OptionType.STRING, "text", "Texte").queue();
	    
	    Main.jda.addEventListener(new CommandTank());
	    Main.jda.upsertCommand("tank", "AMERICA ! F*CK YEAHH !!").queue();
	    Main.jda.addEventListener(new CommandEyes());
	    Main.jda.upsertCommand("eyes", "I'm watching you...").queue();
	    
	    Main.mee6.addEventListener(new CommandFakeBan());
	    Main.mee6.addEventListener(new CommandFakeResetXP());
	    Main.mee6.addEventListener(new CommandLock());
	    
	    new messageByMinuteTest(main);
	    
	    Main.jda.addEventListener(new MessageReceivedListener(main));
	    
	    try {
	    	Main.jda.awaitReady();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onEvent(@NotNull GenericEvent event) {
		if(event instanceof ReadyEvent) System.out.println("§cBot discord allcraft0r prêt !");
    }

}
