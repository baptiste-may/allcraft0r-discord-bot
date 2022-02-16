package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandHask {
	
	public CommandHask(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
			
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
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(":warning: Message de la hiérarchie :warning:");
		embed.setDescription("Votre demande a été prise en compte. :thumbsup:");
		embed.setFooter("Nous vous informerons lorsque nous aurons plus d'informations. :receipt:");
		embed.setColor(Color.ORANGE);
		
		UtilsCommands.replyOrSend(embed, event1, event2);
			
	}

}
