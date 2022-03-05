package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandHask {
	
	public CommandHask(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(":warning: Message de la hiérarchie :warning:");
		embed.setDescription("Votre demande a été prise en compte. \uD83D\uDC4D\uD83C\uDFFC");
		embed.setFooter("Nous vous informerons lorsque nous aurons plus d'informations. \uD83D\uDCC3");
		embed.setColor(Color.ORANGE);
		
		UtilsCommands.replyOrSend(embed, event1, event2);
			
	}

}
