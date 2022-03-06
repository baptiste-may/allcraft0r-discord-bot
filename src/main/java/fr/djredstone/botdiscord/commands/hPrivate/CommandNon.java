package fr.djredstone.botdiscord.commands.hPrivate;

import javax.annotation.Nullable;
import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.commands.UtilsCommands;

public class CommandNon {
	
	public CommandNon(@Nullable String option, MessageReceivedEvent event) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(":warning: Message de la hiérarchie :warning:");
		embed.setDescription("Votre demande a été refusée.");
		embed.setFooter(option);
		embed.setColor(Color.RED);
		embed.setThumbnail("https://images.emojiterra.com/google/android-10/512px/274c.png");
		
		UtilsCommands.replyOrSend(embed, event, null);
		
	}

}
