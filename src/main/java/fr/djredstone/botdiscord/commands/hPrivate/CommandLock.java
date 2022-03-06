package fr.djredstone.botdiscord.commands.hPrivate;

import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;

public class CommandLock {
	
	public CommandLock(MessageReceivedEvent event) {

		Message message = event.getMessage();
		String[] args = message.getContentRaw().split(" ");

		if(message.getMentionedChannels().isEmpty()) {
			UtilsCommands.replyOrSend("Utilisation : " + Main.getPrefix() + "lock <#channel>", event, null);
			return;
		}

		User user = event.getAuthor();
		Guild guild = event.getGuild();
		TextChannel channel = message.getMentionedChannels().get(0);
		TextChannel channelOriginal = event.getTextChannel();

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 2; i < args.length; i++) stringBuilder.append(args[i]).append(" ");
		String messageStr = stringBuilder.toString();

		channel.createPermissionOverride(guild.getPublicRole()).setDeny(Permission.MESSAGE_SEND).queue();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Ce channel a été lock ⛔️");
		embed.setDescription(messageStr);
		embed.setAuthor(user.getAsTag(), null, user.getAvatarUrl());
		embed.setColor(Color.RED);

		channel.sendMessageEmbeds(embed.build()).queue();
		if(channel != channelOriginal) UtilsCommands.replyOrSend("Le channel a été lock !", event, null);
        
	}

}
