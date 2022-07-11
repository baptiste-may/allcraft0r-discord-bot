package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import javax.annotation.Nullable;
import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandLock {
	
	public CommandLock(@Nullable TextChannel targetChannel, @Nullable String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

		if(targetChannel == null) {
			UtilsCommands.replyOrSend(String.format("Utilisation : %1$slock <#channel>", Main.getPrefix()), event1, event2);
			return;
		}

		User user = null;
		Guild guild = null;
		TextChannel channelOriginal = null;
		if (event1 != null) {
			user = event1.getAuthor();
			guild = event1.getGuild();
			channelOriginal = event1.getTextChannel();
		}
		if (event2 != null) {
			user = event2.getUser();
			guild = event2.getGuild();
			channelOriginal = event2.getTextChannel();
		}
		assert user != null;
		assert guild != null;

		targetChannel.getPermissionContainer().upsertPermissionOverride(guild.getPublicRole()).setDenied(Permission.MESSAGE_SEND).queue();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Ce channel a été lock " + Emoji.fromMarkdown("⛔️"));
		embed.setDescription(message);
		embed.setAuthor(user.getAsTag(), null, user.getAvatarUrl());
		embed.setColor(Color.RED);

		targetChannel.sendMessageEmbeds(embed.build()).queue();
		if(targetChannel != channelOriginal) UtilsCommands.replyOrSend("Le channel a été lock !", event1, event2);
        
	}

}
