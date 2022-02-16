package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.ChannelManager;
import net.dv8tion.jda.internal.managers.ChannelManagerImpl;

import fr.djredstone.botdiscord.Main;

public class CommandLock {
	
	public CommandLock(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
        
		Member member;
		Message message;
		Guild guild;
		TextChannel channel;
		TextChannel channelOriginal;
		String messageStr;
		if(event1 != null) {
			member = event1.getMember();
			message = event1.getMessage();
			String[] args = message.getContentRaw().split(" ");
			if(message.getMentionedChannels().isEmpty()) {
				UtilsCommands.replyOrSend("Utilisation : " + Main.prefix + "lock <#channel>", event1, event2);
				return;
			}
			guild = event1.getGuild();
			channel = message.getMentionedChannels().get(0);
			channelOriginal = event1.getTextChannel();
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 2; i < args.length; i++) stringBuilder.append(args[i]).append(" ");
			messageStr = stringBuilder.toString();
		} else {
			assert event2 != null;
			member = event2.getMember();
			guild = event2.getGuild();
			channel = (TextChannel) Objects.requireNonNull(event2.getOption("lock_channel")).getAsMessageChannel();
			channelOriginal = event2.getTextChannel();
			messageStr = Objects.requireNonNull(event2.getOption("lock_message")).getAsString();
		}

		assert member != null;
		assert channel != null;
		assert guild != null;

		if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
           UtilsCommands.replyOrSend(Main.noPermMessage, event1, event2);
           return;
        }

		ChannelManager cm = new ChannelManagerImpl(channel);
        ArrayList<Permission> list = new ArrayList<>();
        list.add(Permission.MESSAGE_WRITE);
		cm.putRolePermissionOverride(guild.getPublicRole().getIdLong(), null, list).queue();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Ce channel a été lock ⛔️");
		embed.setDescription(messageStr);
		embed.setAuthor(member.getUser().getAsTag(), null, member.getAvatarUrl());
		embed.setColor(Color.RED);

		channel.sendMessageEmbeds(embed.build()).queue();
		if(channel != channelOriginal) UtilsCommands.replyOrSend("Le channel a été lock !", event1, event2);
        
	}

}
