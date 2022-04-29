package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import java.awt.*;
import java.util.HashMap;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ItemComponent;

public class UtilsCommands {

	private static final HashMap<User, Integer> commandUsed = new HashMap<>();

	public static HashMap<User, Integer> getCommandUsed() { return commandUsed; }

	public static void replyOrSend(String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2, ItemComponent... components) {
		
		if(event1 != null) {
			event1.getChannel().sendTyping().queue();
    		event1.getChannel().sendMessage(message).setActionRow(components).queue();
    		event1.getMessage().delete().queue();
    	}
    	
    	if(event2 != null) event2.reply(message).addActionRow(components).queue();
		
	}
	
	public static void replyOrSend(EmbedBuilder embed, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2, ItemComponent... components) {
		
		if(event1 != null) {
			event1.getChannel().sendTyping().queue();
			event1.getChannel().sendMessageEmbeds(embed.build()).setActionRow(components).queue();
			event1.getMessage().delete().queue();
    	}
    	
    	if(event2 != null) event2.replyEmbeds(embed.build()).addActionRow(components).queue();
		
	}

	public static EmbedBuilder getEmbedBuilderMusic(User user) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.BLUE);
		embed.setTitle("\uD83C\uDFB6 **Musique** \uD83C\uDFB6");
		embed.setFooter("Commandé par " + user.getAsTag(), user.getAvatarUrl());
		return embed;
	}

	public static EmbedBuilder getEmbedBuilderMusic() {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.BLUE);
		embed.setTitle("\uD83C\uDFB6 **Musique** \uD83C\uDFB6");
		return embed;
	}

}
