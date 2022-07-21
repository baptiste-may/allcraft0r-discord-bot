package fr.djredstone.allcraft0rDiscordBot.commands.forAll;

import java.awt.*;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.Main;

public class CommandSend {

	public CommandSend(SlashCommandInteractionEvent event) {

		Objects.requireNonNull(Main.getJda().getTextChannelById(Main.getAdminIDChannel())).sendMessage(String.format("Nouveau message de %1$s: %2$s", event.getUser().getName(), Objects.requireNonNull(event.getOption("text")).getAsString())).queue();
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("  %1$s Message de la hiérarchie %1$s", Emoji.fromMarkdown("⚠️")));
		embed.setDescription("Votre message a été correctement envoyé.");
		embed.setFooter(event.getUser().getName());
		embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");
		embed.setColor(Color.GREEN);
		
		event.replyEmbeds(embed.build()).setEphemeral(true).queue();
		
	}
	
}
