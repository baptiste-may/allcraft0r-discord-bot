package fr.djredstone.allcraft0rDiscordBot.commands.economy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.annotation.Nullable;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.Utils;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;
import fr.djredstone.allcraft0rDiscordBot.classes.money;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandDaily {

	private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	private static final int value = 250;
	
	public CommandDaily(User user, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) throws SQLException {

		PreparedStatement preparedStatement = Utils.createPreparedStatement("SELECT uuid, last_daily FROM daily WHERE uuid = ?");
		preparedStatement.setString(1, user.getId());
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			final String lastDate = format.format(resultSet.getDate("last_daily"));
			if (format.format(System.currentTimeMillis()).equals(lastDate))
				UtilsCommands.replyOrSend(String.format("Vous avez déjà récupéré votre redstone quotidienne, %1$s", user.getAsMention()), event1, event2);
			else {
				preparedStatement = Utils.createPreparedStatement("UPDATE daily SET last_daily = ? WHERE uuid = ?");
				preparedStatement.setString(2, user.getId());
				preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));

				preparedStatement.executeUpdate();

				giveMoney(user, event1, event2);
			}
		} else {
			preparedStatement = Utils.createPreparedStatement("INSERT INTO daily VALUES(?, ?)");
			preparedStatement.setString(1, user.getId());
			preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

			preparedStatement.executeUpdate();

			giveMoney(user, event1, event2);
		}
	}

	private static void giveMoney(User user, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) throws SQLException {
		money.add(user, value);
		UtilsCommands.replyOrSend(String.format("Tu as reçu **%1$s** %2$s %3$s", value, Main.getRedstoneEmoji(), user.getAsMention()), event1, event2);
	}
	
}
