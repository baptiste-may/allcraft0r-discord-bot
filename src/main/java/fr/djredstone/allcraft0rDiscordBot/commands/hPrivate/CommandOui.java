package fr.djredstone.allcraft0rDiscordBot.commands.hPrivate;

import javax.annotation.Nullable;
import java.awt.*;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandOui {
	
	public CommandOui(@Nullable String option, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("  %1$s Message de la hiérarchie %1$s", Emoji.fromMarkdown("⚠️")));
		embed.setDescription("Votre demande a été acceptée.");
		embed.setFooter(option);
		embed.setColor(Color.GREEN);
		embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");
		
		UtilsCommands.replyOrSend(embed, event1, event2);
			
	}

}
