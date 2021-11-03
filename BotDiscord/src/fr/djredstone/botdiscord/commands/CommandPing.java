package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandPing {
	
	public CommandPing(String cmd, User user, @Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(cmd.equalsIgnoreCase(Main.prefix + "ping")) {
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(":ping_pong: **Pong !**");
			embed.setDescription(":hourglass_flowing_sand: " + Main.jda.getGatewayPing() + " ms");
			embed.setColor(Color.ORANGE);
			
			UtilsCommands.replyOrSend(embed, event1, event2);
			
		}
	}

}
