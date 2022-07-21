package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.awt.*;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandNon {
	
	public CommandNon(SlashCommandInteractionEvent event) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("  %1$s Message de la hiérarchie %1$s", Emoji.fromMarkdown("⚠️")));
		embed.setDescription("Votre demande a été refusée.");
		embed.setColor(Color.RED);
		embed.setThumbnail("https://images.emojiterra.com/google/android-10/512px/274c.png");

		if (event.getOption("text") != null) embed.setFooter(Objects.requireNonNull(event.getOption("text")).getAsString());
		
		event.replyEmbeds(embed.build()).queue();
		
	}

}
