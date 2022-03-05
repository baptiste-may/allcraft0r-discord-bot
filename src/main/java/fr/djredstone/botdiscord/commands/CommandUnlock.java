package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.Main;

public class CommandUnlock {
	
	public CommandUnlock(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

		User user;
		Message message;
		Guild guild;
		TextChannel channel;
		TextChannel channelOriginal;
		if(event1 != null) {
			user = event1.getAuthor();
			message = event1.getMessage();
			if(message.getMentionedChannels().isEmpty()) {
				UtilsCommands.replyOrSend("Utilisation : " + Main.getPrefix() + "lock <#channel>", event1, event2);
				return;
			}
			guild = event1.getGuild();
			channel = message.getMentionedChannels().get(0);
			channelOriginal = event1.getTextChannel();
		} else {
			assert event2 != null;
			user = event2.getUser();
			guild = event2.getGuild();
			channel = (TextChannel) Objects.requireNonNull(event2.getOption("lock_channel")).getAsMessageChannel();
			channelOriginal = event2.getTextChannel();
		}

		assert channel != null;
		assert guild != null;

		channel.createPermissionOverride(guild.getPublicRole()).setAllow(Permission.MESSAGE_SEND).queue();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Ce channel a été unlock ✅");
		embed.setAuthor(user.getAsTag(), null, user.getAvatarUrl());
		embed.setColor(Color.GREEN);

        channel.sendMessageEmbeds(embed.build()).queue();
		if(channel != channelOriginal) UtilsCommands.replyOrSend("Le channel a été unlock !", event1, event2);
        
	}

}