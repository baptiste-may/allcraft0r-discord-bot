package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class UtilsCommands {
	
	public static void replyOrSend(String message, @Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(event1 != null) {
    		
    		event1.getChannel().sendMessage(message).queue();
    		event1.getChannel().sendTyping().queue();
    		event1.getMessage().delete().queue();
    		
    	}
    	
    	if(event2 != null) {
    		
    		event2.reply(message).queue();
    		
    	}
		
	}
	
	public static void replyOrSend(EmbedBuilder embed, @Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(event1 != null) {
    		
    		event1.getChannel().sendMessage(embed.build()).queue();
    		event1.getMessage().delete().queue();
    		
    	}
    	
    	if(event2 != null) {
    		
    		event2.replyEmbeds(embed.build()).queue();
    		
    	}
		
	}

}
