package fr.djredstone.allcraft0rDiscordBot.commands.economy;

import java.sql.SQLException;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.classes.money;

public class CommandMoney {
	
	public CommandMoney(SlashCommandInteractionEvent event) throws SQLException {

		event.reply(String.format("%1$s, tu as actuellement **%2$s** %3$s", event.getUser().getAsMention(), money.get(event.getUser()), Main.getRedstoneEmoji())).queue();
		
	}

}
