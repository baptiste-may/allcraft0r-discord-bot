package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandText {
	
	public CommandText(String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
			
		Member member;
		if(event1 != null) {
			member = event1.getMember();
		} else {
			member = event2.getMember();
		}
		
		if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
			UtilsCommands.replyOrSend(Main.noPermMessage, event1, event2);
            return;
        }
		
		if(event2 != null) {
			message = event2.getOption("text").getAsString();
		}
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(":warning: Message de la hiérarchie :warning:");
		embed.setDescription(message.toString());
		embed.setFooter(" - " + member.getUser().getAsTag());
		embed.setColor(Color.YELLOW);
		
		MessageChannel channel;
		if(event2 != null) {
			channel = event2.getOption("text_channel").getAsMessageChannel();
		} else {
			if(!event1.getMessage().getMentionedChannels().isEmpty()) {
				channel = event1.getMessage().getMentionedChannels().get(0);
			} else {
				return;
			}
		}
		
		if(channel == null) {
			UtilsCommands.replyOrSend(embed, event1, event2);
		} else {
			channel.sendMessageEmbeds(embed.build()).queue();
		}
		
	}

}
