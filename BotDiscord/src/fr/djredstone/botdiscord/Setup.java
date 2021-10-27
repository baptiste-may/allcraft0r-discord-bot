package fr.djredstone.botdiscord;

import javax.security.auth.login.LoginException;

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
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Setup {

	public Setup(Main main) {

		if(!Main.main.getConfig().contains("minNbMessageWarn")) {
			Main.main.getConfig().set("minNbMessageWarn", 4);
		}
		if(!Main.main.getConfig().contains("token")) {
			Main.main.getConfig().set("token", "YOUR TOKEN HERE");
		}
		if(!Main.main.getConfig().contains("tokenMEE6")) {
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
	    
	    Main.jda.addEventListener(this);
	    
	    Main.jda.addEventListener(new CommandP4(main));
	    Main.jda.addEventListener(new CommandFindNumber());
	    Main.jda.addEventListener(new CommandQuitteOuDouble());
	    
	    Main.jda.addEventListener(new CommandMoney());
	    Main.jda.addEventListener(new CommandDashboard());
	    Main.jda.addEventListener(new CommandDaily());;
	    
	    Main.jda.addEventListener(new CommandHelp());
	    Main.jda.addEventListener(new CommandSend());
	    Main.jda.addEventListener(new CommandPing());
	    Main.jda.addEventListener(new CommandLink());
	    
	    Main.jda.addEventListener(new CommandHask());
	    Main.jda.addEventListener(new CommandOui());
	    Main.jda.addEventListener(new CommandNon());
	    Main.jda.addEventListener(new CommandText());
	    
	    Main.jda.addEventListener(new CommandTank());
	    Main.jda.addEventListener(new CommandEyes());
	    
	    Main.mee6.addEventListener(new CommandFakeBan());
	    Main.mee6.addEventListener(new CommandFakeResetXP());
	    
	    new messageByMinuteTest(main);
	    
	    Main.jda.addEventListener(new messageReactionAddListener(main));
	    
	    try {
	    	Main.jda.awaitReady();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
