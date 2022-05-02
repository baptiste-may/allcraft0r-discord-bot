package fr.djredstone.botdiscord.commands.economy;

import java.sql.SQLException;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;
import fr.djredstone.botdiscord.classes.money;

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
