package fr.djredstone.botdiscord;

import org.bukkit.plugin.java.JavaPlugin;
import javax.security.auth.login.LoginException;

import fr.djredstone.botdiscord.commands.CommandFakeBan;
import fr.djredstone.botdiscord.commands.CommandHelp;
import fr.djredstone.botdiscord.commands.CommandPrefix;
import fr.djredstone.botdiscord.commands.CommandSend;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main extends JavaPlugin {
	
	static String token = "ODY1NTAyODczNTU5NjI5ODM0.YPE8XQ.d2X4XXJ_9tyeNnqjg7V73Tq21-w";
	public static String prefix = "!h";
	
	public static JDA jda;
	
	@Override
	public void onEnable() {
		
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
	    
	    jda.addEventListener(new CommandHelp());
	    jda.addEventListener(new CommandSend());
	    jda.addEventListener(new CommandFakeBan());
	    jda.addEventListener(new CommandPrefix());
	    
	    jda.upsertCommand("haide", "Affiche de l'aide").queue();
		
	}
	
	@Override
	public void onDisable() {
		
	}

}
