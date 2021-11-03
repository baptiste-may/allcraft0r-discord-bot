package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandOui {
	
	public CommandOui(String cmd, @Nullable String option, User user, @Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(cmd.equalsIgnoreCase(Main.prefix + "oui")) {
			
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
			
			String message = option;
			if(event2 != null) {
				message = event2.getOption("oui_message").getAsString();
			}

			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(":warning: Message de la hiérarchie :warning:");
			embed.setDescription("Votre demande a été acceptée.");
			embed.setFooter(message.toString());
			embed.setColor(Color.GREEN);
			embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");
			
			UtilsCommands.replyOrSend(embed, event1, event2);
			
		}
	}

}
