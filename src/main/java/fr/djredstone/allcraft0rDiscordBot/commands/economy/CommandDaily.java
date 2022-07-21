package fr.djredstone.allcraft0rDiscordBot.commands.economy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.Utils;
import fr.djredstone.allcraft0rDiscordBot.classes.money;

public class CommandDaily {

	private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	private static final int value = 250;
	
	public CommandDaily(SlashCommandInteractionEvent event) throws SQLException {

		PreparedStatement preparedStatement = Utils.createPreparedStatement("SELECT uuid, last_daily FROM daily WHERE uuid = ?");
		preparedStatement.setString(1, event.getUser().getId());
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			final String lastDate = format.format(resultSet.getDate("last_daily"));
			if (format.format(System.currentTimeMillis()).equals(lastDate))
				event.reply(String.format("Vous avez déjà récupéré votre redstone quotidienne, %1$s", event.getUser().getAsMention())).setEphemeral(true).queue();
			else {
				preparedStatement = Utils.createPreparedStatement("UPDATE daily SET last_daily = ? WHERE uuid = ?");
				preparedStatement.setString(2, event.getUser().getId());
				preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));

				preparedStatement.executeUpdate();

				giveMoney(event);
			}
		} else {
			preparedStatement = Utils.createPreparedStatement("INSERT INTO daily VALUES(?, ?)");
			preparedStatement.setString(1, event.getUser().getId());
			preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

			preparedStatement.executeUpdate();

			giveMoney(event);
		}
	}

	private static void giveMoney(SlashCommandInteractionEvent event) throws SQLException {
		money.add(event.getUser(), value);
		event.reply(String.format("Tu as reçu **%1$s** %2$s %3$s", value, Main.getRedstoneEmoji(), event.getUser().getAsMention())).queue();
	}
	
}
