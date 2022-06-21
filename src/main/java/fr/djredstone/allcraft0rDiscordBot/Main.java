package fr.djredstone.allcraft0rDiscordBot;

import java.util.HashMap;

import fr.djredstone.allcraft0rDiscordBot.mysql.DatabaseManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class Main {

	private static final String prefix = "!";
	public static String getPrefix() { return prefix; }

	private static final String noPermMessage = "Vous n'êtes pas une personne de puissance.";
	public static String getNoPermMessage() { return noPermMessage; }

	private static String adminIDChannel;
	public static String getAdminIDChannel() { return adminIDChannel; }
	public static void setAdminIDChannel(String ID) { adminIDChannel = ID; }

	private static final String redstoneEmoji = "<:redstone:503978809645727745>";
	public static String getRedstoneEmoji() { return redstoneEmoji; }

	private static final HashMap<User, Integer> messageByMinute = new HashMap<>();
	public static HashMap<User, Integer> getMessageByMinute() { return messageByMinute; }

	private static DatabaseManager databaseManager;
	public static DatabaseManager getDatabaseManager() { return databaseManager; }
	public static void setDatabaseManager(DatabaseManager databaseManager) { Main.databaseManager = databaseManager; }

	private static JDA jda;
	public static JDA getJda() { return jda; }
	public static void setJda(JDA jda) { Main.jda = jda; }

	private static JDA mee6;
	public static JDA getMee6() { return mee6; }
	public static void setMee6(JDA jda) { mee6 = jda; }

	public static void main(String[] args) {
		
		new Setup();
		
	}

}
