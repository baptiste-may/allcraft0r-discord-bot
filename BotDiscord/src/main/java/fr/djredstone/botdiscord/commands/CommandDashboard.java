package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.botdiscord.Main;

public class CommandDashboard extends ListenerAdapter {

	public CommandDashboard(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) throws SQLException {

		LinkedHashMap<String, Integer> dashboard = new LinkedHashMap<>();

		PreparedStatement preparedStatement = Main.databaseManager.getDbConnection().getConnection().prepareStatement("SELECT uuid, money FROM ALLCRAFT0R_user_money ORDER BY money DESC LIMIT 10");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			dashboard.put(resultSet.getString("uuid"), resultSet.getInt("money"));
		}

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Dashboard de redstone " + Main.redstoneEmoji);

		StringBuilder stringBuilder = new StringBuilder();
		int i2 = 1;
		for (Map.Entry<String, Integer> entry : dashboard.entrySet()) {
			String name;
			try {
				User user = Main.jda.retrieveUserById(entry.getKey()).complete();
				name = user.getAsMention();
			} catch (IllegalArgumentException | NullPointerException ignored) {
				name = "`unknown`";
			}
			stringBuilder.append("\n").append(i2).append(" - ").append(name).append(" - **").append(entry.getValue()).append("**");
			i2 ++;
		}

		embed.setDescription(stringBuilder);
		UtilsCommands.replyOrSend(embed, event1, event2);

	}
}
