package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandUnlock {
	
	public CommandUnlock(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
        
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
        
		Message message;
		if(event1 != null) {
			message = event1.getMessage();
		} else {
			message = (Message) event2.getTextChannel();
		}
		
        if(message.getMentionedChannels().isEmpty()) {
        	UtilsCommands.replyOrSend("Utilisation : " + Main.prefix + "unlock <#channel>", event1, event2);
        	return;
        }
        
        Guild guild = message.getGuild();
        TextChannel channel = message.getMentionedChannels().get(0);
        PermissionOverride po = channel.createPermissionOverride(guild.getPublicRole()).complete();
        po.getManager().grant(Permission.MESSAGE_SEND).complete();
        po.getManager().setAllow(Permission.MESSAGE_SEND).complete();
        channel.sendMessage("Ce channel a été unlock !");
        UtilsCommands.replyOrSend("Le channel a été unlock !", event1, event2);
        
	}

}
