package fr.djredstone.allcraft0rDiscordBot.commands;

import java.awt.*;
import java.sql.SQLException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.Setup;
import fr.djredstone.allcraft0rDiscordBot.classes.blacklist;

public class UtilsCommands {

	private static final String SQLErrorMessage = "Une erreur vient d'apparaître dans la base de donnée ! Veuillez réessayer. Si cette erreur continue d'apparaître, veuillez contacter un administrateur.";
	public static String getSQLErrorMessage() { return SQLErrorMessage; }

	public static EmbedBuilder getEmbedBuilderMusic() {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.BLUE);
		embed.setTitle(String.format("%1$s Musique %1$s", Emoji.fromMarkdown("\uD83C\uDFB6")));
		return embed;
	}

	public static boolean chekBlacklist(SlashCommandInteractionEvent event2) {

		try {
			if (blacklist.contains(event2.getUser())) return true;
		} catch (SQLException e) {
			e.printStackTrace();
			Setup.DBConnect();
			return true;
		}
		return false;
	}

}
