package fr.djredstone.allcraft0rDiscordBot.commands.economy;

import java.sql.SQLException;

import javax.annotation.Nullable;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;
import fr.djredstone.allcraft0rDiscordBot.classes.money;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandMoney {
	
	public CommandMoney(User user, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
        	
        try {
			UtilsCommands.replyOrSend(String.format("%1$s, tu as actuellement **%2$s** %3$s", user.getAsMention(), money.get(user), Main.getRedstoneEmoji()), event1, event2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
