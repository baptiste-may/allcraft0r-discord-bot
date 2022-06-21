package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandHask {
	
	public CommandHask(MessageReceivedEvent event) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("  %1$s Message de la hiérarchie %1$s", Emoji.fromMarkdown("⚠️")));
		embed.setDescription("Votre demande a été prise en compte. " + Emoji.fromMarkdown("\uD83D\uDC4D\uD83C\uDFFC"));
		embed.setFooter("Nous vous informerons lorsque nous aurons plus d'informations. " + Emoji.fromMarkdown("\uD83D\uDCC3"));
		embed.setColor(Color.ORANGE);
		
		UtilsCommands.replyOrSend(embed, event, null);
			
	}

}
