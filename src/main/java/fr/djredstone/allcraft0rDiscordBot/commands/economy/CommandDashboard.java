package fr.djredstone.allcraft0rDiscordBot.commands.economy;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.Utils;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandDashboard extends ListenerAdapter {

	public CommandDashboard(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

		try {

			final EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Dashboard de redstone " + Main.getRedstoneEmoji());

			final StringBuilder stringBuilder = new StringBuilder();

			final ResultSet resultSet = Utils.createPreparedStatement("SELECT uuid, money FROM ALLCRAFT0R_user_money ORDER BY money DESC LIMIT 10").executeQuery();

			int i2 = 1;
			while (resultSet.next()) {
				String name;
				try {
					User user = Main.getJda().retrieveUserById(resultSet.getString("uuid")).complete();
					name = user.getAsMention();
				} catch (NullPointerException ignored) {
					name = "`unknown`";
				}
				stringBuilder.append("\n").append(i2).append(" - ").append(name).append(" - **").append(resultSet.getInt("money")).append("**");
				i2 ++;
			}

			embed.setDescription(stringBuilder);
			UtilsCommands.replyOrSend(embed, event1, event2);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
