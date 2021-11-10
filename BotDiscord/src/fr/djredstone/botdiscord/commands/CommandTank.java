package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandTank {
	
	public CommandTank(@Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
			
		UtilsCommands.replyOrSend("https://tenor.com/view/tank-gif-10952763", event1, event2);
	}

}
