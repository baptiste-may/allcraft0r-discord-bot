package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandPing {
	
	public CommandPing(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
			
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(":ping_pong: **Pong !**");
		embed.setDescription(":hourglass_flowing_sand: " + Main.jda.getGatewayPing() + " ms");
		embed.setColor(Color.ORANGE);
		
		UtilsCommands.replyOrSend(embed, event1, event2);
	}

}
