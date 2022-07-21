package fr.djredstone.allcraft0rDiscordBot.commands.forAll;

import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.Main;

public class CommandPing {
	
	public CommandPing(SlashCommandInteractionEvent event) {
			
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("%1$s Pong !", Emoji.fromMarkdown("\uD83C\uDFD3")));
		embed.setDescription(String.format("%1$s %2$s ms", Emoji.fromMarkdown("⏳"), Main.getJda().getGatewayPing()));
		embed.setColor(Color.ORANGE);
		
		event.replyEmbeds(embed.build()).setEphemeral(true).queue();
	}

}
