package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UtilsCommands {
	
	public static void replyOrSend(String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(event1 != null) {
    		event1.getChannel().sendMessage(message).queue();
    		event1.getChannel().sendTyping().queue();
    		event1.getMessage().delete().queue();
    	}
    	
    	if(event2 != null) event2.reply(message).queue();
		
	}
	
	public static void replyOrSend(EmbedBuilder embed, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(event1 != null) {
    		event1.getChannel().sendMessageEmbeds(embed.build()).queue();
    		event1.getMessage().delete().queue();
    	}
    	
    	if(event2 != null) event2.replyEmbeds(embed.build()).queue();
		
	}

}
