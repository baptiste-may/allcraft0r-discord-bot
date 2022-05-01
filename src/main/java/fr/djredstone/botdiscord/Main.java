package fr.djredstone.botdiscord;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.botdiscord.mysql.DatabaseManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class Main extends JavaPlugin {

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

	private static Main main;
	public static Main getInstance() { return main; }

	@Override
	public void onEnable() {
		
		main = this;
		
		new Setup(this);
		
	}
	
	@Override
	public void onDisable() {

		databaseManager.close();

		jda.shutdownNow();
		
		mee6.shutdownNow();
		
	}

}
