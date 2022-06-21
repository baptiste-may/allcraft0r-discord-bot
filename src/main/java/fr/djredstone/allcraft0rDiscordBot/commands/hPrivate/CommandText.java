package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandText {
	
	public CommandText(String message, MessageReceivedEvent event) {

		User user = event.getAuthor();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("  %1$s Message de la hiérarchie %1$s", Emoji.fromMarkdown("⚠️")));
		embed.setDescription(message);
		embed.setFooter(" - " + user.getAsTag());
		embed.setColor(Color.YELLOW);
		
		MessageChannel channel;
		if(!event.getMessage().getMentions().getChannels().isEmpty()) channel = (MessageChannel) event.getMessage().getMentions().getChannels().get(0);
		else return;
		
		if(channel == null) UtilsCommands.replyOrSend(embed, event, null);
		else channel.sendMessageEmbeds(embed.build()).queue();
		
	}
}
