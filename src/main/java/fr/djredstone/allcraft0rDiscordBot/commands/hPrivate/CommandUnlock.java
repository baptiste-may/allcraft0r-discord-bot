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

public class CommandUnlock {
	
	public CommandUnlock(MessageReceivedEvent event) {

		User user = event.getAuthor();
		Message message = event.getMessage();
		if(message.getMentions().getChannels().isEmpty()) {
			UtilsCommands.replyOrSend(String.format("Utilisation : %1$slock <#channel>", Main.getPrefix()), event, null);
			return;
		}
		Guild guild = event.getGuild();
		GuildChannel channel = message.getMentions().getChannels().get(0);
		TextChannel channelOriginal = event.getTextChannel();

		channel.getPermissionContainer().upsertPermissionOverride(guild.getPublicRole()).setAllowed(Permission.MESSAGE_SEND).queue();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Ce channel a été unlock " + Emoji.fromMarkdown("✅"));
		embed.setAuthor(user.getAsTag(), null, user.getAvatarUrl());
		embed.setColor(Color.GREEN);

		((TextChannel) channel).sendMessageEmbeds(embed.build()).queue();
		if(channel != channelOriginal) UtilsCommands.replyOrSend("Le channel a été unlock !", event, null);
        
	}

}
