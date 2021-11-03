package fr.djredstone.botdiscord.commands;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandDaily {
	
	private Set<User> hadGet = new HashSet<>();
	
	public CommandDaily(String cmd, User user, @Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(cmd.equalsIgnoreCase(Main.prefix + "daily")) {
			
			if(!hadGet.contains(user)) {
				
				Main.setMoney(user, Main.getMoney(user) + 200);
				
				UtilsCommands.replyOrSend("Tu as reçu **200 redstones** " + Main.redstoneEmoji + user.getAsMention(), event1, event2);
				
				hadGet.add(user);
				
			} else {
				
				UtilsCommands.replyOrSend("Vous avez déjà récupérer votre redstone quotidienne, " + user.getAsMention(), event1, event2);
				
			}
			
		}
		
	}
	
}
