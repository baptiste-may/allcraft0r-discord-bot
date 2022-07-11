package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import javax.annotation.Nullable;
import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandText {
	
	public CommandText(String message, @Nullable TextChannel channel, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

		User user = null;
		if(event1 != null)
			user = event1.getAuthor();

		if(event2 != null)
			user = event2.getUser();

		assert user != null;

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("  %1$s Message de la hiérarchie %1$s", Emoji.fromMarkdown("⚠️")));
		embed.setDescription(message);
		embed.setFooter(" - " + user.getAsTag());
		embed.setColor(Color.YELLOW);

		if(channel == null) {
			if(event1 != null)
				channel = event1.getTextChannel();
			if(event2 != null)
				channel = event2.getTextChannel();
		} else return;

		channel.sendMessageEmbeds(embed.build()).queue();
		
	}
}
