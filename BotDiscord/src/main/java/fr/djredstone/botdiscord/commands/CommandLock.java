package fr.djredstone.botdiscord.commands;

import java.awt.*;
import java.util.ArrayList;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;

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

public class CommandLock {
	
	public CommandLock(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
        
		Member member;
		if(event1 != null) member = event1.getMember();
		else {
			assert event2 != null;
			member = event2.getMember();
		}

		assert member != null;
		if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
           UtilsCommands.replyOrSend(Main.noPermMessage, event1, event2);
           return;
        }
        
		Message message;
		if(event1 != null) message = event1.getMessage();
		else message = (Message) event2.getTextChannel();
		
        if(message.getMentionedChannels().isEmpty()) {
        	UtilsCommands.replyOrSend("Utilisation : " + Main.prefix + "lock <#channel>", event1, event2);
        	return;
        }
        
        Guild guild = message.getGuild();
        TextChannel channel = message.getMentionedChannels().get(0);
		TextChannel channelOriginal;
		if (event1 != null) channelOriginal = event1.getTextChannel();
		else channelOriginal = event2.getTextChannel();
        
        ChannelManager cm = new ChannelManagerImpl(channel);
        ArrayList<Permission> list = new ArrayList<>();
        list.add(Permission.MESSAGE_WRITE);
        cm.putRolePermissionOverride(guild.getPublicRole().getIdLong(), null, list).queue();

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Ce channel a été lock ⛔️");
		embed.setAuthor(member.getUser().getAsTag(), null, member.getAvatarUrl());
		embed.setColor(Color.RED);

		channel.sendMessageEmbeds(embed.build()).queue();
		if(channel != channelOriginal) UtilsCommands.replyOrSend("Le channel a été lock !", event1, event2);
        
	}

}
