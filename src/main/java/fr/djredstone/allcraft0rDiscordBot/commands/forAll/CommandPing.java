package fr.djredstone.allcraft0rDiscordBot.commands.forAll;

import java.awt.Color;

import javax.annotation.Nullable;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandPing {
	
	public CommandPing(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
			
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(String.format("%1$s Pong !", Emoji.fromMarkdown("\uD83C\uDFD3")));
		embed.setDescription(String.format("%1$s %2$s ms", Emoji.fromMarkdown("⏳"), Main.getJda().getGatewayPing()));
		embed.setColor(Color.ORANGE);
		
		UtilsCommands.replyOrSend(embed, event1, event2);
	}

}
