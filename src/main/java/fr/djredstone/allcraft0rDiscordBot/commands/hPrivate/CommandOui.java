package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.awt.*;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandOui {
	
	public CommandOui(SlashCommandInteractionEvent event) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("  %1$s Message de la hiérarchie %1$s", Emoji.fromMarkdown("⚠️")));
		embed.setDescription("Votre demande a été acceptée.");
		embed.setColor(Color.GREEN);
		embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");

		if (event.getOption("text") != null) embed.setFooter(Objects.requireNonNull(event.getOption("text")).getAsString());
		
		event.replyEmbeds(embed.build()).queue();
			
	}

}
