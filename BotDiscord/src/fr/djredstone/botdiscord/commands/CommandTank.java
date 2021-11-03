package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandTank {
	
	public CommandTank(String cmd, User user, @Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(cmd.equalsIgnoreCase(Main.prefix + "tank")) {
			
			UtilsCommands.replyOrSend("https://tenor.com/view/tank-gif-10952763", event1, event2);
			
		}
	}

}
