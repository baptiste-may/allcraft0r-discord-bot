package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandEyes extends ListenerAdapter {
	
	public CommandEyes(String cmd, User user, @Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(cmd.equalsIgnoreCase(Main.prefix + "eyes")) {
			
			UtilsCommands.replyOrSend(":eye::lips::eye:", event1, event2);
			
		}
		
	}

}
