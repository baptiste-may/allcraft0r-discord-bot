package fr.djredstone.botdiscord.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;

public class Logger {
	
	String channelID = "877824690452840488";
	
	public void messageEdited(MessageUpdateEvent event) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("**Message modifié**");
		embed.setDescription("> " + event.getMessage());
		embed.setFooter(event.getMessageId());
		event.getGuild().getTextChannelById(channelID).sendMessage(embed.build()).queue();
	}
	
	public void messageDelete(MessageDeleteEvent event) {
		
	}

}
