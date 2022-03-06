package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UtilsCommands {
	
	public static void replyOrSend(String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		if(event1 != null) {
			event1.getChannel().sendTyping().queue();
    		event1.getChannel().sendMessage(message).complete();
    		event1.getMessage().delete().queue();
    	}
    	
    	if(event2 != null) event2.reply(message).queue();
		
	}
	
	public static void replyOrSend(EmbedBuilder embed, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		if(event1 != null) {
			event1.getChannel().sendTyping().queue();
			event1.getChannel().sendMessageEmbeds(embed.build()).complete();
			event1.getMessage().delete().queue();
    	}
    	
    	if(event2 != null) event2.replyEmbeds(embed.build()).queue();
		
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
