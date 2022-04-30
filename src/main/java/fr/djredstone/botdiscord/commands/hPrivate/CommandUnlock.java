package fr.djredstone.botdiscord.commands.hPrivate;

import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;

public class CommandUnlock {
	
	public CommandUnlock(MessageReceivedEvent event) {

		User user = event.getAuthor();
		Message message = event.getMessage();
		if(message.getMentionedChannels().isEmpty()) {
			UtilsCommands.replyOrSend(String.format("Utilisation : %1$slock <#channel>", Main.getPrefix()), event, null);
			return;
		}
		Guild guild = event.getGuild();
		TextChannel channel = message.getMentionedChannels().get(0);
		TextChannel channelOriginal = event.getTextChannel();

		channel.createPermissionOverride(guild.getPublicRole()).setAllow(Permission.MESSAGE_SEND).queue();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Ce channel a été unlock " + Emoji.fromMarkdown("✅"));
		embed.setAuthor(user.getAsTag(), null, user.getAvatarUrl());
		embed.setColor(Color.GREEN);

        channel.sendMessageEmbeds(embed.build()).queue();
		if(channel != channelOriginal) UtilsCommands.replyOrSend("Le channel a été unlock !", event, null);
        
	}

}
