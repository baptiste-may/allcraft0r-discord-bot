package fr.djredstone.allcraft0rDiscordBot.commands;

import javax.annotation.Nullable;

import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ItemComponent;

public class UtilsCommands {

	private static final String SQLErrorMessage = "Une erreur vient d'apparaître dans la base de donnée !";
	public static String getSQLErrorMessage() { return SQLErrorMessage; }

	public static void replyOrSend(String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2, ItemComponent... components) {
		
		if(event1 != null) {
			event1.getChannel().sendTyping().queue();
			if (components.length == 0) event1.getChannel().sendMessage(message).queue();
			else event1.getChannel().sendMessage(message).setActionRow(components).queue();
    		event1.getMessage().delete().queue();
    	}
    	
    	if(event2 != null) {
			if (components.length == 0) event2.reply(message).queue();
			else event2.reply(message).addActionRow(components).queue();
		}
		
	}
	
	public static void replyOrSend(EmbedBuilder embed, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2, ItemComponent... components) {
		
		if(event1 != null) {
			event1.getChannel().sendTyping().queue();
			if (components.length == 0) event1.getChannel().sendMessageEmbeds(embed.build()).queue();
			else event1.getChannel().sendMessageEmbeds(embed.build()).setActionRow(components).queue();
			event1.getMessage().delete().queue();
    	}
    	
    	if(event2 != null) {
			if (components.length == 0) event2.replyEmbeds(embed.build()).queue();
			else event2.replyEmbeds(embed.build()).addActionRow(components).queue();
		}
		
	}

	public static EmbedBuilder getEmbedBuilderMusic(User user) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.BLUE);
		embed.setTitle(String.format("%1$s Musique %1$s", Emoji.fromMarkdown("\uD83C\uDFB6")));
		embed.setFooter("Commandé par " + user.getAsTag(), user.getAvatarUrl());
		return embed;
	}

	public static EmbedBuilder getEmbedBuilderMusic() {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.BLUE);
		embed.setTitle(String.format("%1$s Musique %1$s", Emoji.fromMarkdown("\uD83C\uDFB6")));
		return embed;
	}

}
