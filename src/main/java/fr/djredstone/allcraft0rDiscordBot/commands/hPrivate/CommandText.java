package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.awt.*;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandText {
	
	public CommandText(SlashCommandInteractionEvent event) {

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("  %1$s Message de la hiérarchie %1$s", Emoji.fromMarkdown("⚠️")));
		embed.setFooter(" - " + event.getUser().getAsTag());
		embed.setColor(Color.YELLOW);

		if (event.getOption("text") != null) embed.setDescription(Objects.requireNonNull(event.getOption("text")).getAsString());

		if(event.getOption("channel") == null) {
			event.replyEmbeds(embed.build()).queue();
		} else {
			Objects.requireNonNull(Objects.requireNonNull(event.getOption("channel")).getAsTextChannel()).sendMessageEmbeds(embed.build()).queue();
			event.reply("Le message a été envoyé !").setEphemeral(true).queue();
		}
		
	}
}
