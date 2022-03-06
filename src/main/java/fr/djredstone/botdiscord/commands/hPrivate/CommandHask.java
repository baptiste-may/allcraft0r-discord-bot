package fr.djredstone.botdiscord.commands.hPrivate;

import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.commands.UtilsCommands;

public class CommandHask {
	
	public CommandHask(MessageReceivedEvent event) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(":warning: Message de la hiérarchie :warning:");
		embed.setDescription("Votre demande a été prise en compte. \uD83D\uDC4D\uD83C\uDFFC");
		embed.setFooter("Nous vous informerons lorsque nous aurons plus d'informations. \uD83D\uDCC3");
		embed.setColor(Color.ORANGE);
		
		UtilsCommands.replyOrSend(embed, event, null);
			
	}

}
