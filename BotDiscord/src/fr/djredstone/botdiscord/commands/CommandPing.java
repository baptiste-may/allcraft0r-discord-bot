package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandPing extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("ping")) {
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(":ping_pong: **Pong !**");
			embed.setDescription(":hourglass_flowing_sand: " + Main.jda.getGatewayPing() + " ms");
			embed.setColor(Color.ORANGE);
			
			event.replyEmbeds(embed.build()).queue();
			event.getChannel().sendTyping().queue();
			
		}
	}

}
