package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandEyes extends ListenerAdapter {
	
	public CommandEyes(@Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
			
		UtilsCommands.replyOrSend(":eye::lips::eye:", event1, event2);
		
	}

}
