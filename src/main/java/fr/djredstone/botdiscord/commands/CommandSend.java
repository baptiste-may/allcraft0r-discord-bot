package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Objects;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandSend {

	public CommandSend(User user, String message, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
			
		if(event2 != null) message = Objects.requireNonNull(event2.getOption("send_message")).getAsString();
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("  :warning: Message de la hiérarchie :warning:");
		embed.setDescription("Votre message a été correctement envoyé.");
		embed.setFooter(user.getName());
		embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");
		embed.setColor(Color.GREEN);
		
		if(event2 != null) message = Objects.requireNonNull(event2.getOption("send_message")).getAsString();
		
		UtilsCommands.replyOrSend(embed, event1, event2);
		Objects.requireNonNull(Main.getJda().getTextChannelById(Main.getAdminIDChannel())).sendMessage("Nouveau message de " + user.getName() + " : " + message).queue();
		
	}
	
}
