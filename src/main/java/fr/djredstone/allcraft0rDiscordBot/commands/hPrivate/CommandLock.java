package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandLock {
	
	public CommandLock(MessageReceivedEvent event) {

		Message message = event.getMessage();
		String[] args = message.getContentRaw().split(" ");

		if(message.getMentions().getChannels().isEmpty()) {
			UtilsCommands.replyOrSend(String.format("Utilisation : %1$slock <#channel>", Main.getPrefix()), event, null);
			return;
		}

		User user = event.getAuthor();
		Guild guild = event.getGuild();
		GuildChannel channel = message.getMentions().getChannels().get(0);
		TextChannel channelOriginal = event.getTextChannel();

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 2; i < args.length; i++) stringBuilder.append(args[i]).append(" ");
		String messageStr = stringBuilder.toString();

		channel.getPermissionContainer().upsertPermissionOverride(guild.getPublicRole()).setDenied(Permission.MESSAGE_SEND).queue();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Ce channel a été lock " + Emoji.fromMarkdown("⛔️"));
		embed.setDescription(messageStr);
		embed.setAuthor(user.getAsTag(), null, user.getAvatarUrl());
		embed.setColor(Color.RED);

		((TextChannel) channel).sendMessageEmbeds(embed.build()).queue();
		if(channel != channelOriginal) UtilsCommands.replyOrSend("Le channel a été lock !", event, null);
        
	}

}
