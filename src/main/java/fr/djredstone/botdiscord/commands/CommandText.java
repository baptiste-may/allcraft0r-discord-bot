package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Objects;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandText {
	
	public CommandText(String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		if(event2 != null) message = Objects.requireNonNull(event2.getOption("text")).getAsString();

		User user;
		if (event1 != null) user = event1.getAuthor();
		else {
			assert event2 != null;
			user = event2.getUser();
		}

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(":warning: Message de la hiérarchie :warning:");
		embed.setDescription(message);
		embed.setFooter(" - " + user.getAsTag());
		embed.setColor(Color.YELLOW);
		
		MessageChannel channel;
		if(event2 != null) channel = Objects.requireNonNull(event2.getOption("text_channel")).getAsMessageChannel();
		else {
			if(!event1.getMessage().getMentionedChannels().isEmpty()) channel = event1.getMessage().getMentionedChannels().get(0);
			else return;
		}
		
		if(channel == null) UtilsCommands.replyOrSend(embed, event1, event2);
		else channel.sendMessageEmbeds(embed.build()).queue();
		
	}
}
