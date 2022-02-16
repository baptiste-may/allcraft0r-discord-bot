package fr.djredstone.botdiscord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.botdiscord.mysql.DatabaseManager;
import fr.djredstone.botdiscord.mysql.DbConnection;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class Main extends JavaPlugin {
	
	static String token;
	static String tokenMEE6;
	public static String prefix = "!";
	public static String noPermMessage = "Vous n'étes pas une personne de puissance.";
	public static String redstoneEmoji = "<:redstone:503978809645727745>";
	public HashMap<User, Integer> messageByMinute = new HashMap<>();
	
	public List<String> P4startMessageID = new ArrayList<>();
	public HashMap<String, String> P4startMessageUser = new HashMap<>();

	public static DatabaseManager databaseManager;
	
	public static JDA jda;
	public static JDA mee6;
	
	public static Main main;
	
	@Override
	public void onEnable() {
		
		Main.main = this;
		
		new Setup(this);
		
	}
	
	@Override
	public void onDisable() {
		
		jda.shutdownNow();
		
		mee6.shutdownNow();
		
	}
	
	public static int getMoney(User user) throws SQLException {
		
		final DbConnection dbConnection = Main.databaseManager.getDbConnection();

		final Connection connection = dbConnection.getConnection();

		final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, money FROM ALLCRAFT0R_user_money WHERE uuid = ?");
		preparedStatement.setString(1, user.getId().toString());
		final ResultSet resultSet = preparedStatement.executeQuery();

		if (!resultSet.next()) {
			final PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO ALLCRAFT0R_user_money VALUES(?, ?, ?, ?)");
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			preparedStatement1.setString(1, user.getId().toString());
			preparedStatement1.setFloat(2, 100);
			preparedStatement1.setTimestamp(3, timestamp);
			preparedStatement1.setTimestamp(4, timestamp);

			preparedStatement1.executeUpdate();
			
			return 100;
		}
		
		return resultSet.getInt("money");
	}
	
	public static void setMoney(User user, int money) throws SQLException {
		
		getMoney(user);
		
		final DbConnection dbConnection = Main.databaseManager.getDbConnection();

		final Connection connection = dbConnection.getConnection();
		
		final PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE ALLCRAFT0R_user_money SET money = ?, updated_at = ? WHERE uuid = ?");

		preparedStatement1.setInt(1, money);
		preparedStatement1.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
		preparedStatement1.setString(3, user.getId());

		preparedStatement1.executeUpdate();

	}

}
